<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li class="at">��Ա��ҳ</li>
			</ul>
		</div>
		<div class="member_main"><!-- item account -->
			<jsp:include page="/web/user/member/common/left_menu.jsp"></jsp:include>
			<div class="right_content">
				<div class="title">
					<div class="block"></div>
					<p>����������Ϣ</p>
				</div>
				<div class="member_area contact_info">
					<div class="tips"><span>��������������Ϣ </span>&nbsp;&nbsp;&nbsp;������֤�����͡�֤�����롢�Ա𡢳������ڡ������Ĺ�ϵΪ������</div>
					<form action="${ctx }/insured/saveInsured.do" id="insured_fill" method="post">
						<input type="hidden" id="editFlag" name="editFlag" value="${editFlag }">
						<br>
<%-- 						<font color="red">${message }</font> --%>
						<input type="hidden" id="userID" name="userPersonal.userID" value="${userPersonal.userID }">
						<input type="hidden" id="serialNo" name="insured.serialNo" value="${insured.serialNo }">
						<div class="input_area">
							<div class="input_row relation">
								<label class="input_label" for="relation"><span class="required">*</span><span class="name">������ϵ��</span></label>
								<input class="input_text relatedToApplicant" id="insRelationToApp" name="insured.relatedToApplicant" type="text"/>
								<label class="lab" id="insRelationToAppTip"></label>
							</div>
							<div class="input_row name">
								<label class="input_label" for="real_name"><span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</span></label>
								<input class="input_text fullName" id="insName" name="insured.fullName" type="text" value="${insured.fullName }" maxlength="20"/>
								<label class="lab" id="insNameTip"></label>
							</div>
							<div class="input_row id_type">
								<label class="input_label" for="id_type"><span class="required">*</span><span class="name">֤�����ͣ�</span></label>
								<input class="input_text idType" id="insIdType" name="insured.idType" type="text"/>
								<label class="lab" id="insIdTypeTip"></label>
							</div>
							<div class="input_row id">
								<label class="input_label" for="id"><span class="required">*</span><span class="name">֤�����룺</span></label>
								<input class="input_text idNumber" id="insIdNo" name="insured.idNumber" type="text"  value="${insured.idNumber }" maxlength="18" onBlur="checkIdCard('ins','','insured_fill')"/>
								<label class="lab" id="insIdNoTip"></label>
								<span class="validIdentifyNumber validateMessage"></span>
							</div>
							<div class="input_row birthday">
								<label class="input_label" for="birthday"><span class="required">*</span><span class="name">�������ڣ�</span></label>
								<input id="ins_year" class="input_text year" name="birth_year" type="text"/>
								<label class="tip_label" for="birthday">��</label>
								<input id="ins_month" class="input_text month" name="birth_month" type="text"/>
								<label class="tip_label" for="birthday">��</label>
								<input id="ins_day" class="input_text day" name="birth_day" type="text"/>
								<label class="tip_label" for="birthday">��</label>
								<input type="hidden" id="insBirthday" name="insured.birthDate" class="birthDate" value="">
								<label class="lab" id="insBirthdayTip"></label>
							</div>
							<div class="input_row sex">
								<label class="input_label" for="sex"><span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</span></label>
								<c:if test="${insured.gender == 1 }">
									<div class="input_sex" id="male" val="0"></div>
									<label class="label_sex">��</label>
									<div class="input_sex selected" id="female" val="1"></div>
									<label class="label_sex">Ů</label>
								</c:if>
								<c:if test="${insured.gender != 1 }">
									<div class="input_sex selected" id="male" val="0"></div>
									<label class="label_sex">��</label>
									<div class="input_sex" id="female" val="1"></div>
									<label class="label_sex">Ů</label>
								</c:if>
								<input id="insSex" class="gender" name="insured.gender" type="hidden"/>
								<label class="lab" id="insSexTip"></label>
							</div>
							<div class="input_row cellphone">
								<label class="input_label" for="cellphone"><span class="name">�ֻ����룺</span></label>
								<input class="input_text mobilePhoneNumber" id="insMobilePhone" name="insured.mobilePhoneNumber" type="text" maxlength="11" value="${insured.mobilePhoneNumber }"/>
								<label class="lab" id="insMobilePhoneTip"></label>
							</div>
							<div class="input_row email">
								<label class="input_label" for="email"><span class="name">�������䣺</span></label>
								<input class="input_text email" id="insEmail" name="insured.email" type="text" maxlength="50" value="${insured.email }"/>
								<label class="lab" id="insEmailTip"></label>
							</div>
							<div class="input_row telephone" >
								<label class="input_label" for="telephone"><span class="name">��ͥ�绰��</span></label>
								<input class="input_text phoneNumber" type="text" name="insured.phoneNumber" id="insHomePhone" value="${insured.phoneNumber }">
								<label class="lab" id="insComPhoneTip"></label>
							</div>
							<div class="input_row provinceCity">
								<label class="input_label" for="address"><span class="name">ʡ�ݳ��У�</span></label>
								<input class="input_text" name="insured.province" id="insProvince" type="text" /> 
								<input class="input_text" name="insured.city" id="insCity" type="text" />
								<input class="input_text" name="insured.county" id="insArea" type="text" />
								<label class="lab" id="insProvinceTip"></label>
								<label class="lab" id="insCityTip"></label>
							</div>
							<div class="input_row contact_address">
								<label class="input_label" for="complete_address"><span class="name">��ϵ��ַ��</span></label>
								<input class="input_text addressLines" id="insAddress" name="insured.addressLines" type="text" maxlength="30" value="${insured.addressLines }"/>
								<label class="lab" id="insAddressTip"></label>
							</div>
							<div class="input_row postcode">
								<label class="input_label" for="postcode"><span class="name">�������룺</span></label>
								<input class="input_text postalCode" id="insZipCode" name="insured.postalCode" type="text" maxlength="6" value="${insured.postalCode }"/>
								<label class="lab" id="insZipCodeTip"></label>
							</div>
							<div class="action">
								<div class="save click_btn" onclick="return validateInsuredForm();">��&nbsp;&nbsp;&nbsp;&nbsp;��</div>
							</div>
						</div>
						<input type="hidden" id="result" name="result" value="${message }">
					</form>
				</div>
				<div class="saved_info">
					<div class="info_title">�ѱ������Ч����������Ϣ</div>
					<div class="info_area">
						<div class="info_row info_name">
							<div class="info_col name">����</div>
							<div class="info_col id_type">֤������</div>
							<div class="info_col id">֤������</div>
							<div class="info_col sex">�Ա�</div>
							<div class="info_col birthday">����</div>
							<div class="info_col relation">�����Ĺ�ϵ</div>
							<div class="info_col bind">&nbsp;</div>
