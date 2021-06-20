package com.alterjuice.jgiphy.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.alterjuice.jgiphy.databinding.ActivityMainBinding;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            GifListFragment gifListFragment = new GifListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(binding.mainFragment.getId(), gifListFragment, GifListFragment.TAG)
                    .commit();
        }
    }

    public void turnOnOffHomeButton(boolean show) {
        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.setDisplayHomeAsUpEnabled(show);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportFragmentManager().popBackStack();
            turnOnOffHomeButton(false);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        new Thread(() -> Glide.get(getApplicationContext()).clearDiskCache()).start();
        Glide.get(this).clearMemory();
        super.onDestroy();
    }
}