package com.bridgelabz.fundoo.customexceptions;

public class InvalidUserException extends RuntimeException {

	public InvalidUserException(String message) {
        super(message);
    }
}
