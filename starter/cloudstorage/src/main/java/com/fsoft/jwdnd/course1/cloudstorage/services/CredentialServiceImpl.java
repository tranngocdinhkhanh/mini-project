package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.dto.CredentialDto;
import com.fsoft.jwdnd.course1.cloudstorage.entity.Credential;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.repository.CredentialRepository;
import com.fsoft.jwdnd.course1.cloudstorage.repository.UserRepository;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Credential> getAllByUserId(int userId) {
        return credentialRepository.findByUserId(userId);
    }

    @Override
    public List<CredentialDto> getAllDtoByUserId(int userId) {
        List<Credential> credentials = credentialRepository.findByUserId(userId);
        List<CredentialDto> credentialDtoList = new ArrayList<>();
        for (Credential credential : credentials) {
            CredentialDto credentialDto = new CredentialDto();
            credentialDto.setId(credential.getId());
            credentialDto.setUrl(credential.getUrl());
            credentialDto.setUserName(credential.getUserName());
            credentialDto.setPassword(encryptionService.decryptValue(credential.getPassword(), "mySecretKey12345"));
            credentialDtoList.add(credentialDto);
        }
        return credentialDtoList;
    }

    @Override
    public void add(Integer id, String url, String username, String password, String name) {
        Credential credential = new Credential();
        User users = userRepository.findByUsername(name);
        String passwordEn = encryptionService.encryptValue(password, "mySecretKey12345");
        credential.setPassword(passwordEn);
        credential.setUser(users);
        credential.setUrl(url);
        credential.setUserName(username);
        credential.setId(id);
        credentialRepository.save(credential);
    }

    @Override
    public void delete(int id) {
        credentialRepository.deleteById(id);
    }
}
