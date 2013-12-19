<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
<%//tao 哥 %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>

<title>编辑增值服务</title>
<style type="text/css">
	td {
		vertical-align:top;
	}
	#productDetail tr {
		height:85px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
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
<body onload="setUpdate();">
<input type="hidden" value="" id="dateTempId"/>
<input type="hidden" value="<s:date name="date" format="yyyy-MM-dd"/>" id="minDate" />
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				编辑活动信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<%//这个隐藏是用于添加多条的时候使用start %>
<table  id="tableInfoHiddenForUpdate" width="100%" style="display: none;">
<tr>
<td>
<!--<div class="frmCreate_kuang_header"><span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>-->
				<div style="padding-left:3px; padding-top:5px;">
		<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
		<tr><td colspan="2" height="30px"><span id="count" style="font-weight: bold;">规则1</span></td></tr>
		
		<tr >
		<td width="15%" style="text-align: right;">保费：</td>
		<td width="40%">
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
		
		<tr style="display: none;">
			<td width="10%" style="text-align: right;">保费的值</td>
			<td width="40%">
									
			</td>
		</tr>
		
		
		<tr>
		<td style="text-align: right;">活动方式：</td>
		<td width="400px">
			<select name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
				<option value="">--请选择--</option>
				<s:if test="geCodeActivityPatternList!=null">
				<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
					<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>">
						<s:property value="#geCodeActivityPattern.codeCName"/>
					</option>
				</s:iterator>
				</s:if>
			</select>
		</td>
		</tr>
		
		
		<tr style="display: none;">
		<td width="10%" style="text-align: right;">商品名称：</td>
		<td width="40%">
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
			<input type="hidden""  name="temp" readonly="readonly" value="00">
			<input type="button" nameCount="0"  value="选择商品" onclick="getGeThirdParterInfo(this);" name="itemName" >
			<input type="hidden" value="" name="itemID" >
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
		
		<tr style="display: none;">
		<td style="text-align: right;" class="dazhe">n的值：</td>
		<td>
			<input type="text"  name="nvalue" style="width:170px;" maxlength="10">
		</td>
		</tr>
		
		<tr style="display: none;"><%//第7行  折扣ID%>
		<td style="text-align: right;"><!--折扣ID(寿险)：--></td><%//折扣为 %>
		<td>
<!--			<input type="text" name="discountID" style="width:170px;" maxlength="200" onkeyup="value=value.replace(/[\W]/g,'')">-->
		</td>
		</tr>
		
		<%//打折因子描述 加了一行 %>
		<tr style="display: none;"><%//第6行 %>
		<td style="text-align: right;">打折因子描述：</td><%//折扣为 %>
		<td>
			<textarea rows="4" cols="56" name="discountRemarkText" ></textarea>
		</td>
		</tr>
		
		<tr style="display: none;"><%//第6行 %>
			<td style="text-align: right;">加购产品：</td>
			<td >
				<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
					<tr>
						<td colspan="2">
							<input type="button" value="增加加购产品" onclick="upaddShopping(this)"/>
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
		</table></div>
</td>
</tr>
</table>


<%//这个也是隐藏增加条时用的 %>
<table style="display: none;">
<tbody id="tableDelShowHidden">
<tr name = "upload">
	<td class="td_head" nowrap>上传图片：</td>
	<td class="td_body" >
	</td>
</tr>

</tbody>
</table>

<%//这个隐藏是用于添加多条的时候 使用增加产品 %>
<table style="display: none;">
<tbody id="tableProductHidden">
<tr typeProduct="productNameTr">
		<td >
			<input type="text"  typeProduct="productName"  name="coreProductName" style="width:235px;" maxlength=80  readonly="readonly">(双击域选择)
<!--			<input type="hidden"  name="coreProductCode"><%//实际上就是EID %>-->
			<input type="button" value="删除本行" onclick="deleteOnself(this);">
		</td> 
</tr>
</tbody>
</table>

<%//这个隐藏域是用于加购产品的 %>
<table style="display: none;">
<tbody id="tableAddShoppingHidden">
<tr>
	<td>
		 <input type='button' value='增加加购产品' onclick='upaddShopping(this)'/>		
		<div><table name="addshoppingProductCallBack" typeProduct="addShopping"></table></div>
	</td>
