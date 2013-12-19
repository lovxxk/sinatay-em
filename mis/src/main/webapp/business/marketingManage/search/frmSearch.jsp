<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>�����������ϵͳ-��ֵ����</title>
	<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
		input,select {
			width:170px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
 	<c:if test="${!(param.bizType eq 'look')}">	
	<div class="public_fun_title">
	Ӫ���ά��<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	</c:if>
	<c:if test="${(param.bizType eq 'look')}">	
	<div class="public_fun_title">
	Ӫ����鿴<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	</c:if>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/marketing/findAddGeAddServiceActivity.do" target="fraSearchList">
			<table class="table_style"  style="width:700px">
				<tr>
				    <td class="td_head" width="80px" nowrap >
						����ƣ�
					</td>
					<td class="td_body" width="120px">
						<input type="text"  name="geAddServiceActivity.activityName" style="width:120px;" >
					</td>
					<td class="td_head " width="80px" nowrap style="display: none;">
					�״̬��
					</td>
					<td class="td_body" width="100px" style="display: none;">
						<select style="width:100px;" id="status" name="geAddServiceActivity.status">
							<option value="all" selected>--ȫ��--</option>
							<acc:showView source="ROLE_ROLE_B_AAGA_V">
							<option value="0">�����</option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_C">
							<option value="1">������������ </option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_BACK">
							<option value="2">����</option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_FIN">
							<option value="3">�ѷ���</option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_DEAL">
							<option value="4">�Ѵ��� </option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_REPEAL">
							<option value="5">����</option>
							</acc:showView>
							<acc:showView source="ROLE_ROLE_B_AAGA_GU">
							<option value="6">�ѷ���</option>
							</acc:showView>
						</select>
					</td>
					<td class="td_head" width="80px"  nowrap>
						�Ƿ���Ч��
					</td>
					<td class="td_body" >
						<select style="width:100px;" name="geAddServiceActivity.validInd">
							<option value="1" selected>��Ч</option>
							<option value="0">��Ч</option>
						</select>
					</td>
					<!-- 
					<td class="td_head" width="80px"  nowrap>
						����������
					</td>
					<td class="td_body" >
						<input type="text" readonly="readonly"  style="width:120px;" id="authorityDepartmentManager" >
						<input type="hidden"  id="authorityid" value="" name="geAddServiceActivity.deptID">
						<input type="button" value="ѡ�����" onclick="alterTree();" style="width:60px"  >
					</td>
					 -->
				</tr>
				<input type="hidden" name="areaCode" value="3" />
				<input type="hidden" name="workFlowId" value="marketingWorkFlow" />
				<input type="hidden" name="taskGroup" value="SXFGroup1" />
				<input type="hidden" name="bizType" value="${param.bizType}">
				<tr height="60px;">
					<td  colspan="4" align="center" >
						<table  id="operatorTable"  align="right">
						
							<tr>
							  <td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>���</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	window.onload = function(){
		
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['˵������ѯӪ������ò�Ʒ<br/>ʹ����Ⱥ��Ӫ���������Ա��<br/>������������Ҫ���ӻ���򣬻��Ʒ��<br/>ע�������������̣�������˵�� ��'];
	    	for ( var i = 0 ; i < 10 ; i++ ){
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
						width:350,
						textAlign: 'left',
						background: '#e0f2ff', 
						tip:true,//����������Ƿ����
						padding :10
					}
				});
	    	}
	}
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		//����ת��
		var status = "all";//document.getElementsByName("geAddServiceActivity.status")[0].value;
		if("look"=="${param.bizType}"){//�鿴
			document.getElementById("frmInput").action="${ctx}/marketing/findAddGeAddServiceActivity.do";
		}else{
			if(status=="1"||status=="2"){ //�߹�����
				document.getElementById("frmInput").action="${ctx}/marketing/findGeAddServiceActivityWorkFlow.do?status="+status;
			}else{//�߱���
				document.getElementById("frmInput").action="${ctx}/marketing/findAddGeAddServiceActivity.do?searchType="+status;
			}
		}
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	//����һ����
	function alterTree(){
		var authorityid = document.getElementById("authorityid").value;
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_AAGA_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
	
	$(function(){
		doSearch();
	});
</script>
</html>