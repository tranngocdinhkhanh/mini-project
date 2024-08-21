package com.fsoft.jwdnd.course1.cloudstorage.controller;

import com.fsoft.jwdnd.course1.cloudstorage.dto.UserDto;
import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.services.FileService;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.EncryptionService;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.HashService;
import com.fsoft.jwdnd.course1.cloudstorage.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HashService hashService;

    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "notification", required = false) String notification) {
        if (notification != null) {
            model.addAttribute("notification", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid @ModelAttribute UserDto userDto, Model model) {
        if (userService.findByUsername(userDto.getUserName()) != null) {
            model.addAttribute("notification", "user already exists");
            return "signup";
        } else {
            User user = new User();
            user.setUsername(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            userService.save(user);
            model.addAttribute("notification", "added user successfully");
            return "login";
        }
    }

    @GetMapping(value = "/home")
    public String home(@ModelAttribute(name = "userId") int userId, Model model) {
        List<File> fileList = fileService.getAllFileByUserId(userId);
        model.addAttribute("fileList", fileList);
        return "home";
    }
}
