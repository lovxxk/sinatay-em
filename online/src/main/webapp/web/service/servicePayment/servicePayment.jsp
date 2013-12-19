<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">֧����ʽ</li>
			</ul>
		</div>
		<div class="service_main">
			<div class="left_dynamic">
				<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
			</div>
			<div class="right_content">
				<jsp:include page="../common/service_head.jsp"></jsp:include>
				<div class="service_area">
					<div class="title">
						<p>֧����ʽ</p>
					</div>
					<div class="service_query_top">
						<div class="policy_top">
							<p>����Ϊ���ṩ�˶�������֧����ʽ����ѡ�񣬷�������ɶ���֧���� </p>
						</div>
					</div>
					<div class="payment_area">
						<div class="payment_type alipay">
							<div class="payment_title"></div>
							<div class="payment_instro">
								<p class="p1">�Ƽ��Ա��û�ʹ��</p>
								<div class="alipay_content">
									<img src="${ctx }/resources/image/service/alipay_icon.png">
									<p>������Ѿ�ӵ��֧�����˻�����ѡ��֧�������и��</p>
									<p>���������֪�����ʹ��֧�������뿴�����<a href="http://home.alipay.com/individual/tutorial.htm" target="_blank">֧����ʹ�ð���</a></p>
								</div>
							</div>
						</div>
						<div class="payment_type bank">
							<div class="payment_title"></div>
							<div class="payment_instro">
								<p class="bank_content">ѡ������֧�����������п���Ҫ��ͨ��Ӧ������ҵ��������������߲�ͬ��������������֧��ǰ�������ڵ����е绰����ѯ���пɹ�����֧�������п����༰��ͨ������</p>
								<p class="p1">����֧��ƽ̨Ŀǰ��֧�ֵ����п����б����£�</p>
								<div class="bank_list">
									<img src="${ctx }/resources/image/bank/payment_boc.png">
									<img src="${ctx }/resources/image/bank/payment_icbc.png">
									<img src="${ctx }/resources/image/bank/payment_cmb.png">
									<img src="${ctx }/resources/image/bank/payment_ccb.png">
									<img src="${ctx }/resources/image/bank/payment_abc.png">
									<img src="${ctx }/resources/image/bank/payment_spdb.png">
									<img src="${ctx }/resources/image/bank/payment_cib.png">
									<img src="${ctx }/resources/image/bank/payment_cgb.png">
									<img src="${ctx }/resources/image/bank/payment_cmbc.png">
									<img src="${ctx }/resources/image/bank/payment_citic.png">
									<img src="${ctx }/resources/image/bank/payment_hzcb.png">
									<img src="${ctx }/resources/image/bank/payment_cebbank.png">
									<img src="${ctx }/resources/image/bank/payment_shbank.png">
									<img src="${ctx }/resources/image/bank/payment_nbbank.png">
									<img src="${ctx }/resources/image/bank/payment_spabank.png">
									<img src="${ctx }/resources/image/bank/payment_bjrcb.png">
									<img src="${ctx }/resources/image/bank/payment_fdb.png">
									<img src="${ctx }/resources/image/bank/payment_postgc.png">
								</div>
								<p class="p1">���ֿ��ִ�������֧���޶�˽���࣬�뿴�����<a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">�����е���������֧���޶��ܱ�</a></p>
							</div>
						</div>
					</div>
					<div class="payment_notice">
						<p><a href="http://help.alipay.com/lab/help_detail.htm?help_id=211661" target="_blank">�˽����</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>