<!--	<td>-->
<!--        <input type='button' value='增加加够产品' onclick='upaddShopping(this)'/>		-->
<!--	</td>-->
</tr>
</tbody>
</table>

<form action="${ctx}/marketing/updateAddGeAddServiceActivity.do" id="frmInput" method="post" target="myFrame" enctype="multipart/form-data">
<div id="BgDiv" ></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">关&nbsp;闭</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>
<input type="hidden" value="<s:date name="date" format="yyyy-MM-dd"/>" id="minDate" />
<input type="hidden" name="uploadPictureSerialNos" /><%//图片的序列号%>
<input type="hidden" name="oldProduct" value="<s:property value="oldProductEid"/>"/><%//老的产品的ID %>
<input type="hidden" name="geAddServiceActivity.activityId" value="<s:property value="geAddServiceActivity.activityId"/>" />
<input type="hidden" name="geAddServiceActivity.createDate" value="<s:date name="geAddServiceActivity.createDate" format="yyyy-MM-dd HH:mm:ss"/>" />
<input type="hidden" name="geAddServiceActivity.status" value="<s:property value="geAddServiceActivity.status"/>" />
<input type="hidden" name="geAddServiceActivity.createDeptId" value="<s:property value="geAddServiceActivity.createDeptId"/>">
<input type="hidden" name="rulds" value="<s:property value="rulds"/>"><%//归则ID %>
<input type="hidden" name="geAddServiceActivity.flag" value="<s:property value="geAddServiceActivity.flag"/>">
<input type="hidden" id="authorityid" name="geAddServiceActivity.province" value="<s:property value="geAddServiceActivity.province"/>"/>
<input type="hidden" id="cityId" name="geAddServiceActivity.deptID" value="<s:property value="geAddServiceActivity.deptID"/>"/>


<table class="table_style" align="center" width="800px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
	<td class="td_body" colspan="3" >
		<div  style="width:650px;margin-top:15px;padding-bottom: 15px;">
		<table>
	<tr>
		<td class="td_head"  nowrap>活动名称：</td>
		<td class="td_body">
			<input type="text" id="activityName" name="geAddServiceActivity.activityName" value="<s:property value="geAddServiceActivity.activityName"/>" style="width:235px;" maxlength=30 >
			<input type="hidden" id="geActivityId" name="geActivityId" value="<s:property value="geAddServiceActivity.activityId"/>">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动内容介绍：</td>
		<td class="td_body" nowrap>
			<%//<textarea rows="8" cols="40" maxlength="810" id="activityContent" name="geAddServiceActivity.activityContent" onkeyup="textAreaMaxLen(this)>"></textarea>%>
			<textarea rows="8" cols="50" id="activityContent" name="geAddServiceActivity.activityContent"  onkeyup="count()" onkeypress="count()" onblur="count();" onChange="count();"><s:property value="geAddServiceActivity.activityContent"/></textarea>
		</td>
	</tr>
