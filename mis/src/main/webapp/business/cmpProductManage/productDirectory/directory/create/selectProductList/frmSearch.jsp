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
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">��ѯ��Ʒ</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" action="" method="post" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						��Ʒ���ͣ�   
					</td>
					<td class="td_body">
						<select id="netSaleProductType" name="netSaleProductType" style="width:80px;" onchange="netSaleProductTypeChange(this);">
							<option value="02" selected>�ǳ�</option>
<!-- 							<option value="01">��</option> -->
<!-- 							<option value="03">��</option> -->
						</select>
					</td>
					<td class="td_head" width="80px" nowrap>ҵ������</td>
					<td class="td_body">
						<select id="businessArea" name="businessArea" onchange="" style="width: 80px;">
							<option value="" selected>--��ѡ��--</option>
						</select>
					</td>
				</tr>
				<tr id="car">
					<td class="td_head" width="80px" nowrap>
						���ִ��룺   
					</td>
					<td class="td_body">
						<input id="riskCode" name="riskCode"  style="width:150px;" />
					</td>
					<td class="td_head" width="80px" nowrap>
						�������ƣ�   
					</td>
					<td class="td_body">
						<input id="riskCName" name="riskCName"  style="width:150px;" />
					</td>
				</tr>
				<tr id="noCar" style="display: none;">
					<td id="nocarProductCode" class="td_head" width="80px" nowrap>
						��Ʒ���룺   
					</td>
					<td id="nocarProdcutName" class="td_body">
						<input name="coreProductCode"  style="width:150px;" />
					</td>
					<td class="td_head" width="80px" nowrap>
						��Ʒ���ƣ�   
					</td>
					<td class="td_body">
						<input name="coreProductSimpleName"  style="width:150px;" />
					</td>
				</tr>
				<tr id="card"  style="display: none;">
					<td class="td_head" width="80px" nowrap>
						����Ʒ���ƣ�   
					</td>
					<td class="td_body" colspan="3">
						<input name="cardProductName"  style="width:150px;" />
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
	if(type == "01"){
		$("#car").show();
		$("#card").hide();
		$("#noCar").hide();
	}else if(type == "02"){
		$("#car").hide();
		$("#card").hide();
		$("#noCar").show();
	}else{
		$("#car").hide();
		$("#card").show();
		$("#noCar").hide();
	}
}

function doSearch(){
	var type = $("#netSaleProductType").val();
	if(type == "01"){
		$("#frmInput").attr("action", "${ctx}/productDirectory/findCarProduct.do");
		$("#frmInput").submit();
	} else if (type == "02"){
		$("#frmInput").attr("action", "${ctx}/productDirectory/findNetsalesProduct.do");
		$("#frmInput").submit();
	} else {
		$("#frmInput").attr("action", "${ctx}/productDirectory/findCardProduct.do");
		$("#frmInput").submit();
	}
}
$(function(){
	$.ajax({
		   cache :false,
		   type: "POST",
		   url: '${ctx}/productDirectory/findBusinessArea.do',
		   data: { perStr : ""},
		   dataType:"json",
		   success: function(data){
			   $("#businessArea").empty();
			   $("#businessArea").append("<option value=''>--��ѡ��--</option>");
		    	for(var i = 0; i < data.length; i++){
		    		var baObj = data[i];
		    		$("#businessArea").append("<option value='" + baObj.code + "'>" + baObj.name + "</option>");
		    	}
			},
		   error:function(XMLHttpRequest, textStatus, errorThrown){
			   
		   }
	});
	doSearch();
});
</script>
</body>
</html>
