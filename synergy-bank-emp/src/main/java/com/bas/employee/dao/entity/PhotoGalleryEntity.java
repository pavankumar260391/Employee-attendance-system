package com.bas.employee.dao.entity;

import java.util.Date;

public class PhotoGalleryEntity {

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
