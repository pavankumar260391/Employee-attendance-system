package com.bas.employee.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bas.admin.dao.entity.FacultyMonthlyAttendanceStatusEntity;
import com.bas.admin.service.BasSchedulerService;
import com.bas.admin.web.controller.form.DepartmentForm;
import com.bas.admin.web.controller.form.FaculityDailyAttendanceReportVO;
import com.bas.admin.web.controller.form.FacultyMonthlyAttendanceStatusVO;
import com.bas.admin.web.controller.form.HolidayEntryForm;
import com.bas.common.constant.NavigationConstant;
import com.bas.common.constant.PageTitleContant;
import com.bas.common.util.DateUtils;
import com.bas.employee.service.BasFacultyService;
import com.bas.employee.utility.MonthUtility;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.FacultyWorkingDaysVO;
import com.bas.employee.web.controller.form.LoginForm;
import com.bas.employee.web.controller.form.ManualAttendanceVO;

/**
 * 
 * @author nagendra
 * This is controller which will give 
 * you attandance status for the faculty.
 *
 */
@Controller
public class AttendStatusController {

	@Autowired
	@Qualifier("BasFacultyServiceImpl")
	private BasFacultyService basFacultyService;
	
	@Autowired
	@Qualifier("BasSchedulerServiceImpl")
	private BasSchedulerService basSchedulerService; 


