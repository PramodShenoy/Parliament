package com.example.root.parliament;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class success extends AppCompatActivity {

    EditText year,ministry,name,title,category;
    Button sub;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        year=(EditText)findViewById(R.id.editText5);
        title=(EditText)findViewById(R.id.editText6);
        ministry=(EditText)findViewById(R.id.editText7);
        category=(EditText)findViewById(R.id.editText8);
        name=(EditText)findViewById(R.id.editText9);
        sub=(Button)findViewById(R.id.button3);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        db=openOrCreateDatabase("system",MODE_PRIVATE,null);
       // db.execSQL("create table if not exists student (name varchar(30),password varchar(30))");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String y = year.getText().toString().trim();
                String t = title.getText().toString().trim();
                String m = ministry.getText().toString().trim();
                String c = category.getText().toString().trim();
                String n = name.getText().toString().trim();



                db.execSQL("insert into lspvtbill values ('" + y + "','" + t+ "','"+ n+ "','"+m+ "','"+c+ "')");


                Toast.makeText(success.this, "INSERTED ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
