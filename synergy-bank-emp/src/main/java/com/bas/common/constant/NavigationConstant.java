package com.bas.common.constant;

public interface NavigationConstant {		
	
	final public static String MESSAGE_BOARD="messageBoard";
	
	final public static String POST_MESSAGE_BOARD_PAGE="postMessageBoard";
	final public static String MESSAGE_BOARD_INBOX_PAGE="messageBoardInbox";
	
	final public static String LMS_PREFIX_PAGE="WEB-INF/jsp/lms/";
	
	final public static String MANAGER_APPROVE_LEAVE ="managerApproveLeave";
	
	////NEW FOR EMPLOYEE
	final public static String EMPLOYEE_PREFIX_PAGE="WEB-INF/jsp/employee/";
	final public static String EMPLOYEE_HOME_PAGE="employeeHome";
	
	final public static String USER_SESSION_DATA="user_session_data";
	final public static String COMMON_PREFIX_PAGE="WEB-INF/jsp/common/";
	final public static String COMMON_HOME_PAGE="basHomePage";
	final public static String FACULTY_PREFIX_PAGE="WEB-INF/jsp/faculty/";
	final public static String LEAVE_HISTORY_PAGE="leaveHistory";
	final public static String ADMIN_PREFIX_PAGE="WEB-INF/jsp/admin/";
	final public static String FACULTY_REGISTRATION_PAGE="facultyRegistration";
	final public static String FACULTY_EDIT_PAGE="editFaculty";
	final public static String SHOW_REGISTEREDFACULTY_PAGE="showAllFaculty";
	final public static String FACULTY_HOME_PAGE="facultyHome";
	final public static String ADMIN_HOME_PAGE="adminHome";
	final public static String LEAVE_BALANCE_PAGE="leaveBalance";
	final public static String ATTEND_STATUS_PAGE="attendanceStatus";	
	final public static String ADMIN_ATTEND_UPDATE_PAGE="adminUpdateAttendance";
	final public static String SALARY_HISTORY_PAGE="facultySalaryHistory";
	final public static String LOGIN_PAGE="login";	
	final public static String RESET_PASSWORD_PAGE="resetPassword"; 
	final public static String ADD_EMPLOYEES="addEmployees"; 
	final public static String PROFILE="profile";
	final public static String SHOW_REGISTEREDFACULTY_WITH_LEAVEHISTORY_PAGE="showAllFacultyWithLeaveHistory";
	final public static String LEAVE_HISTORY_DETAILS_PAGE="leaveHistoryDetails";
	//PK
	final public static String VIEW_ATTENDANCE="viewAttendance";
	//End
	public static final String NATIONAL_HOLIDAY = "National Holiday";
	public static final String REGIONAL_HOLIDAY = "Regional Holiday";
	public static final String NOT_A_PUBLIC_HOLIDAY = "Not a Public Holiday";
	
	final public static String FACULTY_TIME_PAGE="addFacultyTime";
	final public static String FACULTY_LEAVE_APPLY="facultyLeaveApply";

	final public static String LATE_IN_STATUS = "Late";
	final public static String EARLY_OUT_STATUS = "Early";
	
	final public static String LATE_IN_COLOR = "late_in_color";
	final public static String EARLY_OUT_COLOR = "early_out_color";
	final public static String NORMAL_COLOR = "normal_color";
	final public static String HOLIDAY_COLOR = "holiday_color";
	public static final String LEAVE_COLOR = "leave_color";

	//###############ADMIN CONSTANT
	
	//pk for mark lwp 
	final public static String LWP_MARKED_COLOR = "lwpColor";
	final public static String DISABLE_BUTTON = "disableButton";
	
	final public static String WORKING_DAY_PAGE = "empWorkingDays";
	final public static String ADD_DEPARTMENT_PAGE="addDepartment";
	final public static String LEAVE_APPLY_PAGE="leaveApply";
	final public static String MANUAL_ATTENDANCE_PAGE="manualAttendance";
	final public static String ADMINSALARY_HISTORY_PAGE="adminSalaryHistory";
	final public static String ADMINSALARY_HISTORY_PAGEDISP="adminSalaryHistoryDisp";
	final public static String ADMINSALARY_HISTORY_WRKDAYS="adminSalaryHistoryDispWorkdys";
	final public static String ADMINSALARY_HISTORY_BYDATE="adminSalaryHistoryByDate";
	final public static String ADD_DESIGNATION_PAGE = "addDesignation";
	final public static String ADD_EMPLOYEE_TYPE="addEmployeeType";
	final public static String ADD_HOLIDAY_ENTRY="HolidayEntry";
	final public static String ADD_HOLIDAY_CALENDAR="addHolidayCalendar";
	final public static String ADD_HOLIDAY_ENTRY_VIEW="viewHolidayCalender";
	final public static String SHOW_ALL_LEAVE_HISTORY="showAllLeaveHistory";
	final public static String ADD_CATEGORY_PAGE="addCategory";
	final public static String LEAVE_REASON="addLeaveReason";
	final public static String LEAVE_TYPE="addLeaveType";
	final public static String ORG_TIME_PAGE="organizationTime";
	final public static String ADMIN_LEAVEMASTRINIT_PAGE="leaveMasterInit";
	public static final String ADMIN_ATTENDANCE_PAGE = "adminAttendance";
	public static final String SHOW_ALL_PENDING_LEAVE_HISTORY = "approveLeaves";
	public static final String SHOW_ALL_PENDING_RM_LEAVE_HISTORY = "approveRmLeaves";
	public static final String REPORTEE_MANAGEMENT = "reporteeManagement";
	final public static String LEAVE_APPLY_MANAGER = "leaveApplyManager";

	public static final String ADMIN_ATTENDANCE_TODAY = "showEmpAttendenceForToday";

	public static final String VIEW_LWP_EMPLOYEES = "viewLwpEmployees";

	public static final Object LWP = "LWP";

	public static final Object LEAVE_CATEGORY_FULLDAY = "FULLDAY";

	public static final Object LEAVE_STATUS_APPROVED = "APPROVED";

	public static final Object LEAVE_STATUS_NOT_APPROVED = "NOT APPROVED";
	
}

