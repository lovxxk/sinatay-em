<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>

<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>


<title>新建营销活动</title>
<style type="text/css">
	.table_style_new{
	border-collapse:collapse;
	font-family:宋体;
	font-size:12px;
}	
#productDetail tr {
		height:85px;
}
#addRuleDiv table tr {
	height:30px;
}
body,h2{margin:0 ; padding:0;}
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:auto;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:auto; left:50%; top:50%; margin-left:-400px; height:auto; z-index:100;background-color:#fff; border:1px #8FA4F5 solid; padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#27a26b; position:relative; padding-left:10px; line-height:25px;text-align: left;width: 740px}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>

</head>
<body onload="setpageDage()">
<input type="hidden" value="" id="dateTempId"/>
<input type="hidden" value="<s:date name="date" format="yyyy-MM-dd"/>" id="minDate" />

<div class="public_fun_title" style="width:1100px">
新建营销活动<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>

<%//这个隐藏是用于添加多条的时候使用start %>
<table  id="tableInfoHidden" width="100%" style="display: none;">
<tr>
<td>
		<table width="100%" style="border-collapse:collapse;">
		<tr style="border-top:1px solid #DBDBDB;"><td colspan="4"><span id="count" style="font-weight: bold;">规则1</span></td></tr><%//第0行 %>
					
					
					<tr><%//第1行 %>
					<td width="20%" style="text-align: right;">保费：</td>
					<td width="80%">	
						<select onchange="showPremum(this)" name="premumOperator" style="width:170px;">
							<option value="">--请选择--</option>
							<s:if test="geCodePremiumTypeList!=null" >
							<s:iterator value="geCodePremiumTypeList" var="geCodePremiumType">
								<option value="<s:property value="#geCodePremiumType.id.codeCode"/>"><s:property value="#geCodePremiumType.codeCName"/></option>
							</s:iterator>
							</s:if>			
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第2行 %>
					<td width="20%" style="text-align: right;">保费的值：</td>
					<td width="80%">
					<%//js往里写的 %>
					</td>
					</tr>
					
					
					
					
					
					<tr><%//第3行 %>
					<td style="text-align: right;">活动方式：</td>
					<td width="400px">
						<select  name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
							<option value="">--请选择--</option>
							<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
									<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>">
										<s:property value="#geCodeActivityPattern.codeCName"/>
									</option>
							</s:iterator>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第4行 %>
					<td width="20%" style="text-align: right;">商品名称：</td>
					<td width="80%">
					<div class="frmCreate_kuang" style="width:250px;margin-top:10px; " >
					<div class="frmCreate_kuang_header">选择商品<span style="color:#FF9000;font-weight:bold;"></span></div>
					<div style="padding-top:15px;">
					<div><table id="productMoreCallBack" name="productMoreCallBack"></table></div>
					<div class="frmCreate_kuang" style="width:250px;">
					<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
						<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
						<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">展开查看更多</a></span>
						<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
						<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">收起</a></span>
						<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
					</div>
					<div id="productInfo" style="padding-top:15px;clear:both;display: none">
					<div><table id="productMoreCallBacktwo"></table></div>
					</div></div>
					</div></div>
				<input type="hidden" name ="temp"  readonly="readonly" value="00">
					<input type="button" nameCount="1" value="选择商品" name="itemName" onclick="getGeThirdParterInfo(this);" >
						<input type="hidden" value="" name="itemID" id="itemID0">
					</td>
					</tr>
					
					<tr style="display: none;"><%//第5行  打折类型%>
					<td style="text-align: right;">打折类型：</td><%//折扣为 %>
					<td>
						<select name="discountType"  style="width:170px;">
							<option value="">--请选择--</option>						
							<option value="01">打折率</option>
							<option value="02">打折价</option>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第6行 n的值  %>
					<td style="text-align: right;" class="dazhe">n的值：</td><%//折扣为 %>
					<td>
						<input type="text" name="nvalue" style="width:170px;" maxlength="10">
					</td>
					</tr>
				
					<tr style="display: none;"><%//第7行  折扣ID%>
					<td style="text-align: right;"></td><%//折扣为折扣ID(寿险)： %>
					<td>
