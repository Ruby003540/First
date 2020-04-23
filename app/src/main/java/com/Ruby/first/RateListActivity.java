package com.Ruby.first;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends ListActivity implements  Runnable{
    public final String TAG = "Rate";
    String data[]={"one","two","three"};
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);   ListActivity中已经包含有用于填充界面的内容，故此句需要注释掉

        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data);//android.R.layout.simple_expandable_list_item_1安卓平台提供的列表布局
        setListAdapter(adapter);
        Thread t=new Thread();
        t.start();

       handler =new Handler(){
           @Override
           public void handleMessage(@NonNull Message msg) {
               if(msg.what==5){
                   List<String> list2=(List<String>)msg.obj;
                   ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this,android.R.layout.simple_expandable_list_item_1,list2);//android.R.layout.simple_expandable_list_item_1安卓平台提供的列表布局
                   setListAdapter(adapter);
               }
               super.handleMessage(msg);

           }
       };


    }

    @Override
    public void run() {
        //获取网络数据,放入到list中，并返回主线程
        List<String> retList =new ArrayList<String>();
        Document doc =null;
        try {
            Thread.sleep(3000);
            doc = Jsoup.connect("http://www.boc.cn/sourcedb/whpj/").get();//可以直接使用网址访问，就不需要176-187行的代码了。从网页获取源代码

            Log.i(TAG,"run: "+doc.title());
            Elements tables =  doc.getElementsByTag("table");//从源代码获取table
            Element table2 =tables.get(1);
            //获取td中的数据
            Elements tds=table2.getElementsByTag("td");//从table获取td
            for(int i=0;i<tds.size();i+=8){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String str1=td1.text();
                String val=td2.text();
                Log.i(TAG,"run: text"+str1+"==>"+val);

                retList.add(str1+"==>"+val);
            }
        }catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(5);
        //msg.what=5;效果同上
        msg.obj = retList;
        handler.sendMessage(msg);

    }
}
