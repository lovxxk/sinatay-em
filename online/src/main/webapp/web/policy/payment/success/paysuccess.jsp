<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="#">产品展示</a><span> &gt;</span></li>
				<li><a href="#">百万身驾</a><span> &gt;</span></li>
				<li class="at">投保</li>
			</ul>
		</div>
		<div class="success_main">
			<div class="top"></div>
			<div class="success_result">
				<div class="tip"></div>
				<div class="info">
					<p>尊敬的<span>
						${orderForm.insurancePolicy.insuranceApplicant.fullName }
					</span></p>
					<p>您的保费已经支付成功，信息如下：</p>
					<div class="policy_info">
						<div class="info_title">保单信息</div>
						<div class="info_name row">
							<div class="col">
								<div class="value">产品名称</div>
							</div>
							<div class="col">
								<div class="value">投保单号</div>
							</div>
							<div class="col">
								<div class="value">被保险人姓名</div>
							</div>
							<div class="col">
								<div class="value">金额（元）</div>
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
									<div class="value">￥${orderForm.orderAmount }</div>
								</div>
							</div>
						</c:forEach>
						<div class="total row">
							<div class="col">
								<div class="value">￥${orderForm.orderAmount }</div>
							</div>
						</div>
					</div>
					<p>电子保单稍后发送到<span>${orderForm.insurancePolicy.insuranceApplicant.email }</span>，请您注意查收！ 您也可以登录<a href="${ctx}/memberCenter/homePage.do">会员中心</a>查看及下载电子保单。</p>
					<div class="return_index">
						<button class="click_btn" onclick="javascript:window.location.href='${ctx }/index.jsp'">返回首页</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<% 
	//通知支付宝回调结果
	out.print(request.getAttribute("flag"));
%>