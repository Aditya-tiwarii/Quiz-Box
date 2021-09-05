package com.example.android.quizbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    public static final String EMAIL = "email";
    FirebaseDatabase root;
    DatabaseReference ref;
    TextView tName, tEmail, tPhone;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tName = findViewById(R.id.name1_text);
        tEmail = findViewById(R.id.email1_text);
        tPhone = findViewById(R.id.phonenp1_text);

        root = FirebaseDatabase.getInstance();
        ref = root.getReference("users");

        Intent i = getIntent();
        email = i.getExtras().getString("email", "");
        if (email == null) {
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(email)) {
                        tName.setText(ds.child("name").getValue(String.class));
                        tEmail.setText(ds.child("email").getValue(String.class));
                        tPhone.setText(ds.child("phoneno").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), loginActivity.class));
        Toast.makeText(this, "LOGGED OUT", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void exit(View view) {
        Intent i = new Intent(this, dashboardActivity.class);
        startActivity(i);
    }

}