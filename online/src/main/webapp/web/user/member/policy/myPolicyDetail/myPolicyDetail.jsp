<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">��Ա��ҳ</a><span>
						&gt;</span></li>
				<li><a href="${ctx}/info/initPolicyList.do">�ҵı���</a><span>
						&gt;</span></li>
				<li class="at">��������</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">${policy.riskAccount.riskName}</div>
				<div class="balance">

					<c:if
						test="${policy.riskAccount.riskScource =='NETS' &&policy.riskAccount.insuaccFlag=='1'}">

						<div class="receive click_btn">��&nbsp;&nbsp;&nbsp;&nbsp;ȡ</div>
					</c:if>
					<!-- �����̳ǵı�������ȡ -->
					<c:if test="${policy.riskAccount.insuaccFlag =='1'}">
						<div class="money">
							<span class="label">�˻���</span> <span class="int">��<span
								id="total_amount">${policy.riskAccount.cashValue }</span></span>
						</div>
					</c:if>
				</div>
			</div>

			<div class="policy_info">
				<div class="policy_num">
					<span>�������룺</span><span id='cont_no'>${policy.policyNo }</span>
					<div>
						<p>���ۻ������ƣ�${policy.comName }</p>
						<p>���ۻ�����ַ��${policy.comAddress}</p>
					</div>
				</div>
				<div class="info1">
					<div class="info_row">
						<div class="info_col first">
							<span class="tip">�������ˣ�</span><span>${policy.insured.name}</span>
						</div>
						<div class="info_col">
							<span class="tip">�����ڼ䣺</span>
							<!-- ������� ��������ʾ �갴������ʾ -->
							<c:if
								test="${policy.mRisk.insuYear =='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<span>����</span>
							</c:if>
							<c:if
								test="${policy.mRisk.insuYear !='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<span>${policy.mRisk.insuYear }��</span>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='D'}">
								<span>${policy.mRisk.insuYear }��</span>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='A'}">
								<span>${policy.mRisk.insuYear }��</span>
							</c:if>
							
							<c:if test="${policy.mRisk.insuYearFlag=='M'}">
								<span>${policy.mRisk.insuYear }��</span>
							</c:if>

						</div>
						<div class="info_col last">
							<span class="tip">��Ч���ڣ�</span><span>${policy.valiDate }</span>
						</div>
					</div>
					<div class="info_row">
						<div class="info_col first">
							<span class="tip">����״̬��</span><span>${policy.state}</span>
						</div>
						<div class="info_col last visit">
							<span class="tip">�Ƿ�طã�</span><span>${policy.isVisit }</span>
						</div>
					</div>
					<div style="display: none" class="risksStr">${risksStr}</div>
				</div>
				<div class="info2">
					<div class="info_row name">
						<div class="info_col insure_name">��������</div>
						<div class="info_col insure_amount">���ս��</div>
						<div class="info_col premium">����</div>
						<div class="info_col period">�����ڼ�</div>
						<div class="info_col effective_date">��Ч����</div>
						<div class="info_col status last">����״̬</div>
					</div>
					<c:forEach var="risk" items="${policy.risks }">
						<div class="info_row value">
							<div class="info_col insure_name">${risk.riskName}</div>
							<div class="info_col insure_amount">${risk.amnt}</div>
							<div class="info_col premium">${risk.prem}</div>
							<!-- ������� ��������ʾ �갴������ʾ -->
							<c:if
								test="${risk.insuYear =='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<div class="info_col period">����</div>
							</c:if>
							<c:if
								test="${risk.insuYear !='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<div class="info_col period">${risk.insuYear }��</div>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='D'}">
								<div class="info_col period">${policy.mRisk.insuYear }��</div>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='A'}">
								<div class="info_col period">${policy.mRisk.insuYear }��</div>
							</c:if>
							
							<c:if test="${policy.mRisk.insuYearFlag=='M'}">
								<div class="info_col period">${policy.mRisk.insuYear }��</div>
							</c:if>
							
							<div class="info_col effective_date">${risk.cvaliDate}</div>
							<div class="info_col status last">${risk.state}</div>
						</div>
					</c:forEach>
				</div>
				<p class="info_more">
					<a href="#" onclick="productCashBnf(3);return false">�ֽ��ֵ</a><a
						href="#" onclick="productCashBnf(2);return false">��������</a><a
						href="#" onclick="productCashBnf(1);return false">��Ʒ����</a>
				</p>
				<div style="display: none;" id="showSelect">${showSelect }</div>
			</div>

			<form id="fm" name="fm">
				<div class="info_area applicant_info">
					<div class="title">
						<span class="title_name">Ͷ������Ϣ</span>
						<div class="edit">
							<div class="save_edit click_btn">�����޸�</div>
							<div class="to_edit" id="to_edit_appnt">�޸�</div>
							<div class="cancel_edit">ȡ��</div>
						</div>
					</div>
					<div class="input_area">
						<input type="text" class="action" style="display: none"
							value="appntInfoChange.do" />
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.name}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�������ڣ�</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.birthDay }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֤�����ͣ�</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.idTypeName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show" id="marriage">${policy.appnt.marriage
										}</div>
									<select name="policy.appnt.marriage"
										class="edit_option marriage">
										<option value="">��</option>
										<option value="0">δ��</option>
										<option value="1">�ѻ�</option>
										<option value="2">����</option>
										<option value="3">ɥż</option>
										<option value="4">�־�</option>
										<option value="5">�ٻ�</option>
										<option value="6">ͬ��</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֤�����룺</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.idNo}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show" id="nationality">${policy.appnt.nationality}</div>
									<select name="policy.appnt.nationality"
										class="edit_option nationality">
										<option value="">��</option>
										<option value="AUS">�Ĵ�����</option>
										<option value="CHN">�й�</option>
										<option value="ENG">Ӣ��</option>
										<option value="JAN">�ձ�</option>
										<option value="OTH">����</option>
										<option value="RUS">����˹</option>
										<option value="USA">����</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�������ͣ�</div>
								<div class="input_text">
									<div class="text_show" id="licenseType">${policy.appnt.licenseType}</div>
									<select name="policy.appnt.licenseType"
										class="edit_option licenseType">
										<option value="">��</option>
										<option value="A1">A1</option>
										<option value="A2">A2</option>
										<option value="A3">A3</option>
										<option value="B1">B1</option>
										<option value="B2">B2</option>
										<option value="C1">C1</option>
										<option value="C2">C2</option>
										<option value="C3">C3</option>
										<option value="C4">C4</option>
										<option value="D">D</option>
										<option value="E">E</option>
										<option value="F">F</option>
										<option value="M">M</option>
										<option value="N">N</option>
										<option value="P">P</option>
									</select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">������λ��</div>
								<div class="input_text">
									<div class="text_show" id="grpName">${policy.appnt.grpName
										}</div>
									<input class="edit_input grpName" name="policy.appnt.grpName"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
								<div class="input_text">
									<div class="select_job">
										<jsp:include page="/web/common/searchJob.jsp"></jsp:include>
									</div>
								</div>
								<input id="appntJobCode" name="policy.appnt.jobCode"
									style="display: none" type="text"
									value="${policy.appnt.jobCode }" /> <input id="appntJobTemp"
									style="display: none" type="text" value="" />
							</div>
							<div class="input_col">
								<div class="input_label">�������䣺</div>
								<div class="input_text">
									<div class="text_show" id="email">${policy.appnt.email }</div>
									<input class="edit_input email" name="policy.appnt.email"
										type="text" maxlength="30" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">ʡ��</div>
								<div class="input_text">
									<div class="text_show" id="province">${policy.appnt.province
										}</div>
									<select id="aprovince" name="policy.appnt.province"
										class="edit_option province"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�ֻ����룺</div>
								<div class="input_text">
									<div class="text_show" id="mobile">${policy.appnt.mobile}</div>
									<!-- ���������ĵ��ӿ��޸�Ͷ�����ֻ����� -->
									<c:if test="${policy.riskAccount.riskScource !='NETS'}">
										<input class="edit_input mobile" id="mobileInput"
											name="policy.appnt.mobile" type="text" />
									</c:if>
									<c:if test="${policy.riskAccount.riskScource =='NETS'}">
										<input style="display: none;" name="policy.appnt.mobile"
											type="text" id="mobileInput" value="${policy.appnt.mobile}" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�У�</div>
								<div class="input_text">
									<div class="text_show" id="city">${policy.appnt.city }</div>
									<select id="acity" name="policy.appnt.city"
										class="edit_option city"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�칫�绰��</div>
								<div class="input_text">
									<div class="text_show" id="officePhone">${policy.appnt.officePhone}</div>
									<input class="edit_input officePhone officePhone"
										name="policy.appnt.officePhone" type="text" />
								</div>
							</div>
						</div>

						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��/�أ�</div>
								<div class="input_text">
									<div class="text_show" id="county">${policy.appnt.county}</div>
									<select id="acounty" name="policy.appnt.county"
										class="edit_option county"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">����绰��</div>
								<div class="input_text">
									<div class="text_show" id="fax">${policy.appnt.fax }</div>
									<input class="edit_input fax" name="policy.appnt.fax"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�������룺</div>
								<div class="input_text">
									<div class="text_show" id="homeZipCode">${policy.appnt.homeZipCode
										}</div>
									<input class="edit_input homeZipCode"
										name="policy.appnt.homeZipCode" type="text" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">סլ�绰��</div>
								<div class="input_text">
									<div class="text_show" id="phone">${policy.appnt.phone }</div>
									<input class="edit_input myPhone" name="policy.appnt.phone"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row address">
							<div class="input_col">
								<div class="input_label">��ϵ��ַ��</div>
								<div class="input_text">
									<div class="text_show" id="homeAddress">${policy.appnt.homeAddress
										}</div>
									<input class="edit_input homeAddress" style="width: 100%;"
										value="   " name="policy.appnt.homeAddress" type="text"
										maxlength="40" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="info_area insured_info">
					<div class="title">
						<span class="title_name">����������Ϣ</span>
						<div class="edit">
							<div class="save_edit click_btn">�����޸�</div>
							<div class="to_edit" id="to_edit_insured">�޸�</div>
							<div class="cancel_edit">ȡ��</div>
						</div>
					</div>
					<div class="input_area">
						<input type="text" class="action" style="display: none"
							value="insuredInfoChange.do" />
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.name }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�������ڣ�</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.birthDay }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֤�����ͣ�</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.idTypeName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show" id="marriage">${policy.insured.marriage
										}</div>
									<select name="policy.insured.marriage"
										class="edit_option marriage">
										<option value="">��</option>
										<option value="0">δ��</option>
										<option value="1">�ѻ�</option>
										<option value="2">����</option>
										<option value="3">ɥż</option>
										<option value="4">�־�</option>
										<option value="5">�ٻ�</option>
										<option value="6">ͬ��</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">֤�����룺</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.idNo}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show" id="nationality">${policy.insured.nationality
										}</div>
									<select name="policy.insured.nationality"
										class="edit_option nationality">
										<option value="">��</option>
										<option value="AUS">�Ĵ�����</option>
										<option value="CHN">�й�</option>
										<option value="ENG">Ӣ��</option>
										<option value="JAN">�ձ�</option>
										<option value="OTH">����</option>
										<option value="RUS">����˹</option>
										<option value="USA">����</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�������ͣ�</div>
								<div class="input_text">
									<div class="text_show" id="licenseType">${policy.insured.licenseType
										}</div>
									<select name="policy.insured.licenseType"
										class="edit_option licenseType">
										<option value="">��</option>
										<option value="A1">A1</option>
										<option value="A2">A2</option>
										<option value="A3">A3</option>
										<option value="B1">B1</option>
										<option value="B2">B2</option>
										<option value="C1">C1</option>
										<option value="C2">C2</option>
										<option value="C3">C3</option>
										<option value="C4">C4</option>
										<option value="D">D</option>
										<option value="E">E</option>
										<option value="F">F</option>
										<option value="M">M</option>
										<option value="N">N</option>
										<option value="P">P</option>
									</select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">������λ��</div>
								<div class="input_text">
									<div class="text_show" id="grpName">${policy.insured.grpName
										}</div>
									<input class="edit_input grpName" name="policy.insured.grpName"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
								<div class="input_text">
									<div class="text_show" id="jobType">${policy.insured.jobType
										}</div>
									<!-- ���ش�����Ͷ������Ϣ -->
									<div id="infoType" style="display: none;">1</div>

									<input style="display: none" name="policy.insured.jobCode"
										type="text" value="${policy.insured.jobCode }" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�������䣺</div>
								<div class="input_text">
									<div class="text_show" id="email">${policy.insured.email
										}</div>
									<input class="edit_input email" name="policy.insured.email"
										type="text" maxlength="30" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">ʡ��</div>
								<div class="input_text">
									<div class="text_show" id="province">${policy.insured.province
										}</div>
									<select id="iprovince" name="policy.insured.province"
										class="edit_option province"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�ֻ����룺</div>
								<div class="input_text">
									<div class="text_show" id="mobile">${policy.insured.mobile
										}</div>
									<!-- ���������ĵ��ӿ��޸�Ͷ�����ֻ����� -->
									<c:if test="${policy.riskAccount.riskScource !='NETS'}">
										<input class="edit_input mobile" name="policy.insured.mobile"
											id="mobileInput" type="text" />
									</c:if>
									<c:if test="${policy.riskAccount.riskScource =='NETS'}">
										<input style="display: none;" name="policy.insured.mobile"
											id="mobileInput" type="text" value="${policy.insured.mobile}" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�У�</div>
								<div class="input_text">
									<div class="text_show" id="city">${policy.insured.city }</div>
									<select id="icity" name="policy.insured.city"
										class="edit_option city"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�칫�绰��</div>
								<div class="input_text">
									<div class="text_show" id="officePhone">${policy.insured.officePhone}</div>
									<input class="edit_input officePhone"
										name="policy.insured.officePhone" type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��/�أ�</div>
								<div class="input_text">
									<div class="text_show" id="county">${policy.insured.county
										}</div>
									<select id="icounty" name="policy.insured.county"
										class="edit_option county"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">����绰��</div>
								<div class="input_text">
									<div class="text_show" id="fax">${policy.insured.fax }</div>
									<input class="edit_input fax" name="policy.insured.fax"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�������룺</div>
								<div class="input_text">
									<div class="text_show" id="homeZipCode">${policy.insured.homeZipCode
										}</div>
									<input class="edit_input homeZipCode"
										name="policy.insured.homeZipCode" type="text" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">סլ�绰��</div>
								<div class="input_text">
									<div class="text_show" id="phone">${policy.insured.phone
										}</div>
									<input class="edit_input myPhone" name="policy.insured.phone"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row address">
							<div class="input_col">
								<div class="input_label">��ϵ��ַ��</div>
								<div class="input_text">
									<div class="text_show" id="homeAddress">${policy.insured.homeAddress
										}</div>
									<input class="edit_input homeAddress" style="width: 100%;"
										value="   " name="policy.insured.homeAddress" type="text"
										maxlength="30" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="display: none">
					<input id="ctx" name="ctx" type="text" value="${ctx}" /> <input
						name="policySerialNumber" type="text" value="${policy.policyNo }" />
					<input name="policy.appnt.name" type="text"
						value="${policy.appnt.name }" /> <input name="policy.appnt.sex"
						type="text" value="${policy.appnt.sex }" /> <input
						name="policy.appnt.birthDay" type="text"
						value="${policy.appnt.birthDay }" /> <input
						name="policy.appnt.idType" type="text"
						value="${policy.appnt.idType }" /> <input
						name="policy.appnt.idNo" type="text" value="${policy.appnt.idNo }" />
					<input name="policy.insured.name" type="text"
						value="${policy.insured.name }" /> <input
						name="policy.insured.sex" type="text"
						value="${policy.insured.sex }" /> <input
						name="policy.insured.birthDay" type="text"
						value="${policy.insured.birthDay }" /> <input
						name="policy.insured.idType" type="text"
						value="${policy.insured.idType }" /> <input
						name="policy.insured.idNo" type="text"
						value="${policy.insured.idNo }" />
				</div>
			</form>
			<input id="userMobile" type="hidden" value="${userMobile }" />
			<!-- ������Դ -->
			<input id="scource" type="hidden"
				value="${policy.riskAccount.riskScource}">
			<div class="info_area beneficiary_info">
				<form action="#">
					<div class="title">
						<span class="title_name">��������Ϣ</span>
					</div>
					<div class="input_area">
						<div class="input_row name">
							<div class="input_col sequence">����˳��</div>
							<div class="input_col beneficiary_name">����������</div>
							<div class="input_col quotient">����ݶ�</div>
						</div>
						<!-- ��������Ϣ -->
						<div class="input_row" id="first_bnf" style="display: none">
							<div class="input_col sequence">��һ������</div>
							<div class="input_col beneficiary_name">����������</div>
							<div class="input_col quotient">100%</div>
						</div>
						<c:forEach var="bnf" items="${policy.bnfs}">
							<div class="input_row">
								<div class="input_col sequence">��${bnf.beneficiaryOrder}������</div>
								<div class="input_col beneficiary_name">${bnf.name }</div>
								<div class="input_col quotient">${bnf.interestPercent}</div>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>

			<div class="security_info">
				<div class="title">��ȫ��Ϣ�����¼</div>
				<div id="security_list" class="security_list">
					<c:if test="${count!=0}">
						<!-- ��ȫ��Ϣ�����¼ start -->
						<c:forEach var="edor" items="${edors }" varStatus="status">
							<div class="edors_item">
								<!-- �Ƿ�Ϊ���һ�� -->
								<c:if test="${(status.index+1)%3 == 0}">
									<div class="security_item last" id="security_item">
										<div class="accept_no">
											<span class="name">��ȫ�����</span><span class="value">${edor.acceptNo}</span>
										</div>
										<div class="security_row">
											<div class="name">�������ͣ�</div>
											<div class="value">${edor.edorType}</div>
										</div>
										<div class="security_row">
											<div class="name">��/�˷ѽ�</div>
											<div class="value">${edor.getMoney}</div>
										</div>
										<div class="security_row last">
											<div class="name">��Ч���ڣ�</div>
											<div class="value">${edor.edorValiDate }</div>
										</div>
										<!-- �Ƿ���ʾ���� �����ȫ����ŷ������̳ǣ����ṩ���ع��� -->
										<c:if test="${edor.edorSource =='MALL' }">
											<div class="security_bottom">
												<a href="javascript:downfile('${edor.acceptNo}');">����</a>
											</div>
										</c:if>
									</div>
								</c:if>

								<c:if test="${(status.index+1)%3 !=0 }">
									<div class="security_item" id="security_item">
										<div class="accept_no">
											<span class="name">��ȫ�����</span><span class="value">${edor.acceptNo}</span>
										</div>
										<div class="security_row">
											<div class="name">�������ͣ�</div>
											<div class="value">${edor.edorType}</div>
										</div>
										<div class="security_row">
											<div class="name">��/�˷ѽ�</div>
											<div class="value">${edor.getMoney}</div>
										</div>
										<div class="security_row last">
											<div class="name">��Ч���ڣ�</div>
											<div class="value">${edor.edorValiDate }</div>
										</div>
										<!-- �Ƿ���ʾ���� �����ȫ����ŷ������̳ǣ����ṩ���ع��� -->
										<c:if test="${edor.edorSource =='MALL' }">
											<div class="security_bottom">
												<a href="javascript:downfile('${edor.acceptNo}');">����</a>
											</div>
										</c:if>
									</div>
								</c:if>
							</div>
						</c:forEach>
						<!-- ��ȫ��Ϣ�����¼ end -->
					</c:if>
				</div>
			</div>
			<!-- ��ҳҳ�� -->
			<div class="page_index">
				<div class="page prev_page">
					<a>&nbsp;</a>
				</div>
				<div class="page next_page">
					<a>&nbsp;</a>
				</div>
			</div>

		</div>

	</div>
</div>
<input type="hidden" id="receiveFlag" name="receiveFlag" value="${receiveFlag}"/>

<Form id="down_from" method="post" enctype="multipart/form-data"
	target="downloadSubmit" action="${ctx}/myPolicyDetail/downloadEdor.do">
	<input type="hidden" id="url" name="url" />
</Form>
<iframe style="display: none" id="downloadSubmit" name="downloadSubmit"
	src="about:blank"></iframe>

