<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>产品目录属性</title>
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<script src="${ctx}/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js//imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<style type="text/css">
 td{
 	vertical-align:top;
 }
</style>
</head>
<body>
<center>
<form name="frmInput" id="frmInput" action="${ctx}/productDirectory/updateGeDirectoryAttributeInfo.do" method="post" enctype="multipart/form-data">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td class="td_head" width="30%" nowrap>属性ID：</td>
		<td class="td_body" width="70%">${geDirectoryAttributeInfo.attrID}<c:if test="${param.operator=='update'}"></c:if></td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>属性名称：</td>
		<td class="td_body" ><input type="text" id="attrName" name="geDirectoryAttributeInfo.attrName" maxlength="100" ${param.operator=='display'?"readonly":""} value="${geDirectoryAttributeInfo.attrName}"></td>
	</tr>
	<tr>
		<td class="td_head" nowrap>显示顺序：</td>
		<td class="td_body" ><input type="text" id="seqIndex" name="geDirectoryAttributeInfo.seqIndex" maxlength="3" ${param.operator=='display'?"readonly":""} value="${geDirectoryAttributeInfo.seqIndex}"></td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>属性描述：</td>
		<td class="td_body">
		<div style="height:180px;width:180px;">
			<textarea  id="attrDescription" ${param.operator== 'display'?"readonly":""}  name="geDirectoryAttributeInfo.attrDescription" cols="25" rows="8">${geDirectoryAttributeInfo.attrDescription}</textarea>
		</div>
		</td>
	</tr>
	<tr height="85px">
		<td class="td_head"  nowrap>属性宣传信息：</td>
		<td class="td_body">
			<div style="height:180px;width:180px;">
				<textarea id="attrNoteInfo" name="geDirectoryAttributeInfo.attrNoteInfo" cols="25" rows="8" ${param.operator=='display'?"readonly":""}>${geDirectoryAttributeInfo.attrNoteInfo}</textarea>
			</div>
		</td>
	</tr>
	<c:choose>
		<c:when test="${param.operator=='update'}">
			<tr>
				<td class="td_head"  nowrap>
					<a href="${ctx}/global/images/attr_smallPictureOne.jpg" title="图片使用位置" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
					<span style="line-height:23px;">属性小图片1：</span>
				</td>
				<td class="td_body">
					<s:file id="attrSmallPictureOne" name="attrSmallPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrSmallPictureOne != null}">
							<img id="attrSmallPictureOnePreview" src="${ctx}/${geDirectoryAttributeInfo.attrSmallPictureOne}" border="0" width="27" height="26"/>
						</c:when>
						<c:otherwise>
							<img id="attrSmallPictureOnePreview" src="${ctx}/global/images/directorySmallPicture.jpg" border="0" width="27" height="26"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>
					<a href="${ctx}/global/images/attr_smallPictureTwo.jpg" title="图片使用位置" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
					<span style="line-height:23px;">属性小图片2：</span>
				</td>
				<td class="td_body">
					<s:file id="attrSmallPictureTwo" name="attrSmallPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrSmallPictureTwo != null}">
							<img id="attrSmallPictureTwoPreview" src="${ctx}/${geDirectoryAttributeInfo.attrSmallPictureTwo}" border="0"  width="27" height="26"/>
						</c:when>
						<c:otherwise>
							<img id="attrSmallPictureTwoPreview" src="${ctx}/global/images/directorySmallPicture.jpg" border="0" width="27" height="26"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>
					<a href="${ctx}/global/images/attr_middlePictureOne.jpg" title="图片使用位置" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
					<span style="line-height:23px;">属性中图片1：</span>
				</td>
				<td class="td_body">
					<s:file id="attrMiddlePictureOne" name="attrMiddlePictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrMiddlePictureOne != null}">
							<img id="attrMiddlePictureOnePreview" src="${ctx}/${geDirectoryAttributeInfo.attrMiddlePictureOne}" border="0" width="86" height="70"/>
						</c:when>
						<c:otherwise>
							<img id="attrMiddlePictureOnePreview" src="${ctx}/global/images/directoryMiddlePicture.jpg" border="0" width="86" height="70"/>
						</c:otherwise>
					</c:choose>
					
				</td>
			<tr>
				<td class="td_head"  nowrap>属性中图片2：</td>
				<td class="td_body">
					<s:file id="attrMiddlePictureTwo" name="attrMiddlePictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrMiddlePictureTwo != null}">
							<img id="attrMiddlePictureTwoPreview" src="${ctx}/${geDirectoryAttributeInfo.attrMiddlePictureTwo}" border="0" width="86" height="70"/>
						</c:when>
						<c:otherwise>
							<img id="attrMiddlePictureTwoPreview" src="${ctx}/global/images/directoryMiddlePicture.jpg" border="0" width="86" height="70"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>
					<a href="${ctx}/global/images/attr_bigPirctureOne.jpg" title="图片使用位置" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
					<span style="line-height:23px;">属性大图片1：</span>
				</td>
				<td class="td_body">
					<s:file id="attrBigPictureOne" name="attrBigPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrBigPictureOne != null}">
							<img id="attrBigPictureOnePreview" src="${ctx}/${geDirectoryAttributeInfo.attrBigPictureOne}" id="attrBigPictureOnePreview" border="0" width="273" height="222"/>
						</c:when>
						<c:otherwise>
							<img id="attrBigPictureOnePreview" src="${ctx}/global/images/directoryBigPicture.jpg" border="0" width="273" height="222"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>属性大图片2：</td>
				<td class="td_body">
					<s:file id="attrBigPictureTwo" name="attrBigPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
					<c:choose>
						<c:when test="${geDirectoryAttributeInfo.attrBigPictureTwo != null}">
							<img id="attrBigPictureTwoPreview" src="${ctx}/${geDirectoryAttributeInfo.attrBigPictureTwo}" border="0" width="273" height="222"/>
						</c:when>
						<c:otherwise>
							<img id="attrBigPictureTwoPreview" src="${ctx}/global/images/directoryBigPicture.jpg" border="0" width="273" height="222"/>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="td_head"  nowrap>属性小图片1：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrSmallPictureOne != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrSmallPictureOne}" border="0"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>属性小图片2：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrSmallPictureTwo != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrSmallPictureTwo}" border="0"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>属性中图片1：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrMiddlePictureOne != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrMiddlePictureOne}" border="0"/>
					</c:if>
				</td>
			<tr>
				<td class="td_head"  nowrap>属性中图片2：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrMiddlePictureTwo != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrMiddlePictureTwo}" border="0"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>属性大图片1：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrBigPictureOne != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrBigPictureOne}" border="0"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_head"  nowrap>属性大图片2：</td>
				<td class="td_body">
					<c:if test="${geDirectoryAttributeInfo.attrBigPictureTwo != null }">
						<img src="${ctx}/${geDirectoryAttributeInfo.attrBigPictureTwo}" border="0"/>
					</c:if>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td class="td_head"  nowrap>父属性ID：</td>
		<td class="td_body">${geDirectoryAttributeInfo.geDirectoryAttributeInfo.attrID}<c:if test="${param.operator=='update'}"><span style="color:red;padding-left:1mm;">*</span></c:if></td>
	</tr>
	<c:if test="${param.operator=='update'}">
		<tr height=25>
			<td></td>
		</tr>
		<tr>
			<td colspan=2>
			<table width=200 align="center">
				<tr>
					<td id="updateButton" align=right class="btnBigOnFocus" nowrap>修改</td>
					<td width=5>&nbsp;</td>
					<td id="clearButton" align=right class="btnBigOnFocus" onclick="doClear();" nowrap>重置</td>
				</tr>
			</table>
			</td>
		</tr>
		<input type="hidden" id="attrID" name="geDirectoryAttributeInfo.attrID" maxlength="50" value="${geDirectoryAttributeInfo.attrID}">
		<input type="hidden" id="parentAttrID" name="geDirectoryAttributeInfo.geDirectoryAttributeInfo.attrID" maxlength="50" value="${geDirectoryAttributeInfo.geDirectoryAttributeInfo.attrID}">
	</c:if>
