package com.bas.employee.dao.impl;

import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bas.admin.dao.entity.FaculityDailyAttendanceReportEntity;
import com.bas.admin.dao.entity.FacultyMonthlyAttendanceStatusEntity;
import com.bas.admin.dao.entity.HolidayEntryEntity;
import com.bas.common.constant.NavigationConstant;
import com.bas.common.util.DateUtils;
import com.bas.employee.dao.BasFacultyDao;
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

@Repository("BasFacultyDaoImpl")
@Transactional
/*the new code by kushal for registration page */
public class BasFacultyDaoImpl extends JdbcDaoSupport implements BasFacultyDao {

	@Autowired
	@Qualifier("sdatasource")
	public void setDataSourceInSuper(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	/*
	 * PK for viewAttendenceStatus
	 */
	
	
	@Override
	public void saveToFacultyWorkingTable(FacultyWorkingDaysEntity fwdEntity) {
		String query = "insert into faculty_working_days_tbl (empid,noOfDaysWorked,noOfApprovedLeaves,noOfHolidays,totalworkingdays,department,month) values (?,?,?,?,?,?,?,?)";
		super.getJdbcTemplate().update(query, new Object[] {fwdEntity.getEmpid(),fwdEntity.getNoOfDaysWorked(),fwdEntity.getNoOfApprovedLeaves(),fwdEntity.getNoOfHolidays(),fwdEntity.getTotalworkingdays(),fwdEntity.getDepartment(),fwdEntity.getMonth(),fwdEntity.getNoOfLwp()});
	}
	
	
	@Override
	public List<FacultyLeaveApprovalEntity> getLeaveRequestsById(String eid)
	{
		String query = "select * from emp_leave_requests_tbl where manager_id = ? or employee_id= ?";
		List <FacultyLeaveApprovalEntity> facultyLeaveData = super.getJdbcTemplate().query(query,
				new Object[] {eid, eid}, new BeanPropertyRowMapper<FacultyLeaveApprovalEntity>(FacultyLeaveApprovalEntity.class));
		return facultyLeaveData;
	}
 	
	@Override 
	public List<FaculityLeaveMasterEntity> LeaveHistoryByDate(String date1, String date2, String empid) {
		String squery = "select lh.empNo,e.`name`, e.designation,e.department, e.doj, e.image, lh.leaveType,lh.leaveCategory,lh.lstatus,lh.purpose,lh.ldateFrom as leaveFrom,lh.ldateTo as leaveTo,lh.totalDays from emp_leave_history as lh,emp_db as e where lh.empNo=e.id and lh.empNo=? and ldateFrom >= ? and ldateFrom <= ?";
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = (List<FaculityLeaveMasterEntity>) super.getJdbcTemplate().query(squery,new Object[] { Integer.parseInt(empid), date1, date2 },new BeanPropertyRowMapper<FaculityLeaveMasterEntity>(FaculityLeaveMasterEntity.class));
		//System.out.println(squery);
		return faculityLeaveMasterEntitieslist;
	}
	
	
	//Already present..Reusing..
	@Override
	public ManualAttendanceEntity findEmployeeDataForAttendance(String empid) {
		String lquery = "select e.id as eid,e.name,e.designation,e.department,e.type from emp_db as e where e.id=?";
		ManualAttendanceEntity manualAttendanceEntity = (ManualAttendanceEntity) super
				.getJdbcTemplate()
				.queryForObject(lquery, new Object[] { Integer.parseInt(empid) },
						new BeanPropertyRowMapper(ManualAttendanceEntity.class));
		return manualAttendanceEntity;
	
	}
	//End RE
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> getAttendanceStatusByDate(String d1, String d2, String eid) {
		String sql = "select att.fid, DATE_FORMAT(att.cdate,'%Y-%m-%d') as cdate, e.name, e.fatherName, e.department, att.intime, att.outtime, att.status, att.intimestatus, att.outtimestatus, att.present from faculity_att_tab as att,emp_db as e where (att.fid=e.id and att.fid=? and cdate>=? and cdate<=?)";
		List<FaculityDailyAttendanceReportEntity> fDARE = super.getJdbcTemplate().query(sql,new Object[] {eid,d1,d2}, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
		return fDARE;
	}
	
	//New
	@Override
	public List<HolidayEntryEntity> getHoliday(int month){
		String query = "select DATE_FORMAT(cdate,'%Y-%m-%d') as holidayDate,holidayType, description, comment,image from holiday_entry_tbl where month(cdate) = ? ";
		List<HolidayEntryEntity> calenderEntities = super.getJdbcTemplate().query(query,
				new Object[]{month}, new BeanPropertyRowMapper<HolidayEntryEntity>(HolidayEntryEntity.class));
		return calenderEntities;
	}
	
	@Override
	public List<FacultyMonthlyAttendanceStatusEntity> getMonthlyAttendanceByDate(String d1, String d2, String eid) {
		String sql="(select att.fid, att.cdate, e.name, e.fatherName, e.department, att.intime, att.outtime, att.intimestatus, att.outtimestatus, att.status from faculity_att_tab as att,emp_db as e where att.fid=e.id and att.fid=? and att.cdate>=? and att.cdate<=?) union (select *,'' from holiday_entry_tbl as t where t.cdate>=? and t.cdate<=?) order by cdate asc";
		List<FacultyMonthlyAttendanceStatusEntity> fDARE = super.getJdbcTemplate().query(sql,new Object[] {eid,d1,d2,d1,d2}, new BeanPropertyRowMapper<FacultyMonthlyAttendanceStatusEntity>(FacultyMonthlyAttendanceStatusEntity.class));
		return fDARE;
	}
	//End
	
	//End
	
	/*
	 *By: PK
	 *
	 */
	//new for mark Lwp

	@Override
	public void unMarkLwpInDb(int id) {
		String currDate = DateUtils.getCurrentCalendarDate();
		String sql = "delete from emp_leave_history where empNo=? and ldateFrom=? and ldateTo=? and leaveType=?";
		super.getJdbcTemplate().update(sql, new Object[]{id,currDate,currDate,NavigationConstant.LWP});
	}
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> getAllEmployeesByDeptForToday(String dept){
		String q1;
		List<FaculityDailyAttendanceReportEntity> attTodayByDept;
		if(dept.equals("All")){
		 q1 = "select id as fid, designation, name,department from emp_db order by department asc, name asc";
		attTodayByDept = super.getJdbcTemplate().query(q1, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
	}
		else{
		 q1 = "select id as fid, designation, name,department from emp_db where department=? order by department asc, name asc";
		 attTodayByDept = super.getJdbcTemplate().query(q1,new Object[]{dept}, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
		}
	return attTodayByDept;
	}
	
	@Override
	public List<FaculityLeaveMasterEntity> findAllLeaveHistoryForToday(String date) {
    	//String currDate=DateUtils.getCurrentCalendarDate();
		String query = "select e.empNo,e.ldateFrom as leaveFrom,e.ldateTo as leaveTo,e.empName as name,e.deptt as department,e.lstatus,e.leaveType,e.totalDays from emp_leave_history e where e.ldateFrom=? and e.ldateTo=? and leaveType=?";
		List<FaculityLeaveMasterEntity> facultyLeaveHistories = super.getJdbcTemplate().query(query, new Object[]{date, date, NavigationConstant.LWP}, new BeanPropertyRowMapper(FaculityLeaveMasterEntity.class));
		return facultyLeaveHistories;
	}
	
	@Override
	public String markLwpInDb(int id,String description){
    	String currDate=DateUtils.getCurrentCalendarDate();
		String q1 = "select name,department from emp_db where id="+id;
    	FaculityDailyAttendanceReportEntity attTodayAllDept = super.getJdbcTemplate().queryForObject(q1,new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
    	String name = attTodayAllDept.getName();      
    	String department = attTodayAllDept.getDepartment();
		String sql = "insert into emp_leave_history (empNo,empName,deptt,leaveType,leaveCategory,lstatus,purpose,ldateFrom,ldateTo,totalDays) values (?,?,?,?,?,?,?,?,?,?)";
		try{
			super.getJdbcTemplate().update(sql,new Object[]{id,name,department,NavigationConstant.LWP,NavigationConstant.LEAVE_CATEGORY_FULLDAY,NavigationConstant.LEAVE_STATUS_NOT_APPROVED,description,currDate,currDate,1});
			}
			catch(Exception e){
				return "LWP has already been marked for this employee";
			}
		return "success";
		}

	@Override
	public List<FaculityDailyAttendanceReportEntity> attendanceStatus(){
    	String currentDate=DateUtils.getCurrentCalendarDate();
    	String sql = "select e.id as fid, e.name, e.designation, e.department from emp_db e order by department asc,name asc";
    	List<FaculityDailyAttendanceReportEntity> attTodayAllDept = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
		return attTodayAllDept;
	}
	//end
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> showAttendenceforTodayAllDepts(){
    	String currentDate=DateUtils.getCurrentCalendarDate();
    	System.out.println(currentDate+"@@@@@@@@@@@22");
		String sql = "select att.fid, att.cdate, e.name, e.fatherName, e.department, from faculity_att_tab as att,emp_db as e where (att.fid=e.id and cdate='"+currentDate+"')";
		List<FaculityDailyAttendanceReportEntity> attTodayAllDept = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
		
		return attTodayAllDept;
	}

	@Override
	public List<FaculityDailyAttendanceReportEntity> showAttendenceForTodayForSelectedDept(String dep){
    	String currentDate=DateUtils.getCurrentCalendarDate();
		String sql = "select att.fid, att.cdate, e.name, e.fatherName, e.department, att.intime, att.outtime, att.status, att.intimestatus, att.outtimestatus, att.present from faculity_att_tab as att,emp_db as e where (att.fid=e.id and cdate='"+currentDate+"' and e.department='"+dep+"')";
		List<FaculityDailyAttendanceReportEntity> attTodaySelDept = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));		
		return attTodaySelDept;
	}
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> updateAttendenceForToday(List<String> checkedIds){
		String query="";
		int rowsAffected=0;
		String norm = "Normal";
    	String currentDate=DateUtils.getCurrentCalendarDate();
		for(String id : checkedIds){
			System.out.println("-curr Date----"+currentDate);
			query = "update faculity_att_tab as att set att.outtimestatus=?, att.outtime=(select outtime from organisation_time order by sno desc LIMIT 1) where att.fid=? and att.cdate=?";
			
			rowsAffected = super.getJdbcTemplate().update(query,new Object[]{norm,Integer.parseInt(id),currentDate});
		}
		//System.out.println("rowsAffected = ==============="+rowsAffected);
		//System.out.println("Query string =================="+query);
		List<FaculityDailyAttendanceReportEntity> attTodayAllDept = showAttendenceforTodayAllDepts();
		return attTodayAllDept;
	}
	//End
	
	@Override
	public List<String> selectReportingManager() {
		String sql = "SELECT CONCAT(A.NAME, '-', A.ID, '-', A.DEPARTMENT) FROM emp_db A WHERE A.REPORTING_MANAGER='YES'";
		List<String> reportmList = super.getJdbcTemplate().queryForList(sql, String.class);		
		return reportmList;
		
	}





	@Override
	public List<String> selectDesignations(){
		String sql = "Select DesignationName from designations_tbl";
		List<String> desigList = super.getJdbcTemplate().queryForList(sql, String.class);		
		return desigList;
	
	}


	
	@Override
	public String updateEmployee(FaculityDailyAttendanceReportEntity facultyAttendStatusEntity, String fid, String newdate){
		String sql = "UPDATE faculity_att_tab SET intime=?,outtime=?,intimestatus=?,outtimestatus=?,detail=?,present=? WHERE fid=? and Date(cdate)=?";
		Object[] object = new Object[] {facultyAttendStatusEntity.getIntime(),facultyAttendStatusEntity.getOuttime(),
			facultyAttendStatusEntity.getIntimestatus(),facultyAttendStatusEntity.getOuttimestatus(),
			facultyAttendStatusEntity.getDetail(),facultyAttendStatusEntity.getPresent(),fid,newdate};
		super.getJdbcTemplate().update(sql, object);
		return "Attendus Updated Success";
	}


	
	@Override
	public String deleteAttendus(String employeeId, String attndDate){
		String sql = "Delete from faculity_att_tab where fid=? and Date(cdate)=?";
		super.getJdbcTemplate().update(sql,new Object[]{employeeId,attndDate});
		return "Attendus Deleted Successfuly";
	}

	@Override
	public List<String> selectDepartments(){
		String sql = "Select departmentName from departments_tbl";
		List<String> depList = super.getJdbcTemplate().queryForList(sql, String.class);		
		return depList;
	}

	@Override
	public String createEmployeeAccount(FacultyEntity facultyEntity) {
		//Generate the emp id;
		String tokens[]=facultyEntity.getReportingManager().split("-");
		String reportingManageEId=tokens[1]; 
		String sql="select cempid from current_gen_emp_id";
		String empid=(String)getJdbcTemplate().queryForObject(sql, String.class);
		long newempid=Long.parseLong(empid)+1;
		sql="update current_gen_emp_id set cempid='"+newempid+"'";
		getJdbcTemplate().update(sql);
		String query = "insert into emp_db(id,name,email,phoneNumber,dob,sex,designation,department,type,reporting_manager) values(?,?,?,?,?,?,?,?,?,?)";
		Object data[]=new Object[]{newempid,facultyEntity.getName(),facultyEntity.getEmail(),facultyEntity.getPhoneNumber(),facultyEntity.getDob(),facultyEntity.getSex(),facultyEntity.getDesignation(),facultyEntity.getDepartment(),facultyEntity.getType(),reportingManageEId};
		getJdbcTemplate().update(query,data);
		String inserIntoRelation="insert into emp_manager_relation_tb(manager_id,employee_id) values(?,?)";
		getJdbcTemplate().update(inserIntoRelation,new Object[]{reportingManageEId,newempid+""});
		//Generating credential
		String loginDetailQuery="insert into faculity_login_tbl(userid,eid,password,email,locked,role,doe,dom) values(?,?,?,?,?,?,?,?)";
		Object loginDetailData[]=new Object[]{newempid+"",newempid+"",facultyEntity.getPassword(),facultyEntity.getEmail(),"no",facultyEntity.getRole(),facultyEntity.getDoe(),facultyEntity.getDom()};
		getJdbcTemplate().update(loginDetailQuery,loginDetailData);
		return newempid+"";
	}
	
	//put all the queries in the sql file
	@Override
	public String persistFaculty(FacultyEntity facultyEntity) {
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue image = new SqlLobValue(facultyEntity.getImage(),
				lobHandler);
		SqlLobValue rthumbimage = new SqlLobValue(facultyEntity.getRightThumb(),
				lobHandler);
		SqlLobValue lthumbimage = new SqlLobValue(facultyEntity.getLeftThumb(),
				lobHandler);
		Object data[] = new Object[] { facultyEntity.getId(),facultyEntity.getName(),facultyEntity.getFatherName(),facultyEntity.getMotherName(),
				facultyEntity.getSpouseName(),facultyEntity.getGuardianName(),facultyEntity.getEmail(),facultyEntity.getPaddress(),
				facultyEntity.getPhoneNumber(),facultyEntity.getDob(),facultyEntity.getSex(),facultyEntity.getMaritalStatus(),
				facultyEntity.getBloodGroup(),facultyEntity.getDesignation(),facultyEntity.getDepartment(), facultyEntity.getType(),
				facultyEntity.getCategory(),facultyEntity.getDoj(),facultyEntity.getDiploma(),facultyEntity.getBatchlourDegree(),
				facultyEntity.getMastersDegree(),facultyEntity.getPostMastersDegree(),facultyEntity.getOtherQualification(),
				image,lthumbimage,rthumbimage,facultyEntity.getDescription(),facultyEntity.getDom(),facultyEntity.getDoe()};
				String query = "insert into  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					int dataTye[] = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.BLOB,Types.BLOB,Types.BLOB, Types.VARCHAR, Types.DATE, Types.DATE};
		super.getJdbcTemplate().update(query, data, dataTye);
		return "ahahha";
	}

	@Override
	public String updateFaculty(FacultyEntity facultyEntity) {
		/*LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue cimage = new SqlLobValue(facultyEntity.getPhoto(),
				lobHandler);
		Object data[] = new Object[] { facultyEntity.getDesignation(),
				facultyEntity.getMobile(), facultyEntity.getGender(),
				facultyEntity.getEmail(), facultyEntity.getDegree(),
				facultyEntity.getFatherName(), facultyEntity.getDepartment(),
				facultyEntity.getAddress(), cimage, facultyEntity.getName() };
		String query = "update faculty_tbl set  designation=?,mobile=?,gender=?,email=?,degree=?,fatherName=?,department=?,address=?,photo=? where name=?";
		int dataTye[] = new int[] { Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR };
		super.getJdbcTemplate().update(query, data, dataTye);*/
		return "ahahha";
	}

	@Override
	public String deletetFaculty(String name) {
		String query = "delete from faculty_tbl where name=?";
		super.getJdbcTemplate().update(query, name);
		return "deleted";
	}

	@Override
	public List<FacultyEntity> findAllFaculty() {
		//dont use * just use column name because column might be added or deleted
		String query = "SELECT id,name,email,phoneNumber,designation,department,image FROM emp_db";
		List<FacultyEntity> facultyEntities = super.getJdbcTemplate().query(
				query, new BeanPropertyRowMapper(FacultyEntity.class));
		return facultyEntities;
	}
	@Override
	//dont fetch the whole data when u need only the image
	public byte[] findPhotoByEmpId(String empid) {
		String query = "select * from emp_db where id="+Long.parseLong(empid);
		FacultyEntity facultyEntity = null;
		try {
			facultyEntity = (FacultyEntity) super.getJdbcTemplate()
					.queryForObject(query,
							new BeanPropertyRowMapper(FacultyEntity.class));
		} catch (EmptyResultDataAccessException exception) {
			exception.printStackTrace();
		}
		return facultyEntity.getImage();
	}

	@Override
	public FacultyEntity findFacultyByName(String name) {
		String query = "select * from faculty_tbl where name='" + name + "'";
		FacultyEntity facultyEntitie = null;
		try {
			facultyEntitie = (FacultyEntity) super.getJdbcTemplate()
					.queryForObject(query,
							new BeanPropertyRowMapper(FacultyEntity.class));
		} catch (EmptyResultDataAccessException exception) {
			exception.printStackTrace();
		}
		return facultyEntitie;
	}
	
	@Override
	public EmployeeShowFormEntity findFacultyByNamespecific(String name) {
		String query = "select * from employeeShow_tbl where name='" + name + "'";
		EmployeeShowFormEntity specific = null;
		try {
			specific = (EmployeeShowFormEntity) super.getJdbcTemplate()
					.queryForObject(query,
							new BeanPropertyRowMapper(EmployeeShowFormEntity.class));
		} catch (EmptyResultDataAccessException exception) {
			exception.printStackTrace();
		}
		return specific;
	}

	@Override
	public FaculityLeaveMasterEntity findLeaveBalance(String empid) {
		String squery = "select lb.empNo,e.name, e.designation,e.department ,lb.leaveMonth,lb.totalCL,lb.totalSL,lb.totalEL,lb.OD from emp_leave_balance as lb,emp_db as e where lb.empNo=e.id and lb.empNo=?";
		FaculityLeaveMasterEntity faculityLeaveMasterEntity = null;
		try {
			faculityLeaveMasterEntity = (FaculityLeaveMasterEntity) super
					.getJdbcTemplate()
					.queryForObject(
							squery,
							new Object[] { Integer.parseInt(empid) },
							new BeanPropertyRowMapper<FaculityLeaveMasterEntity>(
									FaculityLeaveMasterEntity.class));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return faculityLeaveMasterEntity;
	}
	
	//Changes by PK
	@Override 
	public List<FaculityLeaveMasterEntity> SortLeaveHistoryByDate(String date1, String date2, String empid) {
		String squery = "select lh.empNo,e.`name`, e.designation,e.department, e.doj, e.image, lh.leaveType,lh.leaveCategory,lh.lstatus,lh.purpose,lh.ldateFrom as leaveFrom,lh.ldateTo as leaveTo,lh.totalDays from emp_leave_history as lh,emp_db as e where lh.empNo=e.id and lh.empNo=? and ldateFrom >= ? and ldateTo <= ?";
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = (List<FaculityLeaveMasterEntity>) super.getJdbcTemplate().query(squery,new Object[] { Integer.parseInt(empid), date1, date2 },new BeanPropertyRowMapper<FaculityLeaveMasterEntity>(FaculityLeaveMasterEntity.class));
		//System.out.println(squery);
		return faculityLeaveMasterEntitieslist;
	}
	
	@Override
	public List<FaculityLeaveMasterEntity> findLeaveHistory(String empid) {
		String squery = "select lh.empNo,e.`name`, e.designation,e.department, e.doj, e.image, lh.leaveType,lh.leaveCategory,lh.lstatus,lh.purpose,lh.ldateFrom as leaveFrom,lh.ldateTo as leaveTo,lh.totalDays from emp_leave_history as lh,emp_db as e where lh.empNo=e.id and lh.empNo=?";
		List<FaculityLeaveMasterEntity> faculityLeaveMasterEntitieslist = (List<FaculityLeaveMasterEntity>) super.getJdbcTemplate().query(squery,new Object[] { Integer.parseInt(empid) },new BeanPropertyRowMapper<FaculityLeaveMasterEntity>(FaculityLeaveMasterEntity.class));
		return faculityLeaveMasterEntitieslist;
	}
	
	@Override
	public byte[] findImageById(String empid) {
		String squery = "select image from emp_db where id=?";
		FaculityLeaveMasterEntity imgForEmp = (FaculityLeaveMasterEntity) super.getJdbcTemplate().queryForObject(squery,new Object[] { Integer.parseInt(empid) },new BeanPropertyRowMapper(FaculityLeaveMasterEntity.class));
		return imgForEmp.getImage();
	}
	
	//End
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> findAttendStatus(String fid,int monthValue) {
		String dquery = "select s.cdate,s.intime,s.intimestatus,s.outtime,s.outtimestatus,s.status,s.detail,s.present  from faculity_att_tab as s   where s.fid="
				+ fid + "  and month(s.cdate) = "+ monthValue +" order by s.cdate asc";
		System.out.println(dquery);
		List<FaculityDailyAttendanceReportEntity> facultyAttendStatusEntitieslist = getJdbcTemplate()
				.query(dquery,
						new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(
								FaculityDailyAttendanceReportEntity.class));
		return facultyAttendStatusEntitieslist;
	}


	@Override
	public List<FacultySalaryMasterEntity> findSalaryHistory(String empid) {
		boolean bbb = TransactionSynchronizationManager
				.isActualTransactionActive();
		if (bbb) {
			if(logger.isDebugEnabled()){
				logger.debug("____Ahahahah Spring tx is working and discuss later______");
			}
		}
		/*
		 * java.util.Date date = new java.util.Date(); String dtformat= "MMM";
		 * SimpleDateFormat sdf = new SimpleDateFormat(dtformat); Calendar c =
		 * Calendar.getInstance(); int monthMaxDays =
		 * c.getActualMaximum(Calendar.DAY_OF_MONTH);
		 * 
		 * int monthMaxDays = 30; String cdate =
		 * DateUtils.getCurrentDateSQLDB();
		 * 
		 * String cntquery =
		 * "SELECT COUNT(*) FROM holiday_entry_tbl WHERE cdate<'" + cdate + "'";
		 * int numHol = super.getJdbcTemplate().queryForObject(cntquery,
		 * Integer.class); int totworkdays = monthMaxDays - numHol; int
		 * leavetotal = 0;
		 * 
		 * String leavetknqry =
		 * "SELECT el.totalDays FROM emp_leave_history AS el WHERE ldateFrom<'"
		 * + cdate + "'"; List<Integer> leavetotallist =
		 * super.getJdbcTemplate().queryForList( leavetknqry, Integer.class);
		 * 
		 * for (Integer i : leavetotallist) { leavetotal += i; }
		 * 
		 * int daysworked = totworkdays - leavetotal;
		 * 
		 * String insquery =
		 * "insert into faculty_salary_table values(?,?,?,?,?,?,?,?,?,?,?)";
		 * Object[] data = new Object[] { Integer.parseInt(empid), cdate,
		 * daysworked, leavetotal, totworkdays, 1000, "CS", "paid in Full",
		 * cdate, cdate, "null" }; super.getJdbcTemplate().update(insquery,
		 * data);
		 */

		int eidfin = Integer.parseInt(empid);
		String query = "SELECT sal.empid as eid,sal.month,sal.daysworked,sal.noleaves,sal.totworkdays,sal.salpaid,sal.comment FROM admin_salary_table AS sal WHERE sal.empid="
				+ eidfin + "";
		List<FacultySalaryMasterEntity> facultySalaryMasterEntitieslist = super
				.getJdbcTemplate().query(
						query,
						new BeanPropertyRowMapper<FacultySalaryMasterEntity>(
								FacultySalaryMasterEntity.class));
		return facultySalaryMasterEntitieslist;
	}

	

	@Override
	public FaculityLeaveMasterEntity findLeaveAppData(String empid/*,String leaveMonth*/) {
		//	String lquery = "select e.id as empNo,e.name,e.designation,e.department,e.type,eb.totalCL,eb.totalSL,eb.totalEL,eb.od,e.paddress as address from emp_db as e,emp_leave_balance as eb where (eb.empNo=e.id and e.id=?) and MONTH(eb.leaveMonth)=?";
		String lquery = "select e.id as empNo,e.name,e.designation,e.department,e.type,eb.totalCL,eb.totalSL,eb.totalEL,eb.od,e.paddress as address from emp_db as e,emp_leave_balance as eb where (eb.empNo=e.id and e.id=?) ";

		FaculityLeaveMasterEntity faculityLeaveMasterEntity = (FaculityLeaveMasterEntity) super
				.getJdbcTemplate().queryForObject(
						lquery,
						new Object[] { empid/*,leaveMonth*/ },
						new BeanPropertyRowMapper(
								FaculityLeaveMasterEntity.class));
		//logger
		System.out.println(faculityLeaveMasterEntity.getTotalCL());
		return faculityLeaveMasterEntity;
	}

	@Override
    public void addLeaveEntry(
            FaculityLeaveMasterEntity faculityLeaveMasterEntity) {
        // TODO Auto-generated method stub
        System.out.println("before insert query" + faculityLeaveMasterEntity.getLeaveFrom());
        System.out.println("before insert query" + faculityLeaveMasterEntity.getMobile());
        String sql = "insert into emp_leave_requests_tbl(leaveFrom,leaveTo,totalDays,leaveType,description,purpose,reason,leaveCategory,reportingManager,cCTo,address,leaveMeeting,mobile) "
        		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] data = new Object[] { 
                faculityLeaveMasterEntity.getLeaveFrom(),
                faculityLeaveMasterEntity.getLeaveTo(),
                faculityLeaveMasterEntity.getTotalDays(),
                faculityLeaveMasterEntity.getLeaveType(),
                faculityLeaveMasterEntity.getDescription(),
                faculityLeaveMasterEntity.getPurpose(),
                faculityLeaveMasterEntity.getReason(),
                faculityLeaveMasterEntity.getLeaveCategory(),
                faculityLeaveMasterEntity.getReportingManager(),
                faculityLeaveMasterEntity.getCcTo(),
                faculityLeaveMasterEntity.getAddress(),
                faculityLeaveMasterEntity.getLeaveMeeting(),
                faculityLeaveMasterEntity.getMobile()
        };
        // firing the query
        super.getJdbcTemplate().update(sql, data);
    }
	
	@Override
	public List<String> findRelation(String manager) {
		String query = "select name from emp_db where id in (select employee_id from emp_manager_relation_tb where manager_id = ?)";
		List<String> employee = super.getJdbcTemplate().queryForList(query,new Object[]{manager},String.class);
		System.out.println(employee);
		return employee;
	}
	
	@Override
	public String applyAddendumLeave(FaculityLeaveMasterEntity al) {
		String query = "insert into emp_leave_requests_tbl (mobile, name, leaveFrom, leaveTo, totalDays,leaveType,purpose,"
				+ "leaveCategory,leaveMeeting,reportingManager, address, reason,description,cCTo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object data[] = new Object[] {al.getMobile(),al.getName(),al.getLeaveFrom(),al.getLeaveTo(),
				al.getTotalDays(),al.getLeaveType(),al.getPurpose(),al.getLeaveCategory(),al.getLeaveMeeting(),
				al.getReportingManager(), al.getAddress(),al.getReason(),al.getDescription(),al.getCcTo()};
		super.getJdbcTemplate().update(query,data);
		return "success";
	}

	@Override
	public byte[] findPhotoByEmpName(String name) {
		String query = "select * from emp_db where name='" + name + "'";
		FaculityLeaveMasterEntity faculityLeaveMasterEntity = new FaculityLeaveMasterEntity();
		try {
			faculityLeaveMasterEntity = (FaculityLeaveMasterEntity) super
					.getJdbcTemplate().queryForObject(
							query,
							new BeanPropertyRowMapper(
									FaculityLeaveMasterEntity.class));
		} catch (EmptyResultDataAccessException exception) {
			exception.printStackTrace();
		}
		return faculityLeaveMasterEntity.getImage();

	}

	@Override
	public String enterLeaveHistory(FaculityLeaveMasterVO fl) {

		String query = "insert into emp_leave_history (empNo,deptt,leaveType,ldateFrom,ldateTo,totalDays,purpose,addressTelNoLeave,empName,doe,dom,description,approvedBy,leaveMeeting,leaveCategory,lstatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object data[] = new Object[] { fl.getEmpNo(), fl.getDepartment(),
				fl.getLeaveType(), fl.getLeaveFrom(), fl.getLeaveTo(),
				fl.getTotalDays(), fl.getPurpose(), fl.getAddress()+"  mobile:"+fl.getMobile(),
				fl.getName(), new Date(), new Date(), fl.getDescription(),"NA",
				fl.getLeaveMeeting(), fl.getLeaveCategory(),"PENDING"};
		super.getJdbcTemplate().update(query, data);
		return "success";

	}

	@Override
	public List<FaculityLeaveMasterEntity> findAllLeaveHistory() {
		String query = "select e.empNo,e.ldateFrom as leaveFrom,e.ldateTo as leaveTo,e.empName as name,e.deptt as department,e.lstatus,e.leaveType,e.totalDays,e.doe, em.designation from emp_leave_history as e,emp_db as em where e.empNo=em.id order by e.doe desc";

		List<FaculityLeaveMasterEntity> facultyLeaveHistories = super
				.getJdbcTemplate().query(
						query,
						new BeanPropertyRowMapper(
								FaculityLeaveMasterEntity.class));
		return facultyLeaveHistories;
	}

	@Override
	public List<FaculityLeaveMasterEntity> findAllEmpDb() {
		String query = "select e.id as empNo,e.name,e.designation,e.department,e.type,e.doe,e.dom,e.description,e.type as leaveType,e.category as leaveCategory,e.paddress as address from emp_db as e";
		List<FaculityLeaveMasterEntity> allEmpDb = super.getJdbcTemplate()
				.query(query,
						new BeanPropertyRowMapper(
								FaculityLeaveMasterEntity.class));
		return allEmpDb;
	}

	@Override
	public String deleteLeaveHistory(String name, String date) {
		String query = "delete from emp_leave_history where empNo=? and ldateFrom=?";
		Object data[] = new Object[] { name, date };
		super.getJdbcTemplate().update(query, data);
		return "deleted";
	}

	@Override
	public String addEmployeeManulAttendance(
			ManualAttendanceEntity manualAttendanceEntity) {

		/*String intime = manualAttendanceEntity.getIntime();
		String intimeTokens[] = intime.split(":");
		Time wintime = new Time(Integer.parseInt(intimeTokens[0]),
				Integer.parseInt(intimeTokens[1]), 0);

		String outtime=manualAttendanceEntity.getOuttime();
		String outtimeTokens[] = outtime.split(":");
		Time wouttime = new Time(Integer.parseInt(outtimeTokens[0]),
				Integer.parseInt(outtimeTokens[1]), 0);


		System.out.println(manualAttendanceEntity);
		// match this fid is valid or not
		Time curretTime = DateUtils.getCurrentTime();

		// Computing in status for employee.
		InOutTimeEntity inOutTimeVO = getJdbcTemplate().queryForObject(
				FaculityQuery.SELECT_CURRENT_ORGANIZATION_TIME,
				new BeanPropertyRowMapper<InOutTimeEntity>(
						InOutTimeEntity.class));

		String inStatus = BaoConstants.NORMAL_STATUS;
		if (wintime.getTime() > inOutTimeVO.getLatein().getTime()) {
			inStatus = BaoConstants.LATE_IN_STATUS;
		}

		String outStatus = BaoConstants.NORMAL_STATUS;
		if (wouttime.getTime() <= inOutTimeVO.getLatein().getTime()) {
			outStatus = BaoConstants.EARLY_OUT_STATUS;
		}

		String status=BaoConstants.NORMAL_STATUS;
		//computing the final status
		if(inStatus.equals(BaoConstants.LATE_IN_STATUS) && outStatus.equals(BaoConstants.EARLY_OUT_STATUS)){
			status=BaoConstants.LATE_IN_STATUS+"-"+BaoConstants.EARLY_OUT_STATUS;
		}else if(inStatus.equals(BaoConstants.NORMAL_STATUS) && outStatus.equals(BaoConstants.EARLY_OUT_STATUS)){
			status=BaoConstants.EARLY_OUT_STATUS;
		} else if(inStatus.equals(BaoConstants.LATE_IN_STATUS) && outStatus.equals(BaoConstants.NORMAL_STATUS)){
			status=BaoConstants.LATE_IN_STATUS;
		}

		try {
		// Here we have two use cases
		// this is entry first entry for today for the employee
		Object[] qdata = new Object[] { manualAttendanceEntity.getEid(),
				manualAttendanceEntity.getCdate(),
				wintime,wouttime,status,
				DateUtils.getCurrentDateInSQLFormat(), inStatus,outStatus,"MANUAL",
				"FULLDAY", "YES", "Welcome sir", "NotSent", "admin" };
			getJdbcTemplate()
					.update("insert into faculity_att_tab (fid,cdate,intime,outtime,status,dom,intimestatus,outtimestatus,attmode,detail,present,description,alert,entryby) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							qdata);
		}catch(Exception ex){
			return "NOTDONE";
		}*/
		return "DONE";
	}

	@Override
	public List<FaculityLeaveMasterEntity> findAllPendingLeaveHistory() {
		String query = "select e.empNo,e.ldateFrom as leaveFrom,e.ldateTo as leaveTo,e.empName as name,e.deptt as department,e.lstatus,e.leaveType,e.totalDays,e.doe, em.designation from emp_leave_history as e,emp_db as em where e.empNo=em.id and e.lstatus='RM-APPROVED' order by e.doe desc";
		List<FaculityLeaveMasterEntity> facultyLeaveHistories = super
				.getJdbcTemplate().query(
						query,
						new BeanPropertyRowMapper(
								FaculityLeaveMasterEntity.class));
		return facultyLeaveHistories;
	}





	@Override
	public void updateLeaveHistory(String empNo, String date, String lstatus) {
		String query="update emp_leave_history set lstatus=? where empNo=? and ldateFrom=?";
		Object data[] = new Object[] {lstatus,empNo,date};
		super.getJdbcTemplate().update(query, data);
	}

	@Override
    public List<FaculityLeaveMasterEntity> getReportingManagerList() {
		String query="select id,name,designation,department from emp_db where id in(select manager_id from emp_manager_relation_tb)";
//        String query="select elt.eid as empNo,elt.role,edb.name, edb.designation from faculity_login_tbl as elt,emp_db as edb where elt.role='user' and id=elt.eid";
        List<FaculityLeaveMasterEntity> entities=super.getJdbcTemplate().query(
                query,
                new BeanPropertyRowMapper(
                        FaculityLeaveMasterEntity.class));
        return entities;
    }

	@Override
	public List<FaculityLeaveMasterEntity> getCCToList() {
		String query="select elt.eid as empNo,elt.role,edb.name from faculity_login_tbl as elt,emp_db as edb where elt.role='user' and id=elt.eid";
		List<FaculityLeaveMasterEntity> ccToList=super.getJdbcTemplate().query(
				query,
				new BeanPropertyRowMapper(
						FaculityLeaveMasterEntity.class));
		return ccToList;
	}

	@Override
	public String enterRmLeaveHistory(
			FaculityLeaveMasterVO fl) {
		String query = "insert into emp_leave_history (empNo,deptt,leaveType,ldateFrom,ldateTo,totalDays,purpose,addressTelNoLeave,empName,doe,dom,description,approvedBy,leaveMeeting,leaveCategory,lstatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object data[] = new Object[] { fl.getEmpNo(), fl.getDepartment(),
				fl.getLeaveType(), fl.getLeaveFrom(), fl.getLeaveTo(),
				fl.getTotalDays(), fl.getPurpose(), fl.getAddress()+"  mobile:"+fl.getMobile(),
				fl.getName(), new Date(), new Date(), fl.getDescription(),"NA",
				fl.getLeaveMeeting(), fl.getLeaveCategory(),"RM-PENDING"};
		super.getJdbcTemplate().update(query, data);
		return "success";

	}

