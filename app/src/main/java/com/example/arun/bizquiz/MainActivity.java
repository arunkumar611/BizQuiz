package com.example.arun.bizquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitQuiz(View v) {

        for (int i = 1; i <= 1; i++) {
            CheckAnswer(i);
        }
        Toast.makeText(getApplicationContext(), "Awesome", Toast.LENGTH_SHORT).show();
    }

    public void CheckAnswer(int questionNumber) {
        int rightOrWrongIconId = getResources().getIdentifier("rightOrWrongIcon" + questionNumber, "id", getPackageName());
        int rightOrWrongTextId = getResources().getIdentifier("rightOrWrongText" + questionNumber, "id", getPackageName());
        ImageView rightOrWrongIcon = (ImageView) findViewById(rightOrWrongIconId);
        rightOrWrongIcon.setImageResource(R.drawable.wrong);
        TextView rightOrWrongText = (TextView) findViewById(rightOrWrongTextId);
        rightOrWrongText.setText("You are correct!");
    }
}
