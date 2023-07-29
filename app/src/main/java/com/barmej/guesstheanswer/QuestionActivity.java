package com.barmej.guesstheanswer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private String[] QUESTIONS ;
    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true
    };

    private  String[] ANSWERS_DETAILS ;
    private TextView mquestion;
    private String mCurrentQuestion,mCurrentAnswerDetail;
    private Boolean mCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang","");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this,appLang);

        setContentView(R.layout.activity_main);
        mquestion= findViewById(R.id.text_view_question);
        QUESTIONS = getResources().getStringArray(R.array.questions);
        ANSWERS_DETAILS= getResources().getStringArray(R.array.answers_details);
        showNewQuestion () ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_Change_Lang){
            showLanguageDialogue();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void  showLanguageDialogue(){
        AlertDialog alertdialogue = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }
                        saveLanguage(language);
                        LocaleHelper.setLocale(QuestionActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create();
        alertdialogue.show();
    }
    public void saveLanguage(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang", lang);
        editor.apply();
    }
    public void showNewQuestion (){
        Random random = new Random();
        int randomIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS [randomIndex];
        mCurrentAnswerDetail =ANSWERS_DETAILS[randomIndex];
        mCurrentAnswer = ANSWERS[randomIndex];
        mquestion.setText( mCurrentQuestion );
    }
    public void changequestion(View view){
        showNewQuestion();
    }
    public void trueAnswer (View view){
        if (mCurrentAnswer == true){
            Toast.makeText(this,"اجابة صحيحة",Toast.LENGTH_SHORT).show();
            showNewQuestion ();
        }else {
            Toast.makeText(this,"اجابة خاطئة",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this,AnswerActivity.class);
            intent.putExtra("answer_question",  mCurrentAnswerDetail );
            startActivity(intent);
        }
    }
    public void falseAnswer (View view){
        if (mCurrentAnswer == false){
            Toast.makeText(this,"اجابة صحيحة",Toast.LENGTH_SHORT).show();
            showNewQuestion ();
        }else {
            Toast.makeText(this,"اجابة خاطئة",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionActivity.this,AnswerActivity.class);
            intent.putExtra("answer_question",  mCurrentAnswerDetail );
            startActivity(intent);
        }
    }
    public void onShaireQuestionclicked(View view){
        Intent intent = new Intent(QuestionActivity.this,ShaireActivity.class);
        intent.putExtra("question text extra",  mCurrentQuestion );
        startActivity(intent);
    }
}