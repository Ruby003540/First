package com.Ruby.first;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        String str=(String)inp.getText().toString();
        double c=Double.valueOf(str);
        double f=c*1.8+32;
        out.setText("结果为："+f);
    }
}