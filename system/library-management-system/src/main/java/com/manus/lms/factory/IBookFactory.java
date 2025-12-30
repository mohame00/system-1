package com.manus.lms.factory;

import com.manus.lms.model.Book;

/**
 * واجهة لنمط Factory Method.
 * تُستخدم لإنشاء كائنات Book.
 */
public interface IBookFactory {
    Book createBook(String title, String author, String isbn, String typeSpecificData);
}
