package com.lovapaper.modules.login.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public boolean verifyLogin(String username, String password) {
		if(log.isDebugEnabled()){
			log.debug("登录验证： "+LoginDaoImpl.class+" : verifyLogin");
		}
		Session session = null;
		try{
			session = this.sessionFactory.getCurrentSession();
		if(username!=null&&password!=null){
			Query query = session.createSQLQuery("select username from user");
			List<User> listuser = query.list();
			if(!listuser.isEmpty()){
				System.out.println(listuser.get(0));
			}
		}
		
		
			User user = new User();
			user.setUsername("111");
			user.setPassword("222");
			user.setNickname("333");
			session.save(user);
			User user1 = new User();
			user.setUsername("333");
			user.setPassword("2222");
			user.setNickname("1111111111111111111111111111111111111111111");
			session.save(user1);
		
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException();
			}
		
		return true;
	}

}
