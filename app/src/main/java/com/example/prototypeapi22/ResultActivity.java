package com.example.prototypeapi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity  extends AppCompatActivity {
    private Intent SubActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView score = (TextView) findViewById(R.id.score);
        Button startButton = (Button) findViewById(R.id.startButton);

        Intent intent = getIntent();
        boolean[] answers = intent.getBooleanArrayExtra("answers");

        int trueCount = 0;
        for (boolean answer : answers) {
            if (answer) {
                trueCount++;
            }
        }
        score.setText(String.valueOf(trueCount));

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),StartActivity.class);
                startActivity(intent);
            }
        });
    }
}
