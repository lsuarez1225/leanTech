package com.leantech.prueba.util;

public class Response {

	private String responseCode;
	
	private Object responseObject;
	
	public String getCode() {
		return responseCode;
	}
	
	public void setCode(String code) {
		this.responseCode = code;
	}
	
	public Object getObject() {
		return responseObject;
	}
	
	public void setObject(Object object) {
		this.responseObject = object;
	}
	
}
