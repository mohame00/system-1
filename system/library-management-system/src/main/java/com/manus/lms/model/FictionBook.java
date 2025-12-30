package com.manus.lms.model;

/**
 * فئة تمثل كتاب خيال.
 * تطبق مبدأ Open/Closed Principle (OCP) من خلال توسيع فئة Book المجردة
 * دون تعديلها، مما يسمح بإضافة أنواع جديدة من الكتب بسهولة.
 */
public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn);
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Fiction Book - Genre: " + genre;
    }

    public String getGenre() {
        return genre;
    }
}
