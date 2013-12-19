<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
<%//tao 哥 %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>查询增值服务</title>
<style type="text/css">
	td {
		vertical-align:top;
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
<body onload="addPreviewFaceInit();">
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				查询活动信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div style="padding-top:15px;">
<table class="table_style" align="center" width="800px" id="productTable">
	<tr>
	<td colspan="2">
<!--	<div class="frmCreate_kuang" style="width:650px;padding-bottom: 15px;">-->
		<div style="padding-left:3px; padding-top:15px;">
		<table>
	<tr>
		<td class="td_head"  nowrap>活动名称：</td>
		<td class="td_body">
			<s:property value="geAddServiceActivity.activityName"/>
			<input type="hidden" id="geActivityId" name="geActivityId" value="<s:property value="geAddServiceActivity.activityId"/>">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动内容介绍：</td>
		<td class="td_body" style="word-break : break-all; width: 500px;overflow:hidden"  id="hh">
			<s:property value="geAddServiceActivity.activityContent"/>
		</td>
	</tr>
		<tr>
		<td class="td_head" nowrap>创建机构：</td>
		<td class="td_body" style="word-break : break-all; width: 500px;overflow:hidden"  id="hh">
			${createDeptName}
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动地区：</td>
		<td class="td_body" >
			<s:property value="geAddServiceActivity.deptName"/>
			<input type="hidden" id="actdeptID" name="actdeptID" value="<s:property value="geAddServiceActivity.deptID"/>"/>
			<s:if test="geAddServiceActivity.status==3">
<!--				&nbsp;(代码=<s:property value="geAddServiceActivity.deptID"/>)-->
					<input type="hidden" value="<s:property value="geAddServiceActivity.deptID"/>"/>
			</s:if>
			
		</td>
	</tr>		
	
	<tr>
		<td class="td_head" nowrap>活动起始时间：</td>
		<td class="td_body" >
			<s:date name="geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/>
			<input type="hidden" id="actStartDate" name="actStartDate" value="<s:date name="geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/>">
		</td>
	</tr>	
	
	<tr>
		<td class="td_head" nowrap>活动结束时间：</td>
		<td class="td_body" >
			<s:date name="geAddServiceActivity.activityEndDate" format="yyyy-MM-dd"/>
			<input type="hidden" id="actStartDate" name="actEndDate" value="<s:date name="geAddServiceActivity.activityEndDate" format="yyyy-MM-dd"/>">
		</td>
	</tr>
	</table>
<!--		</div>-->
		</div>
	</td>
	</tr>
	<tr>
	<td class="td_body">
<!--	<div class="frmCreate_kuang" style="width:650px;padding-bottom: 15px;">-->
<!--		<div class="frmCreate_kuang_header">产品图片<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>-->
<!--		<div style="padding-left:3px; padding-top:15px;">-->
	<s:iterator value="geAddServiceActivity.geActivitiesPictures" var="geActivitiesPicture" status="i">
	<table>
	<tr id="uploadPicture${i.index+1}TR" style="display: none;">
		<td class="td_head" nowrap style="padding-left: 20px">上传图片${i.index+1}：</td>
		<td class="td_body" >
			<input type="hidden" name="hiddenImage" id="hiddenImage${i.index+1}"  value="<s:if test="#geActivitiesPicture.nooryes=='yes'">${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/></s:if>" >
			<img  src="${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/>"  width="200" height="100" id="uploadPicture${i.index+1}Preview">
			
		</td>
	</tr>
	<s:if test="#geActivitiesPicture.jumpUrl!=null">
	<tr>
		<td class="td_head" nowrap style="padding-left: 20px">跳转URL：</td>
		<td class="clazzJmup" id="clazzJmup${i.index}" nowrap   style= "word-break:break-all ; width: 600px;"><s:property value="#geActivitiesPicture.jumpUrl"/></td>
	</tr>
	</s:if>
	</table>
	</s:iterator>
<!--	</div>-->
<!--	</div>-->
		</td>
	</tr>
	<tr>
<!--		<td class="td_head" nowrap rowspan="1">规则：</td>-->
		<td class="td_body" >
			<div class="frmCreate_kuang" style="width:680px;">
			<div class="frmCreate_kuang_header">活动规则:<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-left:3px; padding-top:5px;">
			<table  id="tableInfo" width="100%" style="table-layout:fixed;">
			<s:iterator value="geAddServiceActivity.geActivitiesRules" var="geActivitiesRules" status="status">
			<tr id="${status.index+1}">
				<td>
					<table width="100%" style="border-collapse:collapse;">
					<tr><td colspan="4" height="30px"><span id="count" style="font-weight: bold;">规则${status.index+1}</span></td></tr>
					
					<%//保费 %>
					<tr>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<td width="135px"  align="right"><span style="text-align:right ;">保费：</span></td>
					</s:if>
					<s:else>
					<td width="160px" align="right"><span style="text-align:right ;">保费：</span></td>
					</s:else>
					<td width="500px">
					<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>
					</td>
					</tr>
					
					<%//保费的值 %>
					<%//显示保费的值 %>
					<s:if test="#geActivitiesRules.premiumType==2||#geActivitiesRules.premiumType==3||#geActivitiesRules.premiumType==4||#geActivitiesRules.premiumType==5">
						<tr>
							<td width="10%" style="text-align: right;">保费的值<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>：</td>
							<td width="40%">
								<s:property value="#geActivitiesRules.peremiumValue"/>
							</td>
						</tr>	
					</s:if>
					<s:if test="#geActivitiesRules.premiumType==6">
						<tr>
							<td width="10%" style="text-align: right;">保费的值<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>：</td>
							<td width="40%">
								<s:property value="#geActivitiesRules.premiumRange1"/>&nbsp;<=保费的值<=&nbsp; <s:property value="#geActivitiesRules.premiumRange2"/>
							</td>
						</tr>	
					</s:if>
					
					
					<%//商品名称 1面向所有客户 2.面向前N向客户 3面向所有客户 都显示商品名称 %>
					<s:if test="#geActivitiesRules.activityPattern==1||#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3">
						<tr >
					</s:if>
					<s:else><%//当为打折和抽奖的时候 不显示商品 %>
						<tr style="display: none;">
					</s:else>
					
						<td width="10%" style="text-align: right;">商品名称：</td>
						<td width="40%">
							<s:property value="#geActivitiesRules.itemName" escapeHtml="false"/>(商品代码=<s:property value="#geActivitiesRules.itemID"/>)
							<s:if test="geAddServiceActivity.status==3">
							<input type="hidden" value="<s:property value="#geActivitiesRules.itemID"/>"/>
							</s:if>
						</td>
						</tr>
					
					
					<tr>
					<td style="text-align:right ;">活动方式：</td>
					<td width="400px">
						<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
						
							<s:if test="#geCodeActivityPattern.id.codeCode==#geActivitiesRules.activityPattern">
								<s:property value="#geCodeActivityPattern.codeCName"/>
								<input type="hidden"  id="actPattern" name="actPattern" value="<s:property value="#geActivitiesRules.activityPattern"/>"/>
								<s:if test="geAddServiceActivity.status==3">
<!--								&nbsp;(代码=<s:property value="#geActivitiesRules.activityPattern"/>)-->
									<input type="hidden" value="<s:property value="#geActivitiesRules.activityPattern"/>"/>
								</s:if>
							</s:if>
							
						</s:iterator>
					</td>
					</tr>
					
					<%//n的值  为2:面向前N名客户 3:面向概率为N的客户 4:打折      显示N的值 %>
					<%/*
					<s:if test="#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3||#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					
					<s:else>
					<tr style="display: none;">
					</s:else>
					*/ %>
					
					<%//打折类型 %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<td style="text-align:right ;">打折类型：</td>
					<td>
						<s:if test="#geActivitiesRules.discountType==01">打折率</s:if>
						<s:if test="#geActivitiesRules.discountType==02">打折价</s:if>
					</td>
					</tr>
					
					<%//N的值 %>
					<s:if test="#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3||#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<s:if test="#geActivitiesRules.activityPattern==2">
					<td style="text-align:right ;">前N的值：</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==3">
					<td style="text-align:right ;">概率N%的值：</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<td style="text-align:right ;">打折率(%)/打折价(元)：</td>
					</s:if>
					<td>
						<s:property value="#geActivitiesRules.nvalue"/>
					</td>
					</tr>
					
					<%//折扣ID(寿险) %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr style="display: none;">

					<td style="text-align:right ;"><!--折扣ID(寿险)： ---></td>
					<td style="word-wrap : break-word ; width: 500px" >
<!--						<s:property value="#geActivitiesRules.discountID"/>-->
					</td>
					</tr>
					</s:if>
					<%//显示打折因子 %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr>
						<td style="text-align:right ;">打折因子描述：</td>
						<td>
						<%/*
						<textarea rows="4" cols="60" ><s:property value="#geActivitiesRules.discountRemarkText"/></textarea>
						*/ %>
						<table align="left">
							<tr>
							<td style="word-wrap : break-word ; width: 500px; " id="RemarkText">
							<s:property value="#geActivitiesRules.discountRemarkText"/>
							</td>
							</tr>
						</table>
						</td>					
					</tr>
					</s:if>
					<%//显示加购产品 %>
					<s:if test="#geActivitiesRules.activityPattern==5">
					<tr>
						<td style="text-align:right ;">加购产品：</td>
						<td><s:iterator value="#geActivitiesRules.geActivitiesShoppingProducts" var="geActivitiesShoppingProduct" status="status">
						${status.index+1}、<s:property value="#geActivitiesShoppingProduct.productName"/><br />
						</s:iterator>
						</td>					
					</tr>
					</s:if>
					</table>
				</td>
			</tr>
			
			</s:iterator>
			</table>
			</div>
			</div>
		</td>
	</tr>
	<tr id="productType">
<!--		<td class="td_head" nowrap>产品类型：</td>-->
		<td class="td_body"><!--	开始---	-->
		<div class="frmCreate_kuang" style="width: 680px;">
		<div class="frmCreate_kuang_header">产品类型：
		<span id="comChecked" style="color: #FF9000; font-weight: bold;"></span></div>
		<s:iterator value="geAddServiceActivity.geActivitiesProducts"
			var="geActivitiesProduct" status="c">
			<table width="670px">
				<tr style="line-height: 20px">
					<td width="40%">&nbsp;${c.index+1}、<s:property value="#geActivitiesProduct.productName" />
					<input type="hidden" id="coreProducteid" value="<s:property value="#geActivitiesProduct.eid" />">
					</td>
					<s:if test="#geActivitiesProduct.discountID!=null">
					<td width="60%" style= "word-break:break-all ; width: 500px" class="clazz" id="discountBr${c.index}" ><span style="font-weight: bold;"> 折扣ID：</span><s:property value="#geActivitiesProduct.discountID" />
					</td>
					</s:if>
				</tr>
			</table>
			<s:if test="geAddServiceActivity.status==3">			
<!--	&nbsp;(代码=<s:property value="#geActivitiesProduct.eid" />)-->
			<input type="hidden" value="<s:property value="#geActivitiesProduct.eid" />"/>
			</s:if>
		</s:iterator>
		</div>
		<!--结束--></td>
	</tr>
	<s:if test="#request.type==null">
		<%/*
		<tr>
			<td class="td_body" ><input type="button" value="增加规则" onclick="insertTableForUpdate();" ></td>
		</tr>
		
		*/ %>
		<tr height=25><td></td></tr> 
	</s:if>
	<s:else>
		<tr>
			<td colspan=2>
				<table width=200 align="center">
<!--					<input type="hidden" name="type" id="type" value=""/>-->
<!--					<input type="hidden" name="taskID" value="${marktingWrokFlow.task.id }"/>-->
<!--					<input type="hidden" name="workFlowID" value="${marktingWrokFlow.workFlowID}"/>-->
<!--					<input type="hidden" name="taskID" value="${marktingWrokFlow.task.id }"/>-->
					
					<input type="hidden" name="taskID" value="${taskId}"/>
					<input type="hidden" name="workFlowID" value="${workFlowId}"/>
				<tr>
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="audit('1');" nowrap>通过 </td>
					<td width=5>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
	</s:else>
</table> 


</div>
<div style="padding-top:10px;padding-bottom:20px;padding-right: 300px" align="center">
		<form id="operatorForm" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0" id="operatorTable">
				<tr>
					<td onclick="javascript:maximizeGrid(this);">
						&nbsp;
					</td>			
					<c:if test="${!(param.bizType eq 'look')}">	
					<%//走本地**************************************************************************%>
					<c:if test="${workFlowType!='workFlow'}">
					<%//提交审核%>
					<s:if test="geAddServiceActivity.status==0"><%//待审核状态 %>
						<%//编辑%>
						<acc:showView source="ROLE_B_AAGA_U">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
							<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>','<s:property value="geAddServiceActivity.deptID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">编辑</td>
						</c:if>
						</acc:showView>
						<%//删除 %>
						<acc:showView source="ROLE_B_AAGA_D">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
							<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">删除</td>
						</c:if>
						</acc:showView>
						<%//提交审核 %>
						<acc:showView source="ROLE_ROLE_B_AAGA_DV">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
						<td onclick="submitApprovalMarketing('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">提交审核</td>
						</c:if>
						</acc:showView>
					</s:if>
					
					<%//工作流处理地中 %>
					<s:if test="geAddServiceActivity.status==1"><%//工作流状态 %>
					<acc:showView source="ROLE_ROLE_B_AAGA_DC">
						<td onclick="doFinish('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}','<s:property value="geAddServiceActivity.activityName"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">提交</td>
					</acc:showView>
					<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
						<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">撤销</td>
					</acc:showView>
<!--					<acc:showView source="ROLE_ROLE_B_AAGA_DREPEAL">-->
<!--					<td onclick="doGiveUp('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--							onmouseout="this.className='btnBigOnBlur'">回退</td>-->
<!--					</acc:showView>-->
					<acc:showView source="ROLE_B_AAGA_GiveUp">
					<td onclick="doQuit('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">放弃</td>
					</acc:showView>
					</s:if>
					
					<%//撤销 %>
					<s:if test="geAddServiceActivity.status==5"><%//回退 %>
<!--						<acc:showView source="ROLE_B_AAGA_U">-->
<!--								<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--									onmouseout="this.className='btnBigOnBlur'">编辑</td>-->
<!--						</acc:showView>-->
<!--						-->
<!--						<acc:showView source="ROLE_B_AAGA_D">-->
<!--								<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--									onmouseout="this.className='btnBigOnBlur'">删除</td>-->
<!--						</acc:showView>-->
						<%//关闭%> 
						<acc:showView source="ROLE_ROLE_B_AAGA_AGAIN">
							<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>','<s:property value="geAddServiceActivity.deptID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">编辑</td>
						 	</c:if>
						 	<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">删除</td>
							</c:if>
<!--						<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--								onmouseout="this.className='btnBigOnBlur'">编辑</td>-->
<!--						<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--								onmouseout="this.className='btnBigOnBlur'">删除</td>-->
							<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="submitApprovalMarketing('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">提交审核</td>
							</c:if>
					    </acc:showView>
					</s:if>
					</c:if>
					<%//走工作流 **************************************************************************%>
					<c:if test="${workFlowType=='workFlow'}">
					<acc:showView source="ROLE_ROLE_B_AAGA_DC">
						<td onclick="doFinish('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}','<s:property value="geAddServiceActivity.activityName"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">提交</td>
					</acc:showView>
					<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
						<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">撤销</td>
					</acc:showView>
<!--					<acc:showView source="ROLE_ROLE_B_AAGA_DREPEAL">-->
<!--					<td onclick="doGiveUp('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--							onmouseout="this.className='btnBigOnBlur'">回退</td>-->
<!--					</acc:showView>-->
					<acc:showView source="ROLE_B_AAGA_GiveUp">
					<td onclick="doQuit('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">放弃</td>
					</acc:showView>
					</c:if>
					<s:if test="geAddServiceActivity.status==3"><%//已发布%>
						<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
							<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">撤销发布</td>
						</acc:showView>
					</s:if>
					</c:if>
					<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">关闭</td>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;"></iframe>
	</div>
	<script type="text/javascript">
		//提交审核对应的 事件 
		function submitApprovalMarketing(activityId){
		
				$.ajax({
			  		  type: "POST",
			  		  async:false,
			  		  url: contextRootPath+'/marketing/submitApplyMarketing.do',
			  		  dataType: 'text',
			  		  data:{"activityId":activityId},
			  		  success: function(backData){
			  			  if(backData == '01'){
			  				alert('提交审核成功!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
			  			  }else{
			  				alert('提交审核出现异常');
			  				window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
			  			  }
			  		  }
			  		});	//end ajax
		}
	
		/**
		   * 工作流处理完成
		   */
		//boolean doFinishFlag = true;
		function doFinish(activityId,taskId,workflowID,actName){
			if(!isProductAddServiceAct()){//看看其它产品是否存在该活动类型  新的产品  原：isProductAddServiceExistForUpdate()
				return false;
			}else{
			//默认执行
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doFinish.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert(actName+'营销活动已发布！');
							window.parent.opener.parent.frames[0].doSearch();
							window.close();
						}else{
							alert('处理过程出现异常!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					}
				});
			}
		}
		
		// 撤销
		function doRoolBack(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doRoolBack.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('撤销完成!');
							window.parent.opener.parent.frames[0].doSearch();
							window.close();
						}else{
							alert('撤销过程出现异常!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
	
		//回退
		function doGiveUp(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doGiveUp.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('回退完成!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else if(backData == '02'){
							alert('当前节点不能回退!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else{
							alert('回退过程出现异常!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
		//回退
		function doQuit(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doQuit.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('放弃完成!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else{
							alert('放弃过程出现异常!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
		//编辑
		function doEdit(idStr,actDept){
			location.href = "${ctx}/marketing/prepareUpdateAddGeAddServiceActivity.do?activityId=" + idStr+"&dept="+actDept;
			//location.href = "${ctx}/productDirectory/findGeDirectoryByEId.do?geDirectory.eid=" + idStr;		
		}
		
		
		//删除
		function doDelete(idStr){
			if(confirm("确定删除当前活动吗？")){
				location.href = "${ctx}/marketing/deleteAddGeAddServiceActivity.do?activityId=" + idStr;
				window.parent.opener.parent.frames[0].doSearch();
			}
		}
		
		//关闭
		function pageclose(){
			window.close();
			window.parent.opener.parent.frames[0].doSearch();
		}	
		
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
</body>
<script type="text/javascript">
function addPreviewFaceInit(){
	toBreakWord();
	var hiddenImages = document.getElementsByName("hiddenImage");
	for(var i=0;i<hiddenImages.length;i++){
		if(hiddenImages[i].value!=""){
			ImagePreview.MODE = "simple";//让走simplle模式
			var facePic = new ImagePreview( $$(hiddenImages[i].id), $$("uploadPicture"+(i+1)+"Preview"), {maxWidth: 230, maxHeight: 160});
			document.getElementById("uploadPicture"+(i+1)+"TR").style.display="";
			//facePic.preview();
			
		}
	}
}
function doUpdate(){
	window.close();
}
function toBreakWord(){
	if($(".clazzJmup").length>0){
		for(var i=0;i<$(".clazzJmup").length;i++){
			if(document.getElementById("clazzJmup"+i)!=null){
				var obj2=document.getElementById("clazzJmup"+i);
				var strContent2=obj2.innerHTML;
			    var strTemp2="";
			    while(strContent2.length>10){
			          strTemp2+=strContent2.substr(0,95)+"<br />"; 
			          strContent2=strContent2.substr(95,strContent2.length); 
			    }
			    strTemp2+=" "+strContent2;
			    obj2.innerHTML=strTemp2;
			}
		}
	}
	//寿险折扣id
	if($(".clazz").length>0){
		for(var i=0;i<$(".clazz").length;i++){
			if(document.getElementById("discountBr"+i)!=null){
				var obj2=document.getElementById("discountBr"+i);
				var strContent2=obj2.innerHTML;
			    var strTemp2="";
			    while(strContent2.length>10){
			          strTemp2+=strContent2.substr(0,100)+"<br />"; 
			          strContent2=strContent2.substr(100,strContent2.length); 
			    }
			    strTemp2+=" "+strContent2;
			    obj2.innerHTML=strTemp2;
			}
		}
	}
	//打折因子超出换行
	if(document.getElementById("RemarkText")!=null){
		 var obj1=document.getElementById("RemarkText");
		    var contentStr1=obj1.innerHTML;
		    
		    var strContent1=contentStr1.replace(/\，/g,',');
		    var strTemp1="";
		    while(strContent1.length>10){
		          strTemp1+=strContent1.substr(0,83)+"<br />"; 
		          strContent1=strContent1.substr(83,strContent1.length); 
		    }
		    strTemp1+=" "+strContent1;
		    obj1.innerHTML=strTemp1;
	}
	//活动内容超出换行
	if(document.getElementById("hh")!=null){
	    var obj=document.getElementById("hh");
	    var contentStr=obj.innerHTML;
	    
	    var strContent=contentStr.replace(/\，/g,',');
	    
	    var strTemp="";
	    while(strContent.length>10){
	          strTemp+=strContent.substr(0,80)+"<br />"; 
	          strContent=strContent.substr(80,strContent.length); 
	    }
	    strTemp+=" "+strContent;
	    obj.innerHTML=strTemp;
	}
}
function isProductAddServiceAct(){
	var flag = false;
	var actStatus = "";
	var coreProductNamesValue = "";
	var activityPatternsValue = "";
	var coreProductNames = document.getElementsByName("coreProducteid");//取产品
	var activityPatterns = document.getElementsByName("actPattern");//活动方式
	var deptID = $("#actdeptID").val();
	var updatePage = "finishPage";
	var activityId = $("#geActivityId").val();
	var startDate = document.getElementsByName("actStartDate")[0].value;//取开始日期
	var endDate = document.getElementsByName("actEndDate")[0].value;//取结束日期
	//给数组赋值
	for(var i=0;i<coreProductNames.length;i++){
		coreProductNamesValue = coreProductNamesValue+coreProductNames[i].value +",";
	}
	for(var i=1;i<activityPatterns.length;i++){
		activityPatternsValue = activityPatternsValue+activityPatterns[i].value+",";
	}
	//去掉","
	coreProductNamesValue = coreProductNamesValue.substring(0,(coreProductNamesValue.length-1))
	activityPatternsValue = activityPatternsValue.substring(0,(activityPatternsValue.length-1))
	//alert("startDate*"+startDate+"endDate*"+endDate+"coreProductNames*"+coreProductNamesValue+"activityPatterns*"+activityPatternsValue+"deptID*"+deptID);
	//alert(coreProductNamesValue);
	$.ajax({
  		  type: "GET",
  		  async:false,
  		  url: contextRootPath+'/marketing/isProductAddServiceExist.do',
  		  data:"json",
  		  data: {'startDate':startDate,'endDate':endDate,'coreProductNames':coreProductNamesValue,'activityPatterns':activityPatternsValue,'activityId':activityId,'deptID':deptID,'updatePage':updatePage},
  		  success: function(data){
  			  data  = data.geCustomAddServiceActivityList;
  			  if(data!=null){
  				  var error = "";//productName
  				  for(var i=0;i<data.length;i++){
  					  
  					  if(data[i].status=='3'){
	  						actStatus="状      态：【已发布】\r";
	  					  }else{
	  						actStatus="";
	  					  }
  					error = error+" \r"+"活动名称："+data[i].activityName+"\r"+"产品类型："+data[i].productName+"\r"+"活动日期：("+data[i].startDate+"-"+data[i].endDate+")上存在交集\r"+actStatus;
  				  }
  				  if(data.length==0){
  					flag= true;
  				  }else{
  					//error = error+"已经存在 请不要选择   ";
	  				//alert(error);
  					 alert("当前活动与："+error);
  				  }
  			  }else{
  				flag= true;
  			  }
  		  }
	});//end $.ajax
	
	return flag;
}
</script>
</html>