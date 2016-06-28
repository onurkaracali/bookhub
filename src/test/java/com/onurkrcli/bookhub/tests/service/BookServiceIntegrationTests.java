package com.onurkrcli.bookhub.tests.service;


import com.onurkrcli.bookhub.BookhubApplicationConfiguration;
import com.onurkrcli.bookhub.BookhubException;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.repository.BookRepository;
import com.onurkrcli.bookhub.service.BookService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {BookhubApplicationConfiguration.class})
public class BookServiceIntegrationTests {

    @Autowired
    BookService bookService;

    @Autowired
    MongoTemplate mongoTemplate;
    
    
    
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testUpdateBook_withExistingBookObject_shouldUpdate() throws Exception {
    	String testBookName = "Test Book 0";
        String testAuthorName = "Test Author 0";

        Book newBook = new Book(testBookName, testAuthorName);
        bookService.saveBook(newBook);
        
        assertNotNull(newBook.getId());
        
        String updatedBookName = "Test Book 1 Updated";
        String updatedAuthorName = "Test Author 1 Updated";

        newBook.setName(updatedBookName);
        newBook.setAuthor(updatedAuthorName);

        bookService.saveBook(newBook);


        Book fetchedBook = bookService.getBookById(newBook.getId());

        assertNotNull(fetchedBook);
        assertEquals(fetchedBook.getName(), updatedBookName);
        assertEquals(fetchedBook.getAuthor(), updatedAuthorName);
    }
    
    

    @Test
    public void testCreateBook_withValidBookObject_shouldSaveTheBookToDatabase() throws Exception {
        String testBookName = "Test Book 0";
        String testAuthorName = "Test Author 0";

        Book newBook = new Book(testBookName, testAuthorName);
        bookService.saveBook(newBook);
        
        assertNotNull(newBook.getId());
        Book fetchedBook = bookService.getBookById(newBook.getId());
        assertNotNull(fetchedBook);
        
        assertEquals(fetchedBook.getName(), testBookName);
        assertEquals(fetchedBook.getAuthor(), testAuthorName);
    }



    @Test
    public void testDeleteBook_shouldDelete() throws Exception {
        String testBookName = "Test Book 0";
        String testAuthorName = "Test Author 0";
        Book bookToDelete = new Book(testBookName, testAuthorName);

        bookService.saveBook(bookToDelete);

        assertNotNull(bookToDelete.getId());
        
        Book bookFromDb = bookService.getBookById(bookToDelete.getId());
        assertNotNull(bookFromDb);
        
        bookService.deleteBook(bookFromDb.getId());
        
        Book bookAfterDelete = bookService.getBookById(bookToDelete.getId());
        assertNull(bookAfterDelete);
    }
}
