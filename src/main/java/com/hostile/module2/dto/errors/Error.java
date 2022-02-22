package com.hostile.module2.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Error {
    private Integer code;
    private String message;
    private Map<String, String> errors;
}
