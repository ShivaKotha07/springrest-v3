package com.capgemini.exception;

public class UserException extends RuntimeException {
	public UserException(String msg) {
		super(msg);
	}
	
	/*public UserException(Exception e,String msg) {
		super(msg);
		e.printStackTrace();
	}*/
}