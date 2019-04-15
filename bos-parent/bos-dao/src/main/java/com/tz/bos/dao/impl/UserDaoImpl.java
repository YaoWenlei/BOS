package com.tz.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.tz.bos.dao.IUserDao;
import com.tz.bos.dao.ibase.impl.BaseDaoImpl;
import com.tz.bos.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	/**
	 * 根据用户名和密码进行查询数据库
	 */
	@Override
	public User findByUsernameAndPassword(String username, String pWmd5) {
		String hql = "From User u WHERE u.username=? AND u.password=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username, pWmd5);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
