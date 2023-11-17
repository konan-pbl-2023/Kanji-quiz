package com.example.prototypeapi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        String[] kaitoes = intent.getStringArrayExtra("kaitoes");
        String[] mondais = intent.getStringArrayExtra("mondais");
        String[] yomis = intent.getStringArrayExtra("yomis");
        final int N = intent.getIntExtra("allQuestion", 1);

        int trueCount = 0;
        for (boolean answer : answers) {
            if (answer) {
                trueCount++;
            }
        }

        if (trueCount <  answers.length) {
            ImageView kusudamaImage = findViewById(R.id.kusudama);
            ImageView sakuraImage = findViewById(R.id.sakura);
            kusudamaImage.setVisibility(View.INVISIBLE);
            sakuraImage.setVisibility(View.INVISIBLE);
        }

        score.setText(String.valueOf(trueCount));

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AnswerActivity.class);
                intent.putExtra("allQuestion", N);
                intent.putExtra("answers", answers);
                intent.putExtra("kaitoes", kaitoes);
                intent.putExtra("mondais", mondais);
                intent.putExtra("yomis", yomis);
                startActivity(intent);
            }
        });
    }
}
