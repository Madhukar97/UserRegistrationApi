package com.bridgelabz.UserRregistrationApi.customExceptions;

public class InvalidUserException extends RuntimeException {

	public InvalidUserException(String message) {
        super(message);
    }
}
