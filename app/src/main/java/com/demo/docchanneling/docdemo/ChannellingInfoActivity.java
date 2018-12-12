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
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ChannellingInfoActivity extends AppCompatActivity {



    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list , RefinedList;
    ArrayAdapter<String> adapter;
    ChannellingInfo channellingInfo;
    String searchItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channelling_info);



        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){

            searchItem = bundle.getString("SearchItem");

        }



        channellingInfo = new ChannellingInfo();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("docChannelling/channelDocInformation");
        list = new ArrayList<>();
        RefinedList = new ArrayList<>();
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, RefinedList);



        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    channellingInfo = dataSnapshot.getValue(ChannellingInfo.class);

                    String newItem = channellingInfo.getDate()+" | "+channellingInfo.getDocName().toString()+" | "+channellingInfo.getChannelCenterName().toString()+" | "+channellingInfo.getChannelSpec().toString();

                    //check if the list is null or not before add new item.
                    if(!list.contains(null)){
                        list.add(newItem);
                    }

                for (String listItem: list) {
                    if(listItem.toUpperCase().contains(searchItem)){
                        RefinedList.add(listItem);
                    }
                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ChannellingInfoActivity.this, ChannelDoctorInfo.class);
                        intent.putExtra("ChannelItem", listView.getItemAtPosition(position).toString());
                        startActivity(intent);
                    }
                });
                list.clear();
                //it must clear the list right after setting the adapter.


                }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                channellingInfo = dataSnapshot.getValue(ChannellingInfo.class);
                list.remove(channellingInfo.getDate()+" | "+channellingInfo.getDocName().toString()+" | "+channellingInfo.getChannelCenterName().toString()+" | "+channellingInfo.getChannelSpec().toString());
                RefinedList.remove(channellingInfo.getDate()+" | "+channellingInfo.getDocName().toString()+" | "+channellingInfo.getChannelCenterName().toString()+" | "+channellingInfo.getChannelSpec().toString());
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });


        listView.setAdapter(null);


//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    channellingInfo = ds.getValue(ChannellingInfo.class);
//                    list.add(channellingInfo.getDocName().toString()+" "+channellingInfo.getChannelCenterName().toString());
//                }
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
