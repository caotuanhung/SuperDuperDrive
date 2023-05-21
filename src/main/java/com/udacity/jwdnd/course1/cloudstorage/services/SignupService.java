package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SignupService {

    private EncryptionService encryptionService;
    private HashService hashService;
    private UserMapper userMapper;

    public SignupService(EncryptionService encryptionService,HashService hashService, UserMapper userMapper) {
        this.encryptionService = encryptionService;
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public Map<Object, Object> saveOne(User user) {
        Map<Object, Object> resultDetails = new HashMap<>();
        User foundUser = userMapper.selectOneByUsername(user.getUsername());
        if (Objects.nonNull(foundUser)) {
            resultDetails.put("message", "Username is existed!");
            resultDetails.put("id", -1);
        } else {
            formatUserBeforeSave(user);
            Integer id = userMapper.insertOne(user);
            if (Objects.nonNull(id) && id > 0) {
                resultDetails.put("message", "You successfully signed up!");
                resultDetails.put("id", id);
            }
        }
        return resultDetails;
    }

    private void formatUserBeforeSave(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);
    }
}
