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
<title>���������̨����ϵͳ-�༭����</title>
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
			�༭����
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/risk/updateGeRisk.do" id="riskFm" method="post">
	<table class="table_style" align="center" style="width:500px" id="geRiskTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���ִ��룺</td>
		<td class="td_body" width="390px">${geRisk.riskCode}
 		<input type="hidden" id="riskCode" name="geRisk.riskCode" maxlength="4" value="${geRisk.riskCode}" style="width: 160px;"/>	
 		</td>
	</tr>
    <tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="390px" >
            <input id="riskCName" name="geRisk.riskCName" type="text" value="${geRisk.riskCName}" style="width:215px;" maxlength=400"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>Ӣ�����ƣ�</td>
		<td class="td_body" width="390px" >
           <input id="riskEName" name="geRisk.riskEName" type="text" value="${geRisk.riskEName}" style="width:215px;" maxlength=400 />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="390px" >
           <input id="riskTName" name="geRisk.riskTName" type="text" value="${geRisk.riskTName}" style="width:215px;" maxlength=400/>
		</td>
	</tr>
		<tr>
		<td class="td_head" width="120px" nowrap>ҵ������</td>
		<td class="td_body" width="390px">
			<select id="businessArea" name="geRisk.businessArea"  style="width:170px;" >
				<option value="">--��ѡ��--</option>
				<s:iterator value="bussList" var="geCode">
					<option value="<s:property value="#geCode.id.codeCode"/>" 
						<s:if test="geRisk.businessArea==#geCode.id.codeCode">selected</s:if>>
						<s:property value="#geCode.codeCName"/>
					</option>
				</s:iterator>	
             </select>  
		</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>�������</td>
         <td class="td_body" width="390px">
          <select id="riskType" name="geRisk.riskType" style="width:170px;" value="">
				<option value="${geRisk.riskType}" id="formerType">--��ѡ��--</option>
				<option value="01">��ͳ��</option>
				<option value="02">�ֺ�</option>
				<option value="03">Ͷ��</option>
				<option value="04">����</option>
				<option value="05">����</option>
				<option value="13">������</option>
             </select> 
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
					onmouseout="this.className='btnBigOnBlur'" onclick="riskFmSubmit();" nowrap>�޸�  </td>
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
	new tt.LV().set(0,4).add("geRisk.riskCode");
	new tt.LV().set(0,100).add("geRisk.riskCName");
	new tt.LV().set(0,100).add("geRisk.riskEName");
	new tt.LV().set(0,100).add("geRisk.riskTName");
//	new tt.LV().set(0,2).add("riskType");
	tt.vf.num.add("riskCode","riskType");
	tt.vf.req.add("geRisk.riskCode");
	tt.vf.req.add("geRisk.riskCName");
	tt.vf.req.add("geRisk.businessArea");
	tt.vf.req.add("geRisk.riskType");
	var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
	testCName.add("geRisk.riskCName");
	var testEName = new tt.RV().set(new RegExp("^[A-Za-z]+$"), "�Բ���ֻ������Ӣ����ĸ��");
	testEName.add("geRisk.riskEName");
	var testCode = new tt.RV().set(new RegExp("^\\d+$"), "�Բ���ֻ������Ǹ�����");
	testCode.add("geRisk.riskCode");
	testCode.add("geRisk.riskType");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");
	 var riskTypeCode=$("#formerType").val();
	 if(riskTypeCode=="01"){
		 $("#formerType").text("��ͳ��");
	 }else if(riskTypeCode=="02"){
		 $("#formerType").text("�ֺ�"); 
	 }else if(riskTypeCode=="03"){
		 $("#formerType").text("Ͷ��"); 
	 }else if(riskTypeCode=="04"){
		 $("#formerType").text("����");
	 }else if(riskTypeCode=="11"){
		 $("#formerType").text("����");
	 }else if(riskTypeCode=="12"){
		 $("#formerType").text("�Ҳ���");
	 }else if(riskTypeCode=="13"){
		 $("#formerType").text("������");
	 }else{
		 $("#formerType").text("����");
	 }
})
function riskFmSubmit(){
	if(tt.validate()){
	   document.getElementById("riskFm").submit();
	}
}

function doClear(){
	document.getElementById("riskFm").reset();
}

function goToView(){
	window.location.href="${ctx}/risk/viewGeRisk.do?geRisk.riskCode="+'${geRisk.riskCode}';
	
}
</script>
</html>
