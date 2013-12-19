<%@ page language="java" contentType="text/html;charset=GBK"%>
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
	<title>电子商务管理系统-数据字典查询</title>
</head>
<body topmargin="0" leftmargin="0">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">数据字典详细</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/businessManage/dataDictionary/queryGeCodePage.do" target="fraSearchList">
			<input type="hidden" name="geCode.id.codeType" value="<%=request.getAttribute("geCodeType.codeType").toString()%>"/>
			<table align="center" width="560px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						代码：
					</td>
					<td class="td_body">
						<input type="text" style="width:170px" id="codeCode" name="geCode.id.codeCode" maxlength="25">
					</td>
					<td class="td_head" width="80px" nowrap>
						代码中文名：
					</td>
					<td class="td_body">
						<input type="text" style="width:170px" id="userCode" name="geCode.codeCName" maxlength="25">
					</td>
					
				</tr>
				<!--  
				<tr>
					<td class="td_head" width="80px" nowrap>
						代码类型：
					</td>
					<td class="td_body">
						<select id="codeType" name="geCode.id.codeType" style="width:170px;">
							<option value="">全部</option>
							<c:forEach items="${codeTypeList}" var="geCodeType" varStatus="status">
								<option value="${geCodeType.codeType}">${geCodeType.codeTypeCDesc}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_head" width="80px"  nowrap>
						是否有效：
					</td>
					<td class="td_body">
						<select id="status" name="geCode.validInd">
							<option value="">全部</option>
							<option value="0">无效</option>
							<option value="1">有效</option>
						</select>
					</td>
				</tr>
				-->
				<tr height="60px">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:doClear();" nowrap>清空</td>
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
</script>
</html>