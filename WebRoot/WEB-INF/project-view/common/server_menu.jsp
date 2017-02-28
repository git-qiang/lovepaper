<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<div class="span3" style="width:200px;">
	<div class="sidebar-nav">
		<div class="nav-header" data-toggle="collapse"
			data-target="#dashboard-menu">
			<i class="icon-dashboard"></i>检查项管理
		</div>
		<ul id="dashboard-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="checkobjmain" namespace="/checkproj">检查项主页</s:a>
			</li>
			<li>
				<s:a action="searchCheckPro" namespace="/checkproj">检查项查询</s:a>
			</li>
			<li>
				<s:a action="selectChContent" namespace="/checkproj">检查项新增</s:a>
			</li>
			
			<li>
				<s:a action="classmanage" namespace="/">检查项分类管理</s:a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#accounts-menu">
			<i class="icon-briefcase"></i>检查对象管理
		</div>
		<ul id="accounts-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="login" namespace="/checktarget">检查对象主页</s:a>
			</li>  
			<li>
            	<s:a action="jumpSearchChecktarget" namespace="/checktarget">检查对象查询</s:a>
            </li>
            <li>
            	<s:a action="jumpAddChecktarget" namespace="/checktarget">检查对象新增</s:a>
            </li>
            <li>
				<s:a action="targetmanage" namespace="/checktarget">检查对象分类管理</s:a>
			</li>
            
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#settings-menu">
			<i class="icon-exclamation-sign"></i>检查点管理
		</div>
		<ul id="settings-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="checkpointmain" namespace="/checkPoint">检查点主页</s:a>
			</li>
			<li>
				<s:a action="querydata" namespace="/checkPoint">检查点查询</s:a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#legal-menu">
			<i class="icon-legal"></i>检查计划管理
		</div>
		<ul id="legal-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="loadSchedule" namespace="/checkschedule">查询检查计划</s:a>
			</li>
			<li>
				<s:a action="loadAddSchedule" namespace="/checkschedule">新增检查计划</s:a>
			</li>			
			<li>
				<s:a action="loadclassifymanage" namespace="/checkschedule">检查计划分类管理</s:a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#task-menu">
			<i class="icon-dashboard"></i>检查计划、周期、任务
		</div>
		<ul id="task-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="loadplanmaking" namespace="/checkschedule">检查计划主页</s:a>
			</li>
			<!-- 
			<li>
				<s:a action="querydata" namespace="/checktask">检查任务查询</s:a>
			</li>
			 -->
			<li>
				<s:a action="taskpreparation" namespace="/checktask">创建检查任务</s:a>
			</li>
			<li>
				<s:a action="checktaskmain" namespace="/checktask">检查任务管理</s:a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#organize-menu">
			<i class="icon-briefcase"></i>组织结构
		</div>
		<ul id="organize-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="deptaddload" namespace="/checkorgnize">新增部门</s:a>
			</li>
			<li>
				<s:a action="jobaddload" namespace="/checkorgnize">新增岗位</s:a>
			</li>
			<li>
				<s:a action="userprojectaddload" namespace="/checkorgnize">录入人员</s:a>
			</li>
			<li>
				<s:a action="tbuseraddload" namespace="/checkorgnize">新增管理员</s:a>
			</li>
			<li>
				<s:a action="orgnizemain" namespace="/checkorgnize">组织结构主页</s:a>
			</li>
			<li>
				<a href="">..</a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#statistics-menu">
			<i class="icon-exclamation-sign"></i>统计分析
		</div>
		<ul id="statistics-menu" class="nav nav-list collapse">
			<li>
				<a href="">..</a>
			</li>
			<li>
				<a href="">..</a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#11">
			<i class="icon-exclamation-sign"></i>基础数据导入导出
		</div>
		<ul id="11" class="nav nav-list collapse">
			<li>
				<a href="">批量导入人员表</a>
			</li>
			<li>
				<a href="">批量导入检查项</a>
			</li>
			<li>
				<a href="">批量导入检查对象</a>
			</li>
			<li>
				<a href="">批量导出人员表</a>
			</li>
			<li>
				<a href="">批量导出检查项</a>
			</li>
			<li>
				<a href="">批量导出检查对象</a>
			</li>
			<li>
				<a href="">批量导出检查点</a>
			</li>
		</ul>
		<div class="nav-header collapseed" data-toggle="collapse"
			data-target="#owner-menu">
			<i class="icon-legal"></i>权限管理
		</div>
		<ul id="owner-menu" class="nav nav-list collapse">
			<li>
				<a href="">..</a>
			</li>
			<li>
				<a href="">..</a>
			</li>
		</ul>
	</div>
</div>
