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
		<div class="member_main">
			<!-- item service -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>电子函件查询</p>
				</div>
				<div class="email_list">
					<div class="head">查询结果</div>
					<div class="content tip">
						<div class="content_col time">发送时间</div>
						<div class="content_col num">保单号</div>
						<div class="content_col name">险种名称</div>
						<div class="content_col email">电子函件类型</div>
						<div class="content_col operation2">操作</div>
					</div>
					<div id="noticeInfoList">

						<!-- 提示查询通知书信息不存在 -->
						<c:if test="${fn:length(noticeInfos) ==0 }">
							<div class="no_notice">
								<p>查询失败！还没有通知书信息！</p>
							</div>
						</c:if>

						<c:if test="${fn:length(noticeInfos)!=0 }">
							<c:forEach var="noticeInfo" items="${noticeInfos }"
								varStatus="status">
								<div class="notices_item">
									<c:if test="${status.index ==0 }">
										<div class="content select">
											<div class="content_col time">${noticeInfo.sendDate}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col num">${noticeInfo.policyNo}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col name">${noticeInfo.riskName}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col email">${noticeInfo.noticeTypeName}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col operation">
												<c:if test="${noticeInfo.noticeType !='01'}">
													<a href=""
														onclick="check('${noticeInfo.noticeNo}');return false;"
														id="down">下载&nbsp;</a>
												</c:if>
												<!-- 如果是万能险年度报告，需要传保单号（policyNo）开始日期（startDate）结束日期（endDate） -->
												<c:if test="${noticeInfo.noticeType =='01'}">
													<a href=""
														onclick="checkW('${noticeInfo.policyNo}');return false;"
														id="down">下载</a>
												</c:if>
											</div>
										</div>
									</c:if>
									<c:if test="${status.index != 0 }">
										<div class="content">
											<div class="content_col time">${noticeInfo.sendDate}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col num">${noticeInfo.policyNo}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col name">${noticeInfo.riskName}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col email">${noticeInfo.noticeTypeName}&nbsp;&nbsp;&nbsp;&nbsp;</div>
											<div class="content_col operation">
												<c:if test="${noticeInfo.noticeType !='01'}">
													<a href=""
														onclick="check('${noticeInfo.noticeNo}');return false;"
														id="down">下载&nbsp;</a>
												</c:if>
												<!-- 如果是万能险年度报告，需要传保单号（policyNo）开始日期（startDate）结束日期（endDate） -->
												<c:if test="${noticeInfo.noticeType =='01'}">
													<a href=""
														onclick="checkW('${noticeInfo.policyNo}');return false;"
														id="down">下载&nbsp;</a>
												</c:if>
											</div>
										</div>
									</c:if>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<!-- 隐藏开始日期与结束日期 -->

					<div style="display: none;" id="startDate">${startDate }</div>
					<div style="display: none;" id="endDate">${endDate }</div>
					<!-- 分页页码 -->
					<div class="page_index">
						<div class="page prev_page">
							<a>&nbsp;</a>
						</div>
						<div class="page next_page">
							<a>&nbsp;</a>
						</div>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>