package com.biomaamazonia.appei;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private FirebaseAuth mAuth; // Instância do FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialização do FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (validateInputs(name, email, password, confirmPassword)) {
                // Cadastrar o usuário no Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // O cadastro foi bem-sucedido
                                Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                finish(); // Fecha a Activity e retorna para a tela de login
                            } else {
                                // Se o cadastro falhar
                                Toast.makeText(RegisterActivity.this, "Erro ao cadastrar usuário. Tente novamente.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Nome é obrigatório");
            return false;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Email inválido");
            return false;
        }
        if (!isPasswordValid(password)) {
            passwordEditText.setError("A senha deve conter letras maiúsculas, números e caracteres especiais (#, !, etc.)");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("As senhas não coincidem");
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        // Regra de validação da senha
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }
}
