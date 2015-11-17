package com.bas.common.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bas.common.constant.BASMessageConstant;
import com.bas.common.constant.NavigationConstant;
import com.bas.common.constant.PageTitleContant;
import com.bas.common.dao.LoginDao;
import com.bas.common.util.PasswordGenerator;
import com.bas.employee.dao.entity.LoginEntity;
import com.bas.employee.web.controller.form.LoginForm;
import com.bas.soap.service.email.EMailSenderServiceImpl;
import com.bas.soap.service.email.EmailSenderThread;

@Controller //@Component
@Scope("request")
public class LoginController {
	
	@Autowired
	@Qualifier("LoginDaoImpl")
	private LoginDao loginDao;
	
	
	@Autowired
	@Qualifier("EMailSenderServiceImpl")
	private EMailSenderServiceImpl eMailSenderService;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		return NavigationConstant.ADMIN_PREFIX_PAGE+"test";
	}
	
	
	@RequestMapping(value="/adminHome",method=RequestMethod.GET)
	public String adminHome(){
		return NavigationConstant.ADMIN_PREFIX_PAGE+NavigationConstant.ADMIN_HOME_PAGE;
	}
	
	
	@RequestMapping(value="/employeeHome",method=RequestMethod.GET)
	public String employeeHome(){	
		return NavigationConstant.EMPLOYEE_PREFIX_PAGE+NavigationConstant.EMPLOYEE_HOME_PAGE;
	}
	
	@RequestMapping(value="/facultyHome",method=RequestMethod.GET)
	public String facultyHome(){
		return NavigationConstant.FACULTY_PREFIX_PAGE+NavigationConstant.FACULTY_HOME_PAGE;
	}
	
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.GET)
	public String resetPassword(Model model){
		model.addAttribute(PageTitleContant.PAGE_TITLE, PageTitleContant.RESET_PASSWORD_PAGE);
		return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.RESET_PASSWORD_PAGE;
	}
	
	
	/**
	 * This is the page which will change the password and email will be sent for the same! 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public String resetPasswordPost(@RequestParam("email") String email,Model model) {
		//Here we have to write logic to generate the password
		//and same we have to update into the database.........
		PasswordGenerator passwordGenerator = new PasswordGenerator(6);
		char password[]=passwordGenerator.get();
		String strPassword=new String(password);
		loginDao.updatePassword(email, strPassword);

		EmailSenderThread emailSenderThread=new EmailSenderThread(eMailSenderService,email,"Hey! your new password is ...."+strPassword,BASMessageConstant.EMAIL_RESET_PASSWORD_SUBJECT);
		emailSenderThread.start();
		
		//eMailSenderService.sendMail("nagendra.synergisticit@gmail.com", email, BASMessageConstant.EMAIL_RESET_PASSWORD_SUBJECT,"Hey! your new password is ...."+strPassword);	
		model.addAttribute("error","Your Password have been reset successfully, Please check  your email.");
		return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.LOGIN_PAGE;
	}
	
		
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session,Model model){
		session.invalidate();
		model.addAttribute("error","You have successfully logout from application!!!");
		return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.LOGIN_PAGE;
	}
	
	@RequestMapping(value="/bashome",method=RequestMethod.GET)
	public String showHomePage(){	
		return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.COMMON_HOME_PAGE;		
	}
	
	@RequestMapping(value="/auth",method=RequestMethod.GET)
	public String showLoginPage(){	
		return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.LOGIN_PAGE;
	
	}
	
	@RequestMapping(value="/validateUserByAjax",method=RequestMethod.GET)
	public @ResponseBody String validateUserByAjax(@RequestParam("username") String username,
			@RequestParam("password") String password){
		LoginEntity loginEntity=loginDao.validateUser(username, password);
		if(loginEntity!=null){
			return "valid";
		}else{
			return "invalid";
		}
	}

	@RequestMapping(value="/auth",method=RequestMethod.POST)
	public String validateUser(HttpServletRequest request,HttpSession session,Model model){
		//ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bas-att-spring-context.xml");
		//LoginDao loginDao=(LoginDao)applicationContext.getBean("LoginDaoImpl");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("__user name ___ " +username);
		System.out.println("__password ___ " +password);
		LoginEntity loginEntity=loginDao.validateUser(username, password);
		if(loginEntity!=null){
			LoginForm form=new LoginForm();
			BeanUtils.copyProperties(loginEntity, form);
			if(loginEntity!=null &&  loginEntity.getRole()!=null && "user".equals(loginEntity.getRole())){
				session.setAttribute(NavigationConstant.USER_SESSION_DATA, form);
				return NavigationConstant.EMPLOYEE_PREFIX_PAGE+NavigationConstant.EMPLOYEE_HOME_PAGE;
			}else if(loginEntity!=null &&  loginEntity.getRole()!=null && "admin".equals(loginEntity.getRole())){
				session.setAttribute(NavigationConstant.USER_SESSION_DATA, form);
				return NavigationConstant.ADMIN_PREFIX_PAGE+NavigationConstant.ADMIN_HOME_PAGE;
		    }else if(loginEntity!=null &&  loginEntity.getRole()!=null && "userRM".equals(loginEntity.getRole())){
		    	session.setAttribute(NavigationConstant.USER_SESSION_DATA, form);
		    	model.addAttribute("role", "userRM");
				return NavigationConstant.FACULTY_PREFIX_PAGE+NavigationConstant.EMPLOYEE_HOME_PAGE;
		    }
		    else{
		    	session.setAttribute(NavigationConstant.USER_SESSION_DATA, form);
				return NavigationConstant.FACULTY_PREFIX_PAGE+NavigationConstant.FACULTY_HOME_PAGE;
		    }
		   
		}else {
			model.addAttribute("error","Username and password are not valid!!!");
			return NavigationConstant.COMMON_PREFIX_PAGE+NavigationConstant.LOGIN_PAGE;
		}
	}
}

