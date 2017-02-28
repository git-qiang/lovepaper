<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div style="text-align: right; margin-top: 20px; margin-right: 20px;"
	id="page">
	<span>每页显示</span>
	<select onchange="loaddata(1,this.value)" id="selectlist"
		style="width: 8%">
		<c:choose>
			<c:when
				test="${pageModel.list ne null && fn:length(pageModel.list) gt 0}">
				<option value="5"
					<c:if test="${pageModel.pageSize eq 5}">selected="selected"</c:if>>
					5
				</option>
				
				<option value="10"
					<c:if test="${pageModel.pageSize eq 10}">selected="selected"</c:if>>
					10
				</option>
				<option value="15"
					<c:if test="${pageModel.pageSize eq 15}">selected="selected"</c:if>>
					15
				</option>
				<option value="20"
					<c:if test="${pageModel.pageSize eq 20}">selected="selected"</c:if>>
					20
				</option>
				<option value="50"
					<c:if test="${pageModel.pageSize eq 50}">selected="selected"</c:if>>
					50
				</option>
			</c:when>
			<c:otherwise>
				<option>
					0
				</option>
			</c:otherwise>
		</c:choose>
	</select>
	<span>条</span>
	<font style="margin-left: 20px"> 共</font><SPAN style="color: red;"> [ ${pageModel.totalRecords} ] </SPAN><font>条</font>
	<c:choose>
		<c:when test="${pageModel.list ne null && pageModel.pageNo gt 1}">
			<a href="javascript:loaddata(1,'${pageModel.pageSize}')"
				style="margin-left: 20px">首页</a>
			<a
				href="javascript:loaddata('${pageModel.pageNo-1}','${pageModel.pageSize}')"
				style="margin-left: 20px">上一页</a>
		</c:when>
		<c:otherwise>
			<span style="margin-left: 20px">首页</span>
			<span style="margin-left: 20px">上一页</span>
		</c:otherwise>
	</c:choose>
	<SPAN style="color: red;"> [ ${pageModel.pageNo} ] </SPAN>
	<c:choose>
		<c:when
			test="${pageModel.list ne null && pageModel.pageNo lt pageModel.bottomPageNo}">
			<a
				href="javascript:loaddata('${pageModel.pageNo+1}','${pageModel.pageSize}')">下一页</a>
			<a
				href="javascript:loaddata('${pageModel.bottomPageNo}','${pageModel.pageSize}')"
				style="margin-left: 20px">尾页</a>
		</c:when>
		<c:otherwise>
			<span>下一页</span>
			<span style="margin-left: 20px">尾页</span>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript" src="${context_path}/js/jquery.pjax.js"></script>
<script type="text/javascript">
function loaddata(pageNo,pageSize){
		var href = window.location.href;
		var url = window.location.pathname;
		var data = window.location.search;
		var no = data.indexOf("pageNo");
		var ss="";
		var jg;
		if(no!=-1){//包含
			var split = data.split("&");
			for(var i in split){
				if(split[i].indexOf("pageNo")!=-1){
					split[i]="pageNo="+pageNo;
				}
				if(split[i].indexOf("pageSize")!=-1){
					split[i]="pageSize="+pageSize;
				}
			}
			for( var i in split){
				if(i==split.length-1){
					ss += split[i];
					break;
				}
					ss += split[i]+"&";
			}
			if(split[0].indexOf("pageNo")!=-1){
				jg = url+"?"+ss;
			}else{
				jg = url+ss;
			}
			
		}else{
			if(href.indexOf("?")!=-1){
				jg = href+"&pageNo="+pageNo+"&pageSize="+pageSize;
			}else{
				jg = href+"?pageNo="+pageNo+"&pageSize="+pageSize;
			}
		}
		if(jg){
			//if ($.support.pjax) {
		        //$.pjax({url: jg , container: '.table_pjax', fragment: '.table_pjax'});
		        //}  else {
		            window.location.href = jg;
		        //}
		}
	}
	
</script>