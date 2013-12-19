<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx }/web/user/member/account/personalInfo/js/validatePersonalInfoForm.js"></script>
<body onload="fillDate();">
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}/index.jsp">首页</a><span> &gt;</span></li>
				<li class="at">会员首页</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>个人资料</p>
				</div>
				<div class="member_area">
					<br>
					<font color="red">${errorMessage }</font>
					<form action="${ctx }/edit/update.do" method="post" id="personalInfoForm">
						<input type="hidden" name="customer.userID" id="userID" value="${customer.userID }">
						<div class="input_area">
							<div class="input_row name">
								<label class="input_label" for="real_name"><span class="required">*</span><span class="name">真实姓名：</span></label>
								<input class="input_text userName" id="appName" name="customer.userName" type="text" value="${customer.userName }" />
								<label class="lab" id="appNameTip"></label>
							</div>
							<div class="input_row id_type">
								<label class="input_label" for="id_type"><span class="required">*</span><span class="name">证件类型：</span></label>
								<input class="input_text identifyType" id="appIdType"  name="customer.identifyType" type="text"/>
								<label class="lab" id="appIdTypeTip"></label>
							</div>
							<div class="input_row id">
								<label class="input_label" for="id"><span class="required">*</span><span class="name">证件号码：</span></label>
								<input class="input_text identifyNumber" id="appIdNo" name="customer.identifyNumber" type="text" value="${customer.identifyNumber }" maxlength="18" onBlur="checkIdCard('app','','personalInfoForm')"/>
								<label class="lab" id="appIdNoTip"></label>
							</div>
							<div class="input_row birthday">
								<label class="input_label" for="birthday"><span class="required">*</span><span class="name">出生日期：</span></label>
								<input id="app_year" class="input_text year" name="birth_year" type="text"/>
								<label class="tip_label" for="birthday">年</label>
								<input id="app_month" class="input_text month" name="birth_month" type="text"/>
								<label class="tip_label" for="birthday">月</label>
								<input id="app_day" class="input_text day" name="birth_day" type="text"/>
								<label class="tip_label" for="birthday">日</label>
								<input type="hidden" id="appBirthday" name="customer.birthday" value="">
								<label class="lab" id="appBirthdayTip"></label>
							</div>
							<div class="input_row sex">
								<label class="input_label" for="sex"><span class="required">*</span><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
								<c:if test="${customer.sex == '1' }">
									<div class="input_sex" id="male" val="0"></div>
									<label class="label_sex">男</label>
									<div class="input_sex selected" id="female" val="1"></div>
									<label class="label_sex">女</label>
								</c:if>
								<c:if test="${customer.sex != '1' }">
									<div class="input_sex selected" id="male" val="0"></div>
									<label class="label_sex">男</label>
									<div class="input_sex" id="female" val="1"></div>
									<label class="label_sex">女</label>
								</c:if>
								<input id="appSex" class="sex" name="customer.sex" type="hidden" value="${customer.sex }"/>
								<label class="lab" id="appSexTip"></label>
							</div>
							<div class="input_row provinceCity">
								<label class="input_label" for="address"><span class="name">省份城市：</span></label>
								<input class="input_text" name="customer.provinces" id="appProvince" type="text"/>
								<input class="input_text" name="customer.city" id="appCity" type="text"/>
								<input class="input_text" name="customer.area" id="appArea" type="text"/>
								<label class="lab" id="appProvinceTip"></label>
								<label class="lab" id="appCityTip"></label>
							</div>
							<div class="input_row address">
								<label class="input_label" for="address"><span class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span></label>
								<input class="input_text" name="customer.contactAddress" type="text" maxlength="60" value="${customer.contactAddress }"/>
							</div>
							<div class="input_row postcode">
								<label class="input_label" for="postcode"><span class="name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</span></label>
								<input class="input_text" name="customer.zipCode" type="text" maxlength="6" value="${customer.zipCode }"/>
							</div>
						</div>
						<div class="input_area">
							<div class="input_row validity" style="display:none">
								<label class="input_label" for="validity"><span class="name">证件有效期：</span></label>
								<input id="app_validity_year" class="input_text year" name="validity_year" type="text"/>
								<label class="tip_label" for="validity">年</label>
								<input id="app_validity_month" class="input_text month" name="validity_month" type="text"/>
								<label class="tip_label" for="validity">月</label>
								<input id="app_validity_day" class="input_text day" name="validity_day" type="text"/>
								<label class="tip_label" for="validity">日</label>
								<input type="hidden" name="customer.identifyEffectiveDate" id="appIdentifyEffectiveDate" value="">
								<label class="lab" id="appIdentifyEffectiveDateTip"></label>
							</div>
							<div class="input_row nickname">
								<label class="input_label" for="nickname"><span class="name">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span></label>
								<input class="input_text" name="customer.alias" value="${customer.alias }" type="text" maxlength="16"/>
							</div>
							<div class="input_row blood_type">
								<label class="input_label" for="blood_type"><span class="name">血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</span></label>
								<input class="input_text bloodType" id="bloodType" name="customer.bloodType" value="${customer.bloodType }" type="text"/>
							</div>
							<div class="input_row hobby">
								<label class="input_label" for="hobby"><span class="name">爱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好：</span></label>
								<input class="input_text" name="customer.hobby" value="${customer.hobby }" type="text"/>
							</div>
						</div>
						<div class="action">
							<div type="submit" class="next_step click_btn" onclick="return validatePersonalInfo();">保&nbsp;&nbsp;&nbsp;&nbsp;存</div>
						</div>
					</form>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
</body>