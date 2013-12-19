<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="dynamic_frame interest_notice">
	<div class="dynamic_title">
		<p>利率公告</p>
		<div class="more">
			<a href="${ctx}/universal/initUniversalQuery.do">更多&gt;&gt;</a>
		</div>
	</div>
	<label class="notice_select">宝利来两全保险（万能型）A款</label>
	<label class="tip_notice">2013年7月1日后（含当日）生效</label>
	<div class="interest_date">
		结算日期：<span id="interest_date"></span>
	</div>
	<div class="interest_info">
		<div class="info_row label">
			<div class="day">日利率</div>
			<div class="year">年利率</div>
		</div>
		<div class="info_row value">
			<div class="day">
				<span id="day_interest"></span>%
			</div>
			<div class="year">
				<span id="year_interest"></span>%
			</div>
		</div>
	</div>
	<div class="view_detail">
		<a href="${ctx}/universal/initUniversalQuery.do">查看详情</a>
	</div>
	<input type="text" id="ctx" value="${ctx}" style="display:none"/>
</div>
<script>
$(document).ready(function(){
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/universalJson/loadNewUniversal.do",
		dataType : 'json',
		data : '{}',
		success : function(data) {
			$('#interest_date').html(data.newUniversalRate.culDate.substr(0,10));
			$('#day_interest').html(data.newUniversalRate.dateRate);
			$('#year_interest').html(data.newUniversalRate.yearRate);
		},
		error: function(data) {
		}
	});	
});
</script>