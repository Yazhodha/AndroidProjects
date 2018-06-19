package com.demo.docchanneling.docdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private TextView Register;
    private int counter =5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etUserpassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (TextView) findViewById(R.id.tvRegister);

        Info.setText("No of attempts remaining 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //Checking if there's a user already logged in..
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if(user !=null){
//            finish(); // distroys the current activity.
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Your credentials are being verified!");

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    Toast.makeText(MainActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter --;
                    Info.setText("No of attempts remaining: "+ String.valueOf(counter));
                    progressDialog.dismiss();
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });


        //Below Code is just for testing purposes ======================================
        //==============================================================================
//        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//        }else{
//            counter --;
//            Info.setText("No of attempts remaining: "+ String.valueOf(counter));
//            if(counter == 0){
//                Login.setEnabled(false);
//            }
//        }
    }
}















