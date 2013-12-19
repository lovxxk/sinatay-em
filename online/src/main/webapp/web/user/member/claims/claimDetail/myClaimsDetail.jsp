<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/memberCenter/homePage.do">会员首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/claims/initClaimProcess.do">理赔进度</a><span> &gt;</span></li>
				<li class="at">理赔详情</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">&nbsp;</div>
			</div>
			<div class="claims_info">
				<div class="claims_num">
					<span>案件号：${claimDetail.claimNumber }</span>
				</div>
			</div>
			<div class="info_area payout_info">
				<div class="title">
					<span class="title_name">投保人信息</span>
				</div>
				<div class="input_area">
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">事故者姓名：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.insuredName }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">赔案状态：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimState }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">出险时间：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accDate }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">申请时间：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.applyDate }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">出险地点：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accPlace }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">结案时间：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.closeDate }</div>
							</div>
						</div>
					</div>
					<div class="input_row">
						<div class="input_col">
							<div class="input_label">赔款金额：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimMoney }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">赔付结论：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.claimResult }</div>
							</div>
						</div>
					</div>
					<div class="input_row reason">
						<div class="input_col">
							<div class="input_label">出险原因：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.accReason }</div>
							</div>
						</div>
						<div class="input_col">
							<div class="input_label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
							<div class="input_text">
								<div class="text_show">${claimDetail.refuseReason }</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="info_area payout_detail">
				<div class="sub_title">
					<span class="title_name"> 赔款支付明细：</span>
					<div class="fold close"></div>
				</div>
				<c:forEach var="ClaimPayDetail"
					items="${claimDetail.listClaimPayDetail}" varStatus="status">
					<div class="fold_body">
						<div class="input_row gray">
							<div class="input_col">
								<div class="input_label">领款人姓名：</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">支付时间：</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.payDate }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">证件类型：</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverIDType }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">证件号码：</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.receiverID }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">支付方式：</div>
								<div class="input_text">
									<div class="text_show">${ClaimPayDetail.payType }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">领取金额：</div>
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