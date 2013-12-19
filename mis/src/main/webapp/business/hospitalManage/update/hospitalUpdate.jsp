<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�༭ҽԺ</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭ҽԺ
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/hospital/updateHospital.do" id="hospitalFm" method="post">
	<table class="table_style" align="center" style="width:500px" id="hospitalTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>ʡ��</td>
		<td class="td_body" width="390px">
		<input type="hidden" id="serialNo" name="hospital.serialNo" maxlength="32" value="${hospital.serialNo}" style="width: 160px;"/>
		${hospital.province}	
 		<input id="province" name="hospital.province" maxlength="32" value="${hospital.province}" style="display:none;width: 160px;"/>	
 		</td>
	</tr>
    <tr>
		<td class="td_head" width="120px" nowrap>�У�</td>
		<td class="td_body" width="390px" >
			${hospital.city}
            <input id="city" name="hospital.city" type="text" value="${hospital.city}" style="display:none;width:215px;""/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>ҽԺ���ƣ�</td>
		<td class="td_body" width="390px" >
           <input id="hosName" name="hospital.hosName" type="text" value="${hospital.hosName}" style="width:215px;" maxlength=100 />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>ҽԺ��ַ��</td>
		<td class="td_body" width="390px" >
           <input id="hosAddr" name="hospital.hosAddr" type="text" value="${hospital.hosAddr}" style="width:215px;" maxlength=200/>
		</td>
	</tr>
	<tr height=35><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
			<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="goToView();" nowrap>����  </td>
              
				<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="hospitalFmSubmit();" nowrap>�޸�  </td>
                <td id="updateButton" align=right class="btnBigOnBlur" onclick="javascript:doClear();" nowrap>����</td>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	new tt.LV().set(0,100).add("hospital.hosName");
	new tt.LV().set(0,200).add("hospital.hosAddr");
//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
	tt.vf.req.add("hospital.hosName");
	tt.vf.req.add("hospital.hosAddr");
	var testProvince = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
	testProvince.add("hospital.province");
	var testCity = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
	testCity.add("hospital.city");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");
})
function hospitalFmSubmit(){
	if(tt.validate()){
	   document.getElementById("hospitalFm").submit();
	}
}

function doClear(){
	document.getElementById("hospitalFm").reset();
}

function goToView(){
	window.location.href="${ctx}/hospital/viewHospital.do?hospital.serialNo="+'${hospital.serialNo}';
	
}
</script>
</html>
