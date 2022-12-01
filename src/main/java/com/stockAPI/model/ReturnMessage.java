package com.stockAPI.model;

import java.util.Map;

public class ReturnMessage {

	private String message;

	private Map<String, Object> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
