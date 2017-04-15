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

public class TimeLostActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_lost);
        InputStream inputStream = getResources().openRawResource(R.raw.timelost);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists timelost (session varchar(10) primary key,hours float,lost float,issues number);");
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
        ArrayList<TimeLost> input=new ArrayList<>();
        input=getlsg(byteArrayOutputStream.toString());
        ArrayList<TimeLost> str=new ArrayList<>();
        for(int j=0;j<input.size();j++)
        {
            TimeLost obj=input.get(j);
            //  db.addatt(obj.getName(),obj.getState(),obj.getConstituency());
            ContentValues cv = new ContentValues();
            cv.put("session",obj.getSession());
            cv.put("hours",obj.getSitting_hour());
            cv.put("lost",obj.getDisruption());
            cv.put("issues",obj.getRaised());
            db.insert("timelost",null,cv);
        }

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Cursor cursor=db.rawQuery("select * from timelost",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String a = cursor.getString(cursor.getColumnIndex("session"));
                String b = cursor.getString(cursor.getColumnIndex("hours"));
                String c = cursor.getString(cursor.getColumnIndex("lost"));
                String d = cursor.getString(cursor.getColumnIndex("issues"));
                str.add(new TimeLost(a,b,c,d));
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        TimeLostAdapter lsGovAdapter=new TimeLostAdapter(this,str);
        lv.setAdapter(lsGovAdapter);





    }


    private static ArrayList<TimeLost> getlsg(String jsonResp) {
        ArrayList<TimeLost> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String session=obj.optString("Sessions");
                String hrs = obj.optString("Sitting Hours");
                String lost =obj.optString("Time Lost Due to Disruptions or Forced Adjournments (hours)");
                String issue=obj.optString("No. of Matters of Urgent Public Importance Raised During Zero Hour");
                TimeLost lsGovBills=new TimeLost(session,hrs,lost,issue);
                list.add(lsGovBills);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
