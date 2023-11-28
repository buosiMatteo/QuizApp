package net.matteobuosi.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Quiz[] quiz;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;
    private Button mGoAhead;
    public TextView mQuestionText;
    private int num = 0;
    private int point = 0;
    private static int mCurrentIndex = 0;
    private static boolean isSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = new Quiz[]{new Quiz(getString(R.string.questionText1), getString(R.string.answer1_1), getString(R.string.answer1_2), getString(R.string.answer1_3), getString(R.string.answer1_4), 4),
                new Quiz(getString(R.string.questionText2), getString(R.string.answer2_1), getString(R.string.answer2_2), getString(R.string.answer2_3), getString(R.string.answer2_4), 3),
                new Quiz(getString(R.string.questionText3), getString(R.string.answer1_1), getString(R.string.answer3_2), getString(R.string.answer3_3), getString(R.string.answer3_4), 3),
                new Quiz(getString(R.string.questionText4), getString(R.string.answer4_1), getString(R.string.answer4_2), getString(R.string.answer4_3), getString(R.string.answer4_4), 2),};

        mAnswer1 = findViewById(R.id.answer1);
        mAnswer2 = findViewById(R.id.answer2);
        mAnswer3 = findViewById(R.id.answer3);
        mAnswer4 = findViewById(R.id.answer4);
        mGoAhead = findViewById(R.id.goAhead);
        mQuestionText = findViewById(R.id.questionText);

        mGoAhead.setOnClickListener(v -> {
            mCurrentIndex++;
            if (isSelected) {
                if (mCurrentIndex < quiz.length) {
                    setTextElement();
                    stateButton(true, mAnswer1, mAnswer2, mAnswer3, mAnswer4);
                    resetColorButton(mAnswer1, mAnswer2, mAnswer3, mAnswer4);
                }
            } else if (isFinishQuestion()) {
                Intent intent = PointActivity.getInstanceIntent(MainActivity.this, point, quiz.length);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "you have to select an answer!", Toast.LENGTH_SHORT).show();
            }
        });

        setTextElement();

    }

    @SuppressLint("NonConstantResourceId")
    public void clickButton(View v) {
        int id = v.getId();
        Button b = findViewById(id);
        isSelected = true;
        stateButton(false, mAnswer1, mAnswer2, mAnswer3, mAnswer4);
        switch (id) {
            case R.id.answer1:
                num = 1;
                break;
            case R.id.answer2:
                num = 2;
                break;
            case R.id.answer3:
                num = 3;
                break;
            case R.id.answer4:
                num = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }

        checkCorrectAnswer(num, b);
    }

    private boolean isFinishQuestion() {
        return mCurrentIndex>=quiz.length;
    }

    private void setTextElement() {
        mAnswer1.setText(quiz[mCurrentIndex].getAnswer1());
        mAnswer2.setText(quiz[mCurrentIndex].getAnswer2());
        mAnswer3.setText(quiz[mCurrentIndex].getAnswer3());
        mAnswer4.setText(quiz[mCurrentIndex].getAnswer4());
        mQuestionText.setText(quiz[mCurrentIndex].getQuestionText());
    }

    public void stateButton(boolean enable, Button... buttons) {
        for (Button button : buttons) {
            button.setEnabled(enable);
        }
    }

    private void checkCorrectAnswer(int num, Button b) {
        if (quiz[mCurrentIndex].isCorrectAnswer(num)) {
            Toast.makeText(this, "Congratulations! Correct answer!", Toast.LENGTH_SHORT).show();
            b.setBackgroundColor(Color.GREEN);
            point++;
        } else {
            Toast.makeText(this, "Sorry, uncorrected answer!", Toast.LENGTH_SHORT).show();
            b.setBackgroundColor(Color.RED);
            getButton(quiz[mCurrentIndex].getTrueAnswer()).setBackgroundColor(Color.GREEN);
        }


    }

    private Button getButton(int n) {
        Button b = null;
        switch (n) {
            case 1:
                b = findViewById(R.id.answer1);
                break;
            case 2:
                b = findViewById(R.id.answer2);
                break;
            case 3:
                b = findViewById(R.id.answer3);
                break;
            case 4:
                b = findViewById(R.id.answer4);
                break;
        }

        return b;

    }

    private void resetColorButton(Button... buttons) {
        for (Button button : buttons) {
            button.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}