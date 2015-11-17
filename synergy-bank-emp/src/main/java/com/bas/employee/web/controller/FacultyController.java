package com.bas.employee.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.bas.admin.web.controller.form.EmployeeShowForm;
import com.bas.common.constant.BASApplicationConstants;
import com.bas.common.constant.BASMessageConstant;
import com.bas.common.constant.NavigationConstant;
import com.bas.common.constant.PageTitleContant;
import com.bas.common.util.DateUtils;
import com.bas.common.util.PasswordGenerator;
import com.bas.employee.service.BasFacultyService;
import com.bas.employee.web.controller.form.FaculityLeaveMasterVO;
import com.bas.employee.web.controller.form.FacultyForm;
import com.bas.employee.web.controller.form.LoginForm;
import com.bas.employee.web.controller.form.ReportingManagerVO;
import com.bas.soap.service.email.EMailSenderServiceImpl;
import com.bas.soap.service.email.EmailSenderThread;

@Controller
@Scope("request")
public class FacultyController {

	@Autowired
	@Qualifier("BasFacultyServiceImpl")
	private BasFacultyService basFacultyService;
	
	@Autowired
	@Qualifier("EMailSenderServiceImpl")
	private EMailSenderServiceImpl eMailSenderService;
	
	@RequestMapping(value="/showAllFaculty",method=RequestMethod.GET)
	public String showFacultyDatas(Model model){
	List<FacultyForm> employeeFormsD=basFacultyService.findAllFaculty();
		System.out.println(employeeFormsD);
		model.addAttribute("employeeFormsD", employeeFormsD);
		return com.bas.common.constant.NavigationConstant.ADMIN_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.SHOW_REGISTEREDFACULTY_PAGE;
	}
	
	
	/**
	 * 
	 * PK
	 */
	@RequestMapping(value="/showAllFacultyWithleaveHistory",method=RequestMethod.GET)
	public String showFacultyWithLeaveHistory(Model model){
	List<FacultyForm> employeeFormsD=basFacultyService.findAllFaculty();
		model.addAttribute("employeeForms", employeeFormsD);
	    model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.LEAVE_HISTORY);
		return com.bas.common.constant.NavigationConstant.ADMIN_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.SHOW_REGISTEREDFACULTY_WITH_LEAVEHISTORY_PAGE;
	}
	
	@RequestMapping(value="/images",method=RequestMethod.GET)
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
	//End
	
	@ModelAttribute("managerList")
	public List<String> getReportingManagerList() {
		List<FaculityLeaveMasterVO> reportingManagerList = new ArrayList<FaculityLeaveMasterVO>();
		reportingManagerList=basFacultyService.getReportingManagerList();
		List<String> managerList=new ArrayList<String>();
		for (FaculityLeaveMasterVO faculityLeaveMasterVO : reportingManagerList) {
			managerList.add(faculityLeaveMasterVO.getName()+ "-" + faculityLeaveMasterVO.getId()+"-"+faculityLeaveMasterVO.getDesignation());
		}
		return managerList;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String employeeProfile(Model model,HttpSession session) {
		LoginForm loginForm=(LoginForm)session.getAttribute(NavigationConstant.USER_SESSION_DATA);
		FaculityLeaveMasterVO faculityLeaveMasterVO = new FaculityLeaveMasterVO();
		if(loginForm!=null){
			ReportingManagerVO reportingManagerVO=basFacultyService.findReportingManagerByEmpId(loginForm.getEid());	
			model.addAttribute("reportingManagerVO", reportingManagerVO);
		}
		model.addAttribute("faculityLeaveMasterVO", faculityLeaveMasterVO);	
		model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.EMPLOYEE_PROFILE_PAGE);
		return NavigationConstant.EMPLOYEE_PREFIX_PAGE
				+ NavigationConstant.PROFILE;
	}
	
	@RequestMapping(value = "/addEmployees", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.ADD_EMPLOYEE_PAGE);
		model.addAttribute("depList", basFacultyService.selectDepartments());
		model.addAttribute("desigList", basFacultyService.selectDesignations()); 
		model.addAttribute("reportingManagerList", basFacultyService.selectReportingManagers());
		
		FacultyForm facultyForm=new FacultyForm();
		model.addAttribute("facultyForm", facultyForm);
		//model.addattribute("desglist", service.getdesignation)
		return NavigationConstant.ADMIN_PREFIX_PAGE
				+ NavigationConstant.ADD_EMPLOYEES;
	}
	
	@RequestMapping(value = "/addEmployees", method = RequestMethod.POST)
	public String addEmployeePost(Model model,String firstName,String surname,String gender,String dob,String email,String mobile,String employeeType,String designation,String department,String reportingManager) {
		FacultyForm facultyForm=new FacultyForm();
		facultyForm.setName(firstName+" "+surname);
		facultyForm.setSex(gender);
		facultyForm.setDob(dob);
		facultyForm.setEmail(email);
		facultyForm.setPhoneNumber(mobile);
		facultyForm.setType(employeeType);
		facultyForm.setDesignation(designation);
		facultyForm.setDepartment(department);
		facultyForm.setReportingManager(reportingManager);
		facultyForm.setDoe(DateUtils.getCurrentJavaDate());
		facultyForm.setDom(DateUtils.getCurrentJavaDate());
		//Write code to generate userid and password
		PasswordGenerator passwordGenerator = new PasswordGenerator(6);
		char password[]=passwordGenerator.get();
		String strPassword=new String(password);
		facultyForm.setPassword(strPassword);
		facultyForm.setRole(BASApplicationConstants.EMPLOYEE_ROLE);
		String newuserid=basFacultyService.createEmployeeAccount(facultyForm);
		EmailSenderThread emailSenderThread=new EmailSenderThread(eMailSenderService,email,"Hey! your useris ="+newuserid+"  and  password is ...."+strPassword,BASMessageConstant.EMAIL_RESET_PASSWORD_SUBJECT);
		emailSenderThread.start();
		
		model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.ADD_EMPLOYEE_PAGE);
		model.addAttribute("message", "Employee has been added into database sucessfully..");
		return NavigationConstant.ADMIN_PREFIX_PAGE
				+ NavigationConstant.ADD_EMPLOYEES;
	}

	@RequestMapping(value="/register",method=RequestMethod.GET) 
	public String showRegistrationPage(Model model){
		//I am creating an instance of FacultyForm
		FacultyForm facultyForm=new FacultyForm();
		model.addAttribute("facultyCommand", facultyForm);
		return com.bas.common.constant.NavigationConstant.FACULTY_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.FACULTY_REGISTRATION_PAGE;
	}

	@RequestMapping(value="/registerOne",method=RequestMethod.POST)
	public String registerFacultySubmit(@ModelAttribute("facultyCommand") FacultyForm facultyForm,Model model){
		basFacultyService.persistFaculty(facultyForm);
		return com.bas.common.constant.NavigationConstant.COMMON_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.LOGIN_PAGE;
	}
