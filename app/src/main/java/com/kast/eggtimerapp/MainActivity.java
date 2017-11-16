package com.kast.eggtimerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void startAndStop (View view) {

        final Button startAndStop = (Button)findViewById(R.id.startAndStop);
        startAndStop.setText("Go!");
        startAndStop.setTag(1);

        startAndStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int status = (Integer) v.getTag();

                if (status == 1) {

                    // Timer textview .start(); here

                    startAndStop.setText("Stop");
                    v.setTag(0); // Stop

                } else {

                    startAndStop.setText("Go!");
                    v.setTag(1);

                }

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
