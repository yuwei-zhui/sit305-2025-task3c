package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private Button btnStartQuiz, btnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnCalculator = findViewById(R.id.btnCalculator);

        // Load saved user name (if any)
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("userName", "");
        etUserName.setText(savedName);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = etUserName.getText().toString().trim();
                if(userName.isEmpty()){
                    etUserName.setError("Please enter your name");
                    return;
                }
                // Save user name for re-use
                prefs.edit().putString("userName", userName).apply();
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
