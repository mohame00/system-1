package com.manus.lms.strategy;

import com.manus.lms.model.Book;
import java.util.List;

/**
 * واجهة لنمط Strategy.
 * تُستخدم لتحديد خوارزمية البحث.
 * تطبق مبدأ Open/Closed Principle (OCP) ومبدأ Dependency Inversion Principle (DIP).
 */
public interface ISearchStrategy {
    List<Book> search(List<Book> books, String query);
}
