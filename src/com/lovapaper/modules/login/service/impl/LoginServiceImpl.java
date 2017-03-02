package com.lovapaper.modules.login.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovapaper.modules.login.dao.LoginDao;
import com.lovapaper.modules.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	public static Logger log = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public boolean verifyLogin(String username, String password) {
		if(log.isDebugEnabled()){
			log.debug("登录验证： "+ LoginServiceImpl.class+" ：verifyLogin");
		}
		return this.loginDao.verifyLogin(username, password);
	}

}
