package com.example.prototypeapi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity  extends AppCompatActivity {
    private Intent SubActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = (Button) findViewById(R.id.startbutton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.putExtra("difficulty", 0);
                startActivity(intent);
            }
        });

        Button startButton1 = (Button) findViewById(R.id.startbutton1);
        startButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.putExtra("difficulty", 1);
                startActivity(intent);
            }
        });

        Button startButton2 = (Button) findViewById(R.id.startbutton2);
        startButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
            }
        });
    }
}
