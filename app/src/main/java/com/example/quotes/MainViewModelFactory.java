package com.example.quotes;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public MainViewModelFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class" + modelClass.getName());
    }
}
