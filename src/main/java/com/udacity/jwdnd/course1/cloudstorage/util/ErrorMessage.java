package com.udacity.jwdnd.course1.cloudstorage.util;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ErrorMessage {

    // Error messages for User model.
    public static final String USERNAME_REQUIRED_MESSAGE = "Username is required!";
    public static final String PASSWORD_REQUIRED_MESSAGE = "Password is required!";
    public static final String FIRSTNAME_REQUIRED_MESSAGE = "Firstname is required!";
    public static final String LASTNAME_REQUIRED_MESSAGE = "Lastname is required!";
    public static final String FILE_NAME_EXIST_MESSAGE = "File's name is existed!";

    // Error messages for Note model.
    public static final String TITLE_REQUIRED_MESSAGE = "Title is required!";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "Description is required!";

    // Error messages for Credential model.
    public static final String URL_NOT_VALID = "URL is not valid!";

    public static final String FILE_SIZE_LIMIT = "File's size must more than 0, and less than ";
}
