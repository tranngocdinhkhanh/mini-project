package com.fsoft.jwdnd.course1.cloudstorage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notes", schema = "miniprj")
public class Note {
    @Id
    @Column(name = "note_id", nullable = false)
    private Integer id;

    @Column(name = "note_title", length = 20)
    private String noteTitle;

    @Column(name = "note_description", length = 1000)
    private String noteDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}