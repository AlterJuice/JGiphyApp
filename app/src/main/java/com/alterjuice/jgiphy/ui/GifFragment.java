package com.alterjuice.jgiphy.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class GifFragment extends Fragment {
    String TAG = "GifFragment";
    private static final String KEY_GIF = "arg:gif";
    private static final int TIMEOUT_DISMISS_SNACKBAR = 3000;

    private GifFragmentBinding binding;
    private RequestManager grm;
    private GifViewModel model;
    boolean isPortrait;

    BaseTransientBottomBar.BaseCallback<Snackbar> dismissSnackBarCallback = new BaseTransientBottomBar.BaseCallback<Snackbar>() {
        @Override
        public void onShown(Snackbar transientBottomBar) {
            new Handler().postDelayed(transientBottomBar::dismiss, TIMEOUT_DISMISS_SNACKBAR);
            super.onShown(transientBottomBar);
        }
    };

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
        /*
        If you set to R.layout.gif_fragment.gifFrame not clickable
        you can see the properly work of ViewModel and live data
        So only one gif will be shown to the user in several fragment instances
        */

        isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if (getArguments() == null) {
            Log.d(TAG, "FragmentArguments null");
            return binding.getRoot();
        }

        Gif gif = (Gif) getArguments().getSerializable(KEY_GIF);

        if (gif == null) {
            Log.d(TAG, "GifArgument is null");
            return binding.getRoot();
        }
        model = new ViewModelProvider(requireActivity(), new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new GifViewModel(gif);
            }
        }).get(GifViewModel.class);
        model.getGif().postValue(gif);
        grm = Glide.with(requireActivity());
        return binding.getRoot();
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
            set.setDimensionRatio(binding.gifLayoutImage.gifImage.getId(), gif.getImageForOriginal().getImageRatio());
            set.applyTo(binding.gifLayoutImage.gifConstraint);

            grm.load(gif.getImageForOriginal().url)
                    .thumbnail(grm.load(gif.getImageFor(isPortrait).url))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // Well i got NullPointerException errors on binding.gifProgress. Why?
                            if (binding != null && binding.gifProgress != null)
                                binding.gifProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .fitCenter()
                    .skipMemoryCache(true)
                    .into(binding.gifLayoutImage.gifImage);

            binding.gifBack.setOnClickListener(v -> {
                // binding.gifFrame.animate().scaleX(0.2f).scaleY(0.2f).translationY(-400)
                //         .setDuration(2000).rotationX(90).alpha(0.3f)
                //         .setInterpolator(new LinearInterpolator())
                //         .withEndAction(() -> requireActivity().onBackPressed())
                //         .start();
                requireActivity().onBackPressed();

            });
            binding.gifUrl.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, gif.url);
                Intent chooserView = Intent.createChooser(intent, getString(R.string.share_gif_intent));
                requireActivity().startActivityFromFragment(GifFragment.this, chooserView, 1);
            });
            binding.gifUrl.setOnLongClickListener(v -> { copyLinkToClipboard(gif.title, gif.url); return false; });
            binding.gifDownload.setOnClickListener(v -> openUrlInWeb(gif.images.original.url));
            binding.gifPerson.setOnClickListener(v -> { if (gif.hasUser()) openUrlInWeb(gif.user.urlProfile); });
            binding.gifWeb.setOnClickListener(v -> openUrlInWeb(gif.url));
        }
    };

    private void openUrlInWeb(String url){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void copyLinkToClipboard(String urlTitle, String url){
        // Helps user to copy the url link
        ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            // Saving old clip data to restore it with on UNDO click.
            ClipData oldData = clipboard.getPrimaryClip();
            ClipData newClip = ClipData.newPlainText(getString(R.string.gif_clipboard_copy, urlTitle), url);
            clipboard.setPrimaryClip(newClip);
            Snackbar copySnack = Snackbar.make(binding.getRoot(), R.string.action_url_copied, BaseTransientBottomBar.LENGTH_SHORT);
            copySnack.setAction(R.string.undo, v1 -> {
                clipboard.setPrimaryClip(oldData);
                Snackbar undoCopySnack = Snackbar.make(binding.getRoot(), R.string.undo_copy_data_added, BaseTransientBottomBar.LENGTH_SHORT);
                undoCopySnack.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                undoCopySnack.addCallback(dismissSnackBarCallback);
                undoCopySnack.show();
            });
            copySnack.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            copySnack.addCallback(dismissSnackBarCallback);
            copySnack.show();
        }
    }

    @Override
    public void onDestroyView() {
        binding.unbind();
        binding = null;
        super.onDestroyView();
    }
}
