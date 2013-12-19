<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<title>�����������ϵͳ-��ֵ����</title>
	<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">��ֵ����ά��</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/marketing/findAddGeAddServiceActivity.do" target="fraSearchList">
			<table class="table_style" align="center" width="98%">
				<tr>
					<td class="td_head td_head_center" width="4%" nowrap >
						����ƣ�
					</td>
					<td class="td_body" width="10%" >
						<input type="text"  name="geAddServiceActivity.activityName" style="width:170px;" >
					</td>
					
					<td class="td_head td_head_center" width="5%" nowrap>
					�״̬��
					</td>
					<td class="td_body" width="20%" >
						<select style="width:170px;" name="geAddServiceActivity.status">
							<option value="">--��ѡ��--</option>
							<option value="0">�����</option>
							<option value="1">������������ </option>
							<option value="2">����</option>
							<option value="3">�ѷ���</option>
						</select>
					</td>

				</tr>
					
				<tr>
					<td class="td_head td_head_center" width="15%"  nowrap>
						����������
					</td>
					<td class="td_body" width="15%" >
						<input type="text" readonly="readonly"  style="width:170px;" id="authorityDepartmentManager" >
						<input type="hidden"  id="authorityid" value="" name="geAddServiceActivity.deptID">
						<input type="button" value="ѡ�����" onclick="alterTree();"  >
					</td>
				</tr>
				<input type="hidden" name="areaCode" value="3" />
				<input type="hidden" name="workFlowId" value="marketingWorkFlow" />
				<input type="hidden" name="taskGroup" value="SXFGroup1" />
				<tr height="60px;">
					<td  colspan="4" align="center">
						<table  id="operatorTable">
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
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		//����ת��
		var status = document.getElementsByName("geAddServiceActivity.status")[0].value;
		if(status==""){
			status = "all";
		}
		if(status=="all"||status=="0"||status=="2"||status=="3"){//�߱��ص�
			document.getElementById("frmInput").action="${ctx}/marketing/findAddGeAddServiceActivity.do?searchType="+status;
		}else{//�߹������� 
			document.getElementById("frmInput").action="${ctx}/marketing/findGeAddServiceActivityWorkFlow.do";
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
</script>
</html>