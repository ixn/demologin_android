package com.example.ixn.login.mylogin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    private EditText txtUsername, txtPwd1, txtPwd2;
    private Button btnSignUp;
    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBAdapter(this);
        db.open();
        setContentView(R.layout.activity_signup);

        txtUsername = (EditText) findViewById(R.id.editText3);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtUsername, InputMethodManager.SHOW_IMPLICIT);

        txtPwd1 = (EditText) findViewById(R.id.editText4);
        txtPwd2 = (EditText) findViewById(R.id.editText5);
        btnSignUp = (Button) findViewById(R.id.button3);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            final public void onClick(View view)
            {
                if (txtPwd1.getText().toString().equals(txtPwd2.getText().toString())){
                    Username username = new Username();
                    username.setUsername(txtUsername.getText().toString());
                    username.setPassword(txtPwd1.getText().toString());
                    if (db.createUsername(username)){
                        Toast.makeText(getApplicationContext(), "Save Success.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Save Failed.", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password Not Equel.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}