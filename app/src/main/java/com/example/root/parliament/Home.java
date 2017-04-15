package com.example.root.parliament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout text11 = (LinearLayout) findViewById(R.id.text11);
        LinearLayout text12 = (LinearLayout) findViewById(R.id.text12);
        LinearLayout text13 = (LinearLayout) findViewById(R.id.text13);
        LinearLayout text21 = (LinearLayout) findViewById(R.id.text21);
        LinearLayout text22 = (LinearLayout) findViewById(R.id.text22);
        LinearLayout text23 = (LinearLayout) findViewById(R.id.text23);
        LinearLayout text31 = (LinearLayout) findViewById(R.id.text31);
        LinearLayout text32 = (LinearLayout) findViewById(R.id.text32);
        LinearLayout text33 = (LinearLayout) findViewById(R.id.text33);


        text21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MainActivity.class);

                startActivity(i);
            }
        });
        text11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LSMemberActivity.class);

                startActivity(i);


            }
        });
        text12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LSGovActivity.class);

                startActivity(i);


            }
        });
        text13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LSPvtActivity.class);

                startActivity(i);

            }
        });

        text22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, RSMemberActivity.class);

                startActivity(i);
            }
        });

        text23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this, RSGovActivity.class));

            }
        });

        text31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this, RSPvtActivity.class));
            }
        });

        text32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, TimeLostActivity.class));

            }
        });

        text33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Admin.class));

            }
        });
    }
}
