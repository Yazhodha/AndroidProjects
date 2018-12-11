package com.demo.docchanneling.docdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChannelDoctorInfo extends AppCompatActivity {

    TextView chaDoc;
    TextView chaDate;
    TextView chaCenter;
    Button location;
    Button btnBook;
    String  channelCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_doctor_info);

        chaDate  = (TextView) findViewById(R.id.tvChaDate);
        chaDoc = (TextView) findViewById(R.id.tvChaDoc);
        chaCenter = (TextView) findViewById(R.id.tvChaCenter);
        location = (Button) findViewById(R.id.btnLocation);
        btnBook = (Button) findViewById(R.id.btnBook);

          channelCenter = null;
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){

            String channelItem = bundle.getString("ChannelItem");
            String[] parts = channelItem.split("\\|");

            chaDate.setText(parts[0].trim());
            chaDoc.setText(parts[1].trim());
            chaCenter.setText(parts[2].trim());
            channelCenter = parts[2].trim();
        }
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChannelDoctorInfo.this, MapsActivity.class);
                intent.putExtra("ChannelCenter", channelCenter);
                startActivity(intent);
//                startActivity(new Intent(ChannelDoctorInfo.this, MapsActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChannelDoctorInfo.this, booking.class));
            }
        });
    }
}
