<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">会员首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/info/initPolicyList.do">我的保单</a><span> &gt;</span></li>
				<li><a href="${ctx}/myPolicyDetail/myPolicyDetail.do?policyNo=${cashValueInfo.policyNo}">保单详情</a><span> &gt;</span></li>
				<li class="at">现金价值</li>
			</ul>
		</div>
		<div class="email_list" >
					<div class="head">现金价值表</div>
					<div class="content tip">
						<div class="content_col time">保单年度末</div>
						<div class="content_col num">现金价值</div>
						<div class="content_col name">减额交清保额</div>
						
					</div>
					<c:forEach var="valueInfo" items="${cashValueInfo.valueInfos }">
						<div class="content">
						<c:forEach var="value" items="${valueInfo.values}" varStatus="status">
							
								<c:if test="${status.index ==0 }">
									<div class="content_col time">${value}&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</c:if>
								<c:if test="${status.index ==1 }">
									<div class="content_col num">${value}&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</c:if>
								<c:if test="${status.index ==2 }">
									<div class="content_col name">${value}&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</c:if>
							
						</c:forEach>
						</div>
					</c:forEach>
					
				</div>
		

	</div>
</div>

