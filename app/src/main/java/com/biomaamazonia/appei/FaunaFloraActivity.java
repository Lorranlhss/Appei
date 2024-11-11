package com.biomaamazonia.appei;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FaunaFloraActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FaunaFloraAdapter adapter;
    private ArrayList<FaunaFloraItem> faunaFloraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fauna_flora);

        recyclerView = findViewById(R.id.recyclerViewFaunaFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        faunaFloraList = new ArrayList<>();
        adapter = new FaunaFloraAdapter(faunaFloraList);
        recyclerView.setAdapter(adapter);

        // Adicionando 20 cards com dados fictícios
        adicionarItems();
    }

    private void adicionarItems() {
        for (int i = 1; i <= 20; i++) {
            String titulo = "Espécie " + i;
            String descricao = "Descrição breve para a espécie " + i + ".";
            String imagemUrl = "https://via.placeholder.com/200";  // URL de exemplo, troque pelo real, se necessário

            FaunaFloraItem item = new FaunaFloraItem(titulo, descricao, imagemUrl);
            faunaFloraList.add(item);
        }
        adapter.notifyDataSetChanged();  // Atualiza o adaptador para exibir os novos itens
    }
}
