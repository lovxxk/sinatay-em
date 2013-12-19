<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<script type="text/javascript">
	window.onload=function(){
		getAppleTxt();
		addPreviewFaceInit();
		pageValidate();
};
</script>
<%//tao 哥 %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>


<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-编辑产品图片信息</title>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<!--<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>-->
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/checkIdCard.js"></script>
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
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			编辑产品图片信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form id="frmInput" action="${ctx}/business/cmpProductManage/productPictureManage/updateGeProductPictureDetail.do" method="post" enctype="multipart/form-data" target="myFrame">
<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">关&nbsp;闭</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>	
	<table class="table_style" align="center" width="800px" id="productTable">
	
	<tr>
		<td>
			<div style="padding-left:146px;">
				<table>
					<tr>
						<td class="td_head" style="text-align: right;" nowrap>名&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
						<td class="td_body">
							<input type="text" id="picturename" name="geProductPictureDetail.picturename" value="${geProductPictureDetail.picturename}" style="width:240px;" maxlength=80 >
						</td>
						</tr>
						<tr>
							<td class="td_head" style="text-align: right;" nowrap valign="top">描&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
							<td class="td_body" >
								<textarea rows="8" cols="40" id="picturedesc" name="geProductPictureDetail.picturedesc">${geProductPictureDetail.picturedesc}</textarea>
							</td>
						</tr>
						<tr>
						<td class="td_head" style="text-align: right;" nowrap>是否启用：</td>
						<td>
						<input type="radio"	class="no_border_input" id="appleflag1" name="geProductPictureDetail.flag" value="1">启用
						<input type="radio" class="no_border_input" id="appleflag0" name="geProductPictureDetail.flag" value="0">不启用
						<input type="hidden" id="geProductPictureDetailFlag" value="${geProductPictureDetail.flag}">
						</td>
					</tr>				
				</table>
			</div>
		</td>
	</tr>
	<!-- 以下的tr是图片预览展示和修改部分 -->
	<tr>
	<td class="td_body" colspan="3" style="padding-left:56px;">
		<div class="frmCreate_kuang" style="margin-left:90px;width:500px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header">修改产品图片<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style="padding-left:3px; padding-top:15px;">
	<table>
	<s:if test="geProductPictureDetail.geProductPictures.size>0">
		<s:iterator value="geProductPictureDetail.geProductPictures" var="geProductPicture" status="i">
			<%//<s:if test="#geActivitiesPicture.nooryes=='yes'">%>
			<c:choose>
			<c:when test="${(i.index+1)<=3}">
			<tr name = "upload">
				<td class="td_head" nowrap></td>
				<td class="td_head" ><a href="#" title="图片使用位置" onclick="btnShowone('showImg${i.index+1}');" id="showImg${i.index+1}"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
				修改上传图片${i.index+1}：
					<input type="file" onfocus="addPreviewFace(this.id);" name="uploadPicture" id="uploadPicture${i.index+1}" style="width:235px;" maxlength=80 >
				</td>
			</tr>
			<tr id="uploadPicture${i.index+1}TR" style="display: none;">
				<td class="td_head" nowrap>
				</td>
				<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>图片${i.index+1}预览：</div>
					<input type="hidden" value="<s:if test="#geProductPicture.nooryes=='yes'">${ctx}/<s:property value="#geProductPicture.pictureurl"/></s:if>" id="hiddenImage${i.index+1}" name="hiddenImage">
					<div style="padding-left: 130px"><img  src="${ctx}/<s:property value="#geProductPicture.pictureurl"/>" width="200" height="100" id="uploadPicture${i.index+1}Preview"></div>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr name = "upload">
				<td class="td_head" nowrap></td>
				<td class="td_head" >
				<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>
				修改上传图片${i.index+1}：
					<input type="file" onfocus="addPreviewFace(this.id);" name="uploadPicture" disabled="disabled" id="uploadPicture${i.index+1}" style="width:235px;" maxlength=80 >
				</td>
			</tr>
			<tr id="uploadPicture${i.index+1}TR" style="display: none;">
				<td class="td_head" nowrap>
				</td>
				<td class="td_body" ><div style="top: auto;padding-right: 242px" class="td_head" nowrap>图片${i.index+1}预览：</div>
					<input type="hidden" value="<s:if test="#geProductPicture.nooryes=='yes'">${ctx}/<s:property value="#geProductPicture.pictureurl"/></s:if>" id="hiddenImage${i.index+1}" name="hiddenImage">
					<div style="padding-left: 130px"><img  src="${ctx}/<s:property value="##geProductPicture.pictureurl"/>" width="200" height="100" id="uploadPicture${i.index+1}Preview"></div>
				</td>
			</tr>
			</c:otherwise>
			</c:choose>
		</s:iterator>
	</s:if>
	</table>
	</div>
  </div>
