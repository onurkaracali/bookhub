package com.onurkrcli.bookhub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onurkrcli.bookhub.BookhubException;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.service.BookService;
import com.onurkrcli.bookhub.validation.CaptchaValidationUtil;
import com.onurkrcli.bookhub.validation.ValidationException;

@Controller
@ResponseBody
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(path = "/", method= RequestMethod.GET)
	public BookhubResponse<List<Book>> getAllBooks() throws BookhubException {
		BookhubResponse<List<Book>> response= new BookhubResponse<>();
		List<Book> allBooks = bookService.getAllBooks();
		
		response.setData(allBooks);
		response.setResult("OK");
		return response;
	}
	
	
	@RequestMapping(path = "/", method= RequestMethod.POST)
	public BookhubResponse<String> saveBook(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody SaveBookRequestDTO requestObj) throws BookhubException, ValidationException {
		
		BookhubResponse<String> responseObj = new BookhubResponse<>();
		if (!CaptchaValidationUtil.isCaptchaValueValid(request, requestObj.getCapcha())) {
			throw new ValidationException("INCORRECT_CAPTCHA");
		}
		
		bookService.saveBook(requestObj.getBook());
		responseObj.setData("SAVE SUCCEED");
		responseObj.setResult("OK");
		
		return responseObj;
	}
	
	@RequestMapping(path="/{bookId}", method = RequestMethod.DELETE)
	public BookhubResponse<String> deleteBook(@PathVariable("bookId") String bookId) throws BookhubException {
		BookhubResponse<String> responseObj = new BookhubResponse<>();
		bookService.deleteBook(bookId);
		
		responseObj.setData("DELETE");
		responseObj.setResult("OK");
		return responseObj;
	}
	
	@RequestMapping(path="/", method = RequestMethod.PUT)
	public BookhubResponse<String> updateBook(@RequestBody Book book) throws BookhubException {
		BookhubResponse<String> responseObj = new BookhubResponse<>();
		bookService.saveBook(book);
		
		responseObj.setData("UPDATE");
		responseObj.setResult("OK");
		return responseObj;
	}
}
