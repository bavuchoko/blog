package com.parkjongsu.blog.common.exception;

public class AlreadyExistSuchDataCustomException  extends RuntimeException {
    public AlreadyExistSuchDataCustomException(String message) {
        super(message);
    }
}
