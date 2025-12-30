package com.manus.lms.model;

/**
 * فئة أساسية مجردة لتمثيل كتاب في نظام إدارة المكتبة.
 * تُستخدم كجزء من نمط Factory Method لتكوين أنواع مختلفة من الكتب.
 */
public abstract class Book {
    protected String title;
    protected String author;
    protected String isbn;
    protected boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Setters
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    // Abstract method to be implemented by concrete book types (OCP)
    public abstract String getDetails();

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Borrowed: " + isBorrowed;
    }
}