<!-- 							<div class="info_col bind">Ĭ�ϱ�������</div> -->
							<div class="info_col operation">����</div>
						</div>
						<c:forEach items="${insureds }" varStatus="state" var="ins">
							<div class="info_row info_value select">
								<div class="info_col name">&nbsp;${ins.fullName }</div>
								<div class="info_col id_type">
									<c:choose>
										<c:when test="${ins.idType == '0'}">
											���֤
										</c:when>
										<c:when test="${ins.idType == '1'}">
											����
										</c:when>
										<c:when test="${ins.idType == '2'}">
											����֤
										</c:when>
										<c:when test="${ins.idType == '3'}">
											����
										</c:when>
										<c:when test="${ins.idType == '4'}">
											���ڱ�
										</c:when>
										<c:when test="${ins.idType == '5'}">
											ѧ��֤
										</c:when>
										<c:when test="${ins.idType == '6'}">
											����֤
										</c:when>
										<c:when test="${ins.idType == '8'}">
											��֤��
										</c:when>
										<c:when test="${ins.idType == '9'}">
											����
										</c:when>
									</c:choose>
								</div>
								<div class="info_col id">&nbsp;${ins.idNumber }</div>
								<div class="info_col sex">&nbsp;
									<c:if test="${ins.gender == 0 }">
										��
									</c:if>
									<c:if test="${ins.gender != 0 }">
										Ů
									</c:if>
								</div>
								<div class="info_col birthday">&nbsp;${ins.birthDate }</div>
								<div class="info_col relation">
									<c:choose>
										<c:when test="${ins.relatedToApplicant == '07' || ins.relatedToApplicant == '7'}">
											����
										</c:when>
										<c:when test="${ins.relatedToApplicant == '24'}">
											��ĸ
										</c:when>
										<c:when test="${ins.relatedToApplicant == '0'}">
											����
										</c:when>
										<c:when test="${ins.relatedToApplicant == '25'}">
											��Ů
										</c:when>
										<c:otherwise>
											����
										</c:otherwise>
									</c:choose>
								</div>
								<div class="info_col bind">&nbsp;</div>
								<div class="info_col operation">
									<a href="${ctx }/insured/editInsured.do?id=${ins.serialNo }">�޸�</a>
									&nbsp;|&nbsp;
									<a href="javascript:deleteInsured('${ins.serialNo }');">ɾ��</a></div>
							</div>
						</c:forEach>
					</div>
				</div>
				<jsp:include page="/web/user/member/common/product_hot_list.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