	@RequestMapping(value="/attendanceStatus",method=RequestMethod.GET)
	public String showAttendStatus( Model model,HttpSession session)
	{
		MonthUtility monthUtility = new MonthUtility();
		monthUtility.setMonth(monthUtility.getDate());
		model.addAttribute("monthUtility", monthUtility);		
		model.addAttribute("monthList", monthUtility.getMonthList(monthUtility.getDate()));
		List<FaculityDailyAttendanceReportVO> facultyAttendStatusVOlist=basFacultyService.findAttendStatus("801",monthUtility.getDate());
		model.addAttribute("facultyAttendStatusVOlist", facultyAttendStatusVOlist);		
	    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.ATTENDENCE_STATUS);
		return NavigationConstant.EMPLOYEE_PREFIX_PAGE+NavigationConstant.ATTEND_STATUS_PAGE;
	}

	/*
	 * PK
	 */
	
	//New
	@RequestMapping(value = "/employeeAttendance", method = RequestMethod.GET)
	public String currentAttendus(HttpServletRequest request, Model model, HttpSession session) throws ParseException {
	
		int noOfWorkingDays = DateUtils.getNoOfDaysForCurrentMonth();
		int currYear = DateUtils.getCurrentYear();
		int month = new Date().getMonth()+1;
		Calendar c = Calendar.getInstance();		
	    LoginForm loginForm=(LoginForm)session.getAttribute(NavigationConstant.USER_SESSION_DATA);				
		List<FaculityDailyAttendanceReportVO> facultyAttendStatusVO = basFacultyService.getAttStatusByDate(currYear+"-"+month+"-01",currYear+"-"+month+"-"+noOfWorkingDays,loginForm.getEid());
		int noOfDaysWorked = facultyAttendStatusVO.size();
		List<HolidayEntryForm> holidays = basFacultyService.getHolidays(month);		
		List<FacultyMonthlyAttendanceStatusVO> n = new ArrayList<FacultyMonthlyAttendanceStatusVO>();
		int noOfHolidays = holidays.size();
		//List<FaculityLeaveMasterVO> approvalStatus = basFacultyService.getLeaveApproval(NavigationConstant.USER_SESSION_DATA);
		
		for(FaculityDailyAttendanceReportVO f : facultyAttendStatusVO){
				FacultyMonthlyAttendanceStatusVO lst = new FacultyMonthlyAttendanceStatusVO();
				//boolean val = isDatePresent(f.getCdate(),approvalStatus);				
				lst.setCdate(f.getCdate());
				lst.setIntime(f.getIntime());
				lst.setOuttime(f.getOuttime());
				lst.setIntimestatus(f.getIntimestatus());
				lst.setOuttimestatus(f.getOuttimestatus());
				lst.setDescription("");
				lst.setPresent(f.getPresent());
				if(f.getIntimestatus().equals(NavigationConstant.LATE_IN_STATUS) && f.getOuttimestatus().equals(NavigationConstant.EARLY_OUT_STATUS))
					lst.setColor("both");
				if(f.getIntimestatus().equals(NavigationConstant.LATE_IN_STATUS))
					lst.setColor(NavigationConstant.LATE_IN_COLOR);
				else{
					if((f.getOuttimestatus().equals(NavigationConstant.EARLY_OUT_STATUS)))
					  lst.setColor(NavigationConstant.EARLY_OUT_COLOR);
					else
					  lst.setColor(NavigationConstant.NORMAL_COLOR);
				}
				n.add(lst);
		} 
		for(HolidayEntryForm h : holidays){
			FacultyMonthlyAttendanceStatusVO lst2 = new FacultyMonthlyAttendanceStatusVO();
			String[] d = h.getHolidayDate().toString().split(" ");
			String dte = d[0];
			lst2.setCdate(dte);
			lst2.setHolidayDate(h.getHolidayDate());
			lst2.setIntime("----------");
			lst2.setOuttime(h.getDescription());
			lst2.setIntimestatus("---------");
			lst2.setOuttimestatus("----------");
			lst2.setDescription(h.getDescription());
			lst2.setPresent("----------");
			lst2.setColor(NavigationConstant.HOLIDAY_COLOR);
			n.add(lst2);
		}
		int noOfLwp = 0;
		List<FaculityLeaveMasterVO> leave_history = basFacultyService.LeaveHistory(currYear+"-"+month+"-01",currYear+"-"+month+"-"+noOfWorkingDays,loginForm.getEid());
		int noOfApprovedLeaves = leave_history.size();
		for(FaculityLeaveMasterVO lh : leave_history){
			for(int i=0; i< lh.getTotalDays(); i++){
				
				String myDate = DateUtils.getCurrentCalendarDate(lh.getLeaveFrom());
				String date = DateUtils.nextDate(myDate,i);
				int m = DateUtils.twoDateDifference(currYear+"-"+month+"-"+noOfWorkingDays,date);
				if(m < 0) break;
				FacultyMonthlyAttendanceStatusVO lst = new FacultyMonthlyAttendanceStatusVO();
				lst.setCdate(date);
				lst.setIntime("----------");
				lst.setOuttime(lh.getLstatus());
				lst.setIntimestatus("---------");
				lst.setOuttimestatus("----------");
				lst.setPresent(lh.getLeaveType());
				lst.setDescription(lh.getPurpose());
				if(lh.getLeaveType().equals("LWP")){
					lst.setColor(NavigationConstant.LEAVE_COLOR);
					noOfLwp++;
				}
				n.add(lst);
			}
		}
		Collections.sort(n);
		ManualAttendanceVO empDetails = basFacultyService.findEmployeeDataForAttendance(loginForm.getEid());
		
		model.addAttribute("noOfWorkingDays", noOfWorkingDays);
		model.addAttribute("noOfLwp", noOfLwp);
		model.addAttribute("noOfApprovedLeaves", noOfApprovedLeaves);
		model.addAttribute("noOfHolidays", noOfHolidays);

		model.addAttribute("noOfDaysWorked", noOfDaysWorked);
		model.addAttribute("empDetails", empDetails);
		model.addAttribute("EmptyfacultyAttendStatusVO", facultyAttendStatusVO);
		model.addAttribute("faculityDailyAttendanceReportVOs", n);

		return NavigationConstant.EMPLOYEE_PREFIX_PAGE
				+ NavigationConstant.VIEW_ATTENDANCE;	
	}
	
	@RequestMapping(value="/computeNoOfDaysForSalaryInAMonth", method=RequestMethod.GET)
	public String computeNoOfDaysForSalaryInAMonth(){
		try {
			basSchedulerService.computeNoOfDaysForSalaryInAMonth();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return NavigationConstant.EMPLOYEE_PREFIX_PAGE
				+ NavigationConstant.VIEW_ATTENDANCE;	
	}
	
	private boolean isDatePresent(String date, List<FaculityLeaveMasterVO> list){
		for(FaculityLeaveMasterVO f : list){
			for(int i=0; i< f.getTotalDays(); i++){
				
				String myDate = DateUtils.getCurrentCalendarDate(f.getLeaveFrom());
				String dat = DateUtils.nextDate(myDate,i);
				
				int m = DateUtils.twoDateDifference(dat,date);
				if(m == 0){
					return true;
				}
			}
		}
		return true;
	}
	
	@RequestMapping(value = "/viewAttendenceByDate", method = RequestMethod.GET)
	@ResponseBody public List<FacultyMonthlyAttendanceStatusVO> viewAttendanceByDate(@RequestParam("month") String month, @RequestParam("year") String year,@RequestParam("computeDays") String compute,HttpSession session){
		int noOfWorkingDays = DateUtils.getNoOfDaysForCurrentMonth();
		String temp = month.substring(0, 3);
		String m = DateUtils.getMonthIndexByName(temp);
		int mnth = Integer.parseInt(m);
	    LoginForm loginForm=(LoginForm)session.getAttribute(NavigationConstant.USER_SESSION_DATA);				
		List<FaculityDailyAttendanceReportVO> facultyAttendStatusVO = basFacultyService.getAttStatusByDate(year+"-"+mnth+"-01",year+"-"+mnth+"-"+noOfWorkingDays,loginForm.getEid());
		List<HolidayEntryForm> holidays = basFacultyService.getHolidays(mnth);		
		List<FaculityLeaveMasterVO> leave_history = basFacultyService.SortLeaveByDate(year+"-"+mnth+"-01",year+"-"+mnth+"-"+noOfWorkingDays,loginForm.getEid());
		List<FacultyMonthlyAttendanceStatusVO> n = new ArrayList<FacultyMonthlyAttendanceStatusVO>();
		int noOfDaysWorked = facultyAttendStatusVO.size();
		
		int noOfHolidays = holidays.size();
		int noOfApprovedLeaves = leave_history.size();

		
		for(FaculityDailyAttendanceReportVO f : facultyAttendStatusVO){
				FacultyMonthlyAttendanceStatusVO lst = new FacultyMonthlyAttendanceStatusVO();
				lst.setCdate(f.getCdate());
				lst.setIntime(f.getIntime());
				lst.setOuttime(f.getOuttime());
				lst.setIntimestatus(f.getIntimestatus());
				lst.setOuttimestatus(f.getOuttimestatus());
				lst.setDescription("");
				if(f.getIntimestatus().equals(NavigationConstant.LATE_IN_STATUS) && f.getOuttimestatus().equals(NavigationConstant.EARLY_OUT_STATUS))
					lst.setColor("both");
				if(f.getIntimestatus().equals(NavigationConstant.LATE_IN_STATUS))
					lst.setColor(NavigationConstant.LATE_IN_COLOR);
				else{
					if((f.getOuttimestatus().equals(NavigationConstant.EARLY_OUT_STATUS)))
					  lst.setColor(NavigationConstant.EARLY_OUT_COLOR);
					else
					  lst.setColor(NavigationConstant.NORMAL_COLOR);
				}
				n.add(lst);
		}
		for(HolidayEntryForm h : holidays){
			FacultyMonthlyAttendanceStatusVO lst2 = new FacultyMonthlyAttendanceStatusVO();
			String[] d = h.getHolidayDate().toString().split(" ");
			String dte = d[0];
			lst2.setCdate(dte);
			lst2.setHolidayDate(h.getHolidayDate());
			lst2.setIntime("----------");
			lst2.setOuttime(h.getDescription());
			lst2.setIntimestatus("---------");
			lst2.setOuttimestatus("----------");
			lst2.setDescription(h.getDescription());
			lst2.setPresent(h.getHolidayType());
			lst2.setColor(NavigationConstant.HOLIDAY_COLOR);
			n.add(lst2);
		}
int noOfLwp = 0;
		for(FaculityLeaveMasterVO lh : leave_history){
		  for(int i=0; i< lh.getTotalDays(); i++){
			FacultyMonthlyAttendanceStatusVO lst = new FacultyMonthlyAttendanceStatusVO();
			String myDate = null;
			String date = null;
			myDate = DateUtils.getCurrentCalendarDate(lh.getLeaveFrom());
			date = DateUtils.nextDate(myDate,i);
			int monthDifference = DateUtils.twoDateDifference(year+"-"+Integer.parseInt(DateUtils.getMonthIndexByName(month.substring(0,3)))+"-"+noOfWorkingDays,date);
			if(monthDifference < 0) break;
			lst.setCdate(date);
			lst.setIntime("----------");
			lst.setOuttime("APPROVED HOLIDAY");
			lst.setIntimestatus("---------");
			lst.setOuttimestatus("----------");
			lst.setPresent(lh.getLeaveType());
			if(lh.getLeaveType().equals("LWP")){
				lst.setColor(NavigationConstant.LEAVE_COLOR);
				noOfLwp++;
			}
			n.add(lst);
		}
	}
	
		if(compute.equals("computeDays")){
		List<FacultyMonthlyAttendanceStatusVO> newList = new ArrayList<FacultyMonthlyAttendanceStatusVO>();
		FacultyMonthlyAttendanceStatusVO lst = new FacultyMonthlyAttendanceStatusVO();
		lst.setNoOfApprovedLeaves(noOfApprovedLeaves);
		lst.setNoOfDaysWorked(noOfDaysWorked);
		lst.setNoOfHolidays(noOfHolidays);
		lst.setNoOfLwp(noOfLwp);
		newList.add(lst);
		return newList;
	}
		Collections.sort(n);
		return n;
	}
	//END
	
	@RequestMapping(value="/retreiveEmployeeInfo",method=RequestMethod.POST)
	public @ResponseBody List<FaculityDailyAttendanceReportVO> showAttendStatus(@RequestParam(value="dateInfo") String monthVal,HttpSession session)
	{	
		LoginForm loginForm=(LoginForm)session.getAttribute(NavigationConstant.USER_SESSION_DATA);		
		List<FaculityDailyAttendanceReportVO> facultyAttendStatusVOlist=basFacultyService.findAttendStatus("801",Integer.parseInt(monthVal));		
	/*	System.out.println(facultyAttendStatusVOlist.get(0));*/
		return facultyAttendStatusVOlist;
	}

}
