<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-编辑用户组类型信息</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑用户组类型
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:10px">&nbsp;</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/groupManage/updateType.do" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="680px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>用户组类型名称：</td>
		<td class="td_body" >
			<input id="groupTypeName" name="geGrouptype.grouptypename" type="text" value="${geGrouptype.grouptypename}" style="width:170px;" maxlength=20>
			<span id="groupTypeName_msg"></span>
	</tr>
	<tr>
		<td class="td_head"  nowrap>用户组类型描述：</td>
		<td class="td_body" >
			<textarea  id="grouptypeDesc" name="geGrouptype.grouptypedesc"  style="width:400px;" rows="5" >${geGrouptype.grouptypedesc}</textarea>
		</td>
	</tr>
	<tr><td>&nbsp;</td><td><span id="grouptypeDesc_msg"></span></td></tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=85 align="center">
			<tr>
			<td id="backButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
				<td id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
				<td id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>	
				
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
function doUpdate(){
	if(tt.validate()){	
	document.getElementById("frmInput").submit();
	}
}
function doClear(){
	$("#frmInput")[0].reset();
}

function doBack(){
	window.location.href="${ctx}/system/groupManage/queryTypeDetail.do?id=${geGrouptype.grouptypeid}";
}
</script>
<script type="text/javascript">
	//设置显示区域
	var groupTypeName =new tt.Field("","","groupTypeName").setMsgId("groupTypeName_msg");
	var grouptypeDesc =new tt.Field("","","grouptypeDesc").setMsgId("grouptypeDesc_msg");
	//非空验证
	tt.vf.req.add(groupTypeName);
	new tt.LV().set(0,100).add(grouptypeDesc);
</script>
</html>