	@Override
	public List<FaculityLeaveMasterEntity> findAllRmPendingLeaveHistory() {
		String query = "select e.empNo,e.ldateFrom as leaveFrom,e.ldateTo as leaveTo,e.empName as name,e.deptt as department,e.lstatus,e.leaveType,e.totalDays,e.doe, em.designation from emp_leave_history as e,emp_db as em where e.empNo=em.id and e.lstatus='RM-PENDING' order by e.doe desc";
		List<FaculityLeaveMasterEntity> facultyLeaveHistories = super
				.getJdbcTemplate().query(
						query,
						new BeanPropertyRowMapper(
								FaculityLeaveMasterEntity.class));
		return facultyLeaveHistories;
	}
	
	@Override
	public List<FaculityDailyAttendanceReportEntity> showAttendusReport(String date){
		String sql = "select att.fid, att.cdate, e.name, e.fatherName, e.department, att.intime, att.outtime, att.status, att.intimestatus, att.outtimestatus, att.present from faculity_att_tab as att,emp_db as e where (att.fid=e.id and cdate=?)";
		System.out.println("I am showing Data");
		List<FaculityDailyAttendanceReportEntity> fDARE = super.getJdbcTemplate().query(sql,new Object[]{date}, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));
		
		return fDARE;
	}

	@Override
	public List<FaculityDailyAttendanceReportEntity> showAttendusReportByDep(String date, String dep){
		String sql = "select att.fid, att.cdate, e.name, e.fatherName, e.department, att.intime, att.outtime, att.status, att.intimestatus, att.outtimestatus, att.present from faculity_att_tab as att,emp_db as e where (att.fid=e.id and cdate=? and e.department=?)";
		System.out.println("I am showing Data");
		List<FaculityDailyAttendanceReportEntity> fDARE = super.getJdbcTemplate().query(sql,new Object[]{date, dep}, new BeanPropertyRowMapper<FaculityDailyAttendanceReportEntity>(FaculityDailyAttendanceReportEntity.class));		
		return fDARE;
	}

	@Override
	public List<String> searchEmployee(String employeeName) {
		String sQuery = "select name as 'full_name' from emp_db where name like '%"+employeeName+"%'";
		//		String sQuery = "select name from employeeShow_tbl where name like '%"+employeeName+"%'"; 
		List<String> queryf = super.getJdbcTemplate().queryForList(sQuery, String.class);
		return queryf;
	}
	
	@Override
	public List<String> searchEmployeeByManId(String employeeName) {
		String sQuery = "select concat(name, '-', id, '-', designation)  from emp_db where name like '%"+employeeName+"%'";
		//		String sQuery = "select name from employeeShow_tbl where name like '%"+employeeName+"%'"; 
		List<String> queryf = super.getJdbcTemplate().queryForList(sQuery, String.class);
		return queryf;
	}
	
	@Override
	public List<String> searchId(String employeeId) {
		String sQuery = "select concat(name, '-', id, '-', designation)  from emp_db where id like '%"+employeeId+"%'";
		//		String sQuery = "select name from employeeShow_tbl where name like '%"+employeeName+"%'"; 
		List<String> queryf = super.getJdbcTemplate().queryForList(sQuery, String.class);
		return queryf;
	}
	
	
	@Override
	public List<FacultyLeaveApprovalEntity> getLeaveApprovalForManager(int managerId)
	{
		String query = "select * from emp_leave_requests_tbl where manager_id = "+managerId+" and managerApproval = 'Pending'";
		/*String query = "select requestID, name, leaveFrom, leaveTo, totalDays, leaveType"
				+ " from emp_leave_requests_tbl where manager_id = "+managerId+" and managerApproval = 'Pending' ";*/
		List <FacultyLeaveApprovalEntity> facultyLeaveData = super.getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<FacultyLeaveApprovalEntity>(FacultyLeaveApprovalEntity.class));
		System.out.println("Faculty Leave Data" + facultyLeaveData);
		return facultyLeaveData;
		
	}
	@Override
	public String updateLeaveApprovalForManager(int requestID, String managerApproval)
	{
		String query = "update emp_leave_requests_tbl set managerApproval = ? where requestID = ?";
		Object employeeLeaveApproval[]= new Object[]{managerApproval,requestID};
		super.getJdbcTemplate().update(query, employeeLeaveApproval);
		return"Manager apprvoal persisted";
	}


	@Override
	public ReportingManagerEntity findReportingManagerByEmpId(String employeeId) {
		String query="select id,name,designation,department from emp_db where id in(select manager_id from emp_manager_relation_tb where employee_id='"+employeeId+"')";
		ReportingManagerEntity faculityLeaveMasterEntity=null;
		try {
			faculityLeaveMasterEntity = (ReportingManagerEntity) super
					.getJdbcTemplate().queryForObject(query,new BeanPropertyRowMapper(ReportingManagerEntity.class));
		} catch (EmptyResultDataAccessException exception) {
			exception.printStackTrace();
		}
		return faculityLeaveMasterEntity;
	}




}