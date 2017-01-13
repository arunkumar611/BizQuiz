package com.example.arun.bizquiz;

import android.os.Bundle;
import android.provider.Settings;
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
    EditText textResponseView;
    ImageView rightOrWrongIconView;
    TextView rightOrWrongTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText textResponseView1 = (EditText) findViewById(R.id.textResponse1);
        textResponseView = textResponseView1;
        ImageView rightOrWrongIcon = (ImageView) findViewById(R.id.rightOrWrongIcon1);
        rightOrWrongIconView = rightOrWrongIcon;
        TextView rightOrWrongText = (TextView) findViewById(R.id.rightOrWrongText1);
        rightOrWrongTextView = rightOrWrongText;
    }

    /*
    * submitQuiz method checks the answer one by one and calls the checkAnswer method
    */
    public void submitQuiz(View v) {
//        for (int i = 1; i <= 1; i++) {
//            int textResponseId = getResources().getIdentifier("textResponse" + i, "id", getPackageName());
//            EditText textResponseView = (EditText) findViewById(textResponseId);
//            textResponse[0] = textResponseView.getText().toString();
//            textResponse[0] = textResponse[0].toLowerCase();
//        }

        currentTextQuestionNumber = 1;
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
//        int rightOrWrongIconId = getResources().getIdentifier("rightOrWrongIcon" + questionNumber, "id", getPackageName());
//        int rightOrWrongTextId = getResources().getIdentifier("rightOrWrongText" + questionNumber, "id", getPackageName());

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

        if (isCorrect) {
            rightOrWrongIconView.setImageResource(R.drawable.correct);
            rightOrWrongTextView.setText("You are correct!");
        } else {
            rightOrWrongIconView.setImageResource(R.drawable.wrong);
            rightOrWrongTextView.setText("Correct answer is Rupees");
        }
    }

    /*
    * checkTextQuestion method checks whether Text based quiz answers are correct or wrong
    */
    public void checkTextQuestion() {
        answer = textResponseView.getText().toString();
        answer = answer.toLowerCase();
        switch (currentTextQuestionNumber) {
            case 1:
                answer = answer.substring(0, Math.min(answer.length(), 5));
                if (answer.equals("rupee")) {
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
