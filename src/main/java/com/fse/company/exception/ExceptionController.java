package com.fse.company.exception;

import com.fse.company.model.ErrorMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<Object> handleError(CompanyAlreadyExistsException exception) {
        ErrorMsg err = new ErrorMsg(404, exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyTurnoverException.class)
    public ResponseEntity<Object> handleError(CompanyTurnoverException exception) {
        ErrorMsg err = new ErrorMsg(404, exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldsMissingException.class)
    public ResponseEntity<Object> handleError(FieldsMissingException exception) {
        ErrorMsg err = new ErrorMsg(404, exception.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}

