<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务管理系统-客户端用户信息维护</title>
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">客户端用户信息维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/interfacePortal/searchClientUserPage.do" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="120" nowrap>
						账号：
					</td>
					<td class="td_body">
						<input type="text" id="loginName" name="clientUser.loginName" maxlength="50" style="width:170px;">
					</td>
					<td class="td_head" width="120" nowrap>
						账户状态： 
					</td>
					<td class="td_body">
						<select id="status" name="clientUser.status" style="width:88px;">
							<option value="">--请选择--</option>
							<option value="0">启用</option>
							<option value="1">停用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="120" nowrap>
						创建时间：
					</td>
					<td class="td_body" colspan="3">
						<input name="beginDate"  style="width:170px;" id="beginDate" readonly onclick="WdatePicker()" class="Wdate">
						到
						<input name="endDate"  style="width:170px;" id="endDate" readonly onclick="WdatePicker()" class="Wdate">
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
	/**日期控件高度调整*/
	var CDH = new changeDateHeight();
	CDH.dateIds = 'beginDate,endDate';
	CDH.minHeight = "170,30,*,60,0";
	CDH.changeHeight();	
</script>
</html>