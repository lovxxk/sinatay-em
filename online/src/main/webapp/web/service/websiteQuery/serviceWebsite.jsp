<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">������Ϣ</li>
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
						<p>���������ѯ</p>
					</div>
					<div class="query_form">
						<label class="province_label"><span class="required">*</span>��ʡ������</label>
						<input name="province" id="query_province" type="text" /> <input
							name="city" id="query_city" type="text" /> <label
							class="name_label">��������</label> <input name="webName"
							id="website_name" type="text" />
						<div class="query click_btn">��ѯ<input id="ctx" style="display:none" type="text" value="${ctx }"/></div>
					</div>
					<div class="website_list">
						<div class="website tip">
							<div class="content_col num">���</div>
							<div class="content_col name">��������</div>
							<div class="content_col address">&nbsp;&nbsp;&nbsp;&nbsp;�����ַ</div>
							<!-- <div class="content_col phone">&nbsp;&nbsp;&nbsp;&nbsp;����绰</div> -->
						</div>
						<table class="website_table">
						</table>
						<p class="tips">������֪�����������ַʱ������ͨ����������ƽ̨��ѯ�����ַ��</p>
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