<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="product_quote">
	<div class="product_img">
		<img src="${ctx }/upload/images/00116600/00116600_detail_main.jpg">
	</div>
	<div class="quote_info">
		<form id="frmInput" method="post" action="">
		<div class="product_title">
			<p class="main_title">${geDirectory.productName }</p>
			<p class="sub_title" id="sub_title">${geDirectory.productSummary }</p>
		</div>
		<div class="input_field">
			<label>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</label>
			<span class="price" id="price"></span>
		</div>
		<div class="input_field">
			<label>适用人群：</label>
			<span class="suit">${geDirectory.applyPeople }</span>
		</div>
		<div class="input_field select_combo">
			<label>选择套餐：</label>
			<ul class="select_box traffic">
				<li class="box select" tag="box" count="1" amount="50000" unit='type'><div>标准版</div></li>
				<li class="box" tag="box" count="2" amount="100000" unit='type'><div>豪华版</div></li>
			</ul>
		</div>
		<div class="input_field entry_age">
			<label>投保年龄：</label>
			<ul class="select_box traffic">
				<li class="box select" tag="age" count="18-25" unit='y'><div>18-25</div></li>
				<li class="box" tag="age" count="26-40" unit='y'><div>26-40</div></li>
				<li class="box" tag="age" count="41-45" unit='y'><div>41-45</div></li>
				<li class="box" tag="age" count="46-50" unit='y'><div>46-50</div></li>
			</ul>
		</div>
		<div class="input_field premium_term">
			<label>缴费年期：</label>
			<ul class="select_box days">
				<li class="box select" tag="year" count="5" unit='1'><div>5年</div></li>
				<li class="box" tag="year" count="10" unit='1'><div>10年</div></li>
			</ul>
		</div>
		<div class="input_field assurance" style="display: none;">
			<label>保障方案：</label>
			<ul>
				<li><span class="label">驾乘私家车意外身故保障</span><span id="safeguard_0">100</span>万</li>
				<li><span class="label">公共交通意外身故保障</span><span id="safeguard_1">57.5</span>万</li>
				<li><span class="label">最高意外伤残（包括烧伤）保障</span><span id="safeguard_2">7.5</span>万</li>
				<li><span class="label">意外身故保障</span><span id="safeguard_3">7.5</span>万</li>
				<li><span class="label">一般身故保障</span>主险累计已交保费<span>105</span>%</li>
				<li><span class="label">满期生存保险金</span>主险累计已交保费<span>120</span>%</li>
			</ul>
		</div>
		<div class="action">
			<button id="buy_click_btn" class="buy click_btn" onclick="return false;">立即投保</button>
		</div>
		<input type="hidden" id="productCode" name="productCode" value="${productCode}" />
		<input type="hidden" id="premium" name="insureFlowDto.quoteMain.premium" value="0" />
		<input type="hidden" id="grossPremium" name="insureFlowDto.quoteMain.grossPremium" value="0" />
		<input type="hidden" id="unitCount" name="insureFlowDto.quoteMain.unitCount" value="1" />
		<input type="hidden" id="benefitPeriod" name="insureFlowDto.quoteMain.benefitPeriod" value="${insureFlowDto.quoteMain.benefitPeriod}" />
		<input type="hidden" id="benefitPeriodType" name="insureFlowDto.quoteMain.benefitPeriodType" value="${insureFlowDto.quoteMain.benefitPeriodType}" />
		<input type="hidden" id="eid" name="insureFlowDto.quoteMain.eid" value="${geDirectory.eid}" />
		<input type="hidden" id="userId" name="insureFlowDto.quoteMain.userId" value="${insureFlowDto.quoteMain.userId}" />
		<input type="hidden" id="productCode" name="insureFlowDto.quoteMain.productCode" value="${productCode}" />
		<input type="hidden" id="productName" name="insureFlowDto.quoteMain.productName" value="${geDirectory.productName}" />
		<input type="hidden" name="insureFlowDto.quoteMain.comboCode" id="comboCode" value="${insureFlowDto.quoteMain.comboCode }"/>
		<input type="hidden" name="insureFlowDto.quoteMain.comboName" id="comboName" value="${insureFlowDto.quoteMain.comboName }"/>
		<input type="hidden" name="insureFlowDto.quoteMain.paymentDuration" id="paymentDuration" value="${insureFlowDto.quoteMain.paymentDuration }"/>
		<input type="hidden" name="insureFlowDto.quoteMain.paymentDurationMode" id="paymentDurationMode" value="${insureFlowDto.quoteMain.paymentDurationMode }"/>
		<input type="hidden" name="insureFlowDto.quoteMain.ageInterval" id="ageInterval" value="${insureFlowDto.quoteMain.ageInterval }"/>
		<input type="hidden" id="amount" name="amount" value="" />
		<input type="hidden" id="subAmount" name="subAmount" value="" />
		<input type="hidden" id="type" name="type" value="${type }" />
		<input type="hidden" id="day" name="day" value="${day }" />
		<input type="hidden" id="quoteUrlFlag" name="quoteUrlFlag" value="" />
		<input type="hidden" id="quoteJsonSTR" name="quoteJsonSTR" value="" />
		</form>
	</div>
</div>

<script>
var count = 1, countMax = 1;
var t = 0, d = 0;
var ageInterval, paymentDuration;

var _preArray = [['1570','1565','1555','1525'],['1127.5','1125','1115','1100']];
var _amountArray = ['100','57.5','7.5','7.5'];
var _code = [['00116600','00126600','00136600'],['1','2','2']];

