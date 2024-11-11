package com.biomaamazonia.appei;

// FaunaFloraAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class FaunaFloraAdapter extends RecyclerView.Adapter<FaunaFloraAdapter.ViewHolder> {

    private List<FaunaFloraItem> faunaFloraList;

    public FaunaFloraAdapter(List<FaunaFloraItem> faunaFloraList) {
        this.faunaFloraList = faunaFloraList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fauna_flora, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FaunaFloraItem item = faunaFloraList.get(position);
        holder.nomeTextView.setText(item.getTitulo());
        holder.descricaoTextView.setText(item.getDescricao());

        // Usar Glide para carregar a imagem a partir da URL
        Glide.with(holder.itemView.getContext())
                .load(item.getImagemUrl())
                .placeholder(R.drawable.img_onca_fauna)  // Imagem placeholder
                .into(holder.imagemView);
    }

    @Override
    public int getItemCount() {
        return faunaFloraList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;
        TextView descricaoTextView;
        ImageView imagemView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.faunaFloraNameTextView);
            descricaoTextView = itemView.findViewById(R.id.faunaFloraDescriptionTextView);
            imagemView = itemView.findViewById(R.id.faunaFloraImageView);
        }
    }

}