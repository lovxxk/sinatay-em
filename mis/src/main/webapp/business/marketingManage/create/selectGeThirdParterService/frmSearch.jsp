<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<title>电子商务管理系统-第三方合作伙伴</title>
	<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">第三方产品维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/party/findGeThirdParterService.do?opertion=marketing" target="fraSearchList">
			<input type="hidden" name="nameCount" value="<s:property value="nameCount"/>">
			<input type="hidden" name="deptId" value="<s:property value="deptId"/>">
			<table class="table_style" align="center" width="98%">
				<tr>
					<td class="td_head td_head_center" width="20%"  nowrap>
						商品名称：
					</td>
					<td class="td_body" width="20%" >
						<input type="text" name="geThirdParterService.itemName" style="width:170px;" >
					</td>
					
						<td class="td_head td_head_center" width="10%" nowrap>
							所在公司：
						</td>
						<td class="td_body" width="40%" >
						<input type="text" name="thirdParterName" style="width:170px;" readonly="readonly">
						<input type="hidden"  name="geThirdParterService.geThirdParterInfo.thirdParterID" >
						<input type="button" value="选择公司" onclick="alertThirdService();">
						</td>
					
					
				</tr>
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
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	$(function(){
		doSearch();
	});
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	//弹出一棵树
	//弹出一个商品的提示框
	function alertThirdService(){
		(function($){
		    $.getUrlParam = function(name)
		    {
		        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		        var r = window.location.search.substr(1).match(reg);
		        if (r!=null) return unescape(r[2]); return null; 
		    }
		})(jQuery);
		var deptId=$.getUrlParam('deptId');
		//var deptId = "3110000";//document.getElementById("deptId").value;
		window.open(contextRootPath+"/business/marketingManage/create/selectGeThirdParterInfo/index.jsp?deptId="+deptId,"查询供应商" ,"top=100, left=100, width=900,height=600,toolbar=no");
	}
</script>
</html>
