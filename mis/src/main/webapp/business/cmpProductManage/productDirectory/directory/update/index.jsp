<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js//imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>电子商务后台管理系统-编辑产品目录基本信息</title>
<style type="text/css">
	td {
		vertical-align:top;
	}
	#productDetail tr td{
		height:185px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
	}
	
	#addProductDetail, #addPictureBox {
		width:100%;
		margin-top:15px;
		text-align:left;
	}
	
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
				编辑产品目录基本信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div style="padding-top:15px;">

<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">关&nbsp;闭</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>

<center>
	<form id="frmInput" action="${ctx}/productDirectory/updateGeDirectory.do" method="post"  enctype="multipart/form-data" target="myFrame">
	<table  id="geFunctionSwitchTable" width="650px">
		<tr>
			<td class="td_head" width="140px">电子商务产品ID：</td>
			<td class="td_body" >${geDirectory.eid}</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">是否网销：</td>
			<td class="td_body" >
				<select id="isNetSale" name="geDirectory.isNetSale" style="width:170px;">
					<option value="">--请选择--</option>
					<option value="01" ${geDirectory.isNetSale=="01"?"selected":""}>是</option>
					<option value="02" ${geDirectory.isNetSale=="02"?"selected":""}>否</option>
				</select>
			</td>
		</tr>	
		<tr>
			<td class="td_head" width="140px">产品代码/险种代码：</td>
			<td class="td_body" >
				<input id="coreProductCode" name="geDirectory.coreProductCode" type="text" style="width:170px;" value="${geDirectory.coreProductCode}" maxlength="50"  />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">产品名称：</td>
			<td class="td_body" >
				<input id="productName" name="geDirectory.productName" type="text" style="width:400px;" value="${geDirectory.productName}" maxlength="50" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">产品简称：</td>
			<td class="td_body" >
				<input id="coreProductSimpleName" name="geDirectory.coreProductSimpleName" type="text" style="width:400px;" value="${geDirectory.coreProductSimpleName}" maxlength="100" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">业务领域：</td>
			<td class="td_body" >
				<input type="hidden" id="businessArea" name="geDirectory.businessArea" maxlength="30" value="${geDirectory.businessArea}" style="width: 170px;"/>
				<select id="businessAreaSelect" name="businessAreaSelect" onchange="businessAreaSelectChange(this)" style="width:170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<option value="${GeCode_businessArea.id.codeCode}" ${geDirectory.businessArea == GeCode_businessArea.id.codeCode ?"selected":""}>${GeCode_businessArea.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">产品试算代码：</td>
			<td class="td_body" ><input type="text" id="productQuoteCode" name="geDirectory.productQuoteCode" maxlength="50" value="${geDirectory.productQuoteCode}"  style="width: 170px;" /></td>
		</tr>
		<tr>
			<td class="td_head" width="140px">销售渠道：</td>
			<td class="td_body" >
				<select id="saleChannel" name="geDirectory.saleChannel" style="width:170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<option value="${GeCode_saleChannel.id.codeCode}" ${geDirectory.saleChannel == GeCode_saleChannel.id.codeCode ?"selected":""}>${GeCode_saleChannel.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>	
		<tr>
			<td class="td_head" width="140px">承保方式：</td>
			<td class="td_body" >
				<select id="acceptInsurance" name="geDirectory.acceptInsurance" style="width:170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
						<option value="${GeCode_acceptInsurance.id.codeCode}" ${geDirectory.acceptInsurance == GeCode_acceptInsurance.id.codeCode ?"selected":""}>${GeCode_acceptInsurance.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">产品栏目：</td>
			<td class="td_body" >
				<select id="productSection" name="geDirectory.productSection" onchange="" style="width: 170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
						<option value="${GeCode_productSection.id.codeCode}" ${geDirectory.productSection == GeCode_productSection.id.codeCode ?"selected":""}>${GeCode_productSection.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">标的类型 ：</td>
			<td class="td_body" >
				<select id="itemType" name="geDirectory.itemType" style="width:170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
						<option value="${GeCode_itemType.id.codeCode}" ${geDirectory.itemType == GeCode_itemType.id.codeCode ?"selected":""}>${GeCode_itemType.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">发布日期：</td>
			<td class="td_body" >
				<input type="text" id="publishDate" class="Wdate" onclick="publishDateImgClick()" name="geDirectory.publishDate" maxlength="30" value="${geDirectory.publishDate}" style="width: 170px;" readonly/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">停售日期：</td>
			<td class="td_body" >
				<input type="text" id="stopDate" class="Wdate" onclick="stopDateImgClick()" name="geDirectory.stopDate" maxlength="30" value="${geDirectory.stopDate}" style="width: 170px;" readonly/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">险种期限：</td>
			<td class="td_body" >
				<select id="riskLimit" name="geDirectory.riskLimit" style="width:170px;">
					<option value="">--请选择--</option>
					<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
						<option value="${GeCode_riskLimit.id.codeCode}" ${geDirectory.riskLimit == GeCode_riskLimit.id.codeCode ?"selected":""}>${GeCode_riskLimit.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">产品推荐评分：</td>
			<td class="td_body" >
				<select id="productRecommend" name="geDirectory.productRecommend" style="width:170px;">
					<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
						<option value="${GeCode_productRecommend.id.codeCode}" ${geDirectory.productRecommend == GeCode_productRecommend.id.codeCode ?"selected":""}>${GeCode_productRecommend.id.codeCode == '00'?'--请选择--':(GeCode_productRecommend.codeCName)}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div class="frmCreate_kuang" id="addPictureBox">
					<div class="frmCreate_kuang_header">添加产品图片</div>
					<div style="text-align:left;padding-left:10px;padding-top:15px;">
						<table>
							<tr>
								<td class="td_head" width="120px">
									<a href="#" title="图片使用位置" onclick="btnShowone('productSmallPictureOne');" id="productSmallPictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">小图片1：</span>
								</td>
								<td class="td_body">
									<s:file id="smallPictureOne" name="smallPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.smallPictureOne != null}">
											<img id="smallPictureOnePreview" src="${ctx}/${geDirectory.smallPictureOne}" border="0" height="61" width="61"/>
										</c:when>
										<c:otherwise>
											<img id="smallPictureOnePreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">小图片2：</td>
								<td class="td_body">
									<s:file id="smallPictureTwo" name="smallPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.smallPictureTwo != null}">
											<img id="smallPictureTwoPreview" src="${ctx}/${geDirectory.smallPictureTwo}" border="0" height="61" width="61"/>
										</c:when>
										<c:otherwise>
											<img id="smallPictureTwoPreview" src="${ctx}/global/images/productMiddlePicture.jpg" border="0" height="61" width="61"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">
									<a href="#" title="图片使用位置" onclick="btnShowone('productMiddlePictureOne');" id="productMiddlePictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								
<span style="line-height:23px;">中图片1：</span>
								</td>
								<td class="td_body">
									<s:file id="middlePictureOne" name="middlePictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.middlePictureOne != null}">
											<img id="middlePictureOnePreview" src="${ctx}/${geDirectory.middlePictureOne}" border="0"/>
										</c:when>
										<c:otherwise>
											<img id="middlePictureOnePreview" src="${ctx}/global/images/productMiddlePicture.jpg"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">中图片2：</td>
								<td class="td_body">
									<s:file id="middlePictureTwo" name="middlePictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.middlePictureTwo != null}">
											<img id="middlePictureTwoPreview" src="${ctx}/${geDirectory.middlePictureTwo}" border="0"/>
										</c:when>
										<c:otherwise>
											<img id="middlePictureTwoPreview" src="${ctx}/global/images/productMiddlePicture.jpg"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">大图片1：</td>
								<td class="td_body">
									<s:file id="bigPictureOne" name="bigPictureOne" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.bigPictureOne != null}">
											<img id="bigPictureOnePreview" src="${ctx}/${geDirectory.bigPictureOne}" border="0"/>
										</c:when>
										<c:otherwise>
											<img id="bigPictureOnePreview" src="${ctx}/global/images/productMiddlePicture.jpg"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">大图片2：</td>
								<td class="td_body">
									<s:file id="bigPictureTwo" name="bigPictureTwo" onfocus="addPreviewFace(this.id);"></s:file><br/>
									<c:choose>
										<c:when test="${geDirectory.bigPictureTwo != null}">
											<img id="bigPictureTwoPreview" src="${ctx}/${geDirectory.bigPictureTwo}" border="0"/>
										</c:when>
										<c:otherwise>
											<img id="bigPictureTwoPreview" src="${ctx}/global/images/productMiddlePicture.jpg"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div class="frmCreate_kuang" id="addProductDetail">
					<div class="frmCreate_kuang_header">添加产品详细</div>
					<div style="padding-left:10px;padding-top:15px;">
						<table id="productDetail">
							<tr>
								<td class="td_head" width="120px">产品简介：</td>
								<td class="td_body" width="460px">
									<textarea id="productSummary" name="geDirectory.productSummary" cols="72" rows="10">${geDirectory.productSummary}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">产品特色：</td>
								<td class="td_body" width="460px">
									<textarea id="productFeature" name="geDirectory.productFeature" cols="72" rows="10">${geDirectory.productFeature}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">产品条款：</td>
								<td class="td_body" width="460px">
									<textarea id="productArticleDesc" name="geDirectory.productArticleDesc" cols="72" rows="10">${geDirectory.productArticleDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">保费区间：</td>
								<td class="td_body" width="460px">
									<textarea id="premiumRange" name="geDirectory.premiumRange" cols="72" rows="10">${geDirectory.premiumRange}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">保险金额描述：</td>
								<td class="td_body" width="460px">
									<textarea id="insuranceAmountDesc" name="geDirectory.insuranceAmountDesc" cols="72" rows="10">${geDirectory.insuranceAmountDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">保险期间：</td>
								<td class="td_body" width="460px">
									<textarea id="insurancePeriodDesc" name="geDirectory.insurancePeriodDesc"  cols="72" rows="10">${geDirectory.insurancePeriodDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">缴费方式 ：</td>
								<td class="td_body" width="460px">
									<textarea id="payType" name="geDirectory.payType" cols="72" rows="10">${geDirectory.payType}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">保障利益：</td>
								<td class="td_body" width="460px">
									<textarea id="securityInterest" name="geDirectory.securityInterest" cols="72" rows="10">${geDirectory.securityInterest}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">适用人群：</td>
								<td class="td_body" width="460px">
									<textarea id="applyPeople" name="geDirectory.applyPeople" cols="72" rows="10">${geDirectory.applyPeople}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">承保对象：</td>
								<td class="td_body" width="460px">
									<textarea id="underwritingObject" name="geDirectory.underwritingObject" cols="72" rows="10">${geDirectory.underwritingObject}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">投保范围：</td>
								<td class="td_body" width="460px">
									<textarea id="insureRange" name="geDirectory.insureRange" cols="72" rows="10">${geDirectory.insureRange}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">投保年龄：</td>
								<td class="td_body" width="460px">
									<textarea id="insureAge" name="geDirectory.insureAge" cols="72" rows="10">${geDirectory.insureAge}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px" style="text-align: right;">购买须知：</td>
								<td class="td_body" width="460px">
									<textarea id="purchaseNotes" name="geDirectory.purchaseNotes" cols="72" rows="10">${geDirectory.purchaseNotes}</textarea>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right" style="padding-right:40px;">
				<table id="operatorTable">
					<tr>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/productDirectory/productDetail.do?geDirectory.eid=${geDirectory.eid}';">返回</td>
						<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
						<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="eid" name="geDirectory.eid" value="${geDirectory.eid}" />
	<input type="hidden" id="netSaleProductType" name="geDirectory.netSaleProductType" maxlength="50" value="${geDirectory.netSaleProductType}"/>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</center>
</div>
<script type="text/javascript">
	function addPreviewFace(obj){
		var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
		facePic.file.onchange = function(){
			facePic.preview(); 
		};
	}
	function showEdit(obj) {
		window.open("${ctx}/business/cmpProductManage/productDirectory/directory/create/selectProductList/index.jsp?coreProductCode=" + obj.value, "查询产品", "top=100, left=100, width=900,height=600,toolbar=no");
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	$(document).ready(function(){
		
		//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
		tt.vf.req.add("geDirectory.isNetSale");
		tt.vf.req.add("geDirectory.productName");
		tt.vf.req.add("businessAreaSelect");
		tt.vf.req.add("geDirectory.productSection");
		tt.vf.req.add("geDirectory.publishDate");
		tt.vf.req.add("geDirectory.stopDate");
		new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.productQuoteCode");
		new tt.LV().set(0,100).add("geDirectory.coreProductSimpleName");
		new tt.LV().set(0,500).add("geDirectory.productSummary");
		new tt.LV().set(0,500).add("geDirectory.productFeature");
		new tt.LV().set(0,500).add("geDirectory.productArticleDesc");
		new tt.LV().set(0,500).add("geDirectory.premiumRange");
		new tt.LV().set(0,500).add("geDirectory.insuranceAmountDesc");
		new tt.LV().set(0,500).add("geDirectory.insurancePeriodDesc");
		new tt.LV().set(0,500).add("geDirectory.payType");
		new tt.LV().set(0,500).add("geDirectory.insureRange");
		new tt.LV().set(0,500).add("geDirectory.insureAge");
		new tt.LV().set(0,500).add("geDirectory.underwritingObject");
		new tt.LV().set(0,500).add("geDirectory.applyPeople");
		new tt.LV().set(0,500).add("geDirectory.purchaseNotes");
		new tt.LV().set(0,500).add("geDirectory.securityInterest");
		
		if ("${geDirectory.isNetSale}" == "01") {
			$("#coreProductCode").attr("readonly", true);
			$("#productName").attr("readonly", true);
			$("#coreProductSimpleName").attr("readonly", true);
			$("#businessArea").attr("readonly", true);
			$("#coreProductCode").addClass("input_readOnly");
			$("#productName").addClass("input_readOnly");
			$("#coreProductSimpleName").addClass("input_readOnly");
		}
		
		$("#isNetSale").change(function() {
			var isNetSale = $("#isNetSale").val();
			if("01" == isNetSale) {
				$("#coreProductCode").attr("readonly", true);
				$("#productName").attr("readonly", true);
				$("#coreProductSimpleName").attr("readonly", true);
				$("#businessAreaSelect").attr("readonly", true);
				$("#coreProductCode").addClass("input_readOnly");
				$("#productName").addClass("input_readOnly");
				$("#coreProductSimpleName").addClass("input_readOnly");
				$("#coreProductCode").after("<span id='coreProductCodeContent'>&nbsp;<input style='width:80px;' onclick='choiceIndex();' type='button' value='绑定网销产品' /></span>");
				$("#coreProductCode").val("");
				$("#productName").val("");
				$("#coreProductSimpleName").val("");
				$("#businessAreaSelect").val("");
				$("#businessArea").val("");
				
			} else if ("02" == isNetSale) {
				document.getElementById("coreProductCode").readOnly = "";
				document.getElementById("productName").readOnly = "";
				document.getElementById("coreProductSimpleName").readOnly = "";
				$("#coreProductCode").removeClass("input_readOnly");
				$("#productName").removeClass("input_readOnly");
				$("#coreProductSimpleName").removeClass("input_readOnly");
				$("#coreProductCodeContent").html("");
				$("#coreProductCode").val("");
				$("#productName").val("");
				$("#coreProductSimpleName").val("");
				$("#businessAreaSelect").val("");
				$("#businessArea").val("");
			} else {
				$("#coreProductCode").attr("readonly", true);
				$("#coreProductCode").removeClass("input_readOnly");
				$("#productName").removeClass("input_readOnly");
				$("#coreProductSimpleName").removeClass("input_readOnly");
				$("#coreProductCodeContent").html("");
			}
			$("#publishDate").addClass("Wdate");
			$("#stopDate").addClass("Wdate");
		});
		
		$("#coreProductCode").dblclick(function(){
			var isNetSale = $("#isNetSale").val();
			if("01" == isNetSale) {
				showEdit(this);
			} else if ("02" == isNetSale) {
				return null;
			} else {
				return null;
			}
		});
		
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
		var ids = ['coreProductCode','productQuoteCode','publishDate','stopDate','smallPictureOne','smallPictureTwo','middlePictureOne','middlePictureTwo','bigPictureOne','bigPictureTwo'];
		var contents = ['非电子商务产品的代码','寿险产品试算参数代码','产品发布时间','产品自动下架时间','为你推荐，近期浏览产品展示图片(61*61不超过15k)','为你推荐，近期浏览产品展示图片(61*61不超过15k)','产品列表，产品展示图片(166*99不超过40k)','产品列表，产品展示图片(166*99不超过40k)','预留，暂不使用','预留，暂不使用'];
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
	
	function businessAreaSelectChange(obj) {
		var isNetSale = $("#isNetSale").val();
		if("01" == isNetSale) {
			var businessArea = $("#businessArea").val();
			selectSelectedValue(obj, businessArea);
			$("#businessArea").val($("#businessAreaSelect").val());
		} else {
			$("#businessArea").val($("#businessAreaSelect").val());
		}
	}
	
	function selectSelectedValue(selectObj, selectedValue) {
		for (var i = 0; i < selectObj.options.length; i++) {
			if (selectObj.options[i].value == selectedValue) {
				selectObj.options[i].selected = "selected";
				break;
			}
		}
	}
	//发布时间校验
	function publishDateImgClick(){
		var stopDate = $('#stopDate').val();
		if (stopDate == "") {
			WdatePicker({el:'publishDate',minDate:'%y-%M-%d'});
		} else {
			WdatePicker({el:'publishDate',minDate:'%y-%M-%d',maxDate:stopDate});
		}
	}
	
	//停售时间校验
	function stopDateImgClick(){
		var publishDate = $('#publishDate').val();
		if (publishDate == "") {
			WdatePicker({el:'stopDate',minDate:'%y-%M-%d'});
		} else {
			WdatePicker({el:'stopDate',minDate:publishDate});
		}
	}
	function choiceIndex(){
		var isNetSale = $("#isNetSale").val();
		if("01" == isNetSale) {
			showEdit(this);
		}else if ("02" == isNetSale) {
			return null;
		}else {
			return null;
		}
	}

	var y = document.getElementsByTagName("input");
	for (var i=0; i < y.length; i++){
		if(y[i].type == 'text'){
			y[i].onkeyup = showMyStatus;
			if (y[i].readOnly) {
				y[i].className = "input_readOnly";
			}
		} else if(y[i].type == 'file') {
			y[i].className = "input_readOnly";
		} 
	}
	$("#publishDate").addClass("Wdate");
	$("#stopDate").addClass("Wdate");
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
	String.prototype.trim = function(){
	    return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	function validateValue(obj) {
		var patn = /(^\s)|(\s$)/;
		if (patn.test(obj.value))
			obj.value = obj.value.trim();
	}
	var ids = ['productRecommend'];
		var contents = ['推荐评分级别越高，该产品在前台越靠前显示。'];
   	for ( var i = 0 ; i <ids.length ; i++ ){
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


</script>
</body>
</html>
