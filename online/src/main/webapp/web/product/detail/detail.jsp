<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/product/index.jsp">��Ʒѡ��</a><span>&gt;</span></li>
				<li class="at">${geDirectory.productName }</li>
			</ul>
		</div>
		<div class="product_main">
			<div class="left_dynamic">
				<jsp:include page="/web/product/common/product_commend.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
				<div class="display"></div>
				<jsp:include page="/web/product/common/product_commen_question.jsp"></jsp:include>
			</div>
			<div class="right_product">
				<div style="display: none" id="productRisks">${productRisks}</div>
				<jsp:include page="/upload/publishedProduct/${productCode}/${productCode}_premiumCalculation.jsp"></jsp:include>
				<div class="product_detail">
					<div class="detail_tab">
						<ul>
							<li class="select">��������</li>
							<li>������֪</li>
							<li>��������</li>
							<li>�����¼</li>
						</ul>
					</div>
					<div class="detail_container">
						<jsp:include page="/upload/publishedProduct/${productCode}/${productCode}_detail.jsp"></jsp:include>
						<jsp:include page="/web/product/common/product_purchaseHistory.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/web/product/common/product_help.jsp"></jsp:include>
	</div>
</div>