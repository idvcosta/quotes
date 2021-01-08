package com.ingrid.quotes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {

    @PrimaryKey(autoGenerate = true)
    public int authorId;
    //TODO Test with getter and setter
    public String name;

    public Author(){

    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
