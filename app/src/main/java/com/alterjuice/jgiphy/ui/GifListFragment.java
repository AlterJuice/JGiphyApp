package com.alterjuice.jgiphy.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.alterjuice.jgiphy.MainActivity;
import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.adapters.OnBottomReachedListener;
import com.alterjuice.jgiphy.adapters.OnGifClickedListener;
import com.alterjuice.jgiphy.databinding.GifListFragmentBinding;
import com.alterjuice.jgiphy.viewmodel.GifListViewModel;


public class GifListFragment extends Fragment {
    public static final String TAG = "GifListFragment";
    GifListFragmentBinding binding;
    GifAdapter gifAdapter;
    GifListViewModel model;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_list_fragment, container, false);
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        gifAdapter = new GifAdapter(isPortrait, onBottomReachedListener, onGifClickedListener);
        binding.recyclerGifs.setAdapter(gifAdapter);
        binding.appBar.appBarSearch.setOnQueryTextListener(searchQueryListener);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(GifListViewModel.class);
        model.getGifSearchQuery().observe(getViewLifecycleOwner(), s -> {
            gifAdapter.clearItems();
            Log.d(TAG, "GifAdapter Cleared. Setting Offset to 0");
            model.loadGifs();
        });
        model.getGifs().observe(getViewLifecycleOwner(), gifs -> {
            gifAdapter.update(gifs);
            binding.executePendingBindings();
        });
    }

    @Override
    public void onDestroyView() {
        binding.unbind();
        binding = null;
        gifAdapter = null;
        super.onDestroyView();
    }

    private final OnGifClickedListener onGifClickedListener = (gif, position) -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
            ((MainActivity) requireActivity()).showGif(gif);
        }
    };

    private final OnBottomReachedListener onBottomReachedListener = () -> {
        model.loadMoreGifs();
    };

    private final SearchView.OnQueryTextListener searchQueryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (model.getGifSearchQuery().getValue() != query)
                model.getGifSearchQuery().postValue(query);
            binding.appBar.appBarSearch.clearFocus();
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            // Putting Trend Gifs to adapter if searchQuery is empty
            if (TextUtils.isEmpty(newText))
                if (!TextUtils.isEmpty(model.getGifSearchQuery().getValue()))
                    model.getGifSearchQuery().postValue("");
            return false;
        }
    };
}