<!--	<tr>-->
<!--		<td class="td_head" nowrap>活动地域：</td>-->
<!--		<td class="td_body" >-->
<!--			<input type="text" id="authorityDepartmentManager"  value="<s:property value="geAddServiceActivity.deptName"/>" style="width:235px;" maxlength=80 ondblclick="alterTree();" readonly="readonly"><font color="black">(双击域选择)</font>-->
<!--			<input type="hidden" value="<s:property value="geAddServiceActivity.deptID"/>" name="geAddServiceActivity.deptID" id="authorityid">-->
<!--		</td>-->
<!--	</tr>		-->
	<!-- 业务领域开始 -->
		<tr style="display: none;">
		<td class="td_head" nowrap>业务：</td>
		<td class="td_body" >
		<s:iterator value="geCodeBusinessAreaList" var="geCodeBusinessArea">
	
		<c:choose>
		<c:when test="${businessAreaDept=='1'}"><input type="Radio" id="actDeptId1" <c:if test="${(geCodeBusinessArea.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='2'}"><input type="Radio" id="actDeptId2" <c:if test="${(geCodeBusinessArea.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='3'}"><input type="Radio" id="actDeptId3" <c:if test="${(geCodeBusinessArea.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='4'}"><input type="Radio" id="actDeptId4" <c:if test="${(geCodeBusinessArea.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;</c:when>
		<c:otherwise><input type="Radio" id="actDeptId5" checked name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;</c:otherwise>
		</c:choose>
		
		</s:iterator>
		
		</td>
	</tr>
	
	<%//所有的代码的存储都是以deptId为准 %>
	
	<tr style="display: none;">
		<td class="td_head" nowrap>活动地域：</td>
		<td class="td_body" >
		    <div style="float:left">
			<select id="authorityid1" name="province" onchange="selectCity();" >
				<option value="">--请选择--</option>
			</select>
			</div>
			<div style="float:left;display: none;" id="suv">
			<select id="cityId1"  name="deptID" style="display: none;">
				<option value="">--请选择--</option>
			</select>
			</div>
		</td>
	</tr>
		<!--业务领域结束  -->
	<tr>
		<td class="td_head" nowrap>活动起始时间：</td>
		<td class="td_body" >
			<input type="text" class="Wdate" id="activityStartDateId" name="geAddServiceActivity.activityStartDate" value="<s:date name="geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/>" style="width:235px;" maxlength=80 onfocus="startDate();">
		</td>
	</tr>	
	
	<tr>
		<td class="td_head" nowrap>活动结束时间：</td>
		<td class="td_body" >
			<input type="text" class="Wdate" id="activityEndDateId" name="geAddServiceActivity.activityEndDate" value="<s:date name="geAddServiceActivity.activityEndDate" format="yyyy-MM-dd"/>" style="width:235px;" maxlength=80 onfocus="WdatePicker({minDate:'#F{$dp.$D(\'activityStartDateId\')}'})">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>是否有效：</td>
		<td class="td_body" >
			<select style="width:100px;" name="geAddServiceActivity.validInd">
				<option value="1" <c:if test="${geAddServiceActivity.validInd eq '1'}">selected</c:if>>有效</option>
				<option value="0" <c:if test="${geAddServiceActivity.validInd eq '0'}">selected</c:if>>无效</option>
			</select>
		</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
	<tr>
	<td class="td_body" colspan="3" >
		<div class="frmCreate_kuang" style="width:700px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header">修改产品图片<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style="padding-left:3px; padding-top:15px;">
	<table>
	<s:if test="geAddServiceActivity.geActivitiesPictures.size>0">
		<s:iterator value="geAddServiceActivity.geActivitiesPictures" var="geActivitiesPicture" status="i">
			<%//<s:if test="#geActivitiesPicture.nooryes=='yes'">%>
			<c:choose>
			<c:when test="${(i.index+1)<=3}">
			<tr name = "upload">
				<td class="td_head" nowrap></td>
				<td class="td_head" ><a href="#" title="图片使用位置" onclick="btnShowone('showImg${i.index+1}');" id="showImg${i.index+1}"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
				修改上传图片${i.index+1}：
					<input type="file" onfocus="addPreviewFace(this.id);" name="uploadPicture" id="uploadPicture${i.index+1}" style="width:235px;" maxlength=80 >
					跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[${i.index}].jumpUrl" value="<s:property value="#geActivitiesPicture.jumpUrl"/>" style="width:170px;"    maxlength="200">
				</td>
			</tr>
			<tr id="uploadPicture${i.index+1}TR" style="display: none;">
				<td class="td_head" nowrap>
				</td>
				<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片${i.index+1}预览：</div>
					<input type="hidden" value="<s:if test="#geActivitiesPicture.nooryes=='yes'">${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/></s:if>" id="hiddenImage${i.index+1}" name="hiddenImage">
					<div style="padding-left: 130px"><img  src="${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/>" width="200" height="100" id="uploadPicture${i.index+1}Preview"></div>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr name = "upload">
				<td class="td_head" nowrap></td>
				<td class="td_head" >
