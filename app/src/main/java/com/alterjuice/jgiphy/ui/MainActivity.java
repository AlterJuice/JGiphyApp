package com.alterjuice.jgiphy.ui;

import android.os.Bundle;

import com.alterjuice.jgiphy.R;
import com.alterjuice.jgiphy.ui.base.BaseActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            GifListFragment gifListFragment = new GifListFragment();
            addFragment(gifListFragment, GifListFragment.TAG);
        }
    }

    @Override
    protected void onDestroy() {
        new Thread(() -> Glide.get(getApplicationContext()).clearDiskCache()).start();
        Glide.get(this).clearMemory();
        super.onDestroy();
    }

    @Override
    protected Integer getFragmentContainerID() {
        return R.id.mainFragmentContainer;
    }
}