package com.hostile.module2.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostile.module2.dto.errors.Error;
import com.hostile.module2.dto.errors.ErrorDTO;
import com.hostile.module2.errors.CustomApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomApiException.class})
    public ResponseEntity<?> sendResponse(CustomApiException customApiException) throws JsonProcessingException {
        ErrorDTO dto = new ErrorDTO(new Error(
                customApiException.getStatus().value(),
                customApiException.getMessage(),
                customApiException.getErrors()));
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(dto), new HttpHeaders(), customApiException.getStatus());
    }
}
