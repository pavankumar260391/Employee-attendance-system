package com.bas.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bas.admin.dao.MessagePostDao;
import com.bas.admin.dao.entity.MessagePostDaoEntity;
import com.bas.admin.service.MessagePostService;
import com.bas.admin.web.controller.form.MessagePost;

@Service("MessagePostService")
public class MessagePostServiceImpl implements MessagePostService{

	@Autowired
	@Qualifier("MessagePostDaoImpl")
	MessagePostDao mpd;
	
	@Override
	public String storeMessage(MessagePost msg){
		MessagePostDaoEntity men = new MessagePostDaoEntity();
		BeanUtils.copyProperties(msg, men);
		return "success";
	}
}
