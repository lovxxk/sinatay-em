<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<title>�����������ϵͳ-�����쳣��ѯ</title>
</head>
<body topmargin="0" leftmargin="0" onload="pageValidate();">
		<div class="public_fun_title">
		����ҵ������ѯ<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	   </div>
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/exception/findException.do" target="fraSearchList">
			<table class="table_style" align="center" width="720px">
				<tr> 
					<td class="td_head" width="120px" nowrap>ʱ�䣺</td>
					<td class="td_body" style="line-height:22px;width:146px;">
					 <input class="Wdate" name="geMonitorAppException.exceptionTime" style="width:145px;" type="text" id="daLength"  onclick="WdatePicker()"/>
                   
					</td>
					<td class="td_head" width="120px" nowrap>
					      ���ƺţ�
					</td>
					<td class="td_body" style="line-height:22px;width:146px;">
						<input id="identifyFlag" type="text" name="geMonitorAppException.identifyFlag" style="width:145px;border:#999 1px solid;" maxlength="25">
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td nowrap>
							<input type="hidden" name="pageNo" id="pageNo" value="1">
							<input type="hidden" name="pageSize" id="pageSize" value="20">
						</td>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doSearch();" nowrap style="vertical-align: top;padding-top: 5px">��ѯ</td>
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap style="vertical-align: top; padding-top: 5px">���</td>
				 </tr>
			</table>
		</form>
</body>
<script type="text/javascript">
/**���ڿؼ��߶ȵ���*/
var CDH = new changeDateHeight();
CDH.dateIds = 'daLength';
//CDH.minHeight = '130,30,*,40,0';
CDH.changeHeight();

	function doSearch(){
		document.getElementById("pageNo").value = 1;
		if(tt.validate()){
			document.getElementById("frmInput").submit();		}
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	function pageValidate(){
		tt.vf.req.add("geMonitorAppException.exceptionTime","geMonitorAppException.identifyFlag");
	}
	
	$(function(){
		
		
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents =['˵��������ҵ������ѯ ��<br/>ʹ����Ⱥ�����ղ�Ʒ����������Ա��<br/>����������ʱ��������Ϊ�����<br/>ע�����'];
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
	});
	
</script>
</html>
