package com.ingrid.quotes.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ingrid.quotes.repository.QuotesRepository;

public class QuotesViewModelProvider implements ViewModelProvider.Factory {
    private Context context;

    public QuotesViewModelProvider(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == AuthorsViewModel.class) {
            return (T) new AuthorsViewModel(new QuotesRepository(context));
        }else{
            return (T) new QuotesViewModel();
        }
    }
}
