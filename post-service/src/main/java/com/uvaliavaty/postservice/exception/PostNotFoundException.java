package com.uvaliavaty.postservice.exception;

public class PostNotFoundException extends RuntimeException {
    private long id;

    public PostNotFoundException(long id, String message) {
        super(message);
        this.id = id;
    }
}
