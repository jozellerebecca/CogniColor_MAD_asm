package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signUp extends AppCompatActivity {

    private EditText username, password, email, dob, country;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.sign_up_username);
        password = findViewById(R.id.sign_up_password);
        email = findViewById(R.id.sign_up_email);
        dob = findViewById(R.id.sign_up_dob);
        country = findViewById(R.id.sign_up_country);
        Button btnSignUp = findViewById(R.id.btn_sign_up);

        databaseHelper = new DatabaseHelper(this);

        btnSignUp.setOnClickListener(view -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userDob = dob.getText().toString().trim();
            String userCountry = country.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty() || userEmail.isEmpty() || userDob.isEmpty() || userCountry.isEmpty()) {
                Toast.makeText(signUp.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            } else if (databaseHelper.addUser(user, pass, userEmail, userDob, userCountry)) {
                Toast.makeText(signUp.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signUp.this, profilePage.class);
                intent.putExtra("username", user);
                intent.putExtra("email", userEmail);
                intent.putExtra("dob", userDob);
                intent.putExtra("country", userCountry);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(signUp.this, "User already exists", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