<!--						<input type="text"  name="discountID" style="width:170px;"  onkeyup="value=value.replace(/[\W]/g,'')" maxlength="200">-->
						
					</td>
					</tr>
					
					<%//打折因子描述 加了一行 %>
					<tr style="display: none;"><%//第8行 %>
					<td style="text-align: right;">打折因子描述：</td><%//折扣为 %>
					<td>
						<textarea rows="4" cols="60" name="discountRemarkText" ></textarea>
					</td>
					</tr>
					<tr style="display: none;"><%//第9行 %>
					<td style="text-align: right;vertical-align:top">加购产品：</td>
					<td >
						<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
							<tr>
								<td colspan="2">
									<input type="button" value="增加加购产品" onclick="addShopping(this)"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right;padding-right:60px">
						<input type="button" value='删除' onclick="deleteTable(this)">
					</td>
				</tr>
						</table>
					</td>
				</tr>

		</table>

<%//这个隐藏是用于添加多条的时候 使用 增加产品 %>
<table style="display: none;">
<tbody id="tableProductHidden">
<tr typeProduct="productNameTr">
		<td >
<!--			<input type="text"  name="coreProductName" style="width:235px;" maxlength=80  readonly="readonly">(双击域选择)-->
<!--			<input type="text"  name="coreProductCode"><%//实际上就是EID %>-->
<!--			<input type="button" value="删除本行" onclick="deleteOnself(this);">-->
		</td> 
</tr>
</tbody>
</table>

<%//这个隐藏域是用于加购产品的 %>
<table style="display: none">
<tbody id="tableAddShoppingHidden">
<tr>
	<td>
		<input type='button' value='增加加购产品' onclick='addShopping(this)'/>		
		<div><table name="addshoppingProductCallBack" typeProduct="addShopping"></table></div>

	</td>
<!--	<td>-->
<!--        <input type='button' value='增加产品' onclick='addShopping(this)'/>		-->
<!--	</td>-->
</tr>
</tbody>
</table>

