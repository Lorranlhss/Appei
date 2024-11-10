
package com.biomaamazonia.appei;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FaunaFloraAdapter extends RecyclerView.Adapter<FaunaFloraAdapter.FaunaFloraViewHolder> {

    private ArrayList<FaunaFloraItem> faunaFloraList;

    public FaunaFloraAdapter(ArrayList<FaunaFloraItem> faunaFloraList) {
        this.faunaFloraList = faunaFloraList;
    }

    @NonNull
    @Override
    public FaunaFloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fauna_flora, parent, false);
        return new FaunaFloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaunaFloraViewHolder holder, int position) {
        FaunaFloraItem item = faunaFloraList.get(position);
        holder.faunaFloraNameTextView.setText(item.getName());
        holder.faunaFloraDescriptionTextView.setText(item.getDescription());
        // Configurar imagem se houver uma URL ou Drawable
        // Exemplo: Glide.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.faunaFloraImageView);
    }

    @Override
    public int getItemCount() {
        return faunaFloraList.size();
    }

    public static class FaunaFloraViewHolder extends RecyclerView.ViewHolder {
        TextView faunaFloraNameTextView;
        TextView faunaFloraDescriptionTextView;
        ImageView faunaFloraImageView;


        public FaunaFloraViewHolder(@NonNull View itemView) {
            super(itemView);
            faunaFloraNameTextView = itemView.findViewById(R.id.faunaFloraNameTextView);
            faunaFloraDescriptionTextView = itemView.findViewById(R.id.faunaFloraDescriptionTextView);
            faunaFloraImageView = itemView.findViewById(R.id.faunaFloraImageView);
        }
    }
}
