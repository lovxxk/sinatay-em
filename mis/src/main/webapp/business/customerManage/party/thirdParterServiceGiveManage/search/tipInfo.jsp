<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${param.type=='create'}"><!-- �½���ʱ����ʾ��Ϣ -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			window.parent.location.href="${ctx}/global/inc/info1.jsp?message=${message}";
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			alert("${message}");
		</script>
	</c:otherwise>
	</c:choose>
</c:when>
<c:when test="${param.type=='update'}"><!-- �޸�ʱ��ʾ��Ϣ -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
		var fraSearchForm = top.frames[1].frames[2].frames[0];
		fraSearchForm.doSearch();
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert('${message}');
		</script>
	</c:otherwise>
	</c:choose>
</c:when>
<c:otherwise><!-- ����ɾ������ʾ��Ϣ -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			alert("${message}");
			var fraSearchForm = top.frames[1].frames[2].frames[0];
			fraSearchForm.doSearch();
		</script>
	</c:when>
	<c:when test="${flag eq 2}">
		<script language="javascript">
			alert("${message}");
			var fraSearchForm = top.frames[1].frames[2].frames[0];
			fraSearchForm.doSearch();
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			alert("${message}");
		</script>
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>