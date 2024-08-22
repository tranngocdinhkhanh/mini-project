package com.fsoft.jwdnd.course1.cloudstorage.controller;

import com.fsoft.jwdnd.course1.cloudstorage.entity.Note;
import com.fsoft.jwdnd.course1.cloudstorage.services.FileService;
import com.fsoft.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/postSaveNote")
    public String saveNote(@RequestParam("noteTitle") String noteTitle,
                           @RequestParam("noteDescription") String noteDescription,
                           @RequestParam("noteId") Integer noteId,
                           Authentication authentication, Model model) {
        String username = authentication.getName();
        try {
            Note note = new Note();
            note.setNoteTitle(noteTitle);
            note.setNoteDescription(noteDescription);
            note.setId(noteId);
            noteService.addNote(note, username);
        } catch (Exception e) {
            model.addAttribute("uploadError", "Failed to upload file: " + e.getMessage());
            return "redirect:/user/home";
        }
        return "redirect:/user/home";
    }

    @GetMapping(value = "/getDelete/{id}")
    public String deleteNote(@PathVariable("id") Integer id){
        noteService.deleteById(id);
        return "redirect:/user/home";
    }
}
