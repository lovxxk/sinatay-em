<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js//imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<title>打折活动编辑页面</title>
</head>
<body onload="setPageData();">
<input type="hidden" value="" id="dateTempId"/>
<input type="hidden" value="<s:date name="date" format="yyyy-MM-dd"/>" id="minDate" />
<div class="select_header_top_bg">
		<div class="select_header_top_left1"></div>
		<div class="select_header_top_left2"></div>
		<div class="select_header_top_title">
			<div class="select_header_top_title_content" style="width:150px;">修改打折活动 </div>
		</div>
		<div class="select_header_top_right1"></div>
		<div class="select_header_top_right2"></div>
	</div>
<form action="${ctx}/marketing/updateGeDiscountManage.do" id="frmInput" method="post" enctype="multipart/form-data" target="myFrame">
<table class="table_style" align="center" width="900px" id="productTable">
	<tr>
		<td class="td_head" width="120px" nowrap>活动代码：</td>
		<td class="td_body">
			<input type="text" id="activityId" value="${geDiscountManage.discountid}" name="geDiscountManage.discountid" readOnly style="width:170px;border:0px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>活动名称：</td>
		<td class="td_body">
			<input type="text" id="activityId" value="${geDiscountManage.discountname}" name="geDiscountManage.discountname" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>活动内容：</td>
		<td class="td_body">
			<textarea rows="8" cols="40" id="discountcontent" name="geDiscountManage.discountcontent" >${geDiscountManage.discountcontent }</textarea>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>折扣产品：</td>
		<td class="td_body">
			<input type="hidden" id="eid"  value="${geDiscountManage.eid}" name="geDiscountManage.eid" style="width:170px;" maxlength=80 >
			<input type="text" id="productName" onclick="productChoice();" value="${productName }" style="width:265px;"   readOnly />
			<span style="padding-left:3mm;"><input type="button" onclick="productChoice();" value="选择产品" /></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动宣传语：</td>
		<td class="td_body" >
			<textarea rows="8" cols="40" id="activitysiteslogan" name="geDiscountManage.activitysiteslogan" >${geDiscountManage.activitysiteslogan }</textarea>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>打折起期：</td>
		<td class="td_body">
			<input type="text" id="discountstartdate" name="geDiscountManage.discountstartdate" maxlength="30" value="${geDiscountManage.discountstartdate}" style="width: 170px;" readonly/>
			<img id="discountstartdateImg" onclick="discountstartdateImgClick()" src="${ctx}/global/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>打折止期：</td>
		<td class="td_body">
			<input type="text" id="discountenddate" name="geDiscountManage.discountenddate" maxlength="30" value="${geDiscountManage.discountenddate}" style="width: 170px;" readonly/>
			<img id="discountenddateImg" onclick="discountenddateClick()" src="${ctx}/global/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle" />
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>折扣：</td>
		<td class="td_body" >
			<input type="text" id="discount" value="${geDiscountManage.discount}" name="geDiscountManage.discount" style="width:170px;" maxlength=80 >&nbsp;&nbsp;&nbsp;%
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片1：</td>
		<td class="td_body">
			<s:file id="pictureOne" name="pictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.pictureone != null}">
					<img id="pictureOnePreview" src="${ctx}/${geDiscountManage.pictureone}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureOnePreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片2：</td>
		<td class="td_body">
			<s:file id="pictureTwo" name="pictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.picturetwo != null}">
					<img id="pictureTwoPreview" src="${ctx}/${geDiscountManage.picturetwo}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureTwoPreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片3：</td>
		<td class="td_body">
			<s:file id="pictureThree" name="pictureThree" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.picturefour != null}">
					<img id=pictureThreePreview src="${ctx}/${geDiscountManage.picturefour}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureThreePreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片4：</td>
		<td class="td_body">
			<s:file id="pictureFour" name="pictureFour" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.picturefour != null}">
					<img id=pictureFourPreview src="${ctx}/${geDiscountManage.picturefour}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureFourPreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片5：</td>
		<td class="td_body">
			<s:file id="pictureFive" name="pictureFive" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.picturefive != null}">
					<img id=pictureFivePreview src="${ctx}/${geDiscountManage.picturefive}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureFivePreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片6：</td>
		<td class="td_body">
			<s:file id="pictureSix" name="pictureSix" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<c:choose>
				<c:when test="${geDiscountManage.picturesix != null}">
					<img id="pictureSixPreview" src="${ctx}/${geDiscountManage.picturesix}" border="0" height="61" width="61"/>
				</c:when>
				<c:otherwise>
					<img id="pictureSixPreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr height=25>
		<td colspan="2"></td>
	</tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
				<td id="createButton" align=right class="btnBigOnFocus" nowrap>修改 </td>
				<td width=5>&nbsp;</td>
				<td id="resetButton" class="btnBigOnFocus" align=right onclick="doClear();" nowrap>重置</td>
			</tr>
			</table>
		</td>
	</tr>
</table> 
<div id="message"></div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">

$(document).ready(function(){
	var discountReg = "^\\d{1,2}$";
	//var discountReg = "^\\d{1,2}(\.\\d{1,2})?$";
	new tt.RV().set(new RegExp(discountReg), "只能输入整数（0-99）").add("geDiscountManage.discount");
	new tt.LV().set(0,500).add("geDiscountManage.activitysiteslogan");
	new tt.LV().set(0,500).add("geDiscountManage.discountcontent");
	
	$("textarea").blur(function(){
		validateValue(this);
	});
	
	//表单提交
	$("#createButton").click(function(){
		if(!tt.validate()){
			return false;
		}else{
			$("#frmInput").submit();
		}
	});
});

//打折起期
function discountstartdateImgClick(){
	var discountenddate = $('#discountenddate').val();
	if (discountenddate == "") {
		WdatePicker({el:'discountstartdate',minDate:'%y-%M-%d'});
	} else {
		WdatePicker({el:'discountstartdate',minDate:'%y-%M-%d',maxDate:discountenddate});
	}
}

//打折止期
function discountenddateClick(){
	var discountstartdate = $('#discountstartdate').val();
	if (discountstartdate == "") {
		WdatePicker({el:'discountenddate',minDate:'%y-%M-%d'});
	} else {
		WdatePicker({el:'discountenddate',minDate:discountstartdate});
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function productChoice(){
	window.open("${ctx}/business/marketingManage/discountManage/productChoice/index.jsp" ,"产品选择","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
}

function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview(); 
	};
}

var y = document.getElementsByTagName("input");

for (var i=0; i < y.length; i++){
	if(y[i].type == 'text'){
		y[i].onkeyup = showMyStatus;
		if (y[i].readOnly) {
			y[i].className = "input_readOnly";
		}
	} else if (y[i].type == 'file') {
		y[i].className = "input_readOnly";
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
</html>