package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

	private Integer id;
	private String name;
	private String contentType;
	private String size;
	private byte[] data;
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public File() {
		super();
	}

	public File(String name, String contentType, String size, byte[] data, Integer userId) {
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.data = data;
		this.userId = userId;
	}
}
