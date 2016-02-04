package com.example.khome.lnmiitmess;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgot extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputEmail;
    private TextInputLayout inputLayoutEmail;
    private Button btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        toolbar = (Toolbar) findViewById(R.id.toolbar_for);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Forgot");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email_fo);
        inputEmail = (EditText) findViewById(R.id.input_email_fo);
        btnForgot = (Button) findViewById(R.id.btn_forgot);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
      btnForgot.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //boolean valid=true;
              boolean valid = submitForm();
              if (valid) {

                  Toast.makeText(getApplicationContext(), "okk signup ", Toast.LENGTH_SHORT).show();
                  if (NetworkTool.isConnectingToInternet(forgot.this)) {
                      Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                      forgotFirebase();
                  } else
                      showDialogBox();

              }
          }
      });
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

            }
        }
    }
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
   return true;
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
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void forgotFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(forgot.this, "Please wait ...", "Downloading Image ...", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=inputEmail.getText().toString();
                    Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    ref.resetPassword(em, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getApplicationContext(), "reset email sent", Toast.LENGTH_SHORT).show();
                            ringProgressDialog.dismiss();
                            Intent i=new Intent(getApplicationContext(),login.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            ringProgressDialog.dismiss();
                        }
                    });


                    // Thread.sleep(10000);
                } catch (Exception e) {
                }

            }
        }).start();

    }
    public void showDialogBox()
    {
        Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog = new AlertDialog.Builder(
                forgot.this).create();

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
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}
