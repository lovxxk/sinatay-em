<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<!-- 提示框开始 -->
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>新建前台用户权限</title>
</head>
<body onload="pageValidate();">
<form action="${ctx}/authorityManage/createOnlineUserAuthority.do" method="post" id="frmInput" target="myFrame">
<div style="padding:10px 0px 0px 5px;">
	<table border=0 style="width:400px;font-size:9pt;" cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px" nowrap>权限编号：</td>
			<td class="td_body" nowrap>
				<input id="authorityID" name="geUserAuthority.authorityID" type="text" value="ROLE_" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限名称：</td>
			<td  class="td_body" nowrap>
				<input name="geUserAuthority.authorityName" type="text" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>链接类型：</td>
			<td  class="td_body" nowrap>
				<select id="authorityType" name="geUserAuthority.authorityType" onchange="showLink()">
					<option value="">--请选择--</option>
					<option value="02">链接</option>
					<option value="01">非链接</option>
				</select>
			</td>
		</tr>
		<tr id="linkTr" style="display:none;">
			<td class="td_head" nowrap>链接地址：</td>
			<td  class="td_body" nowrap>
				<textarea id="authorityLink" name="geUserAuthority.authorityLink" style="width:300px;height:50px;"></textarea>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限描述：</td>
			<td  class="td_body" nowrap>
				<input name="geUserAuthority.authorityDesp" type="text" style="width:170px;" maxlength=100/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限序号：</td>
			<td  class="td_body" nowrap>
				<input name="geUserAuthority.authorityorder" type="text" style="width:170px;" maxlength=10/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>权限层级：</td>
			<td  class="td_body" nowrap>
				<input name="geUserAuthority.authorityLevel" value="${geUserAuthority.authorityLevel}" style="width:170px;" maxlength=10/>
			</td>
		</tr>
		<tr height="60px">
			<td colspan=2>
				<table width=164 align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<input id="parentid" name="geUserAuthority.parentID" type="hidden" value="${geUserAuthority.parentID}"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//pop提示信息
	var ids = ['authorityID','isMenu','authorityType','authorityLink','opentype'];
	var contents = ['唯一标识不能重复','该权限是菜单还是非菜单','点击菜单时是否有链接出现','点击菜单所链接地址','点击该菜单是弹出形式还是非弹出形式'];
		
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
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
//pop提示信息结束
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
	tt.vf.req.add("geUserAuthority.authorityID","geUserAuthority.authorityName","geUserAuthority.authorityType");
	tt.vf.num.add("geAuthority.authorityorder");
	new tt.RV().set(new RegExp("^ROLE_.+$"), "必须以ROLE_开头").add("geUserAuthority.authorityID"); 
}
function showLink(){
	var authorityType = $("#authorityType").val();
	if(authorityType == "02"){
		$("#linkTr").show();
		tt.vf.req.addName("geUserAuthority.authorityLink");
	}else{
		$("#linkTr").hide();
		tt.vf.req.rmName("geUserAuthority.authorityLink");
	}
}
</script>
</html>