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
<title>���������̨����ϵͳ-�༭��ƷĿ¼������Ϣ</title>
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
				�༭��ƷĿ¼������Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div style="padding-top:15px;">

<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">��&nbsp;��</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>

<center>
	<form id="frmInput" action="${ctx}/productDirectory/updateGeDirectory.do" method="post"  enctype="multipart/form-data" target="myFrame">
	<table  id="geFunctionSwitchTable" width="650px">
		<tr>
			<td class="td_head" width="140px">���������ƷID��</td>
			<td class="td_body" >${geDirectory.eid}</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">�Ƿ�������</td>
			<td class="td_body" >
				<select id="isNetSale" name="geDirectory.isNetSale" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<option value="01" ${geDirectory.isNetSale=="01"?"selected":""}>��</option>
					<option value="02" ${geDirectory.isNetSale=="02"?"selected":""}>��</option>
				</select>
			</td>
		</tr>	
		<tr>
			<td class="td_head" width="140px">��Ʒ����/���ִ��룺</td>
			<td class="td_body" >
				<input id="coreProductCode" name="geDirectory.coreProductCode" type="text" style="width:170px;" value="${geDirectory.coreProductCode}" maxlength="50"  />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">��Ʒ���ƣ�</td>
			<td class="td_body" >
				<input id="productName" name="geDirectory.productName" type="text" style="width:400px;" value="${geDirectory.productName}" maxlength="50" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">��Ʒ��ƣ�</td>
			<td class="td_body" >
				<input id="coreProductSimpleName" name="geDirectory.coreProductSimpleName" type="text" style="width:400px;" value="${geDirectory.coreProductSimpleName}" maxlength="100" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">ҵ������</td>
			<td class="td_body" >
				<input type="hidden" id="businessArea" name="geDirectory.businessArea" maxlength="30" value="${geDirectory.businessArea}" style="width: 170px;"/>
				<select id="businessAreaSelect" name="businessAreaSelect" onchange="businessAreaSelectChange(this)" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<option value="${GeCode_businessArea.id.codeCode}" ${geDirectory.businessArea == GeCode_businessArea.id.codeCode ?"selected":""}>${GeCode_businessArea.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">��Ʒ������룺</td>
			<td class="td_body" ><input type="text" id="productQuoteCode" name="geDirectory.productQuoteCode" maxlength="50" value="${geDirectory.productQuoteCode}"  style="width: 170px;" /></td>
		</tr>
		<tr>
			<td class="td_head" width="140px">����������</td>
			<td class="td_body" >
				<select id="saleChannel" name="geDirectory.saleChannel" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<option value="${GeCode_saleChannel.id.codeCode}" ${geDirectory.saleChannel == GeCode_saleChannel.id.codeCode ?"selected":""}>${GeCode_saleChannel.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>	
		<tr>
			<td class="td_head" width="140px">�б���ʽ��</td>
			<td class="td_body" >
				<select id="acceptInsurance" name="geDirectory.acceptInsurance" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
						<option value="${GeCode_acceptInsurance.id.codeCode}" ${geDirectory.acceptInsurance == GeCode_acceptInsurance.id.codeCode ?"selected":""}>${GeCode_acceptInsurance.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">��Ʒ��Ŀ��</td>
			<td class="td_body" >
				<select id="productSection" name="geDirectory.productSection" onchange="" style="width: 170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
						<option value="${GeCode_productSection.id.codeCode}" ${geDirectory.productSection == GeCode_productSection.id.codeCode ?"selected":""}>${GeCode_productSection.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">������� ��</td>
			<td class="td_body" >
				<select id="itemType" name="geDirectory.itemType" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
						<option value="${GeCode_itemType.id.codeCode}" ${geDirectory.itemType == GeCode_itemType.id.codeCode ?"selected":""}>${GeCode_itemType.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">�������ڣ�</td>
			<td class="td_body" >
				<input type="text" id="publishDate" class="Wdate" onclick="publishDateImgClick()" name="geDirectory.publishDate" maxlength="30" value="${geDirectory.publishDate}" style="width: 170px;" readonly/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">ͣ�����ڣ�</td>
			<td class="td_body" >
				<input type="text" id="stopDate" class="Wdate" onclick="stopDateImgClick()" name="geDirectory.stopDate" maxlength="30" value="${geDirectory.stopDate}" style="width: 170px;" readonly/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">�������ޣ�</td>
			<td class="td_body" >
				<select id="riskLimit" name="geDirectory.riskLimit" style="width:170px;">
					<option value="">--��ѡ��--</option>
					<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
						<option value="${GeCode_riskLimit.id.codeCode}" ${geDirectory.riskLimit == GeCode_riskLimit.id.codeCode ?"selected":""}>${GeCode_riskLimit.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px">��Ʒ�Ƽ����֣�</td>
			<td class="td_body" >
				<select id="productRecommend" name="geDirectory.productRecommend" style="width:170px;">
					<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
						<option value="${GeCode_productRecommend.id.codeCode}" ${geDirectory.productRecommend == GeCode_productRecommend.id.codeCode ?"selected":""}>${GeCode_productRecommend.id.codeCode == '00'?'--��ѡ��--':(GeCode_productRecommend.codeCName)}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div class="frmCreate_kuang" id="addPictureBox">
					<div class="frmCreate_kuang_header">��Ӳ�ƷͼƬ</div>
					<div style="text-align:left;padding-left:10px;padding-top:15px;">
						<table>
							<tr>
								<td class="td_head" width="120px">
									<a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('productSmallPictureOne');" id="productSmallPictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">СͼƬ1��</span>
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
								<td class="td_head" width="120px">СͼƬ2��</td>
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
									<a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('productMiddlePictureOne');" id="productMiddlePictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								
<span style="line-height:23px;">��ͼƬ1��</span>
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
								<td class="td_head" width="120px">��ͼƬ2��</td>
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
								<td class="td_head" width="120px">��ͼƬ1��</td>
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
								<td class="td_head" width="120px">��ͼƬ2��</td>
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
					<div class="frmCreate_kuang_header">��Ӳ�Ʒ��ϸ</div>
					<div style="padding-left:10px;padding-top:15px;">
						<table id="productDetail">
							<tr>
								<td class="td_head" width="120px">��Ʒ��飺</td>
								<td class="td_body" width="460px">
									<textarea id="productSummary" name="geDirectory.productSummary" cols="72" rows="10">${geDirectory.productSummary}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">��Ʒ��ɫ��</td>
								<td class="td_body" width="460px">
									<textarea id="productFeature" name="geDirectory.productFeature" cols="72" rows="10">${geDirectory.productFeature}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">��Ʒ���</td>
								<td class="td_body" width="460px">
									<textarea id="productArticleDesc" name="geDirectory.productArticleDesc" cols="72" rows="10">${geDirectory.productArticleDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">�������䣺</td>
								<td class="td_body" width="460px">
									<textarea id="premiumRange" name="geDirectory.premiumRange" cols="72" rows="10">${geDirectory.premiumRange}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">���ս��������</td>
								<td class="td_body" width="460px">
									<textarea id="insuranceAmountDesc" name="geDirectory.insuranceAmountDesc" cols="72" rows="10">${geDirectory.insuranceAmountDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">�����ڼ䣺</td>
								<td class="td_body" width="460px">
									<textarea id="insurancePeriodDesc" name="geDirectory.insurancePeriodDesc"  cols="72" rows="10">${geDirectory.insurancePeriodDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">�ɷѷ�ʽ ��</td>
								<td class="td_body" width="460px">
									<textarea id="payType" name="geDirectory.payType" cols="72" rows="10">${geDirectory.payType}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">�������棺</td>
								<td class="td_body" width="460px">
									<textarea id="securityInterest" name="geDirectory.securityInterest" cols="72" rows="10">${geDirectory.securityInterest}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">������Ⱥ��</td>
								<td class="td_body" width="460px">
									<textarea id="applyPeople" name="geDirectory.applyPeople" cols="72" rows="10">${geDirectory.applyPeople}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">�б�����</td>
								<td class="td_body" width="460px">
									<textarea id="underwritingObject" name="geDirectory.underwritingObject" cols="72" rows="10">${geDirectory.underwritingObject}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">Ͷ����Χ��</td>
								<td class="td_body" width="460px">
									<textarea id="insureRange" name="geDirectory.insureRange" cols="72" rows="10">${geDirectory.insureRange}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px">Ͷ�����䣺</td>
								<td class="td_body" width="460px">
									<textarea id="insureAge" name="geDirectory.insureAge" cols="72" rows="10">${geDirectory.insureAge}</textarea>
								</td>
							</tr>
							<tr>
								<td class="td_head" width="120px" style="text-align: right;">������֪��</td>
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
							onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/productDirectory/productDetail.do?geDirectory.eid=${geDirectory.eid}';">����</td>
						<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
						<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >�ر�</td>
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
		window.open("${ctx}/business/cmpProductManage/productDirectory/directory/create/selectProductList/index.jsp?coreProductCode=" + obj.value, "��ѯ��Ʒ", "top=100, left=100, width=900,height=600,toolbar=no");
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	
	$(document).ready(function(){
		
		//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
		tt.vf.req.add("geDirectory.isNetSale");
		tt.vf.req.add("geDirectory.productName");
		tt.vf.req.add("businessAreaSelect");
		tt.vf.req.add("geDirectory.productSection");
		tt.vf.req.add("geDirectory.publishDate");
		tt.vf.req.add("geDirectory.stopDate");
		new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");
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
				$("#coreProductCode").after("<span id='coreProductCodeContent'>&nbsp;<input style='width:80px;' onclick='choiceIndex();' type='button' value='��������Ʒ' /></span>");
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
		
		//���ύ
		$("#updateButton").click(function(){
			if(!tt.validate()){
				return false;
			}else{
				$("#frmInput").submit();
			}
		});
		
		//pop��ʾ��Ϣ
		var ids = ['coreProductCode','productQuoteCode','publishDate','stopDate','smallPictureOne','smallPictureTwo','middlePictureOne','middlePictureTwo','bigPictureOne','bigPictureTwo'];
		var contents = ['�ǵ��������Ʒ�Ĵ���','���ղ�Ʒ�����������','��Ʒ����ʱ��','��Ʒ�Զ��¼�ʱ��','Ϊ���Ƽ������������ƷչʾͼƬ(61*61������15k)','Ϊ���Ƽ������������ƷչʾͼƬ(61*61������15k)','��Ʒ�б���ƷչʾͼƬ(166*99������40k)','��Ʒ�б���ƷչʾͼƬ(166*99������40k)','Ԥ�����ݲ�ʹ��','Ԥ�����ݲ�ʹ��'];
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
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
    	}
    	//pop��ʾ��Ϣ����
    	
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
	//����ʱ��У��
	function publishDateImgClick(){
		var stopDate = $('#stopDate').val();
		if (stopDate == "") {
			WdatePicker({el:'publishDate',minDate:'%y-%M-%d'});
		} else {
			WdatePicker({el:'publishDate',minDate:'%y-%M-%d',maxDate:stopDate});
		}
	}
	
	//ͣ��ʱ��У��
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
	String.prototype.trim = function(){
	    return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	function validateValue(obj) {
		var patn = /(^\s)|(\s$)/;
		if (patn.test(obj.value))
			obj.value = obj.value.trim();
	}
	var ids = ['productRecommend'];
		var contents = ['�Ƽ����ּ���Խ�ߣ��ò�Ʒ��ǰ̨Խ��ǰ��ʾ��'];
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
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
   	}


</script>
</body>
</html>