/*	@RequestMapping(value="/profile",method=RequestMethod.GET)  
	public String showRegistrationPagec(Model model){
		//I am creating an instance of FacultyForm
//		FacultyForm facultyForm=new FacultyForm();
//		model.addAttribute("facultyCommand", facultyForm);
		return com.bas.common.constant.NavigationConstant.FACULTY_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.PROFILE;
	}*/

	@RequestMapping(value="/after",method=RequestMethod.POST)
	public String registerFacultySubmitv(@ModelAttribute("facultyCommand") FacultyForm facultyForm,Model model){
		basFacultyService.persistFaculty(facultyForm);
		return com.bas.common.constant.NavigationConstant.COMMON_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.LOGIN_PAGE;
	}

	
	@RequestMapping(value = "/searchEmployeeRama", method = RequestMethod.GET)
	public @ResponseBody List<String> showEmployee(HttpServletRequest request) {	
		System.out.println("Searching");
		List<String> abc = basFacultyService.searchEmployee(request.getParameter("term"));
		System.out.println(abc);
		System.out.println(request.getParameter("term"));
		return 	abc;
				 
				 
	}
	
	
	
	
//	 
//	@RequestMapping(value="/retreiveEmployeeForAdminspecific",method=RequestMethod.POST)
//	public @ResponseBody List<EmployeeShowForm> showAttendStatus(@RequestParam(value="requiredDatas") String employeeName,HttpSession session,
//			Model model)
//	{	
// 		
//		List<EmployeeShowForm> facultyAttendStatusVOlist=basFacultyService.findFacultyByNamespeci(employeeName);
//		 
//		return facultyAttendStatusVOlist;
//	}

		
//		model.addAttribute("employeeform", employeeform);
//		return com.bas.common.constant.NavigationConstant.ADMIN_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.SHOW_REGISTEREDFACULTY_PAGE;
//		com.bas.common.constant.NavigationConstant.SHOW_REGISTEREDFACULTY_PAGE;
//	}
/*	@RequestMapping(value="/showAllFacultyd",method=RequestMethod.POST)
	//Model model = it is used to carry data from controller to the jsp
	public String showFacultyDataGet(Model model){
		List<EmployeeShowForm> employeeFormsD=basFacultyService.findAllEmployee();
		model.addAttribute("employeeFormsD", employeeFormsD);
		return com.bas.common.constant.NavigationConstant.ADMIN_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.SHOW_REGISTEREDFACULTY_PAGE;
	}*/

	@RequestMapping(value="/deleteFaculty",method=RequestMethod.GET)
	//Model model = it is used to carry data from controller to the jsp
	public String deleteFacultyDb(HttpServletRequest request,Model model){
		//delete the data from database as name comming from UI
		String name=request.getParameter("pname");
		basFacultyService.deletetFaculty(name);
		//Here I am fetching once again updated data
		/*List<FacultyEntity> facultyEntities=basFacultyDao.findAllFaculty();
		model.addAttribute("facultyData", facultyEntities);*/
		return "redirect:showFaculty";
	}




	//Model model = it is used to carry data from controller to the jsp
	//String name=request.getParameter("pname");
	@RequestMapping(value="/editFaculty",method=RequestMethod.POST)
	public String showEditFaculty(@ModelAttribute("facultyCommand") FacultyForm facultyForm,Model model){
		String result=basFacultyService.updateFaculty(facultyForm);
		return "redirect:showFaculty";
	}

	@RequestMapping(value="/editFaculty",method=RequestMethod.GET)
	public String showEditFaculty(@RequestParam("pname") String name,Model model){
		FacultyForm facultyForm=basFacultyService.findFacultyByName(name);
		model.addAttribute("facultyCommand", facultyForm);
		return com.bas.common.constant.NavigationConstant.FACULTY_PREFIX_PAGE+com.bas.common.constant.NavigationConstant.FACULTY_EDIT_PAGE;
	}


	@RequestMapping(value="/renderImage",method=RequestMethod.GET)
	public void renderPhoto(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String empid=request.getParameter("empid");
		response.setContentType("image/jpg");
		byte[] image=basFacultyService.findPhotoByEmpId(empid);
		if(image!=null){
			response.getOutputStream().write(image);
		}
	}

	//It converts your upload file into byte array form after it
	//it is populated in customer java object

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
	}
	
	
	@InitBinder
	public void initBinder1(WebDataBinder binder) {
		// now Spring knows how to handle multipart object and convert them
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}
	
	

}
