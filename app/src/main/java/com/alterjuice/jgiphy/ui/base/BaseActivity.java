package com.alterjuice.jgiphy.ui.base;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alterjuice.jgiphy.R;

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG = "BaseActivity";
    protected abstract Integer getFragmentContainerID();
    protected void replaceFragment(Fragment fragment, String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                .replace(getFragmentContainerID(), fragment, tag)
                .commit();
    }

    protected void addFragment(Fragment fragment, String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit,
                        R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                .add(getFragmentContainerID(), fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    protected void turnOnOffHomeButton(boolean show) {
        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.setDisplayHomeAsUpEnabled(show);
    }

    protected Integer getResourceOrientation(){
        return getResources().getConfiguration().orientation;
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
}
