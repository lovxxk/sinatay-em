<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp" %>
<%@ taglib prefix="FCK" uri="http://java.fckeditor.net" %>
<html>
<head>
	<title>告知信息</title>
	<meta http-equiv="content-type" content="text/html; charset=GBK">
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" /> 
	<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js"></script>
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	
</head>
<body>
	<div>
		<form id="frmInput" action="${ctx}/productManage/saveOrUpdateConfigInformBook.do" method="post" target="informBookFrame">
		<input type="hidden" name="ECPRODUCTCODE" value="">
		<div style="text-align:center;width:99.6%;border:1px solid #a1a29c;margin-top:20px;">
			<div class="frmCreate_kuang_header" style="text-align:center;" >告知书配置项</div>
			<div style="height:20px;text-align:left;padding-top:5px;padding-left:10%;"><font size="2" ><b style="color:red;">提示：</b>告知书项配置中的文本框不能直接编辑，请<b style="color:red;">双击文本框</b>弹出编辑器后再进行编辑</font></div>
			<div id="mode" style="border-bottom:1px solid #a1a29c;display:none">
				<input type=checkbox>
				<span>
					<textarea rows="5" cols="25" name="informBook.informContent" readonly style="width:65%;" ondblclick="showEdit(this);"></textarea>
					指定值
					<select name="informBook.informOption">
						<option value="">请选择</option>
						<option value="Y">&nbsp;&nbsp;是</option>
						<option value="N">&nbsp;&nbsp;否</option>
					</select>
					指定顺序
					<select name="informBook.informOrder">
						<option value="">请选择</option>
						<option value="01" >01</option>
						<option value="02" >02</option>
						<option value="03" >03</option>
						<option value="04" >04</option>
						<option value="05" >05</option>
						<option value="06" >06</option>
					</select>
				</span>
			</div>
			<div id="noticeInfo">
				<c:if test="${empty informBookList}">
					<div style="border-bottom:1px solid #a1a29c;">
						<input type=checkbox>
						<span>
							<textarea rows="5" cols="25" name="informBook.informContent" style="width:65%;" readonly ondblclick="showEdit(this,'${inforBook.serialNo}');"></textarea>
							指定值
							<select name="informBook.informOption">
								<option value="">请选择</option>
								<option value="Y" >&nbsp;&nbsp;是</option>
								<option value="N" >&nbsp;&nbsp;否</option>
							</select>
							指定顺序
							<select name="informBook.informOrder">
								<option value="">请选择</option>
								<option value="01" >01</option>
								<option value="02" >02</option>
								<option value="03" >03</option>
								<option value="04" >04</option>
								<option value="05" >05</option>
								<option value="06" >06</option>
							</select>
						</span>
					</div>
				</c:if>
				<c:if test="${not empty informBookList}">
					<c:forEach items="${informBookList}" var="inforBook" varStatus="status">
						
						<div style="border-bottom:1px solid #a1a29c;">
							<input type=checkbox>
							<span>
								<textarea rows="5" cols="25" name="informBook.informContent" style="width:65%;" readonly ondblclick="showEdit(this,'${inforBook.serialNo}');">${inforBook.informContent}</textarea>
								指定值
								<select name="informBook.informOption">
									<option value="">请选择</option>
									<option value="Y" <c:if test="${inforBook.informOption eq 'Y'}">selected</c:if>>&nbsp;&nbsp;是</option>
									<option value="N" <c:if test="${inforBook.informOption eq 'N'}">selected</c:if>>&nbsp;&nbsp;否</option>
								</select>
								指定顺序
								<select name="informBook.informOrder">
									<option value="">请选择</option>
									<option value="01" <c:if test="${inforBook.informOrder eq '01'}">selected</c:if>>&nbsp;&nbsp;01</option>
									<option value="02" <c:if test="${inforBook.informOrder eq '02'}">selected</c:if>>&nbsp;&nbsp;02</option>
									<option value="03" <c:if test="${inforBook.informOrder eq '03'}">selected</c:if>>&nbsp;&nbsp;03</option>
									<option value="04" <c:if test="${inforBook.informOrder eq '04'}">selected</c:if>>&nbsp;&nbsp;04</option>
									<option value="05" <c:if test="${inforBook.informOrder eq '05'}">selected</c:if>>&nbsp;&nbsp;05</option>
									<option value="06" <c:if test="${inforBook.informOrder eq '06'}">selected</c:if>>&nbsp;&nbsp;06</option>
								</select>
							</span>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<table style="margin-bottom:10px;padding-top:10px;" width="100%">
				<tr>
					<td align="right">
						<table>
								<tr>
									<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" onclick="addRow();" nowrap >添加</td>
									<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
											onmouseout="this.className='btnBigOnBlur'" onclick="deleteRow();" nowrap >删除</td>
								</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>  
		<table align="center" style="padding-top:20px;padding-bottom:30px;text-align:center;">
			<tr>
				<!-- 
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doView();" nowrap >预览</td>
				-->	
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doNext();" nowrap >保存</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doReflush();" nowrap >重置</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.close();" nowrap >关闭</td>
			</tr>
		</table>
		<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode'/>">
		</form>
		<div id="edit"></div>
	</div>
	<form id="showText" action="${ctx}/productManage/toShowContent.do" method="post"  target="myFrame">
		<input type="hidden" id="noticeValue" name="noticeValue" value="">
		<input type="hidden" id="editorHeight" name="editorHeight" value="">
		<input type="hidden" id="editorWidth" name="editorWidth" value="">
	</form>
	<iframe id="informBookFrame" name="informBookFrame" style="display:none"></iframe>
