package com.example.booklibrarystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText signupusername, signuppassword, repassword;
    Button createbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]=\\.+[a-z]+";
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupusername = (EditText) findViewById(R.id.signupusername);
        signuppassword = (EditText) findViewById(R.id.signuppassword);
        repassword = (EditText) findViewById(R.id.repassword);
        createbtn = (Button) findViewById(R.id.createbtn);
        DB = new DBHelper(this);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupusername.getText().toString();
                String pass = signuppassword.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(signup.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(signup.this, "User already exist !! please sign in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }
                    }else {
                        Toast.makeText(signup.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
