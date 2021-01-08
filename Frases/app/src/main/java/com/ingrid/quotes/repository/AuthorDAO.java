package com.ingrid.quotes.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ingrid.quotes.model.Author;

import java.util.List;

@Dao
public interface AuthorDAO {
    @Query("SELECT * FROM author")
    List<Author> allAuthors();

    @Insert
    void insertAuthor(Author author);

}
