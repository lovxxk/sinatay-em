<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">网点信息</li>
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
						<p>服务网点查询</p>
					</div>
					<div class="query_form">
						<label class="province_label"><span class="required">*</span>按省份搜索</label>
						<input name="province" id="query_province" type="text" /> <input
							name="city" id="query_city" type="text" /> <label
							class="name_label">网点名称</label> <input name="webName"
							id="website_name" type="text" />
						<div class="query click_btn">查询<input id="ctx" style="display:none" type="text" value="${ctx }"/></div>
					</div>
					<div class="website_list">
						<div class="website tip">
							<div class="content_col num">序号</div>
							<div class="content_col name">网点名称</div>
							<div class="content_col address">&nbsp;&nbsp;&nbsp;&nbsp;网点地址</div>
							<!-- <div class="content_col phone">&nbsp;&nbsp;&nbsp;&nbsp;网点电话</div> -->
						</div>
						<table class="website_table">
						</table>
						<p class="tips">当您不知道服务网点地址时，可以通过服务网点平台查询服务地址！</p>
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