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
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>电子函件订阅</p>
				</div>
				<p class="p1">通过电子邮箱接收《保单年度报告》、《万能年度报告》等函件，比传统的邮寄方式更高效、更便捷，同时低碳环保，
					您可以随时通过登录信泰保险官网查询和管理您的函件。该服务适用于您名下的所有信泰保险的保单。</p>
				<p class="p2">欢迎您订阅电子函件，请您先提供接收各类电子函件的邮箱，</p>
				<p class="p2">订阅成功后，我们将向您每份保单提供的邮箱分别寄发相对于的电子函件。</p>
				<div class="email_list">
					<div class="head">未订阅保单</div>
					<c:if test="${notSubCount ==0 }">
						<div class="no_policy">
							<p class="calSub">
								没有保单订阅电子函件信息<a href="${ctx }/email/myEmailSubscribe.do">去取消订阅&gt;&gt;</a>
							</p>
						</div>
					</c:if>
					<c:if test="${notSubCount !=0 }">
						<div class="content tip">
							<div class="content_col num">保单号</div>
							<div class="content_col name">险种名称</div>
							<div class="content_col email">邮箱地址</div>
						</div>
						<c:forEach var="notSubEmailInfo" items="${notSubEmailInfos }"
							varStatus="status">

							<c:if test="${status.index ==0 }">
								<div class="content select">
							</c:if>
							<c:if test="${status.index!=0 }">
								<div class="content">
							</c:if>
							<div class="content_col num">
								<div class="checkbox" id='${status.index }'></div>

								<div id="policyNo${status.index }">${notSubEmailInfo.policyNo}</div>

							</div>
							<div class="content_col name">${notSubEmailInfo.riskName }</div>
							<div class="content_col email">
								<div class="edit">
									<div class="text_show" id="email${status.index }">${notSubEmailInfo.email}</div>
									<input class="edit_input" name="email" type="text" />
								</div>
								<div class="edit_btn">
									<c:if
										test="${notSubEmailInfo.email ==null||notSubEmailInfo.email ==''}">
										<div class="to_edit">补充</div>
									</c:if>
									<c:if
										test="${notSubEmailInfo.email !=null && notSubEmailInfo.email !=''}">
										<div class="to_edit">修改</div>
									</c:if>

									<div class="save_edit">确定</div>
								</div>
							</div>
				</div>

				</c:forEach>

				</c:if>
			</div>
			<c:if test="${subCount != 0}">
				<div class="no_subscribe">
					您有<span class="num">${subCount}</span>张保单已订阅电子函件<span
						class="subscribe" id="cancle">点此取消订阅</span>
				</div>
			</c:if>
			<div class="action">
				<c:if test="${notSubCount!=0 }">
					<div class="subscribe click_btn">立即订阅</div>
				</c:if>
				<c:if test="${notSubCount==0 }">
					<div class="subscribe back" id="backSub">返&nbsp;&nbsp;回</div>
				</c:if>
			</div>
			<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			
		</div>
		
	</div>
</div>
</div>
</div>