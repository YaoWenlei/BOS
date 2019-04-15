package com.tz.bos.service;

import com.tz.bos.entity.Staff;
import com.tz.bos.utils.PageBean;

/**
 * 配送员类的逻辑层
 * @author DELL
 *
 */
public interface IStaffService {

	void save(Staff model);

	void pageQuery(PageBean pageBean);

	void delectById(String[] id);

	void edit(Staff model);

}
