package com.Ruby.first;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkActivity extends ListActivity implements  Runnable {
    EditText keyword;
    Handler handler;
    String title;
    String href;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        Thread t = new Thread();
        t.start();

        //获取当前系统时间
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr = sdf.format(today);


        handler =new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==5){
                    List<String> list2=(List<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(WorkActivity.this,android.R.layout.simple_expandable_list_item_1,list2);//android.R.layout.simple_expandable_list_item_1安卓平台提供的列表布局
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);

            }
        };
    }

    public void btnsearch(View btn){
        String str = keyword.getText().toString();
        if (str.length() > 0) {
            List<String> retList =new ArrayList<String>();
            Document doc =null;
            try {
                Thread.sleep(3000);
                doc = Jsoup.connect("http://it.swufe.edu.cn/index/tzgg.htm").get();

                Elements lis =  doc.getElementsByTag("li");
                for(int i=0;i<lis.size();i++){
                   title=lis.text();
                    if(title.contains(str)) {
                        retList.add(title);
                    }
                }
            }catch(IOException | InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage(5);
            msg.obj = retList;
            handler.sendMessage(msg);

        } else {
            Toast.makeText(this, "请输入查询内容", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void run() {


    }
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        Object itemPosition=getListView().getItemAtPosition(position);
        Intent web=new Intent(Intent.ACTION_VIEW, Uri.parse(href));
    }

}
