<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">万能险利率查询</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">万能险利率查询</div>
			</div>
			<div class="query_form">
				<label class="main_label">查询条件</label> <label class="input_label">请您选择</label>
				<input class="input_text" id="product_select" type="text" /><br/><label
					class="input_label row_2" id="row_2_label">时间段：从</label> <input class="input_text Wdate row_2"
					id="interest_start" type="text" /> <label class="input_label row_2">至</label>
				<input class="input_text Wdate row_2" id="interest_end" type="text" /> <input
					class="click_btn query_interest row_2" type="button" value="查询" />
			</div>
			<div class="info_area interest_info">
				<div class="title">
					<span class="title_name">万能型产品结算利率公告</span>
				</div>
				<div class="input_area">
					<div class="input_row label">
						<div class="input_col name">产品名称</div>
						<div class="input_col date">结算日期</div>
						<div class="input_col day_interest">结算日利率</div>
						<div class="input_col year_interest">折合年结算利率</div>
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
					<span>提示：</span>折合年结算利率为日单利月复利计算的结果(保留两位小数)。本次公布的结算利率数值仅适用于2013年8月，并不代表未来的投资收益。您可以通过我公司全国客服热线4006008890、客户服务柜台以及您的保险合同的服务人员了解保单的相关信息。
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