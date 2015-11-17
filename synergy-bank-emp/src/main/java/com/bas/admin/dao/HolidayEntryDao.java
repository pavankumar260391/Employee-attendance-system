package com.bas.admin.dao;

import java.util.List;

import com.bas.admin.dao.entity.HolidayEntryEntity;
import com.bas.employee.dao.entity.HolidayCalendarEntity;

/**
 * 
 * @author Amogh
 *
 */
public interface HolidayEntryDao {
	
	public String addHolidayEntry(HolidayEntryEntity holidayEntryEntity);
	public String editHolidayEntry(HolidayEntryEntity holidayEntryEntity);
	public String deleteHolidayEntry(String date);
	public List<HolidayEntryEntity> findHolidayEntry();
	public HolidayEntryEntity findHolidayEntryByDate(String date);
	public List<HolidayEntryEntity> getHolidayId(int month);
	public byte[] getImage(int id);
	public byte[] getHoildayImageByDate(String date);

}
