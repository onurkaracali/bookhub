package com.onurkrcli.bookhub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by onur on 17/06/16.
 */
@Document
public class Book {

    @Id
    private String id;

    private String name;
    private String author;

    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Book bookObj = (Book) obj;
        return this.id.equals(bookObj.getId())
                && this.name.equals(bookObj.getName())
                && this.author.equals(bookObj.getAuthor());
    }
}
