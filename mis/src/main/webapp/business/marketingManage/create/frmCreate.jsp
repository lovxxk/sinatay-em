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


<title>�½�Ӫ���</title>
<style type="text/css">
	.table_style_new{
	border-collapse:collapse;
	font-family:����;
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
�½�Ӫ���<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>

<%//���������������Ӷ�����ʱ��ʹ��start %>
<table  id="tableInfoHidden" width="100%" style="display: none;">
<tr>
<td>
		<table width="100%" style="border-collapse:collapse;">
		<tr style="border-top:1px solid #DBDBDB;"><td colspan="4"><span id="count" style="font-weight: bold;">����1</span></td></tr><%//��0�� %>
					
					
					<tr><%//��1�� %>
					<td width="20%" style="text-align: right;">���ѣ�</td>
					<td width="80%">	
						<select onchange="showPremum(this)" name="premumOperator" style="width:170px;">
							<option value="">--��ѡ��--</option>
							<s:if test="geCodePremiumTypeList!=null" >
							<s:iterator value="geCodePremiumTypeList" var="geCodePremiumType">
								<option value="<s:property value="#geCodePremiumType.id.codeCode"/>"><s:property value="#geCodePremiumType.codeCName"/></option>
							</s:iterator>
							</s:if>			
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��2�� %>
					<td width="20%" style="text-align: right;">���ѵ�ֵ��</td>
					<td width="80%">
					<%//js����д�� %>
					</td>
					</tr>
					
					
					
					
					
					<tr><%//��3�� %>
					<td style="text-align: right;">���ʽ��</td>
					<td width="400px">
						<select  name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
							<option value="">--��ѡ��--</option>
							<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
									<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>">
										<s:property value="#geCodeActivityPattern.codeCName"/>
									</option>
							</s:iterator>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��4�� %>
					<td width="20%" style="text-align: right;">��Ʒ���ƣ�</td>
					<td width="80%">
					<div class="frmCreate_kuang" style="width:250px;margin-top:10px; " >
					<div class="frmCreate_kuang_header">ѡ����Ʒ<span style="color:#FF9000;font-weight:bold;"></span></div>
					<div style="padding-top:15px;">
					<div><table id="productMoreCallBack" name="productMoreCallBack"></table></div>
					<div class="frmCreate_kuang" style="width:250px;">
					<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
						<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
						<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">չ���鿴����</a></span>
						<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
						<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">����</a></span>
						<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
					</div>
					<div id="productInfo" style="padding-top:15px;clear:both;display: none">
					<div><table id="productMoreCallBacktwo"></table></div>
					</div></div>
					</div></div>
				<input type="hidden" name ="temp"  readonly="readonly" value="00">
					<input type="button" nameCount="1" value="ѡ����Ʒ" name="itemName" onclick="getGeThirdParterInfo(this);" >
						<input type="hidden" value="" name="itemID" id="itemID0">
					</td>
					</tr>
					
					<tr style="display: none;"><%//��5��  ��������%>
					<td style="text-align: right;">�������ͣ�</td><%//�ۿ�Ϊ %>
					<td>
						<select name="discountType"  style="width:170px;">
							<option value="">--��ѡ��--</option>						
							<option value="01">������</option>
							<option value="02">���ۼ�</option>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��6�� n��ֵ  %>
					<td style="text-align: right;" class="dazhe">n��ֵ��</td><%//�ۿ�Ϊ %>
					<td>
						<input type="text" name="nvalue" style="width:170px;" maxlength="10">
					</td>
					</tr>
				
					<tr style="display: none;"><%//��7��  �ۿ�ID%>
					<td style="text-align: right;"></td><%//�ۿ�Ϊ�ۿ�ID(����)�� %>
					<td>
<!--						<input type="text"  name="discountID" style="width:170px;"  onkeyup="value=value.replace(/[\W]/g,'')" maxlength="200">-->
						
					</td>
					</tr>
					
					<%//������������ ����һ�� %>
					<tr style="display: none;"><%//��8�� %>
					<td style="text-align: right;">��������������</td><%//�ۿ�Ϊ %>
					<td>
						<textarea rows="4" cols="60" name="discountRemarkText" ></textarea>
					</td>
					</tr>
					<tr style="display: none;"><%//��9�� %>
					<td style="text-align: right;vertical-align:top">�ӹ���Ʒ��</td>
					<td >
						<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
							<tr>
								<td colspan="2">
									<input type="button" value="���Ӽӹ���Ʒ" onclick="addShopping(this)"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right;padding-right:60px">
						<input type="button" value='ɾ��' onclick="deleteTable(this)">
					</td>
				</tr>
						</table>
					</td>
				</tr>

		</table>

<%//���������������Ӷ�����ʱ�� ʹ�� ���Ӳ�Ʒ %>
<table style="display: none;">
<tbody id="tableProductHidden">
<tr typeProduct="productNameTr">
		<td >
<!--			<input type="text"  name="coreProductName" style="width:235px;" maxlength=80  readonly="readonly">(˫����ѡ��)-->
<!--			<input type="text"  name="coreProductCode"><%//ʵ���Ͼ���EID %>-->
<!--			<input type="button" value="ɾ������" onclick="deleteOnself(this);">-->
		</td> 
</tr>
</tbody>
</table>

<%//��������������ڼӹ���Ʒ�� %>
<table style="display: none">
<tbody id="tableAddShoppingHidden">
<tr>
	<td>
		<input type='button' value='���Ӽӹ���Ʒ' onclick='addShopping(this)'/>		
		<div><table name="addshoppingProductCallBack" typeProduct="addShopping"></table></div>

	</td>
<!--	<td>-->
<!--        <input type='button' value='���Ӳ�Ʒ' onclick='addShopping(this)'/>		-->
<!--	</td>-->
</tr>
</tbody>
</table>

<form action="${ctx}/marketing/addAddGeAddServiceActivityAndRule.do" id="frmInput" method="post" target="myFrame" enctype="multipart/form-data">
<div id="BgDiv" ></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;cursor:pointer;">��&nbsp;��</a></h2>
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
		<td class="td_head"  nowrap>����ƣ�</td>
		<td class="td_body" nowrap>
			<input type="text"  id="activityName" name="geAddServiceActivity.activityName" style="width:235px;" onkeyup="countActivityName()" onkeypress="countActivityName()" onblur="countActivityName();" onChange="countActivityName()" >
		</td>
	</tr>
	<tr height="155x">
		<td class="td_head" nowrap valign="top">����ݽ��ܣ�</td>
		<td class="td_body" nowrap >
				<textarea rows="8" cols="50" id="geAddServiceActivity.activityContent" style="margin-bottom:2px;width:365px;height: 145px" name="geAddServiceActivity.activityContent"  onkeyup="count()" onkeypress="count()" onblur="count();" onChange="count();"></textarea>
		</td>
	</tr>
	<tr style="display: none;">
		<td class="td_head" nowrap>ҵ��</td>
		<td class="td_body" >
		<s:iterator value="geCodeBusinessAreaList" var="geCodeBusinessArea">
		    
			<input type="Radio"  style=" border: 0px;"name="BusinessArea" value="<s:property value="#geCodeBusinessArea.id.codeCode"/>" onclick="selectProvince();"><s:property value="#geCodeBusinessArea.codeCName"/>&nbsp;
		
		</s:iterator>
		
		</td>
	</tr>
	
	<%//���еĴ���Ĵ洢������deptIdΪ׼ %>
	<tr style="display: none;">
		<td class="td_head" nowrap>�����</td>
		<td class="td_body" >
		<div style="float:left">
			<select id="authorityid1" name="_province" onchange="selectCity();" style="width: 135px">
				<option value="1" selected>--��ѡ��--</option>
			</select>
			</div>
			<div style="float:left;display: none;" id="suv">
			<select id="cityId1" name="_deptID" style="display: none;">
				<option value="1" selected>--��ѡ��--</option>
			</select>
			</div>
		</td>
	</tr>
	<input type="hidden" id="authorityid" name="geAddServiceActivity.province" value="1"/>
	<input type="hidden" id="cityId" name="geAddServiceActivity.deptID" value="1"/>
	
	
	
	<tr>
		<td class="td_head" nowrap>���ʼʱ�䣺</td>
		<td class="td_body" >
		<input type="text" id="activityStartDate" name="geAddServiceActivity.activityStartDate" class="Wdate" style="width:235px;" maxlength=80 readonly="readonly" onfocus="startDate();">
		</td>
	</tr>
	
	<tr>
		<td class="td_head" nowrap>�����ʱ�䣺</td>
		<td class="td_body" >
		<input type="text" id="activityEndDate" name="geAddServiceActivity.activityEndDate" class="Wdate" style="width:235px;" maxlength=80  readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'activityStartDate\')}'})">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ƿ���Ч��</td>
		<td class="td_body" >
			<select style="width:100px;" name="geAddServiceActivity.validInd">
				<option value="1" selected>��Ч</option>
				<option value="0">��Ч</option>
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
		<td class="td_head" nowrap >��Ʒ���ƣ�</td>
		<td class="td_body" >
			<table  id="productModel" width="100%">
				<tr id="productType" typeProduct="productNameTr">
					<td >
					    <input type="hidden" id="businessAreaForCompare">
					    <%/*
						<input type="text" id="coreProductName0" name="coreProductName"  style="width:235px;" maxlength=80 ondblclick="selectProduct('1');" readonly="readonly">(˫����ѡ��)
						<input type="hidden"  name="coreProductCode">
						*/%>
						<input type="button" value="���Ӳ�Ʒ" onclick="addProduct();">
					</td> 
				</tr>	
			</table>
		</td>
	</tr>
	-->
	<tr>
	<td colspan="3" style="padding-left: 70px;">
		<div class="frmCreate_kuang" style="margin-left:10px;width:720px;margin-top:10px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header">��Ӳ�ƷͼƬ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style="padding-left:3px; padding-top:15px;">
	<table>
	<tr>
		<td class="td_head" nowrap valign="top">
			</td>
		<td class="td_head" valign="top" style="cursor:pointer;"><a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('showImg1');" id="showImg1"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ1��
			<input type="file" name="uploadPicture" id="uploadPicture1"  onblur="addPreviewFace(this.id);"  style="width:235px;" maxlength=80 >
			��תURL��<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[0].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture1TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>ͼƬ1Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture1Preview"  width="200" height="100"/></div>
		</td> 
	</tr>		
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="ͼƬʹ��λ��" style="cursor:pointer;" onclick="btnShowone('showImg2');" id="showImg2"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ2��
			<input type="file" name="uploadPicture" id="uploadPicture2" onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80>
			��תURL��<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[1].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	
	<tr style="display: none;" id="uploadPicture2TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>ͼƬ2Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture2Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	
	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" ><a href="#" title="ͼƬʹ��λ��" style="cursor:pointer;" onclick="btnShowone('showImg3');" id="showImg3"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>�ϴ�ͼƬ3��
			<input type="file" name="uploadPicture" id="uploadPicture3"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			��תURL��<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[2].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture3TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>ͼƬ3Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture3Preview"  width="200" height="100"/></div>
		</td> 
	</tr>

	<tr>
		<td class="td_head" nowrap valign="top">
		</td>
		<td class="td_head" >
