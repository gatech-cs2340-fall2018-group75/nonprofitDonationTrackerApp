package com.example.asus.donationtracker.Controller;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.donationtracker.Model.AccountType;
import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.R;


public class registerAccount extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Spinner accountTypeSpinner;
    private Button submitBtn;
    private TextView goToLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        accountTypeSpinner = findViewById(R.id.regAccountType);
        submitBtn = findViewById(R.id.registerBtn);
        goToLoginLink = findViewById(R.id.regGoToLogin);

        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        goToLoginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(registerAccount.this, login.class));
            }
        });
    }

    private void submit() {
        Users users = Users.getInstance();
        String regPassword = pass.getText().toString();
        String regEmail = email.getText().toString();
        AccountType regAccountType = (AccountType) accountTypeSpinner.getSelectedItem();
        User newUser = new User(regEmail, regPassword, regAccountType);
        if(users.contains(newUser)) {
            Toast.makeText(this.getBaseContext(), "Account already exists",
                    Toast.LENGTH_LONG).show();
        } else {
            users.add(newUser);
            users.setCurrentUser(newUser);

            Intent goToMain = new Intent(registerAccount.this, mainMenu.class);
            startActivity(goToMain);
        }
    }
}