<form action="${ctx}/marketing/addAddGeAddServiceActivityAndRule.do" id="frmInput" method="post" target="myFrame" enctype="multipart/form-data">
<div id="BgDiv" ></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;cursor:pointer;">关&nbsp;闭</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>
<input type="hidden" name="uploadPictureSerialNos" />
<table class="table_style" align="center" width="900px" id="productTable">
	<tr style="display:none">
		<td>
		  <table background="${ctx}/global/images/Process.jpg" width="994px" height="105px">
			  	<tr>
			  		<td colspan="3" height="50px"></td>	
			  	</tr>
			  	<tr>
			  		<td width="580px"></td>
			  		<td width="120px"  class="but" style="cursor:pointer;" onclick="btnShowone('ProcessRole');" id="ProcessRole" >
			  		</td>
			  		<td></td>
			  	</tr>
		  </table>
		</td>
	</tr>
	<tr>
	<td class="td_body" colspan="3" >
		<div  style="width:650px;margin-top:15px;padding-bottom: 15px; padding-left: 80px">
		<table>
	<tr>
		<td class="td_head"  nowrap>活动名称：</td>
		<td class="td_body" nowrap>
			<input type="text"  id="activityName" name="geAddServiceActivity.activityName" style="width:235px;" onkeyup="countActivityName()" onkeypress="countActivityName()" onblur="countActivityName();" onChange="countActivityName()" >
		</td>
	</tr>
	<tr height="155x">
		<td class="td_head" nowrap valign="top">活动内容介绍：</td>
		<td class="td_body" nowrap >
				<textarea rows="8" cols="50" id="geAddServiceActivity.activityContent" style="margin-bottom:2px;width:365px;height: 145px" name="geAddServiceActivity.activityContent"  onkeyup="count()" onkeypress="count()" onblur="count();" onChange="count();"></textarea>
		</td>
	</tr>
	<tr style="display: none;">
		<td class="td_head" nowrap>业务：</td>
		<td class="td_body" >
		<s:iterator value="geCodeBusinessAreaList" var="geCodeBusinessArea">
		    
			<input type="Radio"  style=" border: 0px;"name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>" onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;
		
		</s:iterator>
		
		</td>
	</tr>
	
	<%//所有的代码的存储都是以deptId为准 %>
	<tr style="display: none;">
		<td class="td_head" nowrap>活动地域：</td>
		<td class="td_body" >
		<div style="float:left">
			<select id="authorityid1" name="_province" onchange="selectCity();" style="width: 135px">
				<option value="1" selected>--请选择--</option>
			</select>
			</div>
			<div style="float:left;display: none;" id="suv">
			<select id="cityId1" name="_deptID" style="display: none;">
				<option value="1" selected>--请选择--</option>
			</select>
			</div>
		</td>
	</tr>
	<input type="hidden" id="authorityid" name="geAddServiceActivity.province" value="1"/>
	<input type="hidden" id="cityId" name="geAddServiceActivity.deptID" value="1"/>
	
	
	
	<tr>
		<td class="td_head" nowrap>活动起始时间：</td>
		<td class="td_body" >
		<input type="text" id="activityStartDate" name="geAddServiceActivity.activityStartDate" class="Wdate" style="width:235px;" maxlength=80 readonly="readonly" onfocus="startDate();">
		</td>
	</tr>
	
	<tr>
		<td class="td_head" nowrap>活动结束时间：</td>
		<td class="td_body" >
		<input type="text" id="activityEndDate" name="geAddServiceActivity.activityEndDate" class="Wdate" style="width:235px;" maxlength=80  readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'activityStartDate\')}'})">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>是否有效：</td>
		<td class="td_body" >
			<select style="width:100px;" name="geAddServiceActivity.validInd">
				<option value="1" selected>有效</option>
				<option value="0">无效</option>
			</select>
			<input type="hidden" id="status" name="geAddServiceActivity.status" value="0">
		</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
	<!--
	
	<tr>
		<td class="td_head" nowrap >产品名称：</td>
		<td class="td_body" >
			<table  id="productModel" width="100%">
				<tr id="productType" typeProduct="productNameTr">
					<td >
					    <input type="hidden" id="businessAreaForCompare">
					    <%/*
						<input type="text" id="coreProductName0" name="coreProductName"  style="width:235px;" maxlength=80 ondblclick="selectProduct('1');" readonly="readonly">(双击域选择)
						<input type="hidden"  name="coreProductCode">
						*/%>
						<input type="button" value="增加产品" onclick="addProduct();">
					</td> 
				</tr>	
			</table>
		</td>
	</tr>
	-->
	<tr>
	<td colspan="3" style="padding-left: 70px;">
		<div class="frmCreate_kuang" style="margin-left:10px;width:720px;margin-top:10px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header">添加产品图片<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style="padding-left:3px; padding-top:15px;">
	<table>
	<tr>
		<td class="td_head" nowrap valign="top">
			</td>
		<td class="td_head" valign="top" style="cursor:pointer;"><a href="#" title="图片使用位置" onclick="btnShowone('showImg1');" id="showImg1"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片1：
			<input type="file" name="uploadPicture" id="uploadPicture1"  onblur="addPreviewFace(this.id);"  style="width:235px;" maxlength=80 >
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[0].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture1TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片1预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture1Preview"  width="200" height="100"/></div>
		</td> 
	</tr>		
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="图片使用位置" style="cursor:pointer;" onclick="btnShowone('showImg2');" id="showImg2"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片2：
			<input type="file" name="uploadPicture" id="uploadPicture2" onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80>
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[1].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	
	<tr style="display: none;" id="uploadPicture2TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片2预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture2Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="图片使用位置" style="cursor:pointer;" onclick="btnShowone('showImg3');" id="showImg3"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片3：
			<input type="file" name="uploadPicture" id="uploadPicture3"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[2].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture3TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片3预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture3Preview"  width="200" height="100"/></div>
		</td> 
	</tr>

	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" >
<!--		<a href="${ctx}/global/images/productSmallPictureOne.jpg" title="图片使用位置" class="thickbox"></a> background:#f3f3f3"-->
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>上传图片4：
			<input type="file" name="uploadPicture" id="uploadPicture4"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[3].jumpUrl" style="width:170px;"     maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture4TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片4预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture4Preview"  width="200" height="100" /></div>
		</td> 
	</tr>

	<tr>
		<td class="td_head" nowrap valign="top" >
		</td>
		<td class="td_head" >
