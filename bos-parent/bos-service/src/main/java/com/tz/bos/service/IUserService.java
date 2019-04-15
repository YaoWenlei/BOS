package com.tz.bos.service;

import com.tz.bos.entity.User;
/**
 * 处理用户类的逻辑
 * 1、用户登录的逻辑处理
 * @author DELL
 *
 */
public interface IUserService {
	/**
	 * 用户登录的逻辑
	 * @param model
	 * @return
	 */
	User login(User model);
	/**
	 * 用户更改密码的逻辑
	 * @param password
	 * @param id
	 */
	void updatePassword(String password, String id);

}
