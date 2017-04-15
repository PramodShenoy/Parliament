package com.example.root.parliament;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class result extends AppCompatActivity {

    ListView lv;
    private SQLiteDatabase db;
    ArrayList<RSPvtBill> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String name,title,min,cat,year;
        Intent i=getIntent();

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        name=i.getStringExtra("name");
        min=i.getStringExtra("ministry");
        title =i.getStringExtra("title");
        cat=i.getStringExtra("category");
        year=i.getStringExtra("year");

        int q=0;
        db=openOrCreateDatabase("system",MODE_PRIVATE,null);

        lv=(ListView) findViewById(R.id.list);

        String abc="SELECT * FROM lspvtbill where ";
        if(!name.equals(""))
        {
                abc=abc+" name='"+name+"'";
                q++;
        }


        if(!min.equals(""))
        {
            if(q==0)
                abc=abc+" ministry='"+min+"'";
            else
                abc=abc+" and "+" ministry='"+min+"'";
            q++;
        }


        if(!title.equals(""))
        {
            if(q==0)
                abc=abc+" title='"+title+"'";
            else
                abc=abc+" and "+" title='"+title+"'";
            q++;
        }

        if(!cat.equals(""))
        {
            if(q==0)
                abc=abc+" category='"+cat+"'";
            else
                abc=abc+" and "+" category='"+cat+"'";
            q++;
        }

        if(!year.equals(""))
        {
            if(q==0)
                abc=abc+" year='"+year+"'";
            else
                abc=abc+" and "+" year='"+year+"'";
            q++;
        }
        //abc=abc+";";
        Log.v("____+++","++++++++++++++++++++++++++"+abc+"++++++++++++++++++++++++");

        Cursor cur= db.rawQuery(abc,null);

        if(cur != null)
        {
           while(cur.moveToNext())
           {
               String a= cur.getString(cur.getColumnIndex("year"));
               String b= cur.getString(cur.getColumnIndex("title"));
               String c= cur.getString(cur.getColumnIndex("name"));
               String d= cur.getString(cur.getColumnIndex("ministry"));
               String e= cur.getString(cur.getColumnIndex("category"));
               Log.v("==++++++==","------"+e+"--------");
               list.add(new RSPvtBill(a,b,c,d,e));
           }
        }

        RSPvtAdapter rsp=new RSPvtAdapter(this,list);
        lv.setAdapter(rsp);




    }
}
