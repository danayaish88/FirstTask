package com.example.firsttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.signUpTV)
    TextView txSignUp;
    @BindView(R.id.logInBtn)
    Button btnLogIn;
    @BindView(R.id.email)
    EditText emailEditText;
    @BindView(R.id.password)
    EditText passwordEditText;

    String email = "";
    String password = "";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean validEmail = false;
    boolean validPassword = false;

    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPrefs = getPreferences(MODE_PRIVATE);
    }


    @OnClick(R.id.logInBtn)
    public void logIn() {
        getValues();
        chechValidEmail();
        checkValidPassword();
        if(validEmail && validPassword){
            checkUserExists();
        }

    }

    private void checkUserExists() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String regEmail = sh.getString("email", "");
        String regPassword = sh.getString("password", "");

        if(email.equals(regEmail) && password.equals(regPassword)){
            String name = sh.getString("firstName", "") + " " + sh.getString("lastName", "");
            Dashboard.startScreen(this, name);
        }
        else
            Toast.makeText(this, "Account does not exist", Toast.LENGTH_SHORT).show();

    }

    private void checkValidPassword() {
        if(password.length() > 6) {
            validPassword = true;
        }
        else {
            validPassword = false;
            Toast.makeText(getApplicationContext(),"Password must be 7 characters",Toast.LENGTH_SHORT).show();
        }
    }

    private void chechValidEmail() {
        if (email.matches(emailPattern))
        {
            validEmail = true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            validEmail = false;
        }
    }

    private void getValues() {
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
    }

    @OnClick(R.id.signUpTV)
    public void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }
}