package com.tz.bos.dao.ibase;

import java.io.Serializable;
import java.util.List;

import com.tz.bos.utils.PageBean;
/**
 * 操作数据库的基本类，用于简单的增删改查
 * dao层的抽象提取，
 * @author DELL
 *
 * @param <T> 泛型操作数据库的POJO类
 */
public interface IBaseDao<T> {
	
	public void save(T entity) ;
	public void delete(T entity) ;
	public void update(T entity) ;
	public T findById(Serializable se) ;
	public List<T> findAll(T entity) ;
	public void executeUpdate(String querryName, Object...objects);
	public void pageQuery(PageBean pageBean);
}