<!--		<a href="${ctx}/global/images/productSmallPictureOne.jpg" title="ͼƬʹ��λ��" class="thickbox"></a> background:#f3f3f3"-->
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>�ϴ�ͼƬ4��
			<input type="file" name="uploadPicture" id="uploadPicture4"  onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			��תURL��<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[3].jumpUrl" style="width:170px;"     maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture4TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>ͼƬ4Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture4Preview"  width="200" height="100" /></div>
		</td> 
	</tr>

	<tr>
		<td class="td_head" nowrap valign="top" >
		</td>
		<td class="td_head" >
<!--		<a href="${ctx}/global/images/productSmallPictureOne.jpg" title="ͼƬʹ��λ��" class="thickbox"></a>-->
		<img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/>�ϴ�ͼƬ5��
			<input type="file" name="uploadPicture" id="uploadPicture5"   onfocus="addPreviewFace(this.id);" style="width:235px;" maxlength=80 >
			��תURL��<input type="text" id="jumpUrl" name="geAddServiceActivity.geActivitiesPictures[4].jumpUrl" style="width:170px;"    maxlength="200">
		</td> 
	</tr>
	<tr style="display: none;" id="uploadPicture5TR">
		<td class="td_head" nowrap valign="top">
			
		<td class="td_body" ><div style="top: auto;padding-right: 480px" class="td_head" nowrap>ͼƬ5Ԥ����</div>
			<div style="padding-left: 100px"><img src="${ctx}/global/images/productMiddlePicture.jpg" id="uploadPicture5Preview"  width="200" height="100" /></div>
		</td> 
	</tr>
	</table>
	</div>
	</div>
	<div class="frmCreate_kuang" style="margin-left:10px;width:720px;margin-top:10px;padding-bottom: 15px;">
		<div class="frmCreate_kuang_header" style="width:718px">��ӻ����<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
		<div style=" padding-top:10px;" ><!--id="addRuleDiv"   �ӹ����-->  
	<table width="700px">
	<tr>
		<td class="td_body" >
			<table  id="tableInfo" width="100%">
			<tr id="0">
				<td >
					<table width="710px" style="border-collapse:collapse;">
					<tr><td colspan="4"><span id="count" style="font-weight: bold;">����1</span></td></tr><%//��0�� %>
					
					
					<tr><%//��1�� %>
					<td width="20%" style="text-align: right;">���ѣ�</td>
					<td width="80%">	
						<select onchange="showPremum(this)" name="premumOperator" id="premumOperator0" style="width:170px;">
							<option value="">--��ѡ��--</option>
							<s:if test="geCodePremiumTypeList!=null" >
							<s:iterator value="geCodePremiumTypeList" var="geCodePremiumType">
								<option value="<s:property value="#geCodePremiumType.id.codeCode"/>"><s:property value="#geCodePremiumType.codeCName"/></option>
							</s:iterator>
							</s:if>			
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��2�� %>
					<td width="8%" style="text-align: right;">���ѵ�ֵ��</td>
					<td width="92%">
					<%//js����д�� %>
					</td>
					</tr>
					
					
					
					
					
					<tr><%//��3�� %>
					<td style="text-align: right;">���ʽ��</td>
					<td width="400px">
						<select id="activityPattern0" name="activityPattern" style="width:170px;" onchange="setNValueDisplay(this);" onblur="valid();">
							<option value="">--��ѡ��--</option>
							<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
									<option value="<s:property value="#geCodeActivityPattern.id.codeCode"/>">
										<s:property value="#geCodeActivityPattern.codeCName"/>
									</option>
							</s:iterator>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��4�� %>
					<td width="10%" style="text-align: right;">��Ʒ���ƣ�</td>
					<td width="50%">
					<div class="frmCreate_kuang" style="width:250px;margin-top:10px; " id="kindDiv" >
					<div class="frmCreate_kuang_header">ѡ����Ʒ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
					<div style="padding-top:15px;">
					<div><table id="productMoreCallBack" name="productMoreCallBack"></table></div>
					<div class="frmCreate_kuang" style="width:250px;">
					<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
						<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
						<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">չ���鿴����</a></span>
						<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
						<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">����</a></span>
						<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
					</div>
					<div id="productInfo" style="padding-top:15px;clear:both;display: none">
					<div><table id="productMoreCallBacktwo"></table></div>
					</div></div>
					</div></div>
					<input type="hidden"  name = "temp" id="itemName0"  readonly="readonly" value="00">
					<input type="button" nameCount="1" name="itemName"  value="ѡ����Ʒ" onclick="getGeThirdParterInfo(this);" id="itemName0" >
					<input type="hidden" value="" name="itemID" id="itemID0">
					</td>
					</tr>
					
					<tr style="display: none;"><%//��5��  ��������%>
					<td style="text-align: right;">�������ͣ�</td><%//�ۿ�Ϊ %>
					<td>
						<select name="discountType" id="discountType0" style="width:170px;">
							<option value="">--��ѡ��--</option>						
							<option value="01">������</option>
							<option value="02">���ۼ�</option>
						</select>
					</td>
					</tr>
					
					<tr style="display: none;"><%//��6�� n��ֵ  %>
					<td style="text-align: right;" class="dazhe">������/���ۼ�n��ֵ(%)��</td><%//�ۿ�Ϊ %>
					<td>
						<input type="text" id="nvalue0" name="nvalue" maxlength="10" style="width:170px;">
					</td>
					</tr>
				
					<tr style="display: none;"><%//��7��  �ۿ�ID%>
					<td style="text-align: right;"></td><%//�ۿ�Ϊ �ۿ�ID(����)��%>
					<td>
