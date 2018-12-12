package com.demo.docchanneling.docdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyBookings extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    private String userName;

    ListView listView;
    ArrayList<String> list, RefinedList;
    ArrayAdapter<String> adapter;
    ConfirmedBookingInfo confirmedBookingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        confirmedBookingInfo = new ConfirmedBookingInfo();
        ref = firebaseDatabase.getReference(firebaseAuth.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                userName = userProfile.getName();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyBookings.this, databaseError.getCode(), Toast.LENGTH_LONG);
            }
        });

        DatabaseReference databaseReference = firebaseDatabase.getReference("docChannelling/confirmedAppointments");

        listView = findViewById(R.id.bookingListView);
        list = new ArrayList<>();
        RefinedList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                confirmedBookingInfo = dataSnapshot.getValue(ConfirmedBookingInfo.class);

                String newItem = confirmedBookingInfo.getDateOfAppointment()+" | "+confirmedBookingInfo.getCustomerName().toString()+" | "+confirmedBookingInfo.getDoctorName().toString()+" | "+confirmedBookingInfo.getChannelCenterName().toString()+" | "+confirmedBookingInfo.getTime().toString();

//                list.add(newItem);
                //check if the list is null or not before add new item.
//                if(!list.contains(null)){
                    list.add(newItem);
//             }

//                for (String listItem: list) {
//                    if(listItem.toUpperCase().contains("")){
//                        RefinedList.add(listItem);
//                    }
//                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(MyBookings.this, BookingDoctorInfo.class);
//                        intent.putExtra("ChannelItem", listView.getItemAtPosition(position).toString());
//                        startActivity(intent);
                    }
                });
//                list.clear();
                //it must clear the list right after setting the adapter.
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
