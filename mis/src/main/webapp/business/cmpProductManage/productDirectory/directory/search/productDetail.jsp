<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
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
<title>电子商务后台管理系统-产品详细信息</title>
<style type="text/css">
	td {
		vertical-align:top;
		text-align:right;
	}
	#productDetail tr {
		height:85px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
	}
</style>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				产品详细信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">电子商务产品ID：</td>
				<td class="td_body" width="360px">${geDirectory.eid}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">是否网销：</td>
				<td class="td_body" width="360px">
					<c:if test="${geDirectory.isNetSale == '' }">--请选择--</c:if>
					<c:if test="${geDirectory.isNetSale == '01' }">是</c:if>
					<c:if test="${geDirectory.isNetSale == '02' }">否</c:if>
				</td>
			</tr>	
			<tr>
				<td class="td_head" width="200px">产品代码/险种代码：</td>
				<td class="td_body" width="360px">${geDirectory.coreProductCode}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">产品名称：</td>
				<td class="td_body" width="360px">${geDirectory.productName}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">产品简称：</td>
				<td class="td_body" width="360px">${geDirectory.coreProductSimpleName}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">业务领域：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<c:if test="${geDirectory.businessArea == GeCode_businessArea.id.codeCode}">
							${GeCode_businessArea.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">产品试算代码：</td>
				<td class="td_body" width="360px">${geDirectory.productQuoteCode}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">销售渠道：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<c:if test="${geDirectory.saleChannel == GeCode_saleChannel.id.codeCode}">
							${GeCode_saleChannel.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>	
			<tr>
				<td class="td_head" width="200px">承保方式：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
						<c:if test="${geDirectory.acceptInsurance == GeCode_acceptInsurance.id.codeCode}">
							${GeCode_acceptInsurance.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">产品栏目：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
						<c:if test="${geDirectory.productSection == GeCode_productSection.id.codeCode}">
							${GeCode_productSection.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">标的类型 ：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
						<c:if test="${geDirectory.itemType == GeCode_itemType.id.codeCode}">
							${GeCode_itemType.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">发布日期：</td>
				<td class="td_body" width="360px">${geDirectory.publishDate}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">停售日期：</td>
				<td class="td_body" width="360px">${geDirectory.stopDate}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">险种期限：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
						<c:if test="${geDirectory.riskLimit == GeCode_riskLimit.id.codeCode}">
							${GeCode_riskLimit.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">产品推荐评分：</td>
				<td class="td_body" width="360px">
					<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
						<c:if test="${geDirectory.productRecommend == GeCode_productRecommend.id.codeCode}">
							${GeCode_productRecommend.id.codeCode == '00' ? '--请选择--' : GeCode_productRecommend.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div class="frmCreate_kuang" style="margin-left:100px;width:580px;margin-top:15px;">
						<div class="frmCreate_kuang_header">添加产品图片</div>
						<div style="padding-left:3px; padding-top:15px;">
							<table>
								<tr>
									<td class="td_head" width="120px">小图片1：</td>
									<td class="td_body" width="470px">
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
									<td class="td_body" width="470px">
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
									<td class="td_head" width="120px">中图片1：</td>
									<td class="td_body" width="470px">
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
									<td class="td_body" width="470px">
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
									<td class="td_body" width="470px">
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
									<td class="td_body" width="470px">
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
					<div class="frmCreate_kuang" style="margin-left:100px;width:580px;margin-top:15px;">
						<div class="frmCreate_kuang_header">添加产品详细</div>
						<div style="padding-left:3px; padding-top:15px;">
							<table id="productDetail">
								<tr>
									<td class="td_head" width="120px">产品简介：</td>
									<td class="td_body" width="470px">${geDirectory.productSummary}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">产品特色：</td>
									<td class="td_body" width="470px">${geDirectory.productFeature}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">产品条款：</td>
									<td class="td_body" width="470px">${geDirectory.productArticleDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">保费区间：</td>
									<td class="td_body" width="470px">${geDirectory.premiumRange}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">保险金额描述：</td>
									<td class="td_body" width="470px">${geDirectory.insuranceAmountDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">保险期间：</td>
									<td class="td_body" width="470px">${geDirectory.insurancePeriodDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">缴费方式 ：</td>
									<td class="td_body" width="470px">${geDirectory.payType}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">保障利益：</td>
									<td class="td_body" width="470px">${geDirectory.securityInterest}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">适用人群：</td>
									<td class="td_body" width="470px">${geDirectory.applyPeople}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">承保对象：</td>
									<td class="td_body" width="470px">${geDirectory.underwritingObject}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">投保范围：</td>
									<td class="td_body" width="470px">${geDirectory.insureRange}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">投保年龄：</td>
									<td class="td_body" width="470px">${geDirectory.insureAge}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px" style="text-align: right;">购买须知：</td>
									<td class="td_body" width="470px">${geDirectory.purchaseNotes}</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding-top:10px;padding-bottom:20px;">
		<form id="operatorForm" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0" align="center" id="operatorTable">
				<tr>
					<td onclick="javascript:maximizeGrid(this);">
						&nbsp;
					</td>
					<acc:showView source="ROLE_B_PDIR_B">
						<td onclick="bind_Attribute('${geDirectory.eid}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">绑定属性</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_T">
						<td onclick="recommendProduct('${geDirectory.eid}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">产品推荐</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doEdit('${geDirectory.eid}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">编辑</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_PD">
						<td onclick="productDetial('all', '${geDirectory.eid}', '${geDirectory.isNetSale}', '${geDirectory.productSection}');" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">产品详细</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_NPD">
						<td onclick="productDetial('wssc', '${geDirectory.eid}', '${geDirectory.isNetSale}', '${geDirectory.productSection}');" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">网销产品详细</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_PT">
						<td onclick="productTerms('${geDirectory.eid}', '${geDirectory.productSection}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">产品条款配置</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_S">
						<td onclick="productShelve('${geDirectory.eid}', '01');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">上架</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_X">
						<td onclick="productShelve('${geDirectory.eid}', '02');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">下架</td>
					</acc:showView>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;height:1px;"></iframe>
	</div>
	<script type="text/javascript">
		//编辑
		function doEdit(idStr){
			location.href = "${ctx}/productDirectory/findGeDirectoryByEId.do?geDirectory.eid=" + idStr;		
		}
		
		//绑定属性
		function bind_Attribute(eId) {
			location.href = "${ctx}/productDirectory/findGeDirectoryAttributeRelateByEId.do?treeId=0&eId=" + eId;
		}
		
		//产品推荐
		function recommendProduct(eId) {
			location.href = "${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/index.jsp?eId=" + eId;
		}
		
		//产品条款配置
		function productTerms(idStr, productSection){
			if (productSection == "01") {
				//个人
				window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + idStr + "&sell=tk&productSection=01","", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");	
			} else {
				//企业
				window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + idStr + "&sell=tk&productSection=02", "", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");
			} 
		}
		//产品详细
		function productDetial(obj,eId, isNetSale, productSection) {
				if (obj == "wssc") {
					if (isNetSale != "01") {
						alert("网销产品详细只能对网销产品操作！");
						return;
					} else {
						window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + eId + "&sell=" + obj, "", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");
					}
				} else {
					if (productSection == "01") {
						window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + eId + "&sell=" + obj + "&productSection=01" , "", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");
					} else {
						window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + eId + "&sell=" + obj + "&productSection=02" , "" , "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");
					} 
					
				}
		}
		
		//产品上架下架
		function productShelve(eid, isProductShelf) {
				 $("#operatorForm")[0].action = "${ctx}/productDirectory/updateProductShelf.do?eid=" + eid + "&isProductShelf=" + isProductShelf;
				 $("#operatorForm")[0].submit();
		}
		
		//删除
		function doDelete(){
			if(!getId()){
				return;
			}
			if (count == 0) {
				alert("请选择要删除的产品！");
				return;
			}
			
			if (count > 1) {
				alert("只能对单条产品进行操作！");
				return;
			}
			
			if(confirm("确定删除您选中的"+count+"个产品吗？")){
				window.parent.frames[3].location.href = "${ctx}/productDirectory/delProductDirectory.do?eid=" + idStr;
			}
		}
		
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
</body>
</html>