<!--		<a href="${ctx}/global/images/productSmallPictureOne.jpg" title="图片使用位置" class="thickbox"></a>-->
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>上传图片5：
			<input type="file" name="uploadPicture" id="uploadPicture5"   onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[4].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture5TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片5预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture5Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	</table>
	</div>
	</div>
	<div class="frmCreate_kuang" style="margin-left:10px;width:720px;margin-top:10px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header" style="width:718px">添加活动规则<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style=" padding-top:10px;" ><!--id="addRuleDiv"   加够间距-->  
	<table width="700px">
	<tr>
		<td class="td_body" >
			<table  id="tableInfo" width="100%">
			<tr id="0">
				<td >
					<table width="710px" style="border-collapse:collapse;">
					<tr><td colspan="4"><span id="count" style="font-weight: bold;">规则1</span></td></tr><%//第0行 %>
					
					
					<tr><%//第1行 %>
					<td width="20%" style="text-align: right;">保费：</td>
					<td width="80%">	
						<select onchange="showPremum(this)" name="premumOperator" id="premumOperator0" style="width:170px;">
							<option value="">--请选择--</option>
							<s:if test="geCodePremiumTypeList!=null" >
							<s:iterator value="geCodePremiumTypeList" var="geCodePremiumType">
								<option value="<s:property value="#geCodePremiumType.id.codeCode"/>"><s:property value="#geCodePremiumType.codeCName"/></option>
							</s:iterator>
							</s:if>			
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第2行 %>
					<td width="8%" style="text-align: right;">保费的值：</td>
					<td width="92%">
					<%//js往里写的 %>
					</td>
					</tr>
					
					
					
					
					
					<tr><%//第3行 %>
					<td style="text-align: right;">活动方式：</td>
					<td width="400px">
						<select id="activityPattern0" name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
							<option value="">--请选择--</option>
							<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
									<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>">
										<s:property value="#geCodeActivityPattern.codeCName"/>
									</option>
							</s:iterator>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第4行 %>
					<td width="10%" style="text-align: right;">商品名称：</td>
					<td width="50%">
					<div class="frmCreate_kuang" style="width:250px;margin-top:10px; " id="kindDiv" >
					<div class="frmCreate_kuang_header">选择商品<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
					<div style="padding-top:15px;">
					<div><table id="productMoreCallBack" name="productMoreCallBack"></table></div>
					<div class="frmCreate_kuang" style="width:250px;">
					<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
						<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
						<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">展开查看更多</a></span>
						<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
						<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">收起</a></span>
						<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
					</div>
					<div id="productInfo" style="padding-top:15px;clear:both;display: none">
					<div><table id="productMoreCallBacktwo"></table></div>
					</div></div>
					</div></div>
					<input type="hidden"  name = "temp" id="itemName0"  readonly="readonly" value="00">
					<input type="button" nameCount="1" name="itemName"  value="选择商品" onclick="getGeThirdParterInfo(this);" id="itemName0" >
					<input type="hidden" value="" name="itemID" id="itemID0">
					</td>
					</tr>
					
					<tr style="display: none;"><%//第5行  打折类型%>
					<td style="text-align: right;">打折类型：</td><%//折扣为 %>
					<td>
						<select name="discountType" id="discountType0" style="width:170px;">
							<option value="">--请选择--</option>						
							<option value="01">打折率</option>
							<option value="02">打折价</option>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//第6行 n的值  %>
					<td style="text-align: right;" class="dazhe">打折率/打折价n的值(%)：</td><%//折扣为 %>
					<td>
						<input type="text" id="nvalue0" name="nvalue" maxlength="10" style="width:170px;">
					</td>
					</tr>
				
					<tr style="display: none;"><%//第7行  折扣ID%>
					<td style="text-align: right;"></td><%//折扣为 折扣ID(寿险)：%>
					<td>
