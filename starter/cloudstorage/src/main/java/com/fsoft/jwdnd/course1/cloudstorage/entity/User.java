package com.fsoft.jwdnd.course1.cloudstorage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "miniprj")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_name", length = 20)
    private String username;

    @Column(name = "salt", length = 1000)
    private String salt;

    @Column(name = "password", length = 1000)
    private String password;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

}