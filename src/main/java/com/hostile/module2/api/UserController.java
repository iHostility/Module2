package com.hostile.module2.api;

import com.hostile.module2.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<String> getUserInfo(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/booking")
    public ResponseEntity<String> getUserBooking(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }
}
