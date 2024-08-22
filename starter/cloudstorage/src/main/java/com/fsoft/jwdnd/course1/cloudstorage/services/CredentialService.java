package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.dto.CredentialDto;
import com.fsoft.jwdnd.course1.cloudstorage.entity.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> getAllByUserId(int userId);
    List<CredentialDto> getAllDtoByUserId(int userId);
     void add(Integer id, String url, String username, String password,String name);
     void delete(int id);
}
