<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">����ҽԺ��ѯ</li>
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
						<p>����ҽԺ��ѯ</p>
					</div>
					<p class="tips">1��������������ʱ����ͽ�ѡ������������ȼ������еĶ�����������ϵ�ҽԺ������������Ҫ�ṩ������������������ơ��䶾�����Ʒ����ҽ�ƻ�������
					<br>
					2����Ͷ����̩�����ǳ�����סԺҽ�Ʊ��գ��뵽���¶���ҽԺסԺ���ơ� </p>
					<div class="query_form">
						<label class="province_label"><span class="required">*</span>��ʡ������</label>
						<input name="province" id="query_province" type="text" /> <input
							name="city" id="query_city" type="text" /> <label
							class="name_label">ҽԺ����</label> <input name="hosName"
							id="hospital_name" type="text" />
						<div class="query click_btn">��ѯ<input id="ctx" style="display:none" type="text" value="${ctx }"/></div>
					</div>
					<div class="hospital_list">
						<div class="hospital tip">
							<div class="content_col num">���</div>
							<div class="content_col name">ҽԺ����</div>
							<div class="content_col address">ҽԺ��ַ</div>
						</div>
						<table class="hospital_table">
						</table>
						<div class="page_index">
							<div class="page prev_page">
								<a>&nbsp;</a>
							</div>
							<div class="page next_page">
								<a>&nbsp;</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>