<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>�½�Ȩ��</title>
</head>
<body onload="pageValidate();">
<form action="${ctx}/authorityManage/createMisAuthority.do" method="post" id="frmInput" target="myFrame">
<div style="padding:10px 0px 0px 5px;">
<table border=0 style="width:400px;font-size:9pt;" align="center">
	<tr>
		<td class="td_head" width="65px" nowrap>Ȩ�ޱ�ţ�</td>
		<td class="td_body">
			<input id="authorityID" name="geAuthority.authorityID" value="ROLE_" type="text" style="width:170px;" maxlength=50>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>Ȩ�����ƣ�</td>
		<td  class="td_body">
			<input name="geAuthority.authorityName" type="text" style="width:170px;" maxlength=50>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�˵����ͣ�</td>
		<td  class="td_body">
			<select id="isMenu" name="geAuthority.isMenu" onchange="changeMenuType(this.value);">
				<option value="">--��ѡ��--</option>
				<option value="1">�˵�</option>
				<option value="2">�ǲ˵�</option>
			</select>
		</td>
	</tr>
	<tr id="lianType">
		<td class="td_head" nowrap>�������ͣ�</td>
		<td  class="td_body">
			<select id="authorityType" name="geAuthority.authorityType" onchange="showLink()">
				<option value="">--��ѡ��--</option>
				<option value="02">����</option>
				<option value="01">������</option>
			</select>
		</td>
	</tr>
	<tr id="linkTr" style="display:none">
		<td class="td_head" nowrap>���ӵ�ַ��</td>
		<td  class="td_body">
			<textarea id="authorityLink" name="geAuthority.authorityLink" style="width:300px;height:50px;"></textarea>
		</td>
	</tr>
	<tr id="openTr" style="display:none">
		<td class="td_head" nowrap>�򿪷�ʽ��</td>
		<td  class="td_body">
			<select id="opentype" name="geAuthority.opentype">
				<option value="">--��ѡ��--</option>
				<option value="01">����</option>
				<option value="02">�ǵ���</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>Ȩ��������</td>
		<td  class="td_body">
			<input name="geAuthority.authorityDesp" type="text" style="width:170px;" maxlength=100/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>Ȩ����ţ�</td>
		<td  class="td_body">
			<input name="geAuthority.authorityorder" type="text" style="width:170px;" maxlength=3/>
		</td>
	</tr>
	
	<tr style="display:none;">
		<td class="td_head" nowrap>Ȩ�޲㼶��</td>
		<td  class="td_body">
			&nbsp;${geAuthority.authorityLevel}
			<input name="geAuthority.authorityLevel" value="${geAuthority.authorityLevel}" type="text" style="width:170px;" maxlength=10/>
		</td>
	</tr>
	<tr height="60px">
		<td colspan=2>
			<table width=164 align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
				<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
			</tr>
			</table>
		</td>
	</tr>
</table>
</div>
<input id="parentid" name="geAuthority.parentID" type="hidden" value="${geAuthority.parentID}"/>
<input id="systype" name="geAuthority.systype" type="hidden" value="${geAuthority.systype}"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['authorityID','isMenu','authorityType','authorityLink','opentype'];
	var contents = ['Ψһ��ʶ�����ظ�','��Ȩ���ǲ˵����Ƿǲ˵�','����˵�ʱ�Ƿ������ӳ���','����˵������ӵ�ַ','����ò˵��ǵ�����ʽ���Ƿǵ�����ʽ'];
		
    	for ( var i = 0 ; i < 5 ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
					tooltip: 'leftMiddle', 
					target: 'rightMiddle'
					} 
				}, 
				 style: { 
				border: { 
					width: 2,
					radius: 2,
					color: '#00739f'
					},
					width:100,
					padding: 10, 
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
    	}
//pop��ʾ��Ϣ����
});
function doCreate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geAuthority.authorityID","geAuthority.authorityName","geAuthority.isMenu","geAuthority.authorityType");
	tt.vf.num.add("geAuthority.authorityorder");
	new tt.RV().set(new RegExp("^ROLE_.+$"), "������ROLE_��ͷ").add("geAuthority.authorityID"); 
}

function showLink(){
	var authorityType = $("#authorityType").val();
	if(authorityType == "02"){
		$("#linkTr").show();
		$("#openTr").show();
		tt.vf.req.addName("geAuthority.authorityLink");
		tt.vf.req.addName("geAuthority.opentype");
	}else{
		$("#linkTr").hide();
		$("#openTr").hide();
		$("#authorityLink").val("");
		tt.vf.req.rmName("geAuthority.authorityLink");
		tt.vf.req.rmName("geAuthority.opentype");
	}
}
function changeMenuType(type){
	if(type=="2"){
		$("#authorityType").val("01");
		$("#authorityType").empty();
		$("#authorityType").append("<option value='01'>������</option>");
	}
	if(type=="1"){
		$("#authorityType").val("");
		$("#authorityType").empty();
		$("#authorityType").append("<option value=''>--��ѡ��--</option><option value='02'>����</option><option value='01'>������</option>");
	}
	showLink();
}
</script>
</html>