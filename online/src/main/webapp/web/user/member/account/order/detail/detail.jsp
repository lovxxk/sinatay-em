<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/user/member/common/dataDemo.jsp" %>
<script src="${ctx }/global/js/My97DatePicker4.7/WdatePicker.js" type="text/javascript"></script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx}/memberCenter/homePage.do">��Ա��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/order/orders.do">�ҵĶ���</a><span> &gt;</span></li>
				<li class="at">��������</li>
			</ul>
		</div>
		<form action="${ctx}/order/saveInitEffectStartDate.do" method="post">
		<input type="hidden" name="serialNo" id="serialNo" value="${orderForm.serialNo }">
		<div class="detail_main">
<%-- 			<font color="red">${message }</font> --%>
<%-- 			<font color="red">${orderMessage }</font> --%>
			<c:if test="${!empty inceptionDateMessage }">
				<font color="red">${inceptionDateMessage }</font>
			</c:if>
			<div class="base_info">
				<div class="title">${orderForm.productName }</div>
				<div class="balance">
					<!-- ����״̬Ϊδ֧��,�˱�״̬Ϊ���˱�ͨ���� -->
					<c:if test="${orderForm.orderStatus == 82 && orderForm.insurancePolicy.policyStatus == 2}">
						<!-- <div class="pay_action"><a class="pay_action_a"     ${ctx }/payment/toPayment.do?id=${orderForm.serialNo }  -->
						<div class="receive click_btn"><a style="color: #fff;" href="#" onclick="toPayConfirmInfo('${orderForm.insurancePolicy.productCode }','${orderForm.insurancePolicy.serialNo }');">֧&nbsp;&nbsp;&nbsp;&nbsp;��</a></div>
					</c:if>
					<c:if test="${orderForm.orderStatus == 10 || orderForm.insurancePolicy.policyStatus == 3 }">
						<div class="receive click_btn"><a style="color: #fff;" target="_blank" href="${ctx}/sale/obtainContinueInsuranceData.do?quoteNo=${orderForm.insurancePolicy.quoteNo }&productCode=${orderForm.insurancePolicy.productCode }&serialNo=${orderForm.insurancePolicy.serialNo }&proposalContNo=${orderForm.insurancePolicy.applicationNumber }&policyStatus=${orderForm.insurancePolicy.policyStatus }">��&nbsp;��&nbsp;��&nbsp;��&nbsp;</a></div>
					</c:if>
					<div class="money">
						<span class="label">���ѣ�</span>
						<span class="int">
							��<span id="total_amount">${orderForm.orderAmount }</span>
						</span>
						<span class="decimal"></span>
					</div>
				</div>
			</div>
			
			<div class="order_info">
				<div class="order_num">
					<span>�������룺${orderForm.orderSerialNumber }</span>
				</div>
				<div class="info2">
					<div class="info_row name">
						<div class="info_col first insure_name">��������</div>
						<div class="info_col insure_amount">���ս��</div>
						<div class="info_col last premium">����</div>
					</div>
					<c:forEach items="${orderForm.insurancePolicy.insurancePolicyLiabilities }" var="liabilities">
						<div class="info_row value">
							<div class="info_col first insure_name">${liabilities.liabilityName }</div>
							<div class="info_col insure_amount">
								<c:if test="${orderForm.insurancePolicy.policyStatus eq 1}">
									${liabilities.insuredAmount }
								</c:if>
								<c:if test="${orderForm.insurancePolicy.policyStatus ne 1}">
									${liabilities.grossInsuredAmount }
								</c:if>
							</div>
							<div class="info_col last premium">��
								<c:if test="${orderForm.insurancePolicy.policyStatus eq 1}">
									${liabilities.premium }
								</c:if>
								<c:if test="${orderForm.insurancePolicy.policyStatus ne 1}">
									${liabilities.grossPremium }
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="info1">
					<c:forEach items="${orderForm.insurancePolicy.insureds }" var="insured">
						<div class="info_row">
							<div class="info_col first">
								<span class="tip">�������ˣ�</span><span>${insured.fullName }</span>
							</div>
							<div class="info_col" >
								<div id="periodInput"></div>
								<span class="tip">��Ч���ڣ�</span>
								<span>
									<c:if test="${!empty orderForm.insurancePolicy.inceptionDate}">
										<fmt:formatDate value="${orderForm.insurancePolicy.inceptionDate }" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${empty orderForm.insurancePolicy.inceptionDate }">
										<input class="input_text Wdate" name="effect_start" type="text" id="effect_start" onclick="initEffectStartDate();"/>
										<label class="tip_label" for="birthday">��ʱ��</label>
										<button type="submit">����</button>
									</c:if>
								</span>
							</div>
							<div class="info_col last">
								<span class="tip">����״̬��</span>
								<span>
									<c:if test="${orderForm.orderStatus == 81 }">
										֧���ɹ�
									</c:if>
									<c:if test="${orderForm.orderStatus == 82 }">
										δ֧��
									</c:if>
									<c:if test="${orderForm.orderStatus == 83 }">
										֧����
									</c:if>
									<c:if test="${orderForm.orderStatus == 84 }">
										��֧��
									</c:if>
								</span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="info_area applicant_info">
					<div class="title">
						<span class="title_name">Ͷ������Ϣ</span>
						<div class="edit">
							<div class="save_edit click_btn">�����޸�</div>
