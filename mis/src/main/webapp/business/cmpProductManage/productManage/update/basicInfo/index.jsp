<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
	<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
	<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
	<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
	<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
	<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>电子商务后台管理系统-非车险产品管理-产品基本信息</title>
	<style type="text/css">
		 textarea {
		 	margin-bottom:4px;
		 }
		 .table_Show{
		 	line-height: 20px;
		 }
	</style>
</head>
<body>
<div>
<form id="frmInput1" name="frmInput1" method="post" action="${ctx }/productManage/saveOrUpdateProductDetail.do" target="myFrame">
	<input type="hidden" name="geProductMain.coreProductCode" value="${geProductMain.coreProductCode}" />
	<input type="hidden" name="geProductMain.productName" value="${geProductMain.productName}" />
	<input type="hidden" name="geProductMain.coreProductSimpleName" value="${geProductMain.coreProductSimpleName}" />
	<input type="hidden" name="geProductMain.businessArea" value="${geProductMain.businessArea}" />
	<input type="hidden" name="geProductMain.productStatus" value="${geProductMain.productStatus}" />
	<input type="hidden" name="geProductMain.createDate" value="${geProductMain.createDate}" />
	<input type="hidden" name="geProductMain.productFlow" value="${geProductMain.productFlow}" />
	<input type="hidden" name="geProductMain.operatorID" value="${geProductMain.operatorID}" />
	<input type="hidden" id="deptidSave" name="deptidSave" value="" />
	<div style="height:10px;"></div>
	<table class="table_style" align="center" width="90%" >
		<tr>
			<td class="td_head" nowrap>产品代码:</td>
			<td class="td_body" >
				<input type="text" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" maxlength="50" value="<s:property value='geProductMain.coreProductCode'/>" onblur="" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>产品全称:</td>
			<td class="td_body" >
				<input type="text" id="geProductMain.productName" name="geProductMain.productName" maxlength="50"  value="<s:property value='geProductMain.productName'/>" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>产品简称:</td>
			<td class="td_body" >
				<input type="text" id="geProductMain.coreProductSimpleName" name="geProductMain.coreProductSimpleName" maxlength="20" value="<s:property value='geProductMain.coreProductSimpleName'/>" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>纸质保单配置项:</td>
			<td class="td_body" >
				<select id="geProductMain.isPaper" name="geProductMain.isPaper">
					<option value="" >---请选择---</option>
					<option value="1" <c:if test="${geProductMain.isPaper eq '1'}">selected</c:if> >显示</option>
					<option value="0" <c:if test="${geProductMain.isPaper eq '0'}">selected</c:if> >不显示</option>
				</select>
			</td>
		</tr>
		<tr id = "supportTarget" style="display: none;">
			<td class="td_head" nowrap>
				标的个数:
			</td>
			<td class="td_body" >
				<select id="geProductMain.isSupportTarget" name="geProductMain.isSupportTarget">
					<option value="" >---请选择---</option>
					<option value="1" <c:if test="${geProductMain.isSupportTarget eq '1'}">selected</c:if> >显示</option>
					<option value="0" <c:if test="${geProductMain.isSupportTarget eq '0'}">selected</c:if> >不显示</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				是否显示发票:
			</td>
			<td class="td_body" >
				<select id="isInvoice" name="geProductMain.isInvoice">
					<option value="" >---请选择---</option>
					<option value="02" <c:if test="${geProductMain.isInvoice eq '02'}">selected</c:if> >显示</option>
					<option value="01" <c:if test="${geProductMain.isInvoice eq '01'}">selected</c:if> >不显示</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				是否限定同业保额:
			</td>
			<td class="td_body" >
				<select id="geProductMain.limitSameIndInsuredAmount" name="geProductMain.limitSameIndInsuredAmount" onchange="showlimitSameIndInsuredAmount(this.value);" >
					<option value="" >---请选择---</option>
					<option value="01" <c:if test="${geProductMain.limitSameIndInsuredAmount eq '01'}">selected</c:if> >是</option>
					<option value="02" <c:if test="${geProductMain.limitSameIndInsuredAmount eq '02'}">selected</c:if> >否</option>
				</select>
				<span id="IslimitSameIndInsuredAmount">
					&nbsp;&nbsp;同业保额最大值：
					<input type="text" id="geProductMain.sameIndInsuredAmountMax" name="geProductMain.sameIndInsuredAmountMax" style="width:50px;" value="${geProductMain.sameIndInsuredAmountMax eq '' or geProductMain.sameIndInsuredAmountMax eq null ? '1' : geProductMain.sameIndInsuredAmountMax}">
					万元
				</span>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>紧急联系人标记:</td>
			<td class="td_body" >
				<select id="geProductMain.isSupportEmergency" name="geProductMain.isSupportEmergency">
					<option value="" >---请选择---</option>
					<option value="01" <c:if test="${geProductMain.isSupportEmergency eq '01'}">selected</c:if> >支持</option>
					<option value="02" <c:if test="${geProductMain.isSupportEmergency eq '02'}">selected</c:if> >不支持</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>法定受益人标记:</td>
			<td class="td_body" >
				<select id="geProductMain.isSupportBeneficiary" name="geProductMain.isSupportBeneficiary" onchange="showBenficiary(this.value)">
					<option value="" >---请选择---</option>
					<option value="0" <c:if test="${geProductMain.isSupportBeneficiary eq '0'}">selected</c:if> >法定</option>
					<option value="1" <c:if test="${geProductMain.isSupportBeneficiary eq '1'}">selected</c:if> >非法定</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td style="color:#616161;">
				<span id="beneficiary">
					最小受益人数：
					<input type="text" id="geProductMain.minBeneficiaryNum" name="geProductMain.minBeneficiaryNum" maxlength="20" value="<s:property value='geProductMain.minBeneficiaryNum'/>" style='width:50px;'>&nbsp;个
					&nbsp;&nbsp;最大受益人数：
					<input type="text" id="geProductMain.maxBeneficiaryNum" name="geProductMain.maxBeneficiaryNum" maxlength="20" value="<s:property value='geProductMain.maxBeneficiaryNum'/>" style='width:50px;'>&nbsp;个
				</span>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div id='maxBene_msg'></div>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				是否支持连带被保人:
			</td>
			<td class="td_body" >
				<select id="geProductMain.isSupportPIns" name="geProductMain.isSupportPIns" onchange="showPInsuredNumIterms(this.value);" >
					<option value="" >---请选择---</option>
					<option value="01" <c:if test="${geProductMain.isSupportPIns eq '01'}">selected</c:if> >支持</option>
					<option value="02" <c:if test="${geProductMain.isSupportPIns eq '02'}">selected</c:if> >不支持</option>
				</select>
				
			</td>
		</tr>
		<tr>
			<td></td>
			<td style="color:#616161;">
				<span id="IsSupportPInsLable">
					最小被保人数：
					<input type="text" id="geProductMain.minPInsuredNum" name="geProductMain.minPInsuredNum" style="width:50px;" value="${geProductMain.minPInsuredNum eq '' or geProductMain.minPInsuredNum eq null ? '1' : geProductMain.minPInsuredNum}">&nbsp;人
					&nbsp;&nbsp;最大被保人数：
					<input type="text" id="geProductMain.maxPInsuredNum" name="geProductMain.maxPInsuredNum" style="width:50px;" value="${geProductMain.maxPInsuredNum eq '' or geProductMain.maxPInsuredNum eq null ? '1' : geProductMain.maxPInsuredNum}">&nbsp;人
				</span>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div id='maxPeople_msg'></div>
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				生效日期类型:
			</td>
			<td class="td_body"  >
				<select id="geProductMain.effectDateType" name="geProductMain.effectDateType" onchange="showEffectTypeIterms(this.value);">
					<option value="" selected="selected">---请选择---</option>
					<option value="01" <c:if test="${geProductMain.effectDateType eq '01'}">selected</c:if>>默认</option>
					<option value="02" <c:if test="${geProductMain.effectDateType eq '02'}">selected</c:if>>指定</option>
					<option value="03" <c:if test="${geProductMain.effectDateType eq '03'}">selected</c:if>>自定义</option>
					<option value="04" <c:if test="${geProductMain.effectDateType eq '04'}">selected</c:if>>延迟生效</option>
				</select>
				<span id="EffectType01" style="display: none">
					&nbsp;&nbsp;次日凌晨生效
				</span>
				<span id="EffectType04" style="display: none">
					&nbsp;&nbsp;延迟生效天数：
					<input type="text" value="${geProductMain.delayDays}" id="geProductMain.delayDays" name="geProductMain.delayDays" style="width:50px;">&nbsp;天
				</span>
			</td>
		</tr>
		<tr>
			<td></td>
			<td style="color:#616161;">
				<span id="EffectType02" style="display: none">
					起始日期：
					<input type="text" style="width:90px;" value="<fmt:formatDate value='${geProductMain.specifyStartDate}' pattern= 'yyyy-MM-dd'/>" id="geProductMain.specifyStartDate" name="geProductMain.specifyStartDate" readonly onclick="WdatePicker({minDate:'%y-%M-%d'})" class="Wdate">
					&nbsp;&nbsp;终止日期：
					<input type="text" style="width:90px;"  value="<fmt:formatDate value="${geProductMain.specifyEndDate}" pattern= 'yyyy-MM-dd'/>" id="geProductMain.specifyEndDate" name="geProductMain.specifyEndDate" readonly onclick="WdatePicker({minDate:'%y-%M-%d'})" class="Wdate">
				</span>
				<span id="EffectType03" style="display: none">
					最小限制天数：
					<input type="text" value="${geProductMain.minEffectDateLimited}" id="geProductMain.minEffectDateLimited" name="geProductMain.minEffectDateLimited" style="width:50px;">&nbsp;天
					&nbsp;&nbsp;最大限制天数：
					<input type="text" value="${geProductMain.maxEffectDateLimited}" id="geProductMain.maxEffectDateLimited" name="geProductMain.maxEffectDateLimited" style="width:50px;">&nbsp;天
				</span>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div id='maxDate_msg'></div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div id='maxLimited_msg'></div>
			</td>
		</tr>
		<tr valign="top">
			<td class="td_head" nowrap>
				保险期间配置:
			</td>
			<td>
				<div style="width:485px;">
					<table border="0" cellspacing="0" cellpadding="0" id="periodTable" width="480" class="table_Show">
						<tr>
							<td class="search_head" width="48" valign="middle">&nbsp;</td>
							<td class="search_head" width="200" valign="middle">保险期间</td>
							<td class="search_head" width="200" valign="middle">保险期间类型</td>
						</tr>
						<c:forEach items="${productAttrAllowValueList}"  var="productAttrAllowValue" step="1" varStatus="status">
							<tr height="25" id="periodTableTr_${status.index}">
								<td class="search_body" width="48" valign="top" nowrap>
									<input id="check${status.index}" type="checkbox" class="search_body" style="width:48px;border: 0px" onclick="checkSingleRow()" name="checkChild" value="periodTableTr_${status.index}" />
								</td>
								<td class="search_body" width="200" valign="top" nowrap>
									<input type="hidden" id="attributeKind${status.index}" name="attributeKind${status.index}" value="period" /> 
									<input type="hidden" id="attributeName${status.index}" name="attributeName${status.index}" value="保险期间" /> 
									<input type="hidden" id="allowValuesType${status.index}" name="allowValuesType${status.index}" value="01" /> 
									<input type="hidden" id="attributeType${status.index}" name="attributeType${status.index}" value="PeriodType" />
									<input type="hidden" id="coreProductCode${status.index}" name="coreProductCode${status.index}" value="${geProductMain.coreProductCode}" />  
									<input type="text" id="attributeValue${status.index}"  class="search_body"  name="attributeValue${status.index}" style="width:200px;height:23;margin:0px 0px 5px 0px;" value="${productAttrAllowValue.attributeValue}" /> 
								</td>
								<td  class="search_body" width="200" valign="top" nowrap>
									<select id="attributeTypeValue${status.index}" name="attributeTypeValue${status.index}" style="width:200px;height:23;margin:0px 0px 5px 0px;">
										<option value="">--请选择--</option>
										<c:forEach items="${periodTypeList}" var="GeCode_periodType" step="1" varStatus="status">
											<option value="${GeCode_periodType.id.codeCode}" ${productAttrAllowValue.attributeTypeValue == GeCode_periodType.id.codeCode ?"selected":""}>${GeCode_periodType.codeCName}</option>
										</c:forEach>
									</select>
								</td>
						 	 </tr>
						 	 <span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
						</c:forEach>
					</table>
					<div id="periodInput">
					</div>
					<div style="padding-top:5px;">
						<table align="right" id="addDutyOperator">
							<tr>
								<td id="addPeriod" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>添加</td>
								<td id="delPeriod"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>删除</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr height="100" valign="top">
			<td class="td_head" nowrap >
				特别约定:
			</td>
			<td class="td_body">
				<div style="width:475px;">
					<textarea id="specialAgreement" name="geProductMain.specialAgreement" rows="4" cols="75">${geProductMain.specialAgreement}</textarea>
				</div>
			</td>
		</tr>
		<tr height="100" valign="top">
			<td class="td_head" nowrap>
				备注:
			</td>
			<td class="td_body" >
				<div style="width:475px;">
					<textarea id="remark" name="geProductMain.remark" rows="4" cols="75">${geProductMain.remark}</textarea>
				</div>
			</td>
		</tr>
		<tr valign="top">
			<td class="td_head" nowrap>
				销售地区:
			</td>
			<td>
				<table class="frmCreate_kuang" >
					<tr>
						<td class="frmCreate_kuang_header"></td>
					</tr>
					<tr>
						<td  style="text-align:left; padding-left:15px; padding-top:15px;" valign="top">
							<div id="showListLoading" style="position:absolute;">
								<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
							</div>
							<div id="authorityTree" style="overflow-x:hidden;width:465px;"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="60px;">
			<td colspan="2" align="center">
				<table>
					<tr>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doNext();" nowrap>保存</td>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doReset();" nowrap >重置</td>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >关闭</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<iframe id="myFrame" name="myFrame" style="display:none"></iframe>
