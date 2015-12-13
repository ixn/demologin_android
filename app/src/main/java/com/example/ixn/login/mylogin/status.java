package com.example.ixn.login.mylogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class status extends AppCompatActivity {
    String msg = "mylogin-status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        TextView status = (TextView)findViewById(R.id.textView3);
        Bundle getparam = getIntent().getExtras();
        int value = getparam.getInt("status");

        if (value == 1)
        {
            status.setText("Login Success");
        }
        else{
            status.setText("Login Failed");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(msg, "On start bro");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(msg, "On resume bro");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(msg, "On pause bro");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(msg, "On Stop bro");
    }

    @Override
    public void onDestroy(){
        super.onStop();
        Log.d(msg, "Sudah di Destroy bro");
    }

}
