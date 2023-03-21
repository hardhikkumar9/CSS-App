package com.example.cssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText editTextEmail;
    TextView textViewLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_forgot_password);

        editTextEmail = (EditText) findViewById(R.id.editTextForgotPasswordEmail);

        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        mAuth =FirebaseAuth.getInstance();
    }

    public void textviewForgotLogin(View v) {
        Intent intent = new Intent(this, SignInpage.class);
        startActivity(intent);
    }

    public void forgotPasswordResetBtnPressed(View v) {

        resetPassword();

    }

    private void resetPassword() {

        String txtEmail = editTextEmail.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
            editTextEmail.setError("Please Enter Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "Please Check Your Email to Reset Password", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword.this, SignInpage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgotPassword.this, "Failed to Reset Password", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}