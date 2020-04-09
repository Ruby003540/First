package com.Ruby.first;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView out;
    EditText inp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inp=(EditText) findViewById(R.id.input);
        out=(TextView)findViewById(R.id.output);

    }
    public void onClick(View btn) {
        Log.i("main","computing....");
         String str=" ";
        if(inp.length()>0) {
             str = (String) inp.getText().toString();
        }else{
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        double c=Double.valueOf(str);
        double f=c*1.8+32;
        out.setText("结果为："+f);
    }
}