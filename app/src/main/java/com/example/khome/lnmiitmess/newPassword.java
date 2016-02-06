package com.example.khome.lnmiitmess;

import android.app.ProgressDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.NetworkTool;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class newPassword extends AppCompatActivity {
    EditText npass,opass;
    Button rpass;
    private Toolbar mToolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_np);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Change Pass");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        npass=(EditText)findViewById(R.id.input_password_np);
        opass=(EditText)findViewById(R.id.input_opass_np);
        rpass=(Button)findViewById(R.id.btn_changePass);
        rpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkTool.isConnectingToInternet(newPassword.this))
                {
                    //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                    rpassFirebase();
                }
                else
                {

                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection!", Snackbar.LENGTH_LONG);
                    //snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
                    //NetworkTool.showDialogBox(newPassword.this);
            }
        });



    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void rpassFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(newPassword.this, "Please wait ...", "Connecting ...", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=null;
                    Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    String np=npass.getText().toString();
                    String op=opass.getText().toString();
                    ref.changePassword(em,op , np, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Password Changed!", Snackbar.LENGTH_LONG);
                            //snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();
                            // password changed
                            //Toast.makeText(getApplicationContext(),"pass changed",Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {

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
                                    snackbar = Snackbar.make(coordinatorLayout, "Error!", Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                    break;
                            }
                            // error encountered
                            //Toast.makeText(getApplicationContext(),"error cannot change pass",Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                        }
                    });

                    // Thread.sleep(10000);
                } catch (Exception e) {
                }

            }
        }).start();

    }

}
