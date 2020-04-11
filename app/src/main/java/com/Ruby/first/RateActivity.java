package com.Ruby.first;

import android.content.Intent;
import android.os.Bundle;
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

public class RateActivity extends AppCompatActivity {
    public final String TAG = "Rate";
    private float dollarRate=0.1f;
    private float euroRate=0.2f;
    private float wonRate=0.3f;

      EditText rmb;
      TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb=(EditText)findViewById(R.id.rmb);
        show=(TextView)findViewById(R.id.showOut);
    }

    public void onClick(View btn){
        String str=rmb.getText().toString();
        float r=0;
        if(str.length()>0) {
            r = Float.parseFloat(str);
        }else {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG,"onClick: r="+r);

        if(btn.getId()==R.id.btn_dollar){
            show.setText(String.format("%.2f",r*dollarRate));
        }else if(btn.getId()==R.id.btn_euro){
            show.setText(String.format("%.2f",r*euroRate));
        }else {
            show.setText(String.format("%.2f",r*wonRate));
        }
    }

    public void openOne(View btn){
        openConfig();
    }
    public void openConfig(){
        Intent config=new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i(TAG,"openOne: dollarRate="+dollarRate);
        Log.i(TAG,"openOne: euroRate="+euroRate);
        Log.i(TAG,"openOne: wonRate="+wonRate);

       // startActivity(config);
        startActivityForResult(config,123);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
           openConfig();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//requestCode是源窗口的码，rusultCode是目的窗口的码
        if(requestCode==123&&resultCode==1234){

            Bundle bundle=data.getExtras();
            dollarRate=bundle.getFloat("key_dollar",0.1f);
            euroRate=bundle.getFloat("key_euro",0.1f);
            wonRate=bundle.getFloat("key_won",0.1f);

            Log.i(TAG,"onActivityResult: dollarRate="+dollarRate);
            Log.i(TAG,"onActivityResult: euroRate="+euroRate);
            Log.i(TAG,"onActivityResult: wonRate="+wonRate);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
