<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-更新接口</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="public_fun_title">
	更新接口
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/changeInterface.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="changeInterface();" nowrap>更新接口</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script type="text/javascript">
function changeInterface(){
	document.getElementById("frmInput").submit();
}
$(function (){
	var flag = "${flag}";
	if (flag == "1") {
		alert("更新接口成功！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/changeInterface/changeInterface.jsp";
	} else if (flag == "0") {
		alert("更新接口失败！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/changeInterface/changeInterface.jsp";
	}
});
</script>
</body>
</html>