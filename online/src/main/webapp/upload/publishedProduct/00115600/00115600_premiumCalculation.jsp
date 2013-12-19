<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
<div class="product_quote">
	<div class="product_img">
		<img src="${ctx }/upload/images/00115600/00115600_detail_main.jpg">
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
		<div class="input_field">
			<label>保险期间：</label>
			<ul class="select_box traffic">
				<li class="box select" tag="period" count="6" unit='41'><div>6个月</div></li>
				<li class="box" tag="period" count="9" unit='41'><div>9个月</div></li>
				<li class="box" tag="period" count="1" unit='42'><div>1年</div></li>
			</ul>
		</div>
		<div class="input_field">
			<label>购买数量：</label>
			<ul class="select_box days">
				<li class="box select" tag="number" count="1" unit='share'><div>1份</div></li>
				<li class="box" tag="number" count="10" unit='share'><div>10份</div></li>
				<li class="box" tag="number" count="30" unit='share'><div>30份</div></li>
				<li class="box" tag="number" count="50" unit='share'><div>50份</div></li>
				<li class="box" tag="number" count="100" unit='share'><div>100份</div></li>
				<li class="box" tag="number" count="1" unit='other'><div>其他</div></li>
			</ul>
		</div>
		<div class="input_field buy_quota" style="display:none;">
			<label>&nbsp;</label>
			<div class="calculate">
				<input class="num" value="1" maxlength="3"/>
			</div>
			<span>&nbsp;份/人（最多可购买398份）</span>
		</div>
<!-- 		<div class="input_field buy_quota" style="display:none;"> -->
<!-- 			<label>请选择：</label> -->
<!-- 			<div class="calculate"> -->
<!-- 				<div class="minus click_btn">-</div> -->
<!-- 				<div class="num">1</div> -->
<!-- 				<div class="plus click_btn">+</div> -->
<!-- 			</div> -->
<!-- 			<span>&nbsp;份/人</span> -->
<!-- 		</div> -->
		<div class="action">
			<button id="buy_click_btn" class="buy click_btn" onclick="return false;">立即投保</button>
		</div>
		<input type="hidden" id="productCode" name="productCode" value="${productCode}" />
		<input type="hidden" id="insuredAmount" name="insureFlowDto.quoteMain.insuredAmount" value="0" />
		<input type="hidden" id="premium" name="insureFlowDto.quoteMain.premium" value="0" />
		<input type="hidden" id="grossPremium" name="insureFlowDto.quoteMain.grossPremium" value="0" />
		<input type="hidden" id="unitCount" name="insureFlowDto.quoteMain.unitCount" value="1" />
		<input type="hidden" id="divType" name="insureFlowDto.quoteMain.divType" value="5" />
		<input type="hidden" id="benefitPeriod" name="insureFlowDto.quoteMain.benefitPeriod" value="80" />
		<input type="hidden" id="benefitPeriodType" name="insureFlowDto.quoteMain.benefitPeriodType" value="20" />
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
var reg = new RegExp("(^[1-9]\\d*$)|(^[1-9]\\d*.\\d*[0-9]|0.\\d*[1-9]\\d*$)");

var count = 1, countMax = 398;
var t = 0, d = 0,subRiskFlag= 1;
var riskCode = '00115500',premium,grossPremium,insuredAmount;

$('.input_field .num').blur(function(){
	$('.quote_info .input_field ul li').eq(8).addClass('select');
	$('.quote_info .input_field ul li').eq(8).siblings().removeClass('select');
	var num = $('.input_field .num').attr('value');
	if(!reg.test(num)){
		Sinosoft.alert({
			contentStr: "请输入大于0的数值!",
			width:480,
			okStr: '确定',
			cancelBtnShow:false,//是否显示关闭按钮
			okFunc:function(){
				
			}
		});
//         alert("请输入大于0的数值!");
        $(this).eq(0);
    }else{
    	num = parseInt($('.input_field .num').attr('value'));
    	count = num;
    	if(num > countMax){
    		count = countMax;
    	}
    	premiumCalculation(t,count);
    	$('.input_field .num').attr('value',count);
    	$('#unitCount').attr('value', count);
    }
});

