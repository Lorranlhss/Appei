package com.biomaamazonia.appei;

import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadUserName();

        View headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.buttonMapa).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapaActivity.class)));
        headerView.findViewById(R.id.buttonFaunaFlora).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FaunaFloraActivity.class)));
        headerView.findViewById(R.id.buttonQuiz).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, QuizActivity.class)));
        headerView.findViewById(R.id.buttonImportanciaAmazonia).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ImportanciaAmazoniaActivity.class)));
        headerView.findViewById(R.id.buttonSustentabilidade).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SustentabilidadeActivity.class)));

        Button buttonAvaliacao = findViewById(R.id.buttonAvaliacao);
        buttonAvaliacao.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.255.10:3000/"));
            startActivity(browserIntent);
        });

        // Configura o clique no item "Sobre o Criador" para abrir a nova Activity
        navigationView.getMenu().findItem(R.id.menu_about_creator).setOnMenuItemClickListener(item -> {
            startActivity(new Intent(MainActivity.this, AboutCreatorsActivity.class));
            return true;
        });

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(item -> {
            signOut();
            return true;
        });

        headerView.findViewById(R.id.buttonAvaliacao).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AvaliacaoActivity.class))
        );

        ViewPager2 imageCarousel = findViewById(R.id.imageCarousel);
        List<Integer> images = Arrays.asList(
                R.drawable.imagen1car, R.drawable.imagen2car, R.drawable.imagen3car,
                R.drawable.imagen4car, R.drawable.imagen5car, R.drawable.imagen6car,
                R.drawable.imagen7car, R.drawable.imagen8car
        );

        ImageCarouselAdapter adapter = new ImageCarouselAdapter(this, images);
        imageCarousel.setAdapter(adapter);

        imageCarousel.postDelayed(new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                if (currentItem >= adapter.getItemCount()) {
                    currentItem = 0;
                }
                imageCarousel.setCurrentItem(currentItem++, true);
                //imageCarousel.postDelayed(this, 3000);
            }
        }, 3000);
    }



    private void loadUserName() {
        String savedUserName = sharedPreferences.getString("userName", null);

        if (savedUserName != null) {
            updateWelcomeMessage(savedUserName);
        } else {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String userNameFromDb = dataSnapshot.child("name").getValue(String.class);
                        if (userNameFromDb != null && !userNameFromDb.isEmpty()) {
                            updateWelcomeMessage(userNameFromDb);
                            saveUserNameToPreferences(userNameFromDb);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle possible errors here
                    }
                });
            }
        }
    }

    private void saveUserNameToPreferences(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.apply();
    }

    private void updateWelcomeMessage(String userName) {
        runOnUiThread(() -> {
            View headerView = navigationView.getHeaderView(0);
            TextView welcomeText = headerView.findViewById(R.id.welcomeText);
            welcomeText.setText("Bem-vindo, " + userName);
        });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserName();
    }
}