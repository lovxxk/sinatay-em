<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String tmpDocId = request.getParameter("tmpDocId") == null ? "" : request.getParameter("tmpDocId").trim();
%>
<html>
	<head>
	</head>
	<body>
		<form ENCTYPE="multipart/form-data" method="post"  id="abc" name="abc" action="${ctx}/articleManage/toUploadeImage.do">	
			<input type="hidden" id="tmpDocId" name="tmpDocId" value="<%=tmpDocId %>"/>																	   
			<input type="hidden" id="attNum" name="attNum" value="0"/>
			<input type="hidden" id="docType" name="docType" value="2"/>	
			<!-- <input type="hidden" id="docFileName" name="docFileName" value=""/> -->
			<s:file name="doc" id="doc" label="file"></s:file>
		</form>
	</body>
</html>
