<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<title>��ƷĿ¼����</title>
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery.qtip-1.0.0-rc3.js"></script>
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
<form name="frmInput" id="frmInput" action="${ctx}/productDirectory/addGeDirectoryAttributeInfo.do" method="post" enctype="multipart/form-data">
<table id="diretoryAttributeTable" align="center" style="font-size:9pt;width:95%;padding-top:15px;" cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td class="td_head" width="30%" nowrap>����ID��</td>
		<td class="td_body" width="70%"><input type="text" id="attrID" name="geDirectoryAttributeInfo.attrID" maxlength="32" value=""></td>
	</tr>
	<tr>
		<td class="td_head" width="30%" nowrap>�������ƣ�</td>
		<td class="td_body" width="70%"><input type="text" id="attrName" name="geDirectoryAttributeInfo.attrName" maxlength="100" value=""></td>
	</tr>
	<tr>
		<td class="td_head" width="30%" nowrap>��ʾ˳��</td>
		<td class="td_body" width="70%"><input type="text" id="seqIndex" name="geDirectoryAttributeInfo.seqIndex" maxlength="3" value=""></td>
	</tr>
	<tr>
		<td class="td_head" width="30%" nowrap>����������</td>
		<td class="td_body">
			<div style="height:180px;width:180px;">
				<textarea id="attrDescription" name="geDirectoryAttributeInfo.attrDescription"  cols="25" rows="8"></textarea>
			</div>
		</td>
	</tr>
	<tr height="85px">
		<td class="td_head" width="30%" nowrap>����������Ϣ��</td>
		<td class="td_body">
			<div style="height:180px;width:180px;">
				<textarea id="attrNoteInfo" name="geDirectoryAttributeInfo.attrNoteInfo" cols="25" rows="8"></textarea>
			</div>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>
			<a href="${ctx}/global/images/attr_smallPictureOne.jpg" title="ͼƬʹ��λ��" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
			<span style="line-height:23px;">����СͼƬ1��</span>
		</td>
		<td class="td_body">
			<s:file id="attrSmallPictureOne" name="attrSmallPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directorySmallPicture.jpg" id="attrSmallPictureOnePreview" border="0" width="27" height="26"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>
			<a href="${ctx}/global/images/attr_smallPictureTwo.jpg" title="ͼƬʹ��λ��" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
			<span style="line-height:23px;">����СͼƬ2��</span>
			</td>
		<td class="td_body">
			<s:file id="attrSmallPictureTwo" name="attrSmallPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directorySmallPicture.jpg" id="attrSmallPictureTwoPreview" border="0" width="27" height="26"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>
			<a href="${ctx}/global/images/attr_middlePirctureOne.jpg" title="ͼƬʹ��λ��" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
			<span style="line-height:23px;">������ͼƬ1��</span>
		</td>
		<td class="td_body">
			<s:file id="attrMiddlePictureOne" name="attrMiddlePictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directoryMiddlePicture.jpg" id="attrMiddlePictureOnePreview" border="0" width="86" height="70"/>
		</td>
	<tr>
		<td class="td_head"  nowrap>������ͼƬ2��</td>
		<td class="td_body">
			<s:file id="attrMiddlePictureTwo" name="attrMiddlePictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directoryMiddlePicture.jpg" id="attrMiddlePictureTwoPreview" border="0" width="86" height="70"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>
			<a href="${ctx}/global/images/attr_bigPirctureOne.jpg" title="ͼƬʹ��λ��" class="thickbox"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
			<span style="line-height:23px;">���Դ�ͼƬ1��</span>
		</td>
		<td class="td_body">
			<s:file id="attrBigPictureOne" name="attrBigPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directoryBigPicture.jpg" id="attrBigPictureOnePreview" border="0" width="273" height="222"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>���Դ�ͼƬ2��</td>
		<td class="td_body">
			<s:file id="attrBigPictureTwo" name="attrBigPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
			<img src="${ctx}/global/images/directoryBigPicture.jpg" id="attrBigPictureTwoPreview" border="0" width="273" height="222"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="30%" nowrap>������ID��</td>
		<td class="td_body">${param.parentAttrID}<span style="color:red;padding-left:1mm;">*</span></td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="200" align="center">
				<tr>
					<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="padding-top:5px;" nowrap>����</td>
					<td id="updateButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" style="padding-top:5px;" onclick="doClear();" nowrap>����</td>
				</tr>
			</table>
		</td>
	
	</tr>
