package com.alterjuice.jgiphy.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.databinding.GifFragmentBinding;
import com.alterjuice.jgiphy.model.giphy.Gif;
import com.alterjuice.jgiphy.viewmodel.GifViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class GifFragment extends Fragment {
    private static final String KEY_GIF = "arg:gif";

    private GifFragmentBinding binding;

    String TAG = "GifFragment";
    private RequestManager grm;
    private GifViewModel model;


    public static GifFragment newInstance(Gif gif) {
        GifFragment fragment = new GifFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_GIF, gif);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_fragment, container, false);
        binding.getRoot().setClickable(true);
        if (getArguments() == null) {
            Log.d(TAG, "FragmentArguments null");
            return binding.getRoot();
        }

        Gif gif = (Gif) getArguments().getSerializable(KEY_GIF);

        if (gif == null) {
            Log.d(TAG, "GifArgument is null");
            return binding.getRoot();
        }
        model = new GifViewModel(gif);
        grm = Glide.with(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        model.getGif().observe(getViewLifecycleOwner(), gifObserver);
    }

    private final Observer<Gif> gifObserver = new Observer<Gif>() {
        @Override
        public void onChanged(Gif gif) {
            binding.gifPerson.setVisibility(gif.hasUser() ? View.VISIBLE : View.GONE);
            ConstraintSet set = new ConstraintSet();
            set.clone(binding.gifLayoutImage.gifConstraint);
            set.setDimensionRatio(binding.gifLayoutImage.gifImage.getId(), gif.getFullImage().getImageRatio());
            set.applyTo(binding.gifLayoutImage.gifConstraint);

            grm.load(gif.getFullImage().url)
                    .thumbnail(grm.load(gif.images.originalStill.url))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            binding.gifProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .fitCenter()
                    .skipMemoryCache(true)
                    .into(binding.gifLayoutImage.gifImage);


            binding.gifUrl.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard != null) {
                    ClipData clip = ClipData.newPlainText("Gif " + gif.title + ": ", gif.url);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(v.getContext(), "Url copied!", Toast.LENGTH_SHORT).show();
                }
            });

            binding.gifPerson.setOnClickListener(v -> {
                if (gif.hasUser()) startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(gif.user.urlProfile)));
            });
            binding.gifWeb.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(gif.url))));
            binding.gifBack.setOnClickListener(v -> requireActivity().onBackPressed());
            binding.gifDownload.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(gif.images.original.url))));
        }
    };

    public void onBackPressed(View view) {
        if (view.getId() == R.id.gifBack || view.getId() == R.id.gifFrame) {
            requireActivity().onBackPressed();
            binding.gifLayoutImage.gifImage.destroyDrawingCache();
            // binding.gifFrame.animate().scaleX(0.2f).scaleY(0.2f).translationY(-400)
            //         .setDuration(300)
            //         .rotationX(90)
            //         .setInterpolator(new LinearInterpolator())
            //         .withEndAction(() -> requireActivity().onBackPressed())
            //         .start();
        }
    }


    @Override
    public void onDestroyView() {
        binding.unbind();
        binding = null;
        super.onDestroyView();
    }

}
