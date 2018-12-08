package com.demo.docchanneling.docdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class booking extends AppCompatActivity {

    Button goToHome;
    Button bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        goToHome = (Button) findViewById(R.id.btnGotoHome);
        bookings = (Button) findViewById(R.id.btnBookings);

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(booking.this, SecondActivity.class));
            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(booking.this, MyBookings.class));
            }
        });

    }
}
