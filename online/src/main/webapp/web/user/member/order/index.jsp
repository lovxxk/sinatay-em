<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>����������̩���տͻ�����</title>
		<meta name="description" content="��¼��Ա���Ĺ������������̳ǹ���Ĳ�Ʒ������Ϣ������Ͷ�������֧����" />
		<meta name="keywords" content="������Ϣ,��Ա����,����Ͷ��,���֧��" />
		
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
		<!-- ���� -->
		<script type="text/javascript">
			_ga.push(['_trackPageview','/web/user/member/order']).send();
			_hm.push(['_trackPageview','/web/user/member/order']).send();
		</script>
	</body>
</html>
<script>
/**
 * ������ʾ��
 */
function alret(parameter,subContentStr){
	Sinosoft.alert({
		contentStr: parameter,
		subContentStr:subContentStr,
		width:480,
		okStr: 'ȷ��',
		cancelStr: 'ȡ��',
		cancelBtnShow:false
	});
}

var test;
var loading = new Sinosoft.LoadingDialog({
	contentStr: '�����ĵȴ�',
	titleStr:'',
	okStr:'',
	noCancel: true,
	closeFunc:function(){
	},
	waitFunc:function(){
		return test;
	}
});

/**���ƶ���*/
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
		alret("������ʾ","�ö������ܼ���Ͷ��");
	}
}

/**ת��֧��ѡ��ҳ��*/
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
				alret("�˱�ʧ��",data.result);
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
				alret("�����쳣�����Ժ����ԣ�","");
			}else{
				alret("�����쳣�����Ժ����ԣ�",XMLHttpRequest.status);
			}
		}
	});
}
</script>