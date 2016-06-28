package com.onurkrcli.bookhub.tests.validator;

import com.onurkrcli.bookhub.BookhubApplicationConfiguration;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.validation.BookValidator;
import com.onurkrcli.bookhub.validation.ValidationException;
import com.onurkrcli.bookhub.validation.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;

/**
 * Created by onur on 19/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BookhubApplicationConfiguration.class)
public class BookValidatorTest {

    @Test
    public void testValidate_withEmptyBookFields_shouldNotValidate() {
        Book book = new Book();

        Validator bookValidator = new BookValidator();
        try {
            bookValidator.validate(book);

            fail("Validation should be failed");
        }
        catch(ValidationException ex) {
        }
    }

    @Test
    public void testValidate_withValidBookObject_shouldBeValidated() {
        Book book = new Book();
        book.setName("Test book");
        book.setAuthor("Test author");

        BookValidator bookValidator = new BookValidator();

        try {
            bookValidator.validate(book);
        }
        catch(Exception ex) {
            fail();
        }
    }
}
