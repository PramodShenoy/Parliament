package com.example.root.parliament;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

public class LSGovActivity extends Activity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsgovbill);
        InputStream inputStream = getResources().openRawResource(R.raw.lsgovbill);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists lsgovbill (year string,title varchar(30),ministry varchar(20),category varchar(30),primary key(year,title));");
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
        ArrayList<LSGovBills> input=new ArrayList<>();
        input=getlsg(byteArrayOutputStream.toString());
        ArrayList<LSGovBills> str=new ArrayList<>();
        for(int j=0;j<100;j++)
        {
            LSGovBills obj=input.get(j);
            //  db.addatt(obj.getName(),obj.getState(),obj.getConstituency());
            ContentValues cv = new ContentValues();
            cv.put("year",obj.getYear());
            cv.put("title",obj.getTitle());
            cv.put("ministry",obj.getMinistry());
            cv.put("category",obj.getCategory());
            db.insert("lsgovbill",null,cv);
        }



        Cursor cursor=db.rawQuery("select * from lsgovbill",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String a = cursor.getString(cursor.getColumnIndex("year"));
                String b = cursor.getString(cursor.getColumnIndex("title"));
                String c = cursor.getString(cursor.getColumnIndex("ministry"));
                String d = cursor.getString(cursor.getColumnIndex("category"));
                str.add(new LSGovBills(a,b,c,d));
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        LSGovAdapter lsGovAdapter=new LSGovAdapter(this,str);
        lv.setAdapter(lsGovAdapter);





    }


    private static ArrayList<LSGovBills> getlsg(String jsonResp) {
        ArrayList<LSGovBills> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String seat=obj.optString("Year");
                String name = obj.optString("Short Title of Bill");
                String constituency=obj.optString("Ministry");
                String state=obj.optString("Bill Category");
                LSGovBills lsGovBills=new LSGovBills(seat,name,state,constituency);
                list.add(lsGovBills);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}