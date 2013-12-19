<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<title>电子商务后台管理系统-产品推荐</title>
	<style type="text/css">
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
				产品推荐
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<center>
		<div style="padding-top:10mm;padding-left:10mm;">
			<table width="100%" class="table_Show" id="data_table">
				<tr>
					<td>
						<table class="table_Show" id="data_table" width="400px">
							<tr>
								<td>
									<form id="recommendProductQueryForm" action="${ctx}/productDirectory/findNoRecommendProduct.do" method="post" target="noRecommendProduct">
										<table class="table_style" align="center" width="400px">
											<tr>
												<td class="td_head" width="120" nowrap>
													电子商务产品ID：   
												</td>
												<td class="td_body"> <input type="text" id="noRecommendProductEId" name="noRecommendProductEId" style="width:170px;" maxlength="25">
												</td>
												<td class="td_head td_head_center" width="120" nowrap>
													业务领域：   
												</td>
												<td class="td_body">
													<select id="businessArea" name="businessArea" onchange="" style="width: 80px;">
														<option value="" selected>--请选择--</option>
													</select>
												</td>
											</tr>
											<tr>
												<td class="td_head" width="160" nowrap>
													产品代码/险种代码：
												</td>
												<td class="td_body" colspan="3">
													<input type="text" id="noRecommendProductCoreProductCode" name="noRecommendProductCoreProductCode" style="width:170px;" maxlength="25">
												</td>
											</tr>
											<tr height="60px;">
												<td colspan="4" align="center">
													<table>
														<tr>
															<td id="noRecommendProductQuery"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" onclick="doSearch()" nowrap>查询</td>
															<td id="noRecommendProductClear" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" align="right" nowrap>清空</td>
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
					<td align="center" width="400px" rowspan="2">
						<table  width="90px" height="40px" class="table_Show" id="data_table">
							<tr>
								<td id="addRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">增加&gt&gt</td>
							</tr>
							<tr height="10px">
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td id="removeRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">&lt&lt移除</td>
							</tr>
						</table>
					</td>
					<td>
						<table class="table_Show" id="data_table" width="600px">
							<tr>
								<td>
									<form id="saveRecommendProductForm" name="saveRecommendProductForm" action="${ctx}/productDirectory/addRecommendProduct.do" method="post" target="postTargetIframe">
										<table class="table_style" align="center" width="600px">
											<tr>
												<td colspan="4" align="center">
													<table>
														<tr height="50px">
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td id="saveRecommendProduct"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" nowrap>保存</td>
															<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
																onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/productDirectory/productDetail.do?geDirectory.eid=${param.eId}';">返回</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<input type="hidden" id="productEId" name="productEId" value="" />
										<input type="hidden" id="recommendProducts" name="recommendProducts" value="" />
									</form>
								</td>
							</tr>
						</table>
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
	</center>
	<div style="display: none;">
		<select	id="selectCloneTarget">
			<option value='1'>1</option>
			<option value='2'>2</option>
			<option value='3'>3</option>
			<option value='4'>4</option>
			<option value='5'>5</option>
			<option value='6'>6</option>
			<option value='7'>7</option>
			<option value='8'>8</option>
			<option value='9'>9</option>
			<option value='10'>10</option>
		</select>
	</div>
	<iframe name="postTargetIframe"  style="display:none;"></iframe>
	<script type="text/javascript">
		
		var noRecommendProduct = document.getElementById("noRecommendProduct");
		var recommendProduct = document.getElementById("recommendProduct");
		var idStr = "";
		var count = 0;
		function getId(obj) {
			try{
				obj.contentWindow.checkSingleRow();
				idStr = document.getElementById("idStr").value;
				count = document.getElementById("count").value;
				return true;
			} catch (e) {
				alert("请选择要操作的记录！");
				return false;
			}
		}
		
		$(document).ready(function(){
			
			noRecommendProduct.src = "${ctx}/productDirectory/findNoRecommendProduct.do?eId=${param.eId}";
			recommendProduct.src = "${ctx}/productDirectory/findRecommendProduct.do?eId=${param.eId}";
			$("#open_titleDIV").css("width",  document.body.scrollWidth);
			$("#noRecommendProductQuery").click(function(){
				$("#recommendProductQueryForm").submit();
			});
			
			$("#noRecommendProductClear").click(function(){
				$(':input')   
				 .not(':button, :submit, :reset, :hidden')   
				 .val('')   
				 .removeAttr('checked')   
				 .removeAttr('selected');	
			});
			
			$("#addRecommendProduct").click(function(){
				
				getId(noRecommendProduct);
				
				if (count > 0) {
					var noRecommendProductTrIdStrs = idStr.split(",");
					var trId = "";
					for (var i = 0; i < noRecommendProductTrIdStrs.length; i++) {
						trId = "#tr_" + noRecommendProductTrIdStrs[i];
						var correclation = "<td id='td_" + noRecommendProductTrIdStrs[i] + "' class='td_border_body_left_top_none'><select id='select_" + noRecommendProductTrIdStrs[i] + "'>"+$("#selectCloneTarget").html()+"</select></td>"
						var appendHTML = "<tr id='tr_" + noRecommendProductTrIdStrs[i] + "' class='search_tr_selected'>"+ $("#noRecommendProduct").contents().find(trId).append(correclation).html() + "</tr>";
						$("#recommendProduct").contents().find("#recommendProductTable").append(appendHTML);
						$("#recommendProduct").contents().find("#" + noRecommendProductTrIdStrs[i]).attr("checked", true);
						$("#noRecommendProduct").contents().find(trId).remove();
						
					}
					
					if ($("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked") == "checked") {
						$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",false);
						$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",true);
					} 
					
				} else {
					alert("请选择要操作的记录！");
				}
			});
			
			$("#removeRecommendProduct").click(function(){
				getId(recommendProduct);
				if (count > 0) {
					var recommendProductTrIdStrs = idStr.split(",");
					var trId = "";
					for (var i = 0; i < recommendProductTrIdStrs.length; i++) {
						trId = "#tr_" + recommendProductTrIdStrs[i];
						tdId = "#td_" + recommendProductTrIdStrs[i];
						checkId = "#" + recommendProductTrIdStrs[i];
						var appendHTML =  "<tr id='tr_" + recommendProductTrIdStrs[i] + "'>" + $("#recommendProduct").contents().find(trId).html() + "</tr>";
						$("#noRecommendProduct").contents().find("#noRecommendProductTable").append(appendHTML);
						$("#noRecommendProduct").contents().find(trId).removeClass();
						$("#noRecommendProduct").contents().find(tdId).remove();
						$("#noRecommendProduct").contents().find(checkId).attr("checked",false); 
						$("#recommendProduct").contents().find(trId).remove();
					}
					
					if ($("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked") == "checked") {
						$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",false);
					} 
				} else {
					alert("请选择要操作的记录！");
				}
			});
			
			$("#saveRecommendProduct").click(function(){
				getId(recommendProduct);
				var recommendProductTrIdStrs = idStr.split(",");
				var recommendProducts = "";
				for (var i = 0; i < recommendProductTrIdStrs.length; i++) {
					correclation = $("#recommendProduct").contents().find("#select_" + recommendProductTrIdStrs[i]).val();
					if (i == recommendProductTrIdStrs.length -1) {
						recommendProducts += recommendProductTrIdStrs[i] + ":" + correclation;
					} else {
						recommendProducts += recommendProductTrIdStrs[i] + ":" + correclation + "|";
					}
					
				}
				$("#productEId").val("${param.eId}");
				$("#recommendProducts").val(recommendProducts);
				$("#saveRecommendProductForm").submit();
			});
			$.ajax({
				   cache :false,
				   type: "POST",
				   url: '${ctx}/productDirectory/findBusinessArea.do',
				   data: { perStr : ""},
				   dataType:"json",
				   success: function(data){
					   $("#businessArea").empty();
					   $("#businessArea").append("<option value=''>--请选择--</option>");
				    	for(var i = 0; i < data.length; i++){
				    		var baObj = data[i];
				    		$("#businessArea").append("<option value='" + baObj.code + "'>" + baObj.name + "</option>");
				    	}
					},
				   error:function(XMLHttpRequest, textStatus, errorThrown){
					   
				   }
			});
		});
		
	</script>
</body>
</html>