<!-- 							<div class="to_edit">�޸�</div> -->
						</div>
					</div>
					<div class="input_area">
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.fullName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">֤�����ͣ�</div>
								<div class="input_text">
									<div class="text_show">
										<c:forEach items="${idTypeList }" var="code">
											<c:if test="${code.id.codeCode eq orderForm.insurancePolicy.insuranceApplicant.idType }">${code.codeCName }</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</div>
								<div class="input_text">
									<div class="text_show">
										<c:forEach items="${sexList }" var="code">
											<c:if test="${code.id.codeCode eq orderForm.insurancePolicy.insuranceApplicant.gender }">${code.codeCName }</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">֤�����룺</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.idNumber }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�ֻ����룺</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.mobilePhoneNumber }</div>
									<input class="edit_input" name="cellphone" type="text"/>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�������ڣ�</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.birthDate }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">�������䣺</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.email }</div>
									<input class="edit_input" name="email" type="text"/>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.occupationDescription }</div>
								</div>
							</div>
						</div>
						<div class="input_row">
							<div class="input_col">
								<div class="input_label">ʡ�ݳ��У�</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.provinceName }&nbsp;${orderForm.insurancePolicy.insuranceApplicant.cityName }&nbsp;${orderForm.insurancePolicy.insuranceApplicant.countyName }</div>
								</div>
							</div>
							<div class="input_col">
								<div class="input_label">�������룺</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.postalCode }</div>
								</div>
							</div>
						</div>
						<div class="input_row address">
							<div class="input_col">
								<div class="input_label">��ϵ��ַ��</div>
								<div class="input_text">
									<div class="text_show">${orderForm.insurancePolicy.insuranceApplicant.addressLines }</div>
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
<!-- 							<div class="to_edit">�޸�</div> -->
						</div>
					</div>
					<c:forEach items="${orderForm.insurancePolicy.insureds }" var="insured">
						<div class="input_area">
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">��Ͷ���˹�ϵ��</div>
									<div class="input_text">
										<div class="text_show">
											<c:forEach items="${insRelaToAppList }" var="code">
												<c:if test="${code.id.codeCode eq insured.relatedToApplicant }">${code.codeCName }</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
									<div class="input_text">
										<div class="text_show">${insured.fullName }</div>
									</div>
								</div>
								<div class="input_col">
									<div class="input_label">֤�����ͣ�</div>
									<div class="input_text">
										<div class="text_show">
											<c:forEach items="${idTypeList }" var="code">
												<c:if test="${code.id.codeCode eq insured.idType }">${code.codeCName }</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</div>
									<div class="input_text">
										<div class="text_show">
											<c:forEach items="${sexList }" var="code">
												<c:if test="${code.id.codeCode eq insured.gender }">${code.codeCName }</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="input_col">
									<div class="input_label">֤�����룺</div>
									<div class="input_text">
										<div class="text_show">${insured.idNumber }</div>
									</div>
								</div>
							</div>
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">�ֻ����룺</div>
									<div class="input_text">
										<div class="text_show">${insured.mobilePhoneNumber }</div>
										<input class="edit_input" name="cellphone" type="text"/>
									</div>
								</div>
								<div class="input_col">
									<div class="input_label">�������ڣ�</div>
									<div class="input_text">
										<div class="text_show">${insured.birthDate }</div>
									</div>
								</div>
							</div>
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">�������䣺</div>
									<div class="input_text">
										<div class="text_show">${insured.email }</div>
										<input class="edit_input" name="email" type="text"/>
									</div>
								</div>
								<div class="input_col">
									<div class="input_label">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
									<div class="input_text">
										<div class="text_show">${insured.occupationDescription }</div>
									</div>
								</div>
							</div>
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">ʡ�ݳ��У�</div>
									<div class="input_text">
										<div class="text_show">${insured.provinceName }&nbsp;${insured.cityName }&nbsp;${insured.countyName }</div>
									</div>
								</div>
								<div class="input_col">
									<div class="input_label">�������룺</div>
									<div class="input_text">
										<div class="text_show">${insured.postalCode }</div>
									</div>
								</div>
							</div>
							<div class="input_row address">
								<div class="input_col">
									<div class="input_label">��ϵ��ַ��</div>
									<div class="input_text">
										<div class="text_show">${insured.addressLines }</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="info_area insured_info"><!-- beneficiary_info -->
					<div class="title">
						<span class="title_name">��������Ϣ</span>
					</div>
					<c:if test="${empty orderForm.insurancePolicy.beneficiaries}">
						<div class="input_area">
							<div class="input_row">
								<div class="input_col">
									<div class="input_label">�뱻���˹�ϵ��</div>
									<div class="input_text">
										<div class="text_show">����������</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${!empty orderForm.insurancePolicy.beneficiaries}">
						<c:forEach items="${orderForm.insurancePolicy.beneficiaries }" var="beneficiary" varStatus="count">
							<div class="input_area">
								<div class="input_row">
									<div class="input_col">
										<div class="input_label">�뱻���˹�ϵ��</div>
										<div class="input_text">
											<div class="text_show">
												<c:forEach items="${insRelaToAppList }" var="code">
													<c:if test="${code.id.codeCode eq beneficiary.relatedToInsured }">${code.codeCName }</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<div class="input_row">
									<div class="input_col">
										<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.fullName }</div>
										</div>
									</div>
									<div class="input_col">
										<div class="input_label">֤�����ͣ�</div>
										<div class="input_text">
											<div class="text_show">
												<c:forEach items="${idTypeList }" var="code">
													<c:if test="${code.id.codeCode eq beneficiary.idType }">${code.codeCName }</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<div class="input_row">
									<div class="input_col">
										<div class="input_label">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</div>
										<div class="input_text">
											<div class="text_show">
												<c:forEach items="${sexList }" var="code">
													<c:if test="${code.id.codeCode eq beneficiary.gender }">${code.codeCName }</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="input_col">
										<div class="input_label">֤�����룺</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.idNumber }</div>
										</div>
									</div>
								</div>
								<div class="input_row">
									<div class="input_col">
										<div class="input_label">����˳��</div>
										<div class="input_text">
											<div class="text_show">��&nbsp;${beneficiary.beneficiaryOrder }&nbsp;������</div>
										</div>
									</div>
									<div class="input_col">
										<div class="input_label">�������ڣ�</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.birthDate }</div>
										</div>
									</div>
								</div>
								<div class="input_row">
									<div class="input_col">
										<div class="input_label">����ݶ</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.interestPercent }&nbsp;%</div>
										</div>
									</div>
									<div class="input_col" style="display:none">
										<div class="input_label">�ֻ����룺</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.mobilePhoneNumber }</div>
										</div>
									</div>
								</div>
								<div class="input_row" style="display:none">
									<div class="input_col">
										<div class="input_label">�������䣺</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.email }</div>
										</div>
									</div>
									<div class="input_col">
										<div class="input_label">ְ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ��</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.occupationDescription }</div>
										</div>
									</div>
								</div>
								<div class="input_row" style="display:none">
									<div class="input_col">
										<div class="input_label">ʡ�ݳ��У�</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.provinceName }&nbsp;${beneficiary.cityName }&nbsp;${beneficiary.countyName }</div>
										</div>
									</div>
									<div class="input_col">
										<div class="input_label">�������룺</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.postalCode }</div>
										</div>
									</div>
								</div>
								<div class="input_row" style="display:none">
									<div class="input_col">
										<div class="input_label">��ϵ��ַ��</div>
										<div class="input_text">
											<div class="text_show">${beneficiary.addressLines }</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
