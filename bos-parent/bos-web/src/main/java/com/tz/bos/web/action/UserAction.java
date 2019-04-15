package com.tz.bos.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.cfg.beanvalidation.ActivationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctc.wstx.util.StringUtil;
import com.tz.bos.entity.User;
import com.tz.bos.service.IUserService;
import com.tz.bos.web.action.base.BaseAction;
/**
 * 本类处理用户相关的请求信息，
 * 	1、用户登录的判断
 * @author DELL
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	//属性驱动的方式注入验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	@Autowired
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 具体的登录方法的访问代码实现
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		//获取正确的验证码
		String attribute = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && attribute.equals(checkcode)) {
			//调用业务层方法，处理请求
			User user = userService.login(model);
			if (user != null) {
				//有返回值，查询成功
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}else {
				//用户名或者密码错误
				this.addActionError("用户名或者密码输入错误");
				return LOGIN;
			}
		}else {
			//验证码不通过，跳回原页面重新输入
			this.addActionError("验证码输入错误");
			return LOGIN;
		}
	}
	/**
	 * 用户注销功能的实现
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
		
	}
	/**
	 * 用户更改密码的实现
	 * @return
	 * @throws Exception
	 */
	public String updatePassword() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String f = "1";
		try {
			userService.updatePassword(model.getPassword(),user.getId());
		} catch (Exception e) {
			f = "2";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
		
	}

	
}
