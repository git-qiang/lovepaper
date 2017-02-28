<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                        <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${userproject.username} 
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="${context_path}/outuserlogin">退出</a></li>
                        </ul>
                    </li>
                </ul>
                <a class="brand" href=""><span class="second"><font color="white">检查项前台管理系统</font></span></a>
            </div>
        </div>
    </div>