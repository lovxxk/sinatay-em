<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>���Ӻ�������</p>
				</div>
				<p class="p1">ͨ������������ա�������ȱ��桷����������ȱ��桷�Ⱥ������ȴ�ͳ���ʼķ�ʽ����Ч������ݣ�ͬʱ��̼������
					��������ʱͨ����¼��̩���չ�����ѯ�͹������ĺ������÷��������������µ�������̩���յı�����</p>
				<p class="p2">��ӭ�����ĵ��Ӻ������������ṩ���ո�����Ӻ��������䣬</p>
				<p class="p2">���ĳɹ������ǽ�����ÿ�ݱ����ṩ������ֱ�ķ�����ڵĵ��Ӻ�����</p>
				<div class="email_list">
					<div class="head">δ���ı���</div>
					<c:if test="${notSubCount ==0 }">
						<div class="no_policy">
							<p class="calSub">
								û�б������ĵ��Ӻ�����Ϣ<a href="${ctx }/email/myEmailSubscribe.do">ȥȡ������&gt;&gt;</a>
							</p>
						</div>
					</c:if>
					<c:if test="${notSubCount !=0 }">
						<div class="content tip">
							<div class="content_col num">������</div>
							<div class="content_col name">��������</div>
							<div class="content_col email">�����ַ</div>
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
										<div class="to_edit">����</div>
									</c:if>
									<c:if
										test="${notSubEmailInfo.email !=null && notSubEmailInfo.email !=''}">
										<div class="to_edit">�޸�</div>
									</c:if>

									<div class="save_edit">ȷ��</div>
								</div>
							</div>
				</div>

				</c:forEach>

				</c:if>
			</div>
			<c:if test="${subCount != 0}">
				<div class="no_subscribe">
					����<span class="num">${subCount}</span>�ű����Ѷ��ĵ��Ӻ���<span
						class="subscribe" id="cancle">���ȡ������</span>
				</div>
			</c:if>
			<div class="action">
				<c:if test="${notSubCount!=0 }">
					<div class="subscribe click_btn">��������</div>
				</c:if>
				<c:if test="${notSubCount==0 }">
					<div class="subscribe back" id="backSub">��&nbsp;&nbsp;��</div>
				</c:if>
			</div>
			<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			
		</div>
		
	</div>
</div>
</div>
</div>