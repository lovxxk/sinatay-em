<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<!-- ��ʾ��ʼ -->
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>�����������ϵͳ-��ѯ������Ϣ</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">����������ѯ<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align: middle;color:#E9E7E8" src="/mis/global/images/help.png"/></span></div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/businessManage/onlineOrderManage/queryGeOrderForLogisticsByDisPage.do" target="fraSearchList">
			<table  align="center" width="660px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						�����ţ�
					</td>
					<td class="td_body">
						<input type="text" style="width:170px;" id="orderNo" name="geOrder.orderNo"  maxlength="50">
					</td>
					<td class="td_head" width="80px" nowrap>
						����״̬��
					</td>
					<td class="td_body">
						<select id="flag" name="geOrder.flag">
							<option value="" selected="selected" >ȫ��</option>
							<option value="0">δ����</option>
							<option value="1">������</option>
						</select>
					</td>
					
				</tr>
				<tr>
					<td class="td_head" width="80px" nowrap>
						�����ˣ�
					</td>
					<td class="td_body">
						<input type="text" style="width:170px;" id="recipientName" name="geOrder.recipientName"  maxlength="50">
					</td>
					<td class="td_head" width="80px" nowrap>
						����������
					</td>
					<td class="td_body" >
						<div>
							<div style="width:180px;float:left;">
								<input type="text" id="authorityDepartmentManager" value="--ȫ��--" style="width:170px;" readonly>
								<input type="hidden" id="authorityid" name="authorityid" value=""/>
							</div>
							<div style="float:left;">
								<input style="width:100px;" onclick="deptAuthIdQuery();" type="button" value="ѡ�����Ȩ�ޡ�" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="100px" nowrap>
						֧���ɹ�ʱ�䣺
					</td>
					<td class="td_body" colspan="3">
						<input name="beginDate"  style="width:170px;" id="beginDate" readonly onclick="WdatePicker()" class="Wdate">
						��
						<input name="endDate"  style="width:170px;" id="endDate" readonly onclick="WdatePicker()" class="Wdate">
					</td>
				</tr>
				<tr height="60px">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap>��ѯ</td>
								<td class="btnBigOnBlur" align="right"  onmouseover="this.className='btnBigOnFocus'" 
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
$(document).ready(function(){
	//pop��ʾ��Ϣ
    	var ids = ['des'];
    	var contents = ['˵������ѯǰ̨�ύ�Ķ�����Ϣ���������������ա����պͱ��տ��������۲�Ʒ������������Ϣ<br/>ʹ����Ⱥ������������ά��Ա<br/>����������<br/>ע�����ͨ���������Բ�ѯ������Ͷ���������Բ鿴Ͷ������ϸ'];
        	for ( var i = 0 ; i < 1 ; i++ ){
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
								width:450,
								textAlign: 'left',
								background: '#e0f2ff', 
								tip:true,//����������Ƿ����
								padding :10
							}
						});
        	}
//pop��ʾ��Ϣ����
});
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	window.onload=function(){
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	};
	/**���ڿؼ��߶ȵ���*/
	var CDH = new changeDateHeight();
	CDH.dateIds = 'beginDate,endDate';
	CDH.minHeight = "195,30,*,60,0";
	CDH.changeHeight();
	
	function doClear(){
		document.getElementById("authorityDepartmentManager").value = "--ȫ��--";
		document.getElementById("authorityid").value = "";
		document.getElementById("frmInput").reset();
	}
	function deptAuthIdQuery(){
		var authorityid = document.getElementById("authorityid").value;
		var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
		window.open('${ctx}/deptAuthority/DeptAuthIdQuery.do?authorityid=ROLE_B_OORD_M&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
				'����' ,'top=100, left=500, width=400,height=500,toolbar=no');
	}
</script>
</html>
