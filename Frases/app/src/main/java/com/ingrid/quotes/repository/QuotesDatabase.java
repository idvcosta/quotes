package com.ingrid.quotes.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ingrid.quotes.model.Author;

@Database(entities = {Author.class}, version = 1)
public abstract class QuotesDatabase extends RoomDatabase {
    public abstract AuthorDAO authorDAO();
}
