package com.fsoft.jwdnd.course1.cloudstorage.dto;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class UserDto {

    private String userName;

    private String password;

    private String firstName;

    private String lastName;
}
