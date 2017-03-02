package com.lovapaper.modules.login.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import com.lovapaper.entity.User;
import com.lovapaper.modules.login.dao.LoginDao;

/**
 * 登录登出
 * @author my love
 *
 */
@Repository
public class LoginDaoImpl implements LoginDao{
	
	public static Logger log = Logger.getLogger(LoginDaoImpl.class);
	
	@Autowired
	private HibernateOperations hibernateOperations;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyLogin(String username, String password) {
		if(log.isDebugEnabled()){
			log.debug("登录验证： "+LoginDaoImpl.class+" : verifyLogin");
		}
		if(username!=null&&password!=null){
			try{
			List<User> listuser = this.hibernateOperations.find("from User where id = '1'");
			if(!listuser.isEmpty()){
				System.out.println(listuser.get(0).getUsername());
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			User user = new User();
			user.setUsername("123");
			user.setPassword("1234");
			user.setNickname("12345");
			this.hibernateOperations.save(user);
		}
		return true;
	}

}
