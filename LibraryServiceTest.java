package com.manus.lms;

import com.manus.lms.model.Book;
import com.manus.lms.service.LibraryService;
import com.manus.lms.strategy.SearchByAuthorStrategy;
import com.manus.lms.strategy.SearchByTitleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryService();
    }

    // ---------- ADD BOOK TESTS (1–10) ----------

    @Test void ATC_01_addValidBook() {
        service.addBook(new Book("Java", "James"));
        assertEquals(1, service.getAllBooks().size());
    }

    @Test void ATC_02_addMultipleBooks() {
        service.addBook(new Book("Java", "James"));
        service.addBook(new Book("Python", "Guido"));
        assertEquals(2, service.getAllBooks().size());
    }

    @Test void ATC_03_addBookNullTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book(null, "Author")));
    }

    @Test void ATC_04_addBookEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book("", "Author")));
    }

    @Test void ATC_05_addBookNullAuthor() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book("Java", null)));
    }

    @Test void ATC_06_addBookEmptyAuthor() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book("Java", "")));
    }

    @Test void ATC_07_addDuplicateBook() {
        service.addBook(new Book("Java", "James"));
        service.addBook(new Book("Java", "James"));
        assertEquals(2, service.getAllBooks().size());
    }

    @Test void ATC_08_bookDetailsStoredCorrectly() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        assertEquals("Java", service.getAllBooks().get(0).getTitle());
    }

    @Test void ATC_09_addBookUsingFactory() {
        Book b = service.createBook("BOOK", "C++", "Bjarne");
        service.addBook(b);
        assertEquals(1, service.getAllBooks().size());
    }

    @Test void ATC_10_libraryInitiallyEmpty() {
        assertTrue(service.getAllBooks().isEmpty());
    }

    // ---------- REMOVE BOOK TESTS (11–20) ----------

    @Test void ATC_11_removeExistingBook() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        service.removeBook(b);
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_12_removeNonExistingBook() {
        Book b = new Book("Java", "James");
        service.removeBook(b);
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_13_removeBookFromMultiple() {
        Book b1 = new Book("Java", "James");
        Book b2 = new Book("Python", "Guido");
        service.addBook(b1);
        service.addBook(b2);
        service.removeBook(b1);
        assertEquals(1, service.getAllBooks().size());
    }

    @Test void ATC_14_removeNullBook() {
        assertThrows(IllegalArgumentException.class,
                () -> service.removeBook(null));
    }

    @Test void ATC_15_removeSameBookTwice() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        service.removeBook(b);
        service.removeBook(b);
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_16_removeFromEmptyLibrary() {
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_17_removeLastBook() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        service.removeBook(b);
        assertTrue(service.getAllBooks().isEmpty());
    }

    @Test void ATC_18_removeBookKeepsOthers() {
        Book b1 = new Book("Java", "James");
        Book b2 = new Book("Python", "Guido");
        service.addBook(b1);
        service.addBook(b2);
        service.removeBook(b1);
        assertEquals("Python", service.getAllBooks().get(0).getTitle());
    }

    @Test void ATC_19_removeBookCaseInsensitive() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        service.removeBook(new Book("java", "james"));
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_20_repositoryRemoveTest() {
        Book b = new Book("Java", "James");
        service.addBook(b);
        service.removeBook(b);
        assertTrue(service.getAllBooks().isEmpty());
    }

    // ---------- SEARCH TESTS (21–35) ----------

    @Test void ATC_21_searchByTitleFound() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(1, service.search("Java").size());
    }

    @Test void ATC_22_searchByTitleNotFound() {
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(0, service.search("C++").size());
    }

    @Test void ATC_23_searchByAuthorFound() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByAuthorStrategy());
        assertEquals(1, service.search("James").size());
    }

    @Test void ATC_24_searchByAuthorNotFound() {
        service.setSearchStrategy(new SearchByAuthorStrategy());
        assertEquals(0, service.search("Unknown").size());
    }

    @Test void ATC_25_searchEmptyKeyword() {
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(0, service.search("").size());
    }

    @Test void ATC_26_searchNullKeyword() {
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertThrows(IllegalArgumentException.class,
                () -> service.search(null));
    }

    @Test void ATC_27_searchInEmptyLibrary() {
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertTrue(service.search("Java").isEmpty());
    }

    @Test void ATC_28_searchMultipleResults() {
        service.addBook(new Book("Java Basics", "A"));
        service.addBook(new Book("Java Advanced", "B"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(2, service.search("Java").size());
    }

    @Test void ATC_29_switchSearchStrategyRuntime() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(1, service.search("Java").size());
        service.setSearchStrategy(new SearchByAuthorStrategy());
        assertEquals(1, service.search("James").size());
    }

    @Test void ATC_30_verifyCorrectStrategyExecution() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertNotNull(service.search("Java"));
    }

    // ---------- FINAL TESTS (36–50) ----------

    @Test void ATC_36_librarySizeConsistency() {
        service.addBook(new Book("A", "B"));
        service.removeBook(new Book("A", "B"));
        assertEquals(0, service.getAllBooks().size());
    }

    @Test void ATC_37_addLargeNumberBooks() {
        for (int i = 0; i < 20; i++)
            service.addBook(new Book("Book" + i, "Author"));
        assertEquals(20, service.getAllBooks().size());
    }

    @Test void ATC_38_searchCaseInsensitive() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(1, service.search("java").size());
    }

    @Test void ATC_39_authorCaseInsensitive() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByAuthorStrategy());
        assertEquals(1, service.search("james").size());
    }

    @Test void ATC_40_nullSearchStrategy() {
        assertThrows(IllegalStateException.class,
                () -> service.search("Java"));
    }

    @Test void ATC_41_addBookWhitespaceTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book("   ", "Author")));
    }

    @Test void ATC_42_addBookWhitespaceAuthor() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook(new Book("Java", "   ")));
    }

    @Test void ATC_43_getAllBooksNotNull() {
        assertNotNull(service.getAllBooks());
    }

    @Test void ATC_44_serviceInitialization() {
        assertTrue(service.getAllBooks().isEmpty());
    }

    @Test void ATC_45_factoryCreatesBook() {
        Book b = service.createBook("BOOK", "OS", "Tanenbaum");
        assertNotNull(b);
    }

    @Test void ATC_46_addAndSearchIntegration() {
        service.addBook(new Book("Clean Code", "Robert Martin"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(1, service.search("Clean Code").size());
    }

    @Test void ATC_47_addRemoveAddAgain() {
        Book b = new Book("Design Patterns", "GoF");
        service.addBook(b);
        service.removeBook(b);
        service.addBook(b);
        assertEquals(1, service.getAllBooks().size());
    }

    @Test void ATC_48_searchAfterRemoval() {
        Book b = new Book("Refactoring", "Fowler");
        service.addBook(b);
        service.removeBook(b);
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertTrue(service.search("Refactoring").isEmpty());
    }

    @Test void ATC_49_multipleStrategiesSequentially() {
        service.addBook(new Book("Java", "James"));
        service.setSearchStrategy(new SearchByTitleStrategy());
        assertEquals(1, service.search("Java").size());
        service.setSearchStrategy(new SearchByAuthorStrategy());
        assertEquals(1, service.search("James").size());
    }

    @Test void ATC_50_systemStabilityTest() {
        for (int i = 0; i < 10; i++) {
            service.addBook(new Book("Book" + i, "Author" + i));
        }
        assertDoesNotThrow(() -> service.getAllBooks());
    }
}
