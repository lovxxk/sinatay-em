<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>����֧����ʽ</title>
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
<div class="public_fun_title" >�½�֧����ʽ</div>
<center>
	<form name="frmInput" id="frmInput" action="${ctx}/productManage/createPayment.do" method="post" enctype="multipart/form-data">
		<table width="720px" id="paymentTable" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="25%" class="td_head"  nowrap>֧����ʽ���룺 </td>
				<td width="75%" class="td_body" width="450px;"  valign="top">
					<input type="text" id="paymentCode" name="gePayment.paymentCode"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>֧����ʽ���ƣ�</td>
				<td class="td_body" valign="top">
					<input type="text" id="paymentName" name="gePayment.paymentName"  value="" />
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>���أ�</td>
				<td class="td_body" valign="top">
					<input type="text" id="gateId" name="gePayment.gateId"  value="" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td class="td_head"  valign="top" nowrap>
					<span style="line-height:23px;">logoͼƬ��</span>
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
						��ע��
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
					֧�ֵ���:
				</td>
				<td>
					<table class="frmCreate_kuang" >
						<tr>
							<td class="frmCreate_kuang_header"></td>
						</tr>
						<tr>
							<td style="text-align:left; padding-left:15px; padding-top:15px;" valign="top">
								<div id="showListLoading" style="position:absolute;">
									<div class="loading_process1" style="height:50px; font-size: 16px;">���ݼ����У����Ժ� . . .</div>
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
								onmouseout="this.className='btnBigOnBlur'" nowrap>����</td>
							<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" id="deptidSave" name="deptidSave" value="" />
	</form>
</center>
<script type="text/javascript"><!--
//��ʼ��tree----------------------------------------------------------////
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
var contents = ['���ڽ�֧�������޿�','֧����ʽ����'];
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
			tip:true//����������Ƿ����
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
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("gePayment.paymentCode");
	tt.vf.req.add("gePayment.paymentName");

	$("#paymentCode").blur(function(){
		/*
		var paymentCode = $("#paymentCode").val();
		$.ajax({
			url : "${ctx}/productManage/isHavePayment.do",
			data : {
				"paymentCode" : paymentCode
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#paymentCode").val("");
					$("#paymentCodeContent").remove();
					$("#paymentCode").parent().append("<span id='paymentCodeContent' style='width:300px'><span class='talentErrMsg'>֧����ʽ�����Ѿ����ڣ������֧����ʽ���룡</span></span>")
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
	
	//���ύ
	$("#addButtonSubmit").click(function(){
		var allAuthDepts = getAuthDepts();/**����ѡ�е�Ҷ�ӽڵ�*/
		
		if(!tt.validate()){
			return false;
		}else{
			if(allAuthDepts.length==0){
				alert("��ѡ��֧�ֵ���!");
		    	return;
			}
			// ��֤fck
			var oEditor = FCKeditorAPI.GetInstance("proposalNotice");
		    var msg = oEditor.GetXHTML(true);
		    if(msg.length>1000){
		    	alert("��ע��Ϣ���ݳ��Ȳ��ܳ���1000���ַ�!");
		    	return;
		    }
		    
			$("#deptidSave").attr("value",allAuthDepts);
			$("#frmInput").submit();
		}
	});
	
	var addReslut = "${addReslut}";
	if (addReslut == "success") {
		alert("֧����ʽ��ӳɹ���");
	} else if (addReslut == "failure") {
		alert("֧����ʽ���ʧ�ܣ�");
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
    	var contents = ['˵��:��������������ʹ�õ�֧����ʽ<br/>'
    	                + 'ʹ����Ⱥ:֧����ʽ������Ա<br/>'
    	                + '��������:<br/>'
    	                + 'ע������:���еı�ע��֧����ʽ�Ľ��������֣�����ǰ̨չʾ������ʱ��ͷ�������г����ӵģ����ӵ�ַ��˫���š�������'];
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
								tip:true,//����������Ƿ����
								padding :10
							}
						});
        	}
</script>
</body>
</html>
