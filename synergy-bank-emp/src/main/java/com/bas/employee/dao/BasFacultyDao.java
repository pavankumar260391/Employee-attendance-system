package com.bas.employee.dao;

import java.util.List;

import com.bas.admin.dao.entity.FaculityDailyAttendanceReportEntity;
import com.bas.admin.dao.entity.FacultyMonthlyAttendanceStatusEntity;
import com.bas.admin.dao.entity.HolidayEntryEntity;
import com.bas.employee.dao.entity.EmployeeShowFormEntity;
import com.bas.employee.dao.entity.FaculityLeaveMasterEntity;
import com.bas.employee.dao.entity.FacultyEntity;
import com.bas.employee.dao.entity.FacultyLeaveApprovalEntity;
import com.bas.employee.dao.entity.FacultySalaryMasterEntity;
import com.bas.employee.dao.entity.FacultyWorkingDaysEntity;
import com.bas.employee.dao.entity.ManualAttendanceEntity;
import com.bas.employee.dao.entity.ReportingManagerEntity;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.ManualAttendanceVO;

/**
 * 
 * @author sssss
 *
 */
public interface BasFacultyDao {
	
	
	public String persistFaculty(FacultyEntity facultyEntity);
	public String updateFaculty(FacultyEntity facultyEntity);
	public String deletetFaculty(String name);
	public FacultyEntity findFacultyByName(String name);
//	public List<FacultyEntity> findAllFaculty();
	public List<FacultyEntity> findAllFaculty();
	public byte[] findPhotoByEmpId(String name);
	public FaculityLeaveMasterEntity findLeaveBalance(String empid);
	public List<FaculityDailyAttendanceReportEntity> findAttendStatus(String fid,int monthValue);
	public List<FaculityLeaveMasterEntity> findLeaveHistory(String empid);
	public List<FacultySalaryMasterEntity> findSalaryHistory(String empid);
	public byte[] findPhotoByEmpName(String name);
	public String enterLeaveHistory(FaculityLeaveMasterVO faculityLeaveMasterVO);
	public List<FaculityLeaveMasterEntity> findAllLeaveHistory();
	public List<FaculityLeaveMasterEntity> findAllEmpDb();
	public String deleteLeaveHistory(String name, String date);
	public ManualAttendanceEntity findEmployeeDataForAttendance(String empid);
	public String addEmployeeManulAttendance(ManualAttendanceEntity manualAttendanceEntity);
	public List<FaculityLeaveMasterEntity> findAllPendingLeaveHistory();
	public void updateLeaveHistory(String empNo, String date, String lstatus);
	public List<FaculityLeaveMasterEntity> getReportingManagerList();
	public List<FaculityLeaveMasterEntity> getCCToList();
	public String enterRmLeaveHistory(FaculityLeaveMasterVO faculityLeaveMasterVO);
	public List<FaculityLeaveMasterEntity> findAllRmPendingLeaveHistory();
	public FaculityLeaveMasterEntity findLeaveAppData(String empid/*, String leaveMonth*/);
	public void addLeaveEntry(FaculityLeaveMasterEntity faculityLeaveMasterEntity);
	public String deleteAttendus(String employeeId, String attndDate);
	public List<String> selectDepartments();
	public List<FaculityDailyAttendanceReportEntity> showAttendusReport(String date);
	public List<FaculityDailyAttendanceReportEntity> showAttendusReportByDep(
			String date, String dep);
	public List<String> searchEmployee(String employeeName);
	public EmployeeShowFormEntity findFacultyByNamespecific(String name);
	public String updateEmployee(
			FaculityDailyAttendanceReportEntity facultyAttendStatusEntity,
			String fid, String newdate);
	public String createEmployeeAccount(FacultyEntity facultyEntity);
	public List<FacultyLeaveApprovalEntity> getLeaveApprovalForManager(int managerId);
	public String updateLeaveApprovalForManager(int requestID, String managerApproval);
	public ReportingManagerEntity findReportingManagerByEmpId(String employeeId);
	public List<String> findRelation(String manager);
	public String applyAddendumLeave(FaculityLeaveMasterEntity al);
	public List<String> selectReportingManager();
	public List<String> selectDesignations();
	public List<String> searchEmployeeByManId(String employeeName);
	public List<String> searchId(String employeeId);
	public List<FaculityDailyAttendanceReportEntity> showAttendenceforTodayAllDepts();
	public List<FaculityDailyAttendanceReportEntity> showAttendenceForTodayForSelectedDept(String dep);
	public List<FaculityDailyAttendanceReportEntity> updateAttendenceForToday(List<String> checkedIds);
	public byte[] findImageById(String empid);
	public List<FaculityLeaveMasterEntity> SortLeaveHistoryByDate(String date1,
			String date2, String empid);
	public List<FaculityDailyAttendanceReportEntity> getAttendanceStatusByDate(String d1, String d2, String eid);
	
	public List<FacultyMonthlyAttendanceStatusEntity> getMonthlyAttendanceByDate(
			String d1, String d2, String eid);
	public List<HolidayEntryEntity> getHoliday(int month);
	public List<FaculityLeaveMasterEntity> LeaveHistoryByDate(String date1,
			String date2, String empid);
	 public List<FacultyLeaveApprovalEntity> getLeaveRequestsById(String eid);
	public void saveToFacultyWorkingTable(FacultyWorkingDaysEntity fwdEntity);
	public List<FaculityDailyAttendanceReportEntity> attendanceStatus();
	public String markLwpInDb(int id,String description);
	public List<FaculityLeaveMasterEntity> findAllLeaveHistoryForToday(String date);
	public List<FaculityDailyAttendanceReportEntity> getAllEmployeesByDeptForToday(String date);
	public void unMarkLwpInDb(int id);

}
