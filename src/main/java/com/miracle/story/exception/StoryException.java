package com.miracle.story.exception;

import org.springframework.http.HttpStatus;

import com.miracle.exception.GatewayServiceException;

public class StoryException extends GatewayServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -816387637206481014L;
	public StoryException() {
	}

	/**
	 * @param message
	 */
	public StoryException(String message) {
		super(message);
	}
	
	public StoryException(String message,String errorCode) {
		super(message);
		setErrorCode(errorCode);
	}
	public StoryException(String message,String errorCode,HttpStatus statusCode) {
		super(message);
		setErrorCode(errorCode);
		setStatusCode(statusCode);
	}

	/**
	 * @param cause
	 */
	public StoryException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	public StoryException(String message, Throwable cause,String errorCode) {
		super(message, cause);
		setErrorCode(errorCode);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param errorCode
	 * @param statusCode
	 */
	public StoryException(String message, Throwable cause,String errorCode,HttpStatus statusCode) {
		super(message, cause);
		setErrorCode(errorCode);
		setStatusCode(statusCode);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
