<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">会员首页</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>我的赔案</p>
				</div>
				<c:if test="${hasPolicy == 'N' }">
					<div class="noPolicy">
						<img src="${ctx}/resources/image/user/member/alert.png" />&nbsp;非常抱歉,您账户下还没有保单,请您
						<a href="${ctx}/info/initPolicyList.do">添加保单</a>
					</div>
				</c:if>
				<c:if test="${hasPolicy == 'Y' }">
					<c:if test="${hasClaims == 'N' }">
						<div class="noPolicy">
							<img src="${ctx}/resources/image/user/member/alert.png" />&nbsp;您还未有赔案信息
						</div>
					</c:if>
					<c:if test="${hasClaims == 'Y' }">
						<div class="claims_list">
							<c:forEach var="claimProcessList" items="${listClaimProcessList}"
								varStatus="status">
								<div class="claims_item">
									<div class="claims_info">
										<div class="name detail claimNumber">
											案件号：<span>${claimProcessList.claimNumber }</span>
										</div>
										<div class="detail insuredName">
											出险人：<span>${claimProcessList.insuredName }</span>
										</div>
										<div class="detail accDate">
											出险时间：<span>${claimProcessList.accDate }</span>
										</div>
									</div>
									<div class="claims_status">
										<c:if test="${claimProcessList.claimStatus == '已报案'}">
											<div class="status current">已报案</div>
											<div class="arrow"></div>
											<div class="status">受理中</div>
											<div class="arrow"></div>
											<div class="status">已结案</div>
										</c:if>
										<c:if test="${claimProcessList.claimStatus == '受理中'}">
											<div class="status">已报案</div>
											<div class="arrow"></div>
											<div class="status current">受理中</div>
											<div class="arrow"></div>
											<div class="status">已结案</div>
										</c:if>
										<c:if test="${claimProcessList.claimStatus == '已结案'}">
											<div class="status">已报案</div>
											<div class="arrow"></div>
											<div class="status">受理中</div>
											<div class="arrow"></div>
											<div class="status current">已结案</div>
										</c:if>
									</div>
									<div class="claims_btn click_btn">查看详情</div>
								</div>
							</c:forEach>
							<div class="page_index">
								<div class="page prev_page">
									<a>&nbsp;</a>
								</div>
								<div class="page next_page">
									<a>&nbsp;</a>
								</div>
							</div>
							<form id="fm" style="display: none" method="post" name="fm"
								action="${ctx}/claims/claimDetail.do">
								<input type="text" id="claimNumber"
									name="claimDetail.claimNumber" value="" /> <input type="text"
									id="insuredName" name="claimDetail.insuredName" value="" /> <input
									type="text" id="accDate" name="claimDetail.accDate" value="" />
							</form>
						</div>
					</c:if>
				</c:if>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>