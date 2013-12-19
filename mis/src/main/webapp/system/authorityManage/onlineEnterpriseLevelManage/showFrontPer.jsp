<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page import = "java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>电子商务后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/global/css/dhtmlxtree.css"/>
<script language="javascript" src="${ctx}/global/js/lib/jquery-1.5.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/dhtmlxtree/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${ctx}/global/js/dhtmlxtree/dhtmlxtree.js"></script>
</head>
<body>
<center>
<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/frontUserManage/updatesAuthority.do">
<div id="authorityTree" style="width:300px;height:400px;"></div>
<div>
<table>
<tr>
<td>
<input type="submit" id="sub" value="确认">
</td>
</tr>
</table>
</div>
<input type=hidden name=authoritys id="authoritys">
<input type=hidden name=userLevel id="userLevel" value="${param['userLevel']}">
</form>
</center>
</body>
<script type="text/javascript">
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx}/global/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.setOnCheckHandler(toncheck); 
tree.loadXML("${ctx}/system/frontUserManage/showFrontPer.do?userLevel=${param['userLevel']}");	
var children=tree.getAllCheckedBranches();
 
function toncheck(id,state){
	var children = tree.getAllCheckedBranches();
	$("#authoritys").val(children);
}

if("${flag}"=="1"){
	alert("操作成功!");
	window.close();
}else if("${flag}"=="0"){
	alert("操作失败!");
	window.closed();
}
</script>
</html>