</body>
<script type="text/javascript">
	var selectedObj = "";
	var frmInputWidth = $("#productIframe", top.document.body).width();
	var frmInputHeight = $("#productIframe", top.document.body).height();
	function showEdit(obj,id){
		selectedObj = obj;
		var top = 55;
		if(typeof document.body != 'undefined')
			top = document.body.scrollTop;
		obj.blur();
		document.getElementById("noticeInfo").disabled = true;
		document.getElementById("edit").innerHTML = "";
		document.getElementById("edit").innerHTML = getEditHtml();
		document.getElementById("fcDiv").style.top = top;
		var editorWidth = frmInputWidth - 12;
		var editorHeight = frmInputHeight/2 - 30;
		$("#noticeValue").val(obj.value);
		$("#editorHeight").val(editorHeight);
		$("#editorWidth").val(editorWidth);
		$("#showText").submit();
		document.getElementById("fcDiv").style.display = "";
		$("#fcDiv").css("width", frmInputWidth - 1);
		$("#fcDiv").css("left", 0);
		$("#myFrame").css("width", frmInputWidth - 1);
	}
	
	function getEditHtml(){
		var html = "<div id='fcDiv' style='position:absolute;z-index:9999;top:55px;display:none;border-color:black;border-width:1px;border-style:solid;'>"
				+"<iframe id='myFrame' name='myFrame' style='width:1145px;height:380px;align:center;'></iframe>"
				+"</div>"
		return html;
	}
	
	function addRow(){
		var content = document.getElementById("noticeInfo");
		var mode = document.getElementById("mode");
		var newTR = mode.cloneNode(true);
		newTR.style.display="";
	  	content.appendChild(newTR);
	}
	
	function deleteRow(){
		var content = document.getElementById("noticeInfo");
		var mngInput = content.getElementsByTagName("INPUT");
		var length = mngInput.length;
		for(var n = 0; n < length; n++){
			if(mngInput[n] == null)continue;
			if(mngInput[n].type=="checkbox"&&mngInput[n].checked==true){
				var emement = mngInput[n].parentNode;
				emement.parentNode.removeChild(emement);
				n--;
			}
		}
	}
	
	function doView(){
		window.open("show.jsp");
	}
	
	function doNext(){
		if(!checkRequired()){
			return;
		}
		var textareaCount = document.getElementsByTagName("textarea").length;
		if(textareaCount < 2){
			alert("你没有配置任何告知信息，无需进行保存！");
			return;
		}
		document.getElementById("frmInput").submit();
	}
	
	function doReflush(){
		window.location.reload();
	}
	
	function checkRequired() {
		var noticeInfoDiv = document.getElementById("noticeInfo");
		var textareaTag = noticeInfoDiv.getElementsByTagName("textarea");
		var selectTag = noticeInfoDiv.getElementsByTagName("select");
		for(var i = 0; i < textareaTag.length; i++) {
			if(textareaTag[i].value == ""){
				alert("请输入第"+(i+1)+"条告知书内容");
				textareaTag[i].focus();
				return false;
			}
		}
		for(var i = 0; i < selectTag.length; i++) {
			if(selectTag[i].value == "") {
				if(i % 2 == 0){
					alert("请输入第"+(i / 2 + 1)+"条告知书对应的值");
					selectTag[i].focus();
					return false;
				} else {
					alert("请输入第"+((i + 1) / 2)+"条告知书顺序");
					selectTag[i].focus();
					return false;
				}
			}
		}
		for(var i = 0; i < selectTag.length; i++) {
			if(selectTag[i].value != "") {
				if(i % 2 == 0){
					continue;
				} else {
					for(var k = i + 2; k < selectTag.length; k++) {
						if(selectTag[i].value == selectTag[k].value) {
							alert("请输入第" + ((i + 1) / 2) + "、"  + ((k + 1) / 2)  + "条顺序重复");
							selectTag[i].focus();
							return false;
						}
						k++;
					}
				}
			}
		}
		return true;
	}
	$(document).ready(function(){
		$("input[type='checkbox']").addClass("checkbox_border");
	});
	
</script>
</html>
