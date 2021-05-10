package com.udacity.jdnd.course1.service;

import com.udacity.jdnd.course1.mapper.UserMapper;
import com.udacity.jdnd.course1.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null,
                user.getUsername(),
                encodedSalt,
                hashedPassword,
                user.getFirstName(),
                user.getLastName()));
    }

    public boolean authenticate(String username, String password) {
        User u = getUser(username);
        if (u == null) {
            return false;
        }
        String hashedPassword = hashService.getHashedValue(password, u.getSalt());
        return hashedPassword.equals(u.getPassword());
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

}

