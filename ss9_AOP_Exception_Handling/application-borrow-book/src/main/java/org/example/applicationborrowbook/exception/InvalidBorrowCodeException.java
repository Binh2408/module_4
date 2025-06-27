package org.example.applicationborrowbook.exception;

public class InvalidBorrowCodeException extends Exception{
    public InvalidBorrowCodeException(String message) {
        super(message);
    }
}
