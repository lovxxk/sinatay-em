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
	message="�Բ���û�з��������ļ�¼��";
}else if(message.equals("newSuccess")){
	message="�½��ɹ���";
}else if(message.equals("newFail")){
	message="�½�ʧ�ܣ�";
}else if(message.equals("updateSuccess")){
	message="���³ɹ���";
}else if(message.equals("updateFail")){
	message="����ʧ�ܣ�";
}else if(message.equals("deleteSuccess")){
	message="ɾ���ɹ���";
}else if(message.equals("deleteFail")){
	message="ɾ��ʧ�ܣ�";
}else if(message.equals("newProductSuccess")){
	message="�½���Ʒ�ɹ���";
}else if(message.equals("newProductFail")){
	message="�½���Ʒʧ�ܣ�";
}else if(message.equals("updateProductSuccess")){
	message="���²�Ʒ�ɹ���";
}else if(message.equals("updateProductFail")){
	message="���²�Ʒʧ�ܣ�";
}else if(message.equals("EmailChangeSuccess")){
	message="�����޸ĳɹ�!";
}else if(message.equals("EmailChangeFail")){
	message="�����޸�ʧ��!";
}else if(message.equals("noColumn")){
	message="�Բ��𣬸���Ŀ��û������Ŀ!";
}else if(message.equals("markSuccess")){
	message="��ǳɹ�!";
}else if(message.equals("markFail")){
	message="���ʧ��!";
}else if(message.equals("oldPawWrong")){
	message="ԭʼ�����������!";
}else if(message.equals("setPawSuccess")){
	message="�������óɹ�!";
}else if(message.equals("setPawFail")){
	message="��������ʧ��!";
}else if(message.equals("setSuccess")){
	message="���óɹ�!";
}else if(message.equals("setFail")){
	message="����ʧ��!";
}else if(message.equals("userAlready")){
	message="���û��Ѵ��ڣ����������룡";
}else if(message.equals("dictioinaryValue")){
	message="����ֵ�Ѵ��ڣ����������룡";
}else if(message.equals("dictioinaryName")){
	message="�������Ѵ��ڣ����������룡";
}else if(message.equals("addAdSuccess")){
	message="��ӵ������ɹ���";
}else if(message.equals("addAdFail")){
	message="��ӵ������ʧ�ܣ�";
}else if(message.equals("enterSuccess")){
	message="����ɹ���";
}else if(message.equals("enterFail")){
	message="����ʧ�ܣ�";
}else if(message.equals("alreadyBinding")){
	message="�Բ��𣬴˱������Ѿ����󶨣�";
}else if(message.equals("bindingSuccess")){
	message="�󶨳ɹ���";
}else if(message.equals("bindingFail")){
	message="��ʧ�ܣ�";
}else if(message.equals("cancelBindingSuccess")){
	message="����󶨳ɹ���";
}else if(message.equals("cancelBindingFail")){
	message="�����ʧ�ܣ�";
}else if("sendInfoSuccess".equals(message)){
	message = "���ͳɹ�";
}else if("sendInfoFail".equals(message)){
	message ="�����쳣,���Ժ�����!";
}else if(message.equals("cancelBindingStop")){
	message="�ÿͻ�δ���κα�����";
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
//�����ѯ�����������º������Զ�ˢ������
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
<TITLE>��Ϣ</TITLE>
</HEAD>
<BODY>
<table width=100% height=100%>
<tr>
	<td align=center valign=middle>
		<table cellpadding=0 cellspacing=0 border=0 width="70%" height="70%" border=0>
			<tr>
				<td align="left" valign="top" height=40 width=5><img src="../images/ui/gol_top_left.jpg"></td>
				<td align="left" valign="middle" height=40 width="100%" style="color:white;font-weight:bold;padding-left:10px;background-image:url(../images/ui/gol_top_bg.jpg);background-repeat:repeat-x">
					ϵͳ��Ϣ</td>
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
									<input type="button" value="����" onclick="window.history.back();">
									<%}if(buttonType.trim().equals("backmain")){%>
									<input type="button" value="������ҳ" onclick="javascript:backmain();">
									<%}if(buttonType.trim().equals("close")){%>
									<input type="button" value="�ر�" onclick="window.top.close();">
									<%}if(buttonType.trim().equals("clear")){%>
									<input type="button" value="����" onclick="javascipt:backAndClear();">
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