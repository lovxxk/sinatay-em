<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxtree.css" rel="stylesheet" type="text/css">
<link href="${ctx}/global/dhtmlXTree/css/dhtmlxmenu_dhx_blue.css" rel="stylesheet" type="text/css">
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxcommon.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxmenu.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree.js"></script>
<script src="${ctx}/global/dhtmlXTree/js/dhtmlxtree_ed.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>frmCreate.jsp</title>
</head>
<body>
<div class="public_fun_title">新建角色</div>
<div style="height:10px">&nbsp;</div>
<form action="${ctx}/system/roleManage/createGeRole.do" id="frmInput" method="post" target="myFrame">
<table id="kuang" class="frmCreate_kuang" width="98%" align="center" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="frmCreate_kuang_header" style="text-align:center;border-right:1px solid #dcdcdc;">角色基本信息</td>
		<td class="frmCreate_kuang_header" style="text-align:center;width:275px;">角色权限信息</td>
	</tr>
	<tr>
		<td valign="top" id="basicInfo" style="border-right:1px solid #dcdcdc;">
			<table align="left" style="line-height:25px;">
				<tr>
					<td class="td_head" width="70px" nowrap>角色编号：</td>
					<td class="td_body">
						<input id="roleid" name="geRole.roleID" onblur="readonblur();" type="text" style="width:170px;" class=required maxlength=20>
						<span id="roleid_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>角色名称：</td>
					<td  class="td_body">
						<input id="rolename" name="geRole.roleName" type="text" style="width:170px;" class=required maxlength=20>
						<span id="rolename_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>角色描述：</td>
					<td  class="td_body">
						<textarea id="roledesp" name="geRole.roleDesp"  style="width:380px;height: 90px;"></textarea>
						
					</td>
				</tr>
				<tr><td>&nbsp;</td><td><span id="roledesp_msg"></span></td></tr>
			</table>
		</td>
		<td valign="top">
			<div id="showListLoading" style="position:absolute;">
				<div class="loading_process1" style="height:50px; font-size: 16px;">数据加载中，请稍后 . . .</div>
			</div>
			<div id="authorityTree" style="width:100%;"></div>
		</td>
	</tr>
</table>
<table align="center" style="width:100%;">
<tr height="60px">
	<td colspan=2 align="center">
		<table width=164 align="center">
		<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
			<td width=5>&nbsp;</td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<input type=hidden name="authoritys" id="authoritys">
<input type=hidden name="roleID" value="${geRole.roleID}">
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
if(window.navigator.userAgent.indexOf("MSIE")>=1){//如果浏览器为IE
	$("#kuang").css("height",window.parent.document.getElementById('fraLEFT').height-140);
}
kuang.style.height = document.body.clientHeight-140;
if(document.body.clientWidth > 900){
	kuang.style.width = 900;
}

var authorityTrees = document.getElementById("authorityTree");
authorityTrees.style.height = document.body.clientHeight-165;

var basicInfo = document.getElementById("basicInfo");
basicInfo.style.height = document.body.clientHeight-165;

//初始化tree----------------------------------------------------------////
var tree=new dhtmlXTreeObject("authorityTree","100%","100%",0); 
tree.setImagePath("${ctx }/global/dhtmlXTree/images/DhtxTree/csh_scbrblue/");
tree.enableCheckBoxes(true);
tree.enableThreeStateCheckboxes(true);
tree.setOnCheckHandler(toncheck);
tree.loadXML("${ctx}/system/roleManage/findRoleAuthority.do",loadOver);
function myErrorHandler(type, desc, erData){
   return;
}
dhtmlxError.catchError("ALL",myErrorHandler);

function toncheck(id,state){
	var children = tree.getAllCheckedBranches();
	$("#authoritys").val(children);
}

function openTree(){
	$("#openTree_button").hide();
	$("#authorityTree").show();
}

function loadOver(){
	$("#showListLoading").hide();
}

function doCreate(){
	if(tt.validate()&&readonblur()){
		if($("#authoritys").val() == ""){
			alert("请选择角色拥有的权限！");
			return false;
		}
		$("#frmInput").submit();
	}
}

var bol=false;
function readonblur(){
			var roleid = $("#roleid").val();
			$.ajax({
				url : "${ctx }/system/roleManage/getRolebyId.do",
				data : {
					"geRole.roleID" : roleid
				},//传入参数
				type : 'POST',
				dataType : 'json',
				error : function() {
				},
				cache :false,
				success : function(data) {
					if (data.resultFlag == "success") {
						//$("#roleid").val("");
						$("#coreProductCodeContent").remove();
						$("#roleid").parent().append("<span id='coreProductCodeContent'><span class='talentErrMsg'>该角色编号已存在！</span></span>")
						return bol;
					} else {
						$("#coreProductCodeContent").remove();
						bol=true;
					}
				}
			});
			return bol;
}
function doClear(){
	window.location.reload();
	$("#frmInput")[0].reset();
}
</script>
<script type="text/javascript">
$(function(){
	//设置显示区域
	var roleid =new tt.Field("","","roleid").setMsgId("roleid_msg");
	var rolename =new tt.Field("","","rolename").setMsgId("rolename_msg");
	var roledesp =new tt.Field("","","roledesp").setMsgId("roledesp_msg");
	//非空验证
	tt.vf.req.add(roleid,rolename,roledesp);
	new tt.LV().set(0,500).add(roledesp);
	//针对不同需求的正则表达式验证
	new tt.RV().set(new RegExp(/^[A-Za-z0-9]+$/),"只能输入数字和字母!").add(roleid);
})
var ids = ['roleid'];
		var contents = ['角色唯一标识'];
    	for ( var i = 0 ; i < ids.length ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
					tooltip: 'leftMiddle', 
					target: 'rightMiddle'
					} 
				}, 
				 style: { 
				border: { 
					width: 2,
					radius: 2,
					color: '#00739f'
					},
					width:100,
					padding: 10, 
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
    	$(document).ready(function(){
    		$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
    		var ids = ['des'];
    		    	var contents = ['说明:控制后台功能菜单权限<br/>'
    		    	                + '使用人群:角色管理人员<br/>'
    		    	                + '配置条件:<br/>'
    		    	                + '注意事项:角色描述要清晰<br/>'];
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
</html>
