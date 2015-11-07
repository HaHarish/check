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

import com.nidarooms.R;
import com.nidarooms.baseadapter.CountryCodeSelectionBaseAdapter;
import com.nidarooms.otp.VerifyingOtpActivity;
import com.nidarooms.pojo.Pojo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Travel Expert Registration for Create Account - Travel Expert.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class TravelExpertRegistrationActivity extends AppCompatActivity {

    /** EditText for Enter Agency Name */
    EditText etAgencyName;

    /** EditText for Enter Contact Person */
    EditText etContactPerson;

    /** EditText for Enter Email */
    EditText etEmail;

    /** EditText for Enter UserName */
    EditText etUserName;

    /** EditText for Enter Password */
    EditText etPassword;

    /** EditText for Enter Confirm Password */
    EditText etConfirmPassword;

    /** EditText foe Enter Mobile Number */
    EditText etMobileNumber;

    /** EditText for Choose Country */
    EditText etCountry;

    /** EditText for Choose State */
    EditText etState;

    /** EditText for Choose City */
    EditText etCity;

    /** EditText for Enter Zipcode */
    EditText etZipcode;

    /** EditText for Enter ID Number */
    EditText etIdNo;

    /** EditText for Enter ID Holder Name */
    EditText etIdHolderName;

    /** EditText for Choose Nature of Business */
    EditText etNatureOfBusiness;

    /** EditText for Choose Country Code */
    EditText etCountryCode;

    /** Button for Create Account */
    Button btnCreateAccount;

    /** ListView for Hold Country Name and Code */
    ListView lvCountryNameCode;

    /** Set Toolbar */
    Toolbar toolbar;

    AlertDialog alertDialogCountry;
    AlertDialog alertDialogState;
    AlertDialog alertDialogCity;
    AlertDialog alertDialogBusiness;

    AlertDialog.Builder builderCountry;
    AlertDialog.Builder builderState;
    AlertDialog.Builder builderCity;
    AlertDialog.Builder builderBusiness;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    /** Get Agency Name */
    String mAgencyName;

    /** Get Contact Person */
    String mContactPerson;

    /** Get Email */
    String mEmail;

    /** Get Username */
    String mUserName;

    /** Get Password */
    String mPassword;

    /** Get Confirm Password */
    String mConfirmPassword;

    /** Get Mobile Number */
    String mMobileNumber;

    /** Get Country */
    String mCountry;

    /** Get State */
    String mState;

    /** Get City */
    String mCity;

    /** Get Zipcode */
    String mZipcode;

    /** Get ID Number */
    String mIdNo;

    /** Get ID Holder Name */
    String mIdHolderName;

    /** Get Nature Of Business */
    String mNatureOfBusiness;

    ArrayList details;

    final CharSequence[] countryItems = {"Indonesia", "India", "Singapore"};
    final CharSequence[] stateItems = {"Jakarta", "Sumatra", "Java"};
    final CharSequence[] cityItems = {"Bali", "Jambi", "Solok"};
    final CharSequence[] businessItems = {"Freelancer","Travel Agent","Tour Operator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_expert_registration);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarTravelExpertRegistration);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TravelExpertRegistrationActivity.this, CustomerAgentActivity.class);
                startActivity(i);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Travel Expert Registration");

        etAgencyName = (EditText)findViewById(R.id.edittext_travel_expert_agency_name);
        etContactPerson = (EditText)findViewById(R.id.edittext_travel_expert_contact_person);
        etEmail = (EditText)findViewById(R.id.edittext_travel_expert_email);
        etUserName = (EditText)findViewById(R.id.edittext_travel_expert_user_name);
        etPassword = (EditText)findViewById(R.id.edittext_travel_expert_password);
        etConfirmPassword = (EditText)findViewById(R.id.edittext_travel_expert_confirm_password);
        etMobileNumber = (EditText)findViewById(R.id.edittext_travel_expert_mobile_number);
        etCountry = (EditText)findViewById(R.id.edittext_travel_expert_country);
        etState = (EditText)findViewById(R.id.edittext_travel_expert_state);
        etCity = (EditText)findViewById(R.id.edittext_travel_expert_city);
        etZipcode = (EditText)findViewById(R.id.edittext_travel_expert_zip_code);
        etIdNo = (EditText)findViewById(R.id.edittext_travel_expert_id_no);
        etIdHolderName = (EditText)findViewById(R.id.edittext_travel_expert_id_holder_name);
        etNatureOfBusiness = (EditText)findViewById(R.id.edittext_travel_expert_nature_of_business);

        etCountryCode = (EditText)findViewById(R.id.edittext_travel_expert_mobile_number_code);

        btnCreateAccount = (Button)findViewById(R.id.button_travel_expert_create_account);

        builderCountry = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
        builderCountry.setItems(countryItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etCountry.setText(countryItems[item]);
            }
        });
        alertDialogCountry = builderCountry.create();

        builderState = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
        builderState.setItems(stateItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etState.setText(stateItems[item]);
            }
        });
        alertDialogState = builderState.create();

        builderCity = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
        builderCity.setItems(cityItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etCity.setText(cityItems[item]);
            }
        });
        alertDialogCity = builderCity.create();

        builderBusiness = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
        builderBusiness.setItems(businessItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                etNatureOfBusiness.setText(businessItems[item]);
            }
        });
        alertDialogBusiness = builderBusiness.create();

        lvCountryNameCode = new ListView(this);
        ArrayList<Pojo> results = new ArrayList<Pojo>();
        Pojo data = new Pojo();

        data.setCountryName("Indonesia");
        data.setCountryCode("+62");
        results.add(data);

        details = results;
        lvCountryNameCode.setAdapter(new CountryCodeSelectionBaseAdapter(TravelExpertRegistrationActivity.this, details));

        builder = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
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

        etNatureOfBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBusiness.show();
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAgencyName = etAgencyName.getText().toString();
                mContactPerson = etContactPerson.getText().toString();
                mEmail = etEmail.getText().toString();
                mUserName = etUserName.getText().toString();
                mPassword = etPassword.getText().toString();
                mConfirmPassword = etConfirmPassword.getText().toString();
                mMobileNumber = etMobileNumber.getText().toString();
                mCountry = etCountry.getText().toString();
                mState = etState.getText().toString();
                mCity = etCity.getText().toString();
                mZipcode = etZipcode.getText().toString();
                mIdNo = etIdNo.getText().toString();
                mIdHolderName = etIdHolderName.getText().toString();
                mNatureOfBusiness = etNatureOfBusiness.getText().toString();

                if(mAgencyName.length() > 0) {
                    if(mContactPerson.length() > 0) {
                        if(isValidEmail(mEmail)) {
                            if(mUserName.length() > 0) {
                                if(mPassword.length() > 0) {
                                    if(mPassword.equals(mConfirmPassword)) {
                                        if (mMobileNumber.length() == 10) {
                                            if (mCountry.length() > 0) {
                                                if (mState.length() > 0) {
                                                    if (mCity.length() > 0) {
                                                        if (mZipcode.length() == 6) {
                                                            if (mIdNo.length() > 0) {
                                                                if(mIdHolderName.length() > 0) {
                                                                    if(mNatureOfBusiness.length() > 0) {

                                                                        Intent intentOtpActivity = new Intent(TravelExpertRegistrationActivity.this, VerifyingOtpActivity.class);
                                                                        startActivity(intentOtpActivity);

                                                                    } else {
                                                                        callAlertDialog("Choose Nature of Business");
                                                                    }
                                                                } else {
                                                                    callAlertDialog("Enter ID Holder Name");
                                                                }
                                                            } else {
                                                                callAlertDialog("Enter ID No");
                                                            }
                                                        } else {
                                                            callAlertDialog("Enter Valid Zipcode");
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
                                        callAlertDialog("Password is not Matching");
                                    }
                                } else {
                                    callAlertDialog("Enter Password");
                                }
                            } else {
                                callAlertDialog("Enter User Name");
                            }
                        } else {
                            callAlertDialog("Enter Valid Email");
                        }
                    } else {
                        callAlertDialog("Enter Contact Person");
                    }
                } else {
                    callAlertDialog("Enter Agency Name");
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

        AlertDialog.Builder builder = new AlertDialog.Builder(TravelExpertRegistrationActivity.this);
        // builder.setTitle(alertHeading);
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
