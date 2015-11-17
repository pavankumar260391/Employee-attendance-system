package com.bas.employee.dao.entity;

import java.util.Date;

public class FacultyLeaveApprovalEntity {
	
	private int requestID;
	private String name;
	private String empNo;
	private Date leaveFrom;
	private Date leaveTo;
	private int totalDays;
	private String leaveType;
	private String purpose;
	private String managerApproval;
	private String hrApproval;
	private String mobile;
	private String reportingManager;
	private int employee_id;
	private int manager_id;
	private String hr_Manager_ID;
	private String reason;
	private String address;
	private String cCTo;
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public Date getLeaveFrom() {
		return leaveFrom;
	}
	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}
	public Date getLeaveTo() {
		return leaveTo;
	}
	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getManagerApproval() {
		return managerApproval;
	}
	public void setManagerApproval(String managerApproval) {
		this.managerApproval = managerApproval;
	}
	public String getHrApproval() {
		return hrApproval;
	}
	public void setHrApproval(String hrApproval) {
		this.hrApproval = hrApproval;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReportingManager() {
		return reportingManager;
	}
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getHr_Manager_ID() {
		return hr_Manager_ID;
	}
	public void setHr_Manager_ID(String hr_Manager_ID) {
		this.hr_Manager_ID = hr_Manager_ID;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getcCTo() {
		return cCTo;
	}
	public void setcCTo(String cCTo) {
		this.cCTo = cCTo;
	}
	@Override
	public String toString() {
		return "FacultyLeaveApprovalEntity [requestID=" + requestID + ", name="
				+ name + ", empNo=" + empNo + ", leaveFrom=" + leaveFrom
				+ ", leaveTo=" + leaveTo + ", totalDays=" + totalDays
				+ ", leaveType=" + leaveType + ", purpose=" + purpose
				+ ", managerApproval=" + managerApproval + ", hrApproval="
				+ hrApproval + ", mobile=" + mobile + ", reportingManager="
				+ reportingManager + ", employee_id=" + employee_id
				+ ", manager_id=" + manager_id + ", hr_Manager_ID="
				+ hr_Manager_ID + ", reason=" + reason + ", address=" + address
				+ ", cCTo=" + cCTo + "]";
	}
	
}
