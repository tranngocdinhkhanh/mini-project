package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
