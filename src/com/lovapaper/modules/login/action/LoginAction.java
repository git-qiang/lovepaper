package com.lovapaper.modules.login.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.lovapaper.entity.ReturnResult;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 系统登陆，登出
 * @author 61654
 *
 */
@Action
public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = -9189065399567572175L;
	
	public static Logger log = Logger.getLogger(LoginAction.class);
	
	private Object object1;
	private Object object2;
	private Object object3;
	private Object object4;
	private ReturnResult rr = null;;
	public String skip(){
		return SUCCESS;
	}
	public String verifyLogin(){
		if(log.isDebugEnabled()){
			 log.debug("开始登陆，验证用户名:"+object1+",密码:"+object2);
		}
		if(object1.equals("1")&&object2.equals("1")){
			rr = new ReturnResult(666,"登陆成功","登陆成功");
		}else{
			rr = new ReturnResult(444,"登陆失败","登陆失败");
		}
		if(log.isDebugEnabled()){
			 log.debug("开始登陆，验证用户名:"+object1+",密码:"+object2+"结束");
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	public Object getObject1() {
		return object1;
	}
	public void setObject1(Object object1) {
		this.object1 = object1;
	}
	public Object getObject2() {
		return object2;
	}
	public void setObject2(Object object2) {
		this.object2 = object2;
	}
	public Object getObject3() {
		return object3;
	}
	public void setObject3(Object object3) {
		this.object3 = object3;
	}
	public Object getObject4() {
		return object4;
	}
	public void setObject4(Object object4) {
		this.object4 = object4;
	}
	
	
}
