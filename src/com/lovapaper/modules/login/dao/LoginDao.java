package com.lovapaper.modules.login.dao;

public interface LoginDao {
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verifyLogin(String username,String password);
}
