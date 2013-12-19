<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<script type="text/javascript">
window.opener.document.getElementById('src').value="${ctx}${innerPath}";
window.opener.document.getElementById('alt').value="${fileName}";
window.opener.document.getElementById('title').value="${fileName}";
window.opener.document.getElementById('src').onchange();
window.close();
</script>
