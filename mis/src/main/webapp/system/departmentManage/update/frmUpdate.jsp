<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>编辑机构</title>
</head>
<body onload="pageValidate();">
<form action="${ctx}/departmentManage/updatesGeDepartment.do" method="post" id="frmInput" target="myFrame" >
<div style="padding:10px 0px 0px 5px;">		
	<table border=0 style="font-size:9pt;width:400px"  cellpadding=0 cellspacing=0 align="center">
		<tr>
			<td class="td_head" width="65px" nowrap>机构编号：</td>
			<td class="td_body" nowrap>
				${geDepartment.deptid}
				<input type="hidden" name="geDepartment.deptid" type="text" value="${geDepartment.deptid}" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>机构名称：</td>
			<td  class="td_body" nowrap>
				<input id="deptname" name="geDepartment.deptname" value="${geDepartment.deptname}" type="text" style="width:170px;" maxlength=50>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>业务领域：</td>
			<td  class="td_body" nowrap>
				${depAreaName}
				<input id="businessarea" name="geDepartment.businessarea" type="hidden" value="${geDepartment.businessarea}"/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>机构描述：</td>
			<td  class="td_body" nowrap>
				${depTypeName}
				<input id="depttype" name="geDepartment.depttype" type="hidden" value="${geDepartment.depttype}"/>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>机构等级：</td>
			<td  class="td_body" nowrap>
				${geDepartment.deptlevel}
				<input id="deptlevel" name="geDepartment.deptlevel" type="hidden" value="${geDepartment.deptlevel}"/>
			</td>
		</tr>
		<tr style="height:200px;">
			<td colspan="2" class="frmCreate_kuang" valign="top" nowrap>
				<div class="frmCreate_kuang_header">所属区域
					<span id="comChecked" style="color:#FF9000;font-weight:bold;">
						<c:if test="${not empty geDepartment.gname}">
						(选中区域:${geDepartment.gname})
						</c:if>
						<c:if test="${empty geDepartment.gname}">
						(还未选择任何区域)
		</c:if>
					</span>
				</div>
				<div style="padding-left:3px;">
					<iframe id="mainFrame" src="${ctx}/system/departmentManage/update/areaManageTree.jsp?area=${area}&gname=${geDepartment.gname}&deptid=${geDepartment.deptid}" frameborder="0" style="width:100%;height:175px;overflow-x:hidden;" scrolling="auto"></iframe>
				</div>
			</td>
		</tr>
		<tr height="60px">
			<td colspan=2>
				<table width="164px" align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改 </td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<input id="parentid" name="geDepartment.parentid" type="hidden" value="${geDepartment.parentid}"/>
<input id="areaid" name="geDepartment.gid" type="hidden" value="${geDepartment.gid }"/>
<input id="areaname" name="geDepartment.gname" type="hidden" value="${geDepartment.gname }"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function openTree(){
	$("#openTree_button").hide();
	$("#hiddenDiv").hide();
	$("#mainFrame").show();
}
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.add("geDepartment.deptname");
}
</script>
</html>