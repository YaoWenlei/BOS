package com.tz.bos.utils;
/**
 * 分页查询的基本数据的封装类
 * @author DELL
 *
 */

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {

	private int page;//页数
	private int pageSize;//当前页的数据条数
	private DetachedCriteria dc;//离线查询条件的设置
	private int total;//数据库中的总条数
	private List rows; //从数据库中返回的数据
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDc() {
		return dc;
	}
	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
