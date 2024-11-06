package com.biomaamazonia.appei;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar a Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obter a ActionBar e habilitar o botão de navegação
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // Configurar o menu deslizante
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configurar saudação e versão
        View headerView = navigationView.getHeaderView(0);
        TextView welcomeText = headerView.findViewById(R.id.welcomeText);
        String userName = "[Nome do Usuário]"; // inserir nome de usuario armazenado no banco de dados
        welcomeText.setText(getString(R.string.welcome_message, userName));

        // Definir ações dos botões
        headerView.findViewById(R.id.buttonMapa).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapaActivity.class)));
        headerView.findViewById(R.id.buttonFaunaFlora).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FaunaFloraActivity.class)));
        headerView.findViewById(R.id.buttonQuiz).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, QuizActivity.class)));
        headerView.findViewById(R.id.buttonImportanciaAmazonia).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ImportanciaAmazoniaActivity.class)));
        headerView.findViewById(R.id.buttonSustentabilidade).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SustentabilidadeActivity.class)));

        // Botão de logout
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(item -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Encerra a MainActivity para que o usuário não possa voltar após logout
            return true;
        });

        // Configurar o carrossel de imagens
        ViewPager2 imageCarousel = findViewById(R.id.imageCarousel);
        List<Integer> images = Arrays.asList(R.drawable.imagen1, R.drawable.imagen2, R.drawable.imagen3); // Substitua com suas imagens
        List<String> descriptions = Arrays.asList("Arara-Azul", "Onça Pintada", "Guaraná fruta amazônica");
        List<String> credits = Arrays.asList("Foto: João Silva", "Foto: Maria Oliveira", "Foto: Pedro Santos");

        ImageCarouselAdapter adapter = new ImageCarouselAdapter(this, images, descriptions, credits);
        imageCarousel.setAdapter(adapter);

        // Transição automática de imagens a cada 3 segundos
        imageCarousel.postDelayed(new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                if (currentItem >= adapter.getItemCount()) {
                    currentItem = 0;
                }
                imageCarousel.setCurrentItem(currentItem++, true);
                imageCarousel.postDelayed(this, 3000); // 3 segundos
            }
        }, 3000);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
