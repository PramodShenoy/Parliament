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

public class LSMemberActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsmember);
        InputStream inputStream = getResources().openRawResource(R.raw.lsmembers);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists lsmember (name varchar(50),party varchar(10),state varchar(20),consti varchar(30),primary key(name,party,consti));");


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
        ArrayList<LSMember> input=new ArrayList<>();
        input=getlsg(byteArrayOutputStream.toString());
        ArrayList<LSMember> str=new ArrayList<>();
        for(int j=0;j<input.size();j++)
        {
            LSMember obj=input.get(j);
            ContentValues cv = new ContentValues();
            cv.put("name",obj.getName());
            cv.put("party",obj.getParty());
            cv.put("state",obj.getState());
            cv.put("consti",obj.getConsti());
            db.insert("lsmember",null,cv);
        }



        Cursor cursor=db.rawQuery("select * from lsmember",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String a = cursor.getString(cursor.getColumnIndex("name"));
                String b = cursor.getString(cursor.getColumnIndex("party"));
                String c = cursor.getString(cursor.getColumnIndex("state"));
                String d = cursor.getString(cursor.getColumnIndex("consti"));
                str.add(new LSMember(a,b,c,d));
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        LSMemberAdapter lsGovAdapter=new LSMemberAdapter(this,str);
        lv.setAdapter(lsGovAdapter);





    }


    private static ArrayList<LSMember> getlsg(String jsonResp) {
        ArrayList<LSMember> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String party=obj.optString("Party");
                String consti = obj.optString("Constituencies");
                String cat=obj.optString("State");
                String name =obj.optString("Name");
                LSMember lsGovBills=new LSMember(name,party,cat,consti);
                list.add(lsGovBills);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
