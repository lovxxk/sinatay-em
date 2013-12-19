<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>	
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title">新建用户组类型</div>
<div style="height:60px">&nbsp;</div>
<form action="${ctx}/system/groupManage/createType.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="650px" id="productTable">
<tr>
	<td class="td_head" style="height:30px;" nowrap>用户组类型名称：</td>
	<td class="td_body">
		<input id="grouptypeName" name="geGrouptype.grouptypename" type="text" style="width:170px;" class=required maxlength=15>
		<span id="grouptypeName_msg"></span>
	</td>
</tr>

<tr>
	<td class="td_head"  nowrap>用户组类型描述：</td>
	<td class="td_body" >
		<textarea  id="grouptypeDesc" name="geGrouptype.grouptypedesc" style="width:400px;" rows="5" ></textarea>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><span id="grouptypeDesc_msg"></span></td>
</tr>

<tr height=25><td></td></tr> 
<tr>
	<td colspan=2>
		<table width=164 align="center">
		<tr>
			<td id="createButton" align=right  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<input type="hidden" id="deptid" name="geGrouptype.grouptypedeptid" value="${geOperator.deptid}">
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doCreate(){
	if(tt.validate()){		
		$("#frmInput").submit();
	}	
}
function doClear(){
	document.getElementById("frmInput").reset();
}
$(document).ready(function(){
	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
	var ids = ['des'];
	    	var contents = ['说明:对用户组进行的分类<br/>'
	    	                + '使用人群:用户组类型管理人员<br/>'
	    	                + '配置条件:<br/>'
	    	                + '注意事项:类型描述清楚，方便后续操作<br/>'];
	        	for ( var i = 0 ; i < ids.length ; i++ ){
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
									tip:true,//控制左侧尖角是否出现
									padding :10
								}
							});
	        	}
	});
</script>
<script type="text/javascript">

	//设置显示区域
	var groupTypeName =new tt.Field("","","grouptypeName").setMsgId("grouptypeName_msg");
	var groupTypeDesc =new tt.Field("","","grouptypeDesc").setMsgId("grouptypeDesc_msg");
	new tt.LV().set(0, 100).add(groupTypeDesc); 
	//非空验证
	tt.vf.req.add(groupTypeName);
</script>
</html>
