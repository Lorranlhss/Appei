package com.biomaamazonia.appei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapaButton = findViewById(R.id.buttonMapa);
        Button faunaFloraButton = findViewById(R.id.buttonFaunaFlora);
        Button quizButton = findViewById(R.id.buttonQuiz);

        // Navegação para a tela de Mapa
        mapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                startActivity(intent);
            }
        });

        // Navegação para a tela de Fauna e Flora
        faunaFloraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FaunaFloraActivity.class);
                startActivity(intent);
            }
        });

        // Navegação para a tela de Quiz
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
