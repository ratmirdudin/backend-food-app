package com.ratmirdudin.backendfoodapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationHeaderException extends RuntimeException {
    private AuthorizationHeaderException() {
        super("Authorization header is not correct");
    }

    public AuthorizationHeaderException(String message) {
        super(message);
    }
}
