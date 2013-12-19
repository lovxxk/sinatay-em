<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/checkIdCard.js"></script>
<title>frmCreate.jsp</title>

<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<style type="text/css">
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1100px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:auto; left:50%; top:50%; margin-left:-400px; height:auto; z-index:100;background-color:#fff; border:1px #8FA4F5 solid; padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#27a26b; position:relative; padding-left:10px; line-height:25px;text-align: left;width: 740px}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>
</head>
<body onload="pageValidate();">
	<div class="public_fun_title">�½���ƷͼƬ</div>
<form action="${ctx}/business/cmpProductManage/productPictureManage/addGeProductPicture.do" id="frmInput" method="post" enctype="multipart/form-data" target="myFrame">
<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">��&nbsp;��</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>
<table align="center" width="720px"id="productTable">
	<tr>
		<td>
			<div style="padding-left:80px">
			<table>
				<tr>
				<td class="td_head" style="text-align: right;" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�</td>
				<td class="td_body">
					<input type="text" id="picturename" name="geProductPictureDetail.picturename" style="width:240px;" maxlength=80 >
				</td>
				</tr>
				<tr>
					<td class="td_head" style="text-align: right;" nowrap valign="top">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
					<td class="td_body" id="remark">
					
						<textarea rows="8" cols="40" id="picturedesc" name="geProductPictureDetail.picturedesc"></textarea>
					</td>
				</tr>
				<tr>
				<td class="td_head" style="text-align: right;" nowrap>�Ƿ����ã�</td>
				<td class="td_body">
					<input type="radio" class="no_border_input" name="geProductPictureDetail.flag" value="1" checked>����
					<input type="radio" class="no_border_input" name="geProductPictureDetail.flag" value="0">������
				</td>
			</tr>
			</table>
			</div>
		</td>
	</tr>

	<!-- picture start -->
	<tr>
		<td colspan="3" style="padding-left:76px">
			<div class="frmCreate_kuang" style="margin-left:10px;width:500px;margin-top:10px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header">��Ӳ�ƷͼƬ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style="padding-left:3px; padding-top:15px;">
	<table>
	<tr id="productType"><td></td></tr>
	<tr>
		<td class="td_head" nowrap valign="top">
			</td>
		<td class="td_head" valign="top"><a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('showImg1');" id="showImg1"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ1��
			<input type="file" name="uploadPicture" id="uploadPicture1"  onblur="addPreviewFace(this.id);"  style="width:235px;" maxlength=80 ><br/>
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture1TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>ͼƬ1Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture1Preview"  width="200" height="100"/></div>
		</td> 
	</tr>		
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('showImg2');" id="showImg2"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ2��
			<input type="file" name="uploadPicture" id="uploadPicture2" onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80>
		</td> 
	</tr>
	
	<tr style="display: none;" id="uploadPicture2TR">
		<td class="td_head" nowrap valign="top">
		<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>ͼƬ2Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture2Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('showImg3');" id="showImg3"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ3��
			<input type="file" name="uploadPicture" id="uploadPicture3"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture3TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>ͼƬ3Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture3Preview"  width="200" height="100"/></div>
		</td> 
	</tr>
	<!-- ��������ʱ�����õ�4��5  -->

	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" >
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>�ϴ�ͼƬ4��
			<input type="file" name="uploadPicture" id="uploadPicture4" disabled="disabled" onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture4TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>ͼƬ4Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture4Preview"  width="200" height="100" /></div>
		</td> 
	</tr>

	<tr>
		<td class="td_head" nowrap valign="top" >
		</td>
		<td class="td_head" >
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>�ϴ�ͼƬ5��
			<input type="file" name="uploadPicture" id="uploadPicture5" disabled="disabled"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 ><br/>
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture5TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>ͼƬ5Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture5Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	</table>
	</div>
	</div>
		</td>
	</tr>
	<!-- picture end -->
	<tr>
		<td colspan=2>
			<table width=400 align="center">
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>���� </td>
					<td width=5>&nbsp;</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
					<td width=200>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table> 
<input type="hidden" name="uploadPictureSerialNos" />
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
//�ϴ�ͼƬ��ʾ�Ķ���
$(document).ready(function(){
	//pop��ʾ��Ϣ
	var ids = ['picturedesc','uploadPicture1','uploadPicture2','uploadPicture3','uploadPicture4','uploadPicture5'];
	var contents = ['��ƷͼƬ������','�ϴ�ͼƬ1ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ����600px��50px��','�ϴ�ͼƬ2ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ����244px  ����220px��','�ϴ�ͼƬ3ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ������250px ����30px��','�ϴ�ͼƬ4ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��','�ϴ�ͼƬ5ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��'];
		
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

function addPreviewFace(obj){
	//alert("test@");
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview();
		
		var filepath=document.getElementById(obj).value;
		var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
		extname = extname.toLowerCase();//�����˴�Сд
	    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
	    	document.getElementById(obj+"TR").style.display="none";
	    }else{
	    	document.getElementById(obj+"TR").style.display="";
	    }
	};
}

