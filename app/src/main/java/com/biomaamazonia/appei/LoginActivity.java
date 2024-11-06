package com.biomaamazonia.appei;

import android.content.Intent;
import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializando os campos e verificando se eles foram encontrados
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        TextView textView = findViewById(R.id.textView);

        // Configurando o texto "Entrar como Visitante" com clique
        SpannableString ss = new SpannableString("Entrar como Visitante");
        ss.setSpan(new CustomClickableSpan(), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

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

    private boolean validateCredentials(String username, String password) {
        // Validação simples para teste
        return username.equals("teste") && password.equals("teste");
    }

    // Classe CustomClickableSpan para definir a ação ao clicar no texto "Entrar como Visitante"
    private class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(@NonNull View widget) {
            // Simulando um login automático para visitantes
            String username = "teste"; // Username padrão
            String password = "teste"; // Senha padrão

            if (validateCredentials(username, password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Erro ao entrar como visitante", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#866513")); // Define a cor desejada
            ds.setUnderlineText(false); // Remove o sublinhado
            ds.bgColor = Color.TRANSPARENT; // Remove a cor de fundo ao clicar
        }
    }
}
