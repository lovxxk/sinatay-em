<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html>
<head>
<title>编辑支付方式</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<style type="text/css">
 td{
 	vertical-align:top;
 }
 input, select {
 	width:200px;
 }
 #submitTable {
 	margin-left: 114px;
 	margin-top: 20px;
 }
 #submitTable td {
 	width:85px;
 	vertical-align:middle;
 }
 #frmInput {
 	padding-top:10px;
 }
 textarea {
 	margin-bottom:4px;
 }
</style>
</head>
<body>
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				编辑支付方式
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div style="padding-top:15px;">
	<center>
		<form name="frmInput" id="frmInput" action="${ctx}/productManage/updateGePayment.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table id="dutyKindTable" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td class="td_head" nowrap>支付方式代码： </td>
					<td class="td_body" valign="top" width="343px;">${gePayment.paymentCode}</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>支付方式名称：</td>
					<td class="td_body" valign="top">
						<input type="hidden" name="gePayment.paymentId" value="${gePayment.paymentId}"/>
						<input type="text" id="paymentName" name="gePayment.paymentName"  value="${gePayment.paymentName}" />
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>网关：</td>
					<td class="td_body" valign="top">
						<input maxlength="35" type="text" id="gateId" name="gePayment.gateId"  value="${gePayment.gateId}" />
					</td>
				</tr>
				
				<tr>
					<td class="td_head">
						<span style="line-height:23px;">logo图片：</span>
					</td>
					<td class="td_body">
						<s:file id="logoImg" name="logoImg" onfocus="addPreviewFace(this.id);" size="31"></s:file><br/>
						<c:choose>
							<c:when test="${gePayment.logoImg != null}">
								<img id="logoImgPreview" src="${ctx}/${gePayment.logoImg}" border="0" height="61" width="61"/>
							</c:when>
							<c:otherwise>
								<img id="logoImgPreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr height="130">
					<td  colspan="2"  class="frmCreate_kuang" style="border: 0"valign="top" align="center">
				<div style="width:550" id="remark" >
					<div class="frmCreate_kuang_header" >
						备注：
					</div>
					<div>
					<FCK:editor instanceName="proposalNotice" basePath="/global/fckeditor" height="320px" width="" inputName="gePayment.remark" value="${gePayment.remark}">
					<FCK:config SkinPath="skins/office2003/"/>
					</FCK:editor>
					</div>
				</div>	
				</td>
				</tr>
				<tr style="padding-top: 10px">
					<td class="td_head" nowrap>
						支持地区:
					</td>
					<td>
					<table class="frmCreate_kuang" >
						<tr>
							<td class="frmCreate_kuang_header"></td>
						</tr>
						<tr>
							<td style="text-align:left; padding-left:15px; padding-top:15px;" valign="top">
								<div id="showListLoading" style="position:absolute;">
									<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
								</div>
								<div id="authorityTree" style="overflow-x:hidden;width:290px;height:300px;"></div>
							</td>
						</tr>
					</table>
				</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/productManage/paymentDetail.do?paymentId=${gePayment.paymentId}';">返回</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
									<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="paymentCode" name="gePayment.paymentCode" value="${gePayment.paymentCode}" />
			<input type="hidden" id="deptidSave" name="deptidSave" value="" />
			<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
		</form>
	</center>
</div>
<script type="text/javascript"><!--
var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-330;

//初始化tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/productManage/paymentAreaTree.do?paymentId=${gePayment.paymentId}",loadOver);

function myErrorHandler(type, desc, erData){
	   return;
	}
dhtmlxError.catchError("ALL",myErrorHandler);

function validateDept(deptids){
	if(deptids == ''){
		return false;
	}
	return true;
}
var ids = ['gateId','remark'];
var contents = ['现在仅支持银联无卡','支付方式描述'];
for ( var i = 0 ; i < ids.length ; i++ ){
	$('#' + ids[i]).qtip({ 
		content:contents[i], 
		position: { 
			corner: { 
			tooltip: 'leftMiddle', 
			target: 'rightMiddle'
			} 
		}, 
		 style: { 
		border: { 
			width: 2,
			radius: 2,
			color: '#00739f'
			},
			width:125,
			padding: 6, 
			textAlign: 'left',
			background: '#e0f2ff', 
			tip:true//控制左侧尖角是否出现
			//name: 'green' 
		} 
	}); 
}
function getAuthDepts(){
	var allAuthDepts = "";
	var allSelected=tree.getAllCheckedBranches();
	
	var listId=allSelected.split(",");
	for(var i=0;i<listId.length;i++){
		if(!tree.hasChildren(listId[i])){
			allAuthDepts+=listId[i]+",";
		}
	}

	allAuthDepts = allAuthDepts.substring(0,allAuthDepts.length-1);
	return allAuthDepts;
}

function loadOver(sIdNow){
	$("#showListLoading").hide();
}

--></script>
<script type="text/javascript">
function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview(); 
	};
}

$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	tt.vf.req.add("gePayment.paymentName");
	//new tt.LV().set(1,100).add("gePayment.remark");

	//var oEditor = FCKeditorAPI.GetInstance("proposalNotice");
	//oEditor.blur(function(){
	//	alert("check");
	//});
	
	//表单提交
	$("#addButtonSubmit").click(function(){
		var allAuthDepts = getAuthDepts();/**所有选中的叶子节点*/
		
		if(!tt.validate()){
			return false;
		}else{
			if(allAuthDepts.length==0){
				alert("请选择支持地区!");
		    	return;
			}
			// 验证fck
			var oEditor = FCKeditorAPI.GetInstance("proposalNotice");
		    var msg = oEditor.GetXHTML(true);
		    if(msg.length>1000){
		    	alert("备注信息内容长度不能超过1000个字符!");
		    	return;
		    }
			
			$("#deptidSave").attr("value",allAuthDepts);
			$("#frmInput").submit();
		}
	});
	
});

function doClear(){
	window.location.reload();
	document.getElementById("frmInput").reset();
}
var y = document.getElementsByTagName("input");
for (var i=0; i < y.length; i++){
	if(y[i].type == 'text'){
		y[i].onkeyup = showMyStatus;
	}
}
function showMyStatus(evnt){
	var obj,errorCode;
	if (isIE()) {
		obj = event.srcElement;
	}else {
		obj = evnt.target;
	}
	validateValue(obj);
}

function isIE() {
	if(document.all) return true;
	return false;
}

String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function validateValue(obj) {
	var patn = /(^\s)|(\s$)/;
	if (patn.test(obj.value))
		obj.value = obj.value.trim();
}
</script>
</body>
</html>