package com.manus.lms;

import com.manus.lms.model.Book;
import com.manus.lms.repository.BookRepositoryImpl;
import com.manus.lms.repository.IBookRepository;
import com.manus.lms.service.LibraryService;
import com.manus.lms.factory.BookFactory;
import com.manus.lms.factory.IBookFactory;
import com.manus.lms.strategy.SearchByAuthorStrategy;
import com.manus.lms.strategy.SearchByTitleStrategy;
import com.manus.lms.util.LibraryDBManager; // لضمان تهيئة Singleton

import java.util.List;

/**
 * الفئة الرئيسية لتشغيل نظام إدارة المكتبة.
 * توضح كيفية استخدام المكونات وتطبيق أنماط التصميم ومبادئ SOLID.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("--- نظام إدارة المكتبة (LMS) ---");

        // 1. تطبيق نمط Singleton:
        // يتم الوصول إلى مثيل قاعدة البيانات (المحاكاة) مرة واحدة فقط.
        LibraryDBManager dbManager1 = LibraryDBManager.getInstance();
        LibraryDBManager dbManager2 = LibraryDBManager.getInstance();
        System.out.println("\nSingleton Check: dbManager1 == dbManager2 is " + (dbManager1 == dbManager2));

        // إعداد المكونات الرئيسية (DIP)
        IBookRepository bookRepository = new BookRepositoryImpl();
        LibraryService libraryService = new LibraryService(bookRepository);

        // 2. تطبيق نمط Factory Method:
        IBookFactory bookFactory = new BookFactory();

        // إضافة كتب باستخدام المصنع
        Book book1 = bookFactory.createBook("The Lord of the Rings", "J.R.R. Tolkien", "978-0618260292", "Fiction: Fantasy");
        Book book2 = bookFactory.createBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "978-0062316097", "Non-Fiction: History");
        Book book3 = bookFactory.createBook("The Hobbit", "J.R.R. Tolkien", "978-0345339683", "Fiction: Fantasy");

        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);

        System.out.println("\n--- جميع الكتب في المكتبة ---");
        libraryService.getAllBooks().forEach(System.out::println);

        // 3. تطبيق نمط Strategy:
        System.out.println("\n--- البحث عن طريق العنوان (Strategy 1) ---");
        libraryService.setSearchStrategy(new SearchByTitleStrategy());
        List<Book> searchResults1 = libraryService.searchBooks("Sapiens");
        searchResults1.forEach(System.out::println);

        System.out.println("\n--- البحث عن طريق المؤلف (Strategy 2) ---");
        libraryService.setSearchStrategy(new SearchByAuthorStrategy());
        List<Book> searchResults2 = libraryService.searchBooks("Tolkien");
        searchResults2.forEach(System.out::println);

        // عمليات الإعارة والإرجاع
        System.out.println("\n--- عمليات الإعارة والإرجاع ---");
        libraryService.borrowBook("978-0618260292"); // The Lord of the Rings
        libraryService.borrowBook("978-0618260292"); // محاولة استعارة مرة أخرى (فشل)
        libraryService.returnBook("978-0618260292");
        libraryService.returnBook("978-0618260292"); // محاولة إرجاع مرة أخرى (فشل)

        System.out.println("\n--- حالة الكتب بعد العمليات ---");
        libraryService.getAllBooks().forEach(System.out::println); 
        
    }
}
