package com.example.khome.lnmiitmess;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    EditText emaillogin,passwordlogin;
    Button login,forgotbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login);
        emaillogin=(EditText)findViewById(R.id.emaillogin);
        passwordlogin=(EditText)findViewById(R.id.passwordlogin);
        forgotbutton=(Button)findViewById(R.id.forgotbutton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid=true;
                //valid=initValidationForm();
                if(valid) {

                    Toast.makeText(getApplicationContext(), "okk login ", Toast.LENGTH_SHORT).show();
                    if(NetworkTool.isConnectingToInternet(login.this))
                    {
                        Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                        loginFirebase();
                    }
                    else
                        showDialogBox();
                }

            }
        });
        forgotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(com.example.khome.lnmiitmess.login.this,forgot.class);
                startActivity(i);
                //finish();

            }
        });

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
                    Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    String pass=passwordlogin.getText().toString();
                    ref.authWithPassword(em, pass, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            Toast.makeText(getApplicationContext(),"Successfully logged in with uid: " + authData.getUid(),Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                            Intent i=new Intent(com.example.khome.lnmiitmess.login.this,MainPage.class);
                            startActivity(i);
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
    public boolean initValidationForm()
    {
        boolean flag=true;
        final String emailString = emaillogin.getText().toString();
        if(!isfieldNull()) {

            flag=false;
        }
        if (!isValidEmail(emailString)) {
            emaillogin.setError("Invalid Email");
            flag=false;
        }

        return flag;
    }
    public boolean isfieldNull()
    {
        boolean flag=true;
        String em=emaillogin.getText().toString();
        String pass=passwordlogin.getText().toString();


        if(em==null)
        {
            emaillogin.setError("Empty!");
            flag=false;
        }
        if(pass==null)
        {
            passwordlogin.setError("Empty!");
            flag=false;
        }

        return flag;
    }
    public boolean isValidEmail(String emailString)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailString);
        return matcher.matches();

    }

}
