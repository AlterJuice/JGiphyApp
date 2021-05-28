package com.alterjuice.jgiphy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alterjuice.jgiphy.databinding.ListItemGifBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.utils.ImageUtils;

import java.util.Collection;
import java.util.LinkedList;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> {
    LinkedList<Gif> collection = new LinkedList<>();



    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext())
        // .inflate(R.layout.list_item_gif, parent, false);
        ListItemGifBinding binding = ListItemGifBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GifViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        holder.bind(collection.get(position));

    }

    @Override
    public int getItemCount() {
        return collection.size();
    }
    public void addItem(Gif gif) {
        collection.add(gif);
        notifyItemChanged(collection.size()-1);
        // notifyDataSetChanged();
    }

    public void clearItems() {
        collection.clear();
        notifyDataSetChanged();
    }

    public void setItems(Collection<Gif> gifs) {
        this.collection = new LinkedList<>(gifs);
        notifyDataSetChanged();
    }

    public class GifViewHolder extends RecyclerView.ViewHolder{
        ListItemGifBinding binding;

        public GifViewHolder(@NonNull View itemView, ListItemGifBinding binding) {
            super(itemView);
            this.binding = binding;
        }
        public void bind(Gif gif){
            binding.setGif(gif);
            binding.executePendingBindings();
            ImageUtils.loadGifIntoView(gif.images.fixedHeight.url, binding.gifImage);
        }
    }

}
