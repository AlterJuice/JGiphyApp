package com.alterjuice.jgiphy.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.databinding.GifFragmentBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.utils.ImageUtils;

public class GifFragment extends Fragment {
    private static final String KEY_GIF = "arg:gif";

    private GifFragmentBinding binding;
    private Gif gif;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_fragment, container, false);
        binding.getRoot().setClickable(true);
        if (getArguments() != null) {
            gif = (Gif) getArguments().getSerializable(KEY_GIF);
            if (gif != null)
                ImageUtils.loadGifIntoView(gif.getFullImage().url, binding.gifImage);
            binding.gifUrl.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(v.getContext().CLIPBOARD_SERVICE);
                if (clipboard != null) {
                    ClipData clip = ClipData.newPlainText("Gif url", gif.url);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(v.getContext(), "Url copied!", Toast.LENGTH_SHORT).show();
                }
            });
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
