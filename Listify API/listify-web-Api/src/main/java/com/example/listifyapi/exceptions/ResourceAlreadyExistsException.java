package com.example.listifyapi.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(){
        super("resource already exists");
    }
}
