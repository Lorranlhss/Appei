
package com.biomaamazonia.appei;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FaunaFloraActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private ArrayList<FaunaFloraItem> faunaFloraList = new ArrayList<>();
    private FaunaFloraAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fauna_flora);

        // Configurando o RecyclerView e o adapter
        recyclerView = findViewById(R.id.recyclerViewFaunaFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Inicializando o adapter customizado
        adapter = new FaunaFloraAdapter(faunaFloraList);
        recyclerView.setAdapter(adapter);

        // Inicializar o Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("faunaFlora");

        // Recuperar dados do Firebase
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                faunaFloraList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nome = snapshot.child("nome").getValue(String.class);
                    String descricao = snapshot.child("descricao").getValue(String.class);
                    String imageUrl = snapshot.child("imageUrl").getValue(String.class); // ou definir uma imagem padrão

                    // Criar um novo objeto FaunaFloraItem e adicioná-lo à lista
                    FaunaFloraItem item = new FaunaFloraItem(nome, descricao, imageUrl);
                    faunaFloraList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FaunaFloraActivity.this, "Erro ao carregar dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
