<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('.content').hover(function() {
			$(this).addClass('select');
			}, 
			function() {
				$(this).removeClass('select');
		});
	})
	function searchRewardResult() {
		new Sinosoft.InteractiveDialog({
			layout : bindSuccess(),
			width:410,//自定义面板宽度-根据设计来调整
			cancelBtnShow:false,//是否显示关闭按钮
			okStr : '确认',
			cancelStr : '取消',
			okFunc:function(){
				
			}
		}).open();
	}
	
	function bindSuccess(){
		var subSuccess = $('<div class="alert_subscribe">'
				+'<div class="success"></div><div class="main_content">'
				+'<div class="sub_txt">恭喜您获得了一台Iphone5S</div></div></div>');
		return subSuccess;
	}
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li><a href="#">会员首页</a><span> &gt;</span></li>
				<li class="at">我的活动</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>活动管理</p>
				</div>
				<div class="email_list">
					<div class="headcontent">
						<div class="head num">活动名称</div>
						<div class="head name">活动日期</div>
						<div class="head email">活动明细</div>
						<div class="head operation">操作</div>
					</div>
					<div class="content">
						<div class="content_col num">双十一抽奖活动名字名字</div>
						<div class="content_col name">2012/11/11-2013/11/11</div>
						<div class="content_col email">已抽奖</div>
						<div class="content_col operation">
							<a href="javascript:searchRewardResult()">查看中奖结果</a>
						</div>
					</div>
					<div class="content select">
						<div class="content_col num">双十一抽奖活动</div>
						<div class="content_col name">2012/11/11-2013/11/11</div>
						<div class="content_col email">未抽奖</div>
						<div class="content_col operation">
							<a href="#" class="gohappy"></a>
						</div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
					<div class="content">
						<div class="content_col num"></div>
						<div class="content_col name"></div>
						<div class="content_col email"></div>
						<div class="content_col operation"></div>
					</div>
				</div>
				<div class="titleblock"></div>
				<div class="title">
					<div class="block"></div>
					<p>活动推荐</p>
				</div>
				<div class="product_activity_img">
					<div class="activity_img"></div>
				</div>

				<div class="title">
					<div class="block"></div>
					<p>热销产品</p>
					<div class="more">
						<a href="#">更多&gt;&gt;</a>
					</div>
				</div>
				<div class="product_list">
					<div class="product"></div>
					<div class="product"></div>
					<div class="product"></div>
				</div>
			</div>
		</div>
	</div>
</div>