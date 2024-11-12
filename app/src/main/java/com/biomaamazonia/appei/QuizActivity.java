package com.biomaamazonia.appei;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import android.view.View;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    // Somente declare as variáveis aqui
    DatabaseReference mDatabase;
    TextView perguntaTextView;
    RadioGroup opcoesRadioGroup;
    Button proximaPerguntaButton;

    private final ArrayList<String> perguntas = new ArrayList<>();
    private final ArrayList<String[]> opcoes = new ArrayList<>();
    private final ArrayList<String> respostasCorretas = new ArrayList<>();
    private String userName;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private boolean marcador = false;
    private int pontuacao = 0;
    private int perguntaAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Inicialize as views dentro do onCreate
        perguntaTextView = findViewById(R.id.perguntaTextView);
        opcoesRadioGroup = findViewById(R.id.opcoesRadioGroup);
        proximaPerguntaButton = findViewById(R.id.proximaPerguntaButton);

        // Inicializando o Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("quiz");

        // Carregar as perguntas do Firebase
        mDatabase.child("perguntas").addValueEventListener(new ValueEventListener() { //*
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                perguntas.clear();
                opcoes.clear();
                respostasCorretas.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Adicionar a pergunta
                    perguntas.add(snapshot.child("pergunta").getValue(String.class));

                    // Adicionar as opções como ArrayList
                    ArrayList<String> opcoesList = snapshot.child("opcoes").getValue(new GenericTypeIndicator<>() {});
                    if (opcoesList == null) {
                        opcoesList = new ArrayList<>(); // Inicializa como uma lista vazia se for null
                    }
                    String[] opcoesArray = new String[opcoesList.size()];
                    opcoesArray = opcoesList.toArray(opcoesArray);
                    opcoes.add(opcoesArray);

                    // Adicionar a resposta correta
                    respostasCorretas.add(snapshot.child("respostaCorreta").getValue(String.class));

                }

                // Exibir a primeira pergunta
                exibirPergunta();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuizActivity.this, "Erro ao carregar quiz.", Toast.LENGTH_SHORT).show();
            }
        });

        // Condição para exibir o nome do usuário ou o visitante
        if (currentUser != null && currentUser.getDisplayName() != null) {
            userName = currentUser.getDisplayName(); // Nome do usuário logado
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            userName = sharedPreferences.getString("userName", "Visitante");
        }

        proximaPergunta();
    }


    private void proximaPergunta() {
        opcoesRadioGroup.setVisibility(View.VISIBLE);
        proximaPerguntaButton.setText("Próxima Pergunta");

        proximaPerguntaButton.setOnClickListener(v -> {
            verificarResposta();
            if (marcador){
                perguntaAtual++;
                marcador = false;
            }
            if (perguntaAtual < perguntas.size()) {
                exibirPergunta();
            } else {
                finalizarQuiz();
            }
        });
    }


    private void finalizarQuiz() {

        opcoesRadioGroup.setVisibility(View.GONE);
        proximaPerguntaButton.setText("Refazer o quiz");
        Toast.makeText(QuizActivity.this, "Fim do quiz!", Toast.LENGTH_SHORT).show();
        perguntaTextView.setText("Parabéns, " + userName + "! Sua pontuação foi de: " + pontuacao + "/" + perguntas.size());

        if (userName != null) {
            mDatabase.child("pontuacoes").child(userName).setValue(pontuacao);
        }
        proximaPerguntaButton.setOnClickListener(v -> reiniciarQuiz());
    };


    private void reiniciarQuiz() {
        marcador = false;
        perguntaAtual = 0;
        pontuacao = 0;
        exibirPergunta();
        proximaPergunta();
    };


    // Exibe a pergunta atual no layout
    private void exibirPergunta() {
        perguntaTextView.setText(perguntas.get(perguntaAtual));
        opcoesRadioGroup.clearCheck(); // Limpar a seleção anterior

        // Configurando os RadioButtons com as opções
        ((RadioButton) opcoesRadioGroup.getChildAt(0)).setText(opcoes.get(perguntaAtual)[0]);
        ((RadioButton) opcoesRadioGroup.getChildAt(1)).setText(opcoes.get(perguntaAtual)[1]);
        ((RadioButton) opcoesRadioGroup.getChildAt(2)).setText(opcoes.get(perguntaAtual)[2]);
    }


    // Verifica se a resposta selecionada está correta
    private void verificarResposta() {
        int selectedId = opcoesRadioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) { // Verifica se o usuário selecionou alguma opção
            marcador = true;
            RadioButton selectedRadioButton = findViewById(selectedId);
            String respostaSelecionada = selectedRadioButton.getText().toString();

            String respostaCorreta = respostasCorretas.get(perguntaAtual);
            if (respostaSelecionada.equals(respostaCorreta)) {
                Toast.makeText(this, "Resposta correta!", Toast.LENGTH_SHORT).show();
                pontuacao++;
            } else {
                Toast.makeText(this, "Resposta incorreta. A correta é: " + respostaCorreta, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, selecione uma resposta.", Toast.LENGTH_SHORT).show();

        }
    }
}
