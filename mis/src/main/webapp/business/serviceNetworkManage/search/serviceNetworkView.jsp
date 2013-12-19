<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-查看服务网点</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			查看服务网点信息
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/serviceNetwork/deleteServiceNetwork.do?network.serialNo=${network.serialNo}" id="SMSfm" method="post">
	<table class="table_style" align="center" width="400px" id="geCardTable">
	<tr><td height=10>&nbsp;</td></tr>
	
	<tr>
		<td class="td_head" width="120px" nowrap>省：</td>
		<td class="td_body" width="380px">${network.province}</td>
	</tr>
    <tr>
      <td class="td_head" width="120px" nowrap>市：</td>
      <td class="td_body" width="380px">${network.city}</td>
   </tr>
   	<tr>
		<td class="td_head" width="120px" nowrap>服务网点编码：</td>
		<td class="td_body" width="380px">${network.manageCom}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>服务网点名称：</td>
		<td class="td_body" width="380px">${network.manageName}</td>
	</tr>
	</tr>
	
		<tr>
		<td class="td_head" width="120px" nowrap>优先级：</td>
		<td class="td_body" width="380px">${network.sort}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>服务网点地址：</td>
		<td class="td_body" width="380px">${network.address}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>服务网点电话：</td>
		<td class="td_body" width="380px">${network.phone}</td>
	</tr>
	
		<tr>
		<td class="td_head" width="120px" nowrap>服务类型：：</td>
		<td class="td_body" width="380px">${network.type}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>服务星级：</td>
		<td class="td_body" width="380px">${network.grade}</td>
	</tr>
	
		<tr>
		<td class="td_head" width="120px" nowrap>工作时间：</td>
		<td class="td_body" width="380px">${network.workTime}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>纬度：</td>
		<td class="td_body" width="380px">${network.longItude}</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>经度：</td>
		<td class="td_body" width="380px">${network.latItude}</td>
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
// 删除
 function doDelete(){
	if(confirm('您确认删除该医院吗？')){
		$("#SMSfm").submit();
	}

 }
 // 修改
 function doUpate(){
  	  window.location.href = "${ctx}/serviceNetwork/prepareUpdateServiceNetwork.do?network.serialNo=${network.serialNo}";
  }
 
</script>
</body>
</html>
