package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorsViewModel extends ViewModel {

    private List<Author> authors;
    public MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();

    public AuthorsViewModel() {
        authors = new ArrayList<>();
        authors.add(new Author("Shakespeare"));
        authorsLiveData.postValue(authors);
    }

    public void addAuthor(String authorName) {
        if (isValid(authorName)) {
            Author author = new Author(authorName);
            authors.add(author);
            authorsLiveData.postValue(authors);
        }
    }

    private boolean isValid(String authorName) {
        return authorName.length() >= 3;
    }
}
