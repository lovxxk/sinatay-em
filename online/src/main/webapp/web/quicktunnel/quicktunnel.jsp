<%@ page contentType="text/html; charset=gbk" language="java" import="java.sql.*" errorPage="" isELIgnored="false" %>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="index_area">
	<div class="area_title">快速服务通道</div>
	<div class="area_main">
		<div class="service_channel query">
			<div class="channel_name">快速查询</div>
			<ul class="channel_list">
				<li><a href="${ctx}/web/service/policyQueryGuide/index.jsp" target="_blank">&gt;&nbsp;保单查询与验真</a></li>
				<li><a href="http://zizhu.sinatay.com/xtsave/" target="_blank">&gt;&nbsp;自助保险卡激活</a></li>
				<li><a href="${ctx}/web/service/universalQuery/index.jsp" target="_blank">&gt;&nbsp;利率公告</a></li>
				<li><a href="http://zizhu.sinatay.com/nss/index3.jsp" target="_blank">&gt;&nbsp;赠险查询</a></li>
				<li><a href="${ctx }/web/service/websiteQuery/index.jsp" target="_blank">&gt;&nbsp;网点查询</a></li>
				<li><a href="${ctx }/web/service/problem/login/index.jsp" target="_blank">&gt;&nbsp;常见问题</a></li>
			</ul>
		</div>
		<div class="service_channel transact">
			<div class="channel_name">线上业务办理</div>
			<ul class="channel_list">
				<li><a href="${ctx}/web/service/orderManage/index.jsp" target="_blank">&gt;&nbsp;订单支付</a></li>
				<li><a href="${ctx}/web/service/changeInfo/index.jsp" target="_blank">&gt;&nbsp;变更地址信息</a></li>
				<li><a href="${ctx}/web/service/epolicy/index.jsp" target="_blank">&gt;&nbsp;电子保单下载</a></li>
				<li><a href="${ctx }/web/service/email/index.jsp" target="_blank">&gt;&nbsp;电子函件订阅</a></li>
				<li><a href="${ctx }/web/service/universal/index.jsp" target="_blank">&gt;&nbsp;万能险领取</a></li>
				<li><a href="${ctx}/web/service/changeInfo/index.jsp" target="_blank">&gt;&nbsp;变更个人信息</a></li>
			</ul>
		</div>
		<div class="service_channel last claims">
			<div class="channel_name">闪电理赔</div>
			<ul class="channel_list">
				<li><a href="${ctx}/web/service/claimReport/index.jsp" target="_blank">&gt;&nbsp;在线报案</a></li>
				<li><a href="${ctx}/claims/initClaimProcess.do" target="_blank">&gt;&nbsp;查询理赔进度</a></li>
				<li><a href="${ctx}/web/service/serviceClaims/index.jsp" target="_blank">&gt;&nbsp;理赔时效</a></li>
				<li><a href="${ctx}/web/service/claims/index.jsp" target="_blank">&gt;&nbsp;理赔流程介绍</a></li>
				<li><a href="${ctx}/dcenter/downloadCenterInit.do" target="_blank">&gt;&nbsp;理赔单证下载</a></li>
				<li><a href="${ctx}/web/service/hospitalQuery/index.jsp" target="_blank">&gt;&nbsp;查询理赔定点医院</a></li>
			</ul>
		</div>
	</div>
</div>
	