</table>
<input type="hidden" id="parentAttrID" name="geDirectoryAttributeInfo.geDirectoryAttributeInfo.attrID" maxlength="50" value="${param.parentAttrID}">
</form>
</center>
<script type="text/javascript">
function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview(); 
	};
}
$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	tt.vf.req.add("geDirectoryAttributeInfo.attrID");
	tt.vf.req.add("geDirectoryAttributeInfo.attrName");
	tt.vf.req.add("geDirectoryAttributeInfo.seqIndex");
	new tt.RV().set(new RegExp("^[a-zA-z_0-9]{0,32}$"), "ֻ��������ĸ,����,�»���").add("geDirectoryAttributeInfo.attrID");
	new tt.RV().set(new RegExp("^[0-9]{1,3}$"), "ֻ������������0-999��").add("geDirectoryAttributeInfo.seqIndex");
	new tt.LV().set(0,500).add("geDirectoryAttributeInfo.attrDescription");
	new tt.LV().set(0,500).add("geDirectoryAttributeInfo.attrNoteInfo");
	
	$("#attrID").blur(function(){
		var attrID = $("#attrID").val();
		$.ajax({
			url : "${ctx}/productDirectory/isHaveGeDirectoryAttributeInfoByAttrId.do",
			data : {
				"attrId" : attrID
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
				alert("ɾ������ֵ,���Ժ����!");
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#attrID").val("");
					$("#attrIDContent").remove();
					$("#attrID").parent().append("<span id='eIdContent'><span class='talentErrMsg'>����ID�Ѿ����ڣ����������ID��</span></span>")
				} else {
					$("#attrIDContent").remove();
				}
			}
		});
	});
	
	$("#attrID").focus(function(){
		$("#attrIDContent").remove();
	});
	
	$("textarea").blur(function(){
		validateValue(this);
	});
	
	//���ύ
	$("#addButtonSubmit").click(function(){
		if(!tt.validate()){
			return false;
		}else{
			$("#frmInput").submit();
		}
	});
	
	//pop��ʾ��Ϣ
	var ids = ['attrID','attrSmallPictureOne','attrSmallPictureTwo','attrMiddlePictureOne','attrMiddlePictureTwo','attrBigPictureOne','attrBigPictureTwo'];
	var contents = ['���������Ʒ����Ψһ��ʶ�������ظ�','Ϊ���Ƽ������������ƷչʾͼƬ(61*61������15k)','Ϊ���Ƽ������������ƷչʾͼƬ(61*61������15k)','��Ʒ�б���ƷչʾͼƬ(166*99������40k)','Ԥ�����ݲ�ʹ��','�����̳���ҳ�����ƣ���վ��ҳ�����ƣ�273*222 ������100k��','Ԥ�����ݲ�ʹ��'];
	for ( var i = 0 ; i < 7 ; i++ ){
		$('#' + ids[i]).qtip({ 
			content:contents[i], 
			position: { 
				corner: { 
				tooltip: 'leftMiddle', 
				target: 'right'
				} 
			}, 
			 style: { 
			border: { 
				width: 2,
				radius: 2,
				color: '#00739f'
				},
				width:100,
				padding: 10, 
				textAlign: 'left',
				background: '#e0f2ff', 
				tip:true//����������Ƿ����
				//name: 'green' 
			} 
		}); 
	}
	//pop��ʾ��Ϣ����
	
});

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