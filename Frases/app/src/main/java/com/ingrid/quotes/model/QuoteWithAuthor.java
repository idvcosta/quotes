package com.ingrid.quotes.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class QuoteWithAuthor {
    @Embedded
    public Quote quote;
    @Relation(parentColumn = "authorId", entityColumn = "authorId")
    public Author author;

}
