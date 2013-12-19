<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>�½��û���</title>
</head>
<body>
<div class="public_fun_title">�½���������</div>
<form action="${ctx}/system/deptInfoManage/createDeptInfo.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="480px" id="productTable">
<tr>
	<td class="td_head" width="120px" style="height:30px;" nowrap>���Ա��룺</td>
	<td class="td_body" width="530px" >
		<input id="attrID" name="geDeptInfo.attrID" type="text" style="width:170px;" class=required maxlength=20>
		<span id="attrID_msg"></span>
	</td>
</tr>
<tr>
	<td class="td_head" style="height:30px;" nowrap>�������ƣ�</td>
	<td class="td_body" >
		<input id="attrName" name="geDeptInfo.attrName" type="text" style="width:170px;" maxlength=15>
		<span id="attrName_msg"></span>
	</td>
</tr>
<tr>
	<td class="td_head"  nowrap>����������</td>
	<td class="td_body" >
		<textarea  id="attrDescription" name="geDeptInfo.attrDescription" rows="5" cols="30"  maxlength="100" onkeyup="textAreaMaxLen(this);"></textarea>
	</td>
</tr>
<tr height=25><td></td></tr> 
<tr>
	<td colspan=2>
		<table width=164 align="center">
		<tr>
			<td id="createButton" align=right  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">

function doCreate(){
	if(tt.validate()){		
		$("#frmInput").submit();
	}	
}

function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
<script type="text/javascript">
	//������ʾ����
	var attrID =new tt.Field("","","attrID").setMsgId("attrID_msg");
	var attrName =new tt.Field("","","attrName").setMsgId("attrName_msg");
	tt.vf.req.addId("attrDescription");
	//�ǿ���֤
	tt.vf.req.add(attrID,attrName);
	//new tt.LV().set(0,250).add("geDeptInfo.attrDescription");
	//��Բ�ͬ�����������ʽ��֤
	new tt.RV().set(new RegExp(/^[A-Za-z0-9]+$/),"ֻ���������ֺ�Ӣ����ĸ!").add(attrID);
	
	$("#attrID").blur(function(){
		var attrID = $("#attrID").val();
		$.ajax({
			url : "${ctx}/system/deptInfoManage/findDeptInfoById.do",
			data : {
				"geDeptInfo.attrID" : attrID
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
				alert("ϵͳ�����쳣,���Ժ����!");
			},
			success : function(data) {
				if (data == "1") {
					$("#attrID").val("");
					$("#attrID_msg").html("");
					$("#attrID").after("<span id='attrID_msg'><font color='red' style='padding-left:2mm;'>���Ա����Ѵ���,�����!</font></span>")
				} else {
					$("#attrID_msg").html("");
				}
			}
		});
	});
	
	 function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("�������100����");
		    	return false ;
		    }
	}
</script>
</html>