package com.bas.admin.service;

import java.util.List;

import com.bas.admin.dao.entity.HolidayEntryEntity;
import com.bas.admin.web.controller.form.HolidayEntryForm;
import com.bas.employee.web.controller.form.HolidayCalender;

/**
 * 
 * @author Amogh
 *
 */
public interface HolidayEntryService {
	
	public String addHolidayEntry(HolidayEntryForm holidayEntryForm);
	public String editHolidayEntry(HolidayEntryForm holidayEntryForm);
	public String deleteHolidayEntry(String date);
	public List<HolidayEntryForm> findHolidayEntry();
	public HolidayEntryForm findHolidayEntryByDate(String date);
	public byte[] getImage(int id);
	public List<HolidayEntryForm> getHolidayId(int month);
	public byte[] getHoildayImageByDate(String date);
	 

}
