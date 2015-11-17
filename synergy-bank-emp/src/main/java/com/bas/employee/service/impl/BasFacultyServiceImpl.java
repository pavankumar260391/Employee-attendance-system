package com.bas.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bas.admin.dao.entity.FaculityDailyAttendanceReportEntity;
import com.bas.admin.dao.entity.FacultyMonthlyAttendanceStatusEntity;
import com.bas.admin.dao.entity.HolidayEntryEntity;
import com.bas.admin.web.controller.form.EmployeeShowForm;
import com.bas.admin.web.controller.form.FaculityDailyAttendanceReportVO;
import com.bas.admin.web.controller.form.FacultyMonthlyAttendanceStatusVO;
import com.bas.admin.web.controller.form.HolidayEntryForm;
import com.bas.employee.dao.BasFacultyDao;
import com.bas.employee.dao.entity.EmployeeShowFormEntity;
import com.bas.employee.dao.entity.FaculityLeaveMasterEntity;
import com.bas.employee.dao.entity.FacultyEntity;
import com.bas.employee.dao.entity.FacultyLeaveApprovalEntity;
import com.bas.employee.dao.entity.FacultySalaryMasterEntity;
import com.bas.employee.dao.entity.FacultyWorkingDaysEntity;
import com.bas.employee.dao.entity.ManualAttendanceEntity;
import com.bas.employee.dao.entity.ReportingManagerEntity;
import com.bas.employee.service.BasFacultyService;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.FacultyForm;
import com.bas.employee.web.controller.form.FacultyLeaveApprovalVO;
import com.bas.employee.web.controller.form.FacultySalaryMasterVO;
import com.bas.employee.web.controller.form.FacultyWorkingDaysVO;
import com.bas.employee.web.controller.form.ManualAttendanceVO;
import com.bas.employee.web.controller.form.ReportingManagerVO;

@Service("BasFacultyServiceImpl")
@Transactional
public class BasFacultyServiceImpl implements BasFacultyService {

	@Autowired
	@Qualifier("BasFacultyDaoImpl")
	private BasFacultyDao basFacultyDao;
	
	

/**
 * PK for working days lwp
 */

	@Override
	public void unMarkLwpService(int id) {
		basFacultyDao.unMarkLwpInDb(id);
	}

	@Override
	public List<FaculityDailyAttendanceReportVO> findAllEmpByDept(String dept) {
		List<FaculityDailyAttendanceReportEntity> faculityLeaveMasterEntitieslist = basFacultyDao.getAllEmployeesByDeptForToday(dept);
		List<FaculityDailyAttendanceReportVO> faculityLeaveMasterVOslist = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityDailyAttendanceReportVO faculityLeaveMasterVO = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}
	
	@Override
	public List<FaculityLeaveMasterVO> findAllLeaveHistoryForToday(String date) {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao.findAllLeaveHistoryForToday(date);
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;

	}
	
	@Override
	public String markLwpService(int id,String desc) {

		return basFacultyDao.markLwpInDb(id,desc);
	}
	
	//end
	
	@Override
	public List<String> selectDesignations() {
		return basFacultyDao.selectDesignations();
		
	}


	@Override
	public List<String> selectReportingManagers() {
		return basFacultyDao.selectReportingManager();
	}

	
	@Override
	public List<String> findRelation(String manager) {
		List<String> employees = basFacultyDao.findRelation(manager);
		return employees;
	}
	
	@Override
	public String applyAddendumLeave(FaculityLeaveMasterVO faculityLeaveMasterVO)	{
		FaculityLeaveMasterEntity facultyLeaveEntity = new FaculityLeaveMasterEntity();
		BeanUtils.copyProperties(faculityLeaveMasterVO, facultyLeaveEntity);
		return basFacultyDao.applyAddendumLeave(facultyLeaveEntity);
	}
	
	
	@Override
	public List<FaculityDailyAttendanceReportVO> showAttendusReportByDep(String date, String dep){
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.showAttendusReportByDep(date, dep);
		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}

	@Override
	public List<FaculityDailyAttendanceReportVO> showAttendusReport(String date){
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.showAttendusReport(date);
		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}

