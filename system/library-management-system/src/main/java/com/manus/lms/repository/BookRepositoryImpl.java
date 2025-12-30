package com.manus.lms.repository;

import com.manus.lms.model.Book;
import com.manus.lms.util.LibraryDBManager;
import java.util.List;

/**
 * تطبيق واجهة IBookRepository.
 * يستخدم LibraryDBManager (Singleton) للوصول إلى البيانات.
 * هذا التطبيق الملموس هو الوحدة منخفضة المستوى التي تعتمد عليها الواجهة (DIP).
 */
public class BookRepositoryImpl implements IBookRepository {
    private final LibraryDBManager dbManager;

    public BookRepositoryImpl() {
        // الوصول إلى المثيل الوحيد لـ LibraryDBManager
        this.dbManager = LibraryDBManager.getInstance();
    }

    @Override
    public void save(Book book) {
        dbManager.addBook(book);
    }

    @Override
    public List<Book> findAll() {
        return dbManager.getAllBooks();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return dbManager.findBookByIsbn(isbn);
    }

    @Override
    public void update(Book book) {
        // في هذه المحاكاة البسيطة، لا نحتاج إلى تحديث صريح
        // لأن الكائنات في القائمة يتم تمريرها بالمرجع.
        // في تطبيق حقيقي، قد نحتاج إلى حفظ التغييرات في قاعدة البيانات.
        System.out.println("Repository: Book updated (in-memory reference updated): " + book.getTitle());
    }
}
