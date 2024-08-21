package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;

import java.util.List;

public interface FileService {
    List<File> getAllFileByUserId(int userId);
}
