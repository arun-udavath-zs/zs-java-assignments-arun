package com.zs.assignment11.advice;

import com.zs.assignment11.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionalHandler {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> handleProductNotFoundException(ProductNotFoundException exception) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        errorMap.put("TimeStamp", LocalDateTime.now().toString());
        return errorMap;
    }
}
