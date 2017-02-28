package com.lovapaper.util;

import java.util.Map;

import com.lovapaper.entity.UserProject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 后台登录拦截器
 * @author My  Love
 *
 */
public class UserLoginInterceptor extends AbstractInterceptor {
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例  
		ActionContext ctx = invocation.getInvocationContext();  
		Map session = ctx.getSession();  
		UserProject user = (UserProject) session.get("userproject");  
		// 如果没有登陆，或者..都返回重新登陆  
		if(user == null){
			ctx.put("msg", "你还没有登录");  
			return "userlogin";  
		}
		return invocation.invoke();  
	}

}
