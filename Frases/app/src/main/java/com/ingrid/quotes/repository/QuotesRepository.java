package com.ingrid.quotes.repository;

import android.content.Context;

import androidx.room.Room;

import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuotesRepository {

    private final AppDatabase db;

    public QuotesRepository(Context context) {
         db = Room.databaseBuilder(context, AppDatabase.class, "db").build();
    }

    public List<Author> allAuthors() {
        return db.authorDAO().allAuthors();
    }

    public void add(Author author) {
        db.authorDAO().insertAuthor(author);
    }

    public List<Quote> allQuotes() {
        return db.quotesDAO().allQuotes();
    }

    public void add(Quote quote) {
        db.quotesDAO().insertQuote(quote);
    }

    public void close() {
        db.close();
    }
}
