package com.laposte.pic.examples.core;

public class RequestException extends Exception {

	public RequestException(String message, Exception e) {
		super(message, e);
	}

	public RequestException(Exception e) {
		super(e);
	}


}
