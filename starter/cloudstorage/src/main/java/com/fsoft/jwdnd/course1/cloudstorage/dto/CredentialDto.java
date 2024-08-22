package com.fsoft.jwdnd.course1.cloudstorage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialDto {
    private int id;

    private String url;

    private String userName;

    private String password;
}
