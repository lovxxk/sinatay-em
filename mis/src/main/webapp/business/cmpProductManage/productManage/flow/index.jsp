<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务后台管理系统-非车险产品管理-产品流程定义</title>
</head>
<body>
<div id="div_top">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			定制流程
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div style="height:20px;clear:both;">&nbsp;</div>
<div class="stpess_navigation">
	<ul>
    	<li class="stpli_b">
        	<span class="stpli_spanleft">1</span>
            <span class="stpli_spanright1">新建产品</span>
        </li>
        <li class="stpli_a">
        	<span class="stpli_spanleft">2</span>
            <span class="stpli_spanright1">定制流程</span>
        </li>
        <li class="stpli_b">
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
	<div style="height: 10px"></div>
	<div id="proceeContent">
		<div id="productSaleFlow">
			<div id="flowCustomDesc">
				<div id="flowCustomDescTitle">
					请您定制产品销售流程：
				</div>
			</div>
			<div id="webFlowPage">
				<c:forEach items="${webFlowPageList}" var="webFlowPage" step="1" varStatus="status">
					<c:choose>
						<c:when test="${status.index == 0}">
							<div id="${webFlowPage.gePageConfig.pageCode}_white" style="display:none;">
								<span class="custom_process_white_left"></span>
								<span class="custom_process_white_middle" pageCode="${webFlowPage.gePageConfig.pageCode}"  serialNo="${webFlowPage.serialNo}" firstWebFlowElement="true" onclick="pageSwitch('${webFlowPage.gePageConfig.pageCode}');changeBlue('${webFlowPage.gePageConfig.pageCode}');">${webFlowPage.gePageConfig.pageName}</span>
								<span class="custom_process_white_right"></span>
							</div>
							<div id="${webFlowPage.gePageConfig.pageCode}_blue">
								<span class="custom_process_blue_left"></span>
								<span class="custom_process_blue_middle" pageCode="${webFlowPage.gePageConfig.pageCode}" serialNo="${webFlowPage.serialNo}" firstWebFlowElement="true" onclick="pageSwitch('${webFlowPage.gePageConfig.pageCode}');">${webFlowPage.gePageConfig.pageName}</span>
								<span class="custom_process_blue_right"></span>
							</div>
						</c:when>
						<c:otherwise>
							<div id="${webFlowPage.gePageConfig.pageCode}_white">
								<span class="custom_process_white_left"></span>
								<span class="custom_process_white_middle" pageCode="${webFlowPage.gePageConfig.pageCode}" serialNo="${webFlowPage.serialNo}" onclick="pageSwitch('${webFlowPage.gePageConfig.pageCode}');changeBlue('${webFlowPage.gePageConfig.pageCode}');">${webFlowPage.gePageConfig.pageName}</span>
								<span class="custom_process_white_right"></span>
							</div>
							<div id="${webFlowPage.gePageConfig.pageCode}_blue" style="display:none;">
								<span class="custom_process_blue_left"></span>
								<span class="custom_process_blue_middle" pageCode="${webFlowPage.gePageConfig.pageCode}" serialNo="${webFlowPage.serialNo}" onclick="pageSwitch('${webFlowPage.gePageConfig.pageCode}');">${webFlowPage.gePageConfig.pageName}</span>
								<span class="custom_process_blue_right"></span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="custom_process_blue_bg_line"></div>
			<div id="flowPageElementSpace">
				<c:forEach items="${webFlowPageList}" var="webFlowPage" varStatus="status">
					<c:choose>
						<c:when test="${status.index == 0}">
							<div class="flowPageSwitch" id= "${webFlowPage.gePageConfig.pageCode}_table">
								<table border=0 cellpadding=0 cellspacing=0>
									<tr>
										<td width="200px" valign="top">
											<select id="${webFlowPage.gePageConfig.pageCode}_mode" name="mode" multiple>
												<c:forEach items="${webFlowPage.gePageConfig.gePageConfigRelates}" var="pageConfigRelate">
													<option value="${pageConfigRelate.gePageElementConfig.elementCode}" required="${pageConfigRelate.required}" style="height:18px;">&nbsp;&nbsp;${pageConfigRelate.gePageElementConfig.elementName}</option>
												</c:forEach>
											</select>
										</td>
										<td  style="width:100px;text-align:center;">
											<input type="button" value="增加&gt;&gt;" onclick="addProcess('${webFlowPage.gePageConfig.pageCode}');" id="Button1" name="Button1" style="width:62px;height:23px;border:none;">
											<p>
											<input type="button" value="&lt;&lt;删除" onclick="deleteProcess('${webFlowPage.gePageConfig.pageCode}');" id="Button2" name="Button2" style="width:62px;height:23px;border:none;">
										</td>
										<td width="200px" valign="top">
											<select id="${webFlowPage.gePageConfig.pageCode}_process" name="geWebFlowPageList[${status.index}].geWebFlowPageElements" multiple>
												<c:forEach items="${webFlowPage.geWebFlowPageElements}" var="webFlowPageElement">
													<option value="${webFlowPageElement.gePageElementConfig.elementCode}" serialNo="${webFlowPageElement.serialNo}" style="height:18px;">&nbsp;&nbsp;${webFlowPageElement.gePageElementConfig.elementName}</option>
												</c:forEach>
											</select>
										</td>
										<td  style="width:100px;text-align:center;">
											<input class="upClass"  type="button" value="" onclick="upListItem('${webFlowPage.gePageConfig.pageCode}');" style="width:62px;height:23px; margin:0px 0px 8px 0px;border:none;">
											<br>
											<input class="downClass" type="button" value="" onclick="downListItem('${webFlowPage.gePageConfig.pageCode}');" style="width:62px;height:23px;border:none;">
										</td>
									</tr>
								</table>
								<input type="hidden" name="geWebFlowPageList[${status.index}].geProductMain.coreProductCode" value="${coreProductCode}"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="flowPageSwitch" id="${webFlowPage.gePageConfig.pageCode}_table"  style="display:none;">
								<table align="center" border=0 cellpadding=0 cellspacing=0>
									<tr>
										<td width="200px" valign="top">
											<select id="${webFlowPage.gePageConfig.pageCode}_mode" name="mode" multiple>
												<c:forEach items="${webFlowPage.gePageConfig.gePageConfigRelates}" var="pageConfigRelate">
													<option value="${pageConfigRelate.gePageElementConfig.elementCode}" required="${pageConfigRelate.required}" style="height:18px;">&nbsp;&nbsp;${pageConfigRelate.gePageElementConfig.elementName}</option>
												</c:forEach>
											</select>
										</td>
										<td style="width:100px;text-align:center;">
											<input type="button" value="增加&gt;&gt;" onclick="addProcess('${webFlowPage.gePageConfig.pageCode}');" id="Button1" name="Button1" style="width:62px;height:23px;border:none;">
											<p>
											<input type="button" value="&lt;&lt;删除" onclick="deleteProcess('${webFlowPage.gePageConfig.pageCode}');" id="Button2" name="Button2" style="width:62px;height:23px;border:none;">
										</td>
										<td width="200px" valign="top">
											<select id="${webFlowPage.gePageConfig.pageCode}_process" name="process" multiple>
												<c:forEach items="${webFlowPage.geWebFlowPageElements}" var="webFlowPageElement">
													<option value="${webFlowPageElement.gePageElementConfig.elementCode}" serialNo="${webFlowPageElement.serialNo}" style="height:18px;">&nbsp;&nbsp;${webFlowPageElement.gePageElementConfig.elementName}</option>
												</c:forEach>
											</select>
										</td>
										<td  style="width:100px;text-align:center;">
											<input class="upClass" type="button" value="" onclick="upListItem('${webFlowPage.gePageConfig.pageCode}');" style="width:62px;height:23px;border:none;margin:0px 0px 8px 0px;">
											<br>
											<input class="downClass" type="button" value="" onclick="downListItem('${webFlowPage.gePageConfig.pageCode}');" style="width:62px;height:23px;border:none;">
										</td>
									</tr>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		
		<div id="productSaleFlowDesc" >
			<div id="productCustomSaleFlowDesc">
				您定制的产品销售流程如下：
			</div>
			<div id="processDesc" style="padding-left:20px;padding-top:10px;padding-bottom:30px;">
			</div>
		</div>
	</div>
	<div style="float:left; text-align: center; width:100%; padding-top: 20px;">
		<table align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="saveWebFlowPageElement();" nowrap>保存</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClearNew('${coreProductCode}');" nowrap>重置</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>返回</td>
			</tr>
		</table>
		
		<div style="height: 50px"></div>
	</div>
	

</body>
<script type="text/javascript"><!--
	$(document).ready(function(){
		$("#webFlowPage span[firstWebFlowElement]").trigger("click");
		adjustCustomProcessWidth();
	});
	function pageSwitch(obj) {
		$(".flowPageSwitch").hide();
		$("#" + obj + "_table").show();
		setProcessDesc(obj);
		changeBlue(obj);
	}
	function changeBlue(obj){
		$("#webFlowPage div").each(function(){
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
	function setProcessDesc(obj) {
		var processDesc = "";
		var processSelect  = document.getElementById(obj + "_process");
		for (var i = 0; i < processSelect.length; i++) {
			if (i == 0) {
				processDesc += processSelect[i].text;
			} else {
				processDesc += "&nbsp;&nbsp;<img src='${ctx}/global/images/custom_process_arrow.jpg' style='margin:0px 0px -5px 0px;'></img>" + processSelect[i].text;
			}
		}
		$("#processDesc").html("");
		$("#processDesc").append(processDesc);
	}
	

	function saveWebFlowPageElement() {
		var webFlowPageList = "[";
		var webFlowPageOperator = $("#webFlowPage span[pageCode]");
		for (var i = 0; i < webFlowPageOperator.length ; i++) {
			if ($(webFlowPageOperator[i]).parent().css("display") != "none") {
				var webFlowPageElements = document.getElementById(webFlowPageOperator[i].getAttribute("pageCode") + "_process");
				if (i ==0) {
					for (var j = 0; j < webFlowPageElements.length; j++) {
						if (j == 0) {
						
							webFlowPageList += "{";
							webFlowPageList += "\"gePageElementConfig\":{\"elementCode\":\"" + webFlowPageElements[j].value + "\"},";
							webFlowPageList += "\"geProductMain\":{\"coreProductCode\":\"${coreProductCode}\"},";
							webFlowPageList += "\"geWebFlowPage\":{\"serialNo\":\"" + webFlowPageOperator[i].getAttribute("serialNo") + "\"},";
							if (webFlowPageElements[j].getAttribute("serialNo") != null && webFlowPageElements[j].getAttribute("serialNo") != "") {
								webFlowPageList += "\"serialNo\":\"" + webFlowPageElements[j].getAttribute("serialNo") + "\",";
							}
							webFlowPageList += "\"seqIndex\":" + (j + 1);
							webFlowPageList += "}";
						} else {
							webFlowPageList += ",{";
							webFlowPageList += "\"gePageElementConfig\":{\"elementCode\":\"" + webFlowPageElements[j].value + "\"},";
							webFlowPageList += "\"geProductMain\":{\"coreProductCode\":\"${coreProductCode}\"},";
							webFlowPageList += "\"geWebFlowPage\":{\"serialNo\":\"" + webFlowPageOperator[i].getAttribute("serialNo") + "\"},";
							if (webFlowPageElements[j].getAttribute("serialNo") != null && webFlowPageElements[j].getAttribute("serialNo") != "") {
								webFlowPageList += "\"serialNo\":\"" + webFlowPageElements[j].getAttribute("serialNo") + "\",";
							}
							webFlowPageList += "\"seqIndex\":" + (j + 1);
							webFlowPageList += "}";
						}
					}
				} else {
					for (var j = 0; j < webFlowPageElements.length; j++) {
						if (j == 0) {
							webFlowPageList += ",{";
							webFlowPageList += "\"gePageElementConfig\":{\"elementCode\":\"" + webFlowPageElements[j].value + "\"},";
							webFlowPageList += "\"geProductMain\":{\"coreProductCode\":\"${coreProductCode}\"},";
							webFlowPageList += "\"geWebFlowPage\":{\"serialNo\":\"" + webFlowPageOperator[i].getAttribute("serialNo") + "\"},";
							if (webFlowPageElements[j].getAttribute("serialNo") != null && webFlowPageElements[j].getAttribute("serialNo") != "") {
								webFlowPageList += "\"serialNo\":\"" + webFlowPageElements[j].getAttribute("serialNo") + "\",";
							}
							webFlowPageList += "\"seqIndex\":" + (j + 1);
							webFlowPageList += "}";
						} else {
							webFlowPageList += ",{";
							webFlowPageList += "\"gePageElementConfig\":{\"elementCode\":\"" + webFlowPageElements[j].value + "\"},";
							webFlowPageList += "\"geProductMain\":{\"coreProductCode\":\"${coreProductCode}\"},";
							webFlowPageList += "\"geWebFlowPage\":{\"serialNo\":\"" + webFlowPageOperator[i].getAttribute("serialNo") + "\"},";
							if (webFlowPageElements[j].getAttribute("serialNo") != null && webFlowPageElements[j].getAttribute("serialNo") != "") {
								webFlowPageList += "\"serialNo\":\"" + webFlowPageElements[j].getAttribute("serialNo") + "\",";
							}
							webFlowPageList += "\"seqIndex\":" + (j + 1);
							webFlowPageList += "}";
						}
					}
				}	
			}
		}
		webFlowPageList += "]";
		
		
		$.ajax({
			url : "${ctx}/productManage/saveProductWebFlow.do",
			data : {
				"webFlowPageElementList" : webFlowPageList,
				"coreProductCode":"${coreProductCode}"
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
				alert("保存失败!");
			},
			success : function(data) {
				alert("保存成功!");
				window.parent.close();
				window.parent.opener.parent.frames[0].doSearch();
			}
		});
	}
	
	//添加流程
	function addProcess(obj){
		var listObj = document.getElementById(obj + '_mode');
		var selIndex = listObj.selectedIndex;
		if(selIndex < 0){
			return; 
		}
		var selValue = listObj.options[selIndex].value;
		var selText = listObj.options[selIndex].text;
		var process = document.getElementById(obj + '_process');
		for(var i = 0; i < process.children.length; i++){
			if(selValue == process.children[i].value){
				alert("该流程已经存在！");
				return;
			}
		}
		process.options[process.options.length] = new Option(selText,selValue);
		$(process.options[process.options.length - 1]).css("height", "18px");
		setProcessDesc(obj); 
	}
	
	//删除流程
	function deleteProcess(obj){
		var listObj = document.getElementById(obj + '_mode');
		var process = document.getElementById(obj+ '_process');
		var selIndex = process.selectedIndex;
		if(selIndex < 0){ 
			return; 
		}
		var selValue = process.options[selIndex].value;
		for (var i = 0; i < listObj.length; i++) {
			if (selValue == listObj[i].value) {
				if ("02" == listObj[i].getAttribute("required")) {
					return;
				}
			}
		}
		process.remove(selIndex);
		setProcessDesc(obj); 
	}
	
	function initProcess(){
		var listObj = document.getElementById('process');
		var length = listObj.options.length;
		if(length!=0){
			var selectIndex;
			selectedIndex = length - 1;
			selectedOption = listObj.options(selectedIndex);
			listObj.removeChild(selectedOption);
		}
	}
	
	//将选中item向上 
	function upListItem(obj){ 
		var listObj = document.getElementById(obj + '_process');
		var selIndex = listObj.selectedIndex;
		if(selIndex <= 0){ 
			return; 
		}
		//update list
		var selValue = listObj.options[selIndex].value; 
		var selText = listObj.options[selIndex].text; 
		var lastValue = listObj.options[selIndex-1].value;
		var lastText = listObj.options[selIndex-1].text;
		listObj.options[selIndex].value = lastValue; 
		listObj.options[selIndex].text = lastText; 
		listObj.options[selIndex-1].value = selValue; 
		listObj.options[selIndex-1].text = selText; 
		listObj.selectedIndex = selIndex-1;
		setProcessDesc(obj); 
	}
	
	//将选中item向下 
	function downListItem(obj){ 
		var listObj=document.getElementById(obj + '_process');
		var selIndex=listObj.selectedIndex; 
		if(selIndex<0||selIndex==listObj.options.length-1){ 
			return; 
		}
		//down list
		var selValue=listObj.options[selIndex].value; 
		var selText=listObj.options[selIndex].text; 
		var nextValue = listObj.options[selIndex+1].value;
		var nextText = listObj.options[selIndex+1].text;
		listObj.options[selIndex].value=nextValue; 
		listObj.options[selIndex].text=nextText; 
		listObj.options[selIndex+1].value=selValue; 
		listObj.options[selIndex+1].text=selText; 
		listObj.selectedIndex=selIndex+1;
		setProcessDesc(obj); 
	}
	
	function doNext(){
		getBuyProcess();
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){	
		document.getElementById("frmInput").reset();
		var listObj = document.getElementById('process');
		var length = listObj.options.length;
		for(var i=0;i<length;i++){
			initProcess();
		}
	}
	
	function doClose(){
		this.close();
	}
	
	function doClearNew(coreProductCode){
		location.href = "${ctx}/productManage/findProductWebFlow.do?coreProductCode=" + coreProductCode;
	}
	
</script>
</html>