<!--				<a href="${ctx}/global/images/productSmallPicture${i.index+1}.jpg" title="图片使用位置" class="thickbox"></a>-->
				<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>
				修改上传图片${i.index+1}：
					<input type="file" onfocus="addPreviewFace(this.id);" name="uploadPicture"  id="uploadPicture${i.index+1}" style="width:235px;" maxlength=80 >
					跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[${i.index}].jumpUrl"   value="<s:property value="#geActivitiesPicture.jumpUrl"/>" style="width:170px;"    maxlength="200">
				</td>
			</tr>
			<tr id="uploadPicture${i.index+1}TR" style="display: none;">
				<td class="td_head" nowrap>
				</td>
				<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片${i.index+1}预览：</div>
					<input type="hidden" value="<s:if test="#geActivitiesPicture.nooryes=='yes'">${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/></s:if>" id="hiddenImage${i.index+1}" name="hiddenImage">
					<div style="padding-left: 130px"><img  src="${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/>" width="200" height="100" id="uploadPicture${i.index+1}Preview"></div>
				</td>
			</tr>
			</c:otherwise>
			</c:choose>
			<%//</s:if>%>
		</s:iterator>
	</s:if>
	
	<s:else>
		<tr>
<td>
<table>
	<tr>
		<td class="td_head" nowrap valign="top">
			</td>
		<td class="td_head" valign="top"><a href="#" title="图片使用位置" onclick="btnShowone('showImg1');" id="showImg1"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片1：
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
		<td class="td_head" ><a href="#" title="图片使用位置" onclick="btnShowone('showImg2');" id="showImg2"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片2：
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
		<td class="td_head" ><a href="#" title="图片使用位置" onclick="btnShowone('showImg3');" id="showImg3"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>上传图片3：
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
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[3].jumpUrl" style="width:170px;     maxlength="200">
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
			跳转URL：<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[4].jumpUrl" style="width:170px;    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture5TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>图片5预览：</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture5Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	</table>
</td>
		</tr>
	</s:else>
	
				</table>
			</div>
			</div>
	</td>
	</tr>	
	<tr>
<!--		<td class="td_head" nowrap rowspan="2"></td> 规则： -->
		<td class="td_body" colspan="3">
		<div class="frmCreate_kuang" style="width:700px; border: 1px #e3e3e3 solid;" >
		<div class="frmCreate_kuang_header">修改活动规则<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<table  id="tableInfo" width="100%" style="table-layout:fixed">
			<s:iterator value="geAddServiceActivity.geActivitiesRules" var="geActivitiesRules" status="status">
			<tr id="${status.index+1}">
				<td>
				
