<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-查看短信配置功能</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			查看短信配置
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/configManage/SMSConfig/deleteSms.do" id="frmInput" method="post"  target="smsIframe" >
	<table align="center" style="width:500px;line-height:25px;" id="geSmsConfigTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>短信名称：</td>
		<td class="td_body" width="530px">
			<input id="smsId" name="geSmsConfig.smsId" type="hidden" value="${geSmsConfig.smsId}"/>
			${geSmsConfig.smsName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>适用功能：</td>
		<td class="td_body" width="530px" >
		    ${geSmsConfig.sendSmsName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>有效标志：</td>
		<td class="td_body" width="530px">
		    <c:if test="${geSmsConfig.validInd == '1'}">有效</c:if>
		    <c:if test="${geSmsConfig.validInd == '0'}">无效</c:if>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>短信内容：</td>
		<td class="td_body" width="530px">
		  <textarea id="smsContent" rows="5" cols="31" disabled="disabled" class=required>${geSmsConfig.smsContent}</textarea>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
			   <tr>
                  <acc:showView source="ROLE_S_SMSC_U">
                   <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" onclick="doUpate();" nowrap>编辑</td>
                  </acc:showView>
                  <acc:showView source="ROLE_S_SMSC_M_D">
                    <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
                  </acc:showView>
      				<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
      				  onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
      			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
   <iframe name="smsIframe" style="display: none"></iframe>
</div>
</body>
 
<script type="text/javascript">
   function doUpate(){
   	  window.location.href = "${ctx}/system/configManage/SMSConfig/updatePrepare.do?geSmsConfig.smsId=${geSmsConfig.smsId}";
   }
   function doDelete(){
   	 if(confirm('您确定删除该短信配置吗？')){
   		 $("#frmInput").submit();
   	 }
   }
</script>
</html>
