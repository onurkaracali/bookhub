package com.onurkrcli.bookhub.validation;

import com.onurkrcli.bookhub.model.Book;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by onur on 19/06/16.
 */
public class BookValidator implements Validator<Book> {

    public void validate(Book book) throws ValidationException {

        if(StringUtils.isBlank(book.getAuthor())
                ||StringUtils.isBlank(book.getName())) {
            throw new ValidationException("Mandatory fields is empty");
        }
    }
}
