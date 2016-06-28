package com.onurkrcli.bookhub.controller;

import java.io.Serializable;
import java.util.List;

public class BookhubResponse <T> implements Serializable {
	
	private T data;
	private String result;
	private List<String> messages;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
