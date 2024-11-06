package com.biomaamazonia.appei;

import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private int contV;
    private static final String PREFS_NAME = "VisitorPrefs";
    private static final String VISITOR_COUNT_KEY = "visitor_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializando os campos e verificando se eles foram encontrados
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        TextView entraVisitante = findViewById(R.id.entraVisitante);

        // Carregando a contagem de visitantes salva
        contV = loadVisitorCount(); // Inicializa contV com a contagem salva

        // Obtendo a cor do TextView e aplicando ao texto clicável
        int color = entraVisitante.getCurrentTextColor();

        // Configurando o texto "Entrar como Visitante" com clique
        SpannableString ss = new SpannableString("Entrar como Visitante");
        ss.setSpan(new CustomClickableSpan(color), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        entraVisitante.setText(ss);
        entraVisitante.setMovementMethod(LinkMovementMethod.getInstance());

        if (usernameEditText == null || passwordEditText == null || loginButton == null) {
            Log.e("LoginActivity", "Erro ao encontrar um ou mais elementos de UI");
            Toast.makeText(this, "Erro ao carregar a interface de login", Toast.LENGTH_SHORT).show();
            return;
        }

        // Usando lambda para o OnClickListener
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (validateCredentials(username, password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomClickableSpan extends ClickableSpan {
        private final int color;

        CustomClickableSpan(int color) {
            this.color = color;
        }

        @Override
        public void onClick(@NonNull View textView) {
            contV++;
            saveVisitorCount(contV); // Salva a nova contagem

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Visitantes: " + contV, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(color); // Define a cor baseada no TextView
            ds.setUnderlineText(false); // Remove o sublinhado
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Validação simples para teste
        return username.equals("teste") && password.equals("teste");
    }

    private void saveVisitorCount(int count) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(VISITOR_COUNT_KEY, count);
        editor.apply();
    }

    private int loadVisitorCount() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(VISITOR_COUNT_KEY, 0); // Retorna 0 se não houver contagem salva
    }
}