<!--						<input type="text" id="discountID0" name="discountID" style="width:170px;"   onkeyup="value=value.replace(/[\W]/g,'')" maxlength="200">-->
					</td>
					</tr>
					
					<%//打折因子描述 加了一行 %>
					<tr style="display: none;"><%//第8行 %>
					<td style="text-align: right;">打折因子描述：</td><%//折扣为 %>
					<td>
						<textarea rows="4" cols="60" name="discountRemarkText" id="discountRemarkText0"></textarea>
					</td>
					</tr>
					<tr style="display: none;"><%//第9行 %>
					<td style="text-align: right;vertical-align:text-top">加购产品：</td>
					<td >
						<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
							<tr>
								<td colspan="2">
									<input type="button" value="增加加购产品" onClick="addShopping(this)"/>
								</td>
							</tr>
						</table>
					</td>
					</tr>
					</table>
					</td>
				</tr>
					</table>
				</td>
			</tr>
			</table>
			</div>
			</div>
	</td>
	</tr>
	<tr>
	<td colspan="3" style="padding-left: 130px;">
		
		</td>
	</tr>
	
	 <tr style="display:none;"><%//第9行 %>
		<td style="text-align: right;vertical-align:top">加购产品：</td>
		<td >
			<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
				<tr>
					<td colspan="2">增加加购产品
						<input type="button" value="增加加购产品" onclick="addShopping(this)"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="td_body" style="padding-left:80px;" ><input type="button" value="增加活动规则" onclick="insertTable();" ></td>
	</tr>
	<% /*增加产品*/ %>
	<tr>
<!--		<td  class="td_body" ></td>-->
		<td colspan="2">  
			<div class="frmCreate_kuang" style="width:700px;margin-top:10px;">
			<div class="frmCreate_kuang_header">增加的产品<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-top:15px;">
			<div><table id="productCallBack"></table></div>
			<div class="frmCreate_kuang" style="width:700px;" id="showOrhideInfo">
			<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
				<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
				<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">展开查看更多</a></span>
				<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
				<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">收起</a></span>
				<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
			</div>
			<div id="productInfo" style="padding-top:15px;clear:both;display: none">
			<div><table id="productCallBacktwo"></table></div>
			</div></div>
			</div></div>
			<div style="margin-left:80px;padding-top: 10px;text-align: left;"><input type="button" value="增加产品" onclick="addProductTest();" ></div>
			
		</td>
		
	</tr>
	<tr height=25><td></td></tr> 
	<tr height="60px;">
		<td colspan="2" align="center">
		<table>
			<tr>
			    <input type="hidden" name="functionTypeString" value="serviceADD"/>
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
<script type="text/javascript"><!--
//弹出一个地区的树的菜单
function deptAuthIdQuery(){
	/*
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;//value='--全部--'
		//
		alert("authorityDepartmentManager="+authorityDepartmentManager);
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_OORD_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
		*/
		//alert();
	//window.open('${ctx}/marketing/selectDeptAuthId.do?checkType=1&authorityid=ROLE_B_AAGA&type=2&receivedObj=3110000&receivedObjName=--全部--&deptIdCount=0','机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","查询供应商" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
function selectProduct(count){
	
	if($("#authorityDepartmentManager").val()==""){
		alert("请先选择  活动地域");	
	}else{
		//提练出业务领域
		var authorityid = $("#authorityid").val();
		window.open("${ctx}/business/marketingManage/create/selectProduct/index.jsp?count="+count+"&deptId="+authorityid+"&coreProductCode="+ obj.value, "查询产品", "top=100, left=100, width=900,height=600,toolbar=no");	
	}
	
}


//展示更多产品信息
function showInfo(){
	var productInfo = document.getElementById("productInfo");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	showText.style.display = "none";
	showPicture.style.display = "none";
	hideText.style.display ="";
	hidePicture.style.display ="";
	productInfo.style.display = "";
	
}
//收起更多产品信息
function hideInfo(){
	var productInfo = document.getElementById("productInfo");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	productInfo.style.display = "none";
	hideText.style.display ="none";
	hidePicture.style.display ="none";
	showText.style.display = "";
	showPicture.style.display = "";
}
//起 始时间
function startDate(){
	WdatePicker({onpicked:function(){
		document.getElementById("activityEndDate").focus();
		},minDate:'#F{$dp.$D(\'minDate\')}'})
		
}
function doClear(){
	document.getElementById("frmInput").reset();
	//所有的图片预览都清空
	//alert($("tr[class='reset']").length);
	if($("tr[class='resetNode']").length>0){
		for(var i=0;i<$("tr[class='resetNode']").length;i++){
			document.getElementById("resetNode"+i).style.display="none";
		}
	}
	if($("tr[class='addResetNode']").length>0){
		for(var i=0;i<$("tr[class='addResetNode']").length;i++){
			document.getElementById("addResetNode"+i).style.display="none";
		}
	}
	/*if($("tr[class='classNonoNum']").length>0){
		for(var i =0;i<$("tr[class='classNonoNum']").length+1;i++){
			$("tr[id='"+i+"'][class='classNonoNum']").hide();
		}
		
	}*/
	document.getElementById("cityId").style.display="none";
	
	document.getElementById("uploadPicture1TR").style.display="none";
	document.getElementById("uploadPicture2TR").style.display="none";
	document.getElementById("uploadPicture3TR").style.display="none";
	document.getElementById("uploadPicture4TR").style.display="none";
	document.getElementById("uploadPicture5TR").style.display="none";
	$("#showOrhideInfo").hide();
}

function setpageDage(){
	var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "只能输入数字或字母");
	tt.vf.req.add("geAddServiceActivity.activityName");//活动名称
	tt.vf.req.add("geAddServiceActivity.activityStartDate");//活动起始时间
	tt.vf.req.add("geAddServiceActivity.activityEndDate");//活动结束时间s
	tt.vf.req.add("geAddServiceActivity.activityContent");////活动内容介绍
// 	tt.vf.req.add("geAddServiceActivity.province");////活动地域
// 	tt.vf.req.add("geAddServiceActivity.deptID");////活动地域
	tt.vf.req.addId("premumOperator0");////保费
	tt.vf.req.addId("activityPattern0");//////活动方式
	/*if($("input[name='nvalue']").length>0){
		for(var i=0;i<$("input[name='nvalue']").length;i++){
			ttvf.addId("nvalue"+i);
		}
	}*/
	new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");//活动方式字数限制


	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建营销活动配置产品<br/>使用人群：营销活动配置人员。<br/>配置条件：需要增加活动规则，活动产品。<br/>注意事项：请根据流程，看配置说明 。'];
		
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
					tip:true,//控制左侧尖角是否出现
					padding :10
				}
			});
    	}



}

