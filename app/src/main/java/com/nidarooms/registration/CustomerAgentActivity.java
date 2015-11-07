package com.nidarooms.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nidarooms.R;
import com.nidarooms.login.LoginActivity;

/**
 * CustomerAgentActivity for Choose Profile to Create Account.
 *
 * @author Harish
 * @version 1.0
 */
public class CustomerAgentActivity extends AppCompatActivity {

    /** Button for Create Account - Customer */
    Button btnCustomer;

    /** Button for Create Account - Travel Expert */
    Button btnTravelExpert;

    /** Button for Create Account - Taxi Driver */
    Button btnTaxiDriver;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_or_agent);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarCustomerAgent);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerAgentActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Choose Profile");

        btnCustomer = (Button)findViewById(R.id.button_choose_profile_customer);
        btnTravelExpert = (Button)findViewById(R.id.button_choose_profile_travel_expert);
        btnTaxiDriver = (Button)findViewById(R.id.button_choose_profile_taxi_driver);

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCustomerRegistrationActivity = new Intent(CustomerAgentActivity.this, CustomerRegistrationActivity.class);
                startActivity(intentCustomerRegistrationActivity);
            }
        });

        btnTravelExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentTravelExpertActivity = new Intent(CustomerAgentActivity.this, TravelExpertRegistrationActivity.class);
                startActivity(intentTravelExpertActivity);
            }
        });

        btnTaxiDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTaxiDriverActivity = new Intent(CustomerAgentActivity.this, TaxiDriverRegistrationActivity.class);
                startActivity(intentTaxiDriverActivity);
            }
        });
    }
}