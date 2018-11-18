package com.joseyustiz.msvcdojo.profilesservice.exception;

/**
 * Created by jyustiz on 11/5/18 for project microservice-dojo-katas.
 */
public class UnsupportedPhotoFormatException extends IllegalArgumentException{
    public UnsupportedPhotoFormatException(String message){
        super(message);
    }
}
