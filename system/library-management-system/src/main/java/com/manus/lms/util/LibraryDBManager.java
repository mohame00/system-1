package com.manus.lms.util;

import java.util.ArrayList;
import java.util.List;
import com.manus.lms.model.Book;

/**
 * تطبيق نمط Singleton لإدارة اتصال قاعدة البيانات (محاكاة).
 * يضمن أن يكون هناك مثيل واحد فقط من هذه الفئة (SRP: مسؤولية وحيدة لإدارة "قاعدة البيانات").
 */
public class LibraryDBManager {
    // المثيل الوحيد للفئة
    private static LibraryDBManager instance;
    
    // قائمة الكتب المخزنة (محاكاة لقاعدة البيانات)
    private final List<Book> books;

    /**
     * المُنشئ الخاص لمنع إنشاء مثيلات خارجية.
     */
    private LibraryDBManager() {
        this.books = new ArrayList<>();
        System.out.println("LibraryDBManager: Database connection initialized (Singleton).");
    }

    /**
     * الطريقة العامة للوصول إلى المثيل الوحيد.
     * @return المثيل الوحيد لـ LibraryDBManager.
     */
    public static LibraryDBManager getInstance() {
        if (instance == null) {
            // تزامن لضمان سلامة الخيوط في بيئة متعددة الخيوط
            synchronized (LibraryDBManager.class) {
                if (instance == null) {
                    instance = new LibraryDBManager();
                }
            }
        }
        return instance;
    }

    // عمليات قاعدة البيانات (محاكاة)
    public void addBook(Book book) {
        this.books.add(book);
        System.out.println("DB: Added book: " + book.getTitle());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books); // إرجاع نسخة لتجنب التعديل الخارجي
    }

    public Book findBookByIsbn(String isbn) {
        return this.books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
}
