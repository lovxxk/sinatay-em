<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>���������̨����ϵͳ-�ǳ��ղ�Ʒ����-��ѯ��Ʒ</title>
	<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body topmargin="0" leftmargin="0" onload="setProductType();">
	<div class="public_fun_title">��ѯ��Ʒ</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="" method="post" target="fraSearchList">
		<input type="hidden" value="${xRule}" name="xrule"><%//X����%>
		<input type="hidden" value="${yAddShopping}" name="yaddShopping"><%//Y����%>
		<input type="hidden" value="${businessAreaFlag}" name="businessAreaFlag"><%//ҵ������:1,2,3,4%>
		<input type="hidden" value="${saleDept}" name="saleDept"><%//���۵��� %>
		
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						��Ʒ���ͣ�
					</td>
					<td class="td_body">
						<select id="netSaleProductType" name="netSaleProductType" style="width:80px;" onchange="netSaleProductTypeChange(this);">
							<c:if test="${businessAreaFlag==3}">
								<option value="02" selected>�ǳ�</option>
<!-- 								<option value="01" selected>��</option> -->
<!-- 								<option value="03">��</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==2}">
								<option value="02" selected>�ǳ�</option>
<!-- 								<option value="03">��</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==1}">
								<option value="02" selected>�ǳ�</option>
<!-- 								<option value="01" selected>��</option> -->
<!-- 								<option value="03">��</option> -->
							</c:if>
							<c:if test="${businessAreaFlag==4}">
								<option value="02" selected>�ǳ�</option>
<!-- 								<option value="03">��</option> -->
							</c:if>
							
						</select>
					</td>
					
					<td class="td_head" width="80px" nowrap id="riskCodeTR" style="display: none;">
						���ִ��룺   
					</td>
					<td class="td_body" id="riskCodeInput" style="display: none;">
						<input id="riskCode" name="riskCode"  style="width:150px;" />
					</td>
					
					<td  class="td_head" width="80px" nowrap id="nocarProductCodeTR" style="display: none;">
						��Ʒ���룺   
					</td>
					<td class="td_body" id="nocarProductCodeInput" style="display: none;">
						<input name="coreProductCode"  style="width:150px;" />
					</td>
					
					<td class="td_head" width="80px" nowrap id="deptAreaTR">
						������   
					</td>
					
					<td class="td_body" id="deptAreaInputTR">
						<input type="text" id="authorityDepartmentManager" name="saleDeptSelect"  style="width:150px;" value="<c:if test="${isParentFlag=='no'}">${deptName }</c:if>"/>
						<input type="hidden" id="authorityid" name="saleDeptSelectCode" value="<c:if test="${isParentFlag=='no'}"><s:property value="deptId"/></c:if>"/>
						<c:if test="${isParentFlag=='yes'}">
							<span  id="buttonId">
								<input type="button" value="��ѡ��" onclick="alertTree();" >
							</span>
						</c:if>
					</td>
				</tr>
				
				<tr id="car">
					<td class="td_head" width="80px" nowrap id="riskNameTR" style="display: none;">
						�������ƣ�   
					</td>
					<td class="td_body" id="riskNameInputTR"  style="display: none;">
						<input id="riskCName" name="riskCName"  style="width:150px; " />
					</td>
					
					<td class="td_head" width="80px" nowrap id="productNameTR" style="display: none;">
						��Ʒ���ƣ�   
					</td>
					<td class="td_body" id="productNameInputTR" style="display: none;">
						<input name="coreProductSimpleName"  style="width:150px;" />
					</td>
					
					<td class="td_head" width="80px" nowrap id="cardProductNameTR" style="display: none;">
						����Ʒ���ƣ�   
					</td>
					<td class="td_body" colspan="3" id="cardProductNameInputTR" style="display: none;">
						<input name="cardProductName"  style="width:150px;" />
					</td>
					
					<!--����-->
					<td class="td_head" width="80px" nowrap id="deptAreaTR" style="display: none;">
						������   
					</td>
					
					<td class="td_body" id="deptAreaInputTR" style="display: none;">
						<input type="text" id="authorityDepartmentManager" name="saleDeptSelect"  style="width:150px;" value="<c:if test="${isParentFlag=='no'}">${deptName }</c:if>"/>
						<input type="hidden" id="authorityid" name="saleDeptSelectCode" value="<c:if test="${isParentFlag=='no'}"><s:property value="deptId"/></c:if>"/>
						<c:if test="${isParentFlag=='yes'}">
							<span  id="buttonId">
								<input type="button" value="��ѡ��" onclick="alertTree();" >
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
									onmouseout="this.className='btnBigOnBlur'"  onclick="doSearch()" nowrap>��ѯ</td>
								<td class="btnBigOnFocus" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:$('form')[0].reset();" nowrap>���</td>
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
	if(type == "01"){//��
		//��ʾ���ִ��� 
		document.getElementById("riskCodeTR").style.display = "";
		document.getElementById("riskCodeInput").style.display = "";
		//���ز�Ʒ����
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//��ʾ�������� 
		document.getElementById("riskNameTR").style.display = "";
		document.getElementById("riskNameInputTR").style.display = "";
		//���ز�Ʒ���� ����Ʒ����
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		
		//���ص����ֶ�
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
	}else if(type == "02"){//�ǳ�
		//�������ִ��� 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//��ʾ��Ʒ����
		document.getElementById("nocarProductCodeTR").style.display="";
		document.getElementById("nocarProductCodeInput").style.display = "";
		
		//������������  ����Ʒ����
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		//��ʾ��Ʒ���� 
		document.getElementById("productNameTR").style.display = "";
		document.getElementById("productNameInputTR").style.display = "";
		
		//���ص����ֶ�
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
		
	}else{//��
		
		//�������ִ��� 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//��ʾ��Ʒ����
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//������������  ����Ʒ����
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "";
		document.getElementById("cardProductNameInputTR").style.display = "";
		//��ʾ��Ʒ���� 
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		
		//��ʾ�����ֶ�
		document.getElementById("deptAreaTR").style.display = "";
		document.getElementById("deptAreaInputTR").style.display = "";
		
	}
}

