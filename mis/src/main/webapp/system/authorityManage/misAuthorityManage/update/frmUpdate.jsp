<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<!-- ��ʾ��ʼ -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>�༭Ȩ��</title>
</head>
<body onload="pageValidate();">
<form action="${ctx}/authorityManage/updatesMisAuthority.do" method="post" id="frmInput" target="myFrame">
<div style="padding:10px 0px 0px 5px">
	<table class="table_style" width="400px" align="center">
		<tr>
			<td class="td_head" width="65px"  nowrap>Ȩ�ޱ�ţ�</td>
			<td class="td_body">
				${geAuthority.authorityID}
				<input name="geAuthority.authorityID" value="${geAuthority.authorityID}" type="hidden" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ�����ƣ�</td>
			<td  class="td_body">
				<input name="geAuthority.authorityName" value="${geAuthority.authorityName}" type="text" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>�˵����ͣ�</td>
			<td  class="td_body">
				<select id="isMenu" name="geAuthority.isMenu" onchange="changeMenuType(this.value);">
					<option value="">--��ѡ��--</option>
					<option value="1" ${geAuthority.isMenu=='1'?"selected":""}>�˵�</option>
					<option value="2" ${geAuthority.isMenu=='2'?"selected":""}>�ǲ˵�</option>
				</select>
			</td>
		</tr>
		<tr id="lianType">
			<td class="td_head" nowrap>�������ͣ�</td>
			<td  class="td_body">
				<select id="authorityType" name="geAuthority.authorityType" onchange="showLink()">
					<option value="">--��ѡ��--</option>
					<option value="02" ${geAuthority.authorityType=='02'?"selected":""}>����</option>
					<option value="01" ${geAuthority.authorityType=='01'?"selected":""}>������</option>
				</select>
			</td>
		</tr>
		<tr id="linkTr" style="display:none;">
			<td class="td_head" nowrap>���ӵ�ַ��</td>
			<td  class="td_body">
				<textarea id="authorityLink" name="geAuthority.authorityLink" style="width:300px;height:50px;">${geAuthority.authorityLink}</textarea>
			</td>
		</tr>
		<tr id="openTr" style="display:none;">
			<td class="td_head" nowrap>�򿪷�ʽ��</td>
			<td  class="td_body">
				<select id="opentype" name="geAuthority.opentype">
					<option value="">--��ѡ��--</option>
					<option value="01" ${geAuthority.opentype=='01'?"selected":""}>����</option>
					<option value="02" ${geAuthority.opentype=='02'?"selected":""}>�ǵ���</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ��������</td>
			<td  class="td_body">
				<input name="geAuthority.authorityDesp" value="${geAuthority.authorityDesp}" type="text" style="width:170px;" maxlength=100/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>Ȩ����ţ�</td>
			<td  class="td_body">
				<input name="geAuthority.authorityorder" value="${geAuthority.authorityorder}" type="text" style="width:170px;" maxlength=3/>
			</td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>����ϵͳ��</td>
			<td  class="td_body">
			<select id="opentype" name="geAuthority.systype">
					<c:forEach items="${geCodes}" var="geCode">
						<option value="${geCode.id.codeCode}" ${geCode.id.codeCode eq geAuthority.systype?"selected":""}>${geCode.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>Ȩ�޲㼶��</td>
			<td  class="td_body">
				&nbsp;${geAuthority.authorityLevel}
				<input name="geAuthority.authorityLevel" value="${geAuthority.authorityLevel}" type="hidden" style="width:170px;" maxlength=10/>
			</td>
		</tr>
		<tr height=25><td></td></tr> 
		<tr>
			<td colspan=2>
				<table width=164 align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>�޸� </td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<input name="oldSystype" type="hidden" value="${geAuthority.systype}"/>
<input id="parentid" name="geAuthority.parentID" type="hidden" value="${geAuthority.parentID}"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['authorityType','authorityLink'];
	var contents = ['����˵�ʱ�Ƿ������ӳ���','����˵������ӵ�ַ'];
		
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
changeMenuType('${geAuthority.isMenu}');
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geAuthority.authorityName","geAuthority.authorityType","geAuthority.isMenu");
	tt.vf.num.add("geAuthority.authorityorder");
	
	var authorityType = "${geAuthority.authorityType}";
	if(authorityType == '02'){
		$("#linkTr").show();
		$("#openTr").show();
		tt.vf.req.addName("geAuthority.authorityLink");
		tt.vf.req.addName("geAuthority.opentype");
	}
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
		$("#authorityType").append("<option value=''>--��ѡ��--</option><option ${geAuthority.authorityType=='02'?"selected":""} value='02'>����</option><option ${geAuthority.authorityType=='01'?"selected":""} value='01'>������</option>");
	}
	showLink();
}
</script>
</html>