<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务后台管理系统-外部系统信息查询</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">查询外部系统信息</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="" method="post" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="100px" nowrap>
						外部系统ID：   
					</td>
					<td class="td_body">
						<input id="externalSysId" name="externalSysId"  style="width:150px;" />
					</td>
					<td class="td_head" width="100px" nowrap>外部系统类型：</td>
					<td class="td_body">
						<select id="externalSysType" name="externalSysType" style="width:150px;">
							<option value="" selected>--请选择--</option>
							<option value="1">内网系统</option>
							<option value="2">外部组织系统</option>
						</select>
					</td>
				</tr>
				<tr id="car">
					<td class="td_head" width="100px" nowrap>
						系统名称：   
					</td>
					<td class="td_body">
						<input id="externalSysName" name="externalSysName"  style="width:150px;" />
					</td>
					<td class="td_head" width="100px" nowrap>
						备注：   
					</td>
					<td class="td_body">
						<input id="remark" name="remark"  style="width:150px;" />
					</td>
				</tr>
				<tr height="60px">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnFocus" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="doSearch()" nowrap>查询</td>
								<td class="btnBigOnFocus" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:$('form')[0].reset();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
function doSearch(){
	$("#frmInput").attr("action", "${ctx}/interfacePortal/findExternalSysInfo.do");
	$("#frmInput").submit();
}
</script>
</body>
</html>
