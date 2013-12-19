<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务管理系统-接口信息维护</title>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	doSearch();
});
</script>	
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">接口信息维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/interfacePortal/searchInterfaceInfoPageNew.do" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="120" nowrap>
						交易代码： 
					</td>
					<td class="td_body">
						<input type="text" id="transCode" name="interfaceInfo.transCode" maxlength="50" style="width:170px;">
					</td>
					<td class="td_head" width="120" nowrap>
						交易名称：
					</td>
					<td class="td_body">
						<input type="text" id="transName" name="interfaceInfo.transName" maxlength="50" style="width:170px;">
					</td>
				</tr>
				<tr>
					<td class="td_head" width="120" nowrap>
						账户状态： 
					</td>
					<td class="td_body">
						<select id="status" name="interfaceInfo.status" style="width:88px;">
							<option value="" selected>--请选择--</option>
							<option value="1">启用</option>
							<option value="2">停用</option>
						</select>
					</td>
				</tr>				
				<tr height="60px;">
					<td colspan="8" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" align="right" onclick="doClear()" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="interfaceInfo.transType" id="transType" value="F">
			<input type="hidden" name="interfaceInfo.clientUser.id" id="id" value="">
		</form>
	</div>
</body>
<script type="text/javascript">
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>