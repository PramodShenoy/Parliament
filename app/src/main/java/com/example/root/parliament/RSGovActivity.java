package com.example.root.parliament;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

public class RSGovActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsgov);
        InputStream inputStream = getResources().openRawResource(R.raw.rsgovbill);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists rsgovbill (year string,title varchar(30),ministry varchar(20),category varchar(30),primary key(year,title));");
        // db = new DatabaseAtt(this);

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
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ArrayList<RSGovBills> input=new ArrayList<>();
        input=getlsg(byteArrayOutputStream.toString());
        ArrayList<RSGovBills> str=new ArrayList<>();
        for(int j=0;j<100;j++)
        {
            RSGovBills obj=input.get(j);
            //  db.addatt(obj.getName(),obj.getState(),obj.getConstituency());
            ContentValues cv = new ContentValues();
            cv.put("year",obj.getYear());
            cv.put("title",obj.getTitle());
            cv.put("ministry",obj.getMinistry());
            cv.put("category",obj.getCategory());
            db.insert("rsgovbill",null,cv);
        }



        Cursor cursor=db.rawQuery("select * from rsgovbill",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String a = cursor.getString(cursor.getColumnIndex("year"));
                String b = cursor.getString(cursor.getColumnIndex("title"));
                String c = cursor.getString(cursor.getColumnIndex("ministry"));
                String d = cursor.getString(cursor.getColumnIndex("category"));
                str.add(new RSGovBills(a,b,c,d));
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        RSGovAdapter lsGovAdapter=new RSGovAdapter(this,str);
        lv.setAdapter(lsGovAdapter);





    }


    private static ArrayList<RSGovBills> getlsg(String jsonResp) {
        ArrayList<RSGovBills> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String seat=obj.optString("Bill Year");
                String name = obj.optString("Short Title of Bill");
                String constituency=obj.optString("Ministry");
                String state=obj.optString("Bill Category");
                RSGovBills lsGovBills=new RSGovBills(seat,name,state,constituency);
                list.add(lsGovBills);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
