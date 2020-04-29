package com.example.gomoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class information extends AppCompatActivity implements View.OnClickListener {
    private Button buttonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        buttonOK = (Button) findViewById(R.id.button_OK);

        buttonOK.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        launchGomokuActivity();
    }

    private void launchGomokuActivity() {
        Intent MainActivity = new Intent(information.this, MainActivity.class);
        startActivity(MainActivity);
    }
}