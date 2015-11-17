package com.bas.employee.service;

import java.util.Date;
import java.util.List;

import com.bas.admin.web.controller.form.EmployeeShowForm;
import com.bas.admin.web.controller.form.FaculityDailyAttendanceReportVO;
import com.bas.admin.web.controller.form.FacultyMonthlyAttendanceStatusVO;
import com.bas.admin.web.controller.form.HolidayEntryForm;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.FaculityTimeForm;
import com.bas.employee.web.controller.form.FacultyAttendStatusVO;
import com.bas.employee.web.controller.form.FacultyForm;
import com.bas.employee.web.controller.form.FacultyLeaveApprovalVO;
import com.bas.employee.web.controller.form.FacultySalaryMasterVO;
import com.bas.employee.web.controller.form.FacultyWorkingDaysVO;
import com.bas.employee.web.controller.form.ManualAttendanceVO;
import com.bas.employee.web.controller.form.ReportingManagerVO;

/**
 * 
 * @author Sid
 * 
 */
public interface BasFacultyService {
	public String persistFaculty(FacultyForm facultyForm);

	public String updateFaculty(FacultyForm facultyForm);

	public String deletetFaculty(String name);

	public FacultyForm findFacultyByName(String name);

	public List<FacultyForm> findAllFaculty();
	public List<EmployeeShowForm> findAllEmployee();

	public byte[] findPhotoByEmpId(String empid);

	public FaculityLeaveMasterVO findLeaveBalance(String empid);

	public List<FaculityLeaveMasterVO> findLeaveHistory(String empid);

	public List<FaculityDailyAttendanceReportVO> findAttendStatus(String fid, int monthValue);

	public List<FacultySalaryMasterVO> findSalaryHistory(String empid);

	public ManualAttendanceVO findEmployeeDataForAttendance(String empid);
	
	public String addEmployeeManulAttendance(ManualAttendanceVO manualAttendanceVO);

	public byte[] findEmpPhotoByName(String name);

	public String enterLeaveHistory(FaculityLeaveMasterVO faculityLeaveMasterVO);

	public List<FaculityLeaveMasterVO> findAllLeaveHistory();

	public List<FaculityLeaveMasterVO> findAllEmpDb();

	String deleteLeaveHistory(String name, String date);

	public List<FaculityLeaveMasterVO> findAllPendingLeaveHistory();

	void updateLeaveHistory(String empNo, String date, String lstatus);

	public List<FaculityLeaveMasterVO> getReportingManagerList();

	public List<FaculityLeaveMasterVO> getCCToList();

	public String enterRmLeaveHistory(FaculityLeaveMasterVO faculityLeaveMasterVO);

	public List<FaculityLeaveMasterVO> findAllRmPendingLeaveHistory();

	public FaculityLeaveMasterVO findLeaveAppData(String empid/*, String leaveMonth*/);

	public void addLeaveEntry(FaculityLeaveMasterVO faculityLeaveMasterVO);


	public String deleteAttendus(String employeeId, String attndDate);

	public List<FaculityDailyAttendanceReportVO> showAttendusReport(String date);

	public List<String> selectDepartments();

	public List<FaculityDailyAttendanceReportVO> showAttendusReportByDep(String date,
			String dep);

	public List<String> searchEmployee(String employeeName);

	public EmployeeShowForm findFacultyByNamespeci(String name);

	public String updateEmployee(FaculityDailyAttendanceReportVO dfaAttendStatusVO,
			String fid, String newdate);

	public String createEmployeeAccount(FacultyForm facultyForm);

	public List<FacultyLeaveApprovalVO> getLeaveApprovalForManager(int managerId);

	public String updateLeaveApprovalForManager(int requestID, String managerApproval);

	public ReportingManagerVO findReportingManagerByEmpId(String employeeId);

	public List<String> findRelation(String manager);

	public	String applyAddendumLeave(FaculityLeaveMasterVO faculityLeaveMasterVO);

	public List<String> selectDesignations();

	public List<String> selectReportingManagers();

	public List<String> searchEmployeeByManId(String employeeName);

	public List<String> searchId(String employeeId);

	public List<FaculityDailyAttendanceReportVO> showAttendenceReportByDepForToday(
			String dep);

	public List<FaculityDailyAttendanceReportVO> showAttendenceReportForToday();

	public List<FaculityDailyAttendanceReportVO> updateAttendenceForToday(List<String> checkedIds);

	public byte[] findImgById(String empid);

	public List<FaculityLeaveMasterVO> SortLeaveByDate(String d1, String d2,
			String eid);

	public List<FaculityDailyAttendanceReportVO> getAttStatusByDate(String d1, String d2, String eid);

	public List<FacultyMonthlyAttendanceStatusVO> getMonthlyAttByDate(String d1,
			String d2, String eid);

	public List<HolidayEntryForm> getHolidays(int m);

	public List<FaculityLeaveMasterVO> LeaveHistory(String d1, String d2, String eid);

	public List<FaculityLeaveMasterVO> getLeaveApproval(String eid);

	public void saveToFacultyWorkingTable(
			FacultyWorkingDaysVO facultyWorkingDaysVO);

	public String markLwpService(int id,String desc);

	public List<FaculityLeaveMasterVO> findAllLeaveHistoryForToday(String selectDate);

	 public List<FaculityDailyAttendanceReportVO> findAllEmpByDept(String date);

	public void unMarkLwpService(int id);

	
}
