package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;

import javax.validation.constraints.NotEmpty;

public class User {

    private Integer id;

    @NotEmpty(message = ErrorMessage.USERNAME_REQUIRED_MESSAGE)
    private String username;

    private String salt;
    @NotEmpty(message = ErrorMessage.PASSWORD_REQUIRED_MESSAGE)
    private String password;

    @NotEmpty(message = ErrorMessage.FIRSTNAME_REQUIRED_MESSAGE)
    private String firstname;

    @NotEmpty(message = ErrorMessage.LASTNAME_REQUIRED_MESSAGE)
    private String lastname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