<input type="hidden" name="idStr" id="idStr" />
<input type="hidden" name="count" id="count" />
</div>
</body>
<script type="text/javascript">
var ids = ['specialAgreement','remark'];
   		var contents = ['为了限制责任，经保险公司和投保人共同约定，附加在保单上的特殊协议和条款 。','产品配置的一些说明信息，前台产品页面不会展示。'];
       	for ( var i = 0 ; i < ids.length ; i++ ){
   			$('#' + ids[i]).qtip({ 
   				content:contents[i], 
   				position: { 
   					corner: { 
   					tooltip: 'leftMiddle', 
   					target: 'rightMiddle'
   					} 
   				}, 
   				 style: { 
   				border: { 
   					width: 2,
   					radius: 2,
   					color: '#00739f'
   					},
   					width:100,
   					padding: 10, 
   					textAlign: 'left',
   					background: '#e0f2ff', 
   					tip:true//控制左侧尖角是否出现
   					//name: 'green' 
   				} 
   			}); 
       	}
$(document).ready(function () {
	tt.vf.req.add("geProductMain.minInsuredNum", "geProductMain.maxInsuredNum", "geProductMain.minBeneficiaryNum"
			,"geProductMain.maxBeneficiaryNum","geProductMain.isSupportPIns","geProductMain.effectDateType"
			,"geProductMain.isPaper","geProductMain.isSupportBeneficiary","geProductMain.isSupportEmergency"
			,"geProductMain.limitSameIndInsuredAmount","geProductMain.isInvoice","geProductAttrAllowValues.allowValuesType");
	tt.vf.int.add("geProductMain.minPInsuredNum","geProductMain.maxPInsuredNum");
	showPInsuredNumIterms("${geProductMain.isSupportPIns}");		// 是否支持连带被保人
	showlimitSameIndInsuredAmount("${geProductMain.limitSameIndInsuredAmount}");
	showBenficiary("${geProductMain.isSupportBeneficiary}");		// 法定受益人标记
	showEffectTypeIterms("${geProductMain.effectDateType}");		// 生效日期类型
	if('${geProductMain.businessArea}' == '3') {
		$("#supportTarget").show();
		tt.vf.req.add("geProductMain.isSupportTarget");
	}
	var productAttrAllowValueListSize = "${productAttrAllowValueListSize}"; 
	if (productAttrAllowValueListSize == 0) {
		$("#addPeriod").trigger("click");
	}
	for(var i = 0; i < productAttrAllowValueListSize; i++){
		checkPeriod("attributeValue" + i,"attributeTypeValue" + i);	
	}
});
//下面这一行代码为险种责任3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
var NRVattributeValue = new tt.NRV().set(1, '++');
new tt.LV().set(0,500).add("geProductMain.specialAgreement");
new tt.LV().set(0,500).add("geProductMain.remark");
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
$("textarea").blur(function(){
	validateValue(this);
});
function validateValue(obj) {
	var patn = /(^\s)|(\s$)/;
	if (patn.test(obj.value))
		obj.value = obj.value.trim();
}	
//比较验证
var cvDate = new tt.CV();
var minDate = new tt.Field("起始日期", "geProductMain.specifyStartDate");
var maxDate = new tt.Field("终止日期", "geProductMain.specifyEndDate").setMsgId("maxDate_msg");

