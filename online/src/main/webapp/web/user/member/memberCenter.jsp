<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript">
var bindPolicy = '${bindPolicy }';
function toDetail(pNo){
	$('#policyNo').val(pNo);
	$('#fm').submit();
}

function downfile(filename) {
	$("#down_filename").val(filename);
	$("#down_from").submit();
}
</script>
<div class="middle">	
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main">
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<input type="hidden" id="bindPolicy" value="${bindPolicy }">
			<div class="right_content">
				<div class="personal">
					<div class="welcome"><span class="name">
						<c:choose>
							<c:when test="${!empty geUserPersonal.userName }">
								${geUserPersonal.userName }
							</c:when>
							<c:when test="${!empty geUserPersonal.alias }">
								${geUserPersonal.alias }
							</c:when>
							<c:otherwise>
								${geUserPersonal.userAccount }
							</c:otherwise>
						</c:choose>
					</span>�����ã�</div>
					<a class="fill_info" href="${ctx}/edit/editUserPersonal.do">���������Ϣ</a>
				</div>
				<p class="events">
					<a class="first" href="${ctx }/order/orders.do?status=82">��֧��<span class="num">${unPaidCount }</span></a>
					<a href="${ctx }/order/orders.do?status=10">���ύ<span class="num">${quoteMainCount }</span></a>
					<a class="addPolicy">����ӱ���<span class="num">0</span></a>
					<a class="last" href="${ctx }/insured/addInsured.do">���ñ������˹���</a>
				</p>
				<div class="remind_list" style="display: none;">
					<p class="new">��������</p>
					<p class="remind">����2012/8/30�����Ͷ���˻�XXXX�����Ͷ���˻�X�����Ͷ���˻�������ʲôʲô�䶯��</p>
					<p class="remind">����2012/8/30�����Ͷ���˻�XXXX���Ѿ�ȷ���������˻���</p>
				</div>
				<div class="title">
					<div class="block"></div>
					<p>�ҵĶ���</p>
					<div class="more"><a href="${ctx }/order/orders.do">����&gt;&gt;</a></div>
				</div>
				<c:if test="${empty twoOrderForms}">
					<p class="no_data">���޶���</p>
				</c:if>
				<c:if test="${!empty twoOrderForms}">
					<div class="order_list">
					<c:forEach items="${twoOrderForms }" var="order">
						<div class="order_item">
							<div class="order_info">
								<div class="name">${order.insurancePolicy.productName }</div>
								<div class="detail">${order.orderSerialNumber }</div>
							</div>
							<c:choose>
								<c:when test="${order.insurancePolicy.policyStatus == 1 || order.insurancePolicy.policyStatus == 3}">
								<div class="order_status">
									<div class="status current">
										<p>����д</p>
										<p>��Ϣ</p>
									</div>
									<div class="arrow"></div>
									<div class="status">
										<p>��ȷ��</p>
										<p>����</p>
									</div>
									<div class="arrow"></div>
									<div class="status one_line">��֧��</div>
									<div class="arrow"></div>
									<div class="status one_line">�ѳб�</div>
								</div>
								</c:when>
								<c:when test="${order.insurancePolicy.policyStatus == 2 && order.orderStatus == 82 }">
									<div class="order_status">
										<div class="status">
											<p>����д</p>
											<p>��Ϣ</p>
										</div>
										<div class="arrow"></div>
										<div class="status current">
											<p>��ȷ��</p>
											<p>����</p>
										</div>
										<div class="arrow"></div>
										<div class="status one_line">��֧��</div>
										<div class="arrow"></div>
										<div class="status one_line">�ѳб�</div>
									</div>
								</c:when>
								<c:when test="${order.orderStatus == 84 }">
									<div class="order_status">
										<div class="status">
											<p>����д</p>
											<p>��Ϣ</p>
										</div>
										<div class="arrow"></div>
										<div class="status">
											<p>��ȷ��</p>
											<p>����</p>
										</div>
										<div class="arrow"></div>
										<div class="status one_line current">��֧��</div>
										<div class="arrow"></div>
										<div class="status one_line">�ѳб�</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="order_status">
										<div class="status">
											<p>����д</p>
											<p>��Ϣ</p>
										</div>
										<div class="arrow"></div>
										<div class="status">
											<p>��ȷ��</p>
											<p>����</p>
										</div>
										<div class="arrow"></div>
										<div class="status one_line">��֧��</div>
										<div class="arrow"></div>
										<div class="status one_line current">�ѳб�</div>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="order_btn click_btn">
								<a href="${ctx }/order/orderDetail.do?id=${order.serialNo }" target="_blank">�鿴����</a>
							</div>
						</div>
					</c:forEach>
					</div>
				</c:if>
				<div class="title">
					<div class="block"></div>
					<p>�ҵı���</p>
					<div class="more"><a href="${ctx }/info/initPolicyList.do">����&gt;&gt;</a></div>
				</div>
				<div class="policy_list">
					<div class="load_gif"><img src="${ctx}/resources/image/user/member/st_loading.gif"/></div>
					<!-- <c:forEach var="policyList" items="${policyList}" varStatus="status">
						<div class="policy_item bind_policy">
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
								<a href="javascript:toDetail('${policyList.policySerialNumber}')">����</a>&nbsp;|&nbsp;
								<c:if test="${policyList.downloadString != null }">
									<a href="javascript:downfile('${policyList.downloadString}')">���ر���</a>&nbsp;|&nbsp;
								</c:if>
								<a href="javascript:toDetail('${policyList.policySerialNumber}')">����</a>
							</div>
						</div>
					</c:forEach> -->
				<!-- <div class="policy_list">
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
					<div class="policy_item">
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
					<div class="policy_item">
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
				</div> -->
			</div>
			<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
		</div>
	</div>
</div>
</div>

<form id="fm" method="post" name="fm" action="${ctx}/myPolicyDetail/myPolicyDetail.do" target="detailSubmit" >
	<div style="display: none">
		<input type="text" id="ctx" value="${ctx}" />
		<input type="text" id="hasPolicy" value="${hasPolicy}" />
		<input type="text" id="policyNo" name="policyNo" value="" />
	</div>
</form>

<Form id="down_from" method="post" enctype="multipart/form-data" target="downloadSubmit" action="${ctx}/web/user/member/policy/myPolicy/download.jsp">
	<input type="hidden" id="down_filename" name="down_filename" />
</Form>
<iframe style="display:none" id="downloadSubmit" name="downloadSubmit" src="about:blank" ></iframe>