package com.fsoft.jwdnd.course1.cloudstorage.controller;

import com.fsoft.jwdnd.course1.cloudstorage.dto.CredentialDto;
import com.fsoft.jwdnd.course1.cloudstorage.dto.UserDto;
import com.fsoft.jwdnd.course1.cloudstorage.entity.Credential;
import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.entity.Note;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.services.CredentialService;
import com.fsoft.jwdnd.course1.cloudstorage.services.FileService;
import com.fsoft.jwdnd.course1.cloudstorage.services.NoteService;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.EncryptionService;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.HashService;
import com.fsoft.jwdnd.course1.cloudstorage.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private HashService hashService;

    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "notification", required = false) String notification) {
        if (notification != null) {
            model.addAttribute("notification", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/getSignup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping(value = "/postSignup")
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
    public String home(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        List<File> fileList = fileService.getAllByUserId(user.getId());
        model.addAttribute("fileList", fileList);
        List<Note> noteList = noteService.getAllByUserId(user.getId());
        model.addAttribute("noteList", noteList);
        List<CredentialDto> credentialDtoList = credentialService.getAllDtoByUserId(user.getId());
        model.addAttribute("credentialDtoList", credentialDtoList);
        return "home";
    }
}
