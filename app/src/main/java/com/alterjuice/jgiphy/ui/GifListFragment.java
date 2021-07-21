package com.alterjuice.jgiphy.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alterjuice.jgiphy.adapters.GifAdapter;
import com.alterjuice.jgiphy.adapters.OnBoundsReachedListener;
import com.alterjuice.jgiphy.databinding.GifListFragmentBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.ui.base.BaseFragment;
import com.alterjuice.jgiphy.viewmodel.GifListViewModel;


public class GifListFragment extends BaseFragment {
    public static final String TAG = "GifListFragment";
    private GifListFragmentBinding binding;
    private GifAdapter gifAdapter;
    private GifListViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GifListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.appBar.appBarSearch.setOnQueryTextListener(searchQueryListener);
        initViewModels();
        attachObservers();
        prepareRecyclerGifs();
        vm.loadWithClear();
    }

    @Override
    protected void initViewModels() {
        vm = new ViewModelProvider(requireActivity()).get(GifListViewModel.class);
    }

    @Override
    protected void closeFragment() {
        onBackPressed();
    }

    @Override
    protected void attachObservers() {
        vm.gifSearchQuery.observe(getViewLifecycleOwner(), s -> vm.loadMoreGifs());
        vm.gifs.observe(getViewLifecycleOwner(), gifs ->{
            gifAdapter.submitList(gifs);
            gifAdapter.notifyDataSetChanged();
        });
    }

    private void prepareRecyclerGifs(){
        gifAdapter = new GifAdapter(orientationIsPortrait(), onBoundsReachedListener, this::showGif);
        binding.recyclerGifs.setAdapter(gifAdapter);
        int orientation = StaggeredGridLayoutManager.VERTICAL;
        if (!orientationIsPortrait())
            orientation = StaggeredGridLayoutManager.HORIZONTAL;
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(GifAdapter.LAYOUT_SPAN_COUNT, orientation);
        binding.recyclerGifs.setLayoutManager(layoutManager);
    }

    private void showGif(Gif gif) {
        addFragment(GifFragment.newInstance(gif), "Gif:" + gif.uniqueID);
        turnOnOffHomeButton(true);
    }

    private final OnBoundsReachedListener onBoundsReachedListener = new OnBoundsReachedListener() {
        @Override
        public void onTopReached() { }

        @Override
        public void onBottomReached() {
            vm.loadMoreGifs();
        }
    };

    private final SearchView.OnQueryTextListener searchQueryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (!vm.gifSearchQuery.getValue().equals(query))
                vm.setSearchQuery(query);
            binding.appBar.appBarSearch.clearFocus();
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            // Putting Trend Gifs to adapter if searchQuery is empty
            if (TextUtils.isEmpty(newText))
                if (!TextUtils.isEmpty(vm.gifSearchQuery.getValue()))
                    vm.setSearchQuery("");
            return false;
        }
    };


}
