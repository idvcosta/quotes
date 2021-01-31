package com.ingrid.quotes.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Author.class, parentColumns = "authorId", childColumns = "authorId", onDelete = ForeignKey.CASCADE))
public class Quote {

    @PrimaryKey(autoGenerate = true)
    private int quoteId;

    private String quote;
    private int authorId;
    private boolean isFavorite;

    public Quote() {

    }

    public Quote(String quote, int authorId) {
        this.quote = quote;
        this.authorId = authorId;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
