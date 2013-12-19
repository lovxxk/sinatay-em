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
<%//tao �� %>
<script src="${ctx }/global/js/imgPreview/CJL.0.1.min.js" charset="utf-8"></script>
<script src="${ctx }/global/js/imgPreview/ImagePreviewd.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>��ѯ��ֵ����</title>
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
				��ѯ���Ϣ
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
		<td class="td_head"  nowrap>����ƣ�</td>
		<td class="td_body">
			<s:property value="geAddServiceActivity.activityName"/>
			<input type="hidden" id="geActivityId" name="geActivityId" value="<s:property value="geAddServiceActivity.activityId"/>">
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>����ݽ��ܣ�</td>
		<td class="td_body" style="word-break : break-all; width: 500px;overflow:hidden"  id="hh">
			<s:property value="geAddServiceActivity.activityContent"/>
		</td>
	</tr>
		<tr>
		<td class="td_head" nowrap>����������</td>
		<td class="td_body" style="word-break : break-all; width: 500px;overflow:hidden"  id="hh">
			${createDeptName}
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�������</td>
		<td class="td_body" >
			<s:property value="geAddServiceActivity.deptName"/>
			<input type="hidden" id="actdeptID" name="actdeptID" value="<s:property value="geAddServiceActivity.deptID"/>"/>
			<s:if test="geAddServiceActivity.status==3">
<!--				&nbsp;(����=<s:property value="geAddServiceActivity.deptID"/>)-->
					<input type="hidden" value="<s:property value="geAddServiceActivity.deptID"/>"/>
			</s:if>
			
		</td>
	</tr>		
	
	<tr>
		<td class="td_head" nowrap>���ʼʱ�䣺</td>
		<td class="td_body" >
			<s:date name="geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/>
			<input type="hidden" id="actStartDate" name="actStartDate" value="<s:date name="geAddServiceActivity.activityStartDate" format="yyyy-MM-dd"/>">
		</td>
	</tr>	
	
	<tr>
		<td class="td_head" nowrap>�����ʱ�䣺</td>
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
<!--		<div class="frmCreate_kuang_header">��ƷͼƬ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>-->
<!--		<div style="padding-left:3px; padding-top:15px;">-->
	<s:iterator value="geAddServiceActivity.geActivitiesPictures" var="geActivitiesPicture" status="i">
	<table>
	<tr id="uploadPicture${i.index+1}TR" style="display: none;">
		<td class="td_head" nowrap style="padding-left: 20px">�ϴ�ͼƬ${i.index+1}��</td>
		<td class="td_body" >
			<input type="hidden" name="hiddenImage" id="hiddenImage${i.index+1}"  value="<s:if test="#geActivitiesPicture.nooryes=='yes'">${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/></s:if>" >
			<img  src="${ctx}/<s:property value="#geActivitiesPicture.pictureUrl"/>"  width="200" height="100" id="uploadPicture${i.index+1}Preview">
			
		</td>
	</tr>
	<s:if test="#geActivitiesPicture.jumpUrl!=null">
	<tr>
		<td class="td_head" nowrap style="padding-left: 20px">��תURL��</td>
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
<!--		<td class="td_head" nowrap rowspan="1">����</td>-->
		<td class="td_body" >
			<div class="frmCreate_kuang" style="width:680px;">
			<div class="frmCreate_kuang_header">�����:<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
			<div style="padding-left:3px; padding-top:5px;">
			<table  id="tableInfo" width="100%" style="table-layout:fixed;">
			<s:iterator value="geAddServiceActivity.geActivitiesRules" var="geActivitiesRules" status="status">
			<tr id="${status.index+1}">
				<td>
					<table width="100%" style="border-collapse:collapse;">
					<tr><td colspan="4" height="30px"><span id="count" style="font-weight: bold;">����${status.index+1}</span></td></tr>
					
					<%//���� %>
					<tr>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<td width="135px"  align="right"><span style="text-align:right ;">���ѣ�</span></td>
					</s:if>
					<s:else>
					<td width="160px" align="right"><span style="text-align:right ;">���ѣ�</span></td>
					</s:else>
					<td width="500px">
					<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>
					</td>
					</tr>
					
					<%//���ѵ�ֵ %>
					<%//��ʾ���ѵ�ֵ %>
					<s:if test="#geActivitiesRules.premiumType==2||#geActivitiesRules.premiumType==3||#geActivitiesRules.premiumType==4||#geActivitiesRules.premiumType==5">
						<tr>
							<td width="10%" style="text-align: right;">���ѵ�ֵ<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>��</td>
							<td width="40%">
								<s:property value="#geActivitiesRules.peremiumValue"/>
							</td>
						</tr>	
					</s:if>
					<s:if test="#geActivitiesRules.premiumType==6">
						<tr>
							<td width="10%" style="text-align: right;">���ѵ�ֵ<s:property value="geCodePremiumTypeMap[#geActivitiesRules.premiumType]"/>��</td>
							<td width="40%">
								<s:property value="#geActivitiesRules.premiumRange1"/>&nbsp;<=���ѵ�ֵ<=&nbsp; <s:property value="#geActivitiesRules.premiumRange2"/>
							</td>
						</tr>	
					</s:if>
					
					
					<%//��Ʒ���� 1�������пͻ� 2.����ǰN��ͻ� 3�������пͻ� ����ʾ��Ʒ���� %>
					<s:if test="#geActivitiesRules.activityPattern==1||#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3">
						<tr >
					</s:if>
					<s:else><%//��Ϊ���ۺͳ齱��ʱ�� ����ʾ��Ʒ %>
						<tr style="display: none;">
					</s:else>
					
						<td width="10%" style="text-align: right;">��Ʒ���ƣ�</td>
						<td width="40%">
							<s:property value="#geActivitiesRules.itemName" escapeHtml="false"/>(��Ʒ����=<s:property value="#geActivitiesRules.itemID"/>)
							<s:if test="geAddServiceActivity.status==3">
							<input type="hidden" value="<s:property value="#geActivitiesRules.itemID"/>"/>
							</s:if>
						</td>
						</tr>
					
					
					<tr>
					<td style="text-align:right ;">���ʽ��</td>
					<td width="400px">
						<s:iterator value="geCodeActivityPatternList" var="geCodeActivityPattern">
						
							<s:if test="#geCodeActivityPattern.id.codeCode==#geActivitiesRules.activityPattern">
								<s:property value="#geCodeActivityPattern.codeCName"/>
								<input type="hidden"  id="actPattern" name="actPattern" value="<s:property value="#geActivitiesRules.activityPattern"/>"/>
								<s:if test="geAddServiceActivity.status==3">
