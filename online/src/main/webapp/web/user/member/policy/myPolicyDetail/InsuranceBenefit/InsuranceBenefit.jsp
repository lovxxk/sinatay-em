<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>

<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">会员首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/info/initPolicyList.do">我的保单</a><span> &gt;</span></li>
				<li><a href="${ctx}/myPolicyDetail/myPolicyDetail.do?policyNo=${insuranceBenefitInfo.policyNo}">保单详情</a><span> &gt;</span></li>
				<li class="at">保险利益</li>
			</ul>
		</div>
		<div class="email_list">
			<div class="head">保险利益表</div>
			<div class="content tip">
				<div class="content_col num">保单年度</div>
				<div class="content_col num">疾病身故保险金</div>
				<div class="content_col num">意外身故保险金</div>
				<div class="content_col num">年末生存金</div>


			</div>
			<c:forEach var="valueInfo" items="${insuranceBenefitInfo.valueInfos }">
				<div class="content">
					<c:forEach var="value" items="${valueInfo.values}"
						varStatus="status">
						<div class="content_col num">${value}&nbsp;&nbsp;&nbsp;&nbsp;</div>
					</c:forEach>
				</div>
			</c:forEach>




		</div>


	</div>
</div>

