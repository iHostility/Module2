package com.hostile.module2.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hostile.module2.dto.auth.RegistrationDTO;
import com.hostile.module2.service.UserService;
import com.hostile.module2.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UsersService usersService;
    private final UserService userService;

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<String> register(@Valid RegistrationDTO userForm, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            userService.validationError(result);
        }
        userService.registerUser(userForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
