package com.bas.employee.web.controller.form;

import java.util.Date;

public class PhotoGallery {

	private int eventId;
	private String event_description;
	//private Date eventDate;
	public byte[] photo;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEvent_description() {
		return event_description;
	}
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
	/*
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	*/
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "PhotoGallery [eventId=" + eventId + ", event_description="
				+ event_description + "]";
	}
	
	
}
