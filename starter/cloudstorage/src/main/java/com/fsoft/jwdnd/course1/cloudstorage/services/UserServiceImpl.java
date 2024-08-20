package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        user = userRepository.findByUserName(username);
        return user;
    }
}
