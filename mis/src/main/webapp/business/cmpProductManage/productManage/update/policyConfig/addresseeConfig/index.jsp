<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>�����������ϵͳ-�ռ���������</title>
<script type="text/javascript">
$(document).ready(function(){
	  // ������д��Ĵ���...
	  init();
	  $("input[type='checkbox']").addClass("checkbox_border");
});

function init(){
	//�ռ�������
	var fixedPhone = "<s:property value='geAddresseeConfig.fixedPhone' />"
	if(fixedPhone==2){
		$("input[id='geAddresseeConfig.fixedPhone']").attr("checked",true);
		document.getElementById("geAddresseeConfig.fixedPhone.value").disabled = false;
		$("input[id='geAddresseeConfig.fixedPhone.value']").attr("checked",true);
		$("input[id='geAddresseeConfig.fixedPhone']").attr("value","2");
	}else if(fixedPhone==1){
		$("input[id='geAddresseeConfig.fixedPhone']").attr("checked", true);
		document.getElementById("geAddresseeConfig.fixedPhone.value").disabled = false;
		$("input[id='geAddresseeConfig.fixedPhone']").attr("value","1");
	}
	
	var province = "<s:property value='geAddresseeConfig.province' />"
		if(province==2){
			$("input[id='geAddresseeConfig.province']").attr("checked",true);
			document.getElementById("geAddresseeConfig.province.value").disabled = false;
			$("input[id='geAddresseeConfig.province.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.province']").attr("value","2");
		}else if(province==1){
			$("input[id='geAddresseeConfig.province']").attr("checked", true);
			document.getElementById("geAddresseeConfig.province.value").disabled = false;
			$("input[id='geAddresseeConfig.province']").attr("value","1");
		}
	
	var city = "<s:property value='geAddresseeConfig.city' />"
		if(city==2){
			$("input[id='geAddresseeConfig.city']").attr("checked",true);
			document.getElementById("geAddresseeConfig.city.value").disabled = false;
			$("input[id='geAddresseeConfig.city.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.city']").attr("value","2");
		}else if(city==1){
			$("input[id='geAddresseeConfig.city']").attr("checked", true);
			document.getElementById("geAddresseeConfig.city.value").disabled = false;
			$("input[id='geAddresseeConfig.city']").attr("value","1");
		}
	
	var county = "<s:property value='geAddresseeConfig.county' />"
		if(county==2){
			$("input[id='geAddresseeConfig.county']").attr("checked",true);
			document.getElementById("geAddresseeConfig.county.value").disabled = false;
			$("input[id='geAddresseeConfig.county.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.county']").attr("value","2");
		}else if(county==1){
			$("input[id='geAddresseeConfig.county']").attr("checked", true);
			document.getElementById("geAddresseeConfig.county.value").disabled = false;
			$("input[id='geAddresseeConfig.county']").attr("value","1");
		}
	var email = "<s:property value='geAddresseeConfig.email' />"
		if(email==2){
			$("input[id='geAddresseeConfig.email']").attr("checked",true);
			document.getElementById("geAddresseeConfig.email.value").disabled = false;
			$("input[id='geAddresseeConfig.email.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.email']").attr("value","2");
		}else if(email==1){
			$("input[id='geAddresseeConfig.email']").attr("checked", true);
			document.getElementById("geAddresseeConfig.email.value").disabled = false;
			$("input[id='geAddresseeConfig.email']").attr("value","1");
		}
	var consigneeConfig = "<s:property value='geAddresseeConfig.consigneeConfig' />"
		if(consigneeConfig==2){
			$("input[id='geAddresseeConfig.consigneeConfig']").attr("checked",true);
			document.getElementById("geAddresseeConfig.consigneeConfig.value").disabled = false;
			$("input[id='geAddresseeConfig.consigneeConfig.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.consigneeConfig']").attr("value","2");
		}else if(consigneeConfig==1){
			$("input[id='geAddresseeConfig.consigneeConfig']").attr("checked", true);
			document.getElementById("geAddresseeConfig.consigneeConfig.value").disabled = false;
			$("input[id='geAddresseeConfig.consigneeConfig']").attr("value","1");
		}
	var remark = "<s:property value='geAddresseeConfig.remark' />"
		if(remark==2){
			$("input[id='geAddresseeConfig.remark']").attr("checked",true);
			document.getElementById("geAddresseeConfig.remark.value").disabled = false;
			$("input[id='geAddresseeConfig.remark.value']").attr("checked",true);
			$("input[id='geAddresseeConfig.remark']").attr("value","2");
		}else if(remark==1){
			$("input[id='geAddresseeConfig.remark']").attr("checked", true);
			document.getElementById("geAddresseeConfig.remark.value").disabled = false;
			$("input[id='geAddresseeConfig.remark']").attr("value","1");
		}
	
}

