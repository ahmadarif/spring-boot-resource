package com.ahmadarif.resource.controller;

import com.ahmadarif.resource.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ARIF on 24-Feb-17.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/checkToken")
    public Object checkToken(@RequestParam String access_token) {
        return authService.checkToken(access_token);
    }

    @GetMapping("/user")
    public Object checkToken(Authentication authentication) {
        return authentication.getPrincipal();
    }

}