<!--								&nbsp;(����=<s:property value="#geActivitiesRules.activityPattern"/>)-->
									<input type="hidden" value="<s:property value="#geActivitiesRules.activityPattern"/>"/>
								</s:if>
							</s:if>
							
						</s:iterator>
					</td>
					</tr>
					
					<%//n��ֵ  Ϊ2:����ǰN���ͻ� 3:�������ΪN�Ŀͻ� 4:����      ��ʾN��ֵ %>
					<%/*
					<s:if test="#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3||#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					
					<s:else>
					<tr style="display: none;">
					</s:else>
					*/ %>
					
					<%//�������� %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<td style="text-align:right ;">�������ͣ�</td>
					<td>
						<s:if test="#geActivitiesRules.discountType==01">������</s:if>
						<s:if test="#geActivitiesRules.discountType==02">���ۼ�</s:if>
					</td>
					</tr>
					
					<%//N��ֵ %>
					<s:if test="#geActivitiesRules.activityPattern==2||#geActivitiesRules.activityPattern==3||#geActivitiesRules.activityPattern==4">
					<tr>
					</s:if>
					<s:else>
					<tr style="display: none;">
					</s:else>
					<s:if test="#geActivitiesRules.activityPattern==2">
					<td style="text-align:right ;">ǰN��ֵ��</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==3">
					<td style="text-align:right ;">����N%��ֵ��</td>
					</s:if>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<td style="text-align:right ;">������(%)/���ۼ�(Ԫ)��</td>
					</s:if>
					<td>
						<s:property value="#geActivitiesRules.nvalue"/>
					</td>
					</tr>
					
					<%//�ۿ�ID(����) %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr style="display: none;">

					<td style="text-align:right ;"><!--�ۿ�ID(����)�� ---></td>
					<td style="word-wrap : break-word ; width: 500px" >
