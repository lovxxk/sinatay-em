<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<style>
	.showdiv{
	    float:center;
		width:1000px;
		height:200px;
		align:center;
		background-color:#709CD2;
		border:2px solid #709CD2;
		padding:0px;
		display:none;
	 }
	 .textarea{
	  width:1000px;
	  height:200px;
	}
	 .showdivlit{
	    float:center;
		width:500px;
		height:100px;
		align:center;
		background-color:#709CD2;
		border:2px solid #709CD2;
		padding:0px;
		display:none;
	 }
	 .textarealit{
	  width:500px;
	  height:100px;
	}
	.image{
	 float:right;
	}
</style>
<body topmargin="2" leftmargin="2">

	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap id="idNum">序号</td>
		<td class="search_head" nowrap >日志来自的系统</td>
		<td class="search_head" nowrap>类别</td>
		<td class="search_head" nowrap>用户代码</td>
		<td class="search_head" nowrap>子用户代码</td>
		<td class="search_head" nowrap>具体代码</td>
		<td class="search_head" nowrap>描述</td>
		<!--
		<td class="search_head" nowrap>异常堆栈</td>
		-->
		<td class="search_head" nowrap>时间</td>
		<td class="search_head" nowrap>级别</td>
		<td class="search_head" nowrap>车牌号</td>
	</tr>
	  <c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	<s:if test="page.result!=null">
	<s:iterator value="page.result" var="model" status="status">	
		<tr id="tr_${status.index}">
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>
			 <a href="javascript:viewException('${ctx}/exception/viewExceptionDetil.do?geMonitorAppException.serialNo=<s:property value="#model.serialNo"/>');">
			  <s:property value="#model.appName" />
			  </a>
			</td>
			<td class="search_body_center" nowrap><s:property value="#model.exceptionKind" /></td>
			<td class="search_body_center" nowrap><s:property value="#model.userExceptionCode" /></td>
			<td class="search_body_center" nowrap><s:property value="#model.subUserExceptionCode" /></td>
			<td class="search_body_center" nowrap><s:property value="#model.concreteExceptionCode" /></td>
			<td class="search_body_center" nowrap>
			  <div onclick="showInLit(this);"><image src="${ctx}/global/images/showDiv.png"  name="images"/>
					 <div   class="showdivlit">
		 	          <div id="light" class="win">
		                 <div class="close">
		                     <div class="image">
		                          <image src="${ctx}/global/images/dialogclose.gif" onclick="hideInLit(this);"></image>
		                      </div>
				              <textarea class="textarealit" readonly>
			             	       <s:property value="#model.exceptionDesc" />
			                 </textarea>
			            </div>
			           </div>
		           </div>
			  </div>
			</td>
			<!--<td class="search_body" nowrap>
			  <div onclick="showIn(this);"><image src="${ctx}/global/images/showDiv.png"  name="images"/>
					 <div   class="showdiv">
		 	          <div id="light" class="win">
		                 <div class="close">
		                     <div class="image">
		                          <image src="${ctx}/global/images/dialogclose.gif" onclick="hideIn(this);"></image>
		                      </div>
				              <textarea class="textarea" readonly>
			             	      <s:property value="#model.exceptionReason" />
			                 </textarea>
			            </div>
			           </div>
		           </div>
			  </div>
			</td>
			--><td class="search_body_center" nowrap><s:date name="#model.exceptionTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		    <td class="search_body_center" nowrap><s:property value="#model.exceptionGrade" /></td>
			 <td class="search_body_center" nowrap><s:property value="#model.identifyFlag" /></td>
		</tr>
	</s:iterator>
	</s:if>
	
	</table>
</body>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
function showReason(obj){
	alert("加入黑名单原因:"+obj);
}
// 查看险种
function viewException(url){
    window.open(url,"查看异常" ,"top=100, left=100, width=900,height=600,scrollbars=yes,toolbar=no");
}
//打开div
function showIn(obj){
	var flag = true;
	var imgdiv = $(obj);
	imgdiv.find("div").each(function(){
		if(flag) {
			var showdiv = $(this);
			var classdiv = showdiv.attr("class");
			if(classdiv == "showdiv") {
				showdiv.show();
				flag = false;
				return;
			}
		}
		
	});
}  
//隐藏div
function hideIn(obj){
	var flag = true;
	var imgdiv = $(obj);
	imgdiv.parents("div").each(function(){
		if(flag) {
			var showdiv = $(this);
			var classdiv = showdiv.attr("class");
			if(classdiv == "showdiv") {
				showdiv.slideUp();
				flag = false;
				return;
			}
		}
	});
}

//显示小的div弹出层
//打开div
function showInLit(obj){
	var flag = true;
	var imgdiv = $(obj);
	imgdiv.find("div").each(function(){
		if(flag) {
			var showdiv = $(this);
			var classdiv = showdiv.attr("class");
			if(classdiv == "showdivlit") {
				showdiv.show();
				flag = false;
				return;
			}
		}
		
	});
}  
//关闭小的div
function hideInLit(obj){
	var flag = true;
	var imgdiv = $(obj);
	imgdiv.parents("div").each(function(){
		if(flag) {
			var showdiv = $(this);
			var classdiv = showdiv.attr("class");
			if(classdiv == "showdivlit") {
				showdiv.slideUp();
				flag = false;
				return;
			}
		}
	});
}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

