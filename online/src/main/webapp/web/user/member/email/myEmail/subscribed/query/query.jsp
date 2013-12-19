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
		<div class="member_main">
			<!-- item service -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>���Ӻ�����ѯ</p>
				</div>
				<div class="email_list">
					<div class="head">��ѯ���</div>
					<div class="content tip">
						<div class="content_col time">����ʱ��</div>
						<div class="content_col num">������</div>
						<div class="content_col name">��������</div>
						<div class="content_col email">���Ӻ�������</div>
						<div class="content_col operation2">����</div>
					</div>
					<div id="noticeInfoList">

						<!-- ��ʾ��ѯ֪ͨ����Ϣ������ -->
						<c:if test="${fn:length(noticeInfos) ==0 }">
							<div class="no_notice">
								<p>��ѯʧ�ܣ���û��֪ͨ����Ϣ��</p>
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
														id="down">����&nbsp;</a>
												</c:if>
												<!-- �������������ȱ��棬��Ҫ�������ţ�policyNo����ʼ���ڣ�startDate���������ڣ�endDate�� -->
												<c:if test="${noticeInfo.noticeType =='01'}">
													<a href=""
														onclick="checkW('${noticeInfo.policyNo}');return false;"
														id="down">����</a>
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
														id="down">����&nbsp;</a>
												</c:if>
												<!-- �������������ȱ��棬��Ҫ�������ţ�policyNo����ʼ���ڣ�startDate���������ڣ�endDate�� -->
												<c:if test="${noticeInfo.noticeType =='01'}">
													<a href=""
														onclick="checkW('${noticeInfo.policyNo}');return false;"
														id="down">����&nbsp;</a>
												</c:if>
											</div>
										</div>
									</c:if>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<!-- ���ؿ�ʼ������������� -->

					<div style="display: none;" id="startDate">${startDate }</div>
					<div style="display: none;" id="endDate">${endDate }</div>
					<!-- ��ҳҳ�� -->
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