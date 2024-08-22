package com.fsoft.jwdnd.course1.cloudstorage.controller;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.services.FileService;
import com.fsoft.jwdnd.course1.cloudstorage.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/postAddFile")
    public String saveFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) {
        String username = authentication.getName();
        try {
            fileService.add(fileUpload, username);
        } catch (Exception e) {
            model.addAttribute("uploadError", "Failed to upload file: " + e.getMessage());
            return "redirect:/user/home";
        }
        return "redirect:/user/home";
    }

    @GetMapping(value = "/getView/{id}")
    public void displayFile(@PathVariable Integer id, HttpServletResponse httpServletResponse) throws IOException {
        File fileStore = fileService.getById(id);
        httpServletResponse.setContentType(fileStore.getContentType());
        httpServletResponse.getOutputStream().write(fileStore.getFileData());
        httpServletResponse.getOutputStream().close();
    }

    @GetMapping(value = "/getDelete/{id}")
    public String deleteFile(@PathVariable("id") Integer id){
        fileService.deleteById(id);
        return "redirect:/user/home";
    }

}
