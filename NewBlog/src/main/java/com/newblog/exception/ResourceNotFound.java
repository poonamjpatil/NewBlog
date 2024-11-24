package com.newblog.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message)
    {
        super(message);
    }

}
