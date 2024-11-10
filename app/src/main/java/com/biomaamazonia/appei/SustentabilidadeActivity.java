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
import com.bumptech.glide.Glide;

public class SustentabilidadeActivity extends AppCompatActivity {

    private TextView titulo, textoprincipal, subtitulo1, textview1, subtitulo2, textview2, subtitulo3, textview3, subtitulo4, textview4, subtitulo5, textview5;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sustentabilidade);

        // Inicialize os TextViews e ImageViews
        titulo = findViewById(R.id.titulo);
        textoprincipal = findViewById(R.id.textoprincipal);
        subtitulo1 = findViewById(R.id.subtitulo1);
        textview1 = findViewById(R.id.textview1);
        subtitulo2 = findViewById(R.id.subtitulo2);
        textview2 = findViewById(R.id.textView2);
        subtitulo3 = findViewById(R.id.subtitulo3);
        textview3 = findViewById(R.id.textview3);
        subtitulo4 = findViewById(R.id.subtitulo4);
        textview4 = findViewById(R.id.textview4);
        subtitulo5 = findViewById(R.id.subtitulo5);
        textview5 = findViewById(R.id.textview5);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        // Acesse o Firebase Realtime Database
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("sustentabilidade");

        // Recupera os dados do Firebase
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Recupere os dados do Firebase e atribua aos Views
                String tituloText = dataSnapshot.child("titulo").getValue(String.class);
                String textoprincipalText = dataSnapshot.child("textoprincipal").getValue(String.class);
                String subtitulo1Text = dataSnapshot.child("subtitulo1").getValue(String.class);
                String textview1Text = dataSnapshot.child("textview1").getValue(String.class);
                String subtitulo2Text = dataSnapshot.child("subtitulo2").getValue(String.class);
                String textview2Text = dataSnapshot.child("textview2").getValue(String.class);
                String subtitulo3Text = dataSnapshot.child("subtitulo3").getValue(String.class);
                String textview3Text = dataSnapshot.child("textview3").getValue(String.class);
                String subtitulo4Text = dataSnapshot.child("subtitulo4").getValue(String.class);
                String textview4Text = dataSnapshot.child("textview4").getValue(String.class);
                String subtitulo5Text = dataSnapshot.child("subtitulo5").getValue(String.class);
                String textview5Text = dataSnapshot.child("textview5").getValue(String.class);

                // Verifique se os dados não são nulos antes de atribuir aos Views
                if (tituloText != null) titulo.setText(tituloText);
                if (textoprincipalText != null) textoprincipal.setText(textoprincipalText);
                if (subtitulo1Text != null) subtitulo1.setText(subtitulo1Text);
                if (textview1Text != null) textview1.setText(textview1Text);
                if (subtitulo2Text != null) subtitulo2.setText(subtitulo2Text);
                if (textview2Text != null) textview2.setText(textview2Text);
                if (subtitulo3Text != null) subtitulo3.setText(subtitulo3Text);
                if (textview3Text != null) textview3.setText(textview3Text);
                if (subtitulo4Text != null) subtitulo4.setText(subtitulo4Text);
                if (textview4Text != null) textview4.setText(textview4Text);
                if (subtitulo5Text != null) subtitulo5.setText(subtitulo5Text);
                if (textview5Text != null) textview5.setText(textview5Text);

                // Carregue as imagens a partir da pasta drawable
                carregarImagem(R.drawable.img1_susten, imageView1);
                carregarImagem(R.drawable.img2_susten, imageView2);
                carregarImagem(R.drawable.img3_susten, imageView3);
                carregarImagem(R.drawable.img4_susten, imageView4);
                carregarImagem(R.drawable.img5_susten, imageView5);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Em caso de erro ao acessar os dados
                Toast.makeText(SustentabilidadeActivity.this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarImagem(int imageResourceId, ImageView imageView) {
        // Use o Glide para carregar a imagem diretamente da pasta drawable
        Glide.with(SustentabilidadeActivity.this)
                .load(imageResourceId)
                .fitCenter() // Para garantir que a imagem inteira seja exibida
                .into(imageView);
    }
}
