package com.tz.bos.web.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.bos.entity.Staff;
import com.tz.bos.service.IStaffService;
import com.tz.bos.utils.PageBean;
import com.tz.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
/**
 * 配送员的访问action类
 * @author DELL
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

	@Autowired
	private IStaffService staffService;
	
	/**
	 * 添加配送员的 信息
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		staffService.save(model);
		return "list";
	}

	private int page;//查询的页数
	private int rows;//查询的每页条数
	/**
	 * 分页查询配送员的 信息
	 * @return
	 * @throws Exception
	 */
	public String pageQuery() throws Exception {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		pageBean.setDc(dc);
		staffService.pageQuery(pageBean);
		//将获取到的数据，转换为json数据，并写回页面
		String rows = JSONObject.fromObject(pageBean).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(rows);
		return NONE;
	}
	
	private String ids;
	/**
	 * 删除配送员的 信息
	 * 通过id更改删除标示位
	 * @return
	 * @throws Exception
	 */
	public String delect() throws Exception {
		String[] id = ids.split(",");
		staffService.delectById(id);
		return "list";
	}
	
	/**
	 * 更改配送员的 信息
	 * 通过id更改标示位
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		staffService.edit(model);
		return "list";
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}

}