	/*
	 * PK for viewAttendenceStatus
	 */

	@Override
	public List<FaculityDailyAttendanceReportVO> getAttStatusByDate(String d1, String d2, String eid) {
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.getAttendanceStatusByDate(d1,d2,eid);
		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}
	
	@Override
	public List<FaculityLeaveMasterVO> LeaveHistory(String d1, String d2, String eid){
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao.LeaveHistoryByDate(d1,d2,eid);
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}
	
	@Override
	public void saveToFacultyWorkingTable(FacultyWorkingDaysVO fwd){
		FacultyWorkingDaysEntity fwdEntity = new FacultyWorkingDaysEntity();
		BeanUtils.copyProperties(fwd,fwdEntity);
		basFacultyDao.saveToFacultyWorkingTable(fwdEntity);
	}

	@Override
	public List<FaculityLeaveMasterVO> getLeaveApproval(String eid){
		List<FacultyLeaveApprovalEntity> faculityLeaveMasterEntitieslist = basFacultyDao.getLeaveRequestsById(eid);
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FacultyLeaveApprovalEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}
	
	@Override
	public List<HolidayEntryForm> getHolidays(int m) {
		List<HolidayEntryEntity> facList = basFacultyDao.getHoliday(m);
		List<HolidayEntryForm> fDAV = new ArrayList<HolidayEntryForm>();
		for (HolidayEntryEntity faculityDailyAttendanceReportEntity : facList) {
			HolidayEntryForm facDailyAttRep = new HolidayEntryForm();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}
	
	@Override
	public List<FacultyMonthlyAttendanceStatusVO> getMonthlyAttByDate(String d1, String d2, String eid) {
		List<FacultyMonthlyAttendanceStatusEntity> facList = basFacultyDao.getMonthlyAttendanceByDate(d1,d2,eid);
		List<FacultyMonthlyAttendanceStatusVO> fDAV = new ArrayList<FacultyMonthlyAttendanceStatusVO>();
		for (FacultyMonthlyAttendanceStatusEntity faculityDailyAttendanceReportEntity : facList) {
			FacultyMonthlyAttendanceStatusVO facDailyAttRep = new FacultyMonthlyAttendanceStatusVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}
	
	//Already present..reusing...
	@Override
	public ManualAttendanceVO findEmployeeDataForAttendance(String empid) {
		ManualAttendanceEntity manualAttendanceEntity = basFacultyDao
				.findEmployeeDataForAttendance(empid);
		ManualAttendanceVO manualAttendanceVO = new ManualAttendanceVO();
		BeanUtils.copyProperties(manualAttendanceEntity,manualAttendanceVO);
		return manualAttendanceVO;
	}//End RE
	
	//End
	
	//End
	/*
	 * PK
	 * 
	 */
	@Override
	public List<FaculityDailyAttendanceReportVO> showAttendenceReportByDepForToday(String dep){
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.showAttendenceForTodayForSelectedDept(dep);
		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}
	
	@Override
	public List<FaculityDailyAttendanceReportVO> showAttendenceReportForToday(){
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.attendanceStatus();
		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	}
	
	@Override
	public List<FaculityDailyAttendanceReportVO> updateAttendenceForToday(List<String> checkedIds){
		
		List<FaculityDailyAttendanceReportEntity> facList = basFacultyDao.updateAttendenceForToday(checkedIds);

		List<FaculityDailyAttendanceReportVO> fDAV = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity faculityDailyAttendanceReportEntity : facList) {
			FaculityDailyAttendanceReportVO facDailyAttRep = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(faculityDailyAttendanceReportEntity, facDailyAttRep);
			fDAV.add(facDailyAttRep);
		}				
		return fDAV;
	
	}
	
	@Override
	public byte[] findImgById(String empid) {
		byte[] img = basFacultyDao.findImageById(empid);
		/*FaculityLeaveMasterVO faculityLeaveMasterVOs = new FaculityLeaveMasterVO();
		BeanUtils.copyProperties(faculityLeaveMasterVOs, faculityLeaveMasterEntitieslist);*/
		return img;
	}
	
	@Override
	public List<FaculityLeaveMasterVO> SortLeaveByDate(String d1, String d2, String eid){
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao.SortLeaveHistoryByDate(d1,d2,eid);
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}
	
	
	//End
	
	
	@Override
	public String createEmployeeAccount(FacultyForm facultyForm) {
		FacultyEntity facultyEntity = new FacultyEntity();
		BeanUtils.copyProperties(facultyForm, facultyEntity);
		return basFacultyDao.createEmployeeAccount(facultyEntity);
	}


	public String persistFaculty(FacultyForm facultyForm) {
		FacultyEntity facultyEntity = new FacultyEntity();
		BeanUtils.copyProperties(facultyForm, facultyEntity);
		return basFacultyDao.persistFaculty(facultyEntity);
	}

	@Override
	public String updateFaculty(FacultyForm facultyForm) {
		FacultyEntity facultyEntity = new FacultyEntity();
		BeanUtils.copyProperties(facultyForm, facultyEntity);
		return basFacultyDao.updateFaculty(facultyEntity);
	}

	@Override
	public String deletetFaculty(String name) {
		return basFacultyDao.deletetFaculty(name);
	}

	@Override
	public FacultyForm findFacultyByName(String name) {
		FacultyEntity facultyEntity = basFacultyDao.findFacultyByName(name);
		FacultyForm facultyForm = new FacultyForm();
		BeanUtils.copyProperties(facultyEntity, facultyForm);
		return facultyForm;
	}
	
	@Override
	public EmployeeShowForm findFacultyByNamespeci(String name) {
		EmployeeShowFormEntity employentity= basFacultyDao.findFacultyByNamespecific(name);
		 
		EmployeeShowForm employeeform=  new EmployeeShowForm();
				 
		 
		BeanUtils.copyProperties(employentity, employeeform);
		return employeeform;
	}
	
	@Override
	public List<FacultyForm> findAllFaculty() {
		List<FacultyEntity> facultyEntities = basFacultyDao.findAllFaculty();
		List<FacultyForm> facultyForms = new ArrayList<FacultyForm>();
		for (FacultyEntity fe : facultyEntities) {
			FacultyForm facultyForm = new FacultyForm();
			BeanUtils.copyProperties(fe, facultyForm);
			facultyForms.add(facultyForm);
		}
		return facultyForms;
	}

	@Override
	public byte[] findPhotoByEmpId(String empid) {
		return basFacultyDao.findPhotoByEmpId(empid);
	}

	@Override
	public FaculityLeaveMasterVO findLeaveBalance(String empid) {
		FaculityLeaveMasterEntity faculityLeaveMasterEntity = basFacultyDao
				.findLeaveBalance(empid);
		FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
		BeanUtils.copyProperties(faculityLeaveMasterEntity,
				faculityLeaveMasterVO);
		return faculityLeaveMasterVO;
	}

	@Override
	public List<FaculityLeaveMasterVO> findLeaveHistory(String empid) {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao.findLeaveHistory(empid);
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}

	@Override
	public List<FaculityDailyAttendanceReportVO> findAttendStatus(String fid, int monthValue) {
		List<FaculityDailyAttendanceReportEntity> attendStatusEntities = basFacultyDao
				.findAttendStatus(fid,monthValue);
		List<FaculityDailyAttendanceReportVO> facultyAttendStatusVOList = new ArrayList<FaculityDailyAttendanceReportVO>();
		for (FaculityDailyAttendanceReportEntity fe : attendStatusEntities) {
			FaculityDailyAttendanceReportVO facultyAttendStatusVO = new FaculityDailyAttendanceReportVO();
			BeanUtils.copyProperties(fe, facultyAttendStatusVO);
			facultyAttendStatusVOList.add(facultyAttendStatusVO);
		}
		return facultyAttendStatusVOList;
	}



	@Override
	public List<FacultySalaryMasterVO> findSalaryHistory(String empid) {
		List<FacultySalaryMasterEntity> facultySalaryMasterEntitieslist = basFacultyDao
				.findSalaryHistory(empid);
		List<FacultySalaryMasterVO> facultySalaryMasterVOslist = new ArrayList<FacultySalaryMasterVO>();
		for (FacultySalaryMasterEntity fs : facultySalaryMasterEntitieslist) {
			FacultySalaryMasterVO facultySalaryMasterVO = new FacultySalaryMasterVO();
			BeanUtils.copyProperties(fs, facultySalaryMasterVO);
			facultySalaryMasterVOslist.add(facultySalaryMasterVO);
		}
		return facultySalaryMasterVOslist;
	}

	@Override
	public FaculityLeaveMasterVO findLeaveAppData(String empid/*,String leaveMonth*/) {
		FaculityLeaveMasterEntity faculityLeaveMasterEntity = basFacultyDao
				.findLeaveAppData(empid/*,leaveMonth*/);
		FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
		BeanUtils.copyProperties(faculityLeaveMasterEntity,
				faculityLeaveMasterVO);
		return faculityLeaveMasterVO;
	}

	@Override
	public void addLeaveEntry(FaculityLeaveMasterVO faculityLeaveMasterVO) {
		System.out.println("before Dao" + faculityLeaveMasterVO.getLeaveFrom());
		FaculityLeaveMasterEntity faculityLeaveMasterEntity=new FaculityLeaveMasterEntity();
		BeanUtils.copyProperties(faculityLeaveMasterVO, faculityLeaveMasterEntity);
		basFacultyDao.addLeaveEntry(faculityLeaveMasterEntity);
	}

	@Override
	public byte[] findEmpPhotoByName(String name) {
		return basFacultyDao.findPhotoByEmpName(name);
	}

	@Override
	public String enterLeaveHistory(FaculityLeaveMasterVO faculityLeaveMasterVO) {
		return basFacultyDao.enterLeaveHistory(faculityLeaveMasterVO);
	}

	@Override
	public List<FaculityLeaveMasterVO> findAllLeaveHistory() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao
				.findAllLeaveHistory();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;

	}

