package com.example.gomoku;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
    //Creating button objects, which extend (inherit) the View class
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.start);

        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        launchGomokuActivity();
    }

    private void launchGomokuActivity() {
        Intent GomokuActivity = new Intent(MainActivity.this, Gomoku.class);
        startActivity(GomokuActivity);
    }
}