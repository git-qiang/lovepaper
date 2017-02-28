<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<div class="span3" style="width:200px;">
	<div class="sidebar-nav">
		<div class="nav-header" data-toggle="collapse"
			data-target="#dashboard-menu">
			<i class="icon-dashboard"></i>检查计划
		</div>
		<ul id="dashboard-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="yearplan" namespace="/perschedule">年计划</s:a>
			</li>
			<li>
				<s:a action="monthplan" namespace="/perschedule">月计划</s:a>
			</li>			
			<li>
				<s:a action="dayplan" namespace="/perschedule">日计划</s:a>
			</li>
		<!-- 
			<li>
				<s:a action="loadAddSchedule" namespace="/perschedule">新增检查计划范围</s:a>
			</li>
			 -->
			<li>
				<s:a action="taskpreparation" namespace="/perschedule">创建检查任务</s:a>
			</li>
			<li>
				<s:a action="checktask" namespace="/perschedule">检查任务管理</s:a>
			</li>
		</ul>
		<div class="nav-header" data-toggle="collapse"
			data-target="#accounts-menu">
			<i class="icon-briefcase"></i>统计报表
		</div>
		<ul id="accounts-menu" class="nav nav-list collapse in">
			<li>
				<s:a action="omitleaktable" namespace="/omitleak">检测报告首页</s:a>
			</li>  
			
			
			<li>
            	<s:a action="" namespace="">弱检报告</s:a>
            </li>
            <li>
            	<s:a action="" namespace="">异常报告</s:a>
            </li>
            <li>
				<s:a action="" namespace="">误报错报记录</s:a>
			</li>
			<li>
				<s:a action="" namespace="">计划完成情况</s:a>
			</li>
            
		</ul>
	</div>
</div>
