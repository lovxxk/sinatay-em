<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript">
		//已增加的产品
		function recommendProductSrc(){
			var recommendProduct = document.getElementById("recommendProduct");
			$("#rightView").submit();
		}
		//弹出一个商品的提示框
		function alertThirdService(){
			window.open(contextRootPath+"/business/customerManage/party/thirdParterService/create/selectThirdParterInfo/index.jsp","查询供应商" ,"top=100, left=100, width=900,height=600,toolbar=no,resizable=yes");
		}
	</script>
	<script src="${ctx}/business/marketingManage/create/selectProduct/selectProductMore/moreProduct.js"></script>
	<title>电子商务管理系统-第三方合作伙伴</title>
	<style type="text/css">
		#queryProductRuleTable td {
			vertical-align:top;
		}
	</style>
</head>
<body onload="init();">
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				第三方产品维护
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<center>
		<div  width="900px"style="padding-top:10mm;padding-left:10mm;">
			<table width="100%" class="table_Show" id="data_table" >
				<tr>
					<td>
						<table class="table_Show" id="data_table" width="500px">
							<tr>
								<td>
								   <form id="recommendProductQueryForm" name="frmInput" method="post" action="${ctx}/party/findGeThirdParterService.do?opertion=marketing" target="noRecommendProduct">
											<input type="hidden" name="deptId" value="${param.deptId }">
											<table class="table_style" align="center" width="98%">
												<tr>
													<td class="td_head td_head_center" width="20%"  nowrap>
														商品名称：
														
													</td>
													<td class="td_body" width="20%" >
														<input type="text" name="geThirdParterService.itemName" style="width:170px;" >
													</td>
													<td class="td_head td_head_center" width="10%" nowrap>
														所在公司：
													</td>
													<td class="td_body" width="40%" >
													<input type="text" name="thirdParterName" style="width:170px;" readonly="readonly">
													<input type="hidden"  name="geThirdParterService.geThirdParterInfo.thirdParterID" >
													<input type="button" value="选择公司" onclick="alertThirdService();">
													</td>
												</tr>
												<tr height="60px;">
													<td colspan="8" align="center">
														<table id="operatorTable">
															<tr>
																<td nowrap>
																	<input type="hidden" name="pageNo" id="pageNo" value="1">
																	<input type="hidden" name="pageSize" id="pageSize" value="20">
																</td>
																<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																	onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>查询</td>
																<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																	onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>清空</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</form>
								</td>
							</tr>
						</table>
					</td>
					<td align="center" width="300px" rowspan="2">
						<table  width="90px" height="40px" class="table_Show" id="data_table">
							<tr>
								<td id="addRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="addRecommendProduct();">增加&gt&gt</td>
							</tr>
							<tr height="10px">
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td id="removeRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="removeRecommendProduct();">&lt&lt移除</td>
							</tr>
							<tr>
								<td><div style="margin: 25px 0 0 -5px">
									<form id="saveRecommendProductForm" name="saveRecommendProductForm" action="${ctx}/productDirectory/addRecommendProduct.do" method="post" target="postTargetIframe">
										<table class="table_style" align="center" width="">
											<tr>
												<td id="saveRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
														onmouseout="this.className='btnBigOnBlur'" nowrap onclick="saveRecommendProduct();">提&nbsp;交</td>
										 	</tr>
										</table>
									</form>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td>
				</tr>
				<tr>
					<td>
						<iframe id="noRecommendProduct" name="noRecommendProduct" src="" frameborder="0" width="600px" height="600px" marginwidth="0" marginheight="0" ></iframe>
					</td>
					<td>
						<iframe id="recommendProduct" name="recommendProduct" src="" frameborder="0" width="600px" height="600px" marginwidth="0" marginheight="0" ></iframe>
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" name="idStr" id="idStr" />
		<input type="hidden" name="count" id="count" />
		<input type="hidden" name="eids" id="eids" />
		<input type="hidden" name="productNames" id="productNames" />
		<input type="hidden" name="surplus" id="surplus" />
		<input type="hidden" id="nameCount" name="nameCount" value="${param.nameCount }"/>
	</center>
	<form id="rightView" action="${ctx }/party/prepareAddGeThirdParterService.do" method="post" target="recommendProduct">
		<input type="hidden" name="deptId" value="${param.deptId }">
		<input type="hidden" name="opertion" value="updateUserMarketing">
		<input type="hidden" name="nameCount" value="${param.nameCount }">
		<input type="hidden" name="itemId" value="${param.itemId }">
	</form>
	<iframe name="postTargetIframe"  style="display:none;"></iframe>
</body>
</html>