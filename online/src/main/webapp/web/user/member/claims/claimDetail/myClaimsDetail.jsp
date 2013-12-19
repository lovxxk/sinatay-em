<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/memberCenter/homePage.do">��Ա��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/claims/initClaimProcess.do">�������</a><span> &gt;</span></li>
				<li class="at">��������</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">&nbsp;</div>
			</div>
			<div class="claims_info">
				<div class="claims_num">
					<span>�����ţ�${claimDetail.claimNumber }</span>
				</div>
			</div>
			<div class="info_area payout_info">
				<div class="title">
					<span class="title_name">Ͷ������Ϣ</span>
				</div>
				<div class="input_area">
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">�¹���������</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.insuredName }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">�ⰸ״̬��</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimState }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">����ʱ�䣺</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accDate }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">����ʱ�䣺</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.applyDate }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">���յص㣺</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accPlace }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">�᰸ʱ�䣺</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.closeDate }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">����</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimMoney }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">�⸶���ۣ�</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimResult }</div>
							</div>
						</div>
					</div>
					<div class="input_row reason">
						<div class="input_col">
							<div class="input_label">����ԭ��</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accReason }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע��</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.refuseReason }</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="info_area payout_detail">
				<div class="sub_title">
					<span class="title_name"> ���֧����ϸ��</span>
					<div class="fold close"></div>
				</div>
				<c:forEach var="ClaimPayDetail"
					items="${claimDetail.listClaimPayDetail}" varStatus="status">
					<div class="fold_body">
						<div class="input_row gray">
							<div class="input_col">
								<div class="input_label">�����������</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">֧��ʱ�䣺</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.payDate }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֤�����ͣ�</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverIDType }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">֤�����룺</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverID }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֧����ʽ��</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.payType }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">��ȡ��</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiveMoney }</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>