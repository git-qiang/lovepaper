package com.lovapaper.modules.login.service;

public interface LoginService {
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verifyLogin(String username,String password);
}
