package com.example.arun.bizquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.dial;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    String[] questionType = {"textInput", "checkBox", "radioButton"};
    int currentTextQuestionNumber = 1;
    int currentCheckBoxQuestionNumber = 1;
    int currentRadioButtonQuestionNumber = 1;
    boolean isCorrect = false;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * submitQuiz method checks the answer one by one and calls the checkAnswer method
    *
    */

    public void submitQuiz(View v) {
        for (int i = 0; i <= 0; i++) {
            CheckAnswer(i);
        }
        Toast.makeText(getApplicationContext(), "Awesome", Toast.LENGTH_SHORT).show();
    }

    /*
    * CheckAnswer method runs through the answers one by one, and displays whether the answer is correct or wrong
    * If wrong, then it displays the correct answer
    */
    public void CheckAnswer(int questionNumber) {
        int rightOrWrongIconId = getResources().getIdentifier("rightOrWrongIcon" + questionNumber, "id", getPackageName());
        int rightOrWrongTextId = getResources().getIdentifier("rightOrWrongText" + questionNumber, "id", getPackageName());

        switch (questionType[questionNumber]) {
            case "textInput":
                checkTextQuestion();
                break;
            case "checkBox":
                break;
            case "radioButton":
                break;
        }
        checkTextQuestion();
        ImageView rightOrWrongIcon = (ImageView) findViewById(rightOrWrongIconId);
        TextView rightOrWrongText = (TextView) findViewById(rightOrWrongTextId);
        if (isCorrect) {
            rightOrWrongIcon.setImageResource(R.drawable.correct);
            rightOrWrongText.setText("You are correct!");
        } else {
            rightOrWrongIcon.setImageResource(R.drawable.wrong);
            rightOrWrongText.setText("Correct answer is" + answer);
        }
    }

    /*
    * checkTextQuestion method checks whether Text based quiz answers are correct or wrong
    */
    public void checkTextQuestion() {
        int textResponseId = getResources().getIdentifier("textResponse" + currentTextQuestionNumber, "id", getPackageName());
        EditText textResponseView = (EditText) findViewById(textResponseId);
        String textResponse;
        textResponse = textResponseView.getText().toString();
        switch (currentTextQuestionNumber) {
            case 1:
                textResponse = textResponse.substring(0, Math.min(textResponse.length(), 5));
                textResponse = textResponse.toLowerCase();
                if (textResponse.equals("rupee")) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                    answer = "Rupees";
                }
                break;
        }
        currentTextQuestionNumber++;
    }


}
