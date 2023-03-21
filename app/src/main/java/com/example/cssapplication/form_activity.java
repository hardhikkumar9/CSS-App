package com.example.cssapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class form_activity extends AppCompatActivity {

    String [] items = {"Suggestion", "Enquiry", "Grievance", "Complaint", "Appreciation", "Required Support", "Willing to Support"};
    String [] items2 = {"Approval Process", "AQIS", "PG Scholarship", "ADF", "Pragati", "Saksham", "Swanath", "PMSSS", "Any Other", "FIT India Initiative", "Margdarshan", "SLA", "KARMA", "PMKVY-TI" };
    String [] items3 = {"Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgar" ," Chandigarh", "Dadar and Nagar Haveli", "Daman and Diu", "Delhi",
                        "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshawdeep", "Madhya Pradesh",
                        "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Puducherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand"};
    String [] items4 = {"Issue in Login credentials","Application form unable to submit", "Attendance related", "Fellowship related", "Any Non-technical queries", "Any technical queries", "Other"};

    AutoCompleteTextView autoapplicationdropdown, schemedropdown, statedropdown, querydropdown;

    ArrayAdapter<String> adapterItems, adapterItems2, adapterItems3, adapterItems4;

    //FOR APPLICATION DROPDOWN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_form_activity);

        autoapplicationdropdown = findViewById(R.id.application_dropdown);

        adapterItems = new ArrayAdapter<String>(this,R.layout.application_list_item,items);

        autoapplicationdropdown.setAdapter(adapterItems);

        autoapplicationdropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items, Toast.LENGTH_SHORT).show();
            }
        });


        //FOR SCHEME DROPDOWN

        schemedropdown = findViewById(R.id.scheme_name_dropdown);

        adapterItems2 = new ArrayAdapter<String>(this,R.layout.application_list_item,items2);

        schemedropdown.setAdapter(adapterItems2);

        schemedropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items2= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items2, Toast.LENGTH_SHORT).show();
            }
        });

        // FOR INSTITUTE NAME / UT

        statedropdown = findViewById(R.id.state_dropdown);

        adapterItems3 = new ArrayAdapter<String>(this,R.layout.application_list_item,items3);

        statedropdown.setAdapter(adapterItems3);

        statedropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items3= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items3, Toast.LENGTH_SHORT).show();
            }
        });

        // FOR QUERY

        querydropdown = findViewById(R.id.query_dropdown);

        adapterItems4 = new ArrayAdapter<String>(this,R.layout.application_list_item,items4);

        querydropdown.setAdapter(adapterItems4);

        querydropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items4= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items4, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submitquery(View view) {
        Toast.makeText(form_activity.this, "Query sent successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}