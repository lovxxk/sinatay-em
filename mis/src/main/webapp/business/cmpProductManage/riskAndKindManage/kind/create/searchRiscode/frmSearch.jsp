<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
		<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
		<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<title>险种代码查询</title>
</head>
<body topmargin="0" leftmargin="0">
   <div class="public_fun_title">
		查询要选择的险种代码
	</div>
	<div class="table_content">
	<form id="frmInput" name="frmInput" action="${ctx}/business/cmpProductManage/riskAndKindManage/findKindCode.do" method="post" target="fraSearchList">
		<table class="table_style" align="center" width="98%">
			<tr>
				<td class="td_head" width="10%" nowrap>
					险种代码：
				</td>
				<td class="td_body" width="15%" >
					<input type="text" id="riskCode" name="geRisk.riskCode" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head" width="10%" nowrap>
					中文名称：
				</td>
				<td class="td_body" width="15%" >
					<input type="text" id="riskCName" name="geRisk.riskCName" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head" width="10%" nowrap>
					业务领域：
				</td>
				<td class="td_body" style="width:150px;">
					<select name="geRisk.businessArea">
					    <option value="">--请选择--</option>
					  <c:forEach var="bussinessArea" items="${list}"> 
					     <option  value="${bussinessArea.id.codeCode}">${bussinessArea.codeCName}</option>
					  </c:forEach>
					 </select>
				</td>
			<tr height="60px;">
				<td colspan="8" align="center">
					<table>
						<tr>
							<td nowrap>
								<input type="hidden" name="pageNo" id="pageNo" value="1">
								<input type="hidden" name="pageSize" id="pageSize" value="20">
							</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>查询</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
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
		$("#riskCode").val(jQuery.trim($("#riskCode").val()));
		$("#riskCName").val(jQuery.trim($("#riskCName").val()));
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	$(function(){
		doSearch();
	});
</script>
</body>
</html>
