<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="login_status">
	<span id="message" style="color: red; margin-left: 30px; size: 8;"></span>
	<label class="input_label">��Ա����</label>
	<div class="user_link">
		<div class="link policy"><a href="#">������ѯ</a></div>
		<div class="link right order"><a href="#">����֧��</a></div>
		<div class="link claims"><a href="#">�������</a></div>
		<div class="link right email"><a href="#">���Ӻ���</a></div>
	</div>
	<div class="action">
		<label>�𾴵�<span class="user_name">
			<c:if test="${!empty geUserPersonal.userAccount }">
				${geUserPersonal.userAccount }
			</c:if>
			<c:if test="${empty geUserPersonal.userAccount }">
				${geUserPersonal.alias }
			</c:if>
		</span>���ã�</label>
		<label>��ӭ����<a class="#">��Ա����</a></label>
	</div>
</div>