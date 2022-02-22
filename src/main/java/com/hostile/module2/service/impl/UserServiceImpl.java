package com.hostile.module2.service.impl;

import com.hostile.module2.config.jwt.JwtProvider;
import com.hostile.module2.dto.auth.LoginDTO;
import com.hostile.module2.dto.auth.RegistrationDTO;
import com.hostile.module2.dto.auth.TokenDTO;
import com.hostile.module2.entity.User;
import com.hostile.module2.errors.CustomApiException;
import com.hostile.module2.repo.UserRepository;
import com.hostile.module2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public void registerUser(RegistrationDTO dto) {
        User user = repository.findByUsername(dto.getPhone());
        if (user == null) {
            user = new User();
            user.setFirstName(dto.getFirst_name());
            user.setLastName(dto.getLast_name());
            user.setPassword(encoder.encode(dto.getPassword()));
            user.setUsername(dto.getPhone());
            user.setDocumentNumber(dto.getDocument_number());
            user.setCreatedAt(new Date());
            repository.save(user);
        } else {
            Map<String, String> err = new HashMap<>();
            err.put("phone", "Такой пользователь уже зарегестрирован!");
            throw new CustomApiException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Validation error", err);
        }
    }

    public TokenDTO loginUser(LoginDTO dto){
        User user = repository.findByUsername(dto.getPhone());
        if (user != null && user.getPassword().equals(dto.getPassword())){
            TokenDTO apiToken = new TokenDTO(jwtProvider.generateToken(user.getUsername()));
            user.setAccessToken(apiToken.getToken());
            user.setRole("ROLE_USER");
            repository.save(user);
            return apiToken;
        }
        loginError();
        return null;
    }

    public void validationError(BindingResult result){
        Map<String, String> invalidFields = new HashMap<>();
        result.getFieldErrors().forEach(er -> {
            invalidFields.put(er.getField(), er.getDefaultMessage());
        });
        throw new CustomApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Validation error", invalidFields);
    }

    public void loginError(){
        Map<String, String> invalidFields = new HashMap<>();
        invalidFields.put("phone","Phone or password are incorrect!");
        throw new CustomApiException(HttpStatus.UNAUTHORIZED, "Unauthorized", invalidFields);
    }
}