// 比较验证
var cvLimited = new tt.CV();
minLimited = new tt.Field("最小限制天数", "geProductMain.minEffectDateLimited");
maxLimited = new tt.Field("最大限制天数", "geProductMain.maxEffectDateLimited").setMsgId("maxLimited_msg");

// 比较验证
var cvPeople = new tt.CV();
var minPeople = new tt.Field("最小人数", "geProductMain.minPInsuredNum");
var maxPeople = new tt.Field('最大人数',"geProductMain.maxPInsuredNum").setMsgId("maxPeople_msg");


// 大于1的验证
var NRV = new tt.NRV().set(1, '++');
NRV.add("geProductMain.minInsuredNum","geProductMain.maxInsuredNum");// 最小被保人数、最大呗保人数

// 比较验证
var cvInsure = new tt.CV();
var minInsure = new tt.Field("最小被保人数", "geProductMain.minInsuredNum");
var maxInsure = new tt.Field('最大被保人数',"geProductMain.maxInsuredNum");
cvInsure.add(maxInsure).set('n', '>=', minInsure);

// 比较验证
var cvBene = new tt.CV();
var minBene = new tt.Field("最小受益人数", "geProductMain.minBeneficiaryNum");
var maxBene = new tt.Field('最大受益人数',"geProductMain.maxBeneficiaryNum").setMsgId("maxBene_msg");

	function doNext(){
		var allAuthDepts = getAuthDepts();/**所有选中的叶子节点*/
		if(tt.validate()==false){
			alert("请输入符合条件的值");
		}else if(validateDept(allAuthDepts)==false){
			alert("请选择销售地区");
		}else{
			$("#deptidSave").attr("value",allAuthDepts);
			addPeriod();
			$('#frmInput1').submit();
		}
	}
	
	function doReset(){
		document.getElementById('frmInput1').reset();
	}
	
	function showEffectTypeIterms(obj){
		tt.vf.req.add("geProductMain.specifyStartDate","geProductMain.specifyEndDate","geProductMain.minEffectDateLimited"
				,"geProductMain.maxEffectDateLimited","geProductMain.delayDays");
		// 数字大于1的验证
		NRV.add("geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited","geProductMain.delayDays");//'++'表示无穷大
		cvDate.add(maxDate).set('v', '>=', minDate);
		cvLimited.add(maxLimited).set('n', '>=', minLimited);
		if(obj == "01"){
			$("#EffectType01").show();
			$("#EffectType02").hide();
			$("#EffectType03").hide();
			$("#EffectType04").hide();
			tt.vf.req.rm("geProductMain.specifyStartDate","geProductMain.specifyEndDate","geProductMain.minEffectDateLimited"
					,"geProductMain.maxEffectDateLimited","geProductMain.delayDays");
			NRV.rm("geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited","geProductMain.delayDays");//'++'表示无穷大
			cvDate.rmField(maxDate);
			cvLimited.rmField(maxLimited);
		}else if(obj == "02"){
			$("#EffectType01").hide();
			$("#EffectType02").show();
			$("#EffectType03").hide();
			$("#EffectType04").hide();
			tt.vf.req.rm("geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited","geProductMain.delayDays");
			NRV.rm("geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited","geProductMain.delayDays");//'++'表示无穷大
			cvLimited.rmField(maxLimited);
		}else if(obj == "03"){
			$("#EffectType01").hide();
			$("#EffectType02").hide();
			$("#EffectType03").show();
			$("#EffectType04").hide();
			tt.vf.req.rm("geProductMain.specifyStartDate","geProductMain.specifyEndDate","geProductMain.delayDays");
			NRV.rm("geProductMain.delayDays");//'++'表示无穷大
			cvDate.rmField(maxDate);
		}else if(obj == "04"){
			$("#EffectType01").hide();
			$("#EffectType02").hide();
			$("#EffectType03").hide();
			$("#EffectType04").show();
			tt.vf.req.rm("geProductMain.specifyStartDate","geProductMain.specifyEndDate","geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited");
			NRV.rm("geProductMain.minEffectDateLimited","geProductMain.maxEffectDateLimited");//'++'表示无穷大
			cvDate.rmField(maxDate);
			cvLimited.rmField(maxLimited);
		}
	}
	
	// 是否支持连带被保人
	function showPInsuredNumIterms(obj){
		tt.vf.req.add("geProductMain.minPInsuredNum","geProductMain.maxPInsuredNum");
		NRV.add("geProductMain.minPInsuredNum","geProductMain.maxPInsuredNum"); // 数字大于1的验证
		//tt.vf.int.add("geProductMain.minPInsuredNum","geProductMain.maxPInsuredNum");
		cvPeople.add(maxPeople).set('n', '>=', minPeople);
		if(obj == "01"){
			$("#IsSupportPInsLable").show();
		} else {
			$("#IsSupportPInsLable").hide();
			tt.vf.req.rm("geProductMain.minPInsuredNum","geProductMain.maxPInsuredNum");
			cvPeople.rmField(maxPeople);
		}
	}
	
	
	function showBenficiary(obj) {
		cvBene.add(maxBene).set('n', '>=', minBene);
		tt.vf.req.add("geProductMain.minBeneficiaryNum","geProductMain.maxBeneficiaryNum");
		NRV.add("geProductMain.minBeneficiaryNum","geProductMain.maxBeneficiaryNum");// 受益人
		if(obj == '1'){	
			cvBene.add(maxBene).set('n', '>=', minBene);
			$("#beneficiary").show();
		}else{
			cvBene.rmField(maxBene);
			$("#beneficiary").hide();
			tt.vf.req.rm("geProductMain.minBeneficiaryNum","geProductMain.maxBeneficiaryNum");
			NRV.rm("geProductMain.minBeneficiaryNum","geProductMain.maxBeneficiaryNum");// 受益人
		}
	}
	
	// 是否限定同业保额
	function showlimitSameIndInsuredAmount(obj){
		tt.vf.req.add("geProductMain.sameIndInsuredAmountMax");
		if(obj == "01"){
			$("#IslimitSameIndInsuredAmount").show();
		} else {
			$("#IslimitSameIndInsuredAmount").hide();
			tt.vf.req.rm("geProductMain.sameIndInsuredAmountMax");
		}
	}
	
	var periodTableTrId = $("#periodTable tr").length - 1;
	$("#addPeriod").click(function() {
		var addNumber = periodTableTrId ;
		var periodStr = "<tr height=\"25\" id=\"periodTableTr_" + addNumber + "\">" + 
						"<td class=\"search_body\" width=\"48\" valign=\"middle\" nowrap><input id=\"check" + addNumber + "\" type=\"checkbox\" style=\"width:48px;border:1px\" onclick=\"checkSingleRow()\" name=\"checkChild\" value=\"periodTableTr_" + addNumber + "\" /></td>" +
						"<td class=\"search_body\" width=\"200\" valign=\"top\" nowrap>" +
						"<input type=\"hidden\" id=\"attributeKind" + addNumber + "\" name=\"attributeKind" + addNumber + "\" value=\"period\" /> " + 
						"<input type=\"hidden\" id=\"attributeName" + addNumber + "\" name=\"attributeName" + addNumber + "\" value=\"保险期间\" /> " +
						"<input type=\"hidden\" id=\"allowValuesType" + addNumber + "\" name=\"allowValuesType" + addNumber + "\" value=\"01\" /> " +
						"<input type=\"hidden\" id=\"attributeType" + addNumber + "\" name=\"attributeType" + addNumber + "\" value=\"PeriodType\" /> " +
						"<input type=\"hidden\" id=\"coreProductCode" + addNumber + "\" name=\"coreProductCode" + addNumber + "\" value=\"${geProductMain.coreProductCode}\" /> " +
						"<input type=\"text\" id=\"attributeValue" + addNumber + "\" style=\"width:200px;height:23;margin:0px 0px 5px 0px;\" name=\"attributeValue" + addNumber + "\" value=\"\" /> " + 
						"</td>" +
						"<td class=\"search_body\" width=\"200\" valign=\"top\" nowrap>" + 
						"<select id=\"attributeTypeValue" + addNumber + "\" style=\"width:200px;height:23;margin:0px 0px 5px 0px;\" name=\"attributeTypeValue" + addNumber + "\">" +
						"</select>" + 
						"</td>" +
					  "</tr>"
		$("#periodTable").append(periodStr);
		tt.vf.req.add("attributeValue" + addNumber,"attributeTypeValue" + addNumber);
		var NRVattributeValue = new tt.NRV().set(1, '++');
		NRVattributeValue.add("attributeValue" + addNumber);//输入的保险期间必须大于1的数字
		tt.vf.int.add("attributeValue" + addNumber);//输入的保险期间必须为整数
		getDataFromDic("attributeTypeValue" + addNumber, "PeriodType");
		periodTableTrId += 1;
		checkPeriod("attributeValue" + addNumber,"attributeTypeValue" + addNumber);
	});
	
	$("#delPeriod").click(function() {
		var checkedCount = $("#count").val();
		var dutyNumber = $("#periodTable tr").length -1 ;
		if(checkedCount == 0){
			alert("请选择要删除的保险期间！");
			return;						
		}
		if (dutyNumber == checkedCount) {
			alert("不能删除所有责任！");
			return;
		} else {
			var idStr = $("#idStr").val();
			var delPeriods = idStr.split(",");
			for (var i = 0 ; i < delPeriods.length; i++) {
				$("#periodTable").contents().find("#" + delPeriods[i]).remove();	
			}
			var periods = $("#periodTable tr");
			for (var i = 1; i < periods.length; i++) {
			}
		}
	});
	
	function getDataFromDic(codeSelect, codeType){
		$.ajax({
			   cache :false,
			   type: "POST",
			   url: '${ctx}/productManage/findDataFromDic.do',
			   data: {
					"codeType":codeType
				},//传入参数
			   dataType:"json",
			   success: function(data){
				   var dd = document.getElementById(codeSelect);
				   $(dd).empty();
				   $(dd).append("<option value=''>--请选择--</option>");
			    	for(var i = 0; i < data.mapList.length; i++){
			    		var baObj = data.mapList[i];
			    		$(dd).append("<option value='" + baObj.codeCode + "'>" + baObj.codeName + "</option>");
			    	}
				},
			   error:function(XMLHttpRequest, textStatus, errorThrown){
				   
			   }
		});
	}
	function checkSingleRow() {
		var idStr = "";
		var count = 0;
		var checkArray = document.getElementsByName("checkChild");
		var tr_selected;
		var value_checked;
		for (var i = 0; i < checkArray.length; i++){
			tr_selected = document.getElementById("tr_" + checkArray[i].id);
			if(checkArray[i].checked){
				value_checked = checkArray[i].value;
				if(idStr == ""){
					idStr = value_checked;
				}else {
					idStr = idStr + "," + value_checked;
				}
				count++;
			}else{
			}
		}
		document.getElementById("idStr").value = idStr;
		document.getElementById("count").value = count;
	}
	var dutyNumber = $("#periodTable tr").length -1 ;
	if(dutyNumber > 0){
		var NRVattributeValue = new tt.NRV().set(1, '++');
		for(var addNumber =0; addNumber < dutyNumber; addNumber++){
			tt.vf.req.add("attributeValue" + addNumber,"attributeTypeValue" + addNumber);
			NRVattributeValue.add("attributeValue" + addNumber);//输入的保险期间必须大于1的数字
			tt.vf.int.add("attributeValue" + addNumber);//输入的保险期间必须为整数
		}

	}
	
	/**
	 * 添加保险期间 
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
	
	var authorityTrees = document.getElementById("authorityTree");
	authorityTrees.style.height = document.body.clientHeight-300;

	//初始化tree----------------------------------------------------------////
	tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
	tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
	tree.enableCheckBoxes(true);
	tree.enableThreeStateCheckboxes(true);
	tree.loadXML("${ctx}/productManage/authoritySetDeptAuthTree.do?coreProductCode=${geProductMain.coreProductCode}",loadOver);

	function myErrorHandler(type, desc, erData){
		   return;
		}
	
	dhtmlxError.catchError("ALL",myErrorHandler);

	function validateDept(deptids){
		if(deptids == ''){
			return false;
		}
		return true;
	}

	function getAuthDepts(){
		var allAuthDepts = "";
		var allSelected=tree.getAllCheckedBranches();
		
		var listId=allSelected.split(",");
		for(var i=0;i<listId.length;i++){
			if(!tree.hasChildren(listId[i])){
				allAuthDepts+=listId[i]+",";
			}
		}

		allAuthDepts = allAuthDepts.substring(0,allAuthDepts.length-1);
		return allAuthDepts;
	}

	function loadOver(sIdNow){
		$("#showListLoading").hide();
	}

	//以下代码验证保险期间和保险期间类型的唯一性
	function checkPeriod(attributeValueN,attributeTypeValueN){
		$("#" + attributeTypeValueN).blur(function(){
			var periodTableTrIdCnt = $("#periodTable tr").length - 1;
			var periodTableAttributeValueIds = "";
			var periodTableAttributeTypeValueIds = "";
			for (var i = 0; i < periodTableTrIdCnt; i++){
				var eachAttributeValue = $("#periodTableTr_" + i).contents().find("#attributeValue" + i);
				var eachAttributeTypeValue = $("#periodTableTr_" + i).contents().find("#attributeTypeValue" + i);
				periodTableAttributeValueIds += eachAttributeValue.val() + ",";
				periodTableAttributeTypeValueIds += eachAttributeTypeValue.val() + ",";
			}
			periodTableAttributeValueIds = periodTableAttributeValueIds.split(",");
			periodTableAttributeTypeValueIds = periodTableAttributeTypeValueIds.split(",");
			for(var i = 0; i < periodTableAttributeValueIds.length-1; i++){
				for(var j = i+1; j < periodTableAttributeValueIds.length-1; j++){
					if(periodTableAttributeValueIds[i] == periodTableAttributeValueIds[j] && periodTableAttributeTypeValueIds[i] == periodTableAttributeTypeValueIds[j]
					&& !((periodTableAttributeValueIds[i] == "" || periodTableAttributeValueIds[j] == "" || periodTableAttributeTypeValueIds[i] == "" || periodTableAttributeTypeValueIds[j] == ""))){
						$("#" + attributeValueN).val("");
						$("#" + attributeTypeValueN).val("");
						$("#periodContent").remove();
						$("#comChecked").parent().append("<span id='periodContent'><span class='talentErrMsg'>保险期间和保险期间类型不能重复！</span></span>")
						return;
					}
				}
			}
		});
		$("#" + attributeValueN).focus(function(){
			$("#periodContent").remove();
		});
	}
</script>
</html>
