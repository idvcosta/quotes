package com.ingrid.quotes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {

    @PrimaryKey(autoGenerate = true)
    private int authorId;
    //TODO Test with getter and setter
    public String name;

    public Author(){

    }

    public Author(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
