package com.revature.bibimbop.util.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }
}