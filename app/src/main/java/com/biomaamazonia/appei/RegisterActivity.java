package com.biomaamazonia.appei;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private FirebaseAuth mAuth; // Instância do FirebaseAuth
    private DatabaseReference mDatabase; // Instância do Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialização do FirebaseAuth e FirebaseDatabase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        // Inicialização dos EditText
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        // Configuração do clique do botão de registro
        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (validateInputs(name, email, password, confirmPassword)) {
                // Cadastrar o usuário no Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // O cadastro foi bem-sucedido
                                String userId = mAuth.getCurrentUser().getUid();  // Obter o UID do usuário

                                // Criar um objeto de usuário
                                User user = new User(name, email, password);

                                // Armazenar os dados no Realtime Database
                                mDatabase.child(userId).setValue(user)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                // Dados armazenados com sucesso
                                                Log.d("RegisterActivity", "Nome armazenado no Firebase: " + user.name); // Verificação do nome no Firebase

                                                // Salvar o nome do usuário no SharedPreferences
                                                SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("userName", user.name);
                                                editor.apply();

                                                // Verificação do nome no SharedPreferences
                                                String savedName = sharedPreferences.getString("userName", "Nome não encontrado");
                                                Log.d("RegisterActivity", "Nome armazenado no SharedPreferences: " + savedName);

                                                Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                                finish(); // Fecha a Activity e retorna para a tela de login
                                            } else {
                                                // Se o armazenamento falhar
                                                Toast.makeText(RegisterActivity.this, "Erro ao salvar os dados no banco. Tente novamente.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
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

    // Classe interna para representar os dados do usuário
    public static class User {
        public String name;
        public String email;
        public String password;

        public User() {
            // Construtor vazio necessário para o Firebase
        }

        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }
}
