package com.alterjuice.jgiphy.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.databinding.ListItemGifBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.model.giphy.Image;
import com.alterjuice.jgiphy.utils.ImageUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class GifAdapter extends ListAdapter<Gif, GifAdapter.GifViewHolder> {

    private static final int LAYOUT_SPAN_COUNT = 2;
    private final OnBoundsReachedListener onBoundsReachedListener;
    private final Callback<Gif> onGifClickedListener;
    private final ConstraintSet constraintSet = new ConstraintSet();
    private final boolean orientationIsPortrait;
    private final RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;


    public GifAdapter(boolean orientationIsPortrait, OnBoundsReachedListener onBoundsReachedListener,
                      Callback<Gif> onGifClickedListener){
        super(gifDifferenceCallback);

        this.onGifClickedListener = onGifClickedListener;
        this.onBoundsReachedListener = onBoundsReachedListener;
        this.orientationIsPortrait = orientationIsPortrait;
        int orientation = StaggeredGridLayoutManager.VERTICAL;
        if (!this.orientationIsPortrait)
            orientation = StaggeredGridLayoutManager.HORIZONTAL;
        layoutManager = new StaggeredGridLayoutManager(LAYOUT_SPAN_COUNT, orientation);
    }


    static DiffUtil.ItemCallback<Gif> gifDifferenceCallback = new DiffUtil.ItemCallback<Gif>() {
        @Override
        public boolean areItemsTheSame(@NonNull Gif oldItem, @NonNull Gif newItem) {
            return oldItem.uniqueID.equals(newItem.uniqueID);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Gif oldItem, @NonNull Gif newItem) {
            return oldItem.uniqueID.equals(newItem.uniqueID) &&
                    oldItem.url.equals(newItem.url);
        }
    };


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
        ListItemGifBinding binding = ListItemGifBinding.inflate(inflater, parent, false);
        return new GifViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        if (onBoundsReachedListener != null){
            if (position == 1){
                Log.d("Top Reached", "Pos: "+position +"; Count: "+getItemCount());
                onBoundsReachedListener.onTopReached();
            }
            if (position == getItemCount()-1){
                Log.d("Bottom Reached", "Pos: "+position +"; Count: "+getItemCount());
                onBoundsReachedListener.onBottomReached();
            }
        }

        Gif bindGif = getItem(position);
        holder.bind(bindGif, onGifClickedListener);
    }

    public void update(List<Gif> items) {
        submitList(items);
    }


    public class GifViewHolder extends RecyclerView.ViewHolder {
        ListItemGifBinding binding;

        public GifViewHolder(ListItemGifBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Gif gif, Callback<Gif> gifClickedListener){
            binding.getRoot().setOnClickListener(v -> gifClickedListener.run(gif));
            Image bindImage = gif.getImageFor(orientationIsPortrait);
            ImageUtils.loadGifIntoView(bindImage.url, binding.gifLayoutImage.gifImage);
            constraintSet.clone(binding.gifLayoutImage.gifConstraint);
            constraintSet.setDimensionRatio(binding.gifLayoutImage.gifImage.getId(), bindImage.getImageRatio());
            constraintSet.applyTo(binding.gifLayoutImage.gifConstraint);
        }
    }
}
