package com.bas.employee.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.bas.common.constant.NavigationConstant;
import com.bas.common.constant.PageTitleContant;
import com.bas.employee.service.BasFacultyService;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.LoginForm;
@Controller
public class FacultyLeaveHistoryController {

	@Autowired
	@Qualifier("BasFacultyServiceImpl")
	private BasFacultyService basFacultyService;

	/*@RequestMapping(value="/leaveHistory",method=RequestMethod.GET)
	public String showLeaveHistory(Model model,HttpSession session) {
		LoginForm loginForm=(LoginForm)session.getAttribute(NavigationConstant.USER_SESSION_DATA);
//		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist=basFacultyService.findLeaveHistory(loginForm.getEid());
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist=basFacultyService.findLeaveHistory("555");
		model.addAttribute("faculityLeaveMasterVOslist", faculityLeaveMasterVOslist);
//		return NavigationConstant.FACULTY_PREFIX_PAGE+NavigationConstant.LEAVE_HISTORY_PAGE;
		return NavigationConstant.LMS_PREFIX_PAGE+NavigationConstant.LEAVE_HISTORY_PAGE;
	}*/
	
	/**
	 * PK
	 * @throws IOException 
	 */
	@RequestMapping(value="/leaveHistoryById",method=RequestMethod.GET)
	public String showLeaveHistoryById(Model model,@RequestParam("id") String id, HttpServletResponse response) throws IOException {
		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist=basFacultyService.findLeaveHistory(id);
		model.addAttribute("faculityLeaveMasterVOslist", faculityLeaveMasterVOslist);
	    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.LEAVE_HISTORY);
		return NavigationConstant.LMS_PREFIX_PAGE+NavigationConstant.LEAVE_HISTORY_DETAILS_PAGE;
	}
	
	@RequestMapping(value="/image",method=RequestMethod.GET)
	public void showImage(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
		byte[] photo = basFacultyService.findImgById(id);
		if (photo != null) {
			//System.out.println("found photo");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(photo);
			outputStream.flush();
			outputStream.close();
		}
	}
	
	@RequestMapping(value="/filter",method=RequestMethod.GET)
	@ResponseBody public List<FaculityLeaveMasterVO> filterByDate(Model model,@RequestParam("empId") String empId, @RequestParam("date1") String d1, @RequestParam("date2") String d2) throws IOException {
		String[] str = empId.split(":");
		String eid = str[1].trim();
		SimpleDateFormat myFormat1 = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat formatJSP1 = new SimpleDateFormat("MM/dd/yyyy");
		String reformattedStr1 = null;
		try {
			reformattedStr1 = myFormat1.format(formatJSP1.parse(d1));
		} catch (ParseException e){
			e.printStackTrace();}

		SimpleDateFormat myFormat2 = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat formatJSP2 = new SimpleDateFormat("MM/dd/yyyy");
		String reformattedStr2 = null;
		try {
			reformattedStr2 = myFormat2.format(formatJSP2.parse(d2));
		} catch (ParseException e){
			e.printStackTrace();}

		List<FaculityLeaveMasterVO> faculityLeaveMasterVOslist=basFacultyService.SortLeaveByDate(reformattedStr1, reformattedStr2, eid);
		for(FaculityLeaveMasterVO t : faculityLeaveMasterVOslist){
			System.out.println("leave from : "+t.getLeaveFrom()+" leave to :"+t.getLeaveTo());
		}
		return faculityLeaveMasterVOslist;
		//System.out.println("-------sfsfsfs----------"+eid);
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
             // to actually be able to convert Multipart instance to byte[]
             // we have to register a custom editor
             binder.registerCustomEditor(byte[].class,
                                new ByteArrayMultipartFileEditor());
             // now Spring knows how to handle multipart object and convert them
    }
	//End
	
}
