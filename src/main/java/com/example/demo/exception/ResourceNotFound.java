package com.example.demo.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(){
        super("Resource Not Found");
    }

    public ResourceNotFound(String message){
        super(message);
    }

}
