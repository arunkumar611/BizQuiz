package com.example.arun.bizquiz;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.dial;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    String[] questionType = {"textInput", "checkBox", "radioButton", "checkBox", "textInput", "radioButton"};
    int currentQuestionNumber = 0;
    int currentTextQuestionNumber = 0;
    int currentCheckBoxQuestionNumber = 0;
    int currentRadioButtonQuestionNumber = 0;
    String[] answers = {"Rupees", "A, C & D", "Zimbabwe", "A, B & D", "South America", "MVR"};
    EditText[] textResponseView = new EditText[2];
    CheckBox[] checkBoxResponseView = new CheckBox[8];
    RadioButton[] radioButtonResponseView = new RadioButton[8];
    RadioGroup[] radioButtonResponseViewGroup = new RadioGroup[2];
    ImageView[] rightOrWrongIconView = new ImageView[6];
    TextView[] rightOrWrongTextView = new TextView[6];

    int score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 2; i++) {
            int textResponseId = getResources().getIdentifier("textResponse" + (i + 1), "id", getPackageName());
            EditText textResponse = (EditText) findViewById(textResponseId);
            textResponseView[i] = textResponse;
        }

        for (int i = 0; i < 2; i++) {
            int radioButtonResponseGroupId = getResources().getIdentifier("radioButtonResponse" + (i + 1), "id", getPackageName());
            radioButtonResponseViewGroup[i] = (RadioGroup) findViewById(radioButtonResponseGroupId);
            for (int j = 0; j < 4; j++) {
                int checkBoxResponseId = getResources().getIdentifier("checkBoxResponse" + (i + 1) + "." + (j + 1), "id", getPackageName());
                CheckBox checkBoxResponse = (CheckBox) findViewById(checkBoxResponseId);
                checkBoxResponseView[(i * 4) + j] = checkBoxResponse;
                int radioButtonResponseId = getResources().getIdentifier("radioButtonResponse" + (i + 1) + "." + (j + 1), "id", getPackageName());
                RadioButton radioButtonResponse = (RadioButton) findViewById(radioButtonResponseId);
                radioButtonResponseView[(i * 4) + j] = radioButtonResponse;
            }
        }

        for (int i = 0; i < 6; i++) {
            int rightOrWrongIconId = getResources().getIdentifier("rightOrWrongIcon" + (i + 1), "id", getPackageName());
            ImageView rightOrWrongIcon = (ImageView) findViewById(rightOrWrongIconId);
            rightOrWrongIconView[i] = rightOrWrongIcon;
            int rightOrWrongTextId = getResources().getIdentifier("rightOrWrongText" + (i + 1), "id", getPackageName());
            TextView rightOrWrongText = (TextView) findViewById(rightOrWrongTextId);
            rightOrWrongTextView[i] = rightOrWrongText;
        }
    }

    /*
    * submitQuiz method checks the answer one by one and calls the checkAnswer method
    */
    public void submitQuiz(View v) {
        if (currentQuestionNumber == 6) {
            Toast.makeText(getApplicationContext(), "Reset the game to continue", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < 6; i++) {
            CheckAnswer(i);
        }

        if (score > 4) {
            Toast.makeText(getApplicationContext(), "Awesome. You've got " + score + " questions correct", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nice try. You've got " + score + " questions correct. Why don't you learn and re-try?", Toast.LENGTH_LONG).show();
        }
    }

    /*
    * CheckAnswer method runs through the answers one by one, and displays whether the answer is correct or wrong
    * If wrong, then it displays the correct answer
    */
    public void CheckAnswer(int questionNumber) {
        switch (questionType[questionNumber]) {
            case "textInput":
                checkTextQuestion();
                break;
            case "checkBox":
                checkCheckBoxQuestion();
                break;
            case "radioButton":
                checkRadioQuestion();
                break;
        }
    }

    /*
   * setCurrentQuestionRight method sets the 'correct' image and displays text indicating that the answer is correct
   */
    public void setCurrentQuestionRight() {
        rightOrWrongIconView[currentQuestionNumber].setImageResource(R.drawable.correct);
        rightOrWrongTextView[currentQuestionNumber].setText("You are correct!");
        score++;
    }

    /*
   * setCurrentQuestionWrong method sets the 'wrong' image and displays the correct answer
   */
    public void setCurrentQuestionWrong() {
        rightOrWrongIconView[currentQuestionNumber].setImageResource(R.drawable.wrong);
        rightOrWrongTextView[currentQuestionNumber].setText("Correct answer is " + answers[currentQuestionNumber]);
    }

    /*
    * checkTextQuestion method checks whether Text based quiz answers are correct or wrong
    */
    public void checkTextQuestion() {
        String textAnswer = textResponseView[currentTextQuestionNumber].getText().toString();
        textAnswer = textAnswer.toLowerCase();
        switch (currentTextQuestionNumber) {
            case 0:
                textAnswer = textAnswer.substring(0, Math.min(textAnswer.length(), 5));
                if (textAnswer.equals("rupee")) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
            case 1:
                if (textAnswer.equals("south america")) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
        }
        currentTextQuestionNumber++;
        currentQuestionNumber++;
    }

    /*
    *    checkRadioQuestion method checks whether Radio Question answers are correct or wrong
    */
    public void checkCheckBoxQuestion() {
        switch (currentCheckBoxQuestionNumber) {
            case 0:
                if (checkBoxResponseView[0].isChecked() && (!checkBoxResponseView[1].isChecked()) && checkBoxResponseView[2].isChecked() && checkBoxResponseView[3].isChecked()) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
            case 1:
                if (checkBoxResponseView[4].isChecked() && checkBoxResponseView[5].isChecked() && (!checkBoxResponseView[6].isChecked()) && checkBoxResponseView[7].isChecked()) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
        }
        currentCheckBoxQuestionNumber++;
        currentQuestionNumber++;
    }

    /*
   *    checkRadioQuestion method checks whether Radio Question answers are correct or wrong
   */
    public void checkRadioQuestion() {
        switch (currentRadioButtonQuestionNumber) {
            case 0:
                if ((!radioButtonResponseView[0].isChecked()) && (radioButtonResponseView[1].isChecked()) && (!radioButtonResponseView[2].isChecked()) && (!radioButtonResponseView[3].isChecked())) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
            case 1:
                if ((!radioButtonResponseView[4].isChecked()) && (radioButtonResponseView[5].isChecked()) && (!radioButtonResponseView[6].isChecked()) && (!radioButtonResponseView[7].isChecked())) {
                    setCurrentQuestionRight();
                } else {
                    setCurrentQuestionWrong();
                }
                break;
        }
        currentRadioButtonQuestionNumber++;
        currentQuestionNumber++;
    }

    public void resetQuiz(View v) {
        currentQuestionNumber = 0;
        currentTextQuestionNumber = 0;
        currentCheckBoxQuestionNumber = 0;
        currentRadioButtonQuestionNumber = 0;

        for (int i = 0; i < 6; i++) {
            rightOrWrongIconView[i].setImageDrawable(null);
            rightOrWrongTextView[i].setText("");
        }
        textResponseView[0].getText().clear();
        textResponseView[1].getText().clear();
        radioButtonResponseViewGroup[0].clearCheck();
        radioButtonResponseViewGroup[1].clearCheck();
        for (int i = 0; i < 8; i++) {
            checkBoxResponseView[i].setChecked(false);
        }
    }
}