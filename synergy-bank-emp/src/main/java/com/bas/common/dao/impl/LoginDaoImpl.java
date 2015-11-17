package com.bas.common.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bas.common.dao.LoginDao;
import com.bas.employee.dao.entity.LoginEntity;


/**
 * 
 * @author nagendra
 * 
 *  This is code responsible for all the logic regarding login  
 *
 */
//@Repository ,@Component,@Controller
@Repository("LoginDaoImpl")
@Transactional
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	@Qualifier("sdatasource")
	private DataSource dataSource;

	@Override
	public LoginEntity validateUser(String username, String password) {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		LoginEntity loginEntity=null;
		try {
			loginEntity=(LoginEntity)jdbcTemplate.queryForObject("select * from faculity_login_tbl where userid=? and password=?",new Object[]{username,password},new BeanPropertyRowMapper<LoginEntity>(LoginEntity.class));
			if(loginEntity!=null){
				long eid=Long.parseLong(loginEntity.getEid());
				String reportingManager=(String)jdbcTemplate.queryForObject("select reporting_manager from emp_db where id="+eid,String.class);
				loginEntity.setReportingManager(reportingManager);
			}
		}catch(Exception ex) {
			 String me=ex.getMessage();
			 System.out.println("me = "+me);
			 return null;
		}
		return loginEntity;
	}
	
	@Override
	public String updatePassword(String email,String password) {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		try {
			jdbcTemplate.update("update faculity_login_tbl set password=? where email=?",new Object[]{password,email});
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
			 return null;
		}
		return "updated";
	}

}
