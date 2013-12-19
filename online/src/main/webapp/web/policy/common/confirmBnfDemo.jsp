<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% int bnfCount = 0; %>
<div id="confirmbnfDemo" style="display: none;">
	<div tag="confirmBnf"></div>
	<c:set var="bnf" value="${geSaleProduct.geSaleProBeneficiaryConfigs[0] }"></c:set>
	<div class="title">受益人信息</div>
	<c:choose>
		<c:when test="${not empty bnf }">
			<c:if test="${geSaleProduct.isSupportBeneficiary eq 0}">
				<div class="info_row">
					<div class="info_col">
						<div class="name">与被保人关系：</div>
						<div class="value" id="cfm_bnfRelationToPIns">法定</div>
					</div>
				</div>
			</c:if>
			<c:if test="${geSaleProduct.isSupportBeneficiary eq 1}">
				<div class="info_row">
					<c:if test="${bnf.benRelationToPIns eq 1 ||bnf.benRelationToPIns eq 2}">
						<div class="info_col">
							<div class="name">与被保人关系：</div>
							<div class="value" id="cfm_bnfRelationToPIns#index"></div>
						</div>
					</c:if>
				</div>
			</c:if>
			<div class="info_row">
				<c:if test="${bnf.benName eq 1 ||bnf.benName eq 2}">
					<div class="info_col">
						<div class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
						<div class="value" id="cfm_bnfName#index"></div>
					</div>
				</c:if>
			</div><div class="info_row">
				<c:if test="${bnf.benGender eq 1 ||bnf.benGender eq 2}">
					<div class="info_col">
						<div class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
						<div class="value" id="cfm_bnfSex#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benIdType eq 1 ||bnf.benIdType eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col right_col">
						<div class="name">证件类型：</div>
						<div class="value" id="cfm_bnfIdType#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benIdNumber eq 1 ||bnf.benIdNumber eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col">
						<div class="name">证件号码：</div>
						<div class="value" id="cfm_bnfIdNo#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benBirthday eq 1 ||bnf.benBirthday eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col right_col">
						<div class="name">出生日期：</div>
						<div class="value" id="cfm_bnfBirthday#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benMobilePhone eq 1 ||bnf.benMobilePhone eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col right_col">
						<div class="name">手机号码：</div>
						<div class="value" id="cfm_bnfMobilePhone#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benEmail eq 1 ||bnf.benEmail eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col">
						<div class="name">电子邮件：</div>
						<div class="value" id="cfm_bnfEmail#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benZipCode eq 1 ||bnf.benZipCode eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col right_col">
						<div class="name">邮政编码：</div>
						<div class="value" id="cfm_bnfZipCode#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benHomePhone eq 1 ||bnf.benHomePhone eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col">
						<div class="name">家庭电话：</div>
						<div class="value" id="cfm_bnfHomePhone#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benComPhone eq 1 ||bnf.benComPhone eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col right_col">
						<div class="name">公司电话：</div>
						<div class="value" id="cfm_bnfComPhone#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benAddress eq 1 ||bnf.benAddress eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col">
						<div class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</div>
						<div class="value" id="cfm_bnfAddress#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<c:if test="${bnf.benOrder eq 1 ||bnf.benOrder eq 2}">
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%} %>
					<div class="info_col">
						<div class="name">受益顺序：</div>
						<div class="value" id="cfm_bnfOrder#index"></div>
					</div>
					<% bnfCount++; %>
				</c:if>
				<%if(bnfCount % 2 == 0){ %></div><div class="info_row"><%}else{ %>
				<c:if test="${bnf.benRate eq 1 ||bnf.benRate eq 2}">
					<div class="info_col right_col">
						<div class="name">受益比例：</div>
						<div class="value" id="cfm_bnfRate#index"></div>
					</div>
				</c:if>
				<%} %>
				</div>
		</c:when>
		<c:when test="${ empty bnf }">
			<div class="info_row">
				<div class="info_col">
					<div class="name">与被保人关系：</div>
					<div class="value" id="cfm_bnfRelationToPIns">法定</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</div>