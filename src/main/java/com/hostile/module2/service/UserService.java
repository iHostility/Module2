package com.hostile.module2.service;

import com.hostile.module2.dto.auth.LoginDTO;
import com.hostile.module2.dto.auth.RegistrationDTO;
import com.hostile.module2.dto.auth.TokenDTO;
import org.springframework.validation.BindingResult;

public interface UserService {
    void registerUser(RegistrationDTO dto);
    void validationError(BindingResult result);
    TokenDTO loginUser(LoginDTO dto);
}
