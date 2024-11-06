package com.biomaamazonia.appei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageCarouselAdapter extends RecyclerView.Adapter<ImageCarouselAdapter.CarouselViewHolder> {

    private final Context context;
    private final List<Integer> images;
    private final List<String> descriptions;
    private final List<String> credits;

    public ImageCarouselAdapter(Context context, List<Integer> images, List<String> descriptions, List<String> credits) {
        this.context = context;
        this.images = images;
        this.descriptions = descriptions;
        this.credits = credits;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_item, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position));
        holder.descriptionTextView.setText(descriptions.get(position));
        holder.creditTextView.setText(credits.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView descriptionTextView;
        TextView creditTextView;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.carouselImage);
            descriptionTextView = itemView.findViewById(R.id.carouselDescription);
            creditTextView = itemView.findViewById(R.id.carouselCredit);
        }
    }
}
