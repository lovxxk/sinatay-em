<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String nameCount = (String)request.getParameter("nameCount");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
	<title>���������̨����ϵͳ-��ѯ�����ֵ�����</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="select_header_top_bg">
	<div class="select_header_top_left1"></div>
	<div class="select_header_top_left2"></div>
	<div class="select_header_top_title">
		<div class="select_header_top_title_content" style="width:140px;">��ѯҪѡ��Ĺ�Ӧ��</div>
	</div>
	<div class="select_header_top_right1"></div>
	<div class="select_header_top_right2"></div>
</div>
<div class="table_content">
	<form id="frmInput" name="frmInput" action="${ctx}/party/findThirdParterInfo.do?opertion=fordbclick" method="post" target="fraSearchList">
	<input type="hidden" value="<%=nameCount%>" name="nameCount"/>
		<table class="table_style" align="center" width="98%">
			<tr>
				<td class="td_head td_head_center" width="10%" nowrap>
					��˾���ƣ�
				</td>
				<td class="td_body" width="15%" >
					<input type="text" name="geThirdParterInfo.thirdParterName" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head td_head_center" width="10%" nowrap>
					��˾�绰��
				</td>
				<td class="td_body" width="15%" >
					<input type="text" name="geThirdParterInfo.companyPhone" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head td_head_center" width="10%"  nowrap>
					��˾��ַ��
				</td>
				<td class="td_body" width="15%">
					<%/*
					<select id="businessArea" name="geCodeType.businessArea" style="width:80px;">
						<option value="">--��ѡ��--</option>
						<option value="1">����</option>
						<option value="2">����</option>
						<option value="3">����</option>
						<option value="4">������</option>
						<option value="9">����</option>
					</select>
					*/ %>
					<input type="text" name="geThirdParterInfo.address" style="width:120px;" maxlength="25">
				</td>
				<td class="td_head td_head_center" width="10%"  nowrap>
					��˾�������䣺
				</td>
				<td class="td_body" width="15%">
					<%/*
					<select id="validInd" name="geCodeType.validInd" style="width:80px;">
						<option value="">--��ѡ��--</option>
						<option value="0">��Ч</option>
						<option value="1">��Ч</option>
					</select>
					*/ %>
					<input type="text" name="geThirdParterInfo.email" style="width:120px;" maxlength="25">
				</td>
			</tr>
			
			<tr>
				<td class="td_head td_head_center" width="10%" nowrap>
					��˾��ַ��
				</td>
				<td class="td_body" width="15%" colspan="7">
					<input type="text" name="geThirdParterInfo.url" style="width:120px;" maxlength="25">
				</td>
				
			</tr>
			
			<tr height="60px;">
				<td colspan="8" align="right">
					<table>
						<tr>
							<td nowrap>
								<input type="hidden" name="pageNo" id="pageNo" value="1">
								<input type="hidden" name="pageSize" id="pageSize" value="20">
							</td>
							<td class="btnBigOnFocus" onclick="javascript:doSearch();" nowrap>��ѯ</td>
							<td class="btnBigOnFocus" align="right" onclick="javascript:doClear();" nowrap>���</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	window.onload = doSearch();
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