function showRequeriedIterm(obj){
	if(obj.checked == true){
		obj.value = "1";
		document.getElementById(obj.id+".value").disabled = false;
	}else{
		obj.value = "0";
		document.getElementById(obj.id+".value").checked = false;
		document.getElementById(obj.id+".value").disabled = true;
	}
}

function changeValue(obj){
	if(obj.checked == true){
		document.getElementById(obj.id.replace(".value","")).value = "2";
	}else{
		document.getElementById(obj.id.replace(".value","")).value = "1";
	}
}
</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdateAddresseeConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
	<div style=" width: 550px;margin: 0 auto;padding-top: 20px;">
		<!-- �ռ���������  -->
			<s:if test="geProductMain.isPaper==\"1\" || geProductMain.isInvoice=='02'">
				<table>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.consigneeName"name="geAddresseeConfig.consigneeName" value="2" onclick="return false;" checked>����&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.consigneeName.value" onclick="changeValue(this);" onclick="return false;" checked></td>
						<td width="30">&nbsp;</td>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.telephone"name="geAddresseeConfig.telephone" value="2" onclick="return false;" checked>��ϵ�绰&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.telephone.value" onclick="return false;" checked></td>
					</tr>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.consigneeAddress"name="geAddresseeConfig.consigneeAddress" value="2" onclick="return false;" checked>�ռ���ַ &nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.consigneeAddress.value" onclick="return false;" checked></td>
						<td width="30">&nbsp;</td>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.zipCode"name="geAddresseeConfig.zipCode" value="2" onclick="return false;" checked>��������&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.zipCode.value" onclick="return false;" checked></td>
					</tr>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.fixedPhone"name="geAddresseeConfig.fixedPhone" value="0" onclick="showRequeriedIterm(this);">�̶��绰&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.fixedPhone.value" onclick="changeValue(this);" disabled></td>
						<td width="30">&nbsp;</td>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.province"name="geAddresseeConfig.province" value="0" onclick="showRequeriedIterm(this);">ʡ&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.province.value" onclick="changeValue(this);" disabled></td>
					</tr>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.city"name="geAddresseeConfig.city" value="0" onclick="showRequeriedIterm(this);">��&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.city.value" onclick="changeValue(this);" disabled></td>
						<td width="30">&nbsp;</td>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.county"name="geAddresseeConfig.county" value="0" onclick="showRequeriedIterm(this);">��/��&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.county.value" onclick="changeValue(this);" disabled></td>
					</tr>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.email"name="geAddresseeConfig.email" value="0" onclick="showRequeriedIterm(this);">�������� &nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.email.value" onclick="changeValue(this);" disabled></td>
						<td width="30">&nbsp;</td>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.consigneeConfig"name="geAddresseeConfig.consigneeConfig" value="0" onclick="showRequeriedIterm(this);">������Ϣ&nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.consigneeConfig.value" onclick="changeValue(this);" disabled></td>
					</tr>
					<tr>
						<td width="130px"><input type=checkbox id="geAddresseeConfig.remark"name="geAddresseeConfig.remark" value="0" onclick="showRequeriedIterm(this);">��ע &nbsp;&nbsp;</td>
						<td width="130px">��� <input type=checkbox id="geAddresseeConfig.remark.value" onclick="changeValue(this);" disabled></td>
						<td width="30">&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</s:if>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >�ر�</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function doCreate(){
	document.getElementById("frmInput").submit();
}
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
</html>