<!--						<s:property value="#geActivitiesRules.discountID"/>-->
					</td>
					</tr>
					</s:if>
					<%//��ʾ�������� %>
					<s:if test="#geActivitiesRules.activityPattern==4">
					<tr>
						<td style="text-align:right ;">��������������</td>
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
					<%//��ʾ�ӹ���Ʒ %>
					<s:if test="#geActivitiesRules.activityPattern==5">
					<tr>
						<td style="text-align:right ;">�ӹ���Ʒ��</td>
						<td><s:iterator value="#geActivitiesRules.geActivitiesShoppingProducts" var="geActivitiesShoppingProduct" status="status">
						${status.index+1}��<s:property value="#geActivitiesShoppingProduct.productName"/><br />
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
<!--		<td class="td_head" nowrap>��Ʒ���ͣ�</td>-->
		<td class="td_body"><!--	��ʼ---	-->
		<div class="frmCreate_kuang" style="width: 680px;">
		<div class="frmCreate_kuang_header">��Ʒ���ͣ�
		<span id="comChecked" style="color: #FF9000; font-weight: bold;"></span></div>
		<s:iterator value="geAddServiceActivity.geActivitiesProducts"
			var="geActivitiesProduct" status="c">
			<table width="670px">
				<tr style="line-height: 20px">
					<td width="40%">&nbsp;${c.index+1}��<s:property value="#geActivitiesProduct.productName" />
					<input type="hidden" id="coreProducteid" value="<s:property value="#geActivitiesProduct.eid" />">
					</td>
					<s:if test="#geActivitiesProduct.discountID!=null">
					<td width="60%" style= "word-break:break-all ; width: 500px" class="clazz" id="discountBr${c.index}" ><span style="font-weight: bold;"> �ۿ�ID��</span><s:property value="#geActivitiesProduct.discountID" />
					</td>
					</s:if>
				</tr>
			</table>
			<s:if test="geAddServiceActivity.status==3">			