	@Override
	public List<FaculityLeaveMasterVO> findAllEmpDb() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao
				.findAllEmpDb();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}

	@Override
	public String deleteLeaveHistory(String name, String date) {
		return basFacultyDao.deleteLeaveHistory(name, date);

	}

	

	@Override
	public String addEmployeeManulAttendance(
			ManualAttendanceVO manualAttendanceVO) {
		ManualAttendanceEntity manualAttendanceEntity = new ManualAttendanceEntity();
		BeanUtils.copyProperties(manualAttendanceVO, manualAttendanceEntity);
		return basFacultyDao.addEmployeeManulAttendance(manualAttendanceEntity);
	}

	@Override
	public List<FaculityLeaveMasterVO> findAllPendingLeaveHistory() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao
				.findAllPendingLeaveHistory();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}

	@Override
	public void updateLeaveHistory(String empNo, String date, String lstatus) {
		basFacultyDao.updateLeaveHistory(empNo, date,lstatus);
	}

	@Override
	public List<FaculityLeaveMasterVO> getReportingManagerList() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntities=basFacultyDao.getReportingManagerList();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOs=new ArrayList<FaculityLeaveMasterVO>(); 
		for (FaculityLeaveMasterEntity faculityLeaveMasterEntity : faculityLeaveMasterEntities) {
			FaculityLeaveMasterVO faculityLeaveMasterVO=new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(faculityLeaveMasterEntity, faculityLeaveMasterVO);
			faculityLeaveMasterVOs.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOs;
	}

	@Override
	public List<FaculityLeaveMasterVO> getCCToList() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntities=basFacultyDao.getCCToList();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOs=new ArrayList<FaculityLeaveMasterVO>(); 
		for (FaculityLeaveMasterEntity faculityLeaveMasterEntity : faculityLeaveMasterEntities) {
			FaculityLeaveMasterVO faculityLeaveMasterVO=new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(faculityLeaveMasterEntity, faculityLeaveMasterVO);
			faculityLeaveMasterVOs.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOs;
	}

	@Override
	public String enterRmLeaveHistory(
			FaculityLeaveMasterVO faculityLeaveMasterVO) {
		return basFacultyDao.enterRmLeaveHistory(faculityLeaveMasterVO);

	}

	@Override
	public List<FaculityLeaveMasterVO> findAllRmPendingLeaveHistory() {
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = basFacultyDao
				.findAllRmPendingLeaveHistory();
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist = new ArrayList<FaculityLeaveMasterVO>();
		for (FaculityLeaveMasterEntity flh : faculityLeaveMasterEntitieslist) {
			FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
			BeanUtils.copyProperties(flh, faculityLeaveMasterVO);
			faculityLeaveMasterVOslist.add(faculityLeaveMasterVO);
		}
		return faculityLeaveMasterVOslist;
	}
	
	@Override
	public String updateEmployee(FaculityDailyAttendanceReportVO dfaAttendStatusVO, String fid, String newdate){
		FaculityDailyAttendanceReportEntity facultyAttendStatusEntity = new FaculityDailyAttendanceReportEntity();
		BeanUtils.copyProperties(dfaAttendStatusVO, facultyAttendStatusEntity);
		return basFacultyDao.updateEmployee(facultyAttendStatusEntity, fid, newdate);
	}	



	@Override
	public String deleteAttendus(String employeeId, String attndDate) {		
		return basFacultyDao.deleteAttendus(employeeId,attndDate);
	}

	
	@Override
	public List<String> selectDepartments(){
		return basFacultyDao.selectDepartments();
	}

	@Override
	public List<String> searchEmployee(String employeeName){
		List<String> query = basFacultyDao.searchEmployee(employeeName);
		return query;
	}
	
	 
	@Override
	public List<EmployeeShowForm> findAllEmployee() {
		List<FacultyEntity> employeeEntities = basFacultyDao.findAllFaculty();
		List<EmployeeShowForm> employeeForms = new ArrayList<EmployeeShowForm>();
//		List<FacultyForm> facultyForms = new ArrayList<FacultyForm>();
		for (FacultyEntity fe :employeeEntities) {
			EmployeeShowForm ef= new EmployeeShowForm();
			BeanUtils.copyProperties(fe, ef);
			employeeForms.add(ef);
		}
		return employeeForms;
	} 
	
	@Override
	public List<FacultyLeaveApprovalVO> getLeaveApprovalForManager(int managerId)
	{
		List<FacultyLeaveApprovalEntity> empLeaveApprovalEntityList = basFacultyDao.getLeaveApprovalForManager(managerId);
		List<FacultyLeaveApprovalVO> employeeLeaveApprovalList = new ArrayList<FacultyLeaveApprovalVO>();
		for (FacultyLeaveApprovalEntity eLA: empLeaveApprovalEntityList)
		{
			FacultyLeaveApprovalVO empLeaveApprovalVO = new FacultyLeaveApprovalVO();
			BeanUtils.copyProperties(eLA, empLeaveApprovalVO);
			employeeLeaveApprovalList.add(empLeaveApprovalVO);
		}
		System.out.println("Dao" + employeeLeaveApprovalList);
		return employeeLeaveApprovalList;
		
	}
	
	@Override
	public String updateLeaveApprovalForManager(int requestID, String managerApproval) 	{
		return basFacultyDao.updateLeaveApprovalForManager(requestID, managerApproval);
	}
	
	@Override
	public ReportingManagerVO findReportingManagerByEmpId(String employeeId) {
		ReportingManagerVO reportingManagerVO = new ReportingManagerVO();
		ReportingManagerEntity	reportingManagerEntity=basFacultyDao.findReportingManagerByEmpId(employeeId);
		BeanUtils.copyProperties(reportingManagerEntity, reportingManagerVO);
		return reportingManagerVO;
	}
	
	
	@Override
	public List<String> searchEmployeeByManId(String employeeName){
		List<String> query = basFacultyDao.searchEmployeeByManId(employeeName);
		return query;
	}
	
	@Override
	public List<String> searchId(String employeeId){
		List<String> query = basFacultyDao.searchId(employeeId);
		return query;
	}

	
}
