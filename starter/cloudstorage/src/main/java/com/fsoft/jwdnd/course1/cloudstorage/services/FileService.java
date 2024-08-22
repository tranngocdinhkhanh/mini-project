package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<File> getAllByUserId(int userId);

    void add(MultipartFile fileUpload, String username) throws Exception;

    public File getById(int id);

    public void deleteById(int id);
}
