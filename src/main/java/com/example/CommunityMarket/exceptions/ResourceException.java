package com.example.CommunityMarket.exceptions;

// Resource exception will be used to throw generic exceptions to give client hint
// why the request is wrong
public class ResourceException extends Exception{
        public ResourceException(String str) {
            super(str);
        }
}

