package com.exception;

/**
 * exception thrown when no data found from database
 * @author yipeng
 */
public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DataNotFoundException() {
		super();
		this.message = "Data Not Found";
	}
	
	public DataNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	public String message() {
		return this.message;
	}
	
	@Override
	public String toString() {
		return this.message + "/n" + super.toString();
	}

}
