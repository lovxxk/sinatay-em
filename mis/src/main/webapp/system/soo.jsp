<%@ page language="java"  pageEncoding="GBK"%>
<%@page import="cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator"%>

<%
    GeOperator geoperator=(GeOperator)session.getAttribute("geOperator");
	String operatorid=geoperator.getOperatorid();
%>
<html>
<body>
<form action="http://10.0.2.166:9080/drools/soo.jsp" method="post" id="fo">
<input type="hidden" name="username" value="<%=operatorid%>"/>
</form>
</body>
<script type="text/javascript">
document.getElementById('fo').submit();
</script>
</html>
