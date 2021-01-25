package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.adapters.DeleteListener;
import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;
import com.ingrid.quotes.repository.QuotesRepository;
import com.ingrid.quotes.util.RXManager;

import java.util.List;

public class QuotesViewModel extends ViewModel implements DeleteListener<Quote> {

    public MutableLiveData<List<QuoteWithAuthor>> quotesLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();

    private QuotesRepository quotesRepository;
    private RXManager rxManager = new RXManager();

    public QuotesViewModel(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
        rxManager.
                onIO(() -> {
                    refreshQuotes();
                    refreshAuthor();
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        rxManager.dispose();
        quotesRepository.close();
    }

    private void refreshQuotes() {
        List<QuoteWithAuthor> quoteList = quotesRepository.allQuotes();
        quotesLiveData.postValue(quoteList);
    }

    private void refreshAuthor() {
        List<Author> authorList = quotesRepository.allAuthors();
        authorsLiveData.postValue(authorList);
    }

    public void addQuote(String quoteText, Author author) {
        if (isValid(quoteText)) {
            Quote quote = new Quote(quoteText, author.getAuthorId());
            rxManager.
                    onIO(() -> {
                        quotesRepository.add(quote);
                        refreshQuotes();
                    });
        }
    }

    @Override
    public void delete(Quote quote) {
        rxManager.
                onIO(() -> {
                    quotesRepository.delete(quote);
                    refreshQuotes();
                });
    }

    private boolean isValid(String quoteText) {
        return quoteText.length() >= 3;
    }
}
