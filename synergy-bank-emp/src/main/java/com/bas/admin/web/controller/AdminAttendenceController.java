package com.bas.admin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bas.admin.web.controller.form.AttendanceHeaderVO;
import com.bas.admin.web.controller.form.DepartmentForm;
import com.bas.admin.web.controller.form.FaculityDailyAttendanceReportVO;
import com.bas.common.constant.NavigationConstant;
import com.bas.common.constant.PageTitleContant;
import com.bas.common.util.DateUtils;
import com.bas.employee.service.BasFacultyService;
import com.bas.employee.utility.MonthUtility;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.FacultyAttendStatusVO;
import com.bas.employee.web.controller.form.ManualAttendanceVO;
/**
 * 
 * @author Kushal
 * 
 */

@Controller
@Scope("request")
public class AdminAttendenceController {


@Autowired
@Qualifier("BasFacultyServiceImpl")
private BasFacultyService basFacultyService;

@RequestMapping(value = "/adminAttendance", method = RequestMethod.GET)
public String currentAttendus(HttpServletRequest request, Model model) {
	
	List<String> depList = basFacultyService.selectDepartments();	
	depList.add(0, "All");
	DepartmentForm departmentForm = new DepartmentForm();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	FaculityDailyAttendanceReportVO facultyAttendStatusVO = new FaculityDailyAttendanceReportVO();
	String date = format.format(new Date());
	System.out.println(date);
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = basFacultyService.showAttendusReport(date);
	System.out.println("I am showing Attendance");
	model.addAttribute("depList", depList);
	model.addAttribute("departmentForm", departmentForm);	
	model.addAttribute("EmptyfacultyAttendStatusVO", facultyAttendStatusVO);
	model.addAttribute("faculityDailyAttendanceReportVOs", faculityDailyAttendanceReportVOs);
	return NavigationConstant.ADMIN_PREFIX_PAGE
			+ NavigationConstant.ADMIN_ATTENDANCE_PAGE;	
}

@RequestMapping(value = "/adminAttendanceByPastDate", method = RequestMethod.GET)
public @ResponseBody List<FaculityDailyAttendanceReportVO> pastAttendus(@RequestParam(value="pastDate") String pastDate) {
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");	
	SimpleDateFormat formatJSP = new SimpleDateFormat("MM/dd/yyyy");
	String reformattedStr = null;
	try {
		reformattedStr = myFormat.format(formatJSP.parse(pastDate));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = basFacultyService.showAttendusReport(reformattedStr);
	System.out.println("I am showing Attendance from Past");
	return faculityDailyAttendanceReportVOs;	
}
	
@RequestMapping(value = "/adminAttendanceByPastDateAndDep", method = RequestMethod.GET)
public @ResponseBody List<FaculityDailyAttendanceReportVO> pastDateAndDepAttendus(@RequestParam(value="pastDate") String pastDate,
		@RequestParam(value="depName") String department) {
	
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");	
	SimpleDateFormat formatJSP = new SimpleDateFormat("MM/dd/yyyy");
	String reformattedStr = null;
	try {
		reformattedStr = myFormat.format(formatJSP.parse(pastDate));
	} catch (ParseException e) {		
		e.printStackTrace();
	}	
	String AllCheck = "All";
	System.out.println(department);	
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = new ArrayList<FaculityDailyAttendanceReportVO>();
	if(department.equals(AllCheck)){		
		faculityDailyAttendanceReportVOs = basFacultyService.showAttendusReport(reformattedStr);
	}else{
		faculityDailyAttendanceReportVOs = basFacultyService.showAttendusReportByDep(reformattedStr,department);
	}
	System.out.println("I am showing Attendance from Past Dep");
	return faculityDailyAttendanceReportVOs;	
}


/*
 * PK
 */
//Latest for working days

@RequestMapping(value = "/adminViewLwp", method = RequestMethod.GET)
public String viewLwp(Model model, HttpServletRequest request){

	List<String> depList = basFacultyService.selectDepartments();	
	depList.add(0, "All");
	DepartmentForm departmentForm = new DepartmentForm();
	String currDate = DateUtils.getCurrentCalendarDate();
	List<FaculityLeaveMasterVO> lstOfLeaveHistory = basFacultyService.findAllLeaveHistoryForToday(currDate);
	String lwpColor = NavigationConstant.LWP_MARKED_COLOR;

	for(FaculityLeaveMasterVO leave : lstOfLeaveHistory){
			 leave.setColor(lwpColor); 
	}
	
	int totalEmp = lstOfLeaveHistory.size();
	
    model.addAttribute("totalEmp", totalEmp);
    model.addAttribute("depList", depList);
	model.addAttribute("departmentForm", departmentForm);	
	model.addAttribute("EmpWithLwp", lstOfLeaveHistory);

    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.LWP_RECORDS);
	return NavigationConstant.ADMIN_PREFIX_PAGE+NavigationConstant.VIEW_LWP_EMPLOYEES;
}

@RequestMapping(value="/getAllEmpByDept", method = RequestMethod.GET)
public @ResponseBody List<FaculityDailyAttendanceReportVO> findAllEmpByDept(@RequestParam("date") String selectDate, @RequestParam("department") String dept){
	List<FaculityDailyAttendanceReportVO> lst = basFacultyService.findAllEmpByDept(dept);
	//String date = DateUtils.convertStringIntoDateYYYYMMDD(selectDate);
	String date = DateUtils.convertMMDDYYYYInToYYYYMMDD(selectDate);
	System.out.println(date+"-----------------");
	List<FaculityLeaveMasterVO> lstOfLeaveHistory = basFacultyService.findAllLeaveHistoryForToday(date);
	String lwpColor = NavigationConstant.LWP_MARKED_COLOR;
	String disableBtn = NavigationConstant.DISABLE_BUTTON;

	for(FaculityLeaveMasterVO leave : lstOfLeaveHistory){
	  for(FaculityDailyAttendanceReportVO emp : lst){
		  if(emp.getFid() == leave.getEmpNo()){
			 emp.setColor(lwpColor); 
			 emp.setDisableButton(disableBtn);
		  }
	  }
	}
	return lst;
}


@RequestMapping(value = "/adminMarkLwp", method = RequestMethod.GET)
public String markLwpForToday(Model model, HttpServletRequest request){

	List<String> depList = basFacultyService.selectDepartments();	
	depList.add(0, "All");
	DepartmentForm departmentForm = new DepartmentForm();
	FaculityDailyAttendanceReportVO facultyAttendStatusVO = new FaculityDailyAttendanceReportVO();
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = new ArrayList<FaculityDailyAttendanceReportVO>();
	faculityDailyAttendanceReportVOs = basFacultyService.showAttendenceReportForToday();
	String currDate = DateUtils.getCurrentCalendarDate();
	List<FaculityLeaveMasterVO> lstOfLeaveHistory = basFacultyService.findAllLeaveHistoryForToday(currDate);
	String lwpColor = NavigationConstant.LWP_MARKED_COLOR;
	String disableBtn = NavigationConstant.DISABLE_BUTTON;

	for(FaculityLeaveMasterVO leave : lstOfLeaveHistory){
	  for(FaculityDailyAttendanceReportVO emp : faculityDailyAttendanceReportVOs){
		  if(emp.getFid() == leave.getEmpNo()){
			 emp.setColor(lwpColor); 
			 emp.setDisableButton(disableBtn);
		  }
	  }
	}
	int totalEmp = faculityDailyAttendanceReportVOs.size();
	
    model.addAttribute("totalEmp", totalEmp);
    model.addAttribute("depList", depList);
	model.addAttribute("departmentForm", departmentForm);	
	model.addAttribute("EmptyfacultyAttendStatusVO", facultyAttendStatusVO);
	model.addAttribute("todayAttObj", faculityDailyAttendanceReportVOs);

    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.NUMBER_OF_WORKING_DAYS);
	return NavigationConstant.ADMIN_PREFIX_PAGE+NavigationConstant.WORKING_DAY_PAGE;
}

