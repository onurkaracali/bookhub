package com.onurkrcli.bookhub.controller;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.onurkrcli.bookhub.model.Book;

@XmlRootElement
public class SaveBookRequestDTO implements Serializable{
	private Book book;
	private String capcha;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getCapcha() {
		return capcha;
	}
	public void setCapcha(String capcha) {
		this.capcha = capcha;
	}
}
