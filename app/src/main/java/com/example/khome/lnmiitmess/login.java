package com.example.khome.lnmiitmess;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.example.khome.lnmiitmess.Tools.SharedPreference;
import com.example.khome.lnmiitmess.Tools.UserInfo;
import com.example.khome.lnmiitmess.Tools.UserInfo2;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    EditText emaillogin,passwordlogin;
    Button login;
    String uid;
    TextView signup_login,forgot_login;
    private Toolbar mToolbar;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup_login=(TextView)findViewById(R.id.signup_login);
        forgot_login=(TextView)findViewById(R.id.forgot_login);
        login=(Button)findViewById(R.id.login);
        emaillogin=(EditText)findViewById(R.id.emaillogin);
        passwordlogin=(EditText)findViewById(R.id.passwordlogin);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        emaillogin.addTextChangedListener(new MyTextWatcher(emaillogin));
        passwordlogin.addTextChangedListener(new MyTextWatcher(passwordlogin));

        signup_login.setMovementMethod(LinkMovementMethod.getInstance());
        signup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), signup.class));
            }
        });
        forgot_login.setMovementMethod(LinkMovementMethod.getInstance());
        forgot_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), forgot.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //boolean valid = true;
                boolean valid = submitForm();
                if (valid) {

                    //Toast.makeText(getApplicationContext(), "okk login ", Toast.LENGTH_SHORT).show();
                    if (NetworkTool.isConnectingToInternet(login.this)) {
                        Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                        loginFirebase();
                    } else
                        showDialogBox();
                }

            }
        });


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
    public void loginFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(login.this, "Please wait ...", "Downloading Image ...", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=emaillogin.getText().toString();
                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    String pass=passwordlogin.getText().toString();
                    ref.authWithPassword(em, pass, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            Toast.makeText(getApplicationContext(),"Successfully logged in with uid: " + authData.getUid(),Toast.LENGTH_LONG).show();
                            uid=authData.getUid();
                            fetchData();

                            /*UserInfo user1=new UserInfo();
                            user1.setUid(ref.child("users").child(authData.getUid()).getVa);
                            user1.setEmail();
                            user1.setPassword();
                            user1.setUname();
                            user1.setRollno();*/


                            ringProgressDialog.dismiss();
                            Intent i=new Intent(com.example.khome.lnmiitmess.login.this,MainPage.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(),"cannot login !!",Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                            // there was an error
                        }
                    });



                    // Thread.sleep(10000);
                } catch (Exception e) {
                }
           // ringProgressDialog.dismiss();
            }
        }).start();

    }
    public void fetchData()
    {
        Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/users/"+uid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();
                UserInfo user2=new UserInfo();
                String s=(String)newUser.get("rollno");
                user2.setRollno(s);
                s=(String)newUser.get("uname");
                user2.setUname(s);
                user2.setPassword(passwordlogin.getText().toString());
                user2.setEmail(emaillogin.getText().toString());
                user2.setUid(uid);

                System.out.println("Author: " +user2.getPassword()+user2.getRollno()+user2.getUname() );
                SharedPreference.putSharedPreferInfo(getApplicationContext(), user2);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });



    }
    public void showDialogBox()
    {
        Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog = new AlertDialog.Builder(
                login.this).create();

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

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validateEmail() {
        String email = emaillogin.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(emaillogin);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePassword() {
        if (passwordlogin.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(passwordlogin);
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

                case R.id.emaillogin:
                    validateEmail();
                    break;
                case R.id.passwordlogin:
                    validatePassword();
                    break;
            }
        }
    }

}
