<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>检查项前台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=path %>/WEB-INF/project_style/js/jquery-1.8.3.min.js"></script>
</head>

<body class="login"   onkeydown="enter(this)">

<div>
  <h2>用户名</h2>
  <label>
    <input type="text" id="username" name="username" class="" />
  </label>
  <h2>密码</h2>
  <label>
    <input type="password"  id="userpwd" name="userpwd" class=""/>
  </label>
  <br> <br>
  <div>  
    <label>
      <input type="button"  onclick="login();" value="登录" style="opacity: 0.7;float:right; width:70px; height:32px;"/>
    </label>
  </div>
</div>
 <br /> <br />
<p align="center"> Hangxin Software <a href="" target="_blank" title="航信科技">航信科技</a> - Manage <a href="" title="后台管理" target="_blank">后台管理</a></p>
<script type="text/javascript">
$(function(){
	$("#username,#userpwd").focus(function(){
		  $(this).css("border-color","gray");
	});
});
function enter(e){
		if (event.keyCode==13){
			login();
		}
	}


function login() {	
var username=$('#username').val();;
var userpwd=$('#userpwd').val();;
if(!username){
	$('#username').css("border-color","red");
	alert('请输入用户名 ！');
	return false;
}else if(!userpwd){
	$('#userpwd').css("border-color","red");
	alert('请输入密码 ！');
	return false;
}
$.ajax({
	url:"userlogin",
	type:"post",
	data:{"username":username,"userpwd":userpwd},
	success:function(ret){
		if(ret.code==444){
			alert(ret.msg);
		}else if(ret==666){
			alert(ret.msg+"success");
		}
	},
	error:function(ret){
		alert('通信异常 ！');
	}
});

}	
</script>


</body>
</html>
