package com.tz.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bos.dao.IUserDao;
import com.tz.bos.entity.User;
import com.tz.bos.service.IUserService;
import com.tz.bos.utils.MD5Utils;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User login(User model) {
		String PWmd5 = MD5Utils.md5(model.getPassword());
		return userDao.findByUsernameAndPassword(model.getUsername(),PWmd5);

	}
	@Override
	public void updatePassword(String password, String id) {
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.updatepassword", password, id);
	}

}
