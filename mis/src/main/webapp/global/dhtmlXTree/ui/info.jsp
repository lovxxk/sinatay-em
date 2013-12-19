<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ page import = "com.sinosoft.common.*"%>
<%@ page import = "java.util.*"%>
<%
String pageType = Data.filterStr(request.getParameter("pageType"));
String message = Data.filterStr(request.getParameter("message").trim());
String buttonType = Data.filterStr(request.getParameter("buttonType"));
String location = Data.filterStr(request.getParameter("location")==null?"":request.getParameter("location").trim());
String functionName = Data.filterStr(request.getParameter("functionName")==null?"":request.getParameter("functionName").trim());

pageType = new String(pageType.getBytes("ISO8859_1"),"GBK");
message = new String(message.getBytes("ISO8859_1"),"GBK");
buttonType = new String(buttonType.getBytes("ISO8859_1"),"GBK");
location = new String(location.getBytes("ISO8859_1"),"GBK");
functionName = new String(functionName.getBytes("ISO8859_1"),"GBK");

if(message==null) message="";
if(message.equals("noRecord")){
	message="对不起，没有符合条件的记录！";
}else if(message.equals("newSuccess")){
	message="新建成功！";
}else if(message.equals("newFail")){
	message="新建失败！";
}else if(message.equals("updateSuccess")){
	message="更新成功！";
}else if(message.equals("updateFail")){
	message="更新失败！";
}else if(message.equals("deleteSuccess")){
	message="删除成功！";
}else if(message.equals("deleteFail")){
	message="删除失败！";
}else if(message.equals("newProductSuccess")){
	message="新建产品成功！";
}else if(message.equals("newProductFail")){
	message="新建产品失败！";
}else if(message.equals("updateProductSuccess")){
	message="更新产品成功！";
}else if(message.equals("updateProductFail")){
	message="更新产品失败！";
}else if(message.equals("EmailChangeSuccess")){
	message="邮箱修改成功!";
}else if(message.equals("EmailChangeFail")){
	message="邮箱修改失败!";
}else if(message.equals("noColumn")){
	message="对不起，该栏目下没有子栏目!";
}else if(message.equals("markSuccess")){
	message="标记成功!";
}else if(message.equals("markFail")){
	message="标记失败!";
}else if(message.equals("oldPawWrong")){
	message="原始密码输入错误!";
}else if(message.equals("setPawSuccess")){
	message="密码设置成功!";
}else if(message.equals("setPawFail")){
	message="密码设置失败!";
}else if(message.equals("setSuccess")){
	message="设置成功!";
}else if(message.equals("setFail")){
	message="设置失败!";
}else if(message.equals("userAlready")){
	message="此用户已存在，请重新输入！";
}else if(message.equals("dictioinaryValue")){
	message="数据值已存在，请重新输入！";
}else if(message.equals("dictioinaryName")){
	message="数据名已存在，请重新输入！";
}else if(message.equals("addAdSuccess")){
	message="添加到广告组成功！";
}else if(message.equals("addAdFail")){
	message="添加到广告组失败！";
}else if(message.equals("enterSuccess")){
	message="导入成功！";
}else if(message.equals("enterFail")){
	message="导入失败！";
}else if(message.equals("alreadyBinding")){
	message="对不起，此保单号已经被绑定！";
}else if(message.equals("bindingSuccess")){
	message="绑定成功！";
}else if(message.equals("bindingFail")){
	message="绑定失败！";
}else if(message.equals("cancelBindingSuccess")){
	message="解除绑定成功！";
}else if(message.equals("cancelBindingFail")){
	message="解除绑定失败！";
}else if("sendInfoSuccess".equals(message)){
	message = "发送成功";
}else if("sendInfoFail".equals(message)){
	message ="网络异常,请稍候再试!";
}else if(message.equals("cancelBindingStop")){
	message="该客户未绑定任何保单！";
}
if(pageType.equals("") || pageType==null) pageType="info";
if(buttonType==null) buttonType="";
%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script language="javascript">
//alert(window.top.opener.parent.fraSearchForm.doSearch());
function backmain(){
	window.top.location.href="../../../index.jsp";
}
function backAndClear(){
	location.href="../../business/customerManage/enterprise/create/frmCreate.jsp";
}
//解决查询，创建，更新后数据自动刷新问题
<%
try{
	if("".equals(location)||"".equals(functionName)){			
	
	}else{
		out.println("var str ="+location+"."+functionName+"();");
		out.println("eval(str);");
	}
}catch(Exception e){
	out.println("alert(\"info.getFunction error!!\");");
}
%>
</script>
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
							<td align=right><img src="../images/ui/<%=pageType%>.gif"></td>
							<td colspan=2><%=message%></td>
						</tr>
						<tr>
							<td align=right colspan=3>
								<%
								StringTokenizer st = new StringTokenizer(buttonType,",");
								while (st.hasMoreTokens()){
								buttonType = st.nextToken();
									if(buttonType.trim().equals("back")){
									%>
									<input type="button" value="返回" onclick="window.history.back();">
									<%}if(buttonType.trim().equals("backmain")){%>
									<input type="button" value="返回首页" onclick="javascript:backmain();">
									<%}if(buttonType.trim().equals("close")){%>
									<input type="button" value="关闭" onclick="window.top.close();">
									<%}if(buttonType.trim().equals("clear")){%>
									<input type="button" value="返回" onclick="javascipt:backAndClear();">
									<%
									}
								}
								%>
							</td>
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