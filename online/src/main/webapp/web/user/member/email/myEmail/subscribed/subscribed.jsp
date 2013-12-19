<%@ page language="java" contentType="text/html;charset=GBK"
	import="cn.com.sinosoft.ebusiness.member.emailManage.web.*"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">首页</a><span> &gt;</span></li>
				<li class="at">会员首页</li>
<!-- 				<li class="at">电子函件</li> -->
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>电子函件</p>
				</div>
				<div class="email_list">
					<div class="head">已订阅保单</div>
					<div class="content tip">
						<div class="content_col num">保单号</div>
						<div class="content_col name">险种名称</div>
						<div class="content_col email">邮箱地址</div>
						<div class="content_col operation">操作</div>
					</div>
					<c:forEach var="subEmailInfo" items="${subEmailInfos}"
						varStatus="status">
						<!-- 默认选择第一行样式 -->
						<c:if test="${status.index==0 }">
							<div class="content select">
								<div class="content_col num" id="policyNo">${subEmailInfo.policyNo
									}</div>
								<div class="content_col name">${subEmailInfo.riskName }</div>

								<div class="content_col email">
									<input style="display: none;" id="subType"
										value="${subEmailInfo.subType}" /> <input
										style="display: none;" id="policyNo"
										value="${subEmailInfo.policyNo}" />
									<div class="edit">
										<div class="text_show">${subEmailInfo.email}</div>
										<input class="edit_input" name="email" type="text" />
									</div>
									<div class="edit_btn">
										<div class="to_edit">修改</div>
										<div class="save_edit">确定</div>
										<div></div>
									</div>
								</div>
								<div class="content_col operation" id="cancle_sub">取消订阅</div>
							</div>
						</c:if>
						<c:if test="${status.index!=0 }">
							<div class="content">
								<div class="content_col num" id="policyNo">${subEmailInfo.policyNo
									}</div>
								<div class="content_col name">${subEmailInfo.riskName }</div>

								<div class="content_col email">
									<input style="display: none;" id="subType"
										value="${subEmailInfo.subType}" /> <input
										style="display: none;" id="policyNo"
										value="${subEmailInfo.policyNo}" />
									<div class="edit">
										<div class="text_show">${subEmailInfo.email }</div>
										<input class="edit_input" name="email" type="text" />
									</div>
									<div class="edit_btn">
										<div class="to_edit">修改</div>
										<div class="save_edit">确定</div>
									</div>
								</div>
								<div class="content_col operation" id="cancle_sub">取消订阅</div>
							</div>

						</c:if>
					</c:forEach>
				</div>
				<!-- 隐藏用户手机号码 -->
				<div style="display:none;" id="mobile">${geUserPersonal.mobilePhone}</div>
				<c:if test="${notSubCount !=0}">
					<div class="no_subscribe">
						您还有<span class="num">${notSubCount}</span>张保单可以订阅<span
							class="subscribe">点此订阅</span>
					</div>
				</c:if>
				<div class="query_email">
					<p>查询电子函件发送情况，以及电子函件下载：</p>
					<form action="${ctx}/email/noticeSearch.do" method="post" onsubmit="return checkDate();"
						target="_blank">
						<div class="input_row email_type">
							<label class="input_label" for="email_type"><span
								class="name">电&nbsp;子&nbsp;函&nbsp;&nbsp;件&nbsp;类&nbsp;型：</span></label>
							<select name="noticeType" id="email_type" class="input_text">
								<option value="30">分红通知书</option>
								<option value="41">自动垫交通知书</option>
								<option value="29">增额红利领取通知书</option>
								<option value="BQ11">万能险预中止通知书</option>
								<option value="01">万能险年度报告通知书</option>
								<option value="BQ17">生存保险金领取通知书</option>
							</select>
						</div>
						<div class="input_row send_date">
							<label class="input_label" for="send_date"><span
								class="name">电子函件发送日期：</span></label> <input class="input_text Wdate"
								name="startDate" type="text" id="date_start" /> <label
								class="tip_label">至</label> <input class="input_text Wdate"
								name="endDate" type="text" id="date_end" />
						</div>
						<div class="action">
							<button class="query click_btn" type="submit">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
						</div>
					</form>
				</div>
							<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
						
						<input type="text" id="ctx" value="${ctx}" style="display:none;"/>
						<input type="text" id="ctx" value="${geUserPersonal.userID}" style="display:none;"/>

			</div>
		</div>
	</div>
</div>