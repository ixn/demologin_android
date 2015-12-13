package com.example.ixn.login.mylogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String msg = "mylogin-main";
    private EditText username, password;
    private Button btnLogin, btnCancel;
    private TextView value, signup;
    final Context context = this;
    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(msg, "on Create activity");
        db = new DBAdapter(this);
        db.open();
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

        btnLogin = (Button)findViewById(R.id.button);
        btnCancel = (Button)findViewById(R.id.button2);

        value = (TextView)findViewById(R.id.textView2);
        value.setVisibility(View.GONE);

        signup = (TextView)findViewById(R.id.textView5);
        SpannableString underline = new SpannableString("Sign Up");
        underline.setSpan(new UnderlineSpan(), 0, underline.length(), 0);
        signup.setText(underline);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            final public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, status.class);
                Bundle sendparam = new Bundle();

//                USING Without DB
//                if (username.getText().toString().equals(admin) && password.getText().toString().equals(pwd)) {
//                    //value.setText("SUCCESS BRO");
//                    //value.setVisibility(view.VISIBLE);
//                    Toast.makeText(getApplicationContext(), "Login Success.", Toast.LENGTH_LONG).show();
//                    sendparam.putInt("status", 1);
//                } else {
//                    //value.setText("Salah BRO");
//                    //value.setVisibility(view.VISIBLE);
//                    Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_LONG).show();
//                    sendparam.putInt("status", 2);
//                }
//                intent.putExtras(sendparam);
//                startActivity(intent);
//                onPause();

//              USING WITH DB
                Log.d(msg, "Sampai sebelum cursor username = " + username.getText().toString());
                Cursor cursor = db.getSingleUsername(username.getText().toString());
                Log.d(msg, "cursor yes");
                if (cursor.moveToFirst()) {
                    String retpassword = null;
                    retpassword = (cursor.getString(cursor.getColumnIndex("password")));
                    if (password.getText().toString().equals(retpassword)) {
                        Toast.makeText(getApplicationContext(), "Login Success.", Toast.LENGTH_LONG).show();
                        sendparam.putInt("status", 1);
                        intent.putExtras(sendparam);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            final public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Pesan");
                alertDialog.setMessage("Anda akan keluar dari aplikasi.");
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            final public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });
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
