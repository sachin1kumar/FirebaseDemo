package com.example.sachin.firebaseauthdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText email, password;
    private Button signup;
    private String emailid = "", pass = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);

        signup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailid = email.getText().toString();
                pass = password.getText().toString();

                //checking if email and passwords are empty
                if (TextUtils.isEmpty(emailid)) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                //if the email and password are not empty
                //displaying a progress dialog

                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();

                //creating new user.
                firebaseAuth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            Toast.makeText(MainActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                });
            }
        });
    }
}
