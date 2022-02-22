package com.hostile.module2.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @NotEmpty(message = "Не должно быть пустым")
    private String phone;
    @NotEmpty(message = "Не должно быть пустым")
    private String password;
}
