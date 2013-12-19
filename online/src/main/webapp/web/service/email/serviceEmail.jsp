<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
<!-- 				<li class="at">电子函件</li> -->
			</ul>
		</div>
		<div class="service_main">
			<div class="left_dynamic">
				<jsp:include page="/web/service/common/service_map.jsp"></jsp:include>
				<jsp:include page="/web/service/common/call_service.jsp"></jsp:include>
				<jsp:include page="/web/service/common/interest_notice.jsp"></jsp:include>
				<jsp:include page="/web/product/common/product_rank_left.jsp"></jsp:include>
			</div>
			<div class="right_content">
				<jsp:include page="../common/service_head.jsp"></jsp:include>
				<div class="service_area">
					<div class="title">
						<p>电子函件</p>
					</div>
					<div class="service_question">
						<div class="whatis">
							<div class="question">什么是电子函件？</div>
							<div class="answer">类似于银行的电子账单，它是将客户的十余种通知类函件保存在信泰官网上，客户可随时登陆官网会员中心了解最新及历史函件信息，同时信泰保险还会通过电子邮件形式发送函件的电子版给客户的一种保单信息服务。</div>
						</div>
						<div class="benefit">
							<div class="question">电子函件有什么好处？</div>
							<div class="answer">
								<p class="p1">第一时间了解函件信息，无需苦苦守候</p>
								<p class="p2">随时随地网上查询最新及历史函件信息，还能 将历史函件重发至自己的邮箱</p>
								<p class="p3">电子函件误删不用怕，随时调阅，轻松打理， 避免了纸质信函被他人恶意拆看的风险</p>
								<p class="p4">电子函件替代纸张信函的邮寄，可以节省纸张，为保护我们赖以生存的自然环境尽一份力</p>
							</div>
						</div>
					</div>
					<p class="service_topic">如何开通电子函件？</p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">您可以使用账号（手机、邮箱）、身份证或者第三方（支付宝、QQ、微博）登录会员中心</p>
									<p class="link">
										<c:if test="${geUserPersonal == null }">
											<a href="${ctx }/web/user/login/index.jsp">立即登录</a>
										</c:if>
										<c:if test="${geUserPersonal != null }">
											<a href="${ctx }/memberCenter/homePage.do">会员中心</a>
										</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="process_step right subscribe">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">登录会员中心在“电子函件”中输入您的电子邮箱，点击立即订阅确定订阅电子函件。</p>
									<p class="link">
										<a href="${ctx }/email/myEmailSubscribe.do">电子函件</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<p class="service_topic">支持哪些电子函件类型？</p>
					<p class="service_answer">分红通知书、生存保险金领取通知书、万能险个单年度报告、增额红利领取通知书、自垫通知书、万能险预中止通知书。</p>
					
				</div>
			</div>
		</div>
	</div>
</div>