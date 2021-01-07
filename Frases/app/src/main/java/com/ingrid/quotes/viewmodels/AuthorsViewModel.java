package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorsViewModel extends ViewModel {

    public MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();

    public AuthorsViewModel() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Shakespeare"));
        authorsLiveData.postValue(authors);
    }
}
