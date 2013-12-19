<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">定点医院查询</li>
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
						<p>定点医院查询</p>
					</div>
					<p class="tips">1、当您遇到不幸时，请就近选择国家卫生部等级分类中的二级或二级以上的医院（但不包括主要提供康复、护理、疗养、戒酒、戒毒或类似服务的医疗机构）。
					<br>
					2、如投保信泰世纪星辰个人住院医疗保险，须到以下定点医院住院治疗。 </p>
					<div class="query_form">
						<label class="province_label"><span class="required">*</span>按省份搜索</label>
						<input name="province" id="query_province" type="text" /> <input
							name="city" id="query_city" type="text" /> <label
							class="name_label">医院名称</label> <input name="hosName"
							id="hospital_name" type="text" />
						<div class="query click_btn">查询<input id="ctx" style="display:none" type="text" value="${ctx }"/></div>
					</div>
					<div class="hospital_list">
						<div class="hospital tip">
							<div class="content_col num">序号</div>
							<div class="content_col name">医院名称</div>
							<div class="content_col address">医院地址</div>
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