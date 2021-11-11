package com.fse.company.exception;

public class CompanyTurnoverException extends Exception {
    public CompanyTurnoverException() {
        super("Company turnover must be greater than 100000000!");
    }
}
