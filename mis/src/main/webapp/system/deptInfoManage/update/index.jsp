<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�༭����������Ϣ</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭��������
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/deptInfoManage/updateDeptInfo.do" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="680px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="150px" nowrap>���Ա��룺</td>
		<td class="td_body" width="530px" >
			${geDeptInfo.attrID}
			<input type="hidden" id="groupId" name="geDeptInfo.attrID" style="width:170px;" value="${geDeptInfo.attrID}" maxlength=20 readonly>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�������ƣ�</td>
		<td class="td_body" >
			<input id="groupName" name="geDeptInfo.attrName" type="text" value="${geDeptInfo.attrName}" style="width:170px;" maxlength=15>
			<span id="groupName_msg"></span>
	</tr>
	
	<tr>
		<td class="td_head" nowrap>����������</td>
		<td class="td_body" >
			<textarea  id="groupDesp" name="geDeptInfo.attrDescription"  rows="5" cols="30" onkeyup="textAreaMaxLen(this);"  maxlength="100" >${geDeptInfo.attrDescription}</textarea>
			<span  id="groupDesp_msg"></span>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table style="margin-left:160px">
			<tr>
			   <td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>����</td>
				<td id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>�޸�</td>
					<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doReset();" nowrap>����</td>
			<td id="closeButton" align=right class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
               onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function doUpdate(){
	if(tt.validate()){	
	document.getElementById("frmInput").submit();
	}
}
function doBack(){
	window.location.href="${ctx}/system/deptInfoManage/detail.do?geDeptInfo.attrID=${geDeptInfo.attrID}";
}
</script>

<script type="text/javascript">
	//������ʾ����
	var groupName =new tt.Field("","","groupName").setMsgId("groupName_msg");
	// var groupDesp =new tt.Field("","","groupDesp").setMsgId("groupDesp_msg");
	var groupTypeId =new tt.Field("","","groupTypeId").setMsgId("groupTypeId_msg");
	//�ǿ���֤
	tt.vf.req.add(groupName,groupTypeId);
	tt.vf.req.addId("groupDesp");
	//��Բ�ͬ�����������ʽ��֤
	// new tt.RV().set(new RegExp(/^([\u4E00-\u9FA5]|\w){0,100}$/),"���ܶ���100����!").add(groupDesp);
 function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("�������100����");
		    	return false ;
		    }
	}
 function doReset(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>
