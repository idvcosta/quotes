package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.repository.QuotesRepository;

import java.util.List;

public class AuthorsViewModel extends ViewModel {

    public MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();
    private QuotesRepository repository;

    public AuthorsViewModel(QuotesRepository repository) {
        this.repository = repository;
        new Thread() {
            @Override
            public void run() {
                refreshAuthors(repository);
            }
        }.start();
    }


    @Override
    protected void onCleared() {
        super.onCleared();

        repository.close();
    }


    private void refreshAuthors(QuotesRepository repository) {
        List<Author> authors = repository.allAuthors();
        authorsLiveData.postValue(authors);
    }

    public void addAuthor(String authorName) {
        if (isValid(authorName)) {
            Author author = new Author(authorName);
            new Thread() {
                @Override
                public void run() {
                    repository.add(author);
                    refreshAuthors(repository);
                }
            }.start();
        }
    }

    private boolean isValid(String authorName) {
        return authorName.length() >= 3;
    }
}
