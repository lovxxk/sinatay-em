<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�鿴����</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�鿴������Ϣ
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/universal/deleteUniversal.do?universal.serialNo=${universal.serialNo}" id="SMSfm" method="post">
	<table class="table_style" align="center" width="400px" id="geCardTable">
	<tr><td height=10>&nbsp;</td></tr>
	<tr>
		<td class="td_head" width="120px" nowrap>��Ʒ���ƣ�</td>
		<td class="td_body" width="380px">${universal.riskName}</td>
	</tr>
    <tr>
      <td class="td_head" width="120px" nowrap>�������ڣ�</td>
      <td class="td_body" width="380px"><s:date name="universal.culDate" format="yyyy-MM-dd" /></td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���������ʣ�</td>
		<td class="td_body" width="380px">${universal.dateRate}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�ۺ���������ʣ�</td>
		<td class="td_body" width="380px">${universal.yearRate}</td>
	</tr>
	<tr height=35><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width="200px" align="center">
			<tr>
			    <acc:showView source="ROLE_RISK_U">
                   <td onclick="doUpate();"class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                       onmouseout="this.className='btnBigOnBlur'" nowrap>�༭</td>
                   <td>&nbsp;</td>
                </acc:showView>
                <acc:showView source="ROLE_RISK_D">
      				<td onclick="doDelete();" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" nowrap>ɾ��</td>
      				<td>&nbsp;</td>
                </acc:showView>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
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
	if(confirm('��ȷ��ɾ����������')){
		$("#SMSfm").submit();
	}

 }
 // �޸�
 function doUpate(){
  	  window.location.href = "${ctx}/universal/prepareUpdateUniversal.do?universal.serialNo=${universal.serialNo}";
  }
 
</script>
</body>
</html>