<!--						<input type="text" id="discountID0" name="discountID" style="width:170px;"   onkeyup="value=value.replace(/[\W]/g,'')" maxlength="200">-->
					</td>
					</tr>
					
					<%//������������ ����һ�� %>
					<tr style="display: none;"><%//��8�� %>
					<td style="text-align: right;">��������������</td><%//�ۿ�Ϊ %>
					<td>
						<textarea rows="4" cols="60" name="discountRemarkText" id="discountRemarkText0"></textarea>
					</td>
					</tr>
					<tr style="display: none;"><%//��9�� %>
					<td style="text-align: right;vertical-align:text-top">�ӹ���Ʒ��</td>
					<td >
						<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
							<tr>
								<td colspan="2">
									<input type="button" value="���Ӽӹ���Ʒ" onClick="addShopping(this)"/>
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
	
	 <tr style="display:none;"><%//��9�� %>
		<td style="text-align: right;vertical-align:top">�ӹ���Ʒ��</td>
		<td >
			<table border="0px red solid;" width="100%" style="border-collapse:collapse;">
				<tr>
					<td colspan="2">���Ӽӹ���Ʒ
						<input type="button" value="���Ӽӹ���Ʒ" onclick="addShopping(this)"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="td_body" style="padding-left:80px;" ><input type="button" value="���ӻ����" onclick="insertTable();" ></td>
	</tr>
	<% /*���Ӳ�Ʒ*/ %>
	<tr>
