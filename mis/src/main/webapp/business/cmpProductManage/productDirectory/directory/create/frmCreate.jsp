<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/business/cmpProductManage/carManage/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<title>���������̨����ϵͳ-���Ӳ�ƷĿ¼����</title>
<style type="text/css">
	#productDetail tr td{
		height:185px;
		vertical-align:top;
	}
#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1100px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
#DialogDiv{position:absolute;width:auto; left:50%; top:50%; margin-left:-400px; height:auto; z-index:100;background-color:#fff; border:1px #8FA4F5 solid; padding:1px;}
#DialogDiv h2{ height:25px; font-size:14px; background-color:#27a26b; position:relative; padding-left:10px; line-height:25px;text-align: left;width: 740px}
#DialogDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}
#DialogDiv .form{padding:10px;}
</style>
</head>
<body>
<div class="public_fun_title">�½���ƷĿ¼</div>
<form id="frmInput" name="frmInput" method="post" enctype="multipart/form-data" action="${ctx}/productDirectory/addProductDirectory.do">

<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">��&nbsp;��</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>


<center>
	<table class="table_style" align="center" id="productDirectoryTable">
		<tr>
			<td class="td_head" width="140px" nowrap>�Ƿ�������</td>
			<td class="td_body" width="360"><select id="isNetSale" name="geDirectory.isNetSale" style="width: 170px;">
				<option value="" selected="selected">--��ѡ��--</option>
				<option value="01">��</option>
				<option value="02">��</option>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>��Ʒ����/���ִ��룺</td>
			<td class="td_body" width="360"><input type="text" id="coreProductCode" name="geDirectory.coreProductCode" maxlength="50" value="" readOnly style="width: 170px;" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>��Ʒ���ƣ�</td>
			<td class="td_body" width="360"><input type="text" id="productName" name="geDirectory.productName" maxlength="100" value="" style="width:350px;"></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>��Ʒ��ƣ�</td>
			<td class="td_body" width="360"><input type="text" id="coreProductSimpleName" name="geDirectory.coreProductSimpleName"  value="" style="width:350px;"></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>ҵ������</td>
			<td class="td_body" width="360">
				<input type="hidden" id="businessArea" name="geDirectory.businessArea" maxlength="30" value="" style="width: 170px;"/>
				<select id="businessAreaSelect" name="businessAreaSelect" onchange="businessAreaSelectChange(this)" style="width: 170px;">
					<option value="" selected>--��ѡ��--</option>
						<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
							<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap valign="middle">��Ʒ������룺</td>
			<td class="td_body" width="360"><input type="text" id="productQuoteCode" name="geDirectory.productQuoteCode" maxlength="50" value=""  style="width: 170px;" /></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>����������</td>
			<td class="td_body" width="360">
				<select id="geDirectory.saleChannel" name="geDirectory.saleChannel" onchange="" style="width: 170px;">
					<option value="" selected>--��ѡ��--</option>
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<option value="${GeCode_saleChannel.id.codeCode}">${GeCode_saleChannel.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>�б���ʽ��</td>
			<td class="td_body" width="360"><select id="acceptInsurance" name="geDirectory.acceptInsurance" onchange="" style="width: 170px;">
				<option value="" selected="selected">--��ѡ��--</option>
				<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
					<option value="${GeCode_acceptInsurance.id.codeCode}">${GeCode_acceptInsurance.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>��Ʒ��Ŀ��</td>
			<td class="td_body" width="360"><select id="productSection" name="geDirectory.productSection" onchange="" style="width: 170px;">
				<option value="" selected="selected">--��ѡ��--</option>
				<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
					<option value="${GeCode_productSection.id.codeCode}">${GeCode_productSection.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>������ͣ�</td>
			<td class="td_body" width="360"><select id="itemType" name="geDirectory.itemType" onchange="" style="width: 170px;">
				<option value="" selected>--��ѡ��--</option>
				<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
					<option value="${GeCode_itemType.id.codeCode}">${GeCode_itemType.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>�������ڣ�</td>
			<td class="td_body" width="360">
				<input type="text" onclick="WdatePicker({el:$dp.$('checkTime'),minDate:'%y-%M-%d'})" class="Wdate"  id="publishDate" name="geDirectory.publishDate" maxlength="30" style="width: 170px;" readonly value=""/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>ͣ�����ڣ�</td>
			<td class="td_body" width="360">
				<input type="text" onclick="WdatePicker({el:$dp.$('checkTime'),minDate:'%y-%M-%d'})" class="Wdate"  id="stopDateImg" name="geDirectory.stopDate" maxlength="30" style="width: 170px;" readonly value=""/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>�������ޣ�</td>
			<td class="td_body" width="360"><select id="riskLimit" name="geDirectory.riskLimit" onchange="" style="width: 170px;">
				<option value="" selected="selected">--��ѡ��--</option>
				<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
					<option value="${GeCode_riskLimit.id.codeCode}">${GeCode_riskLimit.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>��Ʒ�Ƽ����֣�</td>
			<td class="td_body" width="360"><select id="productRecommend" name="geDirectory.productRecommend" onchange="" style="width: 170px;">
				<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
					<option value="${GeCode_productRecommend.id.codeCode}">${GeCode_productRecommend.id.codeCode == '00'?'--��ѡ��--':(GeCode_productRecommend.codeCName)}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
			<div class="frmCreate_kuang" style="margin-left:10px;width:500px;margin-top:15px;">
				<div class="frmCreate_kuang_header">��Ӳ�ƷͼƬ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
				<div style="padding-left:3px; padding-top:15px;">
					<table>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>
								<a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('productSmallPictureOne');" id="productSmallPictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">СͼƬ1��</span>
							</td>
							<td class="td_body">
								<s:file id="smallPictureOne" name="smallPictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="smallPictureOnePreview"  width="61" height="61"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>СͼƬ2��</td>
							<td class="td_body">
								<s:file id="smallPictureTwo" name="smallPictureTwo" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="smallPictureTwoPreview"  width="61" height="61"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>
								<a href="#" title="ͼƬʹ��λ��" onclick="btnShowone('productMiddlePictureOne');" id="productMiddlePictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">��ͼƬ1��</span>
							</td>
							<td class="td_body">
								<s:file id="middlePictureOne" name="middlePictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="middlePictureOnePreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>��ͼƬ2��</td>
							<td class="td_body">
								<s:file id="middlePictureTwo" name="middlePictureTwo" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="middlePictureTwoPreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>��ͼƬ1��</td>
							<td class="td_body">
								<s:file id="bigPictureOne" name="bigPictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="bigPictureOnePreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>��ͼƬ2��</td>
							<td class="td_body">
								<s:file id="bigPictureTwo" name="bigPictureTwo" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="bigPictureTwoPreview"  width="166" height="99"/>
							</td>
						</tr>
					</table>
				</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div class="frmCreate_kuang" style="margin-left:10px;width:500px;margin-top:15px;">
					<div class="frmCreate_kuang_header">��Ӳ�Ʒ��ϸ</div>
					<div style="padding-left:3px; padding-top:15px;">
						<table id="productDetail">
							<tr>
								<td class="td_head" width="100px" nowrap>��Ʒ��飺</td>
								<td class="td_body" width="360" valign="top"><textarea id="productSummary" name="geDirectory.productSummary" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>��Ʒ��ɫ��</td>
								<td class="td_body" width="360" valign="top"><textarea id="productFeature" name="geDirectory.productFeature" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>��Ʒ���</td>
								<td class="td_body" width="360" valign="top"><textarea id="productArticleDesc" name="geDirectory.productArticleDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>�������䣺</td>
								<td class="td_body" width="360"><textarea id="premiumRange" name="geDirectory.premiumRange" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>���ս��������</td>
								<td class="td_body" width="360"><textarea id="insuranceAmountDesc" name="geDirectory.insuranceAmountDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>�����ڼ䣺</td>
								<td class="td_body" width="360"><textarea id="insurancePeriodDesc" name="geDirectory.insurancePeriodDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>�ɷѷ�ʽ��</td>
								<td class="td_body" width="360"><textarea id="payType" name="geDirectory.payType" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>�������棺</td>
								<td class="td_body" width="360"><textarea id="securityInterest" name="geDirectory.securityInterest" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>������Ⱥ��</td>
								<td class="td_body" width="360"><textarea id="applyPeople" name="geDirectory.applyPeople" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>�б�����</td>
								<td class="td_body" width="360"><textarea id="underwritingObject" name="geDirectory.underwritingObject" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>Ͷ����Χ��</td>
								<td class="td_body" width="360"><textarea id="insureRange" name="geDirectory.insureRange" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>Ͷ�����䣺</td>
								<td class="td_body" width="360"><textarea id="insureAge" name="geDirectory.insureAge" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>������֪��</td>
								<td class="td_body" width="360"><textarea id="purchaseNotes" name="geDirectory.purchaseNotes" cols="54" rows="10"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr height="60px;">
			<td colspan="2" align="right" style="padding-right:40px;">
			<table>
				<tr>
					<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" nowrap>����</td>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>���</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</center>
<input type="hidden" id="netSaleProductType" name="geDirectory.netSaleProductType" maxlength="50" value=""/>
</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	function addPreviewFace(obj){
		var facePic = new ImagePreview( $$(obj), $$(obj + "Preview"), {maxWidth: 230, maxHeight: 160});
		facePic.file.onchange = function(){
			facePic.preview(); 
		};
	}
	
	function showEdit(obj) {
		window.open("${ctx}/business/cmpProductManage/productDirectory/directory/create/selectProductList/index.jsp?coreProductCode="+ obj.value, "��ѯ��Ʒ", "top=100, left=100, width=900,height=600,toolbar=no");
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
	
	$(document).ready(function(){
		//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
		new tt.LV().set(0,100).add("geDirectory.productName");
		tt.vf.req.add("geDirectory.isNetSale");
		tt.vf.req.add("geDirectory.productName");
		tt.vf.req.add("businessAreaSelect");
		tt.vf.req.add("geDirectory.productSection");
		tt.vf.req.add("geDirectory.publishDate");
		tt.vf.req.add("geDirectory.stopDate");
		new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
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
		
		$("readonly").addClass("input_readOnly");
		$("#publishDate").removeClass("input_readOnly");
		$("#stopDateImg").removeClass("input_readOnly");
		$("#publishDate").addClass("Wdate");
		$("#stopDateImg").addClass("Wdate");
		
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
		});
		

		
		
		$("textarea").blur(function(){
			validateValue(this);
		});
		
		//���ύ
		$("#createButton").click(function(){
			if(!tt.validate()){
				return false;
			}else{
				$("#frmInput").submit();
			}
		});
		
		//pop��ʾ��Ϣ
		var ids = ['coreProductCode','productQuoteCode','publishDate','stopDate','smallPictureOne','smallPictureTwo','middlePictureOne','middlePictureTwo','bigPictureOne','bigPictureTwo'];
		var contents = ['�ǵ��������Ʒ�Ĵ���','���ղ�Ʒ�����������','��Ʒ����ʱ��','��Ʒ�Զ��¼�ʱ��','Ϊ���Ƽ������������ƷչʾͼƬ(61*61������15k)','Ԥ�����ݲ�ʹ��','��Ʒ�б���ƷչʾͼƬ(166*99������40k)','Ԥ�����ݲ�ʹ��','Ԥ�����ݲ�ʹ��','Ԥ�����ݲ�ʹ��'];
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
	function businessAreaSelectChange(obj) {
		var isNetSale = $("#isNetSale").val();
		if("01" == isNetSale) {
			var businessArea = $("#businessArea").val();
			for (var i = 0; i < obj.options.length; i++) {
				if (obj.options[i].value == businessArea) {
					obj.options[i].selected = "selected";
					break;
				}
			}
			$("#businessArea").val($("#businessAreaSelect").val());
		} else {
			$("#businessArea").val($("#businessAreaSelect").val());
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
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['˵��:ͨ�������Ĳ�ƷĿ¼���Ѳ�Ʒ��ǰ̨����չʾ��������չʾ��λ��<br/>'
		    	                + 'ʹ����Ⱥ:��Ʒ������Ա<br/>'
		    	                + '��������:��Ҫ�ȴ�����Ʒ<br/>'
		    	                + 'ע������:�����Ĳ�ƷĿ¼��Ҫͨ����ѯ������Ʒ���а�<br/>'];
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
	

	var addReslut = "${addReslut}";
	
	if (addReslut == "success") {
		alert("��Ʒ��ӳɹ���");
	} else if (addReslut == "failure") {
		alert("��Ʒ���ʧ�ܣ�");
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
	$(function (){
		changeScrollState("");
	});
	var ids = ['productRecommend','stopDateImg'];
	   		var contents = ['�Ƽ����ּ���Խ�ߣ��ò�Ʒ��ǰ̨Խ��ǰ��ʾ��','��Ʒ�¼�ʱ��'];
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
</html>
