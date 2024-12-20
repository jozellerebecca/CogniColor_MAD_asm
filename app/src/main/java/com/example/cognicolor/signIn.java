package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signIn extends AppCompatActivity {

    private EditText username, password;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.sign_in_username);
        password = findViewById(R.id.password);
        Button btnSignIn = findViewById(R.id.btn_sign_up);

        databaseHelper = new DatabaseHelper(this);

        btnSignIn.setOnClickListener(view -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(signIn.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (databaseHelper.validateUser(user, pass)) {
                Toast.makeText(signIn.this, "Sign-in successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signIn.this, gamePlayMode.class);
                startActivity(intent);
            } else {
                Toast.makeText(signIn.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
