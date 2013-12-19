<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx }/global/ui/taglibs.js" language="javascript"></script>
</head>
<body>
<input id="contextPath" value="${pageContext.request.contextPath}" type="hidden"/>
</body>
</html>