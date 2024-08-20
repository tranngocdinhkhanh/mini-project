package com.fsoft.jwdnd.course1.cloudstorage.repository;

import com.fsoft.jwdnd.course1.cloudstorage.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
}
