<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务管理系统-第三方合作伙伴</title>
	<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
			
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">供应商维护</div>
	<div class="table_content">
		<form id="frmInput"  method="post" action="${ctx}/party/findThirdParterInfo.do?opertion=fordbclick" target="fraSearchList">
			<table class="table_style" align="center" width="800px">
				<tr>
					<td class="td_head " width="80px" nowrap>
						公司名称：
					</td>
					<td class="td_body" width="180px" >
						<input type="text"  name="geThirdParterInfo.thirdParterName" style="width:170px;" >
					</td>
						
					
					<td class="td_head" width="80px"  nowrap>
						公司类型：
					</td>
					<td class="td_body" >
						<select name="geThirdParterInfo.companyType">
							<option value="">--请选择--</option>
							<s:if test="geCodeCompanyTypeList!=null" >
								<s:iterator value="geCodeCompanyTypeList" var="geCodeCompanyType">
									<option value="<s:property value="#geCodeCompanyType.id.codeCode"/>">
										<s:property value="#geCodeCompanyType.codeCName"/>
									</option>
								</s:iterator>
							</s:if>
						</select>
					</td>
					
					<td class="td_head" width="80px" nowrap>
						所属机构：
					</td>
					<td class="td_body" >
						<input type="text" readonly="readonly" id="authorityDepartmentManager">
						<input type="hidden" name="geThirdParterInfo.deptID" id="authorityid">
						<input type="button" value="选择区域" onclick="alterTree();">
					</td>
				</tr>
				
				<%/*
				<tr>
					<td class="td_head td_head_center" width="10%" nowrap>
						公司：
					</td>
					<td class="td_body" width="15%" >
						<input type="text"  name="geThirdParterInfo.email" style="width:170px;" >
					</td>
					<td class="td_head td_head_center" width="10%"  nowrap>
						所属机构：
					</td>
					<td class="td_body" width="10%"  nowrap colspan="3">
						<input type="text" readonly="readonly" id="authorityDepartmentManager">
						<input type="hidden" name="geThirdParterInfo.deptID" id="authorityid">
						<input type="button" value="选择区域"  onclick="alterTree();">
					</td>
				</tr>
				*/ %>
				
				<tr height="60px;">
					<td colspan="8" align="center">
						<table id="operatorTable">
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
</body>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	//弹出一棵树
	function alterTree(){
		var authorityid = document.getElementById("authorityid").value;
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_OORD_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
</script>
</html>
