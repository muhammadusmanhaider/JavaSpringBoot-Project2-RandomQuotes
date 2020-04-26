package com.assignment4.API.Models;
import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quotes
{
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "quoteid")
    private String quoteId;

    @Column(name = "quotemessage")
    private String quoteMessage;

    @Column(name = "quoteauthor")
    private String quoteAuthor;

    public Quotes() {
    }

    public Quotes(String id, String quoteId, String quoteMessage, String quoteAuthor) {
        this.quoteId = quoteId;
        this.quoteMessage = quoteMessage;
        this.quoteAuthor = quoteAuthor;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteMessage() {
        return quoteMessage;
    }

    public void setQuoteMessage(String quoteMessage) {
        this.quoteMessage = quoteMessage;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }
}