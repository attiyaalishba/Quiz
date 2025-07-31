package com.example.quiztimerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;


import com.example.quizgame.models.Question;
import com.example.quiztimerapp.QuestionBank;


// ✅ THIS IS REQUIRED

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, timerText, scoreText;
    Button option1, option2, option3, option4, btnFinish, btnNext;

    List<Question> questions;
    int currentIndex = 0;
    int correct = 0, wrong = 0;
    CountDownTimer timer;

    void loadQuestion() {
        if (currentIndex >= questions.size()) return;

        Question q = questions.get(currentIndex);
        questionText.setText(q.getQuestionText());

        String[] opts = q.getOptions();
        option1.setText(opts[0]);
        option2.setText(opts[1]);
        option3.setText(opts[2]);
        option4.setText(opts[3]);

        resetButtonColors();
        startTimer();
    }

    void startTimer() {
        timerText.setText("Time: 30");
        timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerText.setText("Time: 0");
                wrong++;
                scoreText.setText("Correct: " + correct + " | Wrong: " + wrong);
                disableOptions();
            }
        }.start();
    }

    void checkAnswer(int selectedIndex) {
        Question q = questions.get(currentIndex);
        if (timer != null) timer.cancel();

        if (selectedIndex == q.getCorrectIndex()) {
            correct++;
            getButtonByIndex(selectedIndex).setBackgroundColor(Color.GREEN);
        } else {
            wrong++;
            getButtonByIndex(selectedIndex).setBackgroundColor(Color.RED);
            getButtonByIndex(q.getCorrectIndex()).setBackgroundColor(Color.GREEN);
        }

        scoreText.setText("Correct: " + correct + " | Wrong: " + wrong);
        disableOptions();
    }

    Button getButtonByIndex(int i) {
        switch (i) {
            case 0: return option1;
            case 1: return option2;
            case 2: return option3;
            default: return option4;
        }
    }

    void disableOptions() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    void resetButtonColors() {
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        option1.setBackgroundResource(R.drawable.button_option);
        option2.setBackgroundResource(R.drawable.button_option);
        option3.setBackgroundResource(R.drawable.button_option);
        option4.setBackgroundResource(R.drawable.button_option);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        timerText = findViewById(R.id.timerText);
        scoreText = findViewById(R.id.scoreText);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        btnFinish = findViewById(R.id.btnFinish);
        btnNext = findViewById(R.id.btnNext);



        questions = com.example.quiztimerapp.QuestionBank.getQuestions();
        loadQuestion();

        option1.setOnClickListener(v -> checkAnswer(0));
        option2.setOnClickListener(v -> checkAnswer(1));
        option3.setOnClickListener(v -> checkAnswer(2));
        option4.setOnClickListener(v -> checkAnswer(3));

        btnNext.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex < questions.size()) {
                loadQuestion();
            } else {
                Toast.makeText(this, "No more questions", Toast.LENGTH_SHORT).show();
            }
        });

        btnFinish.setOnClickListener(v -> {
            if (timer != null) timer.cancel();
            Toast.makeText(this, "Game Over!\nCorrect: " + correct + " Wrong: " + wrong, Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
