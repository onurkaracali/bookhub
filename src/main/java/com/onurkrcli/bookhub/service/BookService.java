package com.onurkrcli.bookhub.service;

import com.onurkrcli.bookhub.BookhubException;
import com.onurkrcli.bookhub.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by onur on 17/06/16.
 */
public interface BookService {

    List<Book> getAllBooks() throws BookhubException;

    void saveBook(Book newBook) throws BookhubException;

    void deleteBook(String bookId) throws BookhubException;

	Book getBookById(String id);
}
