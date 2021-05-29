package com.alterjuice.jgiphy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.alterjuice.jgiphy.databinding.ListItemGifBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Image;
import com.alterjuice.jgiphy.utils.ImageUtils;

import java.util.Collection;
import java.util.LinkedList;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> implements BaseAdapter<Gif> {
    LinkedList<Gif> collection = new LinkedList<>();

    public OnBottomReachedListener onBottomReachedListener;
    public OnGifClickedListener onGifClickedListener;
    private ConstraintSet constraintSet = new ConstraintSet();
    RecyclerView recyclerView;
    public Image getImageForPreview(Gif gif){ return gif.images.fixedWidth; }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

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
        if (position == getItemCount()-1){
            if (onBottomReachedListener != null)
                onBottomReachedListener.onBottomReached();
        }
        Gif bindGif = collection.get(position);
        Image bindImage = bindGif.getPreviewImage();
        holder.bind(bindGif);
        ImageUtils.loadGifIntoView(bindImage.url, holder.binding.gifImage);
        // constraintSet.clone(holder.binding.gifConstraint);
        // constraintSet.setDimensionRatio(holder.binding.gifImage.getId(), bindImage.getImageRatio());
        // constraintSet.applyTo(holder.binding.gifConstraint);
    }


    @Override
    public int getItemCount() { return collection.size(); }

    public void addItem(Gif gif) {
        collection.add(gif);
        notifyItemChanged(collection.size()-1);
        // notifyDataSetChanged();
    }

    @Override
    public void updateWithStartPosition(Collection<Gif> items, int position) {
        update(items);
        recyclerView.getLayoutManager().scrollToPosition(position);
    }

    @Override
    public void update(Collection<Gif> items) {
        int sizeBeforeInsert = collection.size();
        collection.addAll(items);
        notifyItemRangeChanged(sizeBeforeInsert, items.size());
    }

    @Override
    public void clearItems() {
        collection.clear();
        notifyDataSetChanged();
    }

    public class GifViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ListItemGifBinding binding;
        Gif gif;

        public GifViewHolder(@NonNull View itemView, ListItemGifBinding binding) {
            super(itemView);
            this.binding = binding;
            itemView.setOnClickListener(this);
        }


        public void bind(Gif gif){
            this.gif = gif;
            binding.setGif(gif);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if (onGifClickedListener != null){
                onGifClickedListener.onGifClicked(gif, collection.indexOf(gif));
            }

        }
    }

}
