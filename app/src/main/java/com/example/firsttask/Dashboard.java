package com.example.firsttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends AppCompatActivity {

    @BindView(R.id.buttonlogout)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra("msg");

        TextView textView = findViewById(R.id.hello);
        textView.setText("Hello " + message);
    }

    @OnClick(R.id.buttonlogout)
    public void logOutt(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static void startScreen(Context callingcontext, String name){
        Intent intent = new Intent(callingcontext, Dashboard.class);
        intent.putExtra("msg",name);
        callingcontext.startActivity(intent);
    }
}