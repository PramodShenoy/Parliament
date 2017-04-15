package com.example.root.parliament;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    FlowingDrawer mDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);

        InputStream inputStream = getResources().openRawResource(R.raw.attendance);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists attendance (sno integer primary key,name varchar(30),state varchar(30),area varchar(30),signed varchar(10),foreign key(name,state,area) references lsmember(name,state,consti));");
        // db = new DatabaseAtt(this);

        ImageView bac = (ImageView) findViewById(R.id.back);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

        setupMenu();

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
        ArrayList<Attendance> input=new ArrayList<>();
        input=getatt(byteArrayOutputStream.toString());
        ArrayList<Attendance> str=new ArrayList<>();
        for(int j=0;j<input.size();j++)
        {
            Attendance obj=input.get(j);
            //  db.addatt(obj.getName(),obj.getState(),obj.getConstituency());
            ContentValues cv = new ContentValues();
            cv.put("sno",obj.getSeat());
            cv.put("name",obj.getName());
            cv.put("state",obj.getState());
            cv.put("area",obj.getConstituency());
            cv.put("signed",obj.getSigned());
            db.insert("attendance",null,cv);
        }


        Cursor cursor=db.rawQuery("select * from attendance",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String a = cursor.getString(cursor.getColumnIndex("sno"));
                String b = cursor.getString(cursor.getColumnIndex("name"));
                String c = cursor.getString(cursor.getColumnIndex("state"));
                String d = cursor.getString(cursor.getColumnIndex("area"));
                String e = cursor.getString(cursor.getColumnIndex("signed"));
                str.add(new Attendance(a,b,c,d,e));
            }
        }



        ListView lv=(ListView) findViewById(R.id.list);
        AttendanceAdapter attendanceAdapter=new AttendanceAdapter(this,str);
        lv.setAdapter(attendanceAdapter);




    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static ArrayList<Attendance> getatt(String jsonResp) {
        ArrayList<Attendance> list=new ArrayList<>();
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONArray baseJsonResponse = new JSONArray(jsonResp);
            for(int i=0;i<baseJsonResponse.length();i++)
            {
                JSONObject obj=baseJsonResponse.getJSONObject(i);
                String seat=obj.optString("Division/Seat No.");
                String name = obj.optString("Name of Member");
                String constituency=obj.optString("Constituency");
                String state=obj.optString("State");
                String signed=obj.optString("No. of days member signed the register");
                Attendance attendance=new Attendance(seat,name,state,constituency,signed);
                list.add(attendance);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
