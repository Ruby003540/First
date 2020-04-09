package com.Ruby.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CountActicity extends AppCompatActivity {
    TextView scoreA,scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        scoreA=(TextView)findViewById(R.id.scoreA);
        scoreB=(TextView)findViewById(R.id.scoreB);

    }
    public void btnadd1(View btn) {
        if(btn.getId()==R.id.button3A){
            showscore(1);
        }else {
            showscore2(1);
        }
    }
    public void btnadd2(View btn) {
        if(btn.getId()==R.id.button4A){
            showscore(2);
        }else {
            showscore2(2);
        }
    }
    public void btnadd3(View btn) {
        if(btn.getId()==R.id.button5A){
            showscore(3);
        }else {
            showscore2(3);
        }
    }
    public void reset(View btn){
       scoreA.setText("0");
       scoreB.setText("0");
    }
    public void showscore(int i){
        Log.i("show","result="+i);

        String oldScore=(String)scoreA.getText();
        int newscore=Integer.parseInt(oldScore)+i;
        scoreA.setText(""+newscore);
    }

    public void showscore2(int i){
        Log.i("show","result="+i);

        String oldScore=(String)scoreB.getText();
        int newscore=Integer.parseInt(oldScore)+i;
        scoreB.setText(""+newscore);
    }
}