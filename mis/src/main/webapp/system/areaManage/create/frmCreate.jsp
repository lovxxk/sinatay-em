<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>新建机构</title>
</head>
<body onload="pageValidate();">
<form action="${ctx}/areaAction/createArea.do" method="post" id="frmInput" target="myFrame">
<div style="padding-top:10px;clear:both;">
	<table border=0 style="font-size:9pt;width:400px"  cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px" nowrap>区域编号：</td>
			<td class="td_body" width="100%" nowrap>
				<input id="dep_id" name="geAreaAuthority.gid"  type="text">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>区域名称：</td>
			<td  class="td_body" nowrap>
				<input id="deptname" name="geAreaAuthority.gname" type="text">
			</td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>区域类型：</td>
			<td  class="td_body" nowrap>
				<input id="businessarea" name="geAreaAuthority.type" type="text"/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>备注信息：</td>
			<td  class="td_body" nowrap>
				<input id="deptlevel" name="geAreaAuthority.note" type="text"/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>区域等级：</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.glevel+1}
				<input id="depttype" name="geAreaAuthority.glevel" type="hidden" value="${geAreaAuthority.glevel+1}"/>
			</td>
		</tr>
		<tr style="display:none;">
			<td class="td_head" nowrap>区域父级：</td>
			<td  class="td_body" nowrap>
				${geAreaAuthority.gid}
				<input id="depttype" name="geAreaAuthority.pgid" type="hidden" value="${geAreaAuthority.gid}"/>
			</td>
		</tr>
		<tr height="60px">
			<td colspan=2>
				<table width=164 align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doCreate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geAreaAuthority.gid","geAreaAuthority.gname");
	tt.vf.num.add("geDepartment.deptid");
}
</script>
</html>