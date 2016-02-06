package com.example.khome.lnmiitmess;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.DialogBox;
import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.example.khome.lnmiitmess.Tools.UserInfo;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    boolean flag;
    String uid=null;
    UserInfo user1=null;
    private Toolbar mToolbar;
    private EditText inputName, inputEmail, inputPassword,inputRoll;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword,inputLayoutRoll;
    private Button btnSignUp;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_su);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name_su);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email_su);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password_su);
        inputLayoutRoll = (TextInputLayout) findViewById(R.id.input_layout_roll_su);
        inputName = (EditText) findViewById(R.id.input_name_su);
        inputEmail = (EditText) findViewById(R.id.input_email_su);
        inputPassword = (EditText) findViewById(R.id.input_password_su);
        inputRoll = (EditText) findViewById(R.id.input_roll_su);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputRoll.addTextChangedListener(new MyTextWatcher(inputRoll));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // boolean valid=true;
                boolean valid = submitForm();
                if (valid) {

                    // Toast.makeText(getApplicationContext(), "okk signup ", Toast.LENGTH_SHORT).show();
                    if (NetworkTool.isConnectingToInternet(com.example.khome.lnmiitmess.signup.this)) {
                        //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();

                        signupFirebase();

                    } else {Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection!", Snackbar.LENGTH_LONG);
                         snackbar.show();
                     //   showDialogBox();

                    }
                }
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
            return super.onOptionsItemSelected(item);
    }

    private boolean submitForm() {

        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }
        return true;
    }
    public void signupFirebase()
    {
       final ProgressDialog ringProgressDialog = ProgressDialog.show(signup.this, "Please wait ...", "Connecting.......", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=inputEmail.getText().toString();
                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    String pass=inputPassword.getText().toString();
                    ref.createUser(em, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            //System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            //Toast.makeText(getApplicationContext(),"Successfully created user account with uid: " + result.get("uid"),Toast.LENGTH_LONG).show();
                            boolean f=storeData();
                            ringProgressDialog.dismiss();
                            Intent i=new Intent(getApplicationContext(),login.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            //Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                            Snackbar snackbar;
                            switch (firebaseError.getCode()) {
                                case FirebaseError.USER_DOES_NOT_EXIST:
                                    snackbar = Snackbar.make(coordinatorLayout, "User Does Not Exist!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;
                                case FirebaseError.INVALID_PASSWORD:
                                    snackbar = Snackbar.make(coordinatorLayout, "Invalid Password!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;
                                case FirebaseError.NETWORK_ERROR:
                                    snackbar = Snackbar.make(coordinatorLayout, "Network Error!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;


                                default:
                                    snackbar = Snackbar.make(coordinatorLayout, "SignUp Error!", Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                    break;
                            }
                            ringProgressDialog.dismiss();
                            // there was an error
                        }
                    });


                   // Thread.sleep(10000);
                } catch (Exception e) {
                }

            }
        }).start();

    }
    public boolean storeData()
    {
       final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
        ref.authWithPassword(inputEmail.getText().toString(), inputPassword.getText().toString(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        UserInfo user1=new UserInfo();
                        user1.setUname(inputName.getText().toString());
                        user1.setRollno(inputRoll.getText().toString());
                        user1.setEmail(inputEmail.getText().toString());
                        user1.setUid(authData.getUid());
                        user1.setPassword(inputPassword.getText().toString());
                        ref.child("users").child(authData.getUid()).setValue(user1);
                        flag= true;
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError error) {
                        // Something went wrong :(
                        flag=false;
                    }
                });
    return flag;
    }
    public void showDialogBox()
    {
        Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog = new AlertDialog.Builder(
                signup.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert Dialog");

        // Setting Dialog Message
        alertDialog.setMessage("No internet");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.input_email_su:
                    validateEmail();
                    break;
                case R.id.input_password_su:
                    validatePassword();
                    break;
            }
        }
    }
}
