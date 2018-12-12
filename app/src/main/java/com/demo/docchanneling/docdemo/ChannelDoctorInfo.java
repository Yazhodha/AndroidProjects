package com.demo.docchanneling.docdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChannelDoctorInfo extends AppCompatActivity {

    TextView chaDoc;
    TextView chaDate;
    TextView chaCenter;
    Button location;
    Button btnBook;

   private String  channelCenter;
   private String docName;
   private String date;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseAuth firebaseAuth;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_doctor_info);

        chaDate  = (TextView) findViewById(R.id.tvChaDate);
        chaDoc = (TextView) findViewById(R.id.tvChaDoc);
        chaCenter = (TextView) findViewById(R.id.tvChaCenter);
        location = (Button) findViewById(R.id.btnLocation1);
        btnBook = (Button) findViewById(R.id.btnBook);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        ref = firebaseDatabase.getReference(firebaseAuth.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                userName = userProfile.getName();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChannelDoctorInfo.this, databaseError.getCode(), Toast.LENGTH_LONG);
            }
        });

        //==============================================================================

          channelCenter = null;
          docName = null;
          date = null;

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){

            String channelItem = bundle.getString("ChannelItem");
            String[] parts = channelItem.split("\\|");

            chaDate.setText(parts[0].trim());
            chaDoc.setText(parts[1].trim());
            chaCenter.setText(parts[2].trim());

            channelCenter = parts[2].trim();
            docName = parts[1].trim();
            date = parts[0].trim();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(ChannelDoctorInfo.this);
                builder.setTitle("Place Your Booking");
                builder.setMessage("Are you sure you want to place a booking?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference databaseReference = firebaseDatabase.getReference("docChannelling/appointments");

                        Appointments appointments = new Appointments(userName, docName, channelCenter, date);

                        databaseReference.push().setValue(appointments);

                        startActivity(new Intent(ChannelDoctorInfo.this, booking.class));
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            }
        });
    }
}
