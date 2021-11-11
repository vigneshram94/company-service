package com.fse.company.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{



//    @ExceptionHandler(ForbiddenTargetException.class)
//    public ResponseEntity<Object> handleError(ForbiddenTargetException exception){
//        return new ResponseEntity<>("FORBIDDEN",HttpStatus.FORBIDDEN);
//    }


    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<Object> handleError(CompanyAlreadyExistsException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyTurnoverException.class)
    public ResponseEntity<Object> handleError(CompanyTurnoverException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ErrorModel> handleError(JwtException exception){
//
//        return new ResponseEntity<>( ErrorModel.build("Invalid Input",exception.getMessage()),HttpStatus.UNAUTHORIZED);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(
//            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        return ResponseEntity
//                .badRequest()
//                .body( ErrorModel.build("Invalid Request Body", ex.getCause().getMessage()));
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<String> errorMessages = ex.getAllErrors().stream().map(error-> error.getDefaultMessage()).collect(Collectors.toList());
//
//        return ResponseEntity
//                .badRequest()
//                .body( new ErrorModel("Invalid Request Body",ex.getMessage(), errorMessages));
//    }

}

