<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${param.type=='create'}"><!-- 新建的时候提示信息 -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			var message = encodeURIComponent("${message}");
			window.parent.location.href="${ctx}/global/inc/info1.jsp?message="+message;
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
	<c:when test="${flag eq 1}">
		<script language="javascript">
			alert("${message}");
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
			
		</script>
	</c:when>
	<c:when test="${flag eq 3}">
		<script language="javascript">
			alert("${message}");
			window.parent.close();
			window.open("${ctx}/business/businessManage/dataDictionary/queryGeCodePage.do?geCodeType.codeType=${geCodeType}","数据字典类型下的数据" ,"top=100, left=100, width=900,height=600,toolbar=no");		
			
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert('${message}');
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
	<c:when test="${flag eq 2}">
		<script language="javascript">
			alert("${message}");
			window.parent.frames[0].doSearch();
			window.parent.opener.parent.frames[0].doSearch();
		</script>
	</c:when>
	<c:when test="${flag eq 3}">
		<script language="javascript">		
			alert("${message}");
			window.parent.close();
			window.open("${ctx}/business/businessManage/dataDictionary/queryGeCodePage.do?geCodeType.codeType=${geCodeType}","数据字典类型下的数据" ,"top=100, left=100, width=900,height=600,toolbar=no");		
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