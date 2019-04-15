package com.tz.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tz.bos.dao.IStaffDao;
import com.tz.bos.entity.Staff;
import com.tz.bos.service.IStaffService;
import com.tz.bos.utils.PageBean;
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
		
	}

	@Override
	public void delectById(String[] id) {
		for (String idStr : id) {
			//更改删除标示位为1删除
			staffDao.executeUpdate("staff.updatedeltag", "1", idStr);
		}
	}

	@Override
	public void edit(Staff model) {
		staffDao.delete(model);
		staffDao.save(model);
	}

}
