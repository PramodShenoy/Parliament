package com.example.root.parliament;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LSPvtActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lspvt);
        InputStream inputStream = getResources().openRawResource(R.raw.lspvtbill);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LSPvtActivity.this,search.class));
            }
        });
        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists lspvtbill (year varchar(30),title varchar(30),name varchar(50),ministry varchar(20),category varchar(30),primary key(year,title),foreign key(name) references lsmember(name));");
        // db = new DatabaseAtt(this);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<RSPvtBill> input=new ArrayList<>();
        input=getlsg(byteArrayOutputStream.toString());
        ArrayList<RSPvtBill> str=new ArrayList<>();
        for(int j=0;j<input.size();j++)
        {
            RSPvtBill obj=input.get(j);
            ContentValues cv = new ContentValues();
            cv.put("year",obj.getYear());
            cv.put("title",obj.getTitle());
            cv.put("ministry",obj.getMinistry());
            cv.put("name",obj.getName());
            cv.put("category",obj.getCategory());
            db.insert("lspvtbill",null,cv);
        }



        Cursor cursor=db.rawQuery("select * from lspvtbill",null);


        int j=0;
        if(cursor != null)
        {
            while(cursor.moveToPosition(j))
            {
                String a = cursor.getString(cursor.getColumnIndex("year"));
                String b = cursor.getString(cursor.getColumnIndex("title"));
                String e = cursor.getString(cursor.getColumnIndex("name"));
                String c = cursor.getString(cursor.getColumnIndex("ministry"));
                String d = cursor.getString(cursor.getColumnIndex("category"));
                str.add(new RSPvtBill(a,b,e,c,d));
                j++;
                if(j==100)
                    break;
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        RSPvtAdapter lsGovAdapter=new RSPvtAdapter(this,str);
        lv.setAdapter(lsGovAdapter);





    }


    private static ArrayList<RSPvtBill> getlsg(String jsonResp) {
        ArrayList<RSPvtBill> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String year=obj.optString("Bill Year");
                String title = obj.optString("Short Title of Bill");
                String min =obj.optString("Ministry");
                String cat=obj.optString("Bill Category");
                String name =obj.optString("Member Name");
                RSPvtBill lsGovBills=new RSPvtBill(year,title,name,min,cat);
                list.add(lsGovBills);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
