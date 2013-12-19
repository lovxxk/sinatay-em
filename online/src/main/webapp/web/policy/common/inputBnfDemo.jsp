<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="inputBnfDemo">
	<div id="beneficiary_info#index" class="input_area" tag="inputBnf" index="#index">
		<c:set var="bnf" value="${geSaleProduct.geSaleProBeneficiaryConfigs[0] }"></c:set>
	    <c:if test="${not empty bnf }">
	    	<form id="benForm#index" method="post">
	    	<div class="input_row">
		    	<c:choose>
					<c:when test="${bnf.benRelationToPIns eq 1}">
						<div class="input_col ben_relation">
							<label class="input_label" for="name"><span class="name">与被保人关系：</span></label>
							<input class="input_text" id="benRelationToPIns#index" name="benRelationToPIns" type="text"/>
							<label class="lab" id="benRelationToPIns#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benRelationToPIns eq 2 }">
						<div class="input_col ben_relation">
							<label class="input_label" for="name"><span class="required">*</span><span class="name">与被保人关系：</span></label>
							<input class="input_text" id="benRelationToPIns#index" name="benRelationToPIns" type="text"/>
							<label class="lab" id="benRelationToPIns#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
				<div class="delete" id="delete#index">删除</div>
			</div>
			<div class="input_row">
		    	<c:choose>
					<c:when test="${bnf.benName eq 1}">
						<div class="input_col name">
							<label class="input_label" for="name"><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
							<input class="input_text" id="benName#index" name="benName" type="text" maxlength="20"/>
							<label class="lab" id="benName#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benName eq 2 }">
						<div class="input_col name">
							<label class="input_label" for="name"><span class="required">*</span><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span></label>
							<input class="input_text" id="benName#index" name="benName" type="text" maxlength="20"/>
							<label class="lab" id="benName#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
			<div class="input_row">
				<c:choose>
					<c:when test="${bnf.benIdType eq 1}">
						<div class="input_col id_type">
							<label class="input_label" for="id_type"><span class="name">证件类型：</span></label>
							<input class="input_text" id="benIdType#index" name="benIdType" type="text"/>
							<label class="lab" id="benIdType#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benIdType eq 2 }">
						<div class="input_col id_type">
							<label class="input_label" for="id_type"><span class="required">*</span><span class="name">证件类型：</span></label>
							<input class="input_text" id="benIdType#index" name="benIdType" type="text"/>
							<label class="lab" id="benIdType#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
			<div class="input_row">
				<c:choose>
					<c:when test="${bnf.benIdNumber eq 1 }">
						<div class="input_col id">
							<label class="input_label" for="id"><span class="name">证件号码：</span></label>
							<input class="input_text" id="benIdNo#index" name="benIdNo" type="text" maxlength="18" onBlur="checkIdCard('ben','#index','policy_fill')"/>
							<label class="lab" id="benIdNo#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benIdNumber eq 2 }">
						<div class="input_col id">
							<label class="input_label" for="id"><span class="required">*</span><span class="name">证件号码：</span></label>
							<input class="input_text" id="benIdNo#index" name="benIdNo" type="text" maxlength="18" onBlur="checkIdCard('ben','#index','policy_fill')"/>
							<label class="lab" id="benIdNo#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
				<c:choose>
				<c:when test="${bnf.benBirthday eq 1}">
					<div class="input_row birthday">
						<label class="input_label" for="birthday"><span class="name">出生日期：</span></label>
						<input id="ben_year#index" class="input_text year" name="birth_year" type="text"/>
						<label class="tip_label" for="birthday">年</label>
						<input id="ben_month#index" class="input_text month" name="birth_month" type="text"/>
						<label class="tip_label" for="birthday">月</label>
						<input id="ben_day#index" class="input_text day" name="birth_day" type="text"/>
						<label class="tip_label" for="birthday">日</label>
						<input id="benBirthday#index" name="benBirthday" type="hidden"/>
						<label class="lab" id="benBirthday#indexTip"></label>
					</div>
				</c:when>
				<c:when test="${bnf.benBirthday eq 2}">
					<div class="input_row birthday">
						<label class="input_label" for="birthday"><span class="required">*</span><span class="name">出生日期：</span></label>
						<input id="ben_year#index" class="input_text year" name="birth_year" type="text"/>
						<label class="tip_label" for="birthday">年</label>
						<input id="ben_month#index" class="input_text month" name="birth_month" type="text"/>
						<label class="tip_label" for="birthday">月</label>
						<input id="ben_day#index" class="input_text day" name="birth_day" type="text"/>
						<label class="tip_label" for="birthday">日</label>
						<input id="benBirthday#index" name="benBirthday" type="hidden"/>
						<label class="lab" id="benBirthday#indexTip"></label>
					</div>
				</c:when>
			</c:choose>
			<div class="input_row">
				<c:choose>
					<c:when test="${bnf.benGender eq 1}">
						<div class="input_col sex">
							<label class="input_label" for="sex"><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
							<c:if test="${fn:contains(bnf.benGenderConfig,'0') }">
								<div class="input_sex selected" tag="input_sex#index" id="male#index" val="0" prefix="ben" prefixTag="beneficiary_info#index"></div>
								<label class="label_sex" val="0">男</label>
							</c:if>
							<c:if test="${fn:contains(bnf.benGenderConfig,'1') }">
								<div class="input_sex" tag="input_sex#index" id="female#index" val="1" prefix="ben" prefixTag="beneficiary_info#index"></div>
								<label class="label_sex" val="1">女</label>
							</c:if>
							<input id="benSex#index" name="benSex" type="hidden" value="0"/>
							<label class="lab" id="benSex#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benGender eq 2 }">
						<div class="input_col sex">
							<label class="input_label" for="sex"><span class="required">*</span><span class="name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span></label>
							<c:if test="${fn:contains(bnf.benGenderConfig,'0') }">
								<div class="input_sex selected" tag="input_sex#index" id="male#index" val="0" prefix="ben" prefixTag="beneficiary_info#index"></div>
								<label class="label_sex" val="0">男</label>
							</c:if>
							<c:if test="${fn:contains(bnf.benGenderConfig,'1') }">
								<div class="input_sex" tag="input_sex#index" id="female#index" val="1" prefix="ben" prefixTag="beneficiary_info#index"></div>
								<label class="label_sex" val="1">女</label>
							</c:if>
							<input id="benSex#index" name="benSex" type="hidden" value="0"/>
							<label class="lab" id="benSex#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
			<div class="input_row">
				<c:choose>
					<c:when test="${bnf.benOrder eq 1}">
						<div class="input_col sequence">
							<label class="input_label" for="id_type"><span class="name">受益顺序：</span></label>
							<label class="tip">第</label>
							<input class="input_text" id="benOrder#index" name="benOrder" type="text" maxlength="1"/>
							<label class="tip">受益人</label>
							<label class="lab" id="benOrder#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benOrder eq 2}">
						<div class="input_col sequence">
							<label class="input_label" for="id_type"><span class="required">*</span><span class="name">受益顺序：</span></label>
							<label class="tip">第</label>
							<input class="input_text" id="benOrder#index" name="benOrder" type="text" maxlength="1"/>
							<label class="tip">受益人</label>
							<label class="lab" id="benOrder#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
			<div class="input_row">
				<c:choose>
					<c:when test="${bnf.benRate eq 1 }">
						<div class="input_col proportion">
							<label class="input_label" for="id_type"><span class="name">受益比例：</span></label>
							<input class="input_text" id="benRate#index" name="benRate" type="text" maxlength="3"/>
							<label class="tip">%</label>
							<label class="lab" id="benRate#indexTip"></label>
						</div>
					</c:when>
					<c:when test="${bnf.benRate eq 2}">
						<div class="input_col proportion">
							<label class="input_label" for="id_type"><span class="required">*</span><span class="name">受益比例：</span></label>
							<input class="input_text" id="benRate#index" name="benRate" type="text" maxlength="3"/>
							<label class="tip">%</label>
							<label class="lab" id="benRate#indexTip"></label>
						</div>
					</c:when>
				</c:choose>
			</div>
			</form>
	    </c:if>
    </div>
</div>