// console.log($("#type").val()+", "+$(".traffic .box").length);
// console.log($("#day").val()+", "+$(".days .box").length);
$(".traffic .box").eq($("#type").val()).addClass('select');
$(".traffic .box").eq($("#type").val()).siblings().removeClass('select');
$(".days .box").eq($("#day").val()).addClass('select');
$(".days .box").eq($("#day").val()).siblings().removeClass('select');

premiumCalculation(t,d,count);
$('#amount').attr('value',50000);
$('#subAmount').attr('value',50000*10);
$('#unitCount').attr('value', 1);
$('#benefitPeriod').attr('value','30');
$('#benefitPeriodType').attr('value','42');
$('#paymentDuration').attr('value', '5');
$('#paymentDurationMode').attr('value', '1');
$('#ageInterval').attr('value', '18-25');
$('#comboName').attr('value', $('.input_field .traffic').find('li').eq(0).find('div').text());

$('.quote_info .input_field ul li').click(function(){
	if(!$(this).hasClass('select')){
		var index = $(this).index();
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		if($(this).attr('tag') == 'box'){
			count = $(this).attr('count');
			initAmount(count,$(this).attr('amount'));
			$('#unitCount').attr('value', 1);
			$('#comboName').attr('value', $(this).find('div').text());
		}else if($(this).attr('tag') == 'year'){
			t = index;
			$('#paymentDuration').attr('value', $(this).attr('count'));
			$('#paymentDurationMode').attr('value', $(this).attr('unit'));
		}else if($(this).attr('tag') == 'age'){
			d = index;
			$('#ageInterval').attr('value', $(this).attr('count'));
		}
		premiumCalculation(t,d,count);
	}
});

function premiumCalculation(t,d,c){
	$('.input_field .price').text(c*(_preArray[t][d]*10)/10+"元");
	$('#premium').attr('value', c*(_preArray[t][d]*10)/10);
	$('#grossPremium').attr('value', c*(_preArray[t][d]*10)/10);
}

function initAmount(c,a){
	$('#amount').attr('value',a);
	$('#subAmount').attr('value',parseInt(a*10));
	for(var i=0;i<_amountArray.length;i++){
		$('#safeguard_'+i).text(c*(_amountArray[i]*10)/10);
	}
}

$('#buy_click_btn').click(function(){
	toInsuInsurance();
});

/**set value**/
function copyFirstStep(){
	insureFlowDto.productCode = "${productCode}";
	insureFlowDto.quoteMain.eid = "${eid}";
	insureFlowDto.quoteMain.productCode = "${productCode}";
	insureFlowDto.quoteMain.productName = "${geDirectory.productName}";
	insureFlowDto.quoteMain.unitCount = $('#unitCount').attr('value');
	insureFlowDto.quoteMain.paymentDuration = $('#paymentDuration').attr('value');
	insureFlowDto.quoteMain.paymentDurationMode = 1;
	insureFlowDto.quoteMain.quoteStatus = "1";
	insureFlowDto.quoteMain.premium = $('#premium').attr('value');
	insureFlowDto.quoteMain.grossPremium = $('#grossPremium').attr('value');
	insureFlowDto.quoteMain.benefitPeriod = $('#benefitPeriod').attr('value');
	insureFlowDto.quoteMain.benefitPeriodType = $('#benefitPeriodType').attr('value');
	insureFlowDto.quoteMain.comboCode = $('#comboCode').attr('value');
	insureFlowDto.quoteMain.comboName = $('#comboName').attr('value');
	insureFlowDto.quoteMain.userId = $('#userId').attr('value');
	insureFlowDto.quoteMain.ageInterval = $('#ageInterval').attr('value');
	
	insureFlowDto.quoteMain.quoteLiabilities = [];
	insureFlowDto.quoteMain.quoteLiabilities[0] = {
			liabilityCode: _code[0][0],
			liabilityName: getRiskName(_code[0][0]),
			coreCode: _code[0][0],
			productCode: "${productCode}",
			productName: getRiskName(_code[0][0]),
			subRiskFlag: _code[1][0],
			insuredAmount: $('#amount').attr('value'),
			benefitPeriod: $('#benefitPeriod').attr('value'),
			benefitPeriodType: $('#benefitPeriodType').attr('value'),
			paymentDuration: $('#paymentDuration').attr('value'),
			paymentDurationMode: $('#paymentDurationMode').attr('value'),
			mainRiskCode: _code[0][0],
			unitCount: $('#unitCount').attr('value')
		};
	insureFlowDto.quoteMain.quoteLiabilities[1] = {
			liabilityCode: _code[0][2],
			liabilityName: getRiskName(_code[0][2]),
			coreCode: _code[0][2],
			productCode: "${productCode}",
			productName: getRiskName(_code[0][2]),
			subRiskFlag: _code[1][1],
			insuredAmount: $('#subAmount').attr('value'),
			benefitPeriod: $('#benefitPeriod').attr('value'),
			benefitPeriodType: $('#benefitPeriodType').attr('value'),
			paymentDuration: $('#paymentDuration').attr('value'),
			paymentDurationMode: $('#paymentDurationMode').attr('value'),
			mainRiskCode: _code[0][0],
			unitCount: $('#unitCount').attr('value')
		};
	
	/**健康告知*/
	insureFlowDto.quoteMain.quoteInsureInformBooks = [];
	insureFlowDto.quoteMain.quoteInsureInformBooks[0] = {
			informCode:"001",
			tellVersion:"01",
			tellRemark:",  ,  "
	}
}

/**validate rule*/
function validateQuoteRule(type){
	var message = "success";
	if(type == 'doNext'){
	}
	return message;
}
</script>