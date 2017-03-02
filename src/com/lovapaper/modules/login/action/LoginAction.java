package com.lovapaper.modules.login.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lovapaper.entity.ReturnResult;
import com.lovapaper.modules.login.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 系统登陆，登出
 * @author 61654
 *
 */
public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = -9189065399567572175L;
	
	public static Logger log = Logger.getLogger(LoginAction.class);
	
	@Autowired
	private LoginService loginservice;
	
	private String object1;
	private String object2;
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
		
		boolean b = this.loginservice.verifyLogin(object1, object2);
		if(b){
			rr = new ReturnResult(666,"验证成功","验证成功");
		}else{
			rr = new ReturnResult(444,"验证失败","验证失败");
		}
		
		if(log.isDebugEnabled()){
			 log.debug("开始登陆，验证用户名:"+object1+",密码:"+object2+"结束");
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	public Object getObject1() {
		return object1;
	}
	public void setObject1(String object1) {
		this.object1 = object1;
	}
	public Object getObject2() {
		return object2;
	}
	public void setObject2(String object2) {
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
	public ReturnResult getRr() {
		return rr;
	}
	public void setRr(ReturnResult rr) {
		this.rr = rr;
	}
	
	
}
