package com.example.gomoku;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
    //Creating button objects, which extend (inherit) the View class
    private Button buttonStart;
    private ImageButton buttoninfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.start);

        buttonStart.setOnClickListener(this);

        buttoninfo = (ImageButton) findViewById(R.id.button_info);

        buttoninfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                launchGomokuActivity();
                break;

            case R.id.button_info:
                launchinformation();
                break;
        }
    }

    private void launchGomokuActivity() {
        Intent GomokuActivity = new Intent(MainActivity.this, Gomoku.class);
        startActivity(GomokuActivity);
    }

    private void launchinformation() {
        Intent information = new Intent(MainActivity.this, information.class);
        startActivity(information);
    }
}