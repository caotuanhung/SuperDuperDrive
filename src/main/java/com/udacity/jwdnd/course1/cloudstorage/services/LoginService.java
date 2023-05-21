package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {
    private UserMapper userMapper;
    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public boolean isValidLoginInput(User user, BindingResult bindingResult) {
        boolean result;
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ObjectError> concernErrors = allErrors.stream().filter(error ->
                ErrorMessage.USERNAME_REQUIRED_MESSAGE.equals(error.getDefaultMessage())
                        || ErrorMessage.PASSWORD_REQUIRED_MESSAGE.equals(error.getDefaultMessage())).collect(Collectors.toList());
        if (concernErrors.isEmpty()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    public User findOneByUsername(String username) {
        return userMapper.selectOneByUsername(username);
    }
}
