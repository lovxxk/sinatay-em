<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>�ҵ��ⰸ</p>
				</div>
				<c:if test="${hasPolicy == 'N' }">
					<div class="noPolicy">
						<img src="${ctx}/resources/image/user/member/alert.png" />&nbsp;�ǳ���Ǹ,���˻��»�û�б���,����
						<a href="${ctx}/info/initPolicyList.do">��ӱ���</a>
					</div>
				</c:if>
				<c:if test="${hasPolicy == 'Y' }">
					<c:if test="${hasClaims == 'N' }">
						<div class="noPolicy">
							<img src="${ctx}/resources/image/user/member/alert.png" />&nbsp;����δ���ⰸ��Ϣ
						</div>
					</c:if>
					<c:if test="${hasClaims == 'Y' }">
						<div class="claims_list">
							<c:forEach var="claimProcessList" items="${listClaimProcessList}"
								varStatus="status">
								<div class="claims_item">
									<div class="claims_info">
										<div class="name detail claimNumber">
											�����ţ�<span>${claimProcessList.claimNumber }</span>
										</div>
										<div class="detail insuredName">
											�����ˣ�<span>${claimProcessList.insuredName }</span>
										</div>
										<div class="detail accDate">
											����ʱ�䣺<span>${claimProcessList.accDate }</span>
										</div>
									</div>
									<div class="claims_status">
										<c:if test="${claimProcessList.claimStatus == '�ѱ���'}">
											<div class="status current">�ѱ���</div>
											<div class="arrow"></div>
											<div class="status">������</div>
											<div class="arrow"></div>
											<div class="status">�ѽ᰸</div>
										</c:if>
										<c:if test="${claimProcessList.claimStatus == '������'}">
											<div class="status">�ѱ���</div>
											<div class="arrow"></div>
											<div class="status current">������</div>
											<div class="arrow"></div>
											<div class="status">�ѽ᰸</div>
										</c:if>
										<c:if test="${claimProcessList.claimStatus == '�ѽ᰸'}">
											<div class="status">�ѱ���</div>
											<div class="arrow"></div>
											<div class="status">������</div>
											<div class="arrow"></div>
											<div class="status current">�ѽ᰸</div>
										</c:if>
									</div>
									<div class="claims_btn click_btn">�鿴����</div>
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