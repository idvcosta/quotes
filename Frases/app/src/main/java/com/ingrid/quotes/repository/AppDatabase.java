package com.ingrid.quotes.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.model.Quote;

@Database(entities = {Author.class, Quote.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuthorDAO authorDAO();

    public abstract QuoteDAO quotesDAO();
}