function doCreate(){
	//alert($("input[name='uploadPicture']").val());
	if(!tt.validate()){
		return false;
	}
	var val1 = $("#uploadPicture1").val();
	var val2 = $("#uploadPicture2").val();
	var val3 = $("#uploadPicture3").val();
	if(val1=="" && val2 == "" && val3 == ""){
		alert("�������ϴ�һ��ͼƬ��");
		return false;
	}
	
	//�ж�ͼƬ��ʽ
	var uploadPictures1=	$("input[name='uploadPicture']");
	for(var i=0;i<uploadPictures1.length;i++){
		var filepath1 = uploadPictures1[i].value;
		if(filepath1!=""){
			var extname = filepath1.substring(filepath1.lastIndexOf(".")+1,filepath1.length);
			extname = extname.toLowerCase();//�����˴�Сд
		    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
		    	alert("�ϴ�ͼƬ"+(i+1)+" ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��");
		     	return false;
		    }
		}// end if
	}
	
	//if($("input[name='uploadPicture']").val()==""){
		//alert("��ѡ���ϴ�ͼƬ��");
		//return false;
	//}

	//����ͼƬ���ϴ�
	var uploadPictures = document.getElementsByName("uploadPicture");
	var uploadSerialNos= "";
	for(var i=0;i<uploadPictures.length;i++){
		if(uploadPictures[i].value!=""){
			uploadSerialNos = uploadSerialNos+(i+1)+",";
		}
	}
	uploadSerialNos = uploadSerialNos.substring(0,(uploadSerialNos.length-1));
	document.getElementsByName("uploadPictureSerialNos")[0].value=uploadSerialNos;
		document.getElementById("frmInput").submit();
}

function doClear(){
	document.getElementById("frmInput").reset();
	//���е�ͼƬԤ�������
	document.getElementById("uploadPicture1TR").style.display="none";
	document.getElementById("uploadPicture2TR").style.display="none";
	document.getElementById("uploadPicture3TR").style.display="none";
	document.getElementById("uploadPicture4TR").style.display="none";
	document.getElementById("uploadPicture5TR").style.display="none";
}

function pageValidate(){
	tt.vf.req.add("geProductPictureDetail.picturename","geProductPictureDetail.picturedesc");
	new tt.LV().set(0,30).add("geProductPictureDetail.picturename");
	new tt.LV().set(0,200).add("geProductPictureDetail.picturedesc");
}
//ͼƬʹ��λ�ò���
function btnShowone(showImgs)
		{
		   $("#"+showImgs).click(function()
		   {
		      $("#BgDiv").css({ display:"block",height:"1080px"});
		      var yscroll=document.documentElement.scrollTop;
		      $("#DialogDiv").css("top","100px");
		      $("#DialogDiv").css("display","block");
		      $("#showImg").attr("src","${ctx}/global/images/"+showImgs+".jpg");
		      if(showImgs=="ProcessRole"){
		    	  $("#spanProcessRole").remove();
		    	  $("#h2btn").append("<span id=\"spanProcessRole\">����˵��</span>");
		      }else{
		    	  $("#spanShowImgs").remove();
		    	  $("#h2btn").append("<span id=\"spanShowImgs\">ͼƬʹ��λ�� </span>");
		      }
		      document.documentElement.scrollTop=0;
		   });
		   $("#btnClose").click(function()
		   {
			  $("#spanProcessRole").remove();
			  $("#spanShowImgs").remove();
		      $("#BgDiv").css("display","none");
		      $("#DialogDiv").css("display","none");
		   });
		}
function btnClose(){
	 $("#spanProcessRole").remove();
	  $("#spanShowImgs").remove();
     $("#BgDiv").css("display","none");
     $("#DialogDiv").css("display","none");
}
//�ж�ͼƬ������
function judgePicture(){
var uploadPictures=	$("input[name='uploadPicture']");
	if(uploadPictures.length==1){//ֻ��һ�������ֶε�,��û��Ҫ�ϴ���ͼƬ
		return true;
	}else{//��Ҫ�ϴ���ͼƬ
		
		for(var i=0;i<uploadPictures.length;i++){
			var filepath = uploadPictures[i].value;
			if(filepath!=""){
				var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
				extname = extname.toLowerCase();//�����˴�Сд
			    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
			    	alert("�ϴ�ͼƬ"+(i+1)+" ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��");
			     	return false;
			    }
			}// end if
		}
		return true;
	}
}
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['˵��:ͨ����ƷͼƬ���ϴ�����ǰ̨ĳЩ�ض�λ�õ�ͼƬչʾ<br/>'
	    	                + 'ʹ����Ⱥ:��Ʒ������Ա<br/>'
	    	                + '��������:<br/>'
	    	                + 'ע������:�ϴ�ͼƬʱע��ͼƬ�ĸ�ʽ���С<br/>'];
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
	});
</script>
</html>