@RequestMapping(value="/markLwpButtonClick", method=RequestMethod.GET)
public @ResponseBody String markLwpForAnEmployee(@RequestParam("id") int id, @RequestParam("description") String comment, Model model){
	String result = basFacultyService.markLwpService(id,comment);
    //model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.NUMBER_OF_WORKING_DAYS);
	//return NavigationConstant.ADMIN_PREFIX_PAGE+NavigationConstant.WORKING_DAY_PAGE;
	
	return result;
}

@RequestMapping(value="/unMarkLwpButtonClick", method=RequestMethod.GET)
public @ResponseBody String unMarkLwpForAnEmployee(@RequestParam("id") int id){
	basFacultyService.unMarkLwpService(id);
	return "success";
}
//End
@RequestMapping(value = "/adminAttendanceToday", method = RequestMethod.GET)
public String pastDateAndDepAttendus(Model model, HttpServletRequest request){

	List<String> depList = basFacultyService.selectDepartments();	
	depList.add(0, "All");
	DepartmentForm departmentForm = new DepartmentForm();
	String date=DateUtils.getCurrentCalendarDate();
	FaculityDailyAttendanceReportVO facultyAttendStatusVO = new FaculityDailyAttendanceReportVO();
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = new ArrayList<FaculityDailyAttendanceReportVO>();
	faculityDailyAttendanceReportVOs = basFacultyService.showAttendenceReportForToday();
	
    model.addAttribute("depList", depList);
	model.addAttribute("departmentForm", departmentForm);	
	model.addAttribute("EmptyfacultyAttendStatusVO", facultyAttendStatusVO);
	model.addAttribute("todayAttObj", faculityDailyAttendanceReportVOs);

    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.TODAYS_ATTENDENCE_TITLE);
	return NavigationConstant.ADMIN_PREFIX_PAGE
			+ NavigationConstant.ADMIN_ATTENDANCE_TODAY;	
}

