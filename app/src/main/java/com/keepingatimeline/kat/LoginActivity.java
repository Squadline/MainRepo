package com.keepingatimeline.kat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/* Written by: Louis Leung
   Main login screen, this is what launches on start
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* for forgot password button, if they click it, goes to Change Password
        Activity
         */
        TextView forgotPass = (TextView) findViewById(R.id.forgotPassword);
        TextView registration = (TextView) findViewById(R.id.createAccount);
        /* assert not null just a formality */
        assert forgotPass != null;
        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent newActivity = new Intent("com.keepingatimeline.kat.ChangePassword");
                startActivity(newActivity);
            }
        });

        assert registration != null;
        registration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newActivity = new Intent("com.keepingatimeline.kat.RegistrationScreen");
                startActivity(newActivity);
            }
        });
    }
    /* on clicking log in button, direct to Main screen activity */
    public void LoginSuccess(View view) {
        Intent newActivity = new Intent("com.keepingatimeline.kat.MainScreen");
        startActivity(newActivity);
    }

}
