package com.bas.employee.dao.entity;

import java.sql.Date;

public class FacultyWorkingDaysEntity {

	int empid;
	String month;
	int noOfDaysWorked;
	int noOfApprovedLeaves;
	int totalworkingdays;
	int noOfHolidays;
	int salarypaid;
	String department;
	String description;
	Date dom;
	Date doe;
	String modifyby;
	int noOfLwp;
	
	public int getNoOfLwp() {
		return noOfLwp;
	}
	public void setNoOfLwp(int noOfLwp) {
		this.noOfLwp = noOfLwp;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getNoOfDaysWorked() {
		return noOfDaysWorked;
	}
	public void setNoOfDaysWorked(int noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}
	public int getNoOfApprovedLeaves() {
		return noOfApprovedLeaves;
	}
	public void setNoOfApprovedLeaves(int noOfApprovedLeaves) {
		this.noOfApprovedLeaves = noOfApprovedLeaves;
	}
	public int getTotalworkingdays() {
		return totalworkingdays;
	}
	public void setTotalworkingdays(int totalworkingdays) {
		this.totalworkingdays = totalworkingdays;
	}
	public int getNoOfHolidays() {
		return noOfHolidays;
	}
	public void setNoOfHolidays(int noOfHolidays) {
		this.noOfHolidays = noOfHolidays;
	}
	public int getSalarypaid() {
		return salarypaid;
	}
	public void setSalarypaid(int salarypaid) {
		this.salarypaid = salarypaid;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDom() {
		return dom;
	}
	public void setDom(Date dom) {
		this.dom = dom;
	}
	public Date getDoe() {
		return doe;
	}
	public void setDoe(Date doe) {
		this.doe = doe;
	}
	public String getModifyby() {
		return modifyby;
	}
	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}
	
}
