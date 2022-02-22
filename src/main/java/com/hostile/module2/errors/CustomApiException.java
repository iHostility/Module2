package com.hostile.module2.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data @AllArgsConstructor
public class CustomApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

}