@RequestMapping(value = "/adminAttendanceTodayByDep", method = RequestMethod.GET)
public @ResponseBody List<FaculityDailyAttendanceReportVO> pastDateAndDepAttendus( Model model, @RequestParam("depName") String department){

	String AllCheck = "All";
	//System.out.println("-----bydep"+department);	
	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = new ArrayList<FaculityDailyAttendanceReportVO>();
	
	if(department.equals(AllCheck)){		
		faculityDailyAttendanceReportVOs = basFacultyService.showAttendenceReportForToday();
	}else{
		faculityDailyAttendanceReportVOs = basFacultyService.showAttendenceReportByDepForToday(department);
	}

	return faculityDailyAttendanceReportVOs;
}


@RequestMapping(value = "/adminAttendanceTodays", method = RequestMethod.GET)
public @ResponseBody List<FaculityDailyAttendanceReportVO> pastDateAndDepAttend( Model model, @RequestParam("checkedIds") String aList){
	int i=0;
	String[] al = aList.split(" ");
	List<String> aLst = new ArrayList<String>();
	for(String a : al){
		aLst.add(a);
	}
 	List<FaculityDailyAttendanceReportVO> faculityDailyAttendanceReportVOs = new ArrayList<FaculityDailyAttendanceReportVO>();	
	faculityDailyAttendanceReportVOs = 	basFacultyService.updateAttendenceForToday(aLst);
	return faculityDailyAttendanceReportVOs;
}
/*
@RequestMapping(value = "/adminAttendanceToday", method = RequestMethod.GET)
public String pastDateAndDepAttendence(Model model) {
	FaculityDailyAttendanceReportVO vo = new FaculityDailyAttendanceReportVO();
	model.addAttribute("departmentForm", vo);
	return NavigationConstant.ADMIN_PREFIX_PAGE
			+ NavigationConstant.ADMIN_ATTENDANCE_TODAY;
}
*/
//End

@RequestMapping(value = "/adminAttendenceView.htm", method = RequestMethod.GET)
public String adminAttendanceSubmitGet(HttpServletRequest request, Model model) {
	return NavigationConstant.ADMIN_PREFIX_PAGE
			+ NavigationConstant.ADMIN_ATTENDANCE_PAGE;
	
}


/*
@RequestMapping(value = "/adminAttendenceView.htm", method = RequestMethod.POST)
public String adminAttendanceSubmit(HttpServletRequest request, Model model) {
	String name = request.getParameter("searchFiled");
	if (name != null) {
		String tokens[] = name.split("-");
		String empid = tokens[1];
		List<FacultyAttendStatusVO> facultyAttendStatusVOs = basFacultyService
				.findAttendStatus(empid,06);
ManualAttendanceVO manualAttendanceVO=basFacultyService.findEmployeeDataForAttendance(empid);
model.addAttribute("manualAttendanceVO", manualAttendanceVO);
model.addAttribute("facultyAttendStatusVOs", facultyAttendStatusVOs);
	
}

	return NavigationConstant.ADMIN_PREFIX_PAGE
			+ NavigationConstant.ADMIN_ATTENDANCE_PAGE;
}*/
	
	
}
