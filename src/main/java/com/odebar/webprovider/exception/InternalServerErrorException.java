package com.odebar.webprovider.exception;

import javax.servlet.http.HttpServletResponse;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
