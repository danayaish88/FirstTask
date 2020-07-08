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

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.logIn)
    TextView logIntx;
    @BindView(R.id.signUp)
    Button btnSignUp;
    @BindView(R.id.firstName)
    EditText firstNameET;
    @BindView(R.id.lastName)
    EditText lastNameET;
    @BindView(R.id.email)
    EditText emailET;
    @BindView(R.id.password)
    EditText passwordET;

    boolean validFirstName = false;
    boolean validLasttName = false;
    boolean validEmail = false;
    boolean validPassword = false;

    String fn = "";
    String ln = "";
    String email = "";
    String password = "";

    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mPrefs = getPreferences(MODE_PRIVATE);

    }

    @OnClick(R.id.logIn)
    public void click(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.signUp)
    public void signUp() {
        checkFirstName();
        checkLasttName();
        checkEmail();
        checkPassword();
        if(validPassword && validEmail && validLasttName && validFirstName){
            saveValues();
            Dashboard.startScreen(this, fn + " " + ln);
        }
            
    }

    private void saveValues() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("firstName", fn);
        myEdit.putString("lastName", ln);
        myEdit.putString("email", email);
        myEdit.putString("password", password);

        myEdit.apply();
    }

    private void checkPassword() {
        password = passwordET.getText().toString().trim();
        if(password.length() > 6) {
            validPassword = true;
        }
        else {
            validPassword = false;
            Toast.makeText(getApplicationContext(),"Password must be 7 characters",Toast.LENGTH_SHORT).show();
        }
    }

    private void checkEmail() {
        email = emailET.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

    private void checkLasttName() {
        ln = lastNameET.getText().toString().trim();
        if(ln.length() > 2){
            validLasttName = true;
        }
        else{
            Toast.makeText(this, "Last Name is too short", Toast.LENGTH_SHORT).show();
            validLasttName = false;
        }
    }

    private void checkFirstName() {
        fn = firstNameET.getText().toString().trim();
        if(fn.length() > 2){
            validFirstName = true;
        }
        else{
            Toast.makeText(this, "First Name is too short", Toast.LENGTH_SHORT).show();
            validFirstName = false;
        }
    }
}