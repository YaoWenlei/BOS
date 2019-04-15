package com.tz.bos.dao.ibase.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.tz.bos.dao.ibase.IBaseDao;
import com.tz.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	//代表的是某个实体的类型
	private Class<T> entityClass;
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	//在父类（BaseDaoImpl）的构造方法中动态获得entityClass
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Override
	public void save(T entity) {
		// 用于简单的添加数据
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		// 用于删除数据
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		// 用于更新操作
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public T findById(Serializable se) {
		// 根据ID查询数据库，返回查询到的数据
		this.getHibernateTemplate().get(entityClass, se);
		return null;
	}

	@Override
	public List<T> findAll(T entity) {
		// 根据POJO类，查询所有的数据
		String hql = "from " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql, null);
	}

	@Override
	public void executeUpdate(String querryName, Object... objects) {
		// 执行用户更新操作的通用实现
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(querryName);
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i, object);
			i++;
		}
		query.executeUpdate();
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		int page = pageBean.getPage();//当前页数
		int pageSize = pageBean.getPageSize();//每页的显示条数
		//分页查询的具体实现
		DetachedCriteria detachedCriteria = pageBean.getDc();
		//查询总条数Criterion
		detachedCriteria.setProjection(Projections.rowCount());
		List<?> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total = (Long) list.get(0);
		pageBean.setTotal(total.intValue());
		//查询所有的具体数据
		detachedCriteria.setProjection(null);
		int firstResult = (page-1) * pageSize;
		int maxResults = pageSize;
		List<?> rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}


}
