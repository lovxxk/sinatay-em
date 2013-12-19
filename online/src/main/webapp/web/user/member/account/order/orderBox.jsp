<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li class="at">会员首页</li>
			</ul>
		</div>
		<div class="member_main"><!-- item order -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>我的订单</p>
				</div>
				<div class="order_list">
					<!-- 
					<div class="order_item select">
						<div class="order_row">
							<div class="name">保单号码：</div>
							<div class="value">2013/08/20</div>
						</div>
						<div class="order_row">
							<div class="name">主险名称：</div>
							<div class="value">懒人理财宝懒人理财宝</div>
						</div>
						<div class="order_row">
							<div class="name">保险金额：</div>
							<div class="value">5000000.00</div>
						</div>
						<div class="order_row">
							<div class="name">生效日期：</div>
							<div class="value">2013/08/20</div>
						</div>
						<div class="order_row last">
							<div class="name">保单状态：</div>
							<div class="value">已支付</div>
						</div>
						<div class="policy_bottom operation">
							<a href="#">删除订单</a><div class="pay_action">支&nbsp;&nbsp;&nbsp;付</div>
						</div>
						<div class="close"></div>
					</div>
					 -->
					<div class="order_item add_order">
						<div class="order_action">
							<div class="action_icon"></div>
							<p>点击添加新订单</p>
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