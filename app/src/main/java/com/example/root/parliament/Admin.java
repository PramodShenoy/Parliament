package com.example.root.parliament;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    Button save,login;
    EditText et1,et2,et3,et4;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       //*/ FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        ImageView bac = (ImageView) findViewById(R.id.back);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);

        //save=(Button)findViewById(R.id.button);
        login=(Button)findViewById(R.id.button2);

        db=openOrCreateDatabase("college",MODE_PRIVATE,null);
        db.execSQL("create table if not exists student (name varchar(30),password varchar(30))");
        db.execSQL("insert into student values ('" + "admin" + "','" + "admin" + "')");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et3.getText().toString().trim();
                String password = et4.getText().toString().trim();
                Cursor cursor=db.rawQuery("select * from student where name='" + name +"' and password='"+password+"'", null);
                boolean status=cursor.moveToNext();
                if(status)
                {
                    Toast.makeText(Admin.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Admin.this,success.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Admin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });






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
}
