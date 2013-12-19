<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_frame">
	<span id="message" style="color: red; margin-left: 30px; size: 8;"></span>
	<label class="input_label">会员中心</label>
	<div class="user_link">
		<div class="link policy"><a href="${ctx}/info/initPolicyList.do">保单查询</a></div>
		<div class="link right order"><a href="${ctx}/order/orders.do">订单支付</a></div>
		<div class="link claims"><a href="${ctx}/claims/initClaimProcess.do">理赔进度</a></div>
		<div class="link right email"><a href="${ctx}/email/myEmailSubscribe.do">电子函件</a></div>
	</div>
	<div class="action">
		<label for="remember">尊敬的<span class="user_name">
				<c:choose>
					<c:when test="${!empty geUserPersonal.userName }">
						${geUserPersonal.userName }
					</c:when>
					<c:when test="${!empty geUserPersonal.alias }">
						${geUserPersonal.alias }
					</c:when>
					<c:otherwise>
						${geUserPersonal.userAccount }
					</c:otherwise>
				</c:choose>
			</span>您好！</label>
	</div>
	<div class="login_submit">
		<a class="click_btn" href="${ctx}/memberCenter/homePage.do">进入会员中心</a>
		&nbsp;&nbsp;&nbsp;<br>
		&nbsp;&nbsp;&nbsp;<br>
	</div>
</div>