package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.repository.QuotesRepository;

import java.util.List;

public class QuotesViewModel extends ViewModel {

    public MutableLiveData<List<Quote>> quotesLiveData = new MutableLiveData<>();
    private QuotesRepository quotesRepository;

    public QuotesViewModel(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;

        new Thread() {
            @Override
            public void run() {
                refreshQuotes();
            }
        }.start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        quotesRepository.close();
    }

    private void refreshQuotes() {
        List<Quote> quoteList = quotesRepository.allQuotes();
        quotesLiveData.postValue(quoteList);
    }

    public void addQuote(String quoteText) {
        if (isValid(quoteText)) {
            Quote quote = new Quote(quoteText);
            new Thread() {
                @Override
                public void run() {
                    quotesRepository.add(quote);
                    refreshQuotes();
                }
            }.start();
        }

    }

    private boolean isValid(String quoteText) {
        return quoteText.length() >= 3;
    }
}
