package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView mAnswerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        mAnswerTextView=(TextView)findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra("answer_question");
        if (answer != null){
            mAnswerTextView.setText(answer);
        }
    }
    public void rturnClicked (View view){
        finish();
    }
}