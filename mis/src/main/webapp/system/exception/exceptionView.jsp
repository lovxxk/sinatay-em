<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�鿴�����쳣��Ϣ</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			����ҵ������ѯ��ϸ��Ϣ
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
		<td class="td_head" width="120px" nowrap>��־����ϵͳ��</td>
		<td class="td_body" width="530px">${geMonitorAppException.appName}</td>
	</tr>
    <tr>
      <td class="td_head" width="120px" nowrap>���</td>
      <td class="td_body" width="530px">${geMonitorAppException.exceptionKind}</td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�û����룺</td>
		<td class="td_body" width="530px">${geMonitorAppException.userExceptionCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���û����룺</td>
		<td class="td_body" width="530px">${geMonitorAppException.subUserExceptionCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>������룺</td>
		<td class="td_body" width="530px">${geMonitorAppException.concreteExceptionCode}</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>������</td>
         <td class="td_body" width="530px">
          ${geMonitorAppException.exceptionDesc}
          </td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>������ϸ��ջ��</td>
         <td class="td_body" width="530px">
           <textarea rows="15" cols="100" readonly="readonly">  ${geMonitorAppException.exceptionReason}</textarea>
         </td>
    </tr>
    <tr>
         <td class="td_head" width="120px" nowrap>���ڣ�</td>
         <td class="td_body" width="530px">
         	<fmt:formatDate value="${geMonitorAppException.exceptionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
         </td>
    </tr>
     <tr>
         <td class="td_head" width="120px" nowrap>����</td>
         <td class="td_body" width="530px">
	            ${geMonitorAppException.exceptionGrade}
          </td>
    </tr>
      <tr>
         <td class="td_head" width="120px" nowrap>���ƺţ�</td>
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
                       onmouseout="this.className='btnBigOnBlur'" nowrap>�༭</td>
                   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_BU_P_CARD_XX">
      				<td onclick="doDelete();" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" nowrap>ɾ��</td>
      				<td>&nbsp;</td>
                </acc:showView>
				--><td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
// ɾ��
 function doDelete(){
	$("#SMSfm").submit();
 }
 // �޸�
 function doUpate(){
  	  window.location.href = "${ctx}/risk/prepareUpdateGeRisk.do?geRisk.riskCode=${geRisk.riskCode}";
  }
 
</script>
</body>
</html>
