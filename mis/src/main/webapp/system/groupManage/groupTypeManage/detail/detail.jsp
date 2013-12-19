<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-用户组类型详细</title>
<style type="text/css">
	.td_head{text-align:left;}
	.td_body{text-align:left;}
</style>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			用户组类型详细
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:10px">&nbsp;</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="" id="frmInput" method="post" target="myFrame">
	<table class="table_style" width="500px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120" style="padding-left: 8px;font-size: 13px;">用户组类型名称：</td>
		<td class="td_body" width="380">
			${geGrouptype.grouptypename}</td>
	</tr>
	<tr>
		<td height=5 colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header" >
				用户组类型描述：
			</div>
			<div style="padding-left:3px;height: 145px;overflow-y:scroll;width: 500px;">
				<textarea class="textarea_disabled" style="height:75px;"disabled>${geGrouptype.grouptypedesc}</textarea>
			</div>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=85 align="center">
			<tr>
				<acc:showView source="ROLE_GROUPTYPE_M_U">
					<td id="editButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
				</acc:showView>
				<acc:showView source="ROLE_GROUPTYPE_M_D">
					<td id="deleteButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
				</acc:showView>
				<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" name="id" value="${misUser.id}">
	<input type="hidden"  name="geGrouptype.grouptypedeptid" value="${geGrouptype.grouptypedeptid}" >
	<input type="hidden" name="geGrouptype.grouptypeid" value="${geGrouptype.grouptypeid}" >
	</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function doEdit(){
	window.location.href = "${ctx}/system/groupManage/queryGeGroupTypeForUpdate.do?id=${geGrouptype.grouptypeid}";
}
function doDelete(){
	if(confirm("确定删除此用户组类型吗？")){
		$("#frmInput")[0].action = "${ctx}/system/groupManage/deleteType.do?typeid=${geGrouptype.grouptypeid}";
		$("#frmInput")[0].submit();
	}
}
</script>
<script type="text/javascript">
	//设置显示区域
	var groupTypeName =new tt.Field("用户组类型名称","","groupTypeName").setMsgId("groupTypeName_msg");
	//非空验证
	tt.vf.req.add(groupTypeName);
</script>
</html>