var effectDateT = "${geSaleProduct.effectDateType}";
var inceptionDate = "${inceptionDate}";
var minInceptionDate = "${inceptionDate}";
var maxInceptionDate = "${inceptionDate}";

if(effectDateT == '01'){
	$('#effect_start').attr("value", inceptionDate);
	$('#effect_start').attr("readonly","readonly");
	$('#specifyStartDate').text(inceptionDate);
}
if(effectDateT == '03'){
	minInceptionDate = inceptionDate.split("|")[0];
	maxInceptionDate = inceptionDate.split("|")[1];
}
// alert("minInceptionDate>>" + minInceptionDate);
// alert("maxInceptionDate>>" + maxInceptionDate);
function initEffectStartDate(){
	WdatePicker({
		el:'effect_start',
		//startDate:'%y-%M-%d',
		minDate:minInceptionDate,
		maxDate:maxInceptionDate,
		dateFmt:'yyyy-MM-dd'
	});
}


// $('#effect_start').blur(function(){
// 	$('#specifyStartDate').text($(this).val());
// });
/**
 * ��ӱ����ڼ� 
 */
function addPeriod() {
	$("#periodInput").html("");
	var periods = $("#periodTable tr");
	var periodStr = "";
	for (var i = 1; i < periods.length; i++) {
		var period = $(periods[i]).find("input");
		var attributeTypeValue = $(periods[i]).find("select");
		periodStr += "<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeKind\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeKind\" value=\"" + period[1].value + "\" />" + 
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeName\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeName\" value=\"" + period[2].value + "\" />" + 
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].allowValuesType\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].allowValuesType\" value=\"" + period[3].value + "\" />" + 
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeType\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeType\" value=\"" + period[4].value + "\" />" + 
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].geProductMain.coreProductCode\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].geProductMain.coreProductCode\" value=\"" + period[5].value + "\" />" +  
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeValue\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeValue\" value=\"" + period[6].value + "\" />" +
			"<input type=\"hidden\" id=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeTypeValue\" name=\"geProductMain.geProductAttrAllowValueses[" + (i - 1) + "].attributeTypeValue\" value=\"" + attributeTypeValue[0].value + "\" />";
	}
	$("#periodInput").append(periodStr);
}
function saveInitEffectStartDate() {
	var effect_start = $("#effect_start").val();
	var serialNo = $("#serialNo").val();
	$.ajax({
		url : '${ctx}/order/saveInitEffectStartDate.do',
		type : 'POST',
		data : {effect_start : effect_start, id : serialNo},
		dataType : "text",
		success : function(data){
// 			alert("test");
		}
	});
}
$(document).ready(function () {
	
});
</script>
</div>