package com.manus.lms.model;

/**
 * فئة تمثل كتاب غير خيالي.
 * تطبق مبدأ Open/Closed Principle (OCP) من خلال توسيع فئة Book المجردة.
 */
public class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook(String title, String author, String isbn, String subject) {
        super(title, author, isbn);
        this.subject = subject;
    }

    @Override
    public String getDetails() {
        return "Non-Fiction Book - Subject: " + subject;
    }

    public String getSubject() {
        return subject;
    }
}
