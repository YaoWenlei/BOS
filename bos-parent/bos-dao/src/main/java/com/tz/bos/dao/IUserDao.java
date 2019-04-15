package com.tz.bos.dao;

import com.tz.bos.dao.ibase.IBaseDao;
import com.tz.bos.entity.User;

public interface IUserDao extends IBaseDao<User> {

	User findByUsernameAndPassword(String username, String pWmd5);

}
