package com.nidarooms.forgotpassword;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nidarooms.R;
import com.nidarooms.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ForgotPasswordActivity class to send password reset link to the user's.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    /** EditText for enter email */
    EditText editTextUserEmail;

    /** Button for send email to the user's */
    Button btnSend;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    /** Get Email */
    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Assing Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarForgotPassword);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Forgot Password");

        editTextUserEmail = (EditText)findViewById(R.id.editText_user_email_forgot_password);

        btnSend = (Button)findViewById(R.id.button_send_forgot_password);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hide keypad in the Device
                hideKeyboard();

                mEmail = editTextUserEmail.getText().toString();

                if(isValidEmail(mEmail)) {

                    Toast.makeText(ForgotPasswordActivity.this, "OK", Toast.LENGTH_LONG).show();

                } else {

                    callAlertDialog("Enter Valid Email");

                }
            }
        });
    }

    /**
     * Email Validation
     *
     * @param email Pass the email value to this method
     * @return return email validation result
     */
    public boolean isValidEmail(String email) {

        //General Email Validation
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * AlertDialog for Error Messages
     *
     * @param alertMessage Pass the alert message to this method
     */
    public void callAlertDialog(String alertMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
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
     * Press Back Key in the Device
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){

            Intent intentLoginActivity = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intentLoginActivity);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        return true;
        // Disable back button..............
    }

    /**
     * Hide Keypad in the Device
     */
    public void hideKeyboard() {

        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}