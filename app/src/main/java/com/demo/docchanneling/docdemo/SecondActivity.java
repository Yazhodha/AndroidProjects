package com.demo.docchanneling.docdemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FirebaseAuth firebaseAuth;
    private Button search;
    private EditText etSearch;
//    EditText pickDate;

    //this overrided method will take care the implementation of the app menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //handles the onClick events of items in the created menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu: {
                LogOut();
                break;
            }
            case R.id.profileMenu: {
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
                break;
            }
            case R.id.bookingsMenu: {
                startActivity(new Intent(SecondActivity.this, MyBookings.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void LogOut(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, year);
//        c.set(Calendar.MONTH, month);
//        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDateString = df.format(c.getTime());
//        pickDate.setText(currentDateString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
        etSearch = (EditText) findViewById(R.id.etSearch);
        search = (Button) findViewById(R.id.btnSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SecondActivity.this, ChannellingInfoActivity.class));

//                if(etSearch.getText()==null){
//                    Intent intent = new Intent(SecondActivity.this, ChannellingInfoActivity.class);
//                    intent.putExtra("SearchItem", etSearch.getText().toString().trim());
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(SecondActivity.this, "Please enter a search item in the search box", Toast.LENGTH_SHORT).show();
//                }
                if (etSearch.getText().toString().matches("")) {
                    Toast.makeText(SecondActivity.this, "Please enter a search item in the search box", Toast.LENGTH_SHORT).show();
                    return;
                }else{
//                    Toast.makeText(SecondActivity.this, etSearch.getText().toString(), Toast.LENGTH_SHORT).show();
//                    return;
                    Intent intent = new Intent(SecondActivity.this, ChannellingInfoActivity.class);
                    intent.putExtra("SearchItem", etSearch.getText().toString().toUpperCase().trim());
                    startActivity(intent);
                }

            }
        });

//        pickDate = findViewById(R.id.etPickDate);
//        pickDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment datePicker = new DatePickerFragment();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
