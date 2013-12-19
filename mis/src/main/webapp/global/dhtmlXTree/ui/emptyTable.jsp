<%@ page language="java" contentType="text/html;charset=GBK" %>
<%
//String alert = Data.filterStr(request.getParameter("alert")==null?"":request.getParameter("alert").trim()); 
//String pageSize = Data.filterStr(request.getParameter("pageSize")==null?"":request.getParameter("pageSize").trim()); 

//if(alert==null) alert="";
String alert="";
int ps = 20;
//if(!(pageSize.equals(""))) ps = Integer.parseInt(pageSize);

%>
<HTML>
<HEAD>
<META NAME="GENERATOR" Content="Microsoft Visual Studio 6.0">
<link href="../css/table.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY topmargin=5 leftmargin=2 bgcolor=WHITE>
<table width="100%" cellpadding=0 cellspacing=0 border=0 class="table_Show">
<tr>
<%for (int i=1;i<=8;i++){%>
    <td class="head">&nbsp;</td>  
<%}%>
</tr>
<tr>
	<td height=4 colspan="1<%//=i%>"></td>
</tr>
<%for (int j=1;j<=ps;j++){%>
<tr>
	<%for (int i=1;i<=8;i++){%>
    <td class="TableGrid">&nbsp;</td>
	<%}%>
</tr>
<%}%>
</table>
</BODY>
</HTML>
<%if (!alert.equals("")){%>
<script language="javascript">
	alert("<%=alert%>");
</script>
<%}%>
