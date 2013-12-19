<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String message = request.getParameter("message");
if(message==null) {
	message="";
} else {
message = message.trim();
}
if(message.equals("noColumn")){
	message="对不起，该栏目下没有子栏目!";
}
if(message.equals("noRecord")){
	message="对不起，没有符合条件的记录！";
}
if(message.equals("createTempletSuccess")){
	message="新建样式成功！";
}
if(message.equals("bindTempletSuccess")){
	message="绑定样式成功！";
}
if(message.equals("updateTempletSuccess")){
	message="修改样式成功！";
}
%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
table{
	font-size:9pt;
}
</style>
<TITLE>信息</TITLE>
</HEAD>
<BODY>
<table width=100% height=100%>
<tr>
	<td align=center valign=middle>
		<table cellpadding=0 cellspacing=0 border=0 width="70%" height="70%" border=0>
			<tr>
				<td align="left" valign="top" height=40 width=5><img src="../images/ui/gol_top_left.jpg"></td>
				<td align="left" valign="middle" height=40 width="100%" style="color:white;font-weight:bold;padding-left:10px;background-image:url(../images/ui/gol_top_bg.jpg);background-repeat:repeat-x">
					系统信息</td>
				<td align="left" valign="top" height=40 width=5><img src="../images/ui/gol_top_right.jpg"></td>
			</tr>
			<tr>
				<td align="left" valign="top" style="background-image:url(../images/ui/gol_body_left.jpg);background-repeat:repeat-x;"><img src="../images/ui/gol_body_left.jpg"></td>
				<td align="left" valign="top" style="background-image:url(../images/ui/gol_body_bg.jpg);background-repeat:repeat-x;">
					<table border=0 cellpadding=20>
						<tr>
							<td height=40 colspan=2></td>
						</tr>
						<tr>
							<td width=170 align=right><img src="../images/ui/info.gif"></td>
							<td><%=message%></td><td></td>
						</tr>
						
					</table>
				</td>
				<td align="left" valign="top" width=5 style="background-image:url(../images/ui/gol_body_right.jpg);background-repeat:repeat-x;"></td>
			</tr>							
		</table>
	</td>
</tr>
</table>
</BODY>
</HTML>