<!--				<div class="frmCreate_kuang_header"><span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>-->
				<div style="padding-left:3px; padding-top:5px;">
					<table border="0px red solid;" width="100%" style="border-collapse:collapse;" updateType="update">
					<tr><td colspan="4" height="30px"><span id="count" style="font-weight: bold;">规则${status.index+1}</span></td></tr>
					
					<tr><%//第1行 %>
					<td width="15%" style="text-align: right;">保费：</td>
					<td width="40%">
						<select onchange="showPremum(this)" name="premumOperator" id="premumOperator${status.index}" style="width:170px;">
							<option value="">--请选择--</option>
							<s:if test="geCodePremiumTypeList!=null" >
							<s:iterator value="geCodePremiumTypeList" var="geCodePremiumType">
								<option value="<s:property value="#geCodePremiumType.id.codeCode"/>" <s:if test="#geCodePremiumType.id.codeCode==#geActivitiesRules.premiumType">selected</s:if>>
									<s:property value="#geCodePremiumType.codeCName"/> 
								</option>
							</s:iterator>
							</s:if>
						</select>						
					</td>
					</tr>
					
					<tr <s:if test="#geActivitiesRules.premiumType==2||#geActivitiesRules.premiumType==3||#geActivitiesRules.premiumType==4||#geActivitiesRules.premiumType==5||#geActivitiesRules.premiumType==6">
						</s:if>
						<s:else>
						style="display: none;"
						</s:else>
					><%//第2行 %>
					<td width="10%" style="text-align: right;">保费的值：</td>
					<td width="40%">
						<s:if test="#geActivitiesRules.premiumType==2||#geActivitiesRules.premiumType==3||#geActivitiesRules.premiumType==4||#geActivitiesRules.premiumType==5">
							<input type="text" value="<s:property value="#geActivitiesRules.peremiumValue"/>"  name="premiumRange" id="premiumRange${status.index}"/>
						</s:if>
						
						<s:if test="#geActivitiesRules.premiumType==6">
							<input type="text" value="<s:property value="#geActivitiesRules.premiumRange1"/>"  name="premiumRange1" id="premiumRange1a${status.index}"/> <=保费的值<= <input type="text" value="<s:property value="#geActivitiesRules.premiumRange2"/>"  name="premiumRange2" id="premiumRange2a${status.index}"/>
						</s:if>
						
					</td>
					</tr>
					
					<tr>
					<td style="text-align: right;">活动方式：</td>
					<td width="400px">
						<select id="activityPattern${status.index}" name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
							<option>--请选择--</option>
							<s:if test="geCodeActivityPatternList!=null">
							<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
								<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>" <s:if test="#geCodeActivityPattern.id.codeCode==#geActivitiesRules.activityPattern">selected</s:if>>
									<s:property value="#geCodeActivityPattern.codeCName"/>
								</option>
							</s:iterator>
							</s:if>
						</select>
					</td>
					</tr>
					
					<tr <s:if test="#geActivitiesRules.activityPattern==1||#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3"></s:if>
						<s:else>style="display: none;"</s:else>
					>
					<td width="10%" style="text-align: right;">商品名称：</td>
					<td width="40%">
						<div class="frmCreate_kuang" style="width:250px;margin-top:10px; " id="kindDiv" >
						<div class="frmCreate_kuang_header">选择商品<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
						<div style="padding-top:15px;">
						<div><table id="productMoreCallBack" name="productMoreCallBack">
							<s:property value="#geActivitiesRules.itemName" escapeHtml="false"/>
						</table></div>
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
						<input type="hidden" readonly="readonly"  id="itemName${status.index}"  name="temp" value="<s:property value="#geActivitiesRules.itemName"/>" style="width:170px;">
						<input type="button"  id="itemName${status.index}" nameCount="${status.index+1}" name="itemName" value="选择商品"  onclick="getGeThirdParterInfoForUpdate(this,'<s:property value="#geActivitiesRules.itemID"/>');">
						<input type="hidden" id="itemID${status.index}" name="itemID" value="<s:property value="#geActivitiesRules.itemID"/>">
					</td>
					</tr>
					
					<%//打折类型%>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<td style="text-align: right;" colspan="1">打折类型：</td><%//折扣为 %>
					<td>
						<select name="discountType"  style="width:170px;" id="discountType${status.index}">
							<option value="">--请选择--</option>						
							<option value="01" <s:if test="#geActivitiesRules.discountType==01">selected</s:if>>打折率</option>
							<option value="02" <s:if test="#geActivitiesRules.discountType==02">selected</s:if>>打折价</option>
						</select>
					</td>
					</tr>
					
					<%//n的值 %>
					<tr <s:if test="#geActivitiesRules.activityPattern==1||#geActivitiesRules.activityPattern==5">style="display: none;"</s:if>>
					<s:if test="#geActivitiesRules.activityPattern==1||#geActivitiesRules.activityPattern==5">
						<td style="text-align: right;" class="dazhe"></td><%//折扣为 %>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==2">
						<td style="text-align: right;" class="dazhe">前N的值：</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==3">
						<td style="text-align: right;" class="dazhe">概率N%的值：</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==4">
						<td style="text-align: right;" class="dazhe">打折率(%)/打折价(元)：</td>
					</s:if>
					
					<td>
						<input type="text" id="nvalue${status.index}" name="nvalue" value="<s:property value="#geActivitiesRules.nvalue"/>" maxlength="10" style="width:170px;">
					</td>
					</tr>
					
					<%//折扣ID(寿险)%>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr style="display: none;">
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<td style="text-align: right;"><!-- 折扣ID(寿险)： --></td>
					<td>
						<!-- <input type="text" id="discountID${status.index}" name="discountID" value="<s:property value="#geActivitiesRules.discountID"/>" maxlength="200" style="width:170px;" onkeyup="value=value.replace(/[\W]/g,'')">-->
					</td>
					</tr>
					
					
					<%//打折因子描述 加了一行 %>
					<tr <s:if test="#geActivitiesRules.activityPattern==4"></s:if><s:else>style="display: none;"</s:else>><%//第6行 %>
					<td style="text-align: right;">打折因子描述：</td><%//折扣为 %>
					<td>
						<textarea rows="4" cols="56" id="discountRemarkText${status.index}" name="discountRemarkText" ><s:property value="#geActivitiesRules.discountRemarkText"/></textarea>
					</td>
					</tr>
					
					<tr <s:if test="#geActivitiesRules.activityPattern!=5">style="display: none;"</s:if>><%//第6行 %>
					<td style="text-align: right;vertical-align:text-top">加购产品：</td>
					<td >
					<div style="display: none;">
					<s:iterator value="#geActivitiesRules.geActivitiesShoppingProducts" var="geActivitiesShoppingProduct" status="shoppingi">
						<input type="hidden"   name="shoppingCoreProductCode" value="<s:property value="#geActivitiesShoppingProduct.eid"/>">
					</s:iterator>
					</div>
						<table border="0px red solid;" width="100%" style="border-collapse:collapse;" typeProduct="addShopping">
							<tr>
								<td colspan="2">
									<input type="button" value="增加加购产品" onclick="upaddShopping(this)"/>
								</td>
							</tr>
							<s:iterator value="#geActivitiesRules.geActivitiesShoppingProducts" var="geActivitiesShoppingProduct" status="shoppingi">
							<tr>
								<td>${shoppingi.index+1}、<s:property value="#geActivitiesShoppingProduct.productName"/>
									<input type="hidden" id="addShoppingProduct${status.index}${shoppingi.index}"  name="addShoppingProduct" value="<s:property value="#geActivitiesShoppingProduct.productName"/>"  >
										<input type="hidden"  typeProduct="addShoppingProductCode" name="geAddServiceActivity.geActivitiesRules[${status.index}].geActivitiesShoppingProducts[${shoppingi.index}].eid" value="<s:property value="#geActivitiesShoppingProduct.eid"/>">
								</td>
							</tr>
							</s:iterator>
						</table>
					</td>
					</tr>
					
					<tr>
					<td colspan="2" style="text-align:right;padding-right:60px">
						<s:if test="#request.type==null">
							<input type="button" value="删除" onclick="deleteTable(this)">
						</s:if>
						<s:else>
							<!-- <a href="${ctx}/workFlow/getProcessImg.do?procDefId=${marktingWrokFlow.task.processDefinitionId }&executionId=${marktingWrokFlow.task.executionId}" target="_blank">查看进度</a> -->
						</s:else>
					</td>
					</tr>
					
					</table>
					</div>
					
					</td>
			</s:iterator>
			</table></div>
		</td>
	</tr>
	<s:if test="#request.type==null">
		<tr>
			<td class="td_body" ><input type="button" value="增加活动规则" onclick="insertTableForUpdate();" ></td>
		</tr>
			
	<tr>
