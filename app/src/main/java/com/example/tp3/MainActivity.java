package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editLogin;
    private EditText editPassword;
    Button btnLogin;
SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("CloudBank App");
sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        setupHyperlink();


    }

    @Override
    protected void onResume() {
        super.onResume();
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        btnLogin= findViewById(R.id.login);
      /*  if (!sharedPreferences.getString("login","default").equals("default") && !sharedPreferences.getString("password","default").equals("default")){
            Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
            startActivity(intent);
        }*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFieldsNotEmpty()){
                  /*  sharedPreferences.edit().putString("login", editLogin.getText().toString()).commit();
                    sharedPreferences.edit().putString("password", editPassword.getText().toString()).commit();*/
                    Intent intent = new Intent(getApplicationContext(), TransactionActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkFieldsNotEmpty(){
        String username = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
    }
    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.forgetPassword);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}