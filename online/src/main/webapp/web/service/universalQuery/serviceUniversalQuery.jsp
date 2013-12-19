<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">���������ʲ�ѯ</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">���������ʲ�ѯ</div>
			</div>
			<div class="query_form">
				<label class="main_label">��ѯ����</label> <label class="input_label">����ѡ��</label>
				<input class="input_text" id="product_select" type="text" /><br/><label
					class="input_label row_2" id="row_2_label">ʱ��Σ���</label> <input class="input_text Wdate row_2"
					id="interest_start" type="text" /> <label class="input_label row_2">��</label>
				<input class="input_text Wdate row_2" id="interest_end" type="text" /> <input
					class="click_btn query_interest row_2" type="button" value="��ѯ" />
			</div>
			<div class="info_area interest_info">
				<div class="title">
					<span class="title_name">�����Ͳ�Ʒ�������ʹ���</span>
				</div>
				<div class="input_area">
					<div class="input_row label">
						<div class="input_col name">��Ʒ����</div>
						<div class="input_col date">��������</div>
						<div class="input_col day_interest">����������</div>
						<div class="input_col year_interest">�ۺ����������</div>
					</div>
					<c:forEach var="universalRate" items="${listUniversalRate}" varStatus="status">
						<div class="input_row item">
							<div class="input_col name">
								<p class="main_name">${universalRate.riskName}</p>
								<p class="main_info"></p>
							</div>
							<div class="input_col date">${universalRate.culDate}</div>
							<div class="input_col day_interest">${universalRate.dateRate}%</div>
							<div class="input_col year_interest">${universalRate.yearRate}%</div>
						</div>
					</c:forEach>
				</div>
				<div class="page_index">
					<div class="page prev_page">
						<a>&nbsp;</a>
					</div>
					<div class="page next_page">
						<a>&nbsp;</a>
					</div>
				</div>
				<div class="notice">
					<span>��ʾ��</span>�ۺ����������Ϊ�յ����¸�������Ľ��(������λС��)�����ι����Ľ���������ֵ��������2013��8�£���������δ����Ͷ�����档������ͨ���ҹ�˾ȫ���ͷ�����4006008890���ͻ������̨�Լ����ı��պ�ͬ�ķ�����Ա�˽Ᵽ���������Ϣ��
				</div>
				<div class="interest_detail">
					<div class="interest bonus">
						<jsp:include page="../universalnote/bonusnote/universalquery_include.jsp"></jsp:include>
					</div>
					<div class="interest contract">
						<jsp:include page="../universalnote/appointnote/universalquery_include.jsp"></jsp:include>
					</div>
					<div class="interest life last">
						<jsp:include page="../universalnote/livenote/universalquery_include.jsp"></jsp:include>
					</div>
					<div style="display:none">
						<input type="text" name="ctx" id="ctx" value="${ctx }" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>