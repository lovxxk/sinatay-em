<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="left_menu">
	<div class="head_main">
		<div class="head_frame">
			<div class="head_icon"></div>
		</div>
	</div>
	<ul class="member_menu">
		<li class="item index"><a href="${ctx}/memberCenter/homePage.do"><div class="icon"></div>��Ա��ҳ</a></li>
		<li class="item policy"><a href="${ctx}/info/initPolicyList.do"><div class="icon"></div>�ҵı���</a></li>
		<li class="item order"><a href="${ctx }/order/orders.do"><div class="icon"></div>�ҵĶ���</a></li>
		<li class="item service"><a href="${ctx}/claims/initClaimProcess.do"><div class="icon"></div>�ҵ��ⰸ</a></li>
		<li class="item email"><a href="${ctx}/email/myEmailSubscribe.do"><div class="icon"></div>���Ӻ���</a></li>
		<li class="item account">
			<a href="${ctx}/edit/editUserPersonal.do"><div class="icon"></div>�˻���Ϣ</a>
			<div class="account">
				<ul>
					<li class="account_item"><a href="${ctx}/edit/editUserPersonal.do">��������</a></li>
					<li class="account_item"><a href="${ctx }/edit/accountInfo.do">�ʺ���Ϣ</a></li>
					<li class="account_item"><a href="${ctx }/insured/addInsured.do">���ñ�������</a></li>
					<li class="account_item"><a href="${ctx }/web/user/member/account/changePassword/index.jsp">�޸�����</a></li>
				</ul>
			</div>
		</li>
<%-- 		<li class="item activity"><a href="${ctx }/web/user/member/account/activityInfo/index.jsp"><div class="icon"></div>�ҵĻ</a></li> --%>
	</ul>
</div>