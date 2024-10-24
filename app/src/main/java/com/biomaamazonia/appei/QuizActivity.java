
package com.biomaamazonia.appei;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView perguntaTextView;
    private RadioGroup opcoesRadioGroup;
    private Button proximaPerguntaButton;

    private ArrayList<String> perguntas = new ArrayList<>();
    private ArrayList<String[]> opcoes = new ArrayList<>();
    private ArrayList<String> respostasCorretas = new ArrayList<>();

    private int perguntaAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Inicializando as views
        perguntaTextView = findViewById(R.id.perguntaTextView);
        opcoesRadioGroup = findViewById(R.id.opcoesRadioGroup);
        proximaPerguntaButton = findViewById(R.id.proximaPerguntaButton);

        // Inicializando o Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("quiz").child("perguntas");

        // Carregar as perguntas do Firebase
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                perguntas.clear();
                opcoes.clear();
                respostasCorretas.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Adicionar a pergunta
                    perguntas.add(snapshot.child("pergunta").getValue(String.class));

                    // Adicionar as opções como ArrayList
                    ArrayList<String> opcoesList = (ArrayList<String>) snapshot.child("opcoes").getValue();
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
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(QuizActivity.this, "Erro ao carregar quiz.", Toast.LENGTH_SHORT).show();
            }
        });

        // Avançar para a próxima pergunta ao clicar no botão
        proximaPerguntaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta();
                perguntaAtual++;
                if (perguntaAtual < perguntas.size()) {
                    exibirPergunta();
                } else {
                    Toast.makeText(QuizActivity.this, "Fim do quiz!", Toast.LENGTH_SHORT).show();
                    // Aqui você pode reiniciar o quiz ou mostrar os resultados
                    perguntaAtual = 0;
                    exibirPergunta();
                }
            }
        });
    }

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
            RadioButton selectedRadioButton = findViewById(selectedId);
            String respostaSelecionada = selectedRadioButton.getText().toString();

            String respostaCorreta = respostasCorretas.get(perguntaAtual);
            if (respostaSelecionada.equals(respostaCorreta)) {
                Toast.makeText(this, "Resposta correta!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Resposta incorreta. A correta é: " + respostaCorreta, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, selecione uma resposta.", Toast.LENGTH_SHORT).show();
        }
    }
}
