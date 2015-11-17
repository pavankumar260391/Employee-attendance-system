package com.bas.admin.web.controller.form;

import java.util.Date;

import com.bas.common.util.DateUtils;

public class FacultyMonthlyAttendanceStatusVO implements Comparable<FacultyMonthlyAttendanceStatusVO> {

	private int fid;
	private String name;
	private String fatherName;
	private String status;
	private String present;
	private String department;
	private String designation;
	private String cdate;
	private String detail;
	private String intime;
	private String intimestatus;
	private String outtime;
	private String outtimestatus;
	
	private Date holidayDate;
	private String working;
	private String holiday;
	private String weekend;
	private String description;
	private String comment;
	private String holidayType;
	
	private String InStatusColor;
	private String OutStatusColor;
	private String color;
	
	int noOfDaysWorked;
	int noOfHolidays;
	int noOfApprovedLeaves;
	int noOfLwp;
	
	public int getNoOfDaysWorked() {
		return noOfDaysWorked;
	}

	public void setNoOfDaysWorked(int noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}

	public int getNoOfHolidays() {
		return noOfHolidays;
	}

	public void setNoOfHolidays(int noOfHolidays) {
		this.noOfHolidays = noOfHolidays;
	}

	public int getNoOfApprovedLeaves() {
		return noOfApprovedLeaves;
	}

	public void setNoOfApprovedLeaves(int noOfApprovedLeaves) {
		this.noOfApprovedLeaves = noOfApprovedLeaves;
	}

	public int getNoOfLwp() {
		return noOfLwp;
	}

	public void setNoOfLwp(int noOfLwp) {
		this.noOfLwp = noOfLwp;
	}

	
	@Override
	public int compareTo(FacultyMonthlyAttendanceStatusVO o) {
		FacultyMonthlyAttendanceStatusVO x = (FacultyMonthlyAttendanceStatusVO) o;
		int p = 0;
	
		Date currentDate = DateUtils.convertStringIntoDateYYYYMMDD(this.cdate);
		Date anotherDate = DateUtils.convertStringIntoDateYYYYMMDD(x.cdate);
		
		if(currentDate != null && anotherDate != null){
			p = currentDate.compareTo(anotherDate);
		}
		  return p;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getInStatusColor() {
		return InStatusColor;
	}
	public void setInStatusColor(String inStatusColor) {
		InStatusColor = inStatusColor;
	}
	public String getOutStatusColor() {
		return OutStatusColor;
	}
	public void setOutStatusColor(String outStatusColor) {
		OutStatusColor = outStatusColor;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getIntimestatus() {
		return intimestatus;
	}
	public void setIntimestatus(String intimestatus) {
		this.intimestatus = intimestatus;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	public String getOuttimestatus() {
		return outtimestatus;
	}
	public void setOuttimestatus(String outtimestatus) {
		this.outtimestatus = outtimestatus;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getWorking() {
		return working;
	}
	public void setWorking(String working) {
		this.working = working;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getWeekend() {
		return weekend;
	}
	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	
	
}
