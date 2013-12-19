<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="inputAppDemo" class="input_area" tag="inputApp">
	<c:set var="app" value="${geSaleProduct.geSaleProApplicantConfigs[0] }"></c:set>
	<c:if test="${not empty app }">
		<c:choose>
			<c:when test="${app.appName eq 1}">
				<div class="input_row name">
					<label class="input_label" for="name"><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
					<input class="input_text" id="appName" name="appName" type="text" maxlength="20"/>
					<label class="lab" id="appNameTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appName eq 2}">
				<div class="input_row name">
					<label class="input_label" for="name"><span class="required">*</span><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
					<input class="input_text" id="appName" name="appName" type="text" maxlength="20"/>
					<label class="lab" id="appNameTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appIdType eq 1}">
				<div class="input_row id_type">
					<label class="input_label" for="id_type"><span class="name">证件类型：</span></label>
					<input id="appIdType" class="input_text" name="appIdType" type="text"/>
					<label class="lab" id="appIdTypeTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appIdType eq 2}">
				<div class="input_row id_type">
					<label class="input_label" for="id_type"><span class="required">*</span><span class="name">证件类型：</span></label>
					<input id="appIdType" class="input_text" name="appIdType" type="text"/>
					<label class="lab" id="appIdTypeTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appIdNo eq 1}">
				<div class="input_row id">
					<label class="input_label" for="id"><span class="name">证件号码：</span></label>
					<input class="input_text" id="appIdNo" name="appIdNo" type="text" maxlength="18" onBlur="checkIdCard('app','','applicant_info')"/>
					<label class="lab" id="appIdNoTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appIdNo eq 2}">
				<div class="input_row id">
					<label class="input_label" for="id"><span class="required">*</span><span class="name">证件号码：</span></label>
					<input class="input_text" id="appIdNo" name="appIdNo" type="text" maxlength="18" onBlur="checkIdCard('app','','applicant_info')"/>
					<label class="lab" id="appIdNoTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appBirthday eq 1}">
				<div class="input_row birthday">
					<label class="input_label" for="birthday"><span class="name">出生日期：</span></label>
					<input id="app_year" class="input_text year" name="birth_year" type="text"/>
					<label class="tip_label" for="birthday">年</label>
					<input id="app_month" class="input_text month" name="birth_month" type="text"/>
					<label class="tip_label" for="birthday">月</label>
					<input id="app_day" class="input_text day" name="birth_day" type="text"/>
					<label class="tip_label" for="birthday">日</label>
					<input id="appBirthday" name="appBirthday" type="hidden"/>
					<label class="lab" id="appBirthdayTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appBirthday eq 2}">
				<div class="input_row birthday">
					<label class="input_label" for="birthday"><span class="required">*</span><span class="name">出生日期：</span></label>
					<input id="app_year" class="input_text year" name="birth_year" type="text"/>
					<label class="tip_label" for="birthday">年</label>
					<input id="app_month" class="input_text month" name="birth_month" type="text"/>
					<label class="tip_label" for="birthday">月</label>
					<input id="app_day" class="input_text day" name="birth_day" type="text"/>
					<label class="tip_label" for="birthday">日</label>
					<input id="appBirthday" name="appBirthday" type="hidden"/>
					<label class="lab" id="appBirthdayTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appSex eq 1}">
				<div class="input_row sex">
					<label class="input_label" for="sex"><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
					<c:if test="${fn:contains(app.appSexConfig,'0') }">
						<div class="input_sex selected" id="male" val="0" prefix="app" prefixTag="applicant_info"></div>
						<label class="label_sex" val="0">男</label>
					</c:if>
					<c:if test="${fn:contains(app.appSexConfig,'1') }">
						<div class="input_sex" id="female" val="1" prefix="app" prefixTag="applicant_info"></div>
						<label class="label_sex" val="1">女</label>
					</c:if>
					<input id="appSex" class="sex" name="appSex" type="hidden" value="0"/>
					<label class="lab" id="appSexTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appSex eq 2}">
				<div class="input_row sex">
					<label class="input_label" for="sex"><span class="required">*</span><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
					<c:if test="${fn:contains(app.appSexConfig,'0') }">
						<div class="input_sex selected" id="male" val="0" prefix="app" prefixTag="applicant_info"></div>
						<label class="label_sex" val="0">男</label>
					</c:if>
					<c:if test="${fn:contains(app.appSexConfig,'1') }">
						<div class="input_sex" id="female" val="1" prefix="app" prefixTag="applicant_info"></div>
						<label class="label_sex" val="1">女</label>
					</c:if>
					<input id="appSex" class="sex" name="appSex" type="hidden" value="0"/>
					<label class="lab" id="appSexTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appEmail eq 1}">
				<div class="input_row email">
					<label class="input_label" for="email"><span class="name">电子邮件：</span></label>
					<input class="input_text" id="appEmail" name="appEmail" type="text" maxlength="50" onBlur="checkIsCustomer('Email','app','applicant_info');"/>
					<label class="lab" id="appEmailTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appEmail eq 2}">
				<div class="input_row email">
					<label class="input_label" for="email"><span class="required">*</span><span class="name">电子邮件：</span></label>
					<input class="input_text" id="appEmail" name="appEmail" type="text" maxlength="50" onBlur="checkIsCustomer('Email','app','applicant_info');"/>
					<label class="lab" id="appEmailTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appMobilePhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">手机号码：</span></label>
					<input class="input_text" id="appMobilePhone" name="appMobilePhone" type="text" maxlength="11" onBlur="checkIsCustomer('MobilePhone','app','applicant_info');"/>
					<label class="lab" id="appMobilePhoneTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appMobilePhone eq 2}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">手机号码：</span></label>
					<input class="input_text" id="appMobilePhone" name="appMobilePhone" type="text" maxlength="11" onBlur="checkIsCustomer('MobilePhone','app','applicant_info');"/>
					<label class="lab" id="appMobilePhoneTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appComPhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">公司电话：</span></label>
					<input class="input_text" id="appComPhone" name="appComPhone" type="text" maxlength="20"/>
					<label class="lab" id="appComPhoneTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appComPhone eq 2}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">公司电话：</span></label>
					<input class="input_text" id="appComPhone" name="appComPhone" type="text" maxlength="20"/>
					<label class="lab" id="appComPhoneTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appHomePhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">家庭电话：</span></label>
					<input class="input_text" id="appHomePhone" name="appHomePhone" type="text" maxlength="20"/>
					<label class="lab" id="appHomePhoneTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appHomePhone eq 2}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">家庭电话：</span></label>
					<input class="input_text" id="appHomePhone" name="appHomePhone" type="text" maxlength="20"/>
					<label class="lab" id="appHomePhoneTip"></label>
				</div>
			</c:when>
		</c:choose>
		
		<div class="input_row provinceCity">
			<label class="input_label" for="address"><span class="required">*</span><span class="name">省份城市：</span></label>
			<input class="input_text" name="appProvince" id="appProvince" type="text" onchange="autoSave();"/>
			<input class="input_text" name="appCity" id="appCity" type="text" onchange="autoSave();"/>
			<input class="input_text" name="appArea" id="appArea" type="text" onchange="autoSave();"/>
			<label class="lab" id="appProvinceTip"></label>
			<label class="lab" id="appCityTip"></label>
		</div>
		<div id="red_appProvinceCity"></div>
		
		<c:choose>
			<c:when test="${app.appAddress eq 1}">
				<div class="input_row address">
					<label class="input_label" for="address"><span class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span></label>
					<input class="input_text" id="appAddress" name="appAddress" type="text" maxlength="60"/>
					<label class="lab" id="appAddressTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appAddress eq 2}">
				<div class="input_row address">
					<label class="input_label" for="address"><span class="required">*</span><span class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span></label>
					<input class="input_text" id="appAddress" name="appAddress" type="text" maxlength="60"/>
					<label class="lab" id="appAddressTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appZipCode eq 1}">
				<div class="input_row postcode">
					<label class="input_label" for="postcode"><span class="name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</span></label>
					<input class="input_text" id="appZipCode" name="appZipCode" type="text" maxlength="6"/>
					<label class="lab" id="appZipCodeTip"></label>
				</div>
			</c:when>
			<c:when test="${app.appZipCode eq 2}">
				<div class="input_row postcode">
					<label class="input_label" for="postcode"><span class="required">*</span><span class="name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</span></label>
					<input class="input_text" id="appZipCode" name="appZipCode" type="text" maxlength="6"/>
					<label class="lab" id="appZipCodeTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${app.appOccupation eq 1}">
				<div class="input_row job">
					<label class="input_label" for="job"><span class="name">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</span></label>
					<input id="appOccupation" name="appOccupation" type="hidden"/>
					<div class="select_job" tag="applicant_info">
						<jsp:include page="/web/common/searchJobDemo.jsp"></jsp:include>
					</div>
					<label class="lab" id="appOccupationTip"></label>
				</div>
			</c:when> 
			<c:when test="${app.appOccupation eq 2 }">
				<div class="input_row job">
					<label class="input_label" for="job"><span class="required">*</span><span class="name">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</span></label>
					<input id="appOccupation" name="appOccupation" type="hidden"/>
					<div class="select_job" tag="applicant_info">
						<jsp:include page="/web/common/searchJobDemo.jsp"></jsp:include>
					</div>
					<label class="lab" id="appOccupationTip"></label>
				</div>
			</c:when>
		</c:choose>
		
		<div class="input_row validity" style="display:none">
			<label class="input_label" for="validity"><span class="name">证件有效期：</span></label>
			<input id="app_validity_year" class="input_text year" name="app_validity_year" type="text"/>
			<label class="tip_label" for="validity">年</label>
			<input id="app_validity_month" class="input_text month" name="app_validity_month" type="text"/>
			<label class="tip_label" for="validity">月</label>
			<input id="app_validity_day" class="input_text day" name="app_validity_day" type="text"/>
			<label class="tip_label" for="validity">日</label>
			<input id="appIdentifyEffectiveDate" name="appIdentifyEffectiveDate" type="hidden" value="<fmt:formatDate value='${geUserPersonal.identifyEffectiveDate }' pattern='yyyy-MM-dd'/>"/>
			<label class="lab" id="appIdentifyEffectiveDateTip"></label>
		</div>
		
	</c:if>
</div>