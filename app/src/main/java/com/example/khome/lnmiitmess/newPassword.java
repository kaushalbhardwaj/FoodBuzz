package com.example.khome.lnmiitmess;

import android.app.ProgressDialog;
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

public class newPassword extends AppCompatActivity {
    EditText cpass,npass,opass;
    Button rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        npass=(EditText)findViewById(R.id.newpass);
        opass=(EditText)findViewById(R.id.opass);
        rpass=(Button)findViewById(R.id.rpass);
        rpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkTool.isConnectingToInternet(newPassword.this))
                {
                    Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                    rpassFirebase();
                }
                else
                    NetworkTool.showDialogBox(newPassword.this);
            }
        });



    }
    public void rpassFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(newPassword.this, "Please wait ...", "Downloading Image ...", true);
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
                            // password changed
                            Toast.makeText(getApplicationContext(),"pass changed",Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // error encountered
                            Toast.makeText(getApplicationContext(),"error cannot change pass",Toast.LENGTH_LONG).show();
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
