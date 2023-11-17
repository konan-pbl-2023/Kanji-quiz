package com.example.prototypeapi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {
    private Intent SubActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ans);

        Intent intent = getIntent();
        final int[] questionNumber = new int[1];
        questionNumber[0] = intent.getIntExtra("questionNumber", 1);
        final int N = intent.getIntExtra("allQuestion", 1);
        boolean[] answers = intent.getBooleanArrayExtra("answers");
        String[] kaitoes = intent.getStringArrayExtra("kaitoes");
        String[] mondais = intent.getStringArrayExtra("mondais");
        String[] yomis = intent.getStringArrayExtra("yomis");

        Button previousButton = (Button) findViewById(R.id.button2);
        Button nextButton = (Button) findViewById(R.id.NEXTbutton);
        TextView Q_1 = (TextView) findViewById(R.id.Q_1);
        TextView ans = (TextView) findViewById(R.id.ans);
        TextView ansMe = (TextView) findViewById(R.id.ans_me);
        TextView trueAns = (TextView) findViewById(R.id.true_ans);

        Q_1.setText(mondais[questionNumber[0] - 1]);
        ans.setText(yomis[questionNumber[0] - 1]);
        ansMe.setText(kaitoes[questionNumber[0] - 1]);
        if (answers[questionNumber[0] - 1]) {
            trueAns.setText("⭕");
        } else {
            trueAns.setText("❌");
        }

        previousButton.setEnabled(false);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (N <= questionNumber[0]) {
                    Intent intent = new Intent(getApplication(), StartActivity.class);
                    startActivity(intent);
                } else {
                    questionNumber[0] = questionNumber[0] + 1;
                    Q_1.setText(mondais[questionNumber[0] - 1]);
                    ans.setText(yomis[questionNumber[0] - 1]);
                    ansMe.setText(kaitoes[questionNumber[0] - 1]);
                    if (answers[questionNumber[0] - 1]) {
                        trueAns.setText("⭕");
                    } else {
                        trueAns.setText("❌");
                    }

                    if (1 < questionNumber[0]) {
                        previousButton.setEnabled(true);
                    }
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                questionNumber[0] = questionNumber[0] - 1;

                Q_1.setText(mondais[questionNumber[0] - 1]);
                ans.setText(yomis[questionNumber[0] - 1]);
                ansMe.setText(kaitoes[questionNumber[0] - 1]);
                if (answers[questionNumber[0] - 1]) {
                    trueAns.setText("⭕");
                } else {
                    trueAns.setText("❌");
                }

                if (questionNumber[0] <= 1) {
                    previousButton.setEnabled(false);
                }
            }
        });
    }
}
