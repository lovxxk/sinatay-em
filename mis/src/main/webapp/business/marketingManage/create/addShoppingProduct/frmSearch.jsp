<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>电子商务后台管理系统-非车险产品管理-查询产品</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0" onload="setProductType();">
	<div class="public_fun_title">查询产品</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="" method="post" target="fraSearchList">
		<input type="hidden" value="${xRule}" name="xrule"><%//X坐标%>
		<input type="hidden" value="${yAddShopping}" name="yaddShopping"><%//Y坐标%>
		<input type="hidden" value="${businessAreaFlag}" name="businessAreaFlag"><%//业务领域:1,2,3,4%>
		<input type="hidden" value="${saleDept}" name="saleDept"><%//销售地区 %>
		
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						产品类型：
					</td>
					<td class="td_body">
						<select id="netSaleProductType" name="netSaleProductType" style="width:80px;" onchange="netSaleProductTypeChange(this);">
							<c:if test="${businessAreaFlag==3}">
								<option value="02" selected>非车</option>
<!-- 								<option value="01" selected>车</option> -->
<!-- 								<option value="03">卡</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==2}">
								<option value="02" selected>非车</option>
<!-- 								<option value="03">卡</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==1}">
								<option value="02" selected>非车</option>
<!-- 								<option value="01" selected>车</option> -->
<!-- 								<option value="03">卡</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==4}">
								<option value="02" selected>非车</option>
<!-- 								<option value="03">卡</option> -->
							</c:if>
							
						</select>
					</td>
					
					<td class="td_head" width="80px" nowrap id="riskCodeTR" style="display: none;">
						险种代码：   
					</td>
					<td class="td_body" id="riskCodeInput" style="display: none;">
						<input id="riskCode" name="riskCode"  style="width:150px;" />
					</td>
					
					<td  class="td_head" width="80px" nowrap id="nocarProductCodeTR" style="display: none;">
						产品代码：   
					</td>
					<td class="td_body" id="nocarProductCodeInput" style="display: none;">
						<input name="coreProductCode"  style="width:150px;" />
					</td>
					
					<td class="td_head" width="80px" nowrap id="deptAreaTR">
						地区：   
					</td>
					
					<td class="td_body" id="deptAreaInputTR">
						<input type="text" id="authorityDepartmentManager" name="saleDeptSelect"  style="width:150px;" value="<c:if test="${isParentFlag=='no'}">${deptName }</c:if>"/>
						<input type="hidden" id="authorityid" name="saleDeptSelectCode" value="<c:if test="${isParentFlag=='no'}"><s:property value="deptId"/></c:if>"/>
						<c:if test="${isParentFlag=='yes'}">
							<span  id="buttonId">
								<input type="button" value="请选择" onclick="alertTree();" >
							</span>
						</c:if>
					</td>
				</tr>
				
				<tr id="car">
					<td class="td_head" width="80px" nowrap id="riskNameTR" style="display: none;">
						险种名称：   
					</td>
					<td class="td_body" id="riskNameInputTR"  style="display: none;">
						<input id="riskCName" name="riskCName"  style="width:150px; " />
					</td>
					
					<td class="td_head" width="80px" nowrap id="productNameTR" style="display: none;">
						产品名称：   
					</td>
					<td class="td_body" id="productNameInputTR" style="display: none;">
						<input name="coreProductSimpleName"  style="width:150px;" />
					</td>
					
					<td class="td_head" width="80px" nowrap id="cardProductNameTR" style="display: none;">
						卡产品名称：   
					</td>
					<td class="td_body" colspan="3" id="cardProductNameInputTR" style="display: none;">
						<input name="cardProductName"  style="width:150px;" />
					</td>
					
					<!--地区-->
					<td class="td_head" width="80px" nowrap id="deptAreaTR" style="display: none;">
						地区：   
					</td>
					
					<td class="td_body" id="deptAreaInputTR" style="display: none;">
						<input type="text" id="authorityDepartmentManager" name="saleDeptSelect"  style="width:150px;" value="<c:if test="${isParentFlag=='no'}">${deptName }</c:if>"/>
						<input type="hidden" id="authorityid" name="saleDeptSelectCode" value="<c:if test="${isParentFlag=='no'}"><s:property value="deptId"/></c:if>"/>
						<c:if test="${isParentFlag=='yes'}">
							<span  id="buttonId">
								<input type="button" value="请选择" onclick="alertTree();" >
							</span>
						</c:if>
					</td>
					
				</tr>
				
				
				<tr height="60px">
					<td colspan="6" align="center">
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
function netSaleProductTypeChange(obj){
	var type = $(obj).val();
	if(type == "01"){//车
		//显示险种代码 
		document.getElementById("riskCodeTR").style.display = "";
		document.getElementById("riskCodeInput").style.display = "";
		//隐藏产品代码
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//显示险种名称 
		document.getElementById("riskNameTR").style.display = "";
		document.getElementById("riskNameInputTR").style.display = "";
		//隐藏产品名称 卡产品名称
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		
		//隐藏地区字段
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
	}else if(type == "02"){//非车
		//隐藏险种代码 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//显示产品代码
		document.getElementById("nocarProductCodeTR").style.display="";
		document.getElementById("nocarProductCodeInput").style.display = "";
		
		//隐藏险种名称  卡产品名称
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		//显示产品名称 
		document.getElementById("productNameTR").style.display = "";
		document.getElementById("productNameInputTR").style.display = "";
		
		//隐藏地区字段
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
		
	}else{//卡
		
		//隐藏险种代码 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//显示产品代码
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//隐藏险种名称  卡产品名称
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "";
		document.getElementById("cardProductNameInputTR").style.display = "";
		//显示产品名称 
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		
		//显示地区字段
		document.getElementById("deptAreaTR").style.display = "";
		document.getElementById("deptAreaInputTR").style.display = "";
		
	}
}

