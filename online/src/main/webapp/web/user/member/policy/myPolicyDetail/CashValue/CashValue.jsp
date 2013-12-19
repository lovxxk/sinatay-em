<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">��Ա��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/info/initPolicyList.do">�ҵı���</a><span> &gt;</span></li>
				<li><a href="${ctx}/myPolicyDetail/myPolicyDetail.do?policyNo=${cashValueInfo.policyNo}">��������</a><span> &gt;</span></li>
				<li class="at">�ֽ��ֵ</li>
			</ul>
		</div>
		<div class="email_list" >
					<div class="head">�ֽ��ֵ��</div>
					<div class="content tip">
						<div class="content_col time">�������ĩ</div>
						<div class="content_col num">�ֽ��ֵ</div>
						<div class="content_col name">����屣��</div>
						
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

