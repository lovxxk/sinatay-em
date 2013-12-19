<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>订单管理—信泰保险客户服务</title>
		<meta name="description" content="登录会员中心管理您在网上商城购买的产品订单信息，继续投保或完成支付。" />
		<meta name="keywords" content="订单信息,会员中心,继续投保,完成支付" />
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<link href="${ctx }/resources/css/product/product.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/product/product.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		
		<link href="${ctx }/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/user/member.js" type="text/javascript"></script>
		<link href="${ctx }/resources/css/user/my_order.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx }/resources/js/user/my_order.js" type="text/javascript"></script>
		
		<link href="${ctx}/global/js/msgbox/page.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/msgbox/msgbox.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/msgbox/jquery.myPagination5.0.js" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="order.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/order']).send();
			_hm.push(['_trackPageview','/web/user/member/order']).send();
		</script>
	</body>
</html>
<script>
/**
 * 声明提示框
 */
function alret(parameter,subContentStr){
	Sinosoft.alert({
		contentStr: parameter,
		subContentStr:subContentStr,
		width:480,
		okStr: '确定',
		cancelStr: '取消',
		cancelBtnShow:false
	});
}

var test;
var loading = new Sinosoft.LoadingDialog({
	contentStr: '请耐心等待',
	titleStr:'',
	okStr:'',
	noCancel: true,
	closeFunc:function(){
	},
	waitFunc:function(){
		return test;
	}
});

/**完善订单*/
function perfectOrder(quoteNo,productCode,proposalSID,proposalContNo,policyStatus){
// 	console.log("quoteNo: "+quoteNo+", productCode: "+productCode+", proposalSID: "+proposalSID+", policyStatus: "+policyStatus);
	if(policyStatus == 1 || policyStatus ==  2 || policyStatus ==3){
		$("#quoteNo").val(quoteNo);
		$("#productCode").val(productCode);
		$("#proposalSID").val(proposalSID);
		if(proposalContNo != '' && proposalContNo != 'null')
			$("#proposalContNo").val(proposalContNo);
		$("#order_confirm").attr("action",contextRootPath+"/sale/obtainContinueInsuranceData.do?productCode="+productCode);
		$("#order_confirm").submit();
	}else{
		alret("操作提示","该订单不能继续投保");
	}
}

/**转到支付选择页面*/
function toPayConfirmInfo(productCode,proposalSID){
// 	console.log("toPayConfirmInfo("+productCode+", "+proposalSID+")...");
	loading.open();
	$.ajax({
		type: "POST",
		url:contextRootPath+"/sale/underwritingByorder.do",
		dataType : 'json',
		data:{"proposalSID":proposalSID},
		success: function(data){
			if(data.result != "success"){
				loading.close();
				alret("核保失败",data.result);
			}else{
				$("#productCode").val(productCode);
				$("#proposalSID").val(proposalSID);
				$("#order_confirm").attr("action",contextRootPath+"/sale/toPayConfirmInfo.do");
				$("#order_confirm").submit();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			loading.close();
			if(XMLHttpRequest.status==500){
				alret("操作异常，请稍候再试！","");
			}else{
				alret("操作异常，请稍候再试！",XMLHttpRequest.status);
			}
		}
	});
}
</script>