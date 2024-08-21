package com.fsoft.jwdnd.course1.cloudstorage.services;

import com.fsoft.jwdnd.course1.cloudstorage.entity.User;
import com.fsoft.jwdnd.course1.cloudstorage.repository.UserRepository;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.EncryptionService;
import com.fsoft.jwdnd.course1.cloudstorage.services.secure.HashService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashService hashService;
    @Autowired
    private EncryptionService encryptionService;

    @Override
    public void save(User user) {
        String salt = generateSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), salt);
        String encryptedSalt = encryptionService.encryptValue(salt, "your-encryption-key");
        user.setSalt(encryptedSalt);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private String generateSalt() {
        return UUID.randomUUID().toString();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
//                .build();
//    }
}
