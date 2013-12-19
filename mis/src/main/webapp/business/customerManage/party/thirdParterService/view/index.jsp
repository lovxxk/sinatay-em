<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>电子商务后台管理系统-查询第三方产品</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/customerManage/party/thirdParterInfo/third.js"></script>
<%//tao 哥 %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
</head>
<body onload="addPreviewFaceInit();">
<input id="businessAreaId" value="<s:property value="geThirdParterInfo.businessArea"/>" type="hidden" />
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				查看第三方产品
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form id="frmInput" action="${ctx}/party/updateGeThirdParterService.do" method="post" target="myFrame" enctype="multipart/form-data">
	<input type="hidden" name="geThirdParterService.itemID" value="<s:property value="geThirdParterService.itemID"/>" />
	<input type="hidden" name="geThirdParterService.pictureUrl" value="<s:property value="geThirdParterService.pictureUrl"/>" />
	<table class="table_style" align="center" width="800px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
	<td>
	<div style="padding-left: 150px">
	<table>
	<tr>
		<td class="td_head" nowrap>公司名称：</td>
		<td class="td_body">
			<s:property value="geThirdParterService.geThirdParterInfo.thirdParterName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品名称：</td>
		<td class="td_body">
			<s:property value="geThirdParterService.itemName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品数量：</td>
		<td class="td_body">
			<s:property value="geThirdParterService.totalNumber"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品代码：</td>
		<td class="td_body">
			<s:property value="geThirdParterService.itemID"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>商品图片：</td>
		<td class="td_body">
			<input type="hidden" id="hiddenImg" value="${ctx}/<s:property value="geThirdParterService.pictureUrl"/>" />
			<img  id="attrPictureUploadPreview" style="display: none;"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>库存剩余：</td>
		<td class="td_body">
			<s:property value="geThirdParterService.surplus"/>
		</td>
	</tr>
	
	<s:iterator value="geThirdParterSerialNumberList" var="geThirdParterSerialNumber">
	<tr>
		<td class="td_head" nowrap><s:property value="#geThirdParterSerialNumber.proposalAreaName"/>：</td>
		<td class="td_body">
			送出&nbsp;<s:property value="#geThirdParterSerialNumber.count"/>&nbsp;个商品
		</td>
	</tr>
	</s:iterator>
	<tr>
		<td class="td_head" nowrap>商品简介：</td>
		<td class="td_body">
		 <textarea rows="8" cols="40" disabled><s:property value="geThirdParterService.itemContent"/></textarea>
		</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
	
	
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<div style="padding-top:10px;padding-bottom:20px;padding-right: 120px">
		<form id="operatorForm" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0"  id="operatorTable" align="center">
				<tr>
					<td onclick="javascript:maximizeGrid(this);">
						&nbsp;
					</td>					
					<acc:showView source="ROLE_B_PDIR_U">
					<td onclick="doEdit('<s:property value="geThirdParterService.itemID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" >编辑</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doDelete('<s:property value="geThirdParterService.itemID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" >删除</td>
					</acc:showView>
					<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">关闭</td>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;"></iframe>
	</div>
	<script type="text/javascript">
		//编辑
		function doEdit(idStr){
			location.href = "${ctx}/party/prepareUpdateGeThirdParterService.do?geThirdParterService.itemID=" + idStr;
			
		}
		
		
		//删除
		function doDelete(idStr){
			if(confirm('您确认删除该产品吗？')){
				location.href = "${ctx}/party/deleteGeThirdPartterService.do?geThirdParterService.itemID=" + idStr;
			}
		}
		//关闭
		function pageclose(){
			window.parent.opener.parent.frames[0].doSearch();
			window.close();
			
		}
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
}
function addPreviewFaceInit(){
	if($("#hiddenImg").val()!=""){
		ImagePreview.MODE = "simple";//让走simplle模式
		var facePic = new ImagePreview( $$("hiddenImg"), $$("attrPictureUploadPreview"), {maxWidth: 230, maxHeight: 160});
		facePic.preview();
		document.getElementById("attrPictureUploadPreview").style.display="";	
	}
}
</script>
</body>
</html>
