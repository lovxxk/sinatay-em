<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<script type="text/javascript">
window.opener.document.getElementById('href').value="${ctx}${innerPath}";
window.close();
</script>

