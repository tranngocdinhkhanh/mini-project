package com.fsoft.jwdnd.course1.cloudstorage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credentials", schema = "miniprj")
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id", nullable = false)
    private Integer id;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "user_name", length = 30)
    private String userName;

    @Column(name = "credential_key")
    private String credentialKey;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}