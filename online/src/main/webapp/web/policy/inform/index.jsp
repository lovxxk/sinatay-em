<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<title>填写订单—健康告知—信泰网上商城</title>
</head>
<div class="kuang1">
	<c:forEach items="${geInformBooks }" var="informbook" varStatus="informStatus">
 		<div class="kuang_main">
 			<div class="kuang_text">
 				<span>
 					<span>${informbook[1] }</span>
			 	</span>
 			</div>
 			<!-- ${pageCode == 'insureConfirmPage' ? 'disabled' : ''} ${informbook[2] == 'Y'?"checked":"" } -->
 			<!-- ${pageCode == 'insureConfirmPage' ? 'disabled' : ''} ${informbook[2] == 'N'?"checked":"" } -->
 			<div class="kuang_radio">
 			 	<input type="radio" tag="y${informStatus.index }" act="y" id="yes${informbook[0] }" fid="no${informbook[0] }" name="yes${informbook[0] }" />是
 			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			 	<input type="radio" tag="n${informStatus.index }" act="n" id="no${informbook[0] }" fid="yes${informbook[0] }" name="yes${informbook[0] }" />否
 			</div>
		</div>
	</c:forEach>
</div>
<input type="hidden" id="changeId" value="">
<input type="hidden" id="changeType" value="0">
<script type="text/javascript">
function doCancelInform(obj){
	$("#changeType").val("2");
	$("#"+$("#changeId").val()).click();
	$("#changeType").val("1");
}
function doCloseInform(){
	window.close();
}

// function doCancel(){
// 	$("#changeType").val("1");
// 	this.blur();
// }

function alret_1(parameter,subContentStr){
	Sinosoft.alert({
		contentStr: parameter,
		subContentStr:subContentStr,
		width:480,
		okStr: '确定',
		cancelStr: '取消',
		cancelBtnShow:false,
		okFunc:function(){
			doCancelInform();
		}
	});

}

$(function(){
	$(".kuang_radio input").change(function(){
// 		console.log($(this).attr('checked')+", "+$(this).attr('act'));
		if($(this).attr('act') == 'y'){
			$("#changeType").val("1");
		}else if($(this).attr('act') == 'n'){
			$("#changeType").val("2");
		} 
// 		console.log($("#changeType").val());
		if($("#changeType").val() == "1"){
			$("#changeId").val($(this).attr("fid"));
			alret_1("修改健康告知提示","尊敬的客户，您修改了健康告知，说明不具备该产品的投保条件，系统自动修改为初始状态，以便继续进行投保。");
		}
	});
});

</script>
