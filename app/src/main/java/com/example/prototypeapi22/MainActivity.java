package com.example.prototypeapi22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KanjiTable kanjiTable = new KanjiTable(getResources());
        final int N=10;
        Intent intent = getIntent();
        int difficulty= intent.getIntExtra("difficulty", 0);

        int min=0;      //a:0   b:207   c:818
        int max=1443;    //a:206 b:817   c:1443
        if(difficulty==0){
            min=0;
            max=206;
        }else if(difficulty==1){
            min=207;
            max=817;
        }else if(difficulty==2){
            min=818;
            max=1443;
        }

        kanjiTable.setNum(min, max, N);
        kanjiTable.setIndex(0);
        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView question = (TextView) findViewById(R.id.question);
        EditText input = (EditText) findViewById(R.id.inputAnswer);
        TextView questionNum = (TextView) findViewById(R.id.questionNum);

        questionNum.setText(String.valueOf(kanjiTable.getIndex()+1));
        question.setText(kanjiTable.getWord());

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kanjiTable.setKaito(input.getText().toString());
                kanjiTable.check();
                if(kanjiTable.getIndex()+1<N) {
                    input.getEditableText().clear();
                    kanjiTable.nextIndex();
                    questionNum.setText(String.valueOf(kanjiTable.getIndex() + 1));
                    question.setText(kanjiTable.getWord());
                }else{
                    // 次の画面へ
                    Intent intent = new Intent(getApplication(),ResultActivity.class);
                    intent.putExtra("answers", kanjiTable.getAnswers());
                    intent.putExtra("kaitoes", kanjiTable.getKaitoes());
                    intent.putExtra("mondais", kanjiTable.getMondais());
                    intent.putExtra("yomis", kanjiTable.getYomis());
                    intent.putExtra("allQuestion", N);
                    startActivity(intent);
                }
            }
        });

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals("")) {
                    return source;
                }
                for (int i = start; i < end; i++) {
                    if (!Character.toString(source.charAt(i)).matches("[ぁ-んー]")) {
                        return "";
                    }
                }
                return null;
            }
        };
        input.setFilters(filters);
        /*
        final int N = 10;
        final String QUESTION = "犬";
        final String ANSER = "inu";
        boolean answers[] = new boolean[N];

        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView question = (TextView) findViewById(R.id.question);
        EditText input = (EditText) findViewById(R.id.inputAnswer);

        question.setText(QUESTION);

        nextButton.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            public void onClick(View v) {
                if (Objects.equals(ANSER, input.getText().toString())) {
                    Log.d("正誤", i + 1 + "問目は正解です");
                    answers[i] = true;
                } else {
                    Log.d("正誤", i + 1 + "問目は不正解です");
                    answers[i] = false;
                }
                question.setText(QUESTION);
                i += 1;
                input.getEditableText().clear();
                if (N <= i) {
                    Log.d("終了", "終了しました。");
                    Intent intent = new Intent(getApplication(),ResultActivity.class);
                    intent.putExtra("answers", answers);
                    startActivity(intent);
                }
            }

        });
        */
    }
}
