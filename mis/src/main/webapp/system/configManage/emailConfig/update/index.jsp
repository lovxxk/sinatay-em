<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<title>���������̨����ϵͳ-�༭�����������ù���</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭������������
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/configManage/emailConfig/update.do" id="deptMailfm" method="post">
	<table class="table_style" align="center" width="450px" id="geDeptMailTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="330px">
			<input id="deptMailID" name="geDeptMail.deptMailID" type="hidden" value="${geDeptMail.deptMailID}"/>
            <input id="deptID" name="geDeptMail.deptID" type="hidden" value="${geDeptMail.deptID}"/>
			${geDeptMail.departNmae}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���ù��ܣ�</td>
		<td class="td_body" width="330px" >
            <input type="hidden" name="geDeptMail.functionID" value="${geDeptMail.functionID}"/>
            <input type="hidden" name="geDeptMail.createTime" value="<s:property value="geDeptMail.createTime"/>">
            ${geDeptMail.sendMailName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���䣺</td>
		<td class="td_body" width="330px">
			<input id="mail" name="geDeptMail.mail" onkeyup="tt2hide();" onclick="checkMobile();" type="text" style="width:150px;" value="${geDeptMail.mail}" maxlength=50 >
		</td>
	</tr> 
	<tr>
		<td class="td_head" width="120px" nowrap>�ֻ���</td>
		<td class="td_body" width="330px">
			<input id="mobile" name="geDeptMail.mobile" onkeyup="tt2hide();" onclick="checkMobile();" type="text" style="width:150px;" value="${geDeptMail.mobile}" maxlength=11 >
			<span id="tt2" style="display:none" class="talentErrMsg" style="top: 70px; left: 475px;">���������绰������ѡһ��!</span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>��Ч��־��</td>
		<td class="td_body" width="330px">
		   <select id="validInd" name="geDeptMail.validInd" style="width:150px;">
			<option value="1" 
			<c:if test="${geDeptMail.validInd == '1'}">selected</c:if>
			>��Ч</option>
			<option value="0" 
			<c:if test="${geDeptMail.validInd == '0'}">selected</c:if>
			}>��Ч</option>
		</select>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
			 <td id="returnButton" align=right class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
               onmouseout="this.className='btnBigOnBlur'"
                     onclick="javascript:location.href ='${ctx}/system/configManage/emailConfig/viewDeptMail.do?geDeptMail.deptMailID=${geDeptMail.deptMailID}'" nowrap>����</td>
                <acc:showView source="ROLE_S_ECON_U">
      				<td id="updateButton" align=right class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
               onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSubmit();" nowrap>�޸�</td>
                  <td id="resetButton" align=right class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
               onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doReset();" nowrap>����</td>
                </acc:showView>
				<td id="closeButton" align=right class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
               onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
tt.vf.req.add("geDeptMail.validInd");
tt.vf.email.add("geDeptMail.mail"); 
tt.vf.num.add("geDeptMail.mobile"); 
new tt.RV().set(new RegExp("^1[0-9]{10}$"),  
"�ֻ���ʽ����ȷ��").add("geDeptMail.mobile"); 
function doSubmit(){
	if(tt.validate()){
		if(checkMobile()){
	 	  document.getElementById("deptMailfm").submit();
		}
	}
}
function doReset(){
	document.getElementById("deptMailfm").reset();
}
function checkMobile(){
	if($("#mail").val()==""&&$("#mobile").val()==""){
		//alert("���������绰������ѡһ��");
		$("#tt2").show(); 
		return false;
	}else{
		return true;
	}
}
function tt2hide(){
	if($("#mail").val()!=""||$("#mobile").val()!=""){
		$("#tt2").hide();
	}
}
</script>
</body>
</html>
