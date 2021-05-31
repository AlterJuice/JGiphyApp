package com.alterjuice.jgiphy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alterjuice.jgiphy.model.giphy.Gif;

public class GifViewModel extends ViewModel {
    private final MutableLiveData<Gif> observableGif;

    public GifViewModel(Gif gif){
        observableGif = new MutableLiveData<>(gif);
    }

    public MutableLiveData<Gif> getGif() {
        return observableGif;
    }
}
