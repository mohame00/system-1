package com.manus.lms.factory;

import com.manus.lms.model.Book;
import com.manus.lms.model.FictionBook;
import com.manus.lms.model.NonFictionBook;

/**
 * تطبيق نمط Factory Method.
 * مسؤولية وحيدة (SRP) لإنشاء كائنات Book.
 * يطبق مبدأ Open/Closed Principle (OCP) من خلال استخدام واجهة IBookFactory
 * وفئة Book المجردة، مما يسمح بإضافة أنواع كتب جديدة دون تعديل هذه الفئة.
 */
public class BookFactory implements IBookFactory {

    @Override
    public Book createBook(String title, String author, String isbn, String typeSpecificData) {
        // تحديد نوع الكتاب بناءً على البيانات المحددة
        if (typeSpecificData.toLowerCase().contains("fiction")) {
            // typeSpecificData هنا هو النوع (Genre)
            return new FictionBook(title, author, isbn, typeSpecificData);
        } else if (typeSpecificData.toLowerCase().contains("non-fiction")) {
            // typeSpecificData هنا هو الموضوع (Subject)
            return new NonFictionBook(title, author, isbn, typeSpecificData);
        } else {
            throw new IllegalArgumentException("Invalid book type specified: " + typeSpecificData);
        }
    }
}
