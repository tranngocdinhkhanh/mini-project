package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllByUserId(int userId);
    void addNote(Note note,String username);
    void deleteById(int id);
}
