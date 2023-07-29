package com.barmej.guesstheanswer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShaireActivity extends AppCompatActivity {
private String mQuestiontext;
public EditText mEditTextShairetitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaire);
        mEditTextShairetitle =(EditText) findViewById(R.id.edit_text_shaire_title);
        mQuestiontext = getIntent().getStringExtra("question text extra");
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share_title","");
        mEditTextShairetitle.setText(questionTitle);
    }
    public void onShaireQuestionclicked (View view){
       String questionTitle= mEditTextShairetitle.getText().toString();
        Intent shaireIntent = new Intent();
        shaireIntent.setAction(Intent.ACTION_SEND);
        shaireIntent.putExtra(Intent.EXTRA_TEXT, questionTitle +"\n"+mQuestiontext );
        shaireIntent.setType("text/plain");
        startActivity(shaireIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questionTitle);
        editor.apply();
    }
}