</td>
</tr>
	<tr>
		<td colspan=2>
			<table width=300 align="center">
			<tr>
				<acc:showView source="ROLE_BU_PPM_M_E">
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>	
					<td  class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
				<td>&nbsp;</td>
				</acc:showView>
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>	
				<td>&nbsp;</td>
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>	
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" name="geProductPictureDetail.detailid" id="detailid" value="${geProductPictureDetail.detailid}">
	<input type="hidden" name="geProductPictureDetail.createTime" id="createTime" value="${geProductPictureDetail.createTime}">
	<input type="hidden" name="uploadPictureSerialNos" />
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
//上传图片显示的东西
$(document).ready(function(){
	//pop提示信息
	var ids = ['picturedesc','uploadPicture1','uploadPicture2','uploadPicture3','uploadPicture4','uploadPicture5'];
	var contents = ['产品图片的描述','上传图片1只能上传bmp,jpg,gif格式的图片，长600px宽50px！','上传图片2只能上传bmp,jpg,gif格式的图片，长244px  ，宽220px！','上传图片3只能上传bmp,jpg,gif格式的图片！，长250px ，宽30px！','上传图片4只能上传bmp,jpg,gif格式的图片！','上传图片5只能上传bmp,jpg,gif格式的图片！'];
		
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
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
//pop提示信息结束
});

function addPreviewFace(obj){
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview();
		
		var filepath=document.getElementById(obj).value;
		var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
		extname = extname.toLowerCase();//处理了大小写
	    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
	    	document.getElementById(obj+"TR").style.display="none";
	    }else{
	    	document.getElementById(obj+"TR").style.display="";
	    }
		
	};
}
function getAppleTxt(){
	var flag=document.getElementById("geProductPictureDetailFlag").value;
	//alert(flag);
	if(flag=="1")
	{
		document.getElementById("appleflag1").checked=true;
	}
	else
	{
		document.getElementById("appleflag0").checked=true;
	}
}

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

function doUpdate(){
	//提交前的验证

	if(!tt.validate()){
		return false;
	}
	
	//判断图片格式
	var uploadPictures1=	$("input[name='uploadPicture']");
	for(var i=0;i<uploadPictures1.length;i++){
		var filepath1 = uploadPictures1[i].value;
		if(filepath1!=""){
			var extname = filepath1.substring(filepath1.lastIndexOf(".")+1,filepath1.length);
			extname = extname.toLowerCase();//处理了大小写
		    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
		    	alert("上传图片"+(i+1)+" 只能上传bmp,jpg,gif格式的图片！");
		     	return false;
		    }
		}// end if
	}
	
	//设置图片的上传
	var uploadPictures = document.getElementsByName("uploadPicture");
	
	var uploadSerialNos= "";
	for(var i=0;i<uploadPictures.length;i++){
	//	alert("图"+[i]+ ":"+ uploadPictures[i].value);
		if(uploadPictures[i].value!=""){
			uploadSerialNos = uploadSerialNos+(i+1)+",";
		}
	}
	uploadSerialNos = uploadSerialNos.substring(0,(uploadSerialNos.length-1));
	document.getElementsByName("uploadPictureSerialNos")[0].value=uploadSerialNos;
	
	//if(checkIdentify("identifyNumber_tip")&tt.validate()){
		document.getElementById("frmInput").submit();
	//}
}

function doClear(){
	document.getElementById("frmInput").reset();
	var flag=document.getElementById("geProductPictureDetailFlag").value;
	//alert(flag);
	if(flag=="1")
	{
		document.getElementById("appleflag1").checked=true;
	}
	else
	{
		document.getElementById("appleflag0").checked=true;
	}
}

function doBack(){
	window.location = "${ctx }/business/cmpProductManage/productPictureManage/queryGeProductPictureForShow.do?geProductPictureDetail.detailid=${geProductPictureDetail.detailid}";
}

function pageValidate(){
	tt.vf.req.addName("geProductPictureDetail.picturename","geProductPictureDetail.picturedesc");
	new tt.LV().set(0,30).add("geProductPictureDetail.picturename");
	new tt.LV().set(0,200).add("geProductPictureDetail.picturedesc");
}
//图片使用位置操作
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
		    	  $("#h2btn").append("<span id=\"spanProcessRole\">配置说明</span>");
		      }else{
		    	  $("#spanShowImgs").remove();
		    	  $("#h2btn").append("<span id=\"spanShowImgs\">图片使用位置 </span>");
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
</script>
</body>
</html>
