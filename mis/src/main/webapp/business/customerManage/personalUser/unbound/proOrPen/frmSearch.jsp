<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<title>电子商务后台管理系统-保单解绑</title>
</head>
<body topmargin="0" leftmargin="0">
<div class="public_fun_title">查询已绑保单</div>
<div class="table_content">
	<form id="frmInput" name="frmInput" action="${ctx }/business/customerManage/personalUser/queryGeUserPolicyByDisPage.do" method="post" target="fraSearchList">
		<table align="center" width="800px">
			<tr>
				<td class="td_head" width="80px" nowrap>用户名：</td>
				<td class="td_body">
					${param.userName }
				</td>
				<acc:showView source="ROLE_B_PUSE_BC||ROLE_B_PUSE_BY">
				<td class="td_head" width="80px" nowrap>业务领域：</td>
				</acc:showView>
				<td class="td_body">
					<acc:showView source="ROLE_B_PUSE_BC">
						<input type="radio" buss="1" id="businessArea1" name="geUserPolicy.businessArea" value="3" checked onclick="doCheck()">财险
					</acc:showView>
					<acc:showView source="ROLE_B_PUSE_BY">
						<input type="radio" id="businessArea2" name="geUserPolicy.businessArea" value="4" onclick="doCheck()">养老险
					</acc:showView>
					<acc:showView source="ROLE_B_PUSE_BS">
						<input type="radio" id="businessArea3" name="geUserPolicy.businessArea" value="2" onclick="doCheck('businessArea3')">寿险
					</acc:showView>
				</td>
				<td class="td_head" width="80px" nowrap id="baodanhao">保单号：</td>
				<td class="td_body">
					<input type="text" id="policyNo" name="geUserPolicy.policyNo" style="width:170px;" maxlength="30">
				</td>
			</tr>
			<tr height="60px;">
				<td colspan="6" align="center">
					<table>
						<tr>
							<td nowrap>
								<input type="hidden" name="pageNo" id="pageNo" value="1">
								<input type="hidden" name="pageSize" id="pageSize" value="10">
							</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>查询</td>
							<td class="btnBigOnBlur" align="right"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" id="userID" name="geUserPolicy.userID" value="${param.userID }">
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("input[type='radio']").addClass("checkbox_border");
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
	$(function (){
		if($("#businessArea1").attr("buss") == "1"){
			$("#businessArea1").attr("checked",true);
		}else{
			$("#businessArea2").attr("checked",true);
		}
	});
	function doCheck(id){
		if(id == "businessArea3"){
			$("#policyNo").css("display","none");
			$("#baodanhao").text("");
		}else{
			$("#policyNo").css("display","");
			$("#baodanhao").text("保单号：");
		}
		
	}
</script>
</body>
</html>
