<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_status">
	<span id="message" style="color: red; margin-left: 30px; size: 8;"></span>
	<label class="input_label">会员中心</label>
	<div class="user_link">
		<div class="link policy"><a href="#">保单查询</a></div>
		<div class="link right order"><a href="#">订单支付</a></div>
		<div class="link claims"><a href="#">理赔进度</a></div>
		<div class="link right email"><a href="#">电子函件</a></div>
	</div>
	<div class="action">
		<label>尊敬的<span class="user_name">
			<c:if test="${!empty geUserPersonal.userAccount }">
				${geUserPersonal.userAccount }
			</c:if>
			<c:if test="${empty geUserPersonal.userAccount }">
				${geUserPersonal.alias }
			</c:if>
		</span>您好！</label>
		<label>欢迎进入<a class="#">会员中心</a></label>
	</div>
</div>