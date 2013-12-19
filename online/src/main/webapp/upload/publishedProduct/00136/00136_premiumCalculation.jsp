<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="product_quote">
	<div class="product_img">
		<img src="${ctx }/upload/images/00136/00136_detail_main.jpg">
	</div>
	<div class="quote_info">
		<form id="frmInput" method="post" action="">
		<div class="product_title">
			<p class="main_title">${geDirectory.productName }</p>
			<p class="sub_title" id="sub_title">${geDirectory.productSummary }</p>
		</div>
		<div class="input_field">
			<label>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</label>
			<span class="price" id="price">${geDirectory.insuranceAmountDesc }</span>
		</div>
		<div class="input_field">
			<label>适用人群：</label>
			<span class="suit">${geDirectory.applyPeople }</span>
		</div>
		<div class="input_field">
			<label>交通类型：</label>
			<ul class="select_box traffic">
				<li class="box select" tag="type" amount="50" count="2" code="00136300"><div>飞机</div></li>
				<li class="box" tag="type" amount="10" count="5" code="00136200"><div>火车</div></li>
				<li class="box" tag="type" amount="10" count="5" code="00136400"><div>客运</div></li>
				<li class="box" tag="type" amount="10" count="5" code="00136100"><div>轮船</div></li>
				<li class="box" tag="type" amount="10" count="5" code="00136000"><div>自驾车</div></li>
			</ul>
		</div>
		<div class="input_field">
			<label>保险期间：</label>
			<ul class="select_box days">
<!-- 				<li class="box select" tag="day" count="7" unit='40'><div>7天</div></li> -->
				<li class="box select" tag="day" count="1" unit='41'><div>1个月</div></li>
				<li class="box" tag="day" count="6" unit='41'><div>6个月</div></li>
				<li class="box" tag="day" count="1" unit='42'><div>1年</div></li>
			</ul>
		</div>
		<div class="input_field">
			<label>保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</label>
<!-- 			<input class="amount" type="text"/> -->
			<div id="amount"></div>
		</div>
		<div class="input_field">
			<label>购买数量：</label>
			<div class="calculate">
				<div class="minus click_btn">-</div>
				<div class="num">1</div>
				<div class="plus click_btn">+</div>
			</div>
			<span>份/人（最多可购买<span id="maxUnitCount"></span>份）</span>
		</div>
		<div class="action">
			<button id="buy_click_btn" class="buy click_btn" onclick="return false;">立即投保</button>
		</div>
		<input type="hidden" id="productCode" name="productCode" value="${productCode}" />
		<input type="hidden" id="insuredAmount" name="insureFlowDto.quoteMain.insuredAmount" value="0" />
		<input type="hidden" id="premium" name="insureFlowDto.quoteMain.premium" value="0" />
		<input type="hidden" id="grossPremium" name="insureFlowDto.quoteMain.grossPremium" value="0" />
		<input type="hidden" id="unitCount" name="insureFlowDto.quoteMain.unitCount" value="1" />
		<input type="hidden" id="divType" name="insureFlowDto.quoteMain.divType" value="5" />
		<input type="hidden" id="benefitPeriod" name="insureFlowDto.quoteMain.benefitPeriod" value="${insureFlowDto.quoteMain.benefitPeriod}" />
		<input type="hidden" id="benefitPeriodType" name="insureFlowDto.quoteMain.benefitPeriodType" value="${insureFlowDto.quoteMain.benefitPeriodType}" />
		<input type="hidden" name="insureFlowDto.quoteMain.paymentDurationMode" id="paymentDurationMode" value="${insureFlowDto.quoteMain.paymentDurationMode }"/>
		<input type="hidden" id="eid" name="insureFlowDto.quoteMain.eid" value="${geDirectory.eid}" />
		<input type="hidden" id="userId" name="insureFlowDto.quoteMain.userId" value="${insureFlowDto.quoteMain.userId}" />
		<input type="hidden" id="productCode" name="insureFlowDto.quoteMain.productCode" value="${productCode}" />
		<input type="hidden" id="productName" name="insureFlowDto.quoteMain.productName" value="${geDirectory.productName}" />
		<input type="hidden" id="quoteUrlFlag" name="quoteUrlFlag" value="" />
		<input type="hidden" id="quoteJsonSTR" name="quoteJsonSTR" value="" />
		<input type="hidden" id="type" name="type" value="${type }" />
		<input type="hidden" id="day" name="day" value="${day }" />
		
		</form>
	</div>
</div>

<script>
var count = 1, countMax = 2;
var t = 0, d = 0, benefitPeriod = 7,benefitPeriodType = 40,subRiskFlag= 1;
var riskCode = '00136300',premium,grossPremium,insuredAmount;
$('.input_field .plus').click(function(){
	var num = parseInt($('.input_field .num').text());
	if(num < countMax){
		num++;
	}
	$('.input_field .num').text(num);
	count = num;
	premiumCalculation(t,d,count);
	$('#unitCount').attr('value',count);
});

