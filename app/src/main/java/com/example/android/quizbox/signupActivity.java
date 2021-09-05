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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {

    EditText mfullName, memail, mpassword, mphoneNo;
    Button register;
    FirebaseAuth fAuth;
    FirebaseDatabase root;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mfullName = findViewById(R.id.fullName);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphoneNo = findViewById(R.id.phone);
        register = findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance();
        ref = root.getReference("users");


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mfullName.getText().toString();
                String phoneNo = mphoneNo.getText().toString().trim();
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    mfullName.setError("Name can't be empty");
                }
                if (TextUtils.isEmpty(phoneNo)) {
                    mfullName.setError("PhoneNo. is required");
                }

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
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signupActivity.this, "User Created! LOGIN", Toast.LENGTH_SHORT).show();

                            String userId = fAuth.getCurrentUser().getUid();

                            UserHelperClass user = new UserHelperClass(name, email, phoneNo);

                            ref.child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task1) {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(signupActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(signupActivity.this, "Data not inserted" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                            startActivity(new Intent(getApplicationContext(), loginActivity.class));
                        } else {
                            Toast.makeText(signupActivity.this, "ERROR SIGNING UP!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        TextView tv = (TextView) findViewById(R.id.signinPg);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignIn();
            }
        });


    }

    private void opensignIn() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

}