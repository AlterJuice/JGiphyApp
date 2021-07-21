package com.alterjuice.jgiphy.ui.base;


import android.content.res.Configuration;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment{
    protected abstract void attachObservers();
    protected abstract void initViewModels();
    protected abstract void closeFragment();

    protected void turnOnOffHomeButton(boolean show){
        getBaseActivity().turnOnOffHomeButton(show);
    }

    protected void replaceFragment(Fragment fragment){
        replaceFragment(fragment, null);
    }

    protected void replaceFragment(Fragment fragment, String tag){
        getBaseActivity().replaceFragment(fragment, tag);
    }

    protected void addFragment(Fragment fragment){
        addFragment(fragment, null);
    }
    protected void addFragment(Fragment fragment, String tag){
        getBaseActivity().addFragment(fragment, tag);
    }

    protected Integer getResourceOrientation(){
        return getBaseActivity().getResourceOrientation();
    }

    protected boolean orientationIsPortrait(){
        return getResourceOrientation() == Configuration.ORIENTATION_PORTRAIT;
    }

    protected void onBackPressed(){
        getBaseActivity().onBackPressed();
    }

    private BaseActivity getBaseActivity(){
        return ((BaseActivity) requireActivity());
    }

}
