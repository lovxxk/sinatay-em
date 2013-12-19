<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		
		<title>选择支付—信泰网上商城</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script src="${ctx}/global/js/jquery/jquery.checkbox.js" type="text/javascript"></script>
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/policy/payment.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/policy/payment.css" rel="stylesheet" type="text/css"/>
		
		<script type="text/javascript" src="${ctx}/global/js/common/alert/alert.js"></script>
		<script src="${ctx}/global/js/common/citySelect.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/policy/alert_wait_confirm.css" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="payment.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/policy/payment']).send();
			$("#click_btn").click(function(){
				_ga.push(['_trackEvent', '支付订单', '确认支付']).send();
			});
			_hm.push(['_trackPageview','/web/policy/payment']).send();
		</script>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$('#alipay').click(function(){
		$('#uri').val("/payment/toAliPay.do");
	});
	Sinosoft.initCitySelector($('#frmInput #bankProvince'),$('#frmInput #bankCity'),$('#frmInput #bankArea'));
});

</script>