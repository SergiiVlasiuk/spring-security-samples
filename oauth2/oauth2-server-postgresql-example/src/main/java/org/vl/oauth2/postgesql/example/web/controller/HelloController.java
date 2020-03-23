package org.vl.oauth2.postgesql.example.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("authorized")
    @PostAuthorize("hasRole('CLIENT')")
    public String authorizedHello() {
        return "authorized hello";
    }

    @GetMapping("me")
//    public Principal me(@CurrentSecurityContext(expression = "authentication.principal") Principal security) {
    public Object me(@CurrentSecurityContext Object security) {
        return security;
    }
}
