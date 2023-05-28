package com.everflow.check_and_talk.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/home")
    public static String index(Model model, @AuthenticationPrincipal OAuth2User userInfo) {
        // System.out.println(userInfo.getName());
        // System.out.println(userInfo.getAttributes());
        return "index";
    }
}
