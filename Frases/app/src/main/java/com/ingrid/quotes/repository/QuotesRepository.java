package com.ingrid.quotes.repository;

import android.content.Context;

import androidx.room.Room;

import com.ingrid.quotes.model.Author;

import java.util.List;

public class QuotesRepository {

    private final QuotesDatabase db;

    public QuotesRepository(Context context) {
         db = Room.databaseBuilder(context, QuotesDatabase.class, "db").build();
    }

    public List<Author> allAuthors() {
        return db.authorDAO().allAuthors();
    }

    public void add(Author author) {
        db.authorDAO().insertAuthor(author);
    }
}
