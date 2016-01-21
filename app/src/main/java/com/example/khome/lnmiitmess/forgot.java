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

import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgot extends AppCompatActivity {

    Button resetemail;
    EditText femail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        femail=(EditText)findViewById(R.id.femail);
        resetemail=(Button)findViewById(R.id.resetemail);
        resetemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid=true;
                //valid = initValidationForm();
                if (valid) {

                    Toast.makeText(getApplicationContext(), "okk signup ", Toast.LENGTH_SHORT).show();
                    if(NetworkTool.isConnectingToInternet(forgot.this))
                    {
                        Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                        forgotFirebase();
                    }
                    else
                        showDialogBox();

                }
            }
        });
    }
    public void forgotFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(forgot.this, "Please wait ...", "Downloading Image ...", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=femail.getText().toString();
                    Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    ref.resetPassword(em, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getApplicationContext(), "reset email sent", Toast.LENGTH_SHORT).show();
                            ringProgressDialog.dismiss();
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
    public boolean initValidationForm()
    {
        boolean flag=true;
        final String emailString = femail.getText().toString();
        if(emailString==null)
        {

            flag=false;
        }
        if (!isValidEmail(emailString)) {
            femail.setError("Invalid Email");
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
