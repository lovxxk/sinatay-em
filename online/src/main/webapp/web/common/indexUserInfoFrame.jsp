<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_frame">
	<span id="message" style="color: red; margin-left: 30px; size: 8;"></span>
	<label class="input_label">��Ա����</label>
	<div class="user_link">
		<div class="link policy"><a href="${ctx}/info/initPolicyList.do">������ѯ</a></div>
		<div class="link right order"><a href="${ctx}/order/orders.do">����֧��</a></div>
		<div class="link claims"><a href="${ctx}/claims/initClaimProcess.do">�������</a></div>
		<div class="link right email"><a href="${ctx}/email/myEmailSubscribe.do">���Ӻ���</a></div>
	</div>
	<div class="action">
		<label for="remember">�𾴵�<span class="user_name">
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
			</span>���ã�</label>
	</div>
	<div class="login_submit">
		<a class="click_btn" href="${ctx}/memberCenter/homePage.do">�����Ա����</a>
		&nbsp;&nbsp;&nbsp;<br>
		&nbsp;&nbsp;&nbsp;<br>
	</div>
</div>