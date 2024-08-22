package com.fsoft.jwdnd.course1.cloudstorage.controller;

import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.services.CredentialService;
import com.fsoft.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    @PostMapping("/postSaveCredential")
    public String getCredential(@RequestParam("url") String url,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("id") Integer credentialId,
                                Authentication authentication, Model model) {
        String name = authentication.getName();
        try {
            credentialService.add(credentialId, url, username, password, name);
        } catch (Exception e) {
            model.addAttribute("uploadError", "Failed to upload file: " + e.getMessage());
            return "redirect:/user/home";
        }
        return "redirect:/user/home";
    }

    @GetMapping(value = "/postDelete/{id}")
    public String deleteCredential(@PathVariable("id") Integer id){
        credentialService.delete(id);
        return "redirect:/user/home";
    }
}
