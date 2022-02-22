package com.hostile.module2.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationDTO {
    @NotEmpty(message = "Не должно быть пустым")
    private String first_name;
    @NotEmpty(message = "Не должно быть пустым")
    private String last_name;
    @NotEmpty(message = "Не должно быть пустым")
    private String phone;
    @Pattern(regexp = "[0-9]{10}", message = "Должно содержать 10 цифр")
    private String document_number;
    @NotEmpty(message = "Не должно быть пустым")
    private String password;
}
