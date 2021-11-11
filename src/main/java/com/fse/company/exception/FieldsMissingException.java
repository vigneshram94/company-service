package com.fse.company.exception;

public class FieldsMissingException extends Exception {
    public FieldsMissingException() {
        super("Enter all fields!");
    }
}