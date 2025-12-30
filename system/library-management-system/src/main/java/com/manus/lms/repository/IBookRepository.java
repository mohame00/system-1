package com.manus.lms.repository;

import com.manus.lms.model.Book;
import java.util.List;

/**
 * واجهة لتطبيق نمط Repository.
 * تُستخدم لتطبيق مبدأ Dependency Inversion Principle (DIP).
 * الوحدات عالية المستوى (الخدمات) تعتمد على هذه الواجهة (التجريد) بدلاً من التطبيق الملموس (LibraryDBManager).
 */
public interface IBookRepository {
    void save(Book book);
    List<Book> findAll();
    Book findByIsbn(String isbn);
    void update(Book book);
}
