package com.Ruby.first;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CountActicity extends AppCompatActivity {
    TextView scoreA,scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        scoreA=(TextView)findViewById(R.id.scoreA);
        scoreB=(TextView)findViewById(R.id.scoreB);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String scorea = (String)scoreA.getText();
        String scoreb =(String)scoreB.getText();
        Log.i("run","onSave");
        outState.putString("scorea",scorea);
        outState.putString("scoreb",scoreb);
    }//旋转之前先把当前数据存在Bundle

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea = savedInstanceState.getString("scorea");
        String scoreb = savedInstanceState.getString("scoreb");
        Log.i("run","onRestored");
        scoreA.setText(scorea);
        scoreB.setText(scoreb);

    }//旋转之后将数据从Bundle中取出来

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