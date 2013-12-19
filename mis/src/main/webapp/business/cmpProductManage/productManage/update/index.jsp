<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx }/global/js/util/util.js" defer="defer"></script>
<title>电子商务管理系统-产品详细信息配置</title>
</head>
<body>
<div id="div_top">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			产品维护
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:15px;clear:both;">&nbsp;</div>
<div class="stpess_navigation">
	<ul>
    	<li class="stpli_b">
        	<span class="stpli_spanleft">1</span>
            <span class="stpli_spanright1">新建产品</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">2</span>
            <span class="stpli_spanright1">定制流程</span>
        </li>
        <li class="stpli_a">
        	<span class="stpli_spanleft">3</span>
            <span class="stpli_spanright1">详细定义</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">4</span>
            <span class="stpli_spanright2">审核</span>
        </li>
        <li class="stpli_b">
        	<span class="stpli_spanleft">5</span>
            <span class="stpli_spanright2">发布</span>
        </li>
    </ul>
</div>
</div>
<div id="product_main">
	<div id="productWebFlowElementList">
		<c:forEach items="${productWebFlowElementList}" var="productWebFlowElement" step="1" varStatus="status">
			<c:choose>
				<c:when test="${status.index == 0}">
					<div id="${productWebFlowElement[0]}_white" style="display:none;">
						<span class="custom_process_white_left" firstWebFlowElement="true"></span>
						<span class="custom_process_white_middle" firstWebFlowElement="true" onclick="changeIframe('${productWebFlowElement[0]}','${ctx}/${productWebFlowElement[2]}');changeBlue('${productWebFlowElement[0]}');">${productWebFlowElement[1]}</span>
						<span class="custom_process_white_right" firstWebFlowElement="true"></span>
					</div>
					<div id="${productWebFlowElement[0]}_blue">
						<span class="custom_process_blue_left" firstWebFlowElement="true"></span>
						<span class="custom_process_blue_middle" firstWebFlowElement="true" onclick="changeIframe('${productWebFlowElement[0]}','${ctx}/${productWebFlowElement[2]}');">${productWebFlowElement[1]}</span>
						<span class="custom_process_blue_right" firstWebFlowElement="true"></span>
					</div>
				</c:when>
				<c:otherwise>
					<div id="${productWebFlowElement[0]}_white">
						<span class="custom_process_white_left"></span>
						<span class="custom_process_white_middle" onclick="changeIframe('${productWebFlowElement[0]}','${ctx}/${productWebFlowElement[2]}');changeBlue('${productWebFlowElement[0]}');">${productWebFlowElement[1]}</span>
						<span class="custom_process_white_right"></span>
					</div>
					<div id="${productWebFlowElement[0]}_blue" style="display:none;">
						<span class="custom_process_blue_left"></span>
						<span class="custom_process_blue_middle" onclick="changeIframe('${productWebFlowElement[0]}','${ctx}/${productWebFlowElement[2]}');">${productWebFlowElement[1]}</span>
						<span class="custom_process_blue_right"></span>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<div id="overview_white">
			<span class="custom_process_white_left"></span>
			<span class="custom_process_white_middle" onclick="changeIframeForLook();changeBlue('overview');">总览</span>
			<span class="custom_process_white_right"></span>
		</div>
		<div id="overview_blue" style="display:none;">
			<span class="custom_process_blue_left"></span>
			<span class="custom_process_blue_middle" onclick="changeIframeForLook();">总览</span>
			<span class="custom_process_blue_right"></span>
		</div>
	</div>
</div>
<div id="productWebFlowElement">
	<div class="custom_process_blue_bg_line"></div>
	<iframe id="productIframe" src="" frameborder="0" style="overflow:auto;" scrolling="auto"></iframe>
</div>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#productWebFlowElementList span[firstWebFlowElement]").trigger("click");
		autoAdjustIframeHeightAndWidth();
		adjustCustomProcessWidth();
	});
	
	function autoAdjustIframeHeightAndWidth() {
		var bodyHeight = client.viewport.height;
		var bodyWidth = client.viewport.width;
		var divTopHeight = $("#div_top").height();
		var productMainHeight = $("#product_main").height();
		$("#productWebFlowElement").css("width",(bodyWidth - bodyWidth * 0.1));
		$("#productIframe").css("height", (bodyHeight- divTopHeight - productMainHeight - 20));
		$("#productIframe").css("width", $("#productWebFlowElement").width());
		$("#product_main").css("width", (bodyWidth - bodyWidth * 0.1));
	}
	
	function adjustCustomProcessWidth() {
		$(".custom_process_blue_middle").each(function() {
			if (this.clientWidth < 50 && this.clientWidth > 0) {
				 $(this).css("width", "50px");
			 }
		});
		$(".custom_process_white_middle").each(function() {
			if (this.clientWidth < 50 && this.clientWidth > 0) {
				 $(this).css("width", "50px");
			 }
		});
	}
	function changeBlue(obj){
		$("#productWebFlowElementList div").each(function(){
			var divId = this.id;
			if (divId == (obj + "_blue")) {
				$(this).show();
			} else if (divId == (obj + "_white")) {
				$(this).hide();
			} else {
				if (divId.indexOf("_white") > -1) {
					$(this).show();
				} else {
					$(this).hide();
				}
			}
		});
		adjustCustomProcessWidth();
	}
	
	
	function changeIframeForLook(){
		document.getElementById("productIframe").src = "${ctx}/productManage/toProductDetailOverview.do?coreProductCode=${coreProductCode}";
	}
	
	function changeIframe(obj,url){
		document.getElementById("productIframe").src = url+"?coreProductCode=${coreProductCode}&elementCode=" + obj;
	}
</script>
</html>
