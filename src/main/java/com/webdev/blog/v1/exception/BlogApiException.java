package com.webdev.blog.v1.exception;

public class BlogApiException extends RuntimeException{
    public BlogApiException(String message) {
        super(message);
    }

    public BlogApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogApiException(Throwable cause) {
        super(cause);
    }
}
