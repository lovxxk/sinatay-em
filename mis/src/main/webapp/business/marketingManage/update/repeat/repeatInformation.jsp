<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${param.type=='create'}"><!-- �½���ʱ����ʾ��Ϣ -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			window.parent.location.href="${ctx}/business/marketingManage/update/repeat/info1.jsp?message=${message}&buttonValue=close";
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
			alert("${message}");
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
			
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
			window.parent.frames[0].doSearch();
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