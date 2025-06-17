package com.example.demo.exception;

import com.example.demo.payload.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<ApiResponse<String>> handleHttpException(HttpException ex) {
        return new ResponseEntity<>(
                new ApiResponse<>(
                        ex.getMessage(),
                        ex.getStatusCode()
                ),
                ex.getStatusCode()
        );
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiResponse<Map<String, String>> res = new ApiResponse<>(
                "Validation Failed",
                HttpStatus.BAD_REQUEST,
                errors
        );
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(
            NoResourceFoundException ex
    ) {
        ApiResponse<String> res = new ApiResponse<>("Endpoint Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleMessageNotReadableException(
            HttpMessageNotReadableException ex
    ) {
        ApiResponse<Void> res = new ApiResponse<>(
                "Required Request body is missing",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherException(Exception ex) {
        logger.error("Unknown exception caught: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