<!--	&nbsp;(����=<s:property value="#geActivitiesProduct.eid" />)-->
			<input type="hidden" value="<s:property value="#geActivitiesProduct.eid" />"/>
			</s:if>
		</s:iterator>
		</div>
		<!--����--></td>
	</tr>
	<s:if test="#request.type==null">
		<%/*
		<tr>
			<td class="td_body" ><input type="button" value="���ӹ���" onclick="insertTableForUpdate();" ></td>
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
					<td id="createButton" align=right class="btnBigOnFocus"  onclick="audit('1');" nowrap>ͨ�� </td>
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
					<%//�߱���**************************************************************************%>
					<c:if test="${workFlowType!='workFlow'}">
					<%//�ύ���%>
					<s:if test="geAddServiceActivity.status==0"><%//�����״̬ %>
						<%//�༭%>
						<acc:showView source="ROLE_B_AAGA_U">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
							<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>','<s:property value="geAddServiceActivity.deptID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">�༭</td>
						</c:if>
						</acc:showView>
						<%//ɾ�� %>
						<acc:showView source="ROLE_B_AAGA_D">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
							<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">ɾ��</td>
						</c:if>
						</acc:showView>
						<%//�ύ��� %>
						<acc:showView source="ROLE_ROLE_B_AAGA_DV">
						<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
						<td onclick="submitApprovalMarketing('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">�ύ���</td>
						</c:if>
						</acc:showView>
					</s:if>
					
					<%//������������� %>
					<s:if test="geAddServiceActivity.status==1"><%//������״̬ %>
					<acc:showView source="ROLE_ROLE_B_AAGA_DC">
						<td onclick="doFinish('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}','<s:property value="geAddServiceActivity.activityName"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">�ύ</td>
					</acc:showView>
					<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
						<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">����</td>
					</acc:showView>
<!--					<acc:showView source="ROLE_ROLE_B_AAGA_DREPEAL">-->
<!--					<td onclick="doGiveUp('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--							onmouseout="this.className='btnBigOnBlur'">����</td>-->
<!--					</acc:showView>-->
					<acc:showView source="ROLE_B_AAGA_GiveUp">
					<td onclick="doQuit('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">����</td>
					</acc:showView>
					</s:if>
					
					<%//���� %>
					<s:if test="geAddServiceActivity.status==5"><%//���� %>
<!--						<acc:showView source="ROLE_B_AAGA_U">-->
<!--								<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--									onmouseout="this.className='btnBigOnBlur'">�༭</td>-->
<!--						</acc:showView>-->
<!--						-->
<!--						<acc:showView source="ROLE_B_AAGA_D">-->
<!--								<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--									onmouseout="this.className='btnBigOnBlur'">ɾ��</td>-->
<!--						</acc:showView>-->
						<%//�ر�%> 
						<acc:showView source="ROLE_ROLE_B_AAGA_AGAIN">
							<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>','<s:property value="geAddServiceActivity.deptID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">�༭</td>
						 	</c:if>
						 	<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">ɾ��</td>
							</c:if>
<!--						<td onclick="doEdit('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--								onmouseout="this.className='btnBigOnBlur'">�༭</td>-->
<!--						<td onclick="doDelete('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--								onmouseout="this.className='btnBigOnBlur'">ɾ��</td>-->
							<c:if test="${operatorCode eq geAddServiceActivity.createDeptId}">
								<td onclick="submitApprovalMarketing('<s:property value="geAddServiceActivity.activityId"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">�ύ���</td>
							</c:if>
					    </acc:showView>
					</s:if>
					</c:if>
					<%//�߹����� **************************************************************************%>
					<c:if test="${workFlowType=='workFlow'}">
					<acc:showView source="ROLE_ROLE_B_AAGA_DC">
						<td onclick="doFinish('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}','<s:property value="geAddServiceActivity.activityName"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">�ύ</td>
					</acc:showView>
					<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
						<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">����</td>
					</acc:showView>
<!--					<acc:showView source="ROLE_ROLE_B_AAGA_DREPEAL">-->
<!--					<td onclick="doGiveUp('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" -->
<!--							onmouseout="this.className='btnBigOnBlur'">����</td>-->
<!--					</acc:showView>-->
					<acc:showView source="ROLE_B_AAGA_GiveUp">
					<td onclick="doQuit('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">����</td>
					</acc:showView>
					</c:if>
					<s:if test="geAddServiceActivity.status==3"><%//�ѷ���%>
						<acc:showView source="ROLE_ROLE_B_AAGA_DBACK">
							<td onclick="doRoolBack('<s:property value="geAddServiceActivity.activityId"/>','${taskId}','${workflowId}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">��������</td>
						</acc:showView>
					</s:if>
					</c:if>
					<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">�ر�</td>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;"></iframe>
	</div>
	<script type="text/javascript">
		//�ύ��˶�Ӧ�� �¼� 
		function submitApprovalMarketing(activityId){
		
				$.ajax({
			  		  type: "POST",
			  		  async:false,
			  		  url: contextRootPath+'/marketing/submitApplyMarketing.do',
			  		  dataType: 'text',
			  		  data:{"activityId":activityId},
			  		  success: function(backData){
			  			  if(backData == '01'){
			  				alert('�ύ��˳ɹ�!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
			  			  }else{
			  				alert('�ύ��˳����쳣');
			  				window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
			  			  }
			  		  }
			  		});	//end ajax
		}
	
		/**
		   * �������������
		   */
		//boolean doFinishFlag = true;
		function doFinish(activityId,taskId,workflowID,actName){
			if(!isProductAddServiceAct()){//����������Ʒ�Ƿ���ڸû����  �µĲ�Ʒ  ԭ��isProductAddServiceExistForUpdate()
				return false;
			}else{
			//Ĭ��ִ��
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doFinish.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert(actName+'Ӫ����ѷ�����');
							window.parent.opener.parent.frames[0].doSearch();
							window.close();
						}else{
							alert('������̳����쳣!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					}
				});
			}
		}
		
		// ����
		function doRoolBack(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doRoolBack.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('�������!');
							window.parent.opener.parent.frames[0].doSearch();
							window.close();
						}else{
							alert('�������̳����쳣!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
	
		//����
		function doGiveUp(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doGiveUp.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('�������!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else if(backData == '02'){
							alert('��ǰ�ڵ㲻�ܻ���!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else{
							alert('���˹��̳����쳣!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
		//����
		function doQuit(activityId,taskId,workflowID){
				$.ajax({
					type:"post",
					async:false,
					url: contextRootPath+'/marketing/doQuit.do',
					dataType:'text',
					data:{"activityId":activityId,"taskId":taskId,"workflowId":workflowID},
					success:function(backData){
						if(backData == '01'){
							alert('�������!');
			  				window.parent.opener.parent.frames[0].doSearch();
			  				window.close();
						}else{
							alert('�������̳����쳣!');
							window.close();
			  				window.parent.opener.parent.frames[0].doSearch();
						}
					},
					error:function(dd){
						alert(dd);
					}
				});	
		}
		//�༭
		function doEdit(idStr,actDept){
			location.href = "${ctx}/marketing/prepareUpdateAddGeAddServiceActivity.do?activityId=" + idStr+"&dept="+actDept;
			//location.href = "${ctx}/productDirectory/findGeDirectoryByEId.do?geDirectory.eid=" + idStr;		
		}
		
		
		//ɾ��
		function doDelete(idStr){
			if(confirm("ȷ��ɾ����ǰ���")){
				location.href = "${ctx}/marketing/deleteAddGeAddServiceActivity.do?activityId=" + idStr;
				window.parent.opener.parent.frames[0].doSearch();
			}
		}
		
		//�ر�
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
			ImagePreview.MODE = "simple";//����simplleģʽ
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
	//�����ۿ�id
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
	//�������ӳ�������
	if(document.getElementById("RemarkText")!=null){
		 var obj1=document.getElementById("RemarkText");
		    var contentStr1=obj1.innerHTML;
		    
		    var strContent1=contentStr1.replace(/\��/g,',');
		    var strTemp1="";
		    while(strContent1.length>10){
		          strTemp1+=strContent1.substr(0,83)+"<br />"; 
		          strContent1=strContent1.substr(83,strContent1.length); 
		    }
		    strTemp1+=" "+strContent1;
		    obj1.innerHTML=strTemp1;
	}
	//����ݳ�������
	if(document.getElementById("hh")!=null){
	    var obj=document.getElementById("hh");
	    var contentStr=obj.innerHTML;
	    
	    var strContent=contentStr.replace(/\��/g,',');
	    
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
	var coreProductNames = document.getElementsByName("coreProducteid");//ȡ��Ʒ
	var activityPatterns = document.getElementsByName("actPattern");//���ʽ
	var deptID = $("#actdeptID").val();
	var updatePage = "finishPage";
	var activityId = $("#geActivityId").val();
	var startDate = document.getElementsByName("actStartDate")[0].value;//ȡ��ʼ����
	var endDate = document.getElementsByName("actEndDate")[0].value;//ȡ��������
	//�����鸳ֵ
	for(var i=0;i<coreProductNames.length;i++){
		coreProductNamesValue = coreProductNamesValue+coreProductNames[i].value +",";
	}
	for(var i=1;i<activityPatterns.length;i++){
		activityPatternsValue = activityPatternsValue+activityPatterns[i].value+",";
	}
	//ȥ��","
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
	  						actStatus="״      ̬�����ѷ�����\r";
	  					  }else{
	  						actStatus="";
	  					  }
  					error = error+" \r"+"����ƣ�"+data[i].activityName+"\r"+"��Ʒ���ͣ�"+data[i].productName+"\r"+"����ڣ�("+data[i].startDate+"-"+data[i].endDate+")�ϴ��ڽ���\r"+actStatus;
  				  }
  				  if(data.length==0){
  					flag= true;
  				  }else{
  					//error = error+"�Ѿ����� �벻Ҫѡ��   ";
	  				//alert(error);
  					 alert("��ǰ��룺"+error);
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