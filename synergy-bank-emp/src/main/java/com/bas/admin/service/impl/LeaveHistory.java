package com.bas.admin.service.impl;

import java.util.Arrays;
import java.util.Date;

public class LeaveHistory {

	private int id;
	private String name;
	private String designation;
	private String department;
//	private int lwp;  // number of leaves without pay
//	private int nodw; // number of days worked
	private int noleaves;  // number of leaves without pay
	private int daysworked; // number of days worked
	private byte[] image;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNoleaves() {
		return noleaves;
	}
	public void setNoleaves(int noleaves) {
		this.noleaves = noleaves;
	}
	public int getDaysworked() {
		return daysworked;
	}
	public void setDaysworked(int daysworked) {
		this.daysworked = daysworked;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public LeaveHistory() {
		// TODO Auto-generated constructor stub
	}
	

	public LeaveHistory(int id, String name, String designation,
			String department, int noleaves, int daysworked, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.department = department;
		this.noleaves = noleaves;
		this.daysworked = daysworked;
		this.image = image;
	}
	@Override
	public String toString() {
		return "LeaveHistory [id=" + id + ", name=" + name + ", designation="
				+ designation + ", department=" + department + ", noleaves="
				+ noleaves + ", daysworked=" + daysworked + ", image="
				+ Arrays.toString(image) + "]";
	}

}
