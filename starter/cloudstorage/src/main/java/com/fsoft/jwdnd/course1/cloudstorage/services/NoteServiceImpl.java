package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.Note;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.repository.NoteRepository;
import com.fsoft.jwdnd.course1.cloudstorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Note> getAllByUserId(int userId) {
        return noteRepository.findByUserId(userId);
    }

    @Override
    public void addNote(Note note, String username) {
        User users = userRepository.findByUsername(username);
        note.setUser(users);
        noteRepository.save(note);
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }
}
