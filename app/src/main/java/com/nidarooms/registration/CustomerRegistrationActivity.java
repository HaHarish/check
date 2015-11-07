package com.nidarooms.registration;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.nidarooms.R;
import com.nidarooms.baseadapter.CountryCodeSelectionBaseAdapter;
import com.nidarooms.otp.VerifyingOtpActivity;
import com.nidarooms.pojo.Pojo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Customer Registration Activity to Create Account for Customer.
 * The User have to fill the form and submit.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class CustomerRegistrationActivity extends AppCompatActivity {

    /** EditText for Enter Customer Name */
    EditText etCustomerName;

    /** EditText for Enter Customer Email */
    EditText etCustomerEmail;

    /** EditText for Choose Country Code */
    EditText etCountryCode;

    /** EditText for enter Customer Phone Number */
    EditText etCustomerPhone;

    /** Button for Create Account - Customer */
    Button btnCreateAccount;

    /** ListView for Choose Country Code */
    ListView lvCountryNameCode;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    ArrayList details;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    /** Get Customer Name */
    String mCustomerName;

    /** Get Customer Email */
    String mCustomerEmail;

    /** Get Customer Phone Number */
    String mCustomerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        // Assing Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarCustomerRegistration);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerRegistrationActivity.this, CustomerAgentActivity.class);
                startActivity(i);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Customer Registration");

        etCountryCode = (EditText)findViewById(R.id.edittext_customer_user_mobile_number_code);
        etCountryCode.setFocusable(false);
        etCountryCode.setClickable(true);

        etCustomerName = (EditText)findViewById(R.id.edittext_customer_user_name);
        etCustomerEmail = (EditText)findViewById(R.id.edittext_customer_user_email);
        etCustomerPhone = (EditText)findViewById(R.id.edittext_customer_user_mobile_number);

        btnCreateAccount = (Button)findViewById(R.id.button_customer_create_account);

        lvCountryNameCode = new ListView(this);

        ArrayList<Pojo> results = new ArrayList<Pojo>();
        Pojo data = new Pojo();

        data.setCountryName("Indonesia");
        data.setCountryCode("+62");
        results.add(data);

        details = results;
        lvCountryNameCode.setAdapter(new CountryCodeSelectionBaseAdapter(CustomerRegistrationActivity.this, details));

        builder = new AlertDialog.Builder(CustomerRegistrationActivity.this);
        builder.setView(lvCountryNameCode);
        alertDialog = builder.create();

        etCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
            }
        });

        lvCountryNameCode.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                TextView countryCode = (TextView) arg1.findViewById(R.id.textview_country_code);
                etCountryCode.setText(countryCode.getText());
                alertDialog.cancel();

            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCustomerName = etCustomerName.getText().toString();
                mCustomerEmail = etCustomerEmail.getText().toString();
                mCustomerPhone = etCustomerPhone.getText().toString();

                if (mCustomerName.length() > 0) {
                    if (isValidEmail(mCustomerEmail)) {
                        if (mCustomerPhone.length() == 10) {

                            Intent intentOtpActivity = new Intent(CustomerRegistrationActivity.this, VerifyingOtpActivity.class);
                            startActivity(intentOtpActivity);

                        } else {
                            callAlertDialog("Enter Valid Phone Number");
                        }

                    } else {
                        callAlertDialog("Enter Valid Email");
                    }

                } else {
                    callAlertDialog("Enter Name");
                }
            }
        });
    }

    /**
     * Email Validation
     *
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {

        // General Email Validation
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * AlertDialog for Error Message
     *
     * @param alertMessage
     */
    public void callAlertDialog(String alertMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CustomerRegistrationActivity.this);
    //  builder.setTitle(alertHeading);
        builder.setMessage(alertMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                // Show location settings when the user acknowledges the alert dialog

            }
        });
        Dialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

}