<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>订单详情-会员中心</title>
		
		<%@ include file="/global/ui/taglibs_head.jsp"%>
		
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.tools.min.js"></script>
		<script type="text/javascript" src="${ctx}/global/js/jquery/jquery.colorbox.js"></script>
		
		<link href="${ctx}/global/css/jSelect.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery.sinosoft.select.js" type="text/javascript"></script>
		<link href="${ctx}/resources/css/user/member.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/member.js" type="text/javascript"></script>
		
		<link href="${ctx}/resources/css/user/my_order_detail.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/resources/js/user/my_order_detail.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/alert/alert.js" type="text/javascript"></script>
		
	</head>
	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<jsp:include page="detail.jsp"></jsp:include>
		<jsp:include page="/footer.jsp"></jsp:include>
		<!-- 插码 -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/account/order/detail']).send();
			_hm.push(['_trackPageview','/web/user/member/account/order/detail']).send();
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