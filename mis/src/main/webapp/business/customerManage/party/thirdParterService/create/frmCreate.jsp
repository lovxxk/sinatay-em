<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<%//tao �� %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>

<title>frmCreate.jsp</title>
</head>
<body onload="pageValidate();">
<div class="public_fun_title">
�½���������Ʒ<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<form action="${ctx}/party/addGeThirdParterService.do" id="frmInput" enctype="multipart/form-data" method="post" target="myFrame">
<table align="center" width="550px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>��˾���ƣ�</td>
		<td class="td_body">
		<%//������ѡ���������˾ //ֻ����һ�� %>
		<input name="thirdParterName" value="" ondblclick="alertThirdService();" readonly="readonly" maxlength=30 style="width:175px;">(˫����ѡ��)
		<input type="hidden" name="geThirdParterService.geThirdParterInfo.thirdParterID" style="width:170px;" maxlength=80 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>��Ʒ���ƣ�</td>
		<td class="td_body" >
			<input type="text" name="geThirdParterService.itemName" style="width:175px;" maxlength=180 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>��Ʒ������</td>
		<td class="td_body">
			<input type="text" name="geThirdParterService.totalNumber" style="width:175px;" maxlength=8 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>��ƷͼƬ��</td>
		<td class="td_body">
			<input type="file" name="attrPicture" id="attrPictureUpload" style="width:175px;" maxlength=80 onblur="addPreviewFace(this.id);" >
		</td>
	</tr>
	<tr id="attrPictureUploadTR" style="display: none;">
		<td class="td_head" nowrap valign="top">ͼƬԤ����</td>
		<td class="td_body">
			<img src="${ctx}/global/images/productMiddlePicture.jpg" id="attrPictureUploadPreview"  width="61" height="61" />
		</td>
	</tr>
	<tr height="180px" >
		<td class="td_head" nowrap>��Ʒ��飺</td>
		<td class="td_body">
			<textarea rows="8" cols="40" maxlength="800" name="geThirdParterService.itemContent" onkeyup="textAreaMaxLen(this)" style="overflow: inherit;"></textarea>
		</td>
	</tr>
	<tr><td height="25px"></td></tr>
	<tr>
		<td colspan=2>
			<table align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'"
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doCreate();" style="padding-right: 13px" nowrap>����</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>���</td>
			</tr>
			</table>
		</td>
	</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
var intRange = new tt.RV().set(new RegExp("^[0-9]+$"), "ֻ������������");
intRange.add("geThirdParterService.totalNumber");
function doCreate(){
	<%/*validateIDNO(document.getElementById("identifyNumber"));//У�����֤��
	*/%>
	if(tt.validate()){
		//У��ͼƬ����
		var filepath = document.getElementById("attrPicture").value;
		var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
		extname = extname.toLowerCase();//�����˴�Сд
	    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
	    	alert("ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��");
	     	return false;
	    }
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	/*
	tt.vf.req.add("geBlackList.userName","geBlackList.sex","geBlackList.birthDay","geBlackList.identifyType",
			"geBlackList.identifyNumber","geBlackList.businessArea","geBlackList.reason");
	*/
	tt.vf.req.add("geThirdParterService.itemName","geThirdParterService.itemContent","geThirdParterService.totalNumber","attrPicture","thirdParterName");
	tt.vf.num.add("geThirdParterService.totalNumber");   
	//new tt.RV().set(new RegExp("^(jpg|JPG|gif|GIF|bmp|BMP|png|PNG)$"), "ͼƬ���Ͳ���ȷ").add("geEnterpriseUser.mobilePhone"); 

//pop��ʾ��Ϣ
			var ids = ['des'];
			// <img src="'+contextRootPath+'/global/images/form_success.gif" />
			var contents = ['˵�����½���������Ʒ�ɸ���ͼƬ��<br/>ʹ����Ⱥ�����õ�������Ա��<br/>����������<br/>ע�����'];
				
		    	for ( var i = 0 ; i < 10 ; i++ ){
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
							width:300,
							textAlign: 'left',
							background: '#e0f2ff', 
							tip:true,//����������Ƿ����
							padding :10
						}
					});
		    	}


}

function validateIDNO(obj){
	/**���֤��У��*/
	var IDTypeValue = document.getElementById("identifyType").value;
	if(IDTypeValue != "" && IDTypeValue == "01"){
		if(obj.value != "")tt.vf.idcard.add("geBlackList.identifyNumber");
	}else{
		tt.vf.idcard.rmName("geBlackList.identifyNumber");
	}
}
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("�������800����");
	    }

}
//����һ����Ʒ����ʾ��
function alertThirdService(){
	window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","��ѯ��Ӧ��" ,"top=100, left=100, width=900,height=600,toolbar=no,resizable=yes");
}
function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview();
		document.getElementById(obj+"TR").style.display="";
	};
}
</script>
</html>