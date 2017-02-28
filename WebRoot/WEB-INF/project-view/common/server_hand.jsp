<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                    <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${user.username} 
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="${context_path}/outadminlogin">退出</a></li>
                        </ul>
                    </li>
                </ul>
                <a class="brand" href=""><span class="second"><font color="white">检查项后台管理系统</font></span></a>
            </div>
        </div>
    </div>
