package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserService userService;
    @Override
    public List<File> getAllByUserId(int userId) {
        return fileRepository.findByUserId(userId);
    }

    @Override
    public void add(MultipartFile fileUpload, String username) throws Exception {
        File file = new File();
        file.setFileName(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        file.setFileSize(String.valueOf(fileUpload.getSize()));
        file.setFileData(fileUpload.getBytes());
        file.setId(0);
        User user = userService.findByUsername(username);
        file.setUser(user);
        fileRepository.save(file);
    }

    @Override
    public File getById(int id) {
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        fileRepository.deleteById(id) ;
    }
}
