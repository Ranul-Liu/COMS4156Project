package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class  AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/token")
    public String token(Authentication authentication){
        LOG.debug("Token requested for client:'{}'",authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token is {} ",token);
        return token;
    }


}
