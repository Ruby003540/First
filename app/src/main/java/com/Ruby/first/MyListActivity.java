package com.Ruby.first;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        ListView listview=(ListView)findViewById(R.id.mylist);
        String data[]={"111","222"};
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data);//android.R.layout.simple_expandable_list_item_1安卓平台提供的列表布局
        listview.setAdapter(adapter);//对控件使用adapter
    }
}
