package org.vl.oauth2.inmemory.example.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("authorized-read")
    @PreAuthorize("#oauth2.hasScope('AUTHORIZED_READ')")
    public String withAuthorityHello() {
        return "AUTHORIZED_READ scope restriction";
    }

    @GetMapping("me")
    public Principal me(Principal security) {
//    public Object me(@CurrentSecurityContext Object security) {
        return security;
    }
}
