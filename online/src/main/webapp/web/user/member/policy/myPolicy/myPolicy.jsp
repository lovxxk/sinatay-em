<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
function downfile(filename) {
	$("#down_filename").val(filename);
	$("#down_from").submit();
}
</script>

<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>�ҵı���</p><a class="addPolicy">����ӱ���<span class="num">0</span></a>
				</div>
				<div class="policy_list">
					<c:forEach var="policyList" items="${listPolicyList}" varStatus="status">
						<c:if test="${status.count==1}">
							<div class="policy_item bind_policy select">
						</c:if>
						<c:if test="${status.count!=1}">
							<div class="policy_item bind_policy">
						</c:if>
							<div class="policy_row">
								<div class="name">�������룺</div>
								<div class="value pNo">${policyList.policySerialNumber}</div>
							</div>
							<div class="policy_row">
								<div class="name">�������ƣ�</div>
								<div class="value">${policyList.mainRiskName}</div>
							</div>
							<div class="policy_row" style="display: none">
								<div class="name">���ս�</div>
								<div class="value">${policyList.amount}</div>
							</div>
							<div class="policy_row">
								<div class="name">��Ч���ڣ�</div>
								<div class="value">${policyList.inceptionDate}</div>
							</div>
							<div class="policy_row last">
								<div class="name">����״̬��</div>
								<div class="value">${policyList.policyStatus}</div>
							</div>
							<div class="policy_bottom operation">
								<a  onclick="toDetail('${policyList.policySerialNumber}')">����</a>&nbsp;|&nbsp;
								<c:if test="${policyList.downloadString != null }">
									<a href="javascript:downfile('${policyList.downloadString}')">���ر���</a>&nbsp;|&nbsp;
								</c:if>
								<a  onclick="toDetail('${policyList.policySerialNumber}')">����</a>
							</div>
							<div class="close"></div>
						</div>
					</c:forEach>
					<!-- 
							<div class="policy_item select">
								<div class="policy_row">
									<div class="name">�������룺</div>
									<div class="value">2013/08/20</div>
								</div>
								<div class="policy_row">
									<div class="name">�������ƣ�</div>
									<div class="value">������Ʊ�������Ʊ�</div>
								</div>
								<div class="policy_row">
									<div class="name">���ս�</div>
									<div class="value">5000000.00</div>
								</div>
								<div class="policy_row">
									<div class="name">��Ч���ڣ�</div>
									<div class="value">2013/08/20</div>
								</div>
								<div class="policy_row last">
									<div class="name">����״̬��</div>
									<div class="value">��֧��</div>
								</div>
								<div class="policy_bottom operation">
									<a href="#">����</a>&nbsp;|&nbsp;<a href="#">���ر���</a>&nbsp;|&nbsp;<a href="#">����</a>
								</div>
								<div class="close"></div>
							</div>
							 -->
					<div class="policy_item add_policy_input">
						<div class="policy_action">
							<label class="policy_label">�����ţ�</label> <input
								class="policy_input" type="text" />
							<div class="add_btn click_btn">�������±���</div>
						</div>
						<div class="policy_bottom"></div>
					</div>
					<div class="policy_item add_policy select">
						<div class="policy_action">
							<div class="action_icon"></div>
							<p class="first">���Ƹ�����Ϣ</p>
							<p class="second">���뱣����</p>
						</div>
						<div class="policy_bottom">���б������</div>
					</div>
					<div class="policy_item buy_policy">
						<div class="policy_action">
							<div class="action_icon"></div>
							<p>�����±���</p>
						</div>
						<div class="policy_bottom"></div>
					</div>
					
					<form id="fm" method="post" name="fm" action="${ctx}/myPolicyDetail/myPolicyDetail.do" target="detailSubmit" >
						<div style="display: none">
							<input type="text" id="ctx" value="${ctx}" />
							<input type="text" id="hasPolicy" value="${hasPolicy}" />
							<input type="text" id="policyNo" name="policyNo" value="" />
						</div>
					</form>
				</div>
				<div class="page_index">
					<div class="page first">
						<a>��ҳ</a>
					</div>
					<div class="page prev_page">
						<a>��һҳ</a>
					</div>
					<div class="page next_page">
						<a>��һҳ</a>
					</div>
					<div class="page last">
						<a>βҳ</a>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>


<Form id="down_from" method="post" enctype="multipart/form-data" target="downloadSubmit" action="${ctx}/web/user/member/policy/myPolicy/download.jsp">
	<input type="hidden" id="down_filename" name="down_filename" />
</Form>
<iframe style="display:none" id="downloadSubmit" name="downloadSubmit" src="about:blank" ></iframe>