<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css"></link>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css"></link>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<title>���������̨����ϵͳ-֧����ʽ��ϸ��Ϣ</title>
<style type="text/css">
 td{
 	vertical-align:top;
 }
 input, select {
 	width:200px;
 }
 #submitTable {
 	margin-left: 200px;
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
				֧����ʽ��ϸ��Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">֧����ʽ���룺 </td>
				<td class="td_body">${gePayment.paymentCode}</td>
			</tr>
			<tr>
				<td class="td_head">֧����ʽ���ƣ�</td>
				<td class="td_body">${gePayment.paymentName}</td>
			</tr>
			<tr>
				<td class="td_head">���أ�</td>
				<td class="td_body">${gePayment.gateId}</td>
			</tr>
			<tr>
				<td class="td_head">logoͼƬ��</td>
				<td class="td_body">
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
			<tr>
				<td colspan="2" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				��ע��
			</div>
			<div style="padding-left:3px;height: 100px;width: 645px;" class="td_body">
				 ${gePayment.remark}
			</div>
		</td>
				
				
			</tr>
			<tr style="padding-top: 12px">
				<td  class="td_head" valign="top" nowrap>
					���۵�����
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
				<td colspan="2" >
					<table id="submitTable">
						<tr>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="doEdit('${gePayment.paymentId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">�༭</td>
							</acc:showView>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="doDel();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">ɾ��</td>
							</acc:showView>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="window.close();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">�ر�</td>
							</acc:showView>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	<form id="frmInput2" action="${ctx}/productManage/delPaymentByPaymentCode.do" target="myFrame" method="post">
		<input type="hidden" id="paymentId" name="paymentId" value="${gePayment.paymentId}">
	</form>
<script type="text/javascript"><!--
//��ʼ��tree----------------------------------------------------------////
tree=new dhtmlXTreeObject("authorityTree","100%","100%",0);
tree.setImagePath("${ctx}/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(false);
tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/productManage/paymentAreaDetailTree.do?paymentId=${gePayment.paymentId}",loadOver);

function myErrorHandler(type, desc, erData){
	   return;
	}
dhtmlxError.catchError("ALL",myErrorHandler);

function loadOver(sIdNow){
	$("#showListLoading").hide();
	var authorityTree = $("#authorityTree").text();
	if(authorityTree==''){
		$("#authorityTree").html("<div style='color:#FF9000;font-weight:bold;text-align:center;'>��֧����ʽ��δ����ʹ�õ���</div>");
	}
}
var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-330;
//�༭
function doEdit(idStr){
	location.href = "${ctx}/productManage/findPaymentDetailByPaymentCode.do?paymentId=" + idStr;		
}
//ɾ��

function doDel(){
	if(confirm("ȷ��ɾ����֧����ʽ��")){
	document.getElementById("frmInput2").submit();
	}
}

--></script>
</body>
</html>
