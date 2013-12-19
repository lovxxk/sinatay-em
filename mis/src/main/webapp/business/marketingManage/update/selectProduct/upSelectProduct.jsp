<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script src="${ctx}/business/marketingManage/update/selectProduct/upSelectProduct.js"></script>
	<title>查询产品</title>
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
				查询产品
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
									<form id="recommendProductQueryForm" action="" method="post" target="noRecommendProduct">
									<input type="hidden" value="<s:property value="eids"/>" id="eids"><%//用于更新%>
									<input type="hidden" value="${isParentFlag}" name="isParentFlag" id="isParentFlag"><%//从上个页带过来的地区ID%>
									
									<input type="hidden" value="${saleDept}" name="saleDept"><%//从上个页带过来的地区ID%>
									<input type="hidden" value="${deptName}" name="deptName" id="deptName"><%//从上个页带过来的地区deptName%>
									<input type="hidden" id="authorityid" name="saleDeptSelectCode" />
									<%//是父结点与子结点 %>
									
										<table class="table_style" id="queryProductRuleTable" align="center" width="200px">
											<tr>
												<td class="td_head" width="80" nowrap>
													产品类型：   
												</td>
												<td class="td_body"> 
													<%/*<input type="text" id="noRecommendProductEId" name="noRecommendProductEId" style="width:170px;" maxlength="25">*/%>
													<select id="netSaleProductType" name="netSaleProductType" style="width: 130px" onchange="netSaleProductTypeChange(this);">
														<c:if test="${businessAreaFlag==3}">
<!-- 															<option value="01" selected>车</option> -->
															<option value="02" selected>非车</option>
<!-- 															<option value="03">卡</option> -->
														</c:if>
														<c:if test="${businessAreaFlag==2}">
															<option value="02" selected>非车</option>
<!-- 															<option value="03">卡</option> -->
														</c:if>
														<c:if test="${businessAreaFlag==1}">
<!-- 															<option value="01" selected>车</option> -->
															<option value="02" selected>非车</option>
<!-- 															<option value="03">卡</option> -->
														</c:if>
														<c:if test="${businessAreaFlag==4}">
															<option value="02" selected>非车</option>
<!-- 															<option value="03">卡</option> -->
														</c:if>
													</select>
												</td>
												<td class="td_head td_head_center" width="120" nowrap id="trtd13">
													产品代码：  <%//会根据下拉列表 01险种代码  02产品代码  03 卡没有代码  所以显示卡产品名称%>
												</td>
												<td class="td_body" id="trtd14">
													<%//通过 jquery回写 %>
												</td>
											</tr>
											<tr height="40">
												<td class="td_head" width="80" nowrap id="trtd21">
													产品名称：<%//会根据下拉列表变得 %>
												</td>
												<td class="td_body" id="trtd22" colspan="3">
													<input type="text" id="noRecommendProductCoreProductCode" name="noRecommendProductCoreProductCode" style="width:170px;" maxlength="25">
												</td>
<!-- 												<td class="td_head td_head_center" width="120" nowrap valign="top" id="trtd23"> -->
<%-- 													地区：   	<%//会根据上个页面带过来的值变得 %> --%>
<!-- 												</td> -->
<!-- 												<td class="td_body" id="trtd24"> -->
<!-- 												</td> -->
											</tr>
											
											<tr height="60px;">
												<td colspan="4" align="center">
													<table>
														<tr style="line-height:180%;">
															<td id="noRecommendProductQuery"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" onclick="doSearch()" nowrap><span>查询</span></td>
															<td id="noRecommendProductClear" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" align="right" nowrap onclick="doClean();">清空</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<input type="hidden" id="eId" name="eId" value="${param.eId}">
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
								<td><div style="margin: 25px 0 0 -4px">
									<form id="saveRecommendProductForm" name="saveRecommendProductForm" action="${ctx}/productDirectory/addRecommendProduct.do" method="post" target="postTargetIframe">
										<table>
												<tr>
													<td id="saveRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" nowrap onclick="saveRecommendProduct();">提&nbsp;交</td>
												</tr>
												<tr height="50px">
														<td>&nbsp;</td>
												</tr>
														
													</table>
										<input type="hidden" id="productEId" name="productEId" value="" />
										<input type="hidden" id="recommendProducts" name="recommendProducts" value="" />
									</form>
								</div></td>
							</tr>
						</table>
					</td>
					<td>
<!--						<table class="table_Show" id="data_table" width="300px">-->
<!--							<tr>-->
<!--								<td>-->
<!--									<form id="saveRecommendProductForm" name="saveRecommendProductForm" action="${ctx}/productDirectory/addRecommendProduct.do" method="post" target="postTargetIframe">-->
<!--										<table class="table_style" align="center" width="600px">-->
<!--											<tr>-->
<!--												<td colspan="4" align="center">-->
<!--													<table>-->
<!--														<tr height="50px">-->
<!--															<td>&nbsp;</td>-->
<!--														</tr>-->
<!--														<tr>-->
<!--															<td id="saveRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--																onmouseout="this.className='btnBigOnBlur'" nowrap onclick="saveRecommendProduct();">添加</td>-->
<!--														</tr>-->
<!--													</table>-->
<!--												</td>-->
<!--											</tr>-->
<!--										</table>-->
<!--										<input type="hidden" id="productEId" name="productEId" value="" />-->
<!--										<input type="hidden" id="recommendProducts" name="recommendProducts" value="" />-->
<!--									</form>-->
<!--								</td>-->
<!--							</tr>-->
<!--						</table>-->
					</td>
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
	</center>
	
	<iframe name="postTargetIframe"  style="display:none;"></iframe>
	<script type="text/javascript">
	$(document).ready(function(){
			recommendProduct.src = "${ctx}/productDirectory/findRecommendProduct.do?eId=${param.eId}";
	}
	)
	</script>
</body>
</html>