package com.bas.admin.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bas.admin.dao.DepartmentDao;
import com.bas.admin.dao.entity.DepartmentEntity;

@Repository("DepartmentDaoImpl")
@Scope("singleton")
public class DepartmentDaoImpl extends JdbcDaoSupport implements DepartmentDao {

	/**
	 * This is setting datasource in super class
	 * 
	 * @param dataSource
	 */
	@Autowired
	@Qualifier("sdatasource")
	public void setDataSourceInSuper(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	/**
	 * 
	 * @return
	 */
	private int findNextNum() {
		int num = getJdbcTemplate().queryForInt(
				"select max(departmentId) from departments_tbl");
		num = num + 100;
		return num;
	}

	@Override
	public String validateDep(String depName){
		String sql = "SELECT EXISTS (SELECT departmentName from departments_tbl where departmentName='"+depName+"')";
		int results = super.getJdbcTemplate().queryForObject(sql, Integer.class);
		System.out.println(results);
		String validate;
		if(results!=0){
			validate="fail";
			
		}else
		{
			validate="success";
		}
		return validate;
	}
	
	@Override
	public String addDepartment(DepartmentEntity departmentEntity) {
		boolean active=TransactionSynchronizationManager.isActualTransactionActive();
		if(active){
			System.out.println("____hey spring transaction is working __________");
			System.out.println("____hey spring transaction is working __________");
			System.out.println("____hey spring transaction is working __________");
		}
		String sql = "insert into departments_tbl values(?,?,?,?,?,?,?)";
		Object[] data = new Object[] { findNextNum(),
				departmentEntity.getDepartmentName(),
				departmentEntity.getDepartmentShortName(),
				departmentEntity.getDescription(), new Date(), new Date(),
				departmentEntity.getEntryBy() };
		// firing the query
		
		String query = "select count(*) from departments_tbl where departmentName='"+departmentEntity.getDepartmentName();// TODO Auto-generated
		// method stub
         int count = super.getJdbcTemplate() .queryForInt(query);

      if (count == 0){
		super.getJdbcTemplate().update(sql, data);
      } else {
    	throw new IllegalArgumentException("The department is already exist");
      }
		return "Added";
	}

	@Override
	public String editDepartment(DepartmentEntity departmentEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDepartment(String depName) {
		String query = "delete from departments_tbl where departmentName=?";
		super.getJdbcTemplate().update(query, depName);
		return "deleted";
	}

	@Override
	public List<DepartmentEntity> findDepartments() {
		String query = "select * from departments_tbl";// TODO Auto-generated
														// method stub
		List<DepartmentEntity> departmentEntities = super
				.getJdbcTemplate()
				.query(query, new BeanPropertyRowMapper(DepartmentEntity.class));

		return departmentEntities;
	}

	@Override
	public DepartmentEntity findDepartmentById(int deptId) {
		String query = "select * from departments_tbl where departmentId="
				+ deptId;
		DepartmentEntity departmentEntity = super.getJdbcTemplate()
				.queryForObject(query,
						new BeanPropertyRowMapper(DepartmentEntity.class));
		return departmentEntity;
	}

	@Override
	public String updateDepartment(DepartmentEntity departmentEntity) {
		String query = "update departments_tbl set departmentName=?,departmentShortName=?,description=?,dom=? where departmentId=?";
		super.getJdbcTemplate().update(query,new Object[]{departmentEntity.getDepartmentName(),departmentEntity.getDepartmentShortName(),departmentEntity.getDescription(),new Date(),departmentEntity.getDepartmentId()});
		System.out.println("DAOIMPL: " + departmentEntity);
		System.out.println(query);
		return "success";
	}

	@Override
	public String deleteDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
