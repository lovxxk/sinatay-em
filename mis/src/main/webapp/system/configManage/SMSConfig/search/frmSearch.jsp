<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
	 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>电子商务管理系统-短信配置查询</title>
<style type="text/css">
		#operatorTable tr {
				vertical-align:middle;
				text-align:center;
				width:82px;
		}
	</style>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">
		短信配置维护<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
	</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/system/configManage/SMSConfig/findSms.do" target="fraSearchList">
			<table class="table_style" align="center" width="650px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						短信名称：
					</td>
					<td class="td_body"  width="130px">
						<input id="smsName" name="geSmsConfig.smsName" type="text"  onchange="trimRe(this);" maxlength=200 class=required>
					</td>
					<td class="td_head" width="80px"  nowrap>
						适用功能：
					</td>
					<td class="td_body">
						<select id="functionID" name="geSmsConfig.functionID" style="width:150px;">
                            <option value="">请选择</option>
                            <s:if test="#request.SendSmsTypeList!=null">
                             <s:iterator value="#request.SendSmsTypeList" var="code">
                                <option value="<s:property value="#code.id.codeCode"/>"><s:property value="#code.codeCName"/></option>
                             </s:iterator>
                           </s:if>
						</select>
					</td>
					<td class="td_head" width="80px"  nowrap>
						是否有效：
					</td>
					<td class="td_body">
						<select  name="geSmsConfig.validInd" style="width:70px;">
                            <option value="">请选择</option>
                            <option value="1">有效</option>
                            <option value="0">无效</option>
                             
						</select>
					</td>
				</tr>
				<tr height="60px;">
					<td colspan="4" align="center">
						<table id="operatorTable" align="right">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:doClear();" nowrap>清空</td>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
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
		var ids = ['des'];
		// <img src="'+contextRootPath+'/global/images/form_success.gif" />
		var contents = ['说明：查询短信配置<br/>使用人群：短信配置人员<br/>配置条件：适用功能要准确应用 。<br/>注意事项：短息内容#为占位符，将来程序会替换成文字。--例如：您好！#的投保单#已于#付款成功，金额共计￥#元,请进行后续业务操作 '];
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
						tip:true,//控制左侧尖角是否出现
						padding :10
					}
				});
	    	}
	}
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
	function trimRe(field){
		field.value = field.value.replace(/(^\s*)|(\s*$)/g, ""); 
	}
</script>
</html>
