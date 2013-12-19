<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% int insCount = 0; %>
<div id="confirmInsuredDemo" style="display: none;">
	<div tag="confirmInsured"></div>
	<c:set var="ins" value="${geSaleProduct.geSaleProInsuredConfigs[0] }"></c:set>
	<c:if test="${not empty ins }">
		<div class="title">��������Ϣ</div>
		<div class="info_row">
			<c:if test="${ins.insRelationToApp eq 1 ||ins.insRelationToApp eq 2}">
				<div class="info_col">
					<div class="name">��Ͷ���˹�ϵ��</div>
					<div class="value" id="cfm_insRelationToApp#index"></div>
				</div>
			</c:if>
		</div>
		<div class="info_row">
			<c:if test="${ins.insName eq 1 ||ins.insName eq 2}">
				<div class="info_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
					<div class="value" id="cfm_insName#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insSex eq 1 ||ins.insSex eq 2}">
				<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</div>
					<div class="value" id="cfm_insSex#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insIdType eq 1 ||ins.insIdType eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">֤�����ͣ�</div>
					<div class="value" id="cfm_insIdType#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insIdNo eq 1 ||ins.insIdNo eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">֤�����룺</div>
					<div class="value" id="cfm_insIdNo#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insBirthday eq 1 ||ins.insBirthday eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">�������ڣ�</div>
					<div class="value" id="cfm_insBirthday#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insMobilePhone eq 1 ||ins.insMobilePhone eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">�ֻ����룺</div>
					<div class="value" id="cfm_insMobilePhone#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insEmail eq 1 ||ins.insEmail eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">�����ʼ���</div>
					<div class="value" id="cfm_insEmail#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insZipCode eq 1 ||ins.insZipCode eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">�������룺</div>
					<div class="value" id="cfm_insZipCode#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insHomePhone eq 1 ||ins.insHomePhone eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">��ͥ�绰��</div>
					<div class="value" id="cfm_insHomePhone#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insComPhone eq 1 ||ins.insComPhone eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��˾�绰��</div>
					<div class="value" id="cfm_insComPhone#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<c:if test="${ins.insAddress eq 1 ||ins.insAddress eq 2}">
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">ʡ�ݳ��У�</div>
					<div class="value" id="cfm_insProvince#index"></div>
					<div class="value" id="cfm_insCity#index"></div>
					<div class="value" id="cfm_insArea#index"></div>
				</div>
				<% insCount++; %>
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ַ��</div>
					<div class="value" id="cfm_insAddress#index"></div>
				</div>
				<% insCount++; %>
			</c:if>
			<%if(insCount % 2 == 0){ %></div><div class="info_row"><%}else{ %>
			<c:if test="${ins.insOccupation eq 1 ||ins.insOccupation eq 2}">
				<div class="info_col right_col">
					<div class="name">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
					<div class="value" id="cfm_insOccupation#index"></div>
				</div>
			</c:if>
			<%} %>
		</div>
	</c:if>
</div>