<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
			<div class="h_layout">
				<div class="nav_index">
					<ul>
						<li><a href="${ctx }">首页</a><span> &gt;</span></li>
						<li class="at">会员首页</li>
					</ul>
				</div>
				<div class="member_main"><!-- item email -->
					<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
					<div class="right_content">
						<div class="title">
							<div class="block"></div>
							<p>电子函件订阅</p>
						</div>
						<p class="p1">通过电子邮箱接收《保单年度报告》、《万能年度报告》等函件，比传统的邮寄方式更高效、更便捷，同时低碳环保， 您可以随时通过登录信泰保险官网查询和管理您的函件。该服务适用于您名下的所有信泰保险的保单。</p>
						<p class="p2">欢迎您订阅电子函件，请您先提供接收各类电子函件的邮箱，</p>
						<p class="p2">订阅成功后，我们将向您每份保单提供的邮箱分别寄发相对于的电子函件。</p>
						
						<div class="email_list">
							<div class="head">所有保单</div>
							<div class="no_policy"><p>还没有保单<a href="${ctx }/info/initPolicyList.do">去添加保单&gt;&gt;</a></p></div>
						</div>
						
						<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>