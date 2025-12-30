package com.manus.lms.service;

import com.manus.lms.model.Book;
import com.manus.lms.repository.IBookRepository;
import com.manus.lms.strategy.ISearchStrategy;
import java.util.List;

/**
 * فئة الخدمة الرئيسية.
 * تطبق مبدأ Single Responsibility Principle (SRP): مسؤولة فقط عن منطق الأعمال (إدارة الكتب).
 * تطبق مبدأ Dependency Inversion Principle (DIP): تعتمد على التجريدات (IBookRepository و ISearchStrategy).
 */
public class LibraryService {
    private final IBookRepository bookRepository;
    private ISearchStrategy searchStrategy;

    // DIP: تعتمد على واجهة IBookRepository
    public LibraryService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // OCP: يمكن تغيير استراتيجية البحث دون تعديل هذه الفئة
    public void setSearchStrategy(ISearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    // منطق الأعمال: إضافة كتاب
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    // منطق الأعمال: استعارة كتاب
    public boolean borrowBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
            bookRepository.update(book);
            System.out.println("Service: Book borrowed successfully: " + book.getTitle());
            return true;
        }
        System.out.println("Service: Failed to borrow book with ISBN: " + isbn);
        return false;
    }

    // منطق الأعمال: إرجاع كتاب
    public boolean returnBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            bookRepository.update(book);
            System.out.println("Service: Book returned successfully: " + book.getTitle());
            return true;
        }
        System.out.println("Service: Failed to return book with ISBN: " + isbn);
        return false;
    }

    // منطق الأعمال: البحث عن كتاب (باستخدام نمط Strategy)
    public List<Book> searchBooks(String query) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy must be set before searching.");
        }
        List<Book> allBooks = bookRepository.findAll();
        return searchStrategy.search(allBooks, query);
    }

    // منطق الأعمال: عرض جميع الكتب
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
