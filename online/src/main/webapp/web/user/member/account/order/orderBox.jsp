<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main"><!-- item order -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>�ҵĶ���</p>
				</div>
				<div class="order_list">
					<!-- 
					<div class="order_item select">
						<div class="order_row">
							<div class="name">�������룺</div>
							<div class="value">2013/08/20</div>
						</div>
						<div class="order_row">
							<div class="name">�������ƣ�</div>
							<div class="value">������Ʊ�������Ʊ�</div>
						</div>
						<div class="order_row">
							<div class="name">���ս�</div>
							<div class="value">5000000.00</div>
						</div>
						<div class="order_row">
							<div class="name">��Ч���ڣ�</div>
							<div class="value">2013/08/20</div>
						</div>
						<div class="order_row last">
							<div class="name">����״̬��</div>
							<div class="value">��֧��</div>
						</div>
						<div class="policy_bottom operation">
							<a href="#">ɾ������</a><div class="pay_action">֧&nbsp;&nbsp;&nbsp;��</div>
						</div>
						<div class="close"></div>
					</div>
					 -->
					<div class="order_item add_order">
						<div class="order_action">
							<div class="action_icon"></div>
							<p>�������¶���</p>
						</div>
						<div class="order_bottom">
							
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>