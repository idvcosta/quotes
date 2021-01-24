package com.ingrid.quotes.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;

import java.util.List;

@Dao
public interface QuoteDAO {
    @Query("SELECT * FROM quote")
    List<QuoteWithAuthor> allQuotes();

    @Insert
    void insertQuote(Quote quote);

    @Delete
    void deleteQuote(Quote quote);
}
