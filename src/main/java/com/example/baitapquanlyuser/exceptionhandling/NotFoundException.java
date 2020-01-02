package com.example.baitapquanlyuser.exceptionhandling;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String objectName, int id){
        super(objectName + "(" + id + ") not found");
    }
}

