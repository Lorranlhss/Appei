package com.biomaamazonia.appei;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FaunaFloraActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView listView;
    private ArrayList<String> faunaFloraList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fauna_flora);

        listView = findViewById(R.id.listViewFaunaFlora);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faunaFloraList);
        listView.setAdapter(adapter);

        // Inicializar o Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("faunaFlora");

        // Recuperar dados do Firebase
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                faunaFloraList.clear();  // Limpar a lista para evitar duplicação
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nome = snapshot.child("nome").getValue(String.class);
                    String descricao = snapshot.child("descricao").getValue(String.class);
                    faunaFloraList.add(nome + ": " + descricao);
                }

                // Notificar o adapter que os dados mudaram
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FaunaFloraActivity.this, "Erro ao carregar dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
