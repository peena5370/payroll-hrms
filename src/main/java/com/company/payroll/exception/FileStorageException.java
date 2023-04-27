package com.company.payroll.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 5915457081010942561L;

	public FileStorageException(String message) {
		super(message);
	}
	
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
