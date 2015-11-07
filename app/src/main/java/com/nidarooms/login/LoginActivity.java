package com.nidarooms.login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nidarooms.R;
import com.nidarooms.forgotpassword.ForgotPasswordActivity;
import com.nidarooms.registration.CustomerAgentActivity;

/**
 * LoginActivity for the User to Login.
 * User's have to enter the Username and Password.
 * User's can also retrieve their Username and Password through Forgot Password link.
 * User's can do Create Account.
 *
 * @author Harish
 * @version 1.0 03/11/2015
 */
public class LoginActivity extends AppCompatActivity {

    /** EditText for enter username */
    EditText editTextUserName;

    /** EditText for enter Password */
    EditText editTextPassword;

    /** Button for Login */
    Button buttonLogin;

    /** TextView for click Forgot Password */
    TextView textViewForgotPassword;

    /** TextView for click Create Account */
    TextView textViewCreateAccount;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    /** Get Username */
    String mUserName;

    /** Get Password */
    String mPassword;

    /** Get Roboto Font Medium */
    public static Typeface typefaceMedium1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);

        typefaceMedium1 = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        editTextUserName = (EditText)findViewById(R.id.editText_user_name);
    //  editTextUserName.setTypeface(FontStyleUtils.typefaceRegular);

        editTextPassword = (EditText)findViewById(R.id.editText_password);
    //  editTextPassword.setTypeface(FontStyleUtils.typefaceRegular);

        buttonLogin = (Button)findViewById(R.id.button_login);
        buttonLogin.setTypeface(typefaceMedium1);

        textViewForgotPassword = (TextView)findViewById(R.id.textview_forgot_password);
    //  textViewForgotPassword.setTypeface(typefaceMedium1);

        textViewCreateAccount = (TextView)findViewById(R.id.textview_create_account);
    //  textViewCreateAccount.setTypeface(FontStyleUtils.typefaceMedium);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hide Keypad from after clicks Login Button
                hideKeyPad();

                // Read Username and Password
                mUserName = editTextUserName.getText().toString();
                mPassword = editTextPassword.getText().toString();

                // Check Validations for Username and Password
                if (mUserName.length() > 0) {
                    if (mPassword.length() > 0) {

                        callServer(mUserName,mPassword);

                        /*// If all the Validations are true, Proceed the Home Page Process
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);*/

                    } else {
                        callAlertDialog("Username", "Enter Password");
                    }

                } else {
                    callAlertDialog("Password", "Enter Username");
                }

            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If User clicks Forgot Password Option
                Intent intentForgotPasswordActivity = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intentForgotPasswordActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If User clicks Create Account Option
                Intent intentCustomerAgentActivity = new Intent(LoginActivity.this, CustomerAgentActivity.class);
                startActivity(intentCustomerAgentActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }

    /**
     * callAlertDialog to display AlertDialog for Error Messages.
     *
     * @param alertHeading
     * @param alertMessage
     */
    public void callAlertDialog(String alertHeading, String alertMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
     * hideKeyPad to hide keypad from the screen.
     */
    public void hideKeyPad() {

        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void callServer(final String userName, final String passWord){

        /*try {

            JSONObject params = new JSONObject();
            params.put("username", userName);
            params.put("password", passWord);

            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(120000);

            client.addHeader("Content-Type", "application/json");
            client.addHeader("V-Accept", "1.0");

            StringEntity entity = new StringEntity(params.toString(),"UTF-8");

            client.post(getApplicationContext(),"http://dev-nidarooms.pantheon.io/api?method=mobile_user_login", entity, "application/json",new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    System.out.println("Output : " + response);
                }


            });

        } catch (JSONException e) {
            e.printStackTrace();
        }*/





    }

}
