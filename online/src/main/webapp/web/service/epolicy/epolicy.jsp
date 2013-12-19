<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% 
System.out.println(request.getContextPath());

%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">电子保单</li>
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
						<p>电子保单</p>
					</div>
					<div class="service_question">
						<div class="whatis">
							<div class="question">什么是电子保单？</div>
							<div class="answer">
								<p>您通过网络购买我公司的产品，我们为您提供电子保单，电子保单与纸质保单具有同等的法律效力。</p>
								<p>电子保单由权威第三方电子认证机构——北京数字认证股份有限公司（BJCA）提供数据签名技术，经过签名的电子保单具有唯一性、不可伪造的特点，并且可以防止保单内容被非法篡改。</p></div>
						</div>
						<div class="benefit">
							<div class="question">电子保单具有什么特性？</div>
							<div class="answer">
							</div>
						</div>
					</div>
					<p class="service_topic">如何获得我的电子保单？ </p>
					<div class="process_list">
						<div class="process_step right login">
							<div class="step_bar">
								<div class="line"></div>
								<div class="ball"></div>
							</div>
							<div class="step_info">
								<div class="step_title"></div>
								<div class="step_content">
									<p class="instro">付款成功后，我们会发送投保成功的邮件，邮件中包括电子保单的下载地址</p>
									
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
									<p class="instro">登录会员中心在“我的保单”中查看保单详情和下载电子保单</p>
									<p class="link"><a href="${ctx}/info/initPolicyList.do">我的保单</a></p>
								</div>
							</div>
						</div>
					</div>
					<p class="service_topic">电子保单如何验证? </p>
					<p class="service_answer">通过网站 <a href="http://etrust.bjca.org.cn/tdmm" target="_blank">http://etrust.bjca.org.cn/tdmm</a> 验证电子保单的真伪。登录该网站后上传电子保单，即可完成电子保单的验证。</p>
				</div>
			</div>
		</div>
	</div>
</div>
		