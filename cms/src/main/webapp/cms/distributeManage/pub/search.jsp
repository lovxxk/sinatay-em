<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsDocument" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %> 
<%
String nodeID = request.getParameter("nodeID");
List docList = (List)request.getAttribute("docList") == null ? new ArrayList() : (List)request.getAttribute("docList");
System.out.print(nodeID);
CmsDocument document = new CmsDocument();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/table.css">
<title>search.jsp</title>
</head>
<body leftmargin="0" topmargin="0">
<form id="frmLIST" method="get">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table_Show">
	<tr>
		<td class="td_head" width="50" align="center" nowrap>序号</td>
		<td class="td_head" width="25" align=center><input type="checkbox" id="checkAll" title="全部选中" onclick="javascript:checkAllorders();"></td>
		<td class="td_head" nowrap>文章标题</td>
		<td class="td_head" width="200" nowrap>文章关键字</td>		
		<td class="td_head" width="100" nowrap>文章类型</td>
		<td class="td_head" width="100" nowrap>文章状态</td>
		<td class="td_head" width="100" nowrap>文章创建日期</td>
		<td class="td_head" width="100" nowrap>文章撰写时间</td>
	</tr>
	<%
		int i;
		for(i = 0; i<docList.size(); i++){
			document = (CmsDocument)docList.get(i);
			String docType = "";
			if(document.getDocType()!= null)
				docType = document.getDocType();
			String keyWord = "";
			if(document.getDocKeysWord()!= null)
				keyWord = document.getDocKeysWord();
			String docStatus = "";
			if(document.getDocStatus()!= null)
				docStatus = document.getDocStatus();
			String docTypeValue = "";
			if("0".equals(docType)){
				docTypeValue="普通文章";
			}else if("1".equals(docType)){
				docTypeValue="链接文章";
			}else if("2".equals(docType)){
				docTypeValue="附件文章";
			}
			String docStatusName="";
			if("0".equals(docStatus)){
				docStatusName="待发布";
			}else if("1".equals(docStatus)){
				docStatusName="已发布";
				continue;
			}
			String docMakDate = "";
			if(document.getDocMakeDate()!=null)
			 docMakDate = document.getDocMakeDate().toString();
			System.out.println("docMakDate="+docMakDate);
			
	%>
	<tr id="tr<%=i+1%>">
		<td class="td_head"></td>
		<td class="td_head" align=center><input type="checkbox" id="check<%=i+1%>" onclick="window.parent.fraToolbar.checkID('<%=i+1%>','<%=document.getDocID()%>',this.checked);"></td>
		<td class="td_body"><a style="cursor:hand;text-decoration:underline;"><font color=blue><%=document.getDocTitle()%>&nbsp;</font></a>&nbsp;</td>
		<td class="td_body" width="200" nowrap><%if(keyWord.length()>10){out.print(keyWord.substring(0,10)+"...");}else{out.print(keyWord);}%>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=docTypeValue%>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=docStatusName%>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=docMakDate%>&nbsp;</td>
		<td class="td_body" width="100" nowrap><%=document.getDocRelTime()%>&nbsp;</td>
	</tr>
	<%	
	}
	%>
</table>
</form>
<script type="text/javascript">
	function checkAllorders(){
		var i;
		var check1=window.frmLIST.checkAll.checked;
		var check2;
		
		if (check1)
			window.frmLIST.checkAll.title="全部清除";
		else
			window.frmLIST.checkAll.title="全部选中";
		var count = '<%=i+1%>';	
		for (i=1;i<count;i++){
			eval("check2=window.frmLIST.check" + i + ".checked;");
			if (check1!=check2)
				eval("window.frmLIST.check" + i + ".click();");
		}
	}
</script>
</body>
</html>
