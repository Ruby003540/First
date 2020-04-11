package com.Ruby.first;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements Runnable {
    public final String TAG = "Rate";
    private float dollarRate = 0.1f;
    private float euroRate = 0.2f;
    private float wonRate = 0.3f;

    EditText rmb;
    TextView show;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = (EditText) findViewById(R.id.rmb);
        show = (TextView) findViewById(R.id.showOut);
        //获取sp里保存的数据
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        // SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);另外一种获取对象方法，后续操作是相同的

        dollarRate = sp.getFloat("dollar_rate", 0.0f);
        euroRate = sp.getFloat("euro_rate", 0.0f);
        wonRate = sp.getFloat("won_rate", 0.0f);

        Log.i(TAG, "onCreate: dollarRate=" + dollarRate);
        Log.i(TAG, "onCreate: euroRate=" + euroRate);
        Log.i(TAG, "onCreate: wonRate=" + wonRate);

        //开启子线程
        Thread t = new Thread(this);
        t.start();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5) {
                    String srt = (String) msg.obj;
                    Log.i(TAG, "handleMessage: getMessage msg=" + msg);
                    show.setText(srt);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View btn) {
        String str = rmb.getText().toString();
        float r = 0;
        if (str.length() > 0) {
            r = Float.parseFloat(str);
        } else {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "onClick: r=" + r);

        if (btn.getId() == R.id.btn_dollar) {
            show.setText(String.format("%.2f", r * dollarRate));
        } else if (btn.getId() == R.id.btn_euro) {
            show.setText(String.format("%.2f", r * euroRate));
        } else {
            show.setText(String.format("%.2f", r * wonRate));
        }
    }


    public void openConfig() {
        Intent config = new Intent(this, ConfigActivity.class);
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);

        Log.i(TAG, "openOne: dollarRate=" + dollarRate);
        Log.i(TAG, "openOne: euroRate=" + euroRate);
        Log.i(TAG, "openOne: wonRate=" + wonRate);

        // startActivity(config);
        startActivityForResult(config, 123);
    }

    public void openOne(View btn) {
        openConfig();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_set) {
            openConfig();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//requestCode是源窗口的码，rusultCode是目的窗口的码
        if (requestCode == 123 && resultCode == 1234) {

            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar", 0.1f);
            euroRate = bundle.getFloat("key_euro", 0.1f);
            wonRate = bundle.getFloat("key_won", 0.1f);

            Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
            Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonRate=" + wonRate);

            //将新的汇率写入到sp里
            SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat("dollar_rate", dollarRate);
            editor.putFloat("euro_rate", euroRate);
            editor.putFloat("won_rate", wonRate);
            editor.commit();
            Log.i(TAG, "onAcitivityResult: 数据已存入到sp中");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: running...");
        for (int i = 0; i < 6; i++) {
            Log.i(TAG, "run: i=" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //获取message对象用于返回主线程
        Message msg = handler.obtainMessage(5);
        //msg.what=5;效果同上
        msg.obj = "Hello fron run()";
        handler.sendMessage(msg);

        //获取网络数据
        URL url = null;
        try {
            url = new URL("http://www.icbc.com.cn/icbc/%E7%BD%91%E4%B8%8A%E6%B1%87%E5%B8%82/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html=inputStream2String(in);
            Log.i(TAG,"run: html="+html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"GB2312");
        for( ; ; ){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
                out.append(buffer,0,rsz);
            }
            return out.toString();
        }
    }

