package com.ingrid.quotes.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ingrid.quotes.model.Quote;

import java.util.List;

@Dao
public interface QuoteDAO {
    @Query("SELECT * FROM quote")
    List<Quote> allQuotes();

    @Insert
    void insertQuote(Quote quote);
}
