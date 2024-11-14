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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String PREF_VISITOR_REGISTERED = "visitorRegistered";
    private static final String PREF_USER_NAME = "userName";

    private EditText usernameEditText, passwordEditText;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference visitorsRef = FirebaseDatabase.getInstance().getReference().child("visitantes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verificar se o usuário já está logado
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            navigateToMainActivity();
        }

        // Inicializando as views
        initializeViews();

        // Configurando Google Sign-In
        configureGoogleSignIn();

        // Configuração do login
        configureLoginButton();

        // Configuração do cadastro
        configureSignUpButton();

        // Configuração do clique no "Entrar como Visitante"
        configureVisitorLogin();
    }

    private void initializeViews() {
        usernameEditText = findViewById(R.id.emailField);
        passwordEditText = findViewById(R.id.passwordField);
    }

    private void configureGoogleSignIn() {
        MaterialButton googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void configureLoginButton() {
        MaterialButton loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> validateUser());
    }

    private void configureSignUpButton() {
        MaterialButton signUpButton = findViewById(R.id.registerButton);
        signUpButton.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void configureVisitorLogin() {
        TextView entraVisitante = findViewById(R.id.entraVisitante);
        SpannableString ss = new SpannableString("Entrar como Visitante");
        ss.setSpan(new CustomClickableSpan(getResources().getColor(R.color.primaryColor)), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        entraVisitante.setText(ss);
        entraVisitante.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void validateUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showToast("Preencha todos os campos");
            return;
        }

        checkUserCredentials(username, password);
    }

    private void checkUserCredentials(String username, String password) {
        FirebaseDatabase.getInstance().getReference("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean isValidUser = false;
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String dbUsername = userSnapshot.child("email").getValue(String.class);
                            String dbPassword = userSnapshot.child("password").getValue(String.class);
                            if (dbUsername != null && dbPassword != null && dbUsername.equals(username) && dbPassword.equals(password)) {
                                isValidUser = true;
                                String name = userSnapshot.child("name").getValue(String.class);
                                if (name != null) {
                                    Log.d("LoginActivity", "Nome do usuário recuperado: " + name);
                                    saveUserInSharedPreferences(name);  // Salva o nome no SharedPreferences
                                }
                                break;
                            }
                        }

                        if (isValidUser) {
                            navigateToMainActivity();
                        } else {
                            showToast("Credenciais inválidas");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        showToast("Erro ao acessar o banco de dados");
                    }
                });
    }

    private void saveUserInSharedPreferences(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(PREF_USER_NAME, username).apply();
        Log.d("LoginActivity", "Nome armazenado no SharedPreferences: " + username);
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

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
                            firebaseAuthWithGoogle(account);
                        } else {
                            Log.w("LoginActivity", "signInWithCredential:failure", task.getException());
                            showToast("Falha no login");
                        }
                    });
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(acct.getIdToken(), null))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Extrair o nome do usuário a partir do FirebaseUser
            String name = user.getDisplayName();

            if (name != null && !name.isEmpty()) {
                // Salvar o nome no SharedPreferences
                saveUserInSharedPreferences(name);
                Log.d("LoginActivity", "Nome do usuário Google armazenado: " + name);
            } else {
                Log.w("LoginActivity", "Nome do usuário Google não está disponível.");
                // Opcional: Definir um nome padrão ou lidar com a ausência do nome
                saveUserInSharedPreferences("Usuário Google");
            }

            navigateToMainActivity();
        } else {
            showToast("Erro no login com Google");
        }
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private class CustomClickableSpan extends ClickableSpan {
        private final int color;

        public CustomClickableSpan(int color) {
            this.color = color;
        }

        @Override
        public void onClick(@NonNull View widget) {
            handleVisitorLogin();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(color);
            ds.setUnderlineText(false);
        }
    }

    private void handleVisitorLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(PREF_USER_NAME, "Visitante").apply(); // Salva "Visitante" como nome de usuário

        boolean visitorRegistered = sharedPreferences.getBoolean(PREF_VISITOR_REGISTERED, false);

        if (!visitorRegistered) {
            // Incrementar contagem apenas para novos visitantes
            visitorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer visitorCount = snapshot.exists() ? snapshot.child("cont").getValue(Integer.class) : null;
                    if (visitorCount == null) {
                        visitorCount = 0;
                    }
                    visitorsRef.child("cont").setValue(visitorCount + 1);

                    // Gerar ID único e salvar
                    String uniqueID = UUID.randomUUID().toString();
                    visitorsRef.child("dispositivos").child(uniqueID).setValue(true);

                    // Registrar data e hora do primeiro acesso
                    String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    visitorsRef.child("dispositivos").child(uniqueID).child("primeiroAcesso").setValue(currentDateTime);
                    visitorsRef.child("dispositivos").child(uniqueID).child("ultimoAcesso").setValue(currentDateTime);

                    // Marcar o dispositivo como registrado
                    sharedPreferences.edit()
                            .putBoolean(PREF_VISITOR_REGISTERED, true)
                            .putString("visitorID", uniqueID)
                            .apply();

                    navigateToMainActivity();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    showToast("Erro ao registrar visitante");
                }
            });
        } else {
            // Atualizar apenas a data e hora do último acesso sem incrementar a contagem
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            String visitorID = sharedPreferences.getString("visitorID", "");

            // Verifica se há um visitorID salvo
            if (!visitorID.isEmpty()) {
                visitorsRef.child("dispositivos").child(visitorID).child("ultimoAcesso").setValue(currentDateTime);
            }

            navigateToMainActivity();
        }
    }

}