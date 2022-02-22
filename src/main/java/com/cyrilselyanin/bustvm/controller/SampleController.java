package com.cyrilselyanin.bustvm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/anonymous")
    public String getAnonymousInfo() {
        return "Anonymous";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUserInfo() {
        return "user info";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminInfo() {
        return "admin info";
    }

    @GetMapping("/me")
    public Object getMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder sb = new StringBuilder();
        sb.append("getName: %s\n".formatted(authentication.getName()));
        sb.append("getAuthorities: %s\n".formatted(authentication.getAuthorities().toString()));
        sb.append("getCredentials: %s\n".formatted(authentication.getCredentials().toString()));
        sb.append("getPrincipal: %s\n".formatted(authentication.getPrincipal().toString()));
        sb.append("getDetails: %s\n".formatted(authentication.getDetails()));
        sb.append("\n\n");

        var r = authentication.getAuthorities().stream()
                        .filter(a -> {
                            System.out.println(a.toString());
                            return a.getAuthority().equals("ROLE_USER");
                        })
                        .findFirst();

        if (r.isPresent()) {
            sb.append("role: %s\n".formatted(r.get()));
        }
        sb.append("user id: %s\n".formatted(authentication.getName()));


        return sb.toString();
//        return authentication.getName();
    }
}
