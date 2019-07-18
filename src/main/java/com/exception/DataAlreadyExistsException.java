package com.exception;

/**
 * data already exists exception, used when non-duplicated data already exists in database.
 * @author yipeng
 *
 */
public class DataAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DataAlreadyExistsException() {
		this.message = "Data already exists";
	}
	
	public DataAlreadyExistsException(String message) {
		super();
		this.message = message;
	}
	
	public String message() {
		return this.message;
	}
	
	@Override
	public String toString() {
		return this.message + "\n" + super.toString();
	}
	
}
