package com.alterjuice.jgiphy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.model.giphy.Gif;

public class GifViewModel extends ViewModel {
    private final MutableLiveData<Gif> mutableLiveGif = new MutableLiveData<>();
    public final LiveData<Gif> liveGif = mutableLiveGif;
    public void setGif(Gif gif){
        mutableLiveGif.setValue(gif);
    }
}
