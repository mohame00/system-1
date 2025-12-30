package com.manus.lms.strategy;

import com.manus.lms.model.Book;
import java.util.List;
import java.util.stream.Collectors;

/**
 * تطبيق لنمط Strategy للبحث عن الكتب بالمؤلف.
 * تطبق مبدأ Open/Closed Principle (OCP) ومبدأ Dependency Inversion Principle (DIP).
 */
public class SearchByAuthorStrategy implements ISearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
