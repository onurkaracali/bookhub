package com.onurkrcli.bookhub.repository;

import com.onurkrcli.bookhub.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by onur on 17/06/16.
 */
public interface BookRepository  extends MongoRepository<Book, String> {
}
