package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ltp.gradesubmission.exception.CourseWithNotEnrolledStudentsException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.exception.ErrorResponse;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotEnrolledException;
import com.ltp.gradesubmission.exception.StudentNotEnrolledInAnyCourseException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request")); 
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Cannot delete non-existing resource")); 
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityNotFoundException.class, GradeNotFoundException.class, StudentNotEnrolledException.class, StudentNotEnrolledInAnyCourseException.class, CourseWithNotEnrolledStudentsException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

}


