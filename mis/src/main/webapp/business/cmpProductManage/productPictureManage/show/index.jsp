<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<style type="text/css">
	textarea{background-color:#ffffff;}
	.td_head{
		text-align:left
	}
</style>
<title>电子商务后台管理系统-产品图片详细信息</title>
<script type="text/javascript">
</script>
</head>
<body onload="addPreviewFaceInit();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			产品图片详细信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width=300 id="productTable">
	
	<tr>
	<td colspan="2">
		<div style="padding-left:10px; padding-top:15px;">
		<table align="left">
			<tr>
				<td class="td_head"  nowrap width="80">名&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
				<td class="td_body" nowrap width="220">${geProductPictureDetail.picturename}</td>
			</tr>
			<tr>
				<td colspan="2" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				描述：
			</div>
			<div style="padding-left:3px;height: 100px;width: 300px;">
				<textarea class="textarea_disabled" disabled>${geProductPictureDetail.picturedesc}</textarea>
			</div>
		</td>
			</tr>
			<tr>
				<td class="td_head"  width="80"   nowrap>是否启用：</td>
				<td class="td_body"  width="220" nowrap>${geProductPictureDetail.flag eq "1" ? "是":"否"}</td>
			</tr>
			<tr>
				<td class="td_head"  width="80"   nowrap>创建时间：</td>
				<td class="td_body"  width="220" nowrap><fmt:formatDate pattern="yyyy-MM-dd" value="${geProductPictureDetail.createTime}"/></td>
			</tr>
		</table>
		</div>
	</td>
	</tr>
	<!-- 以下是图片显示部分 -->
	<tr>
		<td class="td_body">
		<s:iterator value="geProductPictureDetail.geProductPictures" var="geProductPicture" status="i">
			<table>
				<tr id="uploadPicture${i.index+1}TR" style="display: none;">
					<td class="td_head" nowrap>上传图片${i.index+1}：</td>
					<td class="td_body">
					<input type="hidden" name="hiddenImage"	id="hiddenImage${i.index+1}" value="<s:if test="#geProductPicture.nooryes=='yes'">${ctx}/<s:property value="#geProductPicture.pictureurl"/></s:if>">
					<img src="${ctx}/<s:property value="#geProductPicture.pictureurl"/>"	width="200" height="100" id="uploadPicture${i.index+1}Preview">
					</td>
				</tr>
			</table>
		</s:iterator>
		</td>
	</tr>

	<tr height="10px"><td colspan="4">&nbsp;</td></tr>
	<tr>
		<td colspan=4>
			<table width=300 align="center">
			<tr>
				<acc:showView source="ROLE_BU_PPM_M_E">
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
					<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_BU_PPM_M_D">
					<td onclick="doDelete();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'">删除</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>关闭</td>	
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	<form id="frmInput2" action="${ctx }/business/cmpProductManage/productPictureManage/delete.do" target="myFrame" method="post">
		<input type="hidden" id="idStr" name="idStr" value="${geProductPictureDetail.detailid}">
	</form>
</div>
<script type="text/javascript">
function addPreviewFaceInit(){
	var hiddenImages = document.getElementsByName("hiddenImage");
	for(var i=0;i<hiddenImages.length;i++){
		if(hiddenImages[i].value!=""){
			//ImagePreview.MODE = "simple";//让走simplle模式
			//var facePic = new ImagePreview( $$(hiddenImages[i].id), $$("uploadPicture"+(i+1)+"Preview"), {maxWidth: 230, maxHeight: 160});
			document.getElementById("uploadPicture"+(i+1)+"TR").style.display="";
			//facePic.preview();
			
		}
	}
}
function doEdit(){
	window.location = "${ctx}/business/cmpProductManage/productPictureManage/queryGeProductPictureDetailForUpdate.do?geProductPictureDetail.detailid=${geProductPictureDetail.detailid}";
}

function doClose(){
	window.close();
}

function doDelete(){
	var confirm1=confirm("您确定要删除吗？");
	if(confirm1==true){
	document.getElementById("frmInput2").submit();
	}else{
		return false;
	}
}
</script>
</body>
</html>
