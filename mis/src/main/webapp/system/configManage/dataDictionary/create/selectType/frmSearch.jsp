<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<title>电子商务后台管理系统-查询数据字典类型</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">查询数据字典类型</div>
<div class="table_content">
	<form id="frmInput" name="frmInput" action="${ctx}/business/businessManage/dataDictionary/queryGeCodeTypePage.do" method="post" target="fraSearchList">
		<table align="center" width="720px">
			<tr>
				<td class="td_head" width="200px" nowrap>
					代码类型：
				</td>
				<td class="td_body" width="150px" >
					<input type="text" id="codeType" name="geCodeType.codeType" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head" width="80px" nowrap>
					简体描述：
				</td>
				<td class="td_body">
					<input type="text" id="codeTypeCDesc" name="geCodeType.codeTypeCDesc" style="width:120px;" maxlength="25">
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
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>查询</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'"  align="right" onclick="javascript:doClear();" nowrap>清空</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>
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
</body>
</html>
