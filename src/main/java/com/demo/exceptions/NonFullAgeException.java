package com.demo.exceptions;


public class NonFullAgeException extends Exception {

	String logMessage;
	
	public NonFullAgeException(String logString) {
		// TODO Auto-generated constructor stub
		logMessage = logString;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
