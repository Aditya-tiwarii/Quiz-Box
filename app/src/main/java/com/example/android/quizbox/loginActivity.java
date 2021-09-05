package com.example.android.quizbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText memail, mpassword;
    Button mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail = findViewById(R.id.Email);
        mpassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mpassword.setError(("Password is Required"));
                    return;
                }

                if (password.length() < 6) {
                    mpassword.setError("Password Must be >= 6 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getBaseContext(), profileActivity.class);
                            i.putExtra("email", email);
                            startActivity(i);
                            startActivity(new Intent(getApplicationContext(), dashboardActivity.class));

                        } else {
                            Toast.makeText(loginActivity.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


        TextView tv = (TextView) findViewById(R.id.createAcc);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccount();
            }
        });

    }

    private void openCreateAccount() {
        Intent intent = new Intent(this, signupActivity.class);
        startActivity(intent);
    }
}