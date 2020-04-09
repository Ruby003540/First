package com.Ruby.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class count extends AppCompatActivity {
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        score=(TextView)findViewById(R.id.score);
    }
    public void btnadd1(View btn) {
        showscore(1);
    }
    public void btnadd2(View btn) {
        showscore(2);
    }
    public void btnadd3(View btn) {
        showscore(3);
    }
    public void reset(View btn){
        score.setText("0");

    }
    public void showscore(int i){
        Log.i("show","result="+i);

        String oldScore=(String)score.getText();
        int newscore=Integer.parseInt(oldScore)+i;
        score.setText(""+newscore);
    }
}