package com.nidarooms.registration;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nidarooms.R;
import com.nidarooms.baseadapter.CountryCodeSelectionBaseAdapter;
import com.nidarooms.otp.VerifyingOtpActivity;
import com.nidarooms.pojo.Pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Taxi Driver Registration Activity for Create Account - Taxi Driver.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class TaxiDriverRegistrationActivity extends AppCompatActivity {

    // EditText for Enter Name
    EditText etName;

    // EditText for Enter Email
    EditText etEmail;

    // EditText for Enter Date of Birth
    EditText etDateOfBirth;

    // EditText for Enter User Name
    EditText etUserName;

    // EditText for Enter Password
    EditText etPassword;

    // EditText for Enter Confirm Password
    EditText etConfirmPassword;

    // EditText for Enter Driving Licence Number
    EditText etDrivingLicenceNumber;

    // EdiText for Enter Badge Number
    EditText etBadgeNumber;

    // EditText for Enter Mobile Number
    EditText etMobileNumber;

    // EditText for Choose Country
    EditText etCountry;

    // EditText for Choose State
    EditText etState;

    // EditText for Choose City
    EditText etCity;

    // EditText for Enter Zipcode
    EditText etZipcode;

    // EditText for Choose Country Code
    EditText etCountryCode;

    // Button for Create Account
    Button btnCreateAccount;

    // ListView for Hold Country Name and Code
    ListView lvCountryNameCode;

    AlertDialog alertDialogCountry;
    AlertDialog alertDialogState;
    AlertDialog alertDialogCity;

    AlertDialog.Builder builderCountry;
    AlertDialog.Builder builderState;
    AlertDialog.Builder builderCity;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    // Set Toolbar
    Toolbar toolbar;

    // Get Name
    String mName;

    // Get Email
    String mEmail;

    // Get Date of Birth
    String mDateOfBirth;

    // Get Username
    String mUserName;

    // Get Password
    String mPassword;

    // Get Confirm Password
    String mConfirmPassword;

    // Get Driving Licence Number
    String mDrivingLicenceNumber;

    // Get Badge Number
    String mBadgeNumber;

    // Get Mobile Number
    String mMobileNumber;

    // Get Country
    String mCountry;

    // Get State
    String mState;

    // Get City
    String mCity;

    // Get ZipCode
    String mZipcode;

    ArrayList details;

    int mYear;
    int mMonth;
    int mDay;
    Calendar c;

    final CharSequence[] countryItems = {"Indonesia", "India", "Singapore"};
    final CharSequence[] stateItems = {"Jakarta", "Sumatra", "Java"};
    final CharSequence[] cityItems = {"Bali", "Jambi", "Solok"};
    final CharSequence[] businessItems = {"Freelancer","Travel Agent","Tour Operator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_driver_registration);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarTaxiDriverRegistration);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaxiDriverRegistrationActivity.this, CustomerAgentActivity.class);
                startActivity(i);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Taxi Driver Registration");

        etName = (EditText)findViewById(R.id.edittext_taxi_driver_name);
        etEmail = (EditText)findViewById(R.id.edittext_taxi_driver_email);
        etDateOfBirth = (EditText)findViewById(R.id.edittext_taxi_driver_date_of_birth);
        etUserName = (EditText)findViewById(R.id.edittext_taxi_driver_user_name);
        etPassword = (EditText)findViewById(R.id.edittext_taxi_driver_password);
        etConfirmPassword = (EditText)findViewById(R.id.edittext_taxi_driver_confirm_password);
        etDrivingLicenceNumber = (EditText)findViewById(R.id.edittext_taxi_driver_driving_licence_number);
        etBadgeNumber = (EditText)findViewById(R.id.edittext_taxi_driver_badge_number);
        etMobileNumber = (EditText)findViewById(R.id.edittext_taxi_driver_mobile_number);
        etCountry = (EditText)findViewById(R.id.edittext_taxi_driver_country);
        etState = (EditText)findViewById(R.id.edittext_taxi_driver_state);
        etCity = (EditText)findViewById(R.id.edittext_taxi_driver_city);
        etZipcode = (EditText)findViewById(R.id.edittext_taxi_driver_zip_code);

        etCountryCode = (EditText)findViewById(R.id.edittext_taxi_driver_mobile_number_code);

        btnCreateAccount = (Button)findViewById(R.id.button_taxi_driver_create_account);

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        builderCountry = new AlertDialog.Builder(TaxiDriverRegistrationActivity.this);
        builderCountry.setItems(countryItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etCountry.setText(countryItems[item]);
            }
        });
        alertDialogCountry = builderCountry.create();

        builderState = new AlertDialog.Builder(TaxiDriverRegistrationActivity.this);
        builderState.setItems(stateItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etState.setText(stateItems[item]);
            }
        });
        alertDialogState = builderState.create();

        builderCity = new AlertDialog.Builder(TaxiDriverRegistrationActivity.this);
        builderCity.setItems(cityItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etCity.setText(cityItems[item]);
            }
        });
        alertDialogCity = builderCity.create();

        etCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogCountry.show();
            }
        });

        etState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogState.show();
            }
        });

        etCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogCity.show();
            }
        });

        lvCountryNameCode = new ListView(this);
        ArrayList<Pojo> results = new ArrayList<Pojo>();
        Pojo data = new Pojo();

        data.setCountryName("Indonesia");
        data.setCountryCode("+62");
        results.add(data);

        details = results;
        lvCountryNameCode.setAdapter(new CountryCodeSelectionBaseAdapter(TaxiDriverRegistrationActivity.this, details));

        builder = new AlertDialog.Builder(TaxiDriverRegistrationActivity.this);
        builder.setView(lvCountryNameCode);
        alertDialog = builder.create();

        etCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
            }
        });

        lvCountryNameCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                TextView countryCode = (TextView) arg1.findViewById(R.id.textview_country_code);
                etCountryCode.setText(countryCode.getText());
                alertDialog.cancel();

            }
        });

        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(TaxiDriverRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                etDateOfBirth.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();

            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mName = etName.getText().toString();
                mEmail = etEmail.getText().toString();
                mDateOfBirth = etDateOfBirth.getText().toString();
                mUserName = etDateOfBirth.getText().toString();
                mPassword = etPassword.getText().toString();
                mConfirmPassword = etConfirmPassword.getText().toString();
                mDrivingLicenceNumber = etDrivingLicenceNumber.getText().toString();
                mBadgeNumber = etBadgeNumber.getText().toString();
                mMobileNumber = etMobileNumber.getText().toString();
                mCountry = etCountry.getText().toString();
                mState = etState.getText().toString();
                mCity = etCity.getText().toString();
                mZipcode = etZipcode.getText().toString();

                if(mName.length() > 0) {
                    if(isValidEmail(mEmail)) {
                        if(mDateOfBirth.length() > 0) {
                            if(mUserName.length() > 0) {
                                if(mPassword.length() > 0) {
                                    if(mPassword.equals(mConfirmPassword)) {
                                        if(mDrivingLicenceNumber.length() > 0) {
                                            if(mBadgeNumber.length() > 0) {
                                                if(mMobileNumber.length() == 10) {
                                                    if(mCountry.length() > 0) {
                                                        if(mState.length() > 0) {
                                                            if(mCity.length() > 0) {
                                                                if(mZipcode.length() > 0) {

                                                                    Intent intentVerifyingOtpActivity = new Intent(TaxiDriverRegistrationActivity.this, VerifyingOtpActivity.class);
                                                                    startActivity(intentVerifyingOtpActivity);

                                                                } else {
                                                                    callAlertDialog("Enter Zipcode");
                                                                }

                                                            } else {
                                                                callAlertDialog("Choose City");
                                                            }

                                                        } else {
                                                            callAlertDialog("Choose State");
                                                        }

                                                    } else {
                                                        callAlertDialog("Choose Country");
                                                    }

                                                } else {
                                                    callAlertDialog("Enter Valid Mobile Number");
                                                }

                                            } else {
                                                callAlertDialog("Enter Badge Number");
                                            }

                                        } else {
                                            callAlertDialog("Enter Driving Licence Number");
                                        }

                                    } else {
                                        callAlertDialog("Password is not Matching");
                                    }

                                } else {
                                    callAlertDialog("Enter Password");
                                }

                            } else {
                                callAlertDialog("Enter Username");
                            }

                        } else {
                            callAlertDialog("Select Date of Birth");
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
     * AlertDialog for Error Messages.
     *
     * @param alertMessage
     */
    public void callAlertDialog(String alertMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(TaxiDriverRegistrationActivity.this);
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

    /**
     * Email Validations.
     *
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {

        //General Email Validation
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
