<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-查看车险异常信息</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			车险业务辅助查询详细信息
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<%// <form action="${ctx}/risk/deleteGeRiskList.do?riskCode=${geRisk.riskCode}" id="SMSfm" method="post">%>
	<table class="table_style" align="center" width="90%" id="geCardTable">
	<tr><td height=10>&nbsp;</td></tr>
	<tr>
		<td class="td_head" width="120px" nowrap>日志来自系统：</td>
		<td class="td_body" width="530px">${geMonitorAppException.appName}</td>
	</tr>
    <tr>
      <td class="td_head" width="120px" nowrap>类别：</td>
      <td class="td_body" width="530px">${geMonitorAppException.exceptionKind}</td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>用户代码：</td>
		<td class="td_body" width="530px">${geMonitorAppException.userExceptionCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>子用户代码：</td>
		<td class="td_body" width="530px">${geMonitorAppException.subUserExceptionCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>具体代码：</td>
		<td class="td_body" width="530px">${geMonitorAppException.concreteExceptionCode}</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>描述：</td>
         <td class="td_body" width="530px">
          ${geMonitorAppException.exceptionDesc}
          </td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>技术明细堆栈：</td>
         <td class="td_body" width="530px">
           <textarea rows="15" cols="100" readonly="readonly">  ${geMonitorAppException.exceptionReason}</textarea>
         </td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>日期：</td>
         <td class="td_body" width="530px">
         	<fmt:formatDate value="${geMonitorAppException.exceptionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
         </td>
    </tr>
     <tr>
         <td class="td_head" width="120px" nowrap>级别：</td>
         <td class="td_body" width="530px">
	            ${geMonitorAppException.exceptionGrade}
          </td>
    </tr>
      <tr>
         <td class="td_head" width="120px" nowrap>车牌号：</td>
         <td class="td_body" width="530px">
	            ${geMonitorAppException.identifyFlag}
          </td>
    </tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr><!--
			    <acc:showView source="ROLE_BU_P_CARD_D">
                   <td onclick="doUpate();"class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                       onmouseout="this.className='btnBigOnBlur'" nowrap>编辑</td>
                   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_BU_P_CARD_XX">
      				<td onclick="doDelete();" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" nowrap>删除</td>
      				<td>&nbsp;</td>
                </acc:showView>
				--><td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
// 删除
 function doDelete(){
	$("#SMSfm").submit();
 }
 // 修改
 function doUpate(){
  	  window.location.href = "${ctx}/risk/prepareUpdateGeRisk.do?geRisk.riskCode=${geRisk.riskCode}";
  }
 
</script>
</body>
</html>
