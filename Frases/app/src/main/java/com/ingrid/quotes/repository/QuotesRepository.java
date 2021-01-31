package com.ingrid.quotes.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ingrid.quotes.model.Author;
import com.ingrid.quotes.model.Quote;
import com.ingrid.quotes.model.QuoteWithAuthor;

import java.util.ArrayList;
import java.util.List;

public class QuotesRepository {

    private final AppDatabase db;

    public QuotesRepository(Context context) {
        db = Room
                .databaseBuilder(context, AppDatabase.class, "db")
                .addMigrations(create1To2Migration())
                .build();
    }

    private Migration create1To2Migration() {
        return new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE Quote ADD COLUMN isFavorite INTEGER not null default 0");
            }
        };
    }

    public List<Author> allAuthors() {
        return db.authorDAO().allAuthors();
    }

    public void add(Author author) {
        db.authorDAO().insertAuthor(author);
    }

    public void delete(Author author) {
        db.authorDAO().deleteAuthor(author);
    }

    public List<QuoteWithAuthor> allQuotes() {
        return db.quotesDAO().allQuotes();
    }

    public void add(Quote quote) {
        db.quotesDAO().insertQuote(quote);
    }

    public void delete(Quote quote) {
        db.quotesDAO().deleteQuote(quote);
    }

    public void close() {
        db.close();
    }

    public void update(Quote quote) {
        db.quotesDAO().update(quote);
    }
}
