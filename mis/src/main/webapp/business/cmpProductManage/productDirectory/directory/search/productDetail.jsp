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
<title>���������̨����ϵͳ-��Ʒ��ϸ��Ϣ</title>
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
				��Ʒ��ϸ��Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">���������ƷID��</td>
				<td class="td_body" width="360px">${geDirectory.eid}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">�Ƿ�������</td>
				<td class="td_body" width="360px">
					<c:if test="${geDirectory.isNetSale == '' }">--��ѡ��--</c:if>
					<c:if test="${geDirectory.isNetSale == '01' }">��</c:if>
					<c:if test="${geDirectory.isNetSale == '02' }">��</c:if>
				</td>
			</tr>	
			<tr>
				<td class="td_head" width="200px">��Ʒ����/���ִ��룺</td>
				<td class="td_body" width="360px">${geDirectory.coreProductCode}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">��Ʒ���ƣ�</td>
				<td class="td_body" width="360px">${geDirectory.productName}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">��Ʒ��ƣ�</td>
				<td class="td_body" width="360px">${geDirectory.coreProductSimpleName}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">ҵ������</td>
				<td class="td_body" width="360px">
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<c:if test="${geDirectory.businessArea == GeCode_businessArea.id.codeCode}">
							${GeCode_businessArea.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">��Ʒ������룺</td>
				<td class="td_body" width="360px">${geDirectory.productQuoteCode}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">����������</td>
				<td class="td_body" width="360px">
					<c:forEach items="${saleChannelList}" var="GeCode_saleChannel" step="1" varStatus="status">
						<c:if test="${geDirectory.saleChannel == GeCode_saleChannel.id.codeCode}">
							${GeCode_saleChannel.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>	
			<tr>
				<td class="td_head" width="200px">�б���ʽ��</td>
				<td class="td_body" width="360px">
					<c:forEach items="${acceptInsuranceList}" var="GeCode_acceptInsurance" step="1" varStatus="status">
						<c:if test="${geDirectory.acceptInsurance == GeCode_acceptInsurance.id.codeCode}">
							${GeCode_acceptInsurance.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">��Ʒ��Ŀ��</td>
				<td class="td_body" width="360px">
					<c:forEach items="${productSectionList}" var="GeCode_productSection" step="1" varStatus="status">
						<c:if test="${geDirectory.productSection == GeCode_productSection.id.codeCode}">
							${GeCode_productSection.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">������� ��</td>
				<td class="td_body" width="360px">
					<c:forEach items="${itemTypeList}" var="GeCode_itemType" step="1" varStatus="status">
						<c:if test="${geDirectory.itemType == GeCode_itemType.id.codeCode}">
							${GeCode_itemType.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">�������ڣ�</td>
				<td class="td_body" width="360px">${geDirectory.publishDate}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">ͣ�����ڣ�</td>
				<td class="td_body" width="360px">${geDirectory.stopDate}</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">�������ޣ�</td>
				<td class="td_body" width="360px">
					<c:forEach items="${riskLimitList}" var="GeCode_riskLimit" step="1" varStatus="status">
						<c:if test="${geDirectory.riskLimit == GeCode_riskLimit.id.codeCode}">
							${GeCode_riskLimit.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_head" width="200px">��Ʒ�Ƽ����֣�</td>
				<td class="td_body" width="360px">
					<c:forEach items="${productRecommendList}" var="GeCode_productRecommend" step="1" varStatus="status">
						<c:if test="${geDirectory.productRecommend == GeCode_productRecommend.id.codeCode}">
							${GeCode_productRecommend.id.codeCode == '00' ? '--��ѡ��--' : GeCode_productRecommend.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div class="frmCreate_kuang" style="margin-left:100px;width:580px;margin-top:15px;">
						<div class="frmCreate_kuang_header">��Ӳ�ƷͼƬ</div>
						<div style="padding-left:3px; padding-top:15px;">
							<table>
								<tr>
									<td class="td_head" width="120px">СͼƬ1��</td>
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
									<td class="td_head" width="120px">СͼƬ2��</td>
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
									<td class="td_head" width="120px">��ͼƬ1��</td>
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
									<td class="td_head" width="120px">��ͼƬ2��</td>
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
									<td class="td_head" width="120px">��ͼƬ1��</td>
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
									<td class="td_head" width="120px">��ͼƬ2��</td>
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
						<div class="frmCreate_kuang_header">��Ӳ�Ʒ��ϸ</div>
						<div style="padding-left:3px; padding-top:15px;">
							<table id="productDetail">
								<tr>
									<td class="td_head" width="120px">��Ʒ��飺</td>
									<td class="td_body" width="470px">${geDirectory.productSummary}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">��Ʒ��ɫ��</td>
									<td class="td_body" width="470px">${geDirectory.productFeature}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">��Ʒ���</td>
									<td class="td_body" width="470px">${geDirectory.productArticleDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">�������䣺</td>
									<td class="td_body" width="470px">${geDirectory.premiumRange}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">���ս��������</td>
									<td class="td_body" width="470px">${geDirectory.insuranceAmountDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">�����ڼ䣺</td>
									<td class="td_body" width="470px">${geDirectory.insurancePeriodDesc}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">�ɷѷ�ʽ ��</td>
									<td class="td_body" width="470px">${geDirectory.payType}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">�������棺</td>
									<td class="td_body" width="470px">${geDirectory.securityInterest}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">������Ⱥ��</td>
									<td class="td_body" width="470px">${geDirectory.applyPeople}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">�б�����</td>
									<td class="td_body" width="470px">${geDirectory.underwritingObject}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">Ͷ����Χ��</td>
									<td class="td_body" width="470px">${geDirectory.insureRange}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px">Ͷ�����䣺</td>
									<td class="td_body" width="470px">${geDirectory.insureAge}</td>
								</tr>
								<tr>
									<td class="td_head" width="120px" style="text-align: right;">������֪��</td>
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
							onmouseout="this.className='btnBigOnBlur'">������</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_T">
						<td onclick="recommendProduct('${geDirectory.eid}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">��Ʒ�Ƽ�</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doEdit('${geDirectory.eid}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">�༭</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_PD">
						<td onclick="productDetial('all', '${geDirectory.eid}', '${geDirectory.isNetSale}', '${geDirectory.productSection}');" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">��Ʒ��ϸ</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_NPD">
						<td onclick="productDetial('wssc', '${geDirectory.eid}', '${geDirectory.isNetSale}', '${geDirectory.productSection}');" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">������Ʒ��ϸ</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_M_PT">
						<td onclick="productTerms('${geDirectory.eid}', '${geDirectory.productSection}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">��Ʒ��������</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_S">
						<td onclick="productShelve('${geDirectory.eid}', '01');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">�ϼ�</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_X">
						<td onclick="productShelve('${geDirectory.eid}', '02');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">�¼�</td>
					</acc:showView>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;height:1px;"></iframe>
	</div>
	<script type="text/javascript">
		//�༭
		function doEdit(idStr){
			location.href = "${ctx}/productDirectory/findGeDirectoryByEId.do?geDirectory.eid=" + idStr;		
		}
		
		//������
		function bind_Attribute(eId) {
			location.href = "${ctx}/productDirectory/findGeDirectoryAttributeRelateByEId.do?treeId=0&eId=" + eId;
		}
		
		//��Ʒ�Ƽ�
		function recommendProduct(eId) {
			location.href = "${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/index.jsp?eId=" + eId;
		}
		
		//��Ʒ��������
		function productTerms(idStr, productSection){
			if (productSection == "01") {
				//����
				window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + idStr + "&sell=tk&productSection=01","", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");	
			} else {
				//��ҵ
				window.open("http://10.0.2.166/admin/cms/web/search_product.jsp?pid=" + idStr + "&sell=tk&productSection=02", "", "top=70, left=180, width=900, height=600, scrollbars, resizable=yes");
			} 
		}
		//��Ʒ��ϸ
		function productDetial(obj,eId, isNetSale, productSection) {
				if (obj == "wssc") {
					if (isNetSale != "01") {
						alert("������Ʒ��ϸֻ�ܶ�������Ʒ������");
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
		
		//��Ʒ�ϼ��¼�
		function productShelve(eid, isProductShelf) {
				 $("#operatorForm")[0].action = "${ctx}/productDirectory/updateProductShelf.do?eid=" + eid + "&isProductShelf=" + isProductShelf;
				 $("#operatorForm")[0].submit();
		}
		
		//ɾ��
		function doDelete(){
			if(!getId()){
				return;
			}
			if (count == 0) {
				alert("��ѡ��Ҫɾ���Ĳ�Ʒ��");
				return;
			}
			
			if (count > 1) {
				alert("ֻ�ܶԵ�����Ʒ���в�����");
				return;
			}
			
			if(confirm("ȷ��ɾ����ѡ�е�"+count+"����Ʒ��")){
				window.parent.frames[3].location.href = "${ctx}/productDirectory/delProductDirectory.do?eid=" + idStr;
			}
		}
		
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
</body>
</html>
