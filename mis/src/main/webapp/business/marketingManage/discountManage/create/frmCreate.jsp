<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>新建打折活动</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js//imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
</head>
<body>
<input type="hidden" value="" id="dateTempId"/>
<input type="hidden" value="<s:date name="date" format="yyyy-MM-dd"/>" id="minDate" />
<div class="public_fun_title">新建打折活动 </div>
<form id="frmInput" method="post" enctype="multipart/form-data" action="${ctx}/marketing/addGeDiscountManage.do">
<table class="table_style" align="center" width="900px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>活动代码：</td>
		<td class="td_body">
			<input type="text" id="discountid" name="geDiscountManage.discountid" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>活动名称：</td>
		<td class="td_body">
			<input type="text" id="discountname" name="geDiscountManage.discountname" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>活动内容：</td>
		<td class="td_body">
			<textarea rows="8" cols="40" id="discountcontent" name="geDiscountManage.discountcontent" ></textarea>
		</td>
	</tr>
	<tr>
	<tr>
		<td class="td_head" width="120px" nowrap>折扣产品：</td>
		<td class="td_body">
			<input type="hidden" id="eid" name="geDiscountManage.eid" style="width:170px;" maxlength=80 >
			<input type="text"  onclick="productChoice();" id="productName" name="productName" style="width:170px;" maxlength=80 >
			<input type="button" onclick="productChoice();" value="选择产品">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动宣传语：</td>
		<td class="td_body" >
			<textarea rows="8" cols="40" id="activitysiteslogan" name="geDiscountManage.activitysiteslogan" ></textarea>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>打折起期：</td>
		<td class="td_body">
			<input type="text" id="discountstartdate" name="geDiscountManage.discountstartdate" maxlength="30" value="" style="width: 170px;" readonly/>
			<img id="discountstartdateImg" onclick="discountstartdateImgClick()" src="${ctx}/global/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>打折止期：</td>
		<td class="td_body">
			<input type="text" id="discountenddate" name="geDiscountManage.discountenddate" maxlength="30" value="" style="width: 170px;" readonly/>
			<img id="discountenddateImg" onclick="discountenddateClick()" src="${ctx}/global/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle" />
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>折扣：</td>
		<td class="td_body" >
			<input type="text" id="activityName" name="geDiscountManage.discount" style="width:170px;" maxlength=80 >&nbsp;&nbsp;&nbsp;%
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片1：</td>
		<td class="td_body">
			<s:file id="pictureOne" name="pictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureOnePreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片2：</td>
		<td class="td_body">
			<s:file id="pictureTwo" name="pictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureTwoPreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片3：</td>
		<td class="td_body">
			<s:file id="pictureThree" name="pictureThree" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureThreePreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片4：</td>
		<td class="td_body">
			<s:file id="pictureFour" name="pictureFour" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureFourPreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片5：</td>
		<td class="td_body">
			<s:file id="pictureFive" name="pictureFive" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureFivePreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="130px" nowrap>图片6：</td>
		<td class="td_body">
			<s:file id="pictureSix" name="pictureSix" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="pictureSixPreview"  width="61" height="61"/>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
			<tr>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建</td>
				<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>重置</td>
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
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	tt.vf.req.add("geDiscountManage.discountid");
	tt.vf.req.add("productName");
	tt.vf.req.add("geDiscountManage.discount");
	tt.vf.req.add("geDiscountManage.discountstartdate");
	tt.vf.req.add("geDiscountManage.discountenddate");
	var discountReg = "^\\d{1,2}$";
	//var discountReg = "^\\d{1,2}(\.\\d{1,2})?$";
	new tt.RV().set(new RegExp(discountReg), "只能输入整数（0-99）").add("geDiscountManage.discount");
	new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDiscountManage.discountid");
	new tt.LV().set(0,500).add("geDiscountManage.activitysiteslogan");
	new tt.LV().set(0,500).add("geDiscountManage.discountcontent");
	
	$("#discountid").blur(function(){
		var discountid = $("#discountid").val();
		$.ajax({
			url : "${ctx}/marketing/findGeDiscountManageByDiscountId.do",
			data : {
				"discountid" : discountid
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
				alert("系统处理异常,请稍后操作!");
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#discountid").val("");
					$("#discountidContent").html("");
					$("#discountid").after("<span id='discountidContent'><font color='red' style='padding-left:2mm;'>活动代码已经存在，请更改活动代码！</font></span>")
				} else {
					$("#discountidContent").html("");
				}
			}
		});
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
function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview(); 
	};
}
var addReslut = "${addReslut}";

if (addReslut == "success") {
	alert("添加成功！");
} else if (addReslut == "failure") {
	alert("添加失败！");
}

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