package com.biomaamazonia.appei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("deprecation") // Suprime os alertas de depreciação para a classe inteira
public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verificar se o usuário já está logado no Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Usuário já logado, redirecionar para a MainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // Inicializando os campos e a contagem de visitantes
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);
        TextView entraVisitante = findViewById(R.id.entraVisitante);
        SignInButton googleSignInButton = findViewById(R.id.googleSignInButton);


        // Configurando o Google Sign-In
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());

        // Configuração do Google Sign-In com o método mais recente
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Validando o login do usuário
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (validateCredentials(username, password)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurando o botão de cadastro
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Configuração do texto "Entrar como Visitante" com clique
        SpannableString ss = new SpannableString("Entrar como Visitante");
        ss.setSpan(new CustomClickableSpan(getResources().getColor(R.color.primaryColor)), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        entraVisitante.setText(ss);
        entraVisitante.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // Método para o login com o Google
    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            GoogleSignInAccount account = task.getResult();
                            if (account != null) {
                                firebaseAuthWithGoogle(account.getIdToken());
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha no login com Google", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // Método para autenticação com o Google e salvar o nome do usuário
    private void firebaseAuthWithGoogle(String idToken) {
        FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String displayName = user != null ? user.getDisplayName() : "Usuário";

                        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("userName", displayName);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Autenticação com Google falhou", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Método simplificado para validação das credenciais
    private boolean validateCredentials(String username, String password) {
        // Retorna false imediatamente se os campos estiverem vazios
        return !(username.isEmpty() || password.isEmpty());
    }

    // Referencia a quantidade de visitantes contida no Firebase
    DatabaseReference visitorsRef = FirebaseDatabase.getInstance().getReference().child("visitantes");

    // Classe CustomClickableSpan para manipular o clique no texto "Entrar como Visitante"
    private class CustomClickableSpan extends ClickableSpan {

        private final int color;

        CustomClickableSpan(int color) {
            this.color = color;
        }

        @Override
        public void onClick(@NonNull View textView) {
            // Lógica para incrementação de visitantes no Firebase
            visitorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Long visitorsCountLong = snapshot.child("cont").getValue(Long.class);

                    if (visitorsCountLong != null) {
                        int visitorsCount = visitorsCountLong.intValue(); // Converte a contagem de visitantes em inteiro caso não seja null
                        visitorsCount++; // Incrementa a contagem de visitantes

                        snapshot.getRef().child("cont").setValue(visitorsCount); // Atualiza a contagem de visitantes no banco de dados

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm/d-M-yy");
                            String formattedNow  = now.format(formatter);

                            snapshot.getRef().child("id").child("" + visitorsCount).setValue(formattedNow);
                        } else {
                            snapshot.getRef().child("id").child("" + visitorsCount).setValue("");
                        }

                        // Inicia a MainActivity para o visitante
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("Error", "O valor de 'cont' é nulo ou não é um número válido.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase", "Erro ao acessar dados do Firebase: " + error.getMessage());
                }
            });
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(color); // Define a cor baseada no TextView
            ds.setUnderlineText(false); // Remove o sublinhado
        }
    }
}
