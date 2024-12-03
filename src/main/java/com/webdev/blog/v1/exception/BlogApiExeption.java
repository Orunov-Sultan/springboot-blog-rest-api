package com.webdev.blog.v1.exception;

public class BlogApiExeption extends RuntimeException{
    public BlogApiExeption(String message) {
        super(message);
    }

    public BlogApiExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogApiExeption(Throwable cause) {
        super(cause);
    }
}
