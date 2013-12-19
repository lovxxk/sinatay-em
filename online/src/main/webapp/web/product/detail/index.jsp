<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script>
function getRiskName(riskCode){
	var productRisks = $("#productRisks").html();
	var json_risks = eval("(" + productRisks + ")");
	for(var i in json_risks){
// 		console.log(json_risks[i]);
		if(json_risks[i].productRiskCode == riskCode){
			return json_risks[i].productRiskName;
		}
	}
}
function toInsuInsurance(){
	copyFirstStep();
	toInputDeal();
}

function forceCheckLogin(c){
	return true;
}
function toInputDeal(){
// 	console.log(insureFlowDto);
	$.ajax({
		type: "POST",
		url:contextRootPath+"/sale/toInputInsuranceInfo.do",
		dataType:'json',
		data:{"insureFlowDto":insureFlowDto},
		success: function(data){
			if(data == "false"){
				console.log("�����쳣�����Ժ�����");
			}else{
				insureFlowDto = data;
				$("#quoteUrlFlag").val("inputInsure");
				$("#quoteJsonSTR").val(JSON.stringify(insureFlowDto));
				$("#frmInput").attr("action","${ctx}/sale/obtainDataForInput.do?productCode="+data.productCode);
				$("#frmInput").submit();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log("�����쳣�����Ժ�����");
			
		}
	}); 
}

/**
 * �ı䰴ť��״̬
 * @param obj
 * @param abledClass
 * @param disAbledClass
 * @param bl
 */
function disabledBtn(obj,abledClass,disAbledClass,bl){
	if(bl){
		$(obj).attr("disabled","disabled");
		$(obj).removeClass(abledClass).addClass(disAbledClass);
	}else{
		$(obj).removeAttr("disabled");
		$(obj).removeClass(disAbledClass).addClass(abledClass);
	}
}

function loadContent(url,func,process){
// 	$("#nocarSaleContent").empty();
	$("#processValue").val(process);
// 	showBg();
// 	$("#nocarSaleContent").load(url,func);
	window.location.href = url;
}

function getProductClause(type){
// 	console.log("getProductClause()...");
	var productRisks = $("#productRisks").html();
	var json_risks = eval("(" + productRisks + ")");
// 	console.log("json_risks: "+json_risks);
	new Sinosoft.InteractiveDialog(
		{
			layout : loadInsuranceSelect(json_risks),
			width : 560,
			okStr : 'ȷ��',
			cancelStr : 'ȡ��',
			okFunc : function() {
				var riskCode=$(".selected").attr('value');
				if(type == 1){
					window.open("${ctx}/web/common/clauseRead.jsp?productCode=${geDirectory.coreProductCode}&riskCode="+riskCode);
				}
			},
			cancelFunc : function() {
			}
		}).open();
}

//���������б�
function loadInsuranceSelect(json_risks) {
	var str='<div class="insurance_select">'
		+ '<div class="select_title">��ѡ�����֣�</div>';
	for(var i=0;i<json_risks.length;i++){
		var json_risk=json_risks[i];
		//�������ֺ���
		if(i==0){
			str +='<div class="select_item selected" value="'+json_risk.productRiskCode+'">'
			//�������ֱ�����
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.productRiskName+'</div>';
		}else{
			str +='  <div class="select_item" value="'+json_risk.productRiskCode+'">'
			str +='  <div class="select_radio" id="insurance1"></div>'+json_risk.productRiskName+'</div>';
		}
	}
	str = str + '</div>';
	var insuranceSelect=$(str);
	//�ı���ʽ
	insuranceSelect.find('.select_item').click(function() {
		$(this).addClass('selected');
		$(this).siblings().removeClass('selected');
	});
	return insuranceSelect;
}
</script>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>��̩����-��Ʒ����</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/product/product_frame.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/resources/css/product/product.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/user/alert_insurance_select.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/product/product.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		
		<link href="${ctx}/global/js/msgbox/page.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/msgbox/msgbox.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/msgbox/jquery.myPagination5.0.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="detail.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- ���� -->
		<script type="text/javascript">
		$(document).ready(function(){
			_ga.push(['_trackPageview','/web/product/detail']).send();
			$(".login_info .login").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '��¼']).send();
			});
			$.each($(".quick_menu a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', $(this).text()]).send();
				});
			});
			$(".nav_index").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '������']).send();
			});
			$.each($(".product_container a"), function(i,n){
				$(n).click(function(){
					_ga.push(['_trackEvent', '��Ʒ����', '��Ʒ�Ƽ���: '+$(this).text()]).send();
				});
			});
			$(".display").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '���λ']).send();
			});
			$("#buy_click_btn").click(function(){
				_ga.push(['_trackEvent', '��Ʒ����', '����Ͷ��']).send();
			});
			_hm.push(['_trackPageview','/web/product/detail']).send();
		});
		</script>
	</body>
</html>