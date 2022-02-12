package com.bridgelabz.UserRregistrationApi.customExceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidUserException extends RuntimeException {

	public InvalidUserException(String message) {
        super(message);
    }
}
