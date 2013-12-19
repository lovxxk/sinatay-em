<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${param.type=='create'}"><!-- 新建的时候提示信息 -->
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
<c:when test="${param.type=='update'}"><!-- 修改时提示信息 -->
	<c:choose>
	<c:when test="${flag eq 2}">
		<script language="javascript">
			window.location.href="${ctx}/global/inc/info3.jsp?message=${message}";
		</script>
	</c:when>
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
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
		</script>
	</c:otherwise>
	</c:choose>
</c:when>
<c:otherwise><!-- 类似删除的提示信息 -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			alert("${message}");
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			alert("${message}");
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
		</script>
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>