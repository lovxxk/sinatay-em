<%@ page language="java" contentType="text/html;charset=GBK"
	import="cn.com.sinosoft.ebusiness.member.emailManage.web.*"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
<!-- 				<li class="at">���Ӻ���</li> -->
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>���Ӻ���</p>
				</div>
				<div class="email_list">
					<div class="head">�Ѷ��ı���</div>
					<div class="content tip">
						<div class="content_col num">������</div>
						<div class="content_col name">��������</div>
						<div class="content_col email">�����ַ</div>
						<div class="content_col operation">����</div>
					</div>
					<c:forEach var="subEmailInfo" items="${subEmailInfos}"
						varStatus="status">
						<!-- Ĭ��ѡ���һ����ʽ -->
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
										<div class="to_edit">�޸�</div>
										<div class="save_edit">ȷ��</div>
										<div></div>
									</div>
								</div>
								<div class="content_col operation" id="cancle_sub">ȡ������</div>
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
										<div class="to_edit">�޸�</div>
										<div class="save_edit">ȷ��</div>
									</div>
								</div>
								<div class="content_col operation" id="cancle_sub">ȡ������</div>
							</div>

						</c:if>
					</c:forEach>
				</div>
				<!-- �����û��ֻ����� -->
				<div style="display:none;" id="mobile">${geUserPersonal.mobilePhone}</div>
				<c:if test="${notSubCount !=0}">
					<div class="no_subscribe">
						������<span class="num">${notSubCount}</span>�ű������Զ���<span
							class="subscribe">��˶���</span>
					</div>
				</c:if>
				<div class="query_email">
					<p>��ѯ���Ӻ�������������Լ����Ӻ������أ�</p>
					<form action="${ctx}/email/noticeSearch.do" method="post" onsubmit="return checkDate();"
						target="_blank">
						<div class="input_row email_type">
							<label class="input_label" for="email_type"><span
								class="name">��&nbsp;��&nbsp;��&nbsp;&nbsp;��&nbsp;��&nbsp;�ͣ�</span></label>
							<select name="noticeType" id="email_type" class="input_text">
								<option value="30">�ֺ�֪ͨ��</option>
								<option value="41">�Զ��潻֪ͨ��</option>
								<option value="29">���������ȡ֪ͨ��</option>
								<option value="BQ11">������Ԥ��ֹ֪ͨ��</option>
								<option value="01">��������ȱ���֪ͨ��</option>
								<option value="BQ17">���汣�ս���ȡ֪ͨ��</option>
							</select>
						</div>
						<div class="input_row send_date">
							<label class="input_label" for="send_date"><span
								class="name">���Ӻ����������ڣ�</span></label> <input class="input_text Wdate"
								name="startDate" type="text" id="date_start" /> <label
								class="tip_label">��</label> <input class="input_text Wdate"
								name="endDate" type="text" id="date_end" />
						</div>
						<div class="action">
							<button class="query click_btn" type="submit">��&nbsp;&nbsp;&nbsp;&nbsp;ѯ</button>
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