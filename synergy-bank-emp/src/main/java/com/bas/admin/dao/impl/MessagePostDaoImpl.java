package com.bas.admin.dao.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.bas.admin.dao.MessagePostDao;
import com.bas.admin.dao.entity.MessagePostDaoEntity;

@Repository("MessagePostDaoImpl")
public class MessagePostDaoImpl extends JdbcDaoSupport implements MessagePostDao {

	@Autowired
	@Qualifier("sdatasource")
	public void setDataSourceInSuper(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	@Override
	public String storeMessage(MessagePostDaoEntity msg){
		
		String query = "insert into message_board_tbl (subject,category,message,image) values (?,?,?,?)";
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue=new SqlLobValue(msg.getImage(),lobHandler);
		Object[] obj =new Object[]{msg.getSubject(),msg.getCategory(),msg.getMessage(),sqlLobValue};
		int[] dataType=new int[] {Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.BLOB};
		super.getJdbcTemplate().update(query,obj,dataType);
		
		return "success";
	}
	
}