</table>
</form>
</center>
</body>
<c:if test="${param.operator=='update'}">
<script type="text/javascript">
	function addPreviewFace(obj){
		var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
		facePic.file.onchange = function(){
			facePic.preview(); 
		};
	}			
	$(document).ready(function(){
		//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
		tt.vf.req.add("geDirectoryAttributeInfo.attrName");
		tt.vf.req.add("geDirectoryAttributeInfo.seqIndex");
		new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "只能输入整数（0-999）").add("geDirectoryAttributeInfo.seqIndex");
		new tt.LV().set(0,500).add("geDirectoryAttributeInfo.attrDescription");
		new tt.LV().set(0,500).add("geDirectoryAttributeInfo.attrNoteInfo");
		
		$("textarea").blur(function(){
			validateValue(this);
		});
		
		//表单提交
		$("#updateButton").click(function(){
			if(!tt.validate()){
				return false;
			}else{
				$("#frmInput").submit();
			}
		});
		
		//pop提示信息
		var ids = ['attrSmallPictureOne','attrSmallPictureTwo','attrMiddlePictureOne','attrMiddlePictureTwo','attrBigPictureOne','attrBigPictureTwo'];
		var contents = ['为你推荐，近期浏览产品展示图片(61*61不超过15k)','为你推荐，近期浏览产品展示图片(61*61不超过15k)','产品列表，产品展示图片(166*99不超过40k)','预留，暂不使用','网上商城首页量身定制，网站首页量身定制（273*222 不超过100k）','预留，暂不使用'];
    	for ( var i = 0 ; i < 9 ; i++ ){
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
					width:120,
					padding: 10, 
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
    	//pop提示信息结束
		
	});
	function doClear() {
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
</c:if>
</html>