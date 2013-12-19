<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<script src="${ctx}/business/cmpProductManage/riskAndKindManage/kind/update/updateGeRelate/updateAddKind.js"></script>
	<title>查询附加险</title>
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
				查询附加险
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<center>
		<div  width="900px" style="padding-top:10mm; padding-left:10mm;">
			<table width="100%" class="table_Show" id="data_table" >
				<tr>
					<td>
						<table class="table_Show" id="data_table" width="500px">
							<tr>
								<td>
									<form id="recommendProductQueryForm" action="${ctx}/business/cmpProductManage/riskAndKindManage/addKindList.do" method="post" target="noRecommendProduct">
									<%//是父结点与子结点 %>
										<table class="table_style" id="queryProductRuleTable" align="center" width="450px">
											<tr>
												<td class="td_head" width="10%" nowrap>
													险种代码：
												</td>
												<td class="td_body" width="25%" >
													<input type="text" value="${param.riskCode}" readonly="readonly" name="geKind.id.riskCode" style="width:120px;" maxlength="4" id="riskV">
												</td>
												<td class="td_head td_head_center" width="10%" nowrap>
													险别代码：
												</td>
												<td class="td_body" width="25%" >
													<input type="text" id="cleanKindCode" name="geKind.id.kindCode" style="width:120px;" maxlength="7">
												</td>
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
        <input type="hidden" name="kinds" id="kinds" />
        <input type="hidden" name="riskCodes" id="riskCodes" />
		<input type="hidden" name="productNames" id="productNames" />
		<input type="hidden" name="idsValue" id="idsValue" value="${param.idsValue}"/>
	</center>
	
	<iframe name="postTargetIframe"  style="display:none;"></iframe>
</body>
<script type="text/javascript">

</script>
</html>