<!--		<td class="td_head" nowrap >产品名称：</td>-->
		<td class="td_body" colspan="3" >
		<div style="display: none;">
		 	<s:iterator value="geAddServiceActivity.geActivitiesProducts" var="geActivitiesProduct" status="count">
			<input type="hidden"   name="upDateProductCode" value="<s:property value="#geActivitiesProduct.eid"/>">

			</s:iterator>
		</div>
		 <div class="frmCreate_kuang" style="width:700px;">
		 <div class="frmCreate_kuang_header">修改产品<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		 <div style="padding-left:3px; padding-top:5px;">
		 <table id="productCallBack">
		 <s:if test="geAddServiceActivity.geActivitiesProducts!=null&&geAddServiceActivity.geActivitiesProducts!=undefined">
		 	<s:iterator value="geAddServiceActivity.geActivitiesProducts" var="geActivitiesProduct" status="count">
				<tr id="productType${count.index}" typeProduct="productNameTr" class="resetNode" ><!--productType加上编号-->
					<td>
						<s:if test="#count.index==0"><input type="hidden" id="businessAreaForCompare"></s:if>
						${count.index+1}、<s:property value="#geActivitiesProduct.productName"/><input type="hidden" tympeProduct="productNae" id="coreProductName${count.index}" name="coreProductName" value="<s:property value="#geActivitiesProduct.productName"/>">
						<input type="hidden" class="coreProductCode" name="geAddServiceActivity.geActivitiesProducts[${count.index}].eid" value="<s:property value="#geActivitiesProduct.eid"/>"/>
					</td>
					
						<td align='right' class='clazz'>
						<div id="activitNone" style="display: none;" class="disShowOrHide" ><span style="font-weight: bold;padding-left: 200px"> 折扣ID(选填)：</span>
						<input type="text" class="activityPatternDiscountID" id="activityDiscountID${count.index}" name="geAddServiceActivity.geActivitiesProducts[${count.index}].discountID" value="<s:property value="#geActivitiesProduct.discountID"/>" maxlength="100"  onkeyup="value=value.replace(/[\\\W]/g,'')" />
						</div>
						</td>
					
				</tr>
			</s:iterator>
			</s:if>
		 </table>
		</div>
		</div>
		<input type="button" value="修改产品" onclick="upProductTest();"></td>
	</tr>
		<tr height=25><td></td></tr> 
	<tr>
		<td colspan="2">
		<div style="padding-left: 200px">
			<table id="operatorTable">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/marketing/view.do?activityId=<s:property value='geAddServiceActivity.activityId'/>';">返回</td>
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap >修改</td>
					<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
				</tr>
			</table>
		</div></td>
	</tr>
	</s:if>
	<s:else>
		<tr>
			<td colspan=2>
				<table width=200 align="center">
					<input type="hidden" name="type" id="type" value=""/>
					<input type="hidden" name="taskID" value="${marktingWrokFlow.task.id }"/>
					<input type="hidden" name="workFlowID" value="${marktingWrokFlow.workFlowID}"/>
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="audit('1');" nowrap>通过 </td>
					<td width=5>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
	</s:else>
