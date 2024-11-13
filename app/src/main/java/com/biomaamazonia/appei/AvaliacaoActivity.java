package com.biomaamazonia.appei;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AvaliacaoActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // Configuração dos botões de avaliação
        configureButton(R.id.buttonExcelente, "excelente");
        configureButton(R.id.buttonBom, "bom");
        configureButton(R.id.buttonMediano, "mediano");
        configureButton(R.id.buttonRegular, "regular");
        configureButton(R.id.buttonPessimo, "pessimo");
    }

    private void configureButton(int buttonId, String rating) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> submitRating(rating));
    }

    private void submitRating(String rating) {
        // Define o identificador do usuário (autenticado ou visitante)
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "visitante_" + getUniqueDeviceId();

        // Referência para verificar se o usuário já votou
        DatabaseReference userVoteRef = databaseReference.child("avaliacoes").child("votosRegistrados").child(userId);

        userVoteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Se o usuário já votou, exibe uma mensagem informativa
                    Toast.makeText(AvaliacaoActivity.this, "Você já votou!", Toast.LENGTH_SHORT).show();
                } else {
                    // Registra o voto do usuário e armazena o identificador
                    databaseReference.child("avaliacoes").child(rating).child("identificadores").child(userId).setValue(true);
                    userVoteRef.setValue(rating);

                    // Atualiza a contagem de votos para a categoria
                    DatabaseReference contRef = databaseReference.child("avaliacoes").child(rating).child("cont");

                    contRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot contSnapshot) {
                            long currentCount = 0;
                            if (contSnapshot.exists()) {
                                currentCount = contSnapshot.getValue(Long.class);
                            }
                            contRef.setValue(currentCount + 1); // Incrementa a contagem de votos
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Lida com o erro, se necessário
                            Toast.makeText(AvaliacaoActivity.this, "Erro ao registrar voto.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(AvaliacaoActivity.this, "Obrigado por sua avaliação!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AvaliacaoActivity.this, "Erro ao registrar voto. Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUniqueDeviceId() {
        // Obtém um identificador exclusivo para o dispositivo, usado para visitantes
        return sharedPreferences.getString("deviceId", "unknown_device");
    }

}
