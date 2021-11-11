package com.fse.company.exception;

public class CompanyAlreadyExistsException extends Exception {
    public CompanyAlreadyExistsException() {
        super("Company with this company code already exists!");
    }
}
