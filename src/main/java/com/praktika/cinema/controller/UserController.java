package com.praktika.cinema.controller;

import com.praktika.cinema.exception.user.UserAlreadyExistsException;
import com.praktika.cinema.security.Pojo.LoginRequest;
import com.praktika.cinema.security.Pojo.SingupRequest;
import com.praktika.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody LoginRequest loginRequest){
        try {
            return ResponseEntity.ok(userService.auth(loginRequest));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SingupRequest singupRequest){
        try{
            return ResponseEntity.ok(userService.create(singupRequest));
        }
        catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }
}
