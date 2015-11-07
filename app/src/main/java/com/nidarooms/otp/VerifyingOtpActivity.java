package com.nidarooms.otp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nidarooms.R;

/**
 * Verifying OTP Activity Class to Enter OTP and Validate.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class VerifyingOtpActivity extends AppCompatActivity {

    // EditText for Enter OTP
    EditText etOtp;

    // Button for Proceed Verification
    Button btnVerify;

    // Set Toolbar (Action Bar)
    Toolbar toolbar;

    // Get OTP
    String mOtp;

   // https://gitlab.com/HarishSelvarasu/NIDARooms.git

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_otp);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarVerifyingOtp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("OTP");

        etOtp = (EditText)findViewById(R.id.edittext_otp);

        btnVerify = (Button)findViewById(R.id.button_verify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOtp = etOtp.getText().toString();

                if(mOtp.length() == 4) {

                } else {
                    callAlertDialog("Enter Valid OTP");
                }

            }
        });
    }

    /**
     * AlertDiaog for Error Messages.
     *
     * @param alertMessage
     */
    public void callAlertDialog(String alertMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(VerifyingOtpActivity.this);
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