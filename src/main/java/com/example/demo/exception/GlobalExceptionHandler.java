package com.example.demo.exception;

import com.example.demo.payload.ApiResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ApiResponseBody<String>> handleHttpException(HttpException ex) {
        return new ResponseEntity<>(
                new ApiResponseBody<>(
                        ex.getMessage(),
                        ex.getStatusCode()
                ),
                ex.getStatusCode()
        );
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseBody<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiResponseBody<Map<String, String>> res = new ApiResponseBody<>(
                "Validation Failed",
                HttpStatus.BAD_REQUEST,
                errors
        );
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponseBody<String>> handleResourceNotFoundException(
            NoResourceFoundException ex
    ) {
        ApiResponseBody<String> res = new ApiResponseBody<>("Endpoint Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseBody<Void>> handleMessageNotReadableException(
            HttpMessageNotReadableException ex
    ) {
        ApiResponseBody<Void> res = new ApiResponseBody<>(
                "Required Request body is missing",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseBody<Void>> handleMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex
    ) {
        ApiResponseBody<Void> res = new ApiResponseBody<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseBody<Void>> handleOtherException(Exception ex) {
        ApiResponseBody<Void> res = new ApiResponseBody<>(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
