package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

public class Credential {

	private Integer id;
	@URL(message = ErrorMessage.URL_NOT_VALID)
	private String url;
	@NotEmpty(message = ErrorMessage.USERNAME_REQUIRED_MESSAGE)
	private String username;
	private String key;
	@NotEmpty(message = ErrorMessage.PASSWORD_REQUIRED_MESSAGE)
	private String password;
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Credential() {
		super();
	}

}
