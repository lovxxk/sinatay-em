<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li>服务中心</li>
			</ul>
		</div>
		<div class="service_main">
			<jsp:include page="leftDynamic.jsp"></jsp:include>
			<jsp:include page="rightContent.jsp"></jsp:include>
		</div>
	</div>
</div>