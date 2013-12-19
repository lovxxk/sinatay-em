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
<title>�½�����������</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<div class="public_fun_title">�½�����������<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span></div>

	<form action="${ctx}/universal/addUniversal.do" id="universalForm" method="post" target="myFrame">
	<table class="table_style" align="center"  id="universalTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>

    <tr>
      <td class="td_head" width="120px" nowrap>��Ʒ���ƣ�</td>
      <td class="td_body" width="380px">
        <input id="riskName" name="universal.riskName" type="text" value="" style="width:240px;" maxlength=60/>
      </td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�������ڣ�</td>
		<td class="td_body" width="380px" >
			<input type="text" id="culDate" name="universal.culDate" maxlength="30" value="" style="width: 170px;" readonly/>
			<img id="discountdateImg" onclick="discountdateImgClick()" src="${ctx}/global/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���������ʣ�</td>
		<td class="td_body" width="380px" >
           <input id="dateRate" name="universal.dateRate" type="text" value="" style="width:100px;" maxlength=10/>%
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�ۺ���������ʣ�</td>
		<td class="td_body" width="380px" >
           <input id="yearRate" name="universal.yearRate" type="text" value="" style="width:100px;" maxlength=10 />%
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=4>
			<table width=64 align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="universalAddSubmit()"  nowrap>
                                                              ����
                </td>
                <td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
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
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�\
	new tt.LV().set(0,80).add("universal.riskName");
	new tt.LV().set(0,10).add("universal.dateRate");
	new tt.LV().set(0,10).add("universal.yearRate");
	tt.vf.req.add("universal.riskName");
	tt.vf.req.add("universal.culDate");
	tt.vf.req.add("universal.dateRate");
	tt.vf.req.add("universal.yearRate");
	new tt.RV().set(new RegExp("^((\\d){1,2})(\\.(\\d){1,6})?$"), "��ȷ�����������").add("universal.dateRate");
	new tt.RV().set(new RegExp("^((\\d){1,2})(\\.(\\d){1,6})?$"), "��ȷ�����������").add("universal.yearRate");

	var ids = ['des'];
	var contents = ['˵�����½����������ʡ�<br/>ʹ����Ⱥ��<br/>����������<br/>ע�����'];
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
					tip:true,//����������Ƿ����
					padding :10
				}
			});
    	}
	initPlaceSelector();
});
function universalAddSubmit(){
	if(tt.validate()){
		document.getElementById("universalForm").submit();
	}
}
//�������
function doClear(){
	document.getElementById("universalForm").reset();
}
function discountdateImgClick(){
	WdatePicker({el:'culDate'})
}
</script>

	
</html>