function doSearch(){
	var type = $("#netSaleProductType").val();
	if(type == "01"){//车 查geRisk表中再查产品目录表  
		$("#frmInput").attr("action", "${ctx}/marketing/findGeRisk.do");
		$("#frmInput").submit();
	} else if (type == "02"){//非车  
		$("#frmInput").attr("action", "${ctx}/marketing/findGeProductMain.do");
		$("#frmInput").submit();
	} else {//卡
		$("#frmInput").attr("action", "${ctx}/marketing/findGeCardProduct.do");
		$("#frmInput").submit();
	}
}

function setProductType(){
	var saleProductType = $("#netSaleProductType").val();
	if(saleProductType=="01"){//车
		//显示险种代码 
		document.getElementById("riskCodeTR").style.display = "";
		document.getElementById("riskCodeInput").style.display = "";
		//隐藏产品代码
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//显示险种名称 
		document.getElementById("riskNameTR").style.display = "";
		document.getElementById("riskNameInputTR").style.display = "";
		//隐藏产品名称 卡产品名称
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		
		//隐藏地区字段
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
	}
	if(saleProductType=="02"){//非车
		//隐藏险种代码 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//显示产品代码
		document.getElementById("nocarProductCodeTR").style.display="";
		document.getElementById("nocarProductCodeInput").style.display = "";
		
		//隐藏险种名称  卡产品名称
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		//显示产品名称 
		document.getElementById("productNameTR").style.display = "";
		document.getElementById("productNameInputTR").style.display = "";
		
		//显示地区字段
		document.getElementById("deptAreaTR").style.display = "";
		document.getElementById("deptAreaInputTR").style.display = "";
	}
}

function alertTree(){
	var parentFilterId = document.getElementsByName("saleDept")[0].value; 
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?&authorityid=ROLE_B_AAGA&type=2&parentFilterId='+parentFilterId,'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
</script>
</body>
</html>
