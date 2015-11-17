package com.bas.admin.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.bas.admin.dao.LeaveHistoryDao;
import com.bas.admin.dao.entity.LeaveHistoryEntity;
import com.bas.common.dao.entity.MessageBoardEntity;

@Repository("LeaveHistoryDaoImpl")
@Scope("singleton")
public class LeaveHistoryDaoImpl extends JdbcDaoSupport
		implements
			LeaveHistoryDao {

	@Autowired
	@Qualifier("sdatasource")
	public void setDataSourceInSuper(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public List<LeaveHistoryEntity> getLeaveHistory(int month, int year, String department) {
		String sql = null;
		if(department.toLowerCase().equals("all")){
//			sql = new String("select 	a.name, a.designation, a.department, a.image,	b.noleaves, b.daysworked from emp_db a, faculty_salary_table b where a.id = b.empid and DATE_FORMAT(b.dom, '%m-%Y') = '"+String.format("%02d", month)+"-"+year+"'");
			sql = "select 	emp_db.id, emp_db.name, emp_db.designation, emp_db.department,	faculty_salary_table.noleaves, faculty_salary_table.daysworked, emp_db.image from emp_db inner join faculty_salary_table on emp_db.id = faculty_salary_table.empid and DATE_FORMAT(faculty_salary_table.dom, '%m-%Y') = '"+String.format("%02d", month)+"-"+year+"';";
		}else{
//			sql = new String("select 	a.name, a.designation, a.department, a.image,	b.noleaves, b.daysworked from emp_db a, faculty_salary_table b where a.id = b.empid and DATE_FORMAT(b.dom, '%m-%Y') = '"+String.format("%02d", month)+"-"+year+"' and a.department='"+department+"'");
			sql = "select 	emp_db.id, emp_db.name, emp_db.designation, emp_db.department,	faculty_salary_table.noleaves, faculty_salary_table.daysworked, emp_db.image from emp_db inner join faculty_salary_table on emp_db.id = faculty_salary_table.empid and DATE_FORMAT(faculty_salary_table.dom, '%m-%Y') = '"+String.format("%02d", month)+"-"+year+"' and emp_db.department='"+department+"';";
		}
		List<LeaveHistoryEntity> eList = super.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(LeaveHistoryEntity.class));
		
//		System.out.println(eList.size());
//		for (LeaveHistoryEntity leaveHistoryEntity : eList) {
//			System.out.println(leaveHistoryEntity);
//		}		
		return eList;
	}

	@Override
	public byte[] findPhotoByID(String id) {
		
		System.out.println("====================================="+id);
		
		String sql = "select emp_db.image from emp_db where emp_db.id =" + id;
		byte[] image = super.getJdbcTemplate().queryForObject(sql, byte[].class);
		return image;
	}
}
