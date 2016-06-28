package com.onurkrcli.bookhub.tests.controller;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.onurkrcli.bookhub.BookhubApplicationConfiguration;
import com.onurkrcli.bookhub.model.Book;
import com.onurkrcli.bookhub.repository.BookRepository;
import com.onurkrcli.bookhub.service.BookService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {BookhubApplicationConfiguration.class})
public class BookControllerIntegrationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	BookService bookService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAllBooksCall() throws Exception {
		List<Book> expectedBooks = Arrays.asList(
                new Book("Test Book 1", "Test"),
                new Book("Test Book 2", "Test"),
                new Book("Test Book 3", "Test"));
		
		BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);

        ReflectionTestUtils.setField(bookService, "bookRepository", mockBookRepository);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/book/"))
			.andDo(print(System.out))
			.andExpect(jsonPath("$.result", is("OK")))
            .andExpect(jsonPath("$.data", hasSize(3)))
            .andExpect(jsonPath("$.data[0].name", is("Test Book 1")))
            .andExpect(jsonPath("$.data[0].author", is("Test")))
            .andExpect(jsonPath("$.data[1].name", is("Test Book 2")))
            .andExpect(jsonPath("$.data[1].author", is("Test")))
			.andExpect(jsonPath("$.data[2].name", is("Test Book 3")))
	        .andExpect(jsonPath("$.data[2].author", is("Test")));

	}

}
