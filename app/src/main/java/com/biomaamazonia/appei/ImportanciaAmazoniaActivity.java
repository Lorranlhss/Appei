package com.biomaamazonia.appei;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImportanciaAmazoniaActivity extends AppCompatActivity {

    private TextView cabecalho, titulo, paragr1, paragr2, paragr3, paragr4;
    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importancia_amazonia);

        // Inicialize os TextViews e ImageViews
        cabecalho = findViewById(R.id.cabecalho);
        titulo = findViewById(R.id.titulo);
        paragr1 = findViewById(R.id.paragr1);
        paragr2 = findViewById(R.id.paragr2);
        paragr3 = findViewById(R.id.paragr3);
        paragr4 = findViewById(R.id.paragr4);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        // Acesse o Firebase Realtime Database
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("conheAmazon");

        // Recupera os dados do Firebase
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Recupere os dados do Firebase e atribua aos Views
                String cabecalhoText = dataSnapshot.child("cabecalho").getValue(String.class);
                String tituloText = dataSnapshot.child("titulo").getValue(String.class);
                String paragr1Text = dataSnapshot.child("paragr1").getValue(String.class);
                String paragr2Text = dataSnapshot.child("paragr2").getValue(String.class);
                String paragr3Text = dataSnapshot.child("paragr3").getValue(String.class);
                String paragr4Text = dataSnapshot.child("paragr4").getValue(String.class);

                if (cabecalhoText != null) cabecalho.setText(cabecalhoText);
                if (tituloText != null) titulo.setText(tituloText);
                if (paragr1Text != null) paragr1.setText(paragr1Text);
                if (paragr2Text != null) paragr2.setText(paragr2Text);
                if (paragr3Text != null) paragr3.setText(paragr3Text);
                if (paragr4Text != null) paragr4.setText(paragr4Text);

                carregarImagem(R.drawable.img1_conhe_amaz, imageView1);
                carregarImagem(R.drawable.img2_conhe_amaz, imageView2);
                carregarImagem(R.drawable.img3_conhe_amaz, imageView3);
                carregarImagem(R.drawable.img4_conhe_amaz, imageView4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImportanciaAmazoniaActivity.this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarImagem(int imageResourceId, ImageView imageView) {
        imageView.setImageResource(imageResourceId);
    }
}