$('.input_field .plus').click(function(){
	var num = parseInt($('.input_field .num').text());
	if(num < countMax){
		num++;
	}
	$('.input_field .num').text(num);
	count = num;
	premiumCalculation(t,count);
	$('#unitCount').attr('value',count);
});

$('.input_field .minus').click(function(){
	var num = parseInt($('.input_field .num').text());
	if(num > 1){
		num--;
		$('.input_field .num').text(num);
		count = num;
		premiumCalculation(t,count);
		$('#unitCount').attr('value', count);
	}
});

var _array = ['预期年化收益率5.02%','预期年化收益率5.12%','预期年化收益率5.3%'];
var prem = 500;
$('#sub_title').text(_array[t]);
premiumCalculation(t,count);
$('#unitCount').attr('value', count);
$('#premium').val(count*(prem*10)/10);
$('#grossPremium').val(count*(prem*10)/10);
$('#paymentDurationMode').attr('value', '4');

//console.log($("#type").val()+", "+$(".traffic .box").length);
//console.log($("#day").val()+", "+$(".days .box").length);
$(".traffic .box").eq($("#type").val()).addClass('select');
$(".traffic .box").eq($("#type").val()).siblings().removeClass('select');
$(".days .box").eq($("#day").val()).addClass('select');
$(".days .box").eq($("#day").val()).siblings().removeClass('select');

$('.quote_info .input_field ul li').click(function(){
	if(!$(this).hasClass('select')){
		var index = $(this).index();
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
		if($(this).attr('tag') == 'period'){
			t = index;
			$('#sub_title').text(_array[t]);
		}else if($(this).attr('tag') == 'number'){
			count = $(this).attr('count');
			$('.input_field .num').attr('value',count);
			$('#unitCount').attr('value', count);
			if($(this).attr('unit') == 'other'){
				$('.input_field.buy_quota').show();
			}else{
				$('.input_field.buy_quota').hide();
			}
		}
		premiumCalculation(t,count);
	}
});

function premiumCalculation(t,c){
	$('.input_field .price').text(c*(prem*10)/10+"元");
	$('#premium').val(c*(prem*10)/10);
	$('#grossPremium').val(c*(prem*10)/10);
}

$('#buy_click_btn').click(function(){
	toInsuInsurance();
});

/**set value**/
function copyFirstStep(){
	insureFlowDto.productCode = "${productCode}";
	insureFlowDto.processValue = $('#processValue').attr('value');
	insureFlowDto.quoteMain.eid = "${eid}";
	insureFlowDto.quoteMain.productCode = "${productCode}";
	insureFlowDto.quoteMain.productName = "${geDirectory.productName}";
	insureFlowDto.quoteMain.unitCount = $('#unitCount').attr('value');
	insureFlowDto.quoteMain.premium = $('#premium').attr('value');
	insureFlowDto.quoteMain.grossPremium = $('#grossPremium').attr('value');
	insureFlowDto.quoteMain.benefitPeriod = $('#benefitPeriod').attr('value');
	insureFlowDto.quoteMain.benefitPeriodType = $('#benefitPeriodType').attr('value');
	insureFlowDto.quoteMain.paymentDurationMode = $('#paymentDurationMode').attr('value');
	insureFlowDto.quoteMain.userId = $('#userId').attr('value');
	insureFlowDto.quoteMain.divType = $('#divType').attr('value');
	
	insureFlowDto.quoteMain.quoteLiabilities = [];
	insureFlowDto.quoteMain.quoteLiabilities[0] = {
			liabilityCode: riskCode,
			coreCode: riskCode,
			liabilityName: getRiskName("00115600"),
			productCode: "${productCode}",
			productName: getRiskName("00115600"),
			subRiskFlag: subRiskFlag,
			bonusGetMode: $('#divType').attr('value'),
			liabilityOrder: "1",
			premium: $('#premium').val(),
			benefitPeriod: $('#benefitPeriod').val(),
			benefitPeriodType: $('#benefitPeriodType').val(),
			paymentDurationMode: $('#paymentDurationMode').attr('value'),
			mainRiskCode: riskCode,
			unitCount: $('#unitCount').val()
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