</table> 
<div id="message"></div>
<%//<input type="hidden" id="userType" name="geBlackList.userType" value="01">%>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
//弹出一棵树
function alterTree(){
	/*
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?checkType=2&authorityid=ROLE_B_AAGA&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
	*/
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/marketing/selectDeptAuthId.do?operation=marketing&checkType=2&authorityid=ROLE_B_AAGA&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

function doClear(){
	location.href=location.href;
	//document.getElementById("frmInput").reset();
}

function audit(type){
	$("#type").val(type);
	$("#frmInput")[0].submit();
}




function getCountCity(field){
	var nameCount = "";
	var tableObj = $(field).parent().parent().parent();
	tableObj.find("input").each(function(index, value){
		if(index=="2"){
			nameCount = $(value).attr("nameCount"); 
		}
	});
	return nameCount;
}
//起 始时间
function startDate(){
	WdatePicker({onpicked:function(){
		document.getElementById("activityEndDateId").focus();
		},minDate:'#F{$dp.$D(\'minDate\')}'})
		
}


function selectProduct(count){
	if($("#authorityDepartmentManager").val()==""){
		alert("请先选择  活动地域");	
	}else{
		//提练出业务领域
		var authorityid = $("#authorityid").val();
		//var businessArea = authorityid = authorityid.substring(0,1);
		//alert(${ctx}/business/marketingManage/create/selectProduct/index.jsp?deptId="+authorityid+"&count="+count+"&businessArea="+businessArea+"&coreProductCode="+ obj.value);
		window.open("${ctx}/business/marketingManage/create/selectProduct/index.jsp?deptId="+authorityid+"&count="+count+"&coreProductCode="+ obj.value, "查询产品", "top=100, left=100, width=900,height=600,toolbar=no");	
	}
}

//上传图片显示的东西
$(document).ready(function(){
	//pop提示信息
	var ids = ['uploadPicture1','uploadPicture2','uploadPicture3','uploadPicture4','uploadPicture5'];
//	var contents = ['上传图片1只能上传bmp,jpg,gif格式的图片！','上传图片2只能上传bmp,jpg,gif格式的图片！','上传图片3只能上传bmp,jpg,gif格式的图片！','上传图片4只能上传bmp,jpg,gif格式的图片！','上传图片5只能上传bmp,jpg,gif格式的图片！'];
	var contents = ['上传图片1只能上传bmp，jpg，gif格式的图片，长600px宽50px。','上传图片2只能上传bmp，jpg，gif格式的图片，长244px  ，宽220px。','上传图片3只能上传bmp，jpg，gif格式的图片，长250px ，宽30px。','上传图片4只能上传bmp，jpg，gif格式的图片。','上传图片5只能上传bmp，jpg，gif格式的图片。'];	
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
	ImagePreview.MODE = "filter";//让走simplle模式
	var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
	facePic.file.onchange = function(){
		facePic.preview();
		document.getElementById(obj+"TR").style.display="";
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
</script>
</html>