<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>新建险种</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<div class="public_fun_title">新建险种<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span></div>

	<form action="${ctx}/risk/addGeRisk.do" id="riskForm" method="post" target="myFrame">
	<table class="table_style" align="center"  id="geRiskTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>

    <tr>
      <td class="td_head" width="120px" nowrap>险种代码：</td>
      <td class="td_body" width="380px">
         <input id="riskCode" name="geRisk.riskCode" type="text" value="" style="width:240px;" maxlength=4>
      </td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>中文名称：</td>
		<td class="td_body" width="380px" >
            <input id="riskCName" name="geRisk.riskCName" type="text" value="" style="width:240px;" maxlength=400/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>英文名称：</td>
		<td class="td_body" width="380px" >
           <input id="riskEName" name="geRisk.riskEName" type="text" value="" style="width:240px;" maxlength=400/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>繁体名称：</td>
		<td class="td_body" width="380px" >
           <input id="riskTName" name="geRisk.riskTName" type="text" value="" style="width:240px;" maxlength=400 />
		</td>
	</tr>
		<tr>
		<td class="td_head" width="120px" nowrap>业务领域：</td>
		<td class="td_body" width="380px">
			<select id="businessArea" name="geRisk.businessArea"  style="width:170px;" >
				<option value="">--请选择--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}">${bussArea.codeCName}</option>
				</c:forEach>
             </select>  
		</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>险种类别：</td>
         <td class="td_body" width="380px">
          <select id="riskType" name="geRisk.riskType" style="width:170px;">
				<option value="">--请选择--</option>
				<option value="01">传统险</option>
				<option value="02">分红</option>
				<option value="03">投连</option>
				<option value="04">万能</option>
				<option value="05">其它</option>
				<option value="13">意外险</option>
             </select>  
         </td>
    </tr>
   
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=4>
			<table width=64 align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="riskAddSubmit()"  nowrap>
                                                              创建
                </td>
                <td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//下面这一行代码为3个字段添加了“非空”验证，同时会自动在3个字段上加上红色星号\
	new tt.LV().set(0,4).add("geRisk.riskCode");
	new tt.LV().set(0,100).add("geRisk.riskCName");
	new tt.LV().set(0,100).add("geRisk.riskEName");
	new tt.LV().set(0,100).add("geRisk.riskTName");
//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
	tt.vf.req.add("geRisk.riskCode");
	tt.vf.req.add("geRisk.riskCName");
	tt.vf.req.add("geRisk.businessArea");
	tt.vf.req.add("geRisk.riskType");
	var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "对不起，只能输入汉字！");
	testCName.add("geRisk.riskCName");
	var testEName = new tt.RV().set(new RegExp("^[A-Za-z]+$"), "对不起，只能输入英文字母！");
	testEName.add("geRisk.riskEName");
	var testCode = new tt.RV().set(new RegExp("^\\d+$"), "对不起，只能输入非负整数");
	testCode.add("geRisk.riskCode");
	//testCode.add("geRisk.riskType");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "只能输入A-Z,a-z,0-9,且长度为1-30").add("geDirectory.productQuoteCode");

	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建险种。<br/>使用人群：车险产品管理配置人员。<br/>配置条件：险种用于强制或电话营销险。<br/>注意事项：'];
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
					width:300,
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true,//控制左侧尖角是否出现
					padding :10
				}
			});
    	}

})
function riskAddSubmit(){
	if(tt.validate()){
	   document.getElementById("riskForm").submit();
	}
}
//清空输入
function doClear(){
	document.getElementById("riskForm").reset();
}
</script>

	
</html>