<!--		<td  class="td_body" ></td>-->
		<td colspan="2">  
			<div class="frmCreate_kuang" style="width:700px;margin-top:10px;">
			<div class="frmCreate_kuang_header">���ӵĲ�Ʒ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-top:15px;">
			<div><table id="productCallBack"></table></div>
			<div class="frmCreate_kuang" style="width:700px;" id="showOrhideInfo">
			<div class="frmCreate_kuang_header" id="showInfo" style="display:none;">
				<span style="float:left;"><img id ="showPicture" src="${ctx}/global/images/switchV_R.jpg" style="padding-top:3px;"></span>
				<span style="float:left;padding-left:3px;"><a href="" id="showText" onclick="showInfo();return false;" style="color:#84A4D1">չ���鿴����</a></span>
				<span style="float:left;"><img style="display:none;padding-top:3px;"  id="hidePicture" src="${ctx}/global/images/prompt_inquiry_pic1.jpg""></span>
				<span style="float:left;width:150px;padding-left:3px;"><a href="" style="display:none;color:#84A4D1;" id = "hideText" onclick="hideInfo();return false;">����</a></span>
				<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span>
			</div>
			<div id="productInfo" style="padding-top:15px;clear:both;display: none">
			<div><table id="productCallBacktwo"></table></div>
			</div></div>
			</div></div>
			<div style="margin-left:80px;padding-top: 10px;text-align: left;"><input type="button" value="���Ӳ�Ʒ" onclick="addProductTest();" ></div>
			
		</td>
		
	</tr>
	<tr height=25><td></td></tr> 
	<tr height="60px;">
		<td colspan="2" align="center">
		<table>
			<tr>
			    <input type="hidden" name="functionTypeString" value="serviceADD"/>
				<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>����</td>
				<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>����</td>
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
//����һ�����������Ĳ˵�
function deptAuthIdQuery(){
	/*
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;//value='--ȫ��--'
		//
		alert("authorityDepartmentManager="+authorityDepartmentManager);
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_OORD_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
		*/
		//alert();
	//window.open('${ctx}/marketing/selectDeptAuthId.do?checkType=1&authorityid=ROLE_B_AAGA&type=2&receivedObj=3110000&receivedObjName=--ȫ��--&deptIdCount=0','����' ,'top=100, left=500, width=400,height=500,toolbar=no');
	window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","��ѯ��Ӧ��" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
function selectProduct(count){
	
	if($("#authorityDepartmentManager").val()==""){
		alert("����ѡ��  �����");	
	}else{
		//������ҵ������
		var authorityid = $("#authorityid").val();
		window.open("${ctx}/business/marketingManage/create/selectProduct/index.jsp?count="+count+"&deptId="+authorityid+"&coreProductCode="+ obj.value, "��ѯ��Ʒ", "top=100, left=100, width=900,height=600,toolbar=no");	
	}
	
}


//չʾ�����Ʒ��Ϣ
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
//��������Ʒ��Ϣ
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
//�� ʼʱ��
function startDate(){
	WdatePicker({onpicked:function(){
		document.getElementById("activityEndDate").focus();
		},minDate:'#F{$dp.$D(\'minDate\')}'})
		
}
function doClear(){
	document.getElementById("frmInput").reset();
	//���е�ͼƬԤ�������
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
	var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "ֻ���������ֻ���ĸ");
	tt.vf.req.add("geAddServiceActivity.activityName");//�����
	tt.vf.req.add("geAddServiceActivity.activityStartDate");//���ʼʱ��
	tt.vf.req.add("geAddServiceActivity.activityEndDate");//�����ʱ��s
	tt.vf.req.add("geAddServiceActivity.activityContent");////����ݽ���
// 	tt.vf.req.add("geAddServiceActivity.province");////�����
// 	tt.vf.req.add("geAddServiceActivity.deptID");////�����
	tt.vf.req.addId("premumOperator0");////����
	tt.vf.req.addId("activityPattern0");//////���ʽ
	/*if($("input[name='nvalue']").length>0){
		for(var i=0;i<$("input[name='nvalue']").length;i++){
			ttvf.addId("nvalue"+i);
		}
	}*/
	new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");//���ʽ��������


	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['˵�����½�Ӫ������ò�Ʒ<br/>ʹ����Ⱥ��Ӫ���������Ա��<br/>������������Ҫ���ӻ���򣬻��Ʒ��<br/>ע�������������̣�������˵�� ��'];
		
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

//�ϴ�ͼƬ��ʾ�Ķ���
$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	//var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "ֻ���������ֻ���ĸ");
	//tt.vf.req.add("geAddServiceActivity.activityName");//�����
	//tt.vf.req.add("geAddServiceActivity.activityStartDate");//���ʼʱ��
	//tt.vf.req.add("geAddServiceActivity.activityEndDate");//�����ʱ��s
	///tt.vf.req.add("geAddServiceActivity.activityContent");////����ݽ���
	//tt.vf.req.add("geAddServiceActivity.province");////�����
	//tt.vf.req.add("premumOperator");////����
	//tt.vf.req.add("activityPattern");//////���ʽ
	//tt.vf.req.add("nvalue");//n��ֵ
	//tt.vf.req.add("discountID");//����ID ����
	//tt.vf.req.add("geAddServiceActivity.activityEndDate");//��Ʒ����
	new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");
	//pop��ʾ��Ϣ
	var ids = ['uploadPicture1','uploadPicture2','uploadPicture3','uploadPicture4','uploadPicture5'];
	var contents = ['�ϴ�ͼƬ1ֻ���ϴ�bmp��jpg��gif��ʽ��ͼƬ����600px����50px��','�ϴ�ͼƬ2ֻ���ϴ�bmp��jpg��gif��ʽ��ͼƬ����240px����330px��','�ϴ�ͼƬ3ֻ���ϴ�bmp��jpg��gif��ʽ��ͼƬ����250px ����30px��','�ϴ�ͼƬ4ֻ���ϴ�bmp��jpg��gif��ʽ��ͼƬ��','�ϴ�ͼƬ5ֻ���ϴ�bmp��jpg��gif��ʽ��ͼƬ��'];
		
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
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
    	}
//pop��ʾ��Ϣ����
});


function addPreviewFace(obj){
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
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("�������500����");
	    	return false;
	    }

}
//ͼƬʹ��λ�ò���
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
		    	  $("#h2btn").append("<span id=\"spanProcessRole\" style=cursor:pointer>����˵��</span>");
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
</script>
</html>