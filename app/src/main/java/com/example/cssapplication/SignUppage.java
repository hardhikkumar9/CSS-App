package com.example.cssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUppage extends AppCompatActivity {

    String [] items = {"Student", "Participant(ATAL)", "Coordinator(ATAL)", "Faculty", "Admin", "Other"};
    String [] items2 = {"Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgar" ," Chandigarh", "Dadar and Nagar Haveli", "Daman and Diu", "Delhi",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshawdeep", "Madhya Pradesh",
            "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Puducherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand"};

    AutoCompleteTextView institutedropdown, statedropdown ;

    ArrayAdapter<String> adapterItems, adapterItems2;

    TextView textviewregisterLogin;

    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextMobileNo;
    CheckBox discheckBox;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_uppage);

        textviewregisterLogin = (TextView) findViewById(R.id.textviewloginregister);

        // DROPDOWN INSTITUTE
        institutedropdown = findViewById(R.id.institute_dropdown);

        adapterItems = new ArrayAdapter<String>(this,R.layout.application_list_item,items);

        institutedropdown.setAdapter(adapterItems);

        institutedropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items, Toast.LENGTH_SHORT).show();
            }
        });

            // DROPDOWN STATE
        statedropdown = findViewById(R.id.state_dropdown);

        adapterItems2 = new ArrayAdapter<String>(this,R.layout.application_list_item,items2);

        statedropdown.setAdapter(adapterItems2);

        statedropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items2= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items2, Toast.LENGTH_SHORT).show();
            }
        });


        editTextUserName = (EditText) findViewById(R.id.textname);
        editTextEmail = (EditText) findViewById(R.id.textemailsignup);
        editTextPassword = (EditText) findViewById(R.id.textpasswordsignup);
        editTextMobileNo = (EditText) findViewById(R.id.textphone);
        discheckBox = findViewById(R.id.checkBox);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }


    public void signupButtonClicked(View v) {

        String txtUserName = editTextUserName.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtMobileNo = editTextMobileNo.getText().toString().trim();

        if (txtUserName.isEmpty()) {
            editTextUserName.setError("Enter User name");
            editTextUserName.requestFocus();
        }

        if (txtEmail.isEmpty()) {
            editTextEmail.setError("Enter Email");
            editTextEmail.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6) {
            editTextPassword.setError("Enter Password");
            editTextPassword.requestFocus();
        }

        if (txtMobileNo.isEmpty()) {
            editTextMobileNo.setError("Enter MobileNo");
            editTextMobileNo.requestFocus();
        }

//        if(discheckBox.isChecked()) {
//                discheckBox.setError("Accept the checkbox");
//                discheckBox.requestFocus();
//        }

//        public void declarbox(View view) {
//
//            if(!discheckBox.isChecked()) {
//                discheckBox.setError("Accept the checkbox");
//                discheckBox.requestFocus();
//            }
//        }


        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    User user = new User(txtUserName, txtEmail, txtPassword, txtMobileNo);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(SignUppage.this, "User Registered Successfully", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(SignUppage.this, SignInpage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUppage.this, "User Failed to Register ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUppage.this, "User Failed to Register ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void textviewregisterlogin(View v) {
        Intent intent = new Intent(this, SignInpage.class);
        startActivity(intent);
    }
}