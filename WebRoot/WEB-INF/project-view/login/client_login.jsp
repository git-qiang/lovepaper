<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="./Wopop_files/style_log.css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/style.css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/userpanel.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/plug-ins/layer/skin/layer.css"/>
<script type="text/javascript" src="<%=path %>/plug-ins/layer/layer.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body class="login"   onkeydown="enter(this)">
<div class="login_m">
<div class="login_logo"><img src="./Wopop_files/logon.png" width="196" height="46" /></div>
<div class="login_boder">

<div class="login_padding" id="login_model">
  <h2>用户名</h2>
  <label>
    <input type="text" id="username" name="username" class="txt_input txt_input2" />
  </label>
  <h2>密码</h2>
  <label>
    <input type="password"  id="userpwd" name="userpwd" class="txt_input"/>
  </label>
  <br> <br>
  <div class="rem_sub">  
    <label>
      <input type="button"  onclick="login();" value="登录" 
       style="opacity: 0.7;
      float:right; width:70px; height:32px; background:url(./Wopop_files/site_bg.png) no-repeat -153px -850px; 
      border:none; color:#FFF; padding-bottom:2px; margin-right:13px; font-size:14px; font-weight:bold;"/>
    </label>
  </div>
</div>



<!--login_padding  Sign up end-->
</div><!--login_boder end-->
</div><!--login_m end-->
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
	layer.alert('请输入用户名 ！', {icon: 6});
	return false;
}else if(!userpwd){
	$('#userpwd').css("border-color","red");
	layer.alert('请输入密码 ！', {icon: 6});
	return false;
}
$.ajax({
	url:"yhlogin",
	type:"post",
	data:{"username":username,"userpwd":userpwd},
	success:function(ret){
		if(ret=='no'){
			layer.alert('用户名或密码错误！', {icon: 6});
		}else if(ret=='ok'){
			window.location.href = "<%=path %>/perschedule/checktask";
		}
	},
	error:function(ret){
		layer.alert('通信异常 ！', {icon: 6});
	}
});

}	
</script>


</body>
</html>
