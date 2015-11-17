package com.bas.admin.web.controller.form;


public class MessagePost {

	private String subject;
	private String category;
	private String message;
	byte[] image;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MessagePostDao [subject=" + subject + ", category=" + category
				+ ", message=" + message + "]";
	}
	
}
