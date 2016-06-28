package com.onurkrcli.bookhub.service;

import com.onurkrcli.bookhub.BookhubException;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.repository.BookRepository;
import com.onurkrcli.bookhub.validation.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by onur on 17/06/16.
 */
@Service
public class BookServiceImpl implements BookService {
	
	
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() throws BookhubException {
        return bookRepository.findAll();
    }

    public void saveBook(Book newBook) throws BookhubException {
        try {
            BookValidator bookValidator = new BookValidator();
            bookValidator.validate(newBook);
            bookRepository.save(newBook);
        }
        catch(Exception ex) {
            throw new BookhubException("Could not save book object", ex);
        }
    }

    public void deleteBook(String bookId) throws BookhubException {
        bookRepository.delete(bookId);
    }

	@Override
	public Book getBookById(String id) {
		return bookRepository.findOne(id);
	}
}
