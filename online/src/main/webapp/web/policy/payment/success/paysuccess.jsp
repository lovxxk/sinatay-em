<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="#">��Ʒչʾ</a><span> &gt;</span></li>
				<li><a href="#">�������</a><span> &gt;</span></li>
				<li class="at">Ͷ��</li>
			</ul>
		</div>
		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip"></div>
				<div class="info">
					<p>�𾴵�<span>
						${orderForm.insurancePolicy.insuranceApplicant.fullName }
					</span></p>
					<p>���ı����Ѿ�֧���ɹ�����Ϣ���£�</p>
					<div class="policy_info">
						<div class="info_title">������Ϣ</div>
						<div class="info_name row">
							<div class="col">
								<div class="value">��Ʒ����</div>
							</div>
							<div class="col">
								<div class="value">Ͷ������</div>
							</div>
							<div class="col">
								<div class="value">������������</div>
							</div>
							<div class="col">
								<div class="value">��Ԫ��</div>
							</div>
						</div>
						<c:forEach items="${orderForm.insurancePolicy.insureds }" var="insured">
							<div class="info_value row">
								<div class="col">
									<c:choose>
										<c:when test="${fn:length(orderForm.productName) >= 10 }">
											<div class="value" title="${orderForm.productName}">
												${fn:substring(orderForm.productName, 0, 10)}
											</div>
										</c:when>
										<c:when test="${fn:length(orderForm.productName) < 10 }">
											<div class="value" title="${orderForm.productName}">
												${orderForm.productName}
											</div>
										</c:when>
									</c:choose>
									
								</div>
								<div class="col">
									<div class="value">${orderForm.insurancePolicy.applicationNumber }</div>
								</div>
								<div class="col">
									<div class="value">${insured.fullName }</div>
								</div>
								<div class="col">
									<div class="value">��${orderForm.orderAmount }</div>
								</div>
							</div>
						</c:forEach>
						<div class="total row">
							<div class="col">
								<div class="value">��${orderForm.orderAmount }</div>
							</div>
						</div>
					</div>
					<p>���ӱ����Ժ��͵�<span>${orderForm.insurancePolicy.insuranceApplicant.email }</span>������ע����գ� ��Ҳ���Ե�¼<a href="${ctx}/memberCenter/homePage.do">��Ա����</a>�鿴�����ص��ӱ�����</p>
					<div class="return_index">
						<button class="click_btn" onclick="javascript:window.location.href='${ctx }/index.jsp'">������ҳ</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<% 
	//֪֧ͨ�����ص����
	out.print(request.getAttribute("flag"));
%>