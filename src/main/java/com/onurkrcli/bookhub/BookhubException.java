package com.onurkrcli.bookhub;

/**
 * Created by onur on 19/06/16.
 */
public class BookhubException extends Exception {
    public BookhubException() {
    }

    public BookhubException(String message) {
        super(message);
    }

    public BookhubException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookhubException(Throwable cause) {
        super(cause);
    }

    public BookhubException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
