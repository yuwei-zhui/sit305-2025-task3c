package com.example.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvWelcome, tvQuestion;
    private RadioGroup rgAnswers;
    private Button btnSubmit, btnNext;
    private ProgressBar progressBar;

    private List<Question> questionList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean isAnswered = false;

    private class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        tvWelcome = findViewById(R.id.tvWelcome);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgAnswers = findViewById(R.id.rgAnswers);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);

        String userName = getIntent().getStringExtra("userName");
        tvWelcome.setText("Welcome to the quiz, " + userName + "!");

        questionList.add(new Question("Which method is called first when an Activity starts?", new String[]{"onStart()", "onResume()", "onCreate()", "onRestart()"}, 2));
        questionList.add(new Question("What file format is used for Android layout files?", new String[]{"XML", "JSON", "HTML", "YAML"}, 0));
        questionList.add(new Question("Which tool is used to build Android projects in Android Studio?", new String[]{"Gradle", "Maven", "Ant", "Buck"}, 0));
        questionList.add(new Question("What does FCM stand for in Android?", new String[]{"Firebase Cloud Messaging", "Fast Connection Module", "File Cache Manager", "Fragment Context Menu"}, 0));
        questionList.add(new Question("Where are most Android apps published for distribution?", new String[]{"Amazon Appstore", "Huawei AppGallery", "APKMirror", "Google Play Store"}, 3));

        loadQuestion();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rgAnswers.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isAnswered) {
                    Toast.makeText(QuizActivity.this, "You already submitted. Please tap NEXT.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedButton = findViewById(selectedId);
                int answerIndex = rgAnswers.indexOfChild(selectedButton);
                Question current = questionList.get(currentQuestionIndex);

                if (answerIndex == current.correctAnswerIndex) {
                    selectedButton.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    selectedButton.setBackgroundColor(Color.RED);
                }
                isAnswered = true;
                int progress = (int) (((float) (currentQuestionIndex + 1) / questionList.size()) * 100);
                progressBar.setProgress(progress);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAnswered) {
                    Toast.makeText(QuizActivity.this, "Please submit your answer first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (currentQuestionIndex + 1 < questionList.size()) {
                    currentQuestionIndex++;
                    loadQuestion();
                } else {
                    // All questions answered, go to results page
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex > 0) {
            tvWelcome.setVisibility(View.GONE);
        }

        Question current = questionList.get(currentQuestionIndex);
        tvQuestion.setText(current.questionText);
        rgAnswers.clearCheck();
        for (int i = 0; i < rgAnswers.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rgAnswers.getChildAt(i);
            rb.setText(current.options[i]);
            rb.setBackgroundColor(Color.TRANSPARENT);
        }
        isAnswered = false;
    }
}
