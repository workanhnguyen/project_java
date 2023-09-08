package com.example.server.controllers;

import com.example.server.components.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class LetterController {
    @Autowired
    private Environment env;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/letters")
    public String index(Model model, @RequestParam Map<String, String> params, Principal loggedInUser, RedirectAttributes redirectAttributes) {
        String dynamicToken = jwtService.generateTokenLogin(loggedInUser.getName());
        model.addAttribute("authToken", dynamicToken);

        if (params.isEmpty()) {
            // If not present, add the default value of "page=1" to the parameters
            params.put("page", "1");

            // Redirect to the same URL with the "page" parameter added
            redirectAttributes.addAttribute("page", "1");
            return "redirect:/letters";
        }

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

        return "listLetter";
    }
}