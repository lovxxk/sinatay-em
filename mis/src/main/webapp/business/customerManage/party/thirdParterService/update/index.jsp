<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<title>电子商务后台管理系统-编辑第三方产品</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
<%//tao 哥 %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<style type="text/css">
	td {
		vertical-align:top;
	}
	#productDetail tr {
		height:85px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
	}
</style>
</head>
<body  onload="pageValidate();">
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				编辑第三方产品
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form id="frmInput" action="${ctx}/party/updateGeThirdParterService.do" method="post" target="myFrame" enctype="multipart/form-data">
	<input type="hidden" name="geThirdParterService.itemID" value="<s:property value="geThirdParterService.itemID"/>" />
	<input type="hidden" name="geThirdParterService.pictureUrl" value="<s:property value="geThirdParterService.pictureUrl"/>" />
	<table  align="center" width="550px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>公司名称：</td>
		<td class="td_body">
			<input name="thirdParterName" value="<s:property value="geThirdParterService.geThirdParterInfo.thirdParterName"/>" ondblclick="alertThirdService();" readonly="readonly" maxlength=30 style="width:175px;">(双击域选择)
			<input type="hidden" name="geThirdParterService.geThirdParterInfo.thirdParterID" value="<s:property value="geThirdParterService.geThirdParterInfo.thirdParterID"/>" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品名称：</td>
		<td class="td_body">
			<input type="text" name="geThirdParterService.itemName" value="<s:property value="geThirdParterService.itemName"/>" style="width:175px;" maxlength=180 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品数量：</td>
		<td class="td_body">
			<input type="text" name="geThirdParterService.totalNumber" value="<s:property value="geThirdParterService.totalNumber"/>" style="width:175px;" maxlength=8 >
		</td>
	</tr>
	<%/*
	<tr>
		<td class="td_head" nowrap>商品图片：</td>
		<td class="td_body">
			<img src="${ctx}/<s:property value="geThirdParterService.pictureUrl"/>"/>
		</td>
	</tr>*/%>
	<tr>
		<td class="td_head" nowrap>修改图片：</td>
		<td class="td_body">
			<input type="file" name="attrPicture"  id="attrPictureUpload" style="width:175px;" onblur="addPreviewFace(this.id);"/>
		</td>
	</tr>
	<tr id="attrPictureUploadTR" >
		<td class="td_head" nowrap valign="top" >图片预览：</td>
		<td class="td_body">
			<input type="hidden" id="hiddenImg" value="${ctx}/<s:property value="geThirdParterService.pictureUrl"/>" />
			<img  id="attrPictureUploadPreview" style="display: none;"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品简介：</td>
		<td class="td_body">
			<textarea rows="8" cols="40"  onkeyup="textAreaMaxLen(this)" maxlength="800" name="geThirdParterService.itemContent"><s:property value="geThirdParterService.itemContent" /></textarea>
		</td>
	</tr>
	<tr><td height="25px"></td></tr>
	<tr>
		<td colspan="2">
			<table align="center" id="operatorTable">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/party/view.do?geThirdParterService.itemID=<s:property value='geThirdParterService.itemID'/>';">返回</td>
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	<div id="message"></div>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">

			
//表单提交
$("#updateButton").click(function(){
	if(!tt.validate()){
		return false;
	}else{
		$("#frmInput").submit();
	}
});


function doClear(){
	location.href=location.href;
	//document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.addName("geThirdParterService.itemName","geThirdParterService.itemContent","thirdParterName","geThirdParterService.totalNumber");
	tt.vf.num.add("geThirdParterService.totalNumber");
	var intRange = new tt.RV().set(new RegExp("^[0-9]+$"), "只能输入正整数");
	intRange.add("geThirdParterService.totalNumber");
	//显示图片的
	addPreviewFaceInit("hiddenImg");
}


/**
 * 身份证号校验
 */
function validateIDNO(obj){
	
	var IDTypeValue = document.getElementById("identifyType").value;
	if(IDTypeValue != "" && IDTypeValue == "01"){
		if(obj.value != "")tt.vf.idcard.addName("geBlackList.identifyNumber");
	}else{
		tt.vf.idcard.rmName("geBlackList.identifyNumber");
	}
}
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("最多输入800个字");
	    }

}
//弹出一个商品的提示框
function alertThirdService(){
	window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","查询供应商" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

function addPreviewFaceInit(obj){
	if($("#hiddenImg").val()!=""){
		ImagePreview.MODE = "simple";//让走simplle模式
		var facePic = new ImagePreview( $$("hiddenImg"), $$("attrPictureUploadPreview"), {maxWidth: 230, maxHeight: 160});
		facePic.preview();
		document.getElementById("attrPictureUploadPreview").style.display="";	
	}
}

function addPreviewFace(obj){
	ImagePreview.MODE = "filter";//让走simplle模式
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview();
		document.getElementById(obj+"TR").style.display="";
	};
}
</script>
</body>
</html>
