package com.onurkrcli.bookhub.validation;

/**
 * Created by onur on 19/06/16.
 */
public interface Validator<T> {

    void  validate(T object) throws ValidationException;
}
