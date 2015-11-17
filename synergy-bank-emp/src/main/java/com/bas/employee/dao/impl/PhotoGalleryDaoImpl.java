package com.bas.employee.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.bas.employee.dao.PhotoGalleryDao;
import com.bas.employee.dao.entity.PhotoGalleryEntity;

@Repository("PhotoGalleryDaoImpl")
public class PhotoGalleryDaoImpl extends JdbcDaoSupport implements PhotoGalleryDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("sdatasource")
	public void setDataSourceInSuper(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	@Override
	public PhotoGalleryEntity findGalleryPhotoFromDb(String pid) {
			String query="select * from event_photos_tbl where eventId="+Integer.parseInt(pid);
			PhotoGalleryEntity p = super.getJdbcTemplate().queryForObject(query,new BeanPropertyRowMapper(PhotoGalleryEntity.class));
			return p;
	}
	
	@Override
	public List<PhotoGalleryEntity> getPhotoListFromDb(int pid) {
		String query = "select * from event_photos_tbl where eventId=?";
		List<PhotoGalleryEntity> photoList = (List<PhotoGalleryEntity>) super.getJdbcTemplate().query(query,new Object[]{pid}, new BeanPropertyRowMapper(PhotoGalleryEntity.class));
		return photoList;
	}
	
	@Override
	public byte[] getPhotoById(int pid) {
		String query = "select * from event_photos_tbl where photoId=?";
		PhotoGalleryEntity photo = (PhotoGalleryEntity) super.getJdbcTemplate().queryForObject(query,new Object[]{pid},new BeanPropertyRowMapper(PhotoGalleryEntity.class) );
		return photo.getPhoto();
	}
	
	@Override
	public List<Integer> findPhotoIds(int pid){
		String query = "Select photoId from event_photos_tbl where eventId=?";
		List<Integer> idList = super.getJdbcTemplate().queryForList(query,new Object[]{pid},Integer.class);
		return idList;
	}
}
