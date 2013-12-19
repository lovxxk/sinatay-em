<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<% int appCount = 0; %>
<div id="confirmAppDemo" class="policy_area applicant_info" tag="confirmApp">
	<c:set var="app" value="${geSaleProduct.geSaleProApplicantConfigs[0] }"></c:set>
	<c:if test="${not empty app }">
		<div class="title">Ͷ������Ϣ</div>
		<div class="info_row">
			<c:if test="${app.appName eq 1 ||app.appName eq 2}">
				<div class="info_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
					<div class="value" id="cfm_appName"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appSex eq 1 ||app.appSex eq 2}">
				<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</div>
					<div class="value" id="cfm_appSex"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appIdType eq 1 ||app.appIdType eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">֤�����ͣ�</div>
					<div class="value" id="cfm_appIdType"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appIdNo eq 1 ||app.appIdNo eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">֤�����룺</div>
					<div class="value" id="cfm_appIdNo"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appBirthday eq 1 ||app.appBirthday eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">�������ڣ�</div>
					<div class="value" id="cfm_appBirthday"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appMobilePhone eq 1 ||app.appMobilePhone eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">�ֻ����룺</div>
					<div class="value" id="cfm_appMobilePhone"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appEmail eq 1 ||app.appEmail eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">�����ʼ���</div>
					<div class="value" id="cfm_appEmail"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appZipCode eq 1 ||app.appZipCode eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">�������룺</div>
					<div class="value" id="cfm_appZipCode"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appHomePhone eq 1 ||app.appHomePhone eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">��ͥ�绰��</div>
					<div class="value" id="cfm_appHomePhone"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appComPhone eq 1 ||app.appComPhone eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��˾�绰��</div>
					<div class="value" id="cfm_appComPhone"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<c:if test="${app.appAddress eq 1 ||app.appAddress eq 2}">
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col">
					<div class="name">ʡ�ݳ��У�</div>
					<div class="value" id="cfm_appProvince"></div>
					<div class="value" id="cfm_appCity"></div>
					<div class="value" id="cfm_appArea"></div>
				</div>
				<% appCount++; %>
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%} %>
				<div class="info_col right_col">
					<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ַ��</div>
					<div class="value" id="cfm_appAddress"></div>
				</div>
				<% appCount++; %>
			</c:if>
			<%if(appCount % 2 == 0){ %></div><div class="info_row"><%}else{ %>
			<c:if test="${app.appOccupation eq 1 ||app.appOccupation eq 2}">
				<div class="info_col right_col">
					<div class="name">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
					<div class="value" id="cfm_appOccupation"></div>
				</div>
			</c:if>
			<%} %>
		</div>
	</c:if>
</div>