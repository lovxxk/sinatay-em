<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-查看险种</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			查看险种信息
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/risk/deleteGeRiskList.do?riskCode=${geRisk.riskCode}" id="SMSfm" method="post">
	<table class="table_style" align="center" width="400px" id="geCardTable">
	<tr><td height=10>&nbsp;</td></tr>
	<tr>
		<td class="td_head" width="120px" nowrap>险种代码：</td>
		<td class="td_body" width="380px">${geRisk.riskCode}</td>
	</tr>
    <tr>
      <td class="td_head" width="120px" nowrap>中文名称：</td>
      <td class="td_body" width="380px">${geRisk.riskCName}</td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>英文名称：</td>
		<td class="td_body" width="380px">${geRisk.riskEName}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>繁体名称：</td>
		<td class="td_body" width="380px">${geRisk.riskTName}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>业务领域：</td>
		<td class="td_body" width="380px">${areaName}</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>险种类别：</td>
         <td class="td_body" width="380px" id="riskType"></td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>操作人员：</td>
         <td class="td_body" width="380px">${geRisk.operatorID}</td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>创建日期：</td>
         <td class="td_body" width="380px">
         	<fmt:formatDate value="${geRisk.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
         </td>
    </tr>
   
	<tr height=35><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width="200px" align="center">
			<tr>
			    <acc:showView source="ROLE_RISK_U">
                   <td onclick="doUpate();"class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                       onmouseout="this.className='btnBigOnBlur'" nowrap>编辑</td>
                   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_RISK_D">
      				<td onclick="doDelete();" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" nowrap>删除</td>
      				<td>&nbsp;</td>
                </acc:showView>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
$(function(){
	// var riskTypeCode=;
	 var riskTypeCode=${geRisk.riskType};
	 if(riskTypeCode=="01"){
		 $("#riskType").text("传统险");
	 }else if(riskTypeCode=="02"){
		 $("#riskType").text("分红"); 
	 }else if(riskTypeCode=="03"){
		 $("#riskType").text("投连"); 
	 }else if(riskTypeCode=="04"){
		 $("#riskType").text("万能");
	 }else if(riskTypeCode=="11"){
		 $("#riskType").text("车险");
	 }else if(riskTypeCode=="12"){
		 $("#riskType").text("家财险");
	 }else if(riskTypeCode=="13"){
		 $("#riskType").text("意外险");
	 }else{
		 $("#riskType").text("其他");
	 }
});
// 删除
 function doDelete(){
	if(confirm('您确认删除该险种吗？')){
		$("#SMSfm").submit();
	}

 }
 // 修改
 function doUpate(){
  	  window.location.href = "${ctx}/risk/prepareUpdateGeRisk.do?geRisk.riskCode=${geRisk.riskCode}";
  }
 
</script>
</body>
</html>
