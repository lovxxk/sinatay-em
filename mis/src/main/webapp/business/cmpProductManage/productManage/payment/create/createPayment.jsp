<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>创建支付方式</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
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
 	margin-top: 20px;
 	margin-left: 60px;
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
<div class="public_fun_title" >新建支付方式</div>
<center>
	<form name="frmInput" id="frmInput" action="${ctx}/productManage/createPayment.do" method="post" enctype="multipart/form-data">
		<table width="720px" id="paymentTable" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="25%" class="td_head"  nowrap>支付方式代码： </td>
				<td width="75%" class="td_body" width="450px;"  valign="top">
					<input type="text" id="paymentCode" name="gePayment.paymentCode"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>支付方式名称：</td>
				<td class="td_body" valign="top">
					<input type="text" id="paymentName" name="gePayment.paymentName"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>网关：</td>
				<td class="td_body" valign="top">
					<input type="text" id="gateId" name="gePayment.gateId"  value="" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td class="td_head"  valign="top" nowrap>
					<span style="line-height:23px;">logo图片：</span>
				</td>
				<td class="td_body">
					<s:file id="logoImg" name="logoImg" onfocus="addPreviewFace(this.id);" size="31" ></s:file><br/>
					<img src="${ctx}/global/images/productMiddlePicture.jpg" id="logoImgPreview"  width="61" height="61"/>
				</td>
			</tr>
			<tr>
				
				<td  colspan="2"  class="frmCreate_kuang" style="border: 0"valign="top" align="center">
				<div style="width:550" id="remark" >
					<div class="frmCreate_kuang_header" >
						备注：
					</div>
					<div>
					<FCK:editor instanceName="proposalNotice" basePath="/global/fckeditor" height="320px" width="" inputName="gePayment.remark" >
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
				<td align="center" colspan="2">
					<table id="submitTable" >
						<tr>
							<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" nowrap>创建</td>
							<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" id="deptidSave" name="deptidSave" value="" />
	</form>
</center>
<script type="text/javascript"><!--
//初始化tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/productManage/paymentAreaTree.do",loadOver);

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
	tt.vf.req.add("gePayment.paymentCode");
	tt.vf.req.add("gePayment.paymentName");

	$("#paymentCode").blur(function(){
		/*
		var paymentCode = $("#paymentCode").val();
		$.ajax({
			url : "${ctx}/productManage/isHavePayment.do",
			data : {
				"paymentCode" : paymentCode
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#paymentCode").val("");
					$("#paymentCodeContent").remove();
					$("#paymentCode").parent().append("<span id='paymentCodeContent' style='width:300px'><span class='talentErrMsg'>支付方式代码已经存在，请更改支付方式代码！</span></span>")
				} else {
					$("#paymentCodeContent").remove();
				}
			}
		});*/
		
	});
	
	$("#paymentCode").focus(function(){
		$("#paymentCodeContent").remove();
	});
	
	$("textarea").blur(function(){
		validateValue(this);
	});
	
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
	
	var addReslut = "${addReslut}";
	if (addReslut == "success") {
		alert("支付方式添加成功！");
	} else if (addReslut == "failure") {
		alert("支付方式添加失败！");
	}	
	
});

function doClear(){
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
$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
var ids = ['des'];
    	var contents = ['说明:创建集团网销等使用的支付方式<br/>'
    	                + '使用人群:支付方式配置人员<br/>'
    	                + '配置条件:<br/>'
    	                + '注意事项:其中的备注是支付方式的介绍性文字，会在前台展示，配置时开头空两格，有超链接的，链接地址用双引号“”括起'];
        	for ( var i = 0 ; i < ids.length ; i++ ){
    			$('#' + ids[i]).qtip({ 
    				content:contents[i], 
    				position: { 
						corner: { 
						tooltip: 'topMiddle',
						target: 'bottomMiddle'
						} ,
						adjust: { 
							screen: true 
							}
					}, 
					 style: {
							border: { 
								width: 1,
								radius: 1,
								color: '#00739f'
								},
								width:450,
								textAlign: 'left',
								background: '#e0f2ff', 
								tip:true,//控制左侧尖角是否出现
								padding :10
							}
						});
        	}
</script>
</body>
</html>
