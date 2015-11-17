package com.bas.employee.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;





import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bas.employee.dao.PhotoGalleryDao;
import com.bas.employee.dao.entity.PhotoGalleryEntity;
import com.bas.employee.dao.impl.PhotoGalleryDaoImpl;
import com.bas.employee.service.PhotoGalleryService;
import com.bas.employee.web.controller.form.PhotoGallery;

@Service("PhotoGalleryServiceImpl")
public class PhotoGalleryServiceImpl implements PhotoGalleryService {

	@Autowired
	@Qualifier("PhotoGalleryDaoImpl")
	private PhotoGalleryDao pgdi;

	@Override
	public List<PhotoGallery> findPhotoById(int photoId){
		
		List<PhotoGallery> pg = new ArrayList<PhotoGallery>();
		List<PhotoGalleryEntity> pEnList = pgdi.getPhotoListFromDb(photoId);
		//PhotoGalleryEntity pEntity = pgdi.findGalleryPhotoFromDb(photoId);
		
		for(PhotoGalleryEntity pob: pEnList){
			PhotoGallery pgl = new PhotoGallery();
			BeanUtils.copyProperties(pob,pgl);
			pg.add(pgl);
		}
		return pg;
	}

	@Override
	public List<Integer> findPhotoIds(int pid){
		List<Integer> newList = pgdi.findPhotoIds(pid);
		return newList;
	}
	
	@Override
	public byte[] getPhotoById(int pid) {
		byte[] photo = pgdi.getPhotoById(pid);
		return photo;
	}

}
