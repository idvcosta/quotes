package com.ingrid.quotes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quote {

    @PrimaryKey( autoGenerate = true)
    public int quoteId;

    private String quote;

    public Quote(){

    }

    public Quote(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