function doSearch(){
	var type = $("#netSaleProductType").val();
	if(type == "01"){//�� ��geRisk�����ٲ��ƷĿ¼��  
		$("#frmInput").attr("action", "${ctx}/marketing/findGeRisk.do");
		$("#frmInput").submit();
	} else if (type == "02"){//�ǳ�  
		$("#frmInput").attr("action", "${ctx}/marketing/findGeProductMain.do");
		$("#frmInput").submit();
	} else {//��
		$("#frmInput").attr("action", "${ctx}/marketing/findGeCardProduct.do");
		$("#frmInput").submit();
	}
}

function setProductType(){
	var saleProductType = $("#netSaleProductType").val();
	if(saleProductType=="01"){//��
		//��ʾ���ִ��� 
		document.getElementById("riskCodeTR").style.display = "";
		document.getElementById("riskCodeInput").style.display = "";
		//���ز�Ʒ����
		document.getElementById("nocarProductCodeTR").style.display="none";
		document.getElementById("nocarProductCodeInput").style.display = "none";
		
		//��ʾ�������� 
		document.getElementById("riskNameTR").style.display = "";
		document.getElementById("riskNameInputTR").style.display = "";
		//���ز�Ʒ���� ����Ʒ����
		document.getElementById("productNameTR").style.display = "none";
		document.getElementById("productNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		
		//���ص����ֶ�
		document.getElementById("deptAreaTR").style.display = "none";
		document.getElementById("deptAreaInputTR").style.display = "none";
	}
	if(saleProductType=="02"){//�ǳ�
		//�������ִ��� 
		document.getElementById("riskCodeTR").style.display = "none";
		document.getElementById("riskCodeInput").style.display = "none";
		//��ʾ��Ʒ����
		document.getElementById("nocarProductCodeTR").style.display="";
		document.getElementById("nocarProductCodeInput").style.display = "";
		
		//������������  ����Ʒ����
		document.getElementById("riskNameTR").style.display = "none";
		document.getElementById("riskNameInputTR").style.display = "none";
		document.getElementById("cardProductNameTR").style.display = "none";
		document.getElementById("cardProductNameInputTR").style.display = "none";
		//��ʾ��Ʒ���� 
		document.getElementById("productNameTR").style.display = "";
		document.getElementById("productNameInputTR").style.display = "";
		
		//��ʾ�����ֶ�
		document.getElementById("deptAreaTR").style.display = "";
		document.getElementById("deptAreaInputTR").style.display = "";
	}
}

function alertTree(){
	var parentFilterId = document.getElementsByName("saleDept")[0].value; 
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?&authorityid=ROLE_B_AAGA&type=2&parentFilterId='+parentFilterId,'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
</script>
</body>
</html>
