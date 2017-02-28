package com.lovapaper.util;

import java.util.Map;

import com.lovapaper.entity.User;
import com.lovapaper.entity.UserProject;
import com.opensymphony.xwork2.ActionContext;

public class CommonUtil {
	/**
	 * 获取前台登录用户
	 */
	public static UserProject getUserProject(){
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		UserProject user = (UserProject) session.get("userproject");
		return user;
	}
	/**
	 * 获取后台登录用户
	 */
	public static User getUser(){
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		User user = (User) session.get("user");
		return user;
	}
}