// console.log($("#type").val()+", "+$(".traffic .box").length);
// console.log($("#day").val()+", "+$(".days .box").length);
$(".traffic .box").eq($("#type").val()).addClass('select');
$(".traffic .box").eq($("#type").val()).siblings().removeClass('select');
$(".days .box").eq($("#day").val()).addClass('select');
$(".days .box").eq($("#day").val()).siblings().removeClass('select');
riskCode = $(".traffic .box").eq($("#type").val()).attr('code');
countMax = $(".traffic .box").eq($("#type").val()).attr('count');
insuredAmount = $(".traffic .box").eq($("#type").val()).attr('amount')*10000;
$('#insuredAmount').attr('value',insuredAmount);
benefitPeriod = $(".days .box").eq($("#day").val()).attr('count');
benefitPeriodType = $(".days .box").eq($("#day").val()).attr('unit');
$('#benefitPeriod').attr('value',benefitPeriod);
$('#benefitPeriodType').attr('value',benefitPeriodType);
t = $("#type").val()==null||$("#type").val()==''?0:$("#type").val();
$("#productName").val(getRiskName(riskCode));
$('#paymentDurationMode').attr('value', '4');
$('.input_field .minus').click(function(){
	var num = parseInt($('.input_field .num').text());
	if(num > 1){
		num--;
		$('.input_field .num').text(num);
		count = num;
		premiumCalculation(t,d,count);
		$('#unitCount').attr('value',count);
	}
});

//var priceArray = [['3.75','5','17.5','25'],['1.05','1.4','4.9','7'],['3.75','5','17.5','25'],['2.25','3','10.05','15'],['9','12','42','60']];
var priceArray = [['5','17.5','25'],['1.4','4.9','7'],['5','17.5','25'],['3','10.50','15'],['12','42','60']];
if($("#type").val() != 0){
	$('#amount').text("10万");
	$('#insuredAmount').attr('value','100000');
	$('#maxUnitCount').text('5');
}else{
	$('#amount').text("50万");
	$('#insuredAmount').attr('value','500000');
	$('#maxUnitCount').text('2');
}
premiumCalculation(t,d,count);

$('.quote_info .input_field ul li').click(function(){
	if(!$(this).hasClass('select')){
		var index = $(this).index();
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		if($(this).attr('tag') == 'type'){
			$('#amount').text($(this).attr('amount')+"万");
			countMax = $(this).attr('count');
			t = index;
			insuredAmount = $(this).attr('amount')*10000;
			riskCode = $(this).attr('code');
			$('.input_field .num').text(1);
			$('#unitCount').attr('value',1);
			$('#insuredAmount').attr('value',insuredAmount);
			$('#maxUnitCount').text(countMax);
			$("#productName").val(getRiskName(riskCode));
		}else if($(this).attr('tag') == 'day'){
			d = index;
			benefitPeriod = $(this).attr('count');
			benefitPeriodType = $(this).attr('unit');
			$('#benefitPeriod').attr('value',benefitPeriod);
			$('#benefitPeriodType').attr('value',benefitPeriodType);
		}
		premiumCalculation(t,d,count);
	}
});

function premiumCalculation(t,d,c){
	$('.input_field .price').text(c*(priceArray[t][d]*10)/10+"元");
	premium = c*(priceArray[t][d]*10)/10;
	grossPremium = c*(priceArray[t][d]*10)/10;
	$('#premium').attr('value',premium);
	$('#grossPremium').attr('value',grossPremium);
}

$('#buy_click_btn').click(function(){
	toInsuInsurance();
});

/**set value**/
function copyFirstStep(){
	insureFlowDto.processValue = $('#processValue').val();
	insureFlowDto.quoteMain.eid = "${eid}";
	insureFlowDto.productCode = "${productCode}";
	insureFlowDto.quoteMain.productCode = "${productCode}";
	insureFlowDto.quoteMain.productName = getRiskName(riskCode);
	insureFlowDto.quoteMain.unitCount = count;
	insureFlowDto.quoteMain.quoteStatus = "1";
	insureFlowDto.quoteMain.insuredAmount = insuredAmount;
	insureFlowDto.quoteMain.premium = premium;
	insureFlowDto.quoteMain.grossPremium = grossPremium;
	insureFlowDto.quoteMain.benefitPeriod = benefitPeriod;
	insureFlowDto.quoteMain.benefitPeriodType = benefitPeriodType;
	insureFlowDto.quoteMain.paymentDurationMode = $('#paymentDurationMode').attr('value');
	insureFlowDto.quoteMain.userId = $('#userId').val();
	insureFlowDto.quoteMain.divType = $('#divType').val();
	
	insureFlowDto.quoteMain.quoteLiabilities = [];
	insureFlowDto.quoteMain.quoteLiabilities[0] = {
			liabilityCode: riskCode,
			liabilityName: getRiskName(riskCode),
			coreCode: riskCode,
			productCode: "${productCode}",
			productName: getRiskName(riskCode),
			subRiskFlag: subRiskFlag,
			liabilityOrder: "1",
			insuredAmount: $('#insuredAmount').attr('value'),
// 			premium: premium,
			benefitPeriod: benefitPeriod,
			benefitPeriodType: benefitPeriodType,
			paymentDurationMode: $('#paymentDurationMode').attr('value'),
			mainRiskCode: riskCode,
			unitCount: count
		};
	
	insureFlowDto.quoteMain.quoteInsureInformBooks = [];
}

/**validate rule*/
function validateQuoteRule(type){
	var message = "success";
	if(type == 'doNext'){
	}
	return message;
}
</script>