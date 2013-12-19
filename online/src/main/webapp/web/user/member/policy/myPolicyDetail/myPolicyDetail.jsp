<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx}">首页</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">会员首页</a><span>
						&gt;</span></li>
				<li><a href="${ctx}/info/initPolicyList.do">我的保单</a><span>
						&gt;</span></li>
				<li class="at">保单详情</li>
			</ul>
		</div>
		<div class="detail_main">
			<div class="base_info">
				<div class="title">${policy.riskAccount.riskName}</div>
				<div class="balance">

					<c:if
						test="${policy.riskAccount.riskScource =='NETS' &&policy.riskAccount.insuaccFlag=='1'}">

						<div class="receive click_btn">领&nbsp;&nbsp;&nbsp;&nbsp;取</div>
					</c:if>
					<!-- 网上商城的保单能领取 -->
					<c:if test="${policy.riskAccount.insuaccFlag =='1'}">
						<div class="money">
							<span class="label">账户余额：</span> <span class="int">￥<span
								id="total_amount">${policy.riskAccount.cashValue }</span></span>
						</div>
					</c:if>
				</div>
			</div>

			<div class="policy_info">
				<div class="policy_num">
					<span>保单号码：</span><span id='cont_no'>${policy.policyNo }</span>
					<div>
						<p>销售机构名称：${policy.comName }</p>
						<p>销售机构地址：${policy.comAddress}</p>
					</div>
				</div>
				<div class="info1">
					<div class="info_row">
						<div class="info_col first">
							<span class="tip">被保险人：</span><span>${policy.insured.name}</span>
						</div>
						<div class="info_col">
							<span class="tip">保险期间：</span>
							<!-- 如果是年 按照年显示 岁按照岁显示 -->
							<c:if
								test="${policy.mRisk.insuYear =='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<span>终身</span>
							</c:if>
							<c:if
								test="${policy.mRisk.insuYear !='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<span>${policy.mRisk.insuYear }年</span>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='D'}">
								<span>${policy.mRisk.insuYear }天</span>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='A'}">
								<span>${policy.mRisk.insuYear }年</span>
							</c:if>
							
							<c:if test="${policy.mRisk.insuYearFlag=='M'}">
								<span>${policy.mRisk.insuYear }月</span>
							</c:if>

						</div>
						<div class="info_col last">
							<span class="tip">生效日期：</span><span>${policy.valiDate }</span>
						</div>
					</div>
					<div class="info_row">
						<div class="info_col first">
							<span class="tip">保单状态：</span><span>${policy.state}</span>
						</div>
						<div class="info_col last visit">
							<span class="tip">是否回访：</span><span>${policy.isVisit }</span>
						</div>
					</div>
					<div style="display: none" class="risksStr">${risksStr}</div>
				</div>
				<div class="info2">
					<div class="info_row name">
						<div class="info_col insure_name">险种名称</div>
						<div class="info_col insure_amount">保险金额</div>
						<div class="info_col premium">保费</div>
						<div class="info_col period">保险期间</div>
						<div class="info_col effective_date">生效日期</div>
						<div class="info_col status last">保单状态</div>
					</div>
					<c:forEach var="risk" items="${policy.risks }">
						<div class="info_row value">
							<div class="info_col insure_name">${risk.riskName}</div>
							<div class="info_col insure_amount">${risk.amnt}</div>
							<div class="info_col premium">${risk.prem}</div>
							<!-- 如果是年 按照年显示 岁按照岁显示 -->
							<c:if
								test="${risk.insuYear =='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<div class="info_col period">终身</div>
							</c:if>
							<c:if
								test="${risk.insuYear !='1000' && policy.mRisk.insuYearFlag=='Y'}">
								<div class="info_col period">${risk.insuYear }年</div>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='D'}">
								<div class="info_col period">${policy.mRisk.insuYear }天</div>
							</c:if>
							<c:if test="${policy.mRisk.insuYearFlag=='A'}">
								<div class="info_col period">${policy.mRisk.insuYear }年</div>
							</c:if>
							
							<c:if test="${policy.mRisk.insuYearFlag=='M'}">
								<div class="info_col period">${policy.mRisk.insuYear }月</div>
							</c:if>
							
							<div class="info_col effective_date">${risk.cvaliDate}</div>
							<div class="info_col status last">${risk.state}</div>
						</div>
					</c:forEach>
				</div>
				<p class="info_more">
					<a href="#" onclick="productCashBnf(3);return false">现金价值</a><a
						href="#" onclick="productCashBnf(2);return false">保险利益</a><a
						href="#" onclick="productCashBnf(1);return false">产品条款</a>
				</p>
				<div style="display: none;" id="showSelect">${showSelect }</div>
			</div>

			<form id="fm" name="fm">
				<div class="info_area applicant_info">
					<div class="title">
						<span class="title_name">投保人信息</span>
						<div class="edit">
							<div class="save_edit click_btn">保存修改</div>
							<div class="to_edit" id="to_edit_appnt">修改</div>
							<div class="cancel_edit">取消</div>
						</div>
					</div>
					<div class="input_area">
						<input type="text" class="action" style="display: none"
							value="appntInfoChange.do" />
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.name}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">出生日期：</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.birthDay }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">证件类型：</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.idTypeName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姻：</div>
								<div class="input_text">
									<div class="text_show" id="marriage">${policy.appnt.marriage
										}</div>
									<select name="policy.appnt.marriage"
										class="edit_option marriage">
										<option value="">无</option>
										<option value="0">未婚</option>
										<option value="1">已婚</option>
										<option value="2">离异</option>
										<option value="3">丧偶</option>
										<option value="4">分居</option>
										<option value="5">再婚</option>
										<option value="6">同居</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">证件号码：</div>
								<div class="input_text">
									<div class="text_show">${policy.appnt.idNo}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍：</div>
								<div class="input_text">
									<div class="text_show" id="nationality">${policy.appnt.nationality}</div>
									<select name="policy.appnt.nationality"
										class="edit_option nationality">
										<option value="">无</option>
										<option value="AUS">澳大利亚</option>
										<option value="CHN">中国</option>
										<option value="ENG">英国</option>
										<option value="JAN">日本</option>
										<option value="OTH">其他</option>
										<option value="RUS">俄罗斯</option>
										<option value="USA">美国</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">驾照类型：</div>
								<div class="input_text">
									<div class="text_show" id="licenseType">${policy.appnt.licenseType}</div>
									<select name="policy.appnt.licenseType"
										class="edit_option licenseType">
										<option value="">无</option>
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
								<div class="input_label">工作单位：</div>
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
								<div class="input_label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</div>
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
								<div class="input_label">电子邮箱：</div>
								<div class="input_text">
									<div class="text_show" id="email">${policy.appnt.email }</div>
									<input class="edit_input email" name="policy.appnt.email"
										type="text" maxlength="30" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">省：</div>
								<div class="input_text">
									<div class="text_show" id="province">${policy.appnt.province
										}</div>
									<select id="aprovince" name="policy.appnt.province"
										class="edit_option province"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">手机号码：</div>
								<div class="input_text">
									<div class="text_show" id="mobile">${policy.appnt.mobile}</div>
									<!-- 不是网销的单子可修改投保人手机号码 -->
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
								<div class="input_label">市：</div>
								<div class="input_text">
									<div class="text_show" id="city">${policy.appnt.city }</div>
									<select id="acity" name="policy.appnt.city"
										class="edit_option city"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">办公电话：</div>
								<div class="input_text">
									<div class="text_show" id="officePhone">${policy.appnt.officePhone}</div>
									<input class="edit_input officePhone officePhone"
										name="policy.appnt.officePhone" type="text" />
								</div>
							</div>
						</div>

						<div class="input_row">
							<div class="input_col">
								<div class="input_label">区/县：</div>
								<div class="input_text">
									<div class="text_show" id="county">${policy.appnt.county}</div>
									<select id="acounty" name="policy.appnt.county"
										class="edit_option county"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">传真电话：</div>
								<div class="input_text">
									<div class="text_show" id="fax">${policy.appnt.fax }</div>
									<input class="edit_input fax" name="policy.appnt.fax"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">邮政编码：</div>
								<div class="input_text">
									<div class="text_show" id="homeZipCode">${policy.appnt.homeZipCode
										}</div>
									<input class="edit_input homeZipCode"
										name="policy.appnt.homeZipCode" type="text" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">住宅电话：</div>
								<div class="input_text">
									<div class="text_show" id="phone">${policy.appnt.phone }</div>
									<input class="edit_input myPhone" name="policy.appnt.phone"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row address">
							<div class="input_col">
								<div class="input_label">联系地址：</div>
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
						<span class="title_name">被保险人信息</span>
						<div class="edit">
							<div class="save_edit click_btn">保存修改</div>
							<div class="to_edit" id="to_edit_insured">修改</div>
							<div class="cancel_edit">取消</div>
						</div>
					</div>
					<div class="input_area">
						<input type="text" class="action" style="display: none"
							value="insuredInfoChange.do" />
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.name }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">出生日期：</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.birthDay }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">证件类型：</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.idTypeName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姻：</div>
								<div class="input_text">
									<div class="text_show" id="marriage">${policy.insured.marriage
										}</div>
									<select name="policy.insured.marriage"
										class="edit_option marriage">
										<option value="">无</option>
										<option value="0">未婚</option>
										<option value="1">已婚</option>
										<option value="2">离异</option>
										<option value="3">丧偶</option>
										<option value="4">分居</option>
										<option value="5">再婚</option>
										<option value="6">同居</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">证件号码：</div>
								<div class="input_text">
									<div class="text_show">${policy.insured.idNo}</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍：</div>
								<div class="input_text">
									<div class="text_show" id="nationality">${policy.insured.nationality
										}</div>
									<select name="policy.insured.nationality"
										class="edit_option nationality">
										<option value="">无</option>
										<option value="AUS">澳大利亚</option>
										<option value="CHN">中国</option>
										<option value="ENG">英国</option>
										<option value="JAN">日本</option>
										<option value="OTH">其他</option>
										<option value="RUS">俄罗斯</option>
										<option value="USA">美国</option>
									</select>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">驾照类型：</div>
								<div class="input_text">
									<div class="text_show" id="licenseType">${policy.insured.licenseType
										}</div>
									<select name="policy.insured.licenseType"
										class="edit_option licenseType">
										<option value="">无</option>
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
								<div class="input_label">工作单位：</div>
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
								<div class="input_label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</div>
								<div class="input_text">
									<div class="text_show" id="jobType">${policy.insured.jobType
										}</div>
									<!-- 隐藏代表是投保人信息 -->
									<div id="infoType" style="display: none;">1</div>

									<input style="display: none" name="policy.insured.jobCode"
										type="text" value="${policy.insured.jobCode }" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">电子邮箱：</div>
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
								<div class="input_label">省：</div>
								<div class="input_text">
									<div class="text_show" id="province">${policy.insured.province
										}</div>
									<select id="iprovince" name="policy.insured.province"
										class="edit_option province"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">手机号码：</div>
								<div class="input_text">
									<div class="text_show" id="mobile">${policy.insured.mobile
										}</div>
									<!-- 不是网销的单子可修改投保人手机号码 -->
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
								<div class="input_label">市：</div>
								<div class="input_text">
									<div class="text_show" id="city">${policy.insured.city }</div>
									<select id="icity" name="policy.insured.city"
										class="edit_option city"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">办公电话：</div>
								<div class="input_text">
									<div class="text_show" id="officePhone">${policy.insured.officePhone}</div>
									<input class="edit_input officePhone"
										name="policy.insured.officePhone" type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">区/县：</div>
								<div class="input_text">
									<div class="text_show" id="county">${policy.insured.county
										}</div>
									<select id="icounty" name="policy.insured.county"
										class="edit_option county"></select>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">传真电话：</div>
								<div class="input_text">
									<div class="text_show" id="fax">${policy.insured.fax }</div>
									<input class="edit_input fax" name="policy.insured.fax"
										type="text" />
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">邮政编码：</div>
								<div class="input_text">
									<div class="text_show" id="homeZipCode">${policy.insured.homeZipCode
										}</div>
									<input class="edit_input homeZipCode"
										name="policy.insured.homeZipCode" type="text" />
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">住宅电话：</div>
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
								<div class="input_label">联系地址：</div>
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
			<!-- 保单来源 -->
			<input id="scource" type="hidden"
				value="${policy.riskAccount.riskScource}">
			<div class="info_area beneficiary_info">
				<form action="#">
					<div class="title">
						<span class="title_name">受益人信息</span>
					</div>
					<div class="input_area">
						<div class="input_row name">
							<div class="input_col sequence">受益顺序</div>
							<div class="input_col beneficiary_name">受益人姓名</div>
							<div class="input_col quotient">受益份额</div>
						</div>
						<!-- 受益人信息 -->
						<div class="input_row" id="first_bnf" style="display: none">
							<div class="input_col sequence">第一受益人</div>
							<div class="input_col beneficiary_name">法定收益人</div>
							<div class="input_col quotient">100%</div>
						</div>
						<c:forEach var="bnf" items="${policy.bnfs}">
							<div class="input_row">
								<div class="input_col sequence">第${bnf.beneficiaryOrder}受益人</div>
								<div class="input_col beneficiary_name">${bnf.name }</div>
								<div class="input_col quotient">${bnf.interestPercent}</div>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>

			<div class="security_info">
				<div class="title">保全信息变更记录</div>
				<div id="security_list" class="security_list">
					<c:if test="${count!=0}">
						<!-- 保全信息变更记录 start -->
						<c:forEach var="edor" items="${edors }" varStatus="status">
							<div class="edors_item">
								<!-- 是否为最后一行 -->
								<c:if test="${(status.index+1)%3 == 0}">
									<div class="security_item last" id="security_item">
										<div class="accept_no">
											<span class="name">保全受理号</span><span class="value">${edor.acceptNo}</span>
										</div>
										<div class="security_row">
											<div class="name">批改类型：</div>
											<div class="value">${edor.edorType}</div>
										</div>
										<div class="security_row">
											<div class="name">补/退费金额：</div>
											<div class="value">${edor.getMoney}</div>
										</div>
										<div class="security_row last">
											<div class="name">生效日期：</div>
											<div class="value">${edor.edorValiDate }</div>
										</div>
										<!-- 是否显示下载 如果保全受理号非网上商城，不提供下载功能 -->
										<c:if test="${edor.edorSource =='MALL' }">
											<div class="security_bottom">
												<a href="javascript:downfile('${edor.acceptNo}');">下载</a>
											</div>
										</c:if>
									</div>
								</c:if>

								<c:if test="${(status.index+1)%3 !=0 }">
									<div class="security_item" id="security_item">
										<div class="accept_no">
											<span class="name">保全受理号</span><span class="value">${edor.acceptNo}</span>
										</div>
										<div class="security_row">
											<div class="name">批改类型：</div>
											<div class="value">${edor.edorType}</div>
										</div>
										<div class="security_row">
											<div class="name">补/退费金额：</div>
											<div class="value">${edor.getMoney}</div>
										</div>
										<div class="security_row last">
											<div class="name">生效日期：</div>
											<div class="value">${edor.edorValiDate }</div>
										</div>
										<!-- 是否显示下载 如果保全受理号非网上商城，不提供下载功能 -->
										<c:if test="${edor.edorSource =='MALL' }">
											<div class="security_bottom">
												<a href="javascript:downfile('${edor.acceptNo}');">下载</a>
											</div>
										</c:if>
									</div>
								</c:if>
							</div>
						</c:forEach>
						<!-- 保全信息变更记录 end -->
					</c:if>
				</div>
			</div>
			<!-- 分页页码 -->
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

