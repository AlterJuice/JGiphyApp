package com.alterjuice.jgiphy.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.adapters.OnBoundsReachedListener;
import com.alterjuice.jgiphy.databinding.GifListFragmentBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.viewmodel.GifListViewModel;


public class GifListFragment extends Fragment {
    public static final String TAG = "GifListFragment";
    GifListFragmentBinding binding;
    GifAdapter gifAdapter;
    GifListViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GifListFragmentBinding.inflate(inflater, container, false);
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        gifAdapter = new GifAdapter(isPortrait, onBoundsReachedListener, this::showGif);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerGifs.setAdapter(gifAdapter);
        binding.appBar.appBarSearch.setOnQueryTextListener(searchQueryListener);

        model = new ViewModelProvider(this).get(GifListViewModel.class);
        model.getGifSearchQuery().observe(getViewLifecycleOwner(), s -> {
            model.loadMoreGifs();
        });
        model.gifs.observe(getViewLifecycleOwner(), gifs -> gifAdapter.update(gifs));
        model.loadWithClear();

    }


    public void showGif(Gif gif) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                .add(R.id.mainFragment, GifFragment.newInstance(gif), "Gif:" + gif.uniqueID)
                .addToBackStack("Gif:" + gif.uniqueID)
                .commit();
        ((MainActivity) requireActivity()).turnOnOffHomeButton(true);
    }

    private final OnBoundsReachedListener onBoundsReachedListener = new OnBoundsReachedListener() {
        @Override
        public void onTopReached() { }

        @Override
        public void onBottomReached() {
            model.loadMoreGifs();
        }
    };

    private final SearchView.OnQueryTextListener searchQueryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (model.getGifSearchQuery().getValue() != query)
                model.getGifSearchQuery().setValue(query);
            binding.appBar.appBarSearch.clearFocus();
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            // Putting Trend Gifs to adapter if searchQuery is empty
            if (TextUtils.isEmpty(newText))
                if (!TextUtils.isEmpty(model.getGifSearchQuery().getValue()))
                    model.getGifSearchQuery().setValue("");
            return false;
        }
    };
}
