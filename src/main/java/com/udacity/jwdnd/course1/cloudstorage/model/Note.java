package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Valid
public class Note {

	private Integer id;
	@NotEmpty(message = ErrorMessage.TITLE_REQUIRED_MESSAGE)
	private String title;
	@NotEmpty(message = ErrorMessage.DESCRIPTION_REQUIRED_MESSAGE)
	private String description;
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Note() {
		super();
	}

}
