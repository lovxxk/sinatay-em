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
<title>电子商务后台管理系统-增加产品目录管理</title>
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
<div class="public_fun_title">新建产品目录</div>
<form id="frmInput" name="frmInput" method="post" enctype="multipart/form-data" action="${ctx}/productDirectory/addProductDirectory.do">

<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none">
<h2 id="h2btn"><a href="#" id="btnClose" style="text-align: inherit;">关&nbsp;闭</a></h2>
    <div class="form" >
	<img src="" id="showImg">
    </div>
</div>


<center>
	<table class="table_style" align="center" id="productDirectoryTable">
		<tr>
			<td class="td_head" width="140px" nowrap>是否网销：</td>
			<td class="td_body" width="360"><select id="isNetSale" name="geDirectory.isNetSale" style="width: 170px;">
				<option value="" selected="selected">--请选择--</option>
				<option value="01">是</option>
				<option value="02">否</option>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>产品代码/险种代码：</td>
			<td class="td_body" width="360"><input type="text" id="coreProductCode" name="geDirectory.coreProductCode" maxlength="50" value="" readOnly style="width: 170px;" />
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>产品名称：</td>
			<td class="td_body" width="360"><input type="text" id="productName" name="geDirectory.productName" maxlength="100" value="" style="width:350px;"></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>产品简称：</td>
			<td class="td_body" width="360"><input type="text" id="coreProductSimpleName" name="geDirectory.coreProductSimpleName"  value="" style="width:350px;"></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>业务领域：</td>
			<td class="td_body" width="360">
				<input type="hidden" id="businessArea" name="geDirectory.businessArea" maxlength="30" value="" style="width: 170px;"/>
				<select id="businessAreaSelect" name="businessAreaSelect" onchange="businessAreaSelectChange(this)" style="width: 170px;">
					<option value="" selected>--请选择--</option>
						<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
							<option value="${GeCode_businessArea.id.codeCode}">${GeCode_businessArea.codeCName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap valign="middle">产品试算代码：</td>
			<td class="td_body" width="360"><input type="text" id="productQuoteCode" name="geDirectory.productQuoteCode" maxlength="50" value=""  style="width: 170px;" /></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>销售渠道：</td>
			<td class="td_body" width="360">
				<select id="geDirectory.saleChannel" name="geDirectory.saleChannel" onchange="" style="width: 170px;">
					<option value="" selected>--请选择--</option>
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<option value="${GeCode_saleChannel.id.codeCode}">${GeCode_saleChannel.codeCName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>承保方式：</td>
			<td class="td_body" width="360"><select id="acceptInsurance" name="geDirectory.acceptInsurance" onchange="" style="width: 170px;">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
					<option value="${GeCode_acceptInsurance.id.codeCode}">${GeCode_acceptInsurance.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>产品栏目：</td>
			<td class="td_body" width="360"><select id="productSection" name="geDirectory.productSection" onchange="" style="width: 170px;">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
					<option value="${GeCode_productSection.id.codeCode}">${GeCode_productSection.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>标的类型：</td>
			<td class="td_body" width="360"><select id="itemType" name="geDirectory.itemType" onchange="" style="width: 170px;">
				<option value="" selected>--请选择--</option>
				<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
					<option value="${GeCode_itemType.id.codeCode}">${GeCode_itemType.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>发布日期：</td>
			<td class="td_body" width="360">
				<input type="text" onclick="WdatePicker({el:$dp.$('checkTime'),minDate:'%y-%M-%d'})" class="Wdate"  id="publishDate" name="geDirectory.publishDate" maxlength="30" style="width: 170px;" readonly value=""/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>停售日期：</td>
			<td class="td_body" width="360">
				<input type="text" onclick="WdatePicker({el:$dp.$('checkTime'),minDate:'%y-%M-%d'})" class="Wdate"  id="stopDateImg" name="geDirectory.stopDate" maxlength="30" style="width: 170px;" readonly value=""/>
			</td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>险种期限：</td>
			<td class="td_body" width="360"><select id="riskLimit" name="geDirectory.riskLimit" onchange="" style="width: 170px;">
				<option value="" selected="selected">--请选择--</option>
				<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
					<option value="${GeCode_riskLimit.id.codeCode}">${GeCode_riskLimit.codeCName}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td class="td_head" width="140px" nowrap>产品推荐评分：</td>
			<td class="td_body" width="360"><select id="productRecommend" name="geDirectory.productRecommend" onchange="" style="width: 170px;">
				<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
					<option value="${GeCode_productRecommend.id.codeCode}">${GeCode_productRecommend.id.codeCode == '00'?'--请选择--':(GeCode_productRecommend.codeCName)}</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
			<div class="frmCreate_kuang" style="margin-left:10px;width:500px;margin-top:15px;">
				<div class="frmCreate_kuang_header">添加产品图片<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
				<div style="padding-left:3px; padding-top:15px;">
					<table>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>
								<a href="#" title="图片使用位置" onclick="btnShowone('productSmallPictureOne');" id="productSmallPictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">小图片1：</span>
							</td>
							<td class="td_body">
								<s:file id="smallPictureOne" name="smallPictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="smallPictureOnePreview"  width="61" height="61"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>小图片2：</td>
							<td class="td_body">
								<s:file id="smallPictureTwo" name="smallPictureTwo" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="smallPictureTwoPreview"  width="61" height="61"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>
								<a href="#" title="图片使用位置" onclick="btnShowone('productMiddlePictureOne');" id="productMiddlePictureOne"><img src="${ctx}/global/images/help.jpg" border="0" style="vertical-align:bottom;"/></a>
								<span style="line-height:23px;">中图片1：</span>
							</td>
							<td class="td_body">
								<s:file id="middlePictureOne" name="middlePictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="middlePictureOnePreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>中图片2：</td>
							<td class="td_body">
								<s:file id="middlePictureTwo" name="middlePictureTwo" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="middlePictureTwoPreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>大图片1：</td>
							<td class="td_body">
								<s:file id="bigPictureOne" name="bigPictureOne" onfocus="addPreviewFace(this.id);" size="27" ></s:file><br/>
								<img src="${ctx}/global/images/productMiddlePicture.jpg" id="bigPictureOnePreview"  width="166" height="99"/>
							</td>
						</tr>
						<tr>
							<td class="td_head" width="100px" valign="top" nowrap>大图片2：</td>
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
					<div class="frmCreate_kuang_header">添加产品详细</div>
					<div style="padding-left:3px; padding-top:15px;">
						<table id="productDetail">
							<tr>
								<td class="td_head" width="100px" nowrap>产品简介：</td>
								<td class="td_body" width="360" valign="top"><textarea id="productSummary" name="geDirectory.productSummary" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>产品特色：</td>
								<td class="td_body" width="360" valign="top"><textarea id="productFeature" name="geDirectory.productFeature" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>产品条款：</td>
								<td class="td_body" width="360" valign="top"><textarea id="productArticleDesc" name="geDirectory.productArticleDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>保费区间：</td>
								<td class="td_body" width="360"><textarea id="premiumRange" name="geDirectory.premiumRange" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>保险金额描述：</td>
								<td class="td_body" width="360"><textarea id="insuranceAmountDesc" name="geDirectory.insuranceAmountDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>保险期间：</td>
								<td class="td_body" width="360"><textarea id="insurancePeriodDesc" name="geDirectory.insurancePeriodDesc" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>缴费方式：</td>
								<td class="td_body" width="360"><textarea id="payType" name="geDirectory.payType" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>保障利益：</td>
								<td class="td_body" width="360"><textarea id="securityInterest" name="geDirectory.securityInterest" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>适用人群：</td>
								<td class="td_body" width="360"><textarea id="applyPeople" name="geDirectory.applyPeople" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>承保对象：</td>
								<td class="td_body" width="360"><textarea id="underwritingObject" name="geDirectory.underwritingObject" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>投保范围：</td>
								<td class="td_body" width="360"><textarea id="insureRange" name="geDirectory.insureRange" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>投保年龄：</td>
								<td class="td_body" width="360"><textarea id="insureAge" name="geDirectory.insureAge" cols="54" rows="10"></textarea></td>
							</tr>
							<tr>
								<td class="td_head" width="100px" nowrap>购买须知：</td>
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
					onmouseout="this.className='btnBigOnBlur'" nowrap>创建</td>
					<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>清空</td>
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
		window.open("${ctx}/business/cmpProductManage/productDirectory/directory/create/selectProductList/index.jsp?coreProductCode="+ obj.value, "查询产品", "top=100, left=100, width=900,height=600,toolbar=no");
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
		//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号
		new tt.LV().set(0,100).add("geDirectory.productName");
		tt.vf.req.add("geDirectory.isNetSale");
		tt.vf.req.add("geDirectory.productName");
		tt.vf.req.add("businessAreaSelect");
		tt.vf.req.add("geDirectory.productSection");
		tt.vf.req.add("geDirectory.publishDate");
		tt.vf.req.add("geDirectory.stopDate");
		new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.eid");
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
		});
		

		
		
		$("textarea").blur(function(){
			validateValue(this);
		});
		
		//表单提交
		$("#createButton").click(function(){
			if(!tt.validate()){
				return false;
			}else{
				$("#frmInput").submit();
			}
		});
		
		//pop提示信息
		var ids = ['coreProductCode','productQuoteCode','publishDate','stopDate','smallPictureOne','smallPictureTwo','middlePictureOne','middlePictureTwo','bigPictureOne','bigPictureTwo'];
		var contents = ['非电子商务产品的代码','寿险产品试算参数代码','产品发布时间','产品自动下架时间','为你推荐，近期浏览产品展示图片(61*61不超过15k)','预留，暂不使用','产品列表，产品展示图片(166*99不超过40k)','预留，暂不使用','预留，暂不使用','预留，暂不使用'];
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
	$(document).ready(function(){
		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
		var ids = ['des'];
		    	var contents = ['说明:通过创建的产品目录，把产品在前台进行展示，并控制展示的位置<br/>'
		    	                + '使用人群:产品配置人员<br/>'
		    	                + '配置条件:需要先创建产品<br/>'
		    	                + '注意事项:网销的产品目录需要通过查询网销产品进行绑定<br/>'];
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
										tip:true,//控制左侧尖角是否出现
										padding :10
									}
								});
		        	}
		});
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
		alert("产品添加成功！");
	} else if (addReslut == "failure") {
		alert("产品添加失败！");
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
	   		var contents = ['推荐评分级别越高，该产品在前台越靠前显示。','产品下架时间'];
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
</html>
