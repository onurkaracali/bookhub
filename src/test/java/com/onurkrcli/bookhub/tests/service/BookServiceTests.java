package com.onurkrcli.bookhub.tests.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.onurkrcli.bookhub.BookhubApplicationConfiguration;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.repository.BookRepository;
import com.onurkrcli.bookhub.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {BookhubApplicationConfiguration.class})
public class BookServiceTests {

	@Autowired
    BookService bookService;

    @Autowired
    MongoTemplate mongoTemplate;
	
	@Test
    public void testGetAllBooks_shouldReturnAllMockBooks() throws Exception {
        List<Book> expectedBooks = Arrays.asList(
                new Book("Test Book 1", "Test"),
                new Book("Test Book 2", "Test"),
                new Book("Test Book 3", "Test"));

        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);

        ReflectionTestUtils.setField(bookService, "bookRepository", mockBookRepository);

        List<Book> allBooks = bookService.getAllBooks();

        assertTrue("Service should return expected result",
                allBooks.equals(expectedBooks));
    }
}
