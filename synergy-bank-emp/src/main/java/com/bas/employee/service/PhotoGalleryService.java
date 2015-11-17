package com.bas.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.bas.employee.web.controller.form.PhotoGallery;

public interface PhotoGalleryService {

		public List<PhotoGallery> findPhotoById(int photoId);

		public List<Integer> findPhotoIds(int pid);

		public byte[] getPhotoById(int pid);

}
