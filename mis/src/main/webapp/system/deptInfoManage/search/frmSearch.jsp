<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<title>电子商务管理系统-角色查询</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">属性维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/deptInfoManage/queryDeptInfoByDisPage.do" target="fraSearchList">
			<table align="center" width="450px">
				<tr>
					<td class="td_head td_head_center" width="120px" nowrap>
						属性编号：
					</td>
					<td class="td_body" width="155px">
						<input type="text" id="roleid" name="geDeptInfo.attrID" style="width:150px;" maxlength="25">
					</td>
					<td class="td_head td_head_center" width="80px" nowrap>
						属性名称：
					</td>
					<td class="td_body">
						<input type="text" id="rolename" name="geDeptInfo.attrName" style="width:150px;" maxlength="25">
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		doSearch();
	});
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