package com.tz.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tz.bos.entity.User;
/**
 * 用户登录拦截器interceptor，拦截无用户名的session域
 * @author DELL
 *
 */
public class BOSLoginIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获取登录的用户名
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user == null) {
			return "login";
		}
		return invocation.invoke();
	}

}
