<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="product_map">
	<div class="category traffic">
		<div class="cate_name"><div></div>交通工具</div>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=0&day=0">飞机</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=1&day=0">火车</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=3&day=0" class="last">轮船</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=2&day=0">客车</a>
		<a class="last" href="${ctx }/sale/toQuote.do?eid=G120130902152737017&type=4&day=0">私家车</a>
	</div>
	<div class="category finance">
		<div class="cate_name"><div></div>理财险</div>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=2&day=0">1年</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=1&day=0">9个月</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152647034&type=0&day=0" class="last">6个月</a>
	</div>
	<div class="category life">
		<div class="cate_name"><div></div>人身寿险</div>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=0&day=0">100万</a>
		<a href="${ctx }/sale/toQuote.do?eid=G120130902152808046&type=1&day=0" class="last">200万</a>
	</div>
	<div class="category child">
		<div class="cate_name"><div></div>少儿保险</div>
		<a href="#" disabled>教育</a>
		<a href="#" disabled>疾病</a>
		<a href="#" class="last" disabled>意外</a>
	</div>
</div>