package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import com.fsoft.jwdnd.course1.cloudstorage.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<File> getAllFileByUserId(int userId) {
        return fileRepository.findByUserId(userId);
    }
}