//上传图片显示的东西
$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
	//var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "只能输入数字或字母");
	//tt.vf.req.add("geAddServiceActivity.activityName");//活动名称
	//tt.vf.req.add("geAddServiceActivity.activityStartDate");//活动起始时间
	//tt.vf.req.add("geAddServiceActivity.activityEndDate");//活动结束时间s
	///tt.vf.req.add("geAddServiceActivity.activityContent");////活动内容介绍
	//tt.vf.req.add("geAddServiceActivity.province");////活动地域
	//tt.vf.req.add("premumOperator");////保费
	//tt.vf.req.add("activityPattern");//////活动方式
	//tt.vf.req.add("nvalue");//n的值
	//tt.vf.req.add("discountID");//打折ID 寿险
	//tt.vf.req.add("geAddServiceActivity.activityEndDate");//商品名称
	new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");
	//pop提示信息
	var ids = ['uploadPicture1','uploadPicture2','uploadPicture3','uploadPicture4','uploadPicture5'];
	var contents = ['上传图片1只能上传bmp，jpg，gif格式的图片，长600px，宽50px。','上传图片2只能上传bmp，jpg，gif格式的图片，长240px，宽330px。','上传图片3只能上传bmp，jpg，gif格式的图片，长250px ，宽30px。','上传图片4只能上传bmp，jpg，gif格式的图片。','上传图片5只能上传bmp，jpg，gif格式的图片。'];
		
    	for ( var i = 0 ; i < 10 ; i++ ){
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
					width:220,
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
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("最多输入500个字");
	    	return false;
	    }

}
//图片使用位置操作
function btnShowone(showImgs)
		{
		   $("#"+showImgs).click(function()
		   {
		      $("#BgDiv").css({ display:"block",height:$(document).height()});
		      var yscroll=document.documentElement.scrollTop;
		      $("#DialogDiv").css("top","100px");
		      $("#DialogDiv").css("display","block");
		      $("#showImg").attr("src","${ctx}/global/images/"+showImgs+".jpg");
		      if(showImgs=="ProcessRole"){
		    	  $("#spanProcessRole").remove();
		    	  $("#h2btn").append("<span id=\"spanProcessRole\" style=cursor:pointer>配置说明</span>");
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
</html>