package com.bas.employee.dao;

import java.util.List;

import com.bas.employee.dao.entity.PhotoGalleryEntity;

public interface PhotoGalleryDao {

	
	public PhotoGalleryEntity findGalleryPhotoFromDb(String id);
	
	public List<PhotoGalleryEntity> getPhotoListFromDb(int pid);

	public List<Integer> findPhotoIds(int pid);

	public byte[] getPhotoById(int pid);
	
}
