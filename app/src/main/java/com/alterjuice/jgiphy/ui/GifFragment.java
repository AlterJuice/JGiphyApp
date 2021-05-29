package com.alterjuice.jgiphy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.databinding.GifFragmentBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;

public class GifFragment extends Fragment {
    private static final String KEY_GIF_ID = "id";
    private static final String KEY_GIF_ORIGINAL_URL = "original_url";
    private static final String KEY_GIF = "arg:gif";

    private GifFragmentBinding binding;
    private Gif gif;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_fragment, container, false);
        if (getArguments() != null) {
            gif = (Gif) getArguments().getSerializable("gif");
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        binding.unbind();
        binding = null;
        super.onDestroyView();
    }
    public static GifFragment newInstance(Gif gif){
        GifFragment fragment = new GifFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_GIF, gif);
        fragment.setArguments(args);
        return fragment;
    }
}
