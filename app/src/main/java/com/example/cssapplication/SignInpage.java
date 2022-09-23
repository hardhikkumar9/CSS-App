package com.example.cssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInpage extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;
    Button loginbutton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_inpage);

        //DATABASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("New user registered");

        //Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("CSS_Application", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CSS_Application", "Failed to read value.", error.toException());
            }
        });

        editTextEmail = (EditText) findViewById(R.id.editTextSignInEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextSignInPassword);

        textViewRegister = (TextView) findViewById(R.id.txtSignInRegister);
        textViewForgotPassword = (TextView) findViewById(R.id.txtSignInForgotPassword);

        loginbutton =  (Button) findViewById(R.id.button1login);

        mAuth = FirebaseAuth.getInstance();
    }

    public void txtSignInForgotPasswordClicked(View v){
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }

    public void txtSignInRegisterClicked(View v) {
        Intent intent = new Intent(this, SignUppage.class);
        startActivity(intent);
    }

    //////////
    public void loginhome(View v) {

        String txtemail = editTextEmail.getText().toString().trim();
        String txtpassword = editTextPassword.getText().toString().trim();

        if (txtemail.isEmpty()) {
            editTextEmail.setError("Enter Email");
            editTextEmail.requestFocus();
        }

        if (txtpassword.isEmpty() || txtpassword.length() < 10) {
            editTextPassword.setError("Enter Password");
            editTextPassword.requestFocus();
        }

        else {
            Intent intent = new Intent(this, home_page.class);
            startActivity(intent);

        }

    }
    //////


//    public void loginhome(View v) {
//
//
//        String UserName = editTextUserName.getText().toString().trim();
//        String Password = editTextPassword.getText().toString().trim();
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(UserName).matches()) {
//            editTextUserName.setError("Please Enter a Valid Email");
//            editTextUserName.requestFocus();
//        }
//
//        if (editTextPassword.length() < 6) {
//            editTextPassword.setError("Please Enter Correct Password");
//            editTextPassword.requestFocus();
//        }
//
//
//        mAuth.signInWithEmailAndPassword(UserName, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if (task.isSuccessful()) {
//                    Toast.makeText(SignInpage.this, "User Has Successfully Signed In",Toast.LENGTH_LONG).show();
//
//                    startActivity(new Intent(SignInpage.this, home_page.class));
//                }
//
//                else {
//                    Toast.makeText(SignInpage.this, "User is failed Signed In",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//    }
}