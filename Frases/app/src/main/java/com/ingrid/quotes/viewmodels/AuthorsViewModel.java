package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.adapters.DeleteListener;
import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.repository.QuotesRepository;
import com.ingrid.quotes.util.RXManager;

import java.util.List;

public class AuthorsViewModel extends ViewModel implements DeleteListener<Author> {

    public MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();
    private final QuotesRepository repository;
    private RXManager rxManager = new RXManager();

    public AuthorsViewModel(QuotesRepository repository) {
        this.repository = repository;

        rxManager.onIO(this::refreshAuthors);
    }

    private void refreshAuthors() {
        List<Author> authors = repository.allAuthors();
        authorsLiveData.postValue(authors);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        rxManager.dispose();
        repository.close();
    }

    public void addAuthor(String authorName) {
        if (isValid(authorName)) {
            Author author = new Author(authorName);

            rxManager.onIO(() -> {
                repository.add(author);
                refreshAuthors();
            });
        }
    }

    private boolean isValid(String authorName) {
        return authorName.length() >= 3;
    }

    @Override
    public void delete(Author author) {
        rxManager.onIO(() -> {
            repository.delete(author);
            refreshAuthors();
        });
    }
}
