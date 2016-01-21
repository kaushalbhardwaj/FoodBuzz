package com.example.khome.lnmiitmess;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.DialogBox;
import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    EditText name,rollno,email,phone,password,cpassword;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initFields();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid=true;
                //valid = initValidationForm();
                if (valid) {

                    Toast.makeText(getApplicationContext(), "okk signup ", Toast.LENGTH_SHORT).show();
                        if(NetworkTool.isConnectingToInternet(com.example.khome.lnmiitmess.signup.this))
                        {
                            Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                            signupFirebase();
                        }
                        else
                        showDialogBox();

                }
            }

        });
    }
    public void signupFirebase()
    {
       final ProgressDialog ringProgressDialog = ProgressDialog.show(signup.this, "Please wait ...", "Downloading Image ...", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=email.getText().toString();
                    Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    String pass=password.getText().toString();
                    ref.createUser(em, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            //System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            Toast.makeText(getApplicationContext(),"Successfully created user account with uid: " + result.get("uid"),Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void initFields()
    {
        name=(EditText)findViewById(R.id.nameet);
        rollno=(EditText)findViewById(R.id.rollnoet);
        email=(EditText)findViewById(R.id.emailet);
        phone=(EditText)findViewById(R.id.phoneet);
        password=(EditText)findViewById(R.id.passwordet);
        cpassword=(EditText)findViewById(R.id.cpasswordet);
        signup=(Button)findViewById(R.id.signup);


    }
    public boolean initValidationForm()
    {
        boolean flag=true;
        final String emailString = email.getText().toString();
        if(!isfieldNull())
        {

            flag=false;
        }
        if (!isValidEmail(emailString)) {
            email.setError("Invalid Email");
            flag=false;
        }


        if (!isValidPassword()) {
            cpassword.setError("Password does not match");
            flag=false;
        }
        if (!isValidPhone()) {
            phone.setError("Invalid Phone Number");
            flag=false;
        }

        return flag;
    }
    public boolean isfieldNull()
    {
        boolean flag=true;
        String nm=name.getText().toString();
        String em=email.getText().toString();
        String pass=password.getText().toString();
        String cp=cpassword.getText().toString();

        if(nm==null)
        {
            name.setError("Empty!");
            flag=false;
        }
        if(em==null)
        {
            email.setError("Empty!");
            flag=false;
        }
        if(pass==null)
        {
            password.setError("Empty!");
            flag=false;
        }
        if(cp==null)
        {
            cpassword.setError("Empty!");
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
    public boolean isValidPassword()
    {
        String pass=password.getText().toString();
        String cpass=cpassword.getText().toString();
        if(pass.equals(cpass))
        {
            return true;

        }
        else
            return false;

    }
    public boolean isValidPhone()
    {
        String ph=phone.getText().toString();
        if(ph.length()==10)
        {
            return true;

        }
        else
            return false;

    }
}
