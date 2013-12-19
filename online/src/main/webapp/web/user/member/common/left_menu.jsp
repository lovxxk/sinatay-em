<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="left_menu">
	<div class="head_main">
		<div class="head_frame">
			<div class="head_icon"></div>
		</div>
	</div>
	<ul class="member_menu">
		<li class="item index"><a href="${ctx}/memberCenter/homePage.do"><div class="icon"></div>会员首页</a></li>
		<li class="item policy"><a href="${ctx}/info/initPolicyList.do"><div class="icon"></div>我的保单</a></li>
		<li class="item order"><a href="${ctx }/order/orders.do"><div class="icon"></div>我的订单</a></li>
		<li class="item service"><a href="${ctx}/claims/initClaimProcess.do"><div class="icon"></div>我的赔案</a></li>
		<li class="item email"><a href="${ctx}/email/myEmailSubscribe.do"><div class="icon"></div>电子函件</a></li>
		<li class="item account">
			<a href="${ctx}/edit/editUserPersonal.do"><div class="icon"></div>账户信息</a>
			<div class="account">
				<ul>
					<li class="account_item"><a href="${ctx}/edit/editUserPersonal.do">个人资料</a></li>
					<li class="account_item"><a href="${ctx }/edit/accountInfo.do">帐号信息</a></li>
					<li class="account_item"><a href="${ctx }/insured/addInsured.do">常用被保险人</a></li>
					<li class="account_item"><a href="${ctx }/web/user/member/account/changePassword/index.jsp">修改密码</a></li>
				</ul>
			</div>
		</li>
<%-- 		<li class="item activity"><a href="${ctx }/web/user/member/account/activityInfo/index.jsp"><div class="icon"></div>我的活动</a></li> --%>
	</ul>
</div>