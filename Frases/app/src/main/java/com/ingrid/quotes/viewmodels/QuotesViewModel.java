package com.ingrid.quotes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.quotes.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesViewModel extends ViewModel {

    public MutableLiveData<List<Quote>> quotesLiveData = new MutableLiveData<>();

    public QuotesViewModel(){
        ArrayList<Quote> quoteList = new ArrayList<>();
        quoteList.add(new Quote("Oi"));
        quotesLiveData.postValue(quoteList);
    }

    public void addQuote(String quote) {

    }
}
