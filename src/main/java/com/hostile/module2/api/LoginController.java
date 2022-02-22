package com.hostile.module2.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostile.module2.dto.auth.Data;
import com.hostile.module2.dto.auth.LoginDTO;
import com.hostile.module2.dto.auth.TokenDTO;
import com.hostile.module2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final ObjectMapper om;

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<String> login(@Valid LoginDTO userForm, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            userService.validationError(result);
        }

        TokenDTO accessToken = userService.loginUser(userForm);
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Bearer " + accessToken.getToken());
        return new ResponseEntity<>(om.writeValueAsString(new Data(accessToken.getToken())), headers, HttpStatus.OK);
    }
}
