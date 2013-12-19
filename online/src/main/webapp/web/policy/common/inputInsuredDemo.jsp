<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="inputInsured" class="input_area" tag="inputInsured">
	<c:set var="insured" value="${geSaleProduct.geSaleProInsuredConfigs[0] }"></c:set>
    <c:if test="${not empty insured }">
		<c:choose>
			<c:when test="${insured.insRelationToApp eq 1}">
				<div class="input_row relation">
					<label class="input_label" for="name"><span class="name">与投保人关系：</span></label>
					<input class="input_text" id="insRelationToApp" name="insRelationToApp" type="text"/>
					<label class="lab" id="insRelationToAppTip"></label>
					<input class="input_text" id="topinsId" name="topinsId" type="hidden"/>
					<div class="save_contact" id="save_contact">
						<div class="save_check" id="save_topInsured"></div>
						<input class="input_text" id="save_topInsured" name="save_topInsured" type="hidden" value="0"/>
						<label>保存为常用被保险人</label>
					</div>
				</div>
			</c:when>  
			<c:when test="${insured.insRelationToApp eq 2 }">
				<div class="input_row relation">
					<label class="input_label" for="name"><span class="required">*</span><span class="name">与投保人关系：</span></label>
					<input class="input_text" id="insRelationToApp" name="insRelationToApp" type="text"/>
					<label class="lab" id="insRelationToAppTip"></label>
					<input class="input_text" id="topinsId" name="topinsId" type="hidden"/>
					<div class="save_contact">
						<div class="save_check" id="save_topInsured"></div>
						<input class="input_text" id="save_topInsured" name="save_topInsured" type="hidden" value="0"/>
						<label>保存为常用被保险人</label>
					</div>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insName eq 1}"> 
				<div class="input_row name">
					<label class="input_label" for="name"><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
					<input class="input_text" id="insName" name="insName" type="text" maxlength="20"/>
					<label class="lab" id="insNameTip"></label>
				</div>
				<div id="red_insName"></div>
			</c:when> 
			<c:when test="${insured.insName eq 2 }"> 
				<div class="input_row name">
					<label class="input_label" for="name"><span class="required">*</span><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
					<input class="input_text" id="insName" name="insName" type="text" maxlength="20"/>
					<label class="lab" id="insNameTip"></label>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insIdType eq 1}">
				<div class="input_row id_type">
					<label class="input_label" for="id_type"><span class="name">证件类型：</span></label>
					<input class="input_text" id="insIdType" name="insIdType" type="text"/>
					<label class="lab" id="insIdTypeTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insIdType eq 2 }">
				<div class="input_row id_type">
					<label class="input_label" for="id_type"><span class="required">*</span><span class="name">证件类型：</span></label>
					<input class="input_text" id="insIdType" name="insIdType" type="text"/>
					<label class="lab" id="insIdTypeTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${insured.insIdNo eq 1}">
				<div class="input_row id">
					<label class="input_label" for="id"><span class="name">证件号码：</span></label>
					<input class="input_text" id="insIdNo" name="insIdNo" type="text" maxlength="18" onBlur="checkIdCard('ins','','insured_info')"/>
					<label class="lab" id="insIdNoTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insIdNo eq 2 }">
				<div class="input_row id">
					<label class="input_label" for="id"><span class="required">*</span><span class="name">证件号码：</span></label>
					<input class="input_text" id="insIdNo" name="insIdNo" type="text" maxlength="18" onBlur="checkIdCard('ins','','insured_info')"/>
					<label class="lab" id="insIdNoTip"></label>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${insured.insBirthday eq 1}">
				<div class="input_row birthday">
					<label class="input_label" for="birthday"><span class="name">出生日期：</span></label>
					<input id="ins_year" class="input_text year" name="birth_year" type="text"/>
					<label class="tip_label" for="birthday">年</label>
					<input id="ins_month" class="input_text month" name="birth_month" type="text"/>
					<label class="tip_label" for="birthday">月</label>
					<input id="ins_day" class="input_text day" name="birth_day" type="text"/>
					<label class="tip_label" for="birthday">日</label>
					<input id="insBirthday" name="insBirthday" type="hidden"/>
					<label class="lab" id="insBirthdayTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insBirthday eq 2 }">
				<div class="input_row birthday">
					<label class="input_label" for="birthday"><span class="required">*</span><span class="name">出生日期：</span></label>
					<input id="ins_year" class="input_text year" name="birth_year" type="text"/>
					<label class="tip_label" for="birthday">年</label>
					<input id="ins_month" class="input_text month" name="birth_month" type="text"/>
					<label class="tip_label" for="birthday">月</label>
					<input id="ins_day" class="input_text day" name="birth_day" type="text"/>
					<label class="tip_label" for="birthday">日</label>
					<input id="insBirthday" name="insBirthday" type="hidden"/>
					<label class="lab" id="insBirthdayTip"></label>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insSex eq 1}">
				<div class="input_row sex">
					<label class="input_label" for="sex"><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
					<c:if test="${fn:contains(insured.insSexConfig,'0') }">
						<div class="input_sex selected" id="male" val="0" prefix="ins" prefixTag="insured_info"></div>
						<label class="label_sex" val="0">男</label>
					</c:if>
					<c:if test="${fn:contains(insured.insSexConfig,'1') }">
						<div class="input_sex" id="female" val="1" prefix="ins" prefixTag="insured_info"></div>
						<label class="label_sex" val="1">女</label>
					</c:if>
					<input id="insSex" class="sex" name="insSex" type="hidden" value="0"/>
					<label class="lab" id="insSexTip"></label>
				</div>
			</c:when>
			<c:when test="${insured.insSex eq 2 }">
				<div class="input_row sex">
					<label class="input_label" for="sex"><span class="required">*</span><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
					<c:if test="${fn:contains(insured.insSexConfig,'0') }">
						<div class="input_sex selected" id="male" val="0" prefix="ins" prefixTag="insured_info"></div>
						<label class="label_sex" val="0">男</label>
					</c:if>
					<c:if test="${fn:contains(insured.insSexConfig,'1') }">
						<div class="input_sex" id="female" val="1" prefix="ins" prefixTag="insured_info"></div>
						<label class="label_sex" val="1">女</label>
					</c:if>
					<input id="insSex" class="sex" name="insSex" type="hidden" value="0"/>
					<label class="lab" id="insSexTip"></label>
				</div>
			</c:when>
		</c:choose> 
		<c:choose>
			<c:when test="${insured.insEmail eq 1}">
				<div class="input_row email">
					<label class="input_label" for="email"><span class="name">电子邮件：</span></label>
					<input class="input_text" id="insEmail" name="insEmail" type="text" maxlength="50"/>
					<label class="lab" id="insEmailTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insEmail eq 2 }">
				<div class="input_row email">
					<label class="input_label" for="email"><span class="required">*</span><span class="name">电子邮件：</span></label>
					<input class="input_text" id="insEmail" name="insEmail" type="text" maxlength="50"/>
					<label class="lab" id="insEmailTip"></label>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insMobilePhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">手机号码：</span></label>
					<input class="input_text" id="insMobilePhone" name="insMobilePhone" type="text" maxlength="11"/>
					<label class="lab" id="insMobilePhoneTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insMobilePhone eq 2 }">
		    	<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">手机号码：</span></label>
					<input class="input_text" id="insMobilePhone" name="insMobilePhone" type="text" maxlength="11"/>
					<label class="lab" id="insMobilePhoneTip"></label>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insComPhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">公司电话：</span></label>
					<input class="input_text" id="insComPhone" name="insComPhone" type="text" maxlength="20"/>
					<label class="lab" id="insComPhoneTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insComPhone eq 2 }">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">公司电话：</span></label>
					<input class="input_text" id="insComPhone" name="insComPhone" type="text" maxlength="20"/>
					<label class="lab" id="insComPhoneTip"></label>
				</div>
			</c:when>
		</c:choose> 
		<c:choose>
			<c:when test="${insured.insHomePhone eq 1}">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="name">家庭电话：</span></label>
					<input class="input_text" id="insHomePhone" name="insHomePhone" type="text" maxlength="20"/>
					<label class="lab" id="insHomePhoneTip"></label>
				</div>
				<div id="red_insHomePhone"></div>
			</c:when> 
			<c:when test="${insured.insHomePhone eq 2 }">
				<div class="input_row cellphone">
					<label class="input_label" for="cellphone"><span class="required">*</span><span class="name">家庭电话：</span></label>
					<input class="input_text" id="insHomePhone" name="insHomePhone" type="text" maxlength="20"/>
					<label class="lab" id="insHomePhoneTip"></label>
				</div>
			</c:when> 
		</c:choose>
		
		<div class="input_row provinceCity">
			<label class="input_label" for="address"><span class="required">*</span><span class="name">省份城市：</span></label>
			<input class="input_text" name="insProvince" id="insProvince" type="text" /> 
			<input class="input_text" name="insCity" id="insCity" type="text" />
			<input class="input_text" name="insArea" id="insArea" type="text" />
			<label class="lab" id="insProvinceTip"></label>
			<label class="lab" id="insCityTip"></label>
		</div>
		
		<c:choose>
			<c:when test="${insured.insAddress eq 1}">
				<div class="input_row address">
					<label class="input_label" for="address"><span class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span></label>
					<input class="input_text" id="insAddress" name="insAddress" type="text" maxlength="60"/>
					<label class="lab" id="insAddressTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insAddress eq 2}">
				<div class="input_row address">
					<label class="input_label" for="address"><span class="required">*</span><span class="name">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span></label>
					<input class="input_text" id="insAddress" name="insAddress" type="text" maxlength="60"/>
					<label class="lab" id="insAddressTip"></label>
				</div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insZipCode eq 1}">
				<div class="input_row postcode">
					<label class="input_label" for="postcode"><span class="name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</span></label>
					<input class="input_text" id="insZipCode" name="insZipCode" type="text" maxlength="6"/>
					<label class="lab" id="insZipCodeTip"></label>
				</div>
				<div id="red_insZipCode"></div>
			</c:when> 
			<c:when test="${insured.insZipCode eq 2 }">
				<div class="input_row postcode">
					<label class="input_label" for="postcode"><span class="required">*</span><span class="name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</span></label>
					<input class="input_text" id="insZipCode" name="insZipCode" type="text" maxlength="6"/>
					<label class="lab" id="insZipCodeTip"></label>
				</div>
				<div id="red_insZipCode"></div>
			</c:when> 
		</c:choose>
		<c:choose>
			<c:when test="${insured.insOccupation eq 1}">
				<div class="input_row job">
					<label class="input_label" for="job"><span class="name">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</span></label>
					<div class="select_job" tag="insured_info">
						<jsp:include page="/web/common/searchJobDemo.jsp"></jsp:include>
					</div>
					<input id="insOccupation" name="insOccupation" type="hidden"/>
					<label class="lab" id="insOccupationTip"></label>
				</div>
			</c:when> 
			<c:when test="${insured.insOccupation eq 2 }">
				<div class="input_row job">
					<label class="input_label" for="job"><span class="required">*</span><span class="name">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</span></label>
					<div class="select_job" tag="insured_info">
						<jsp:include page="/web/common/searchJobDemo.jsp"></jsp:include>
					</div>
					<input id="insOccupation" name="insOccupation" type="hidden"/>
					<label class="lab" id="insOccupationTip"></label>
				</div>
			</c:when>
		</c:choose> 
		
		<div class="input_row validity" style="display:none">
			<label class="input_label" for="validity"><span class="name">证件有效期：</span></label>
			<input id="ins_validity_year" class="input_text year" name="ins_validity_year" type="text"/>
			<label class="tip_label" for="validity">年</label>
			<input id="ins_validity_month" class="input_text month" name="ins_validity_month" type="text"/>
			<label class="tip_label" for="validity">月</label>
			<input id="ins_validity_day" class="input_text day" name="ins_validity_day" type="text"/>
			<label class="tip_label" for="validity">日</label>
			<input id="insIdentifyEffectiveDate" name="insIdentifyEffectiveDate" type="hidden"/>
			<label class="lab" id="insIdentifyEffectiveDateTip"></label>
		</div>
	</c:if>
</div>