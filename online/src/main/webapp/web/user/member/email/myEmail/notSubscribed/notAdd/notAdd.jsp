<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
			<div class="h_layout">
				<div class="nav_index">
					<ul>
						<li><a href="${ctx }">��ҳ</a><span> &gt;</span></li>
						<li class="at">��Ա��ҳ</li>
					</ul>
				</div>
				<div class="member_main"><!-- item email -->
					<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
					<div class="right_content">
						<div class="title">
							<div class="block"></div>
							<p>���Ӻ�������</p>
						</div>
						<p class="p1">ͨ������������ա�������ȱ��桷����������ȱ��桷�Ⱥ������ȴ�ͳ���ʼķ�ʽ����Ч������ݣ�ͬʱ��̼������ ��������ʱͨ����¼��̩���չ�����ѯ�͹������ĺ������÷��������������µ�������̩���յı�����</p>
						<p class="p2">��ӭ�����ĵ��Ӻ������������ṩ���ո�����Ӻ��������䣬</p>
						<p class="p2">���ĳɹ������ǽ�����ÿ�ݱ����ṩ������ֱ�ķ�����ڵĵ��Ӻ�����</p>
						
						<div class="email_list">
							<div class="head">���б���</div>
							<div class="no_policy"><p>��û�б���<a href="${ctx }/info/initPolicyList.do">ȥ��ӱ���&gt;&gt;</a></p></div>
						</div>
						
						<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>