package com.alterjuice.jgiphy.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.databinding.ListItemGifBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Image;
import com.alterjuice.jgiphy.utils.ImageUtils;

import java.util.Collection;
import java.util.LinkedList;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> implements BaseAdapter<Gif> {

    private final OnBottomReachedListener onBottomReachedListener;
    private final OnGifClickedListener onGifClickedListener;

    private final ConstraintSet constraintSet = new ConstraintSet();
    final boolean orientationIsPortrait;
    private final RecyclerView.LayoutManager layoutManager;
    private static final int layoutSpanCount = 2;

    RecyclerView recyclerView;
    LinkedList<Gif> collection = new LinkedList<>();

    public GifAdapter(boolean orientationIsPortrait, OnBottomReachedListener onBottomReachedListener,
                      OnGifClickedListener onGifClickedListener){
        this.onGifClickedListener = onGifClickedListener;
        this.onBottomReachedListener = onBottomReachedListener;
        this.orientationIsPortrait = orientationIsPortrait;
        int orientation = StaggeredGridLayoutManager.VERTICAL;
        if (!this.orientationIsPortrait)
            orientation = StaggeredGridLayoutManager.HORIZONTAL;
        layoutManager = new StaggeredGridLayoutManager(layoutSpanCount, orientation);
    }

    Image getPreviewImage(Gif gif){
        if (orientationIsPortrait)
            return gif.images.fixedWidth;
        return gif.images.fixedHeight;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setLayoutManager(layoutManager);
        super.onAttachedToRecyclerView(this.recyclerView);
    }

    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemGifBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_gif, parent, false);
        return new GifViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        if (position == getItemCount()-1){
            if (onBottomReachedListener != null)
                onBottomReachedListener.onBottomReached();
        }
        Gif bindGif = collection.get(position);
        Image bindImage = getPreviewImage(bindGif);
        holder.bind(bindGif);
        ImageUtils.loadGifIntoView(bindImage.url, holder.binding.gifLayoutImage.gifImage);
        constraintSet.clone(holder.binding.gifLayoutImage.gifConstraint);
        constraintSet.setDimensionRatio(holder.binding.gifLayoutImage.gifImage.getId(), bindImage.getImageRatio());
        constraintSet.applyTo(holder.binding.gifLayoutImage.gifConstraint);
    }


    @Override
    public int getItemCount() { return collection.size(); }

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
