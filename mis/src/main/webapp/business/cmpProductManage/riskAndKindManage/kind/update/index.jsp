<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�༭������Ϣ</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<script src="${ctx}/business/cmpProductManage/riskAndKindManage/kind/update/updateGeRelate/updateAddKind.js"></script>
</head>
<body onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭�ձ���Ϣ
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
<form id="frmInput" action="${ctx}/business/cmpProductManage/riskAndKindManage/updateGeEnKind.do" method="post" target="myFrame">
	<table class="table_style" align="center" width="720px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	
	<tr>
		<td class="td_head" nowrap>���ִ��룺</td>
		<td class="td_body" >
		  <s:property value="geKind.id.riskCode" />
		   <input  type="hidden" name="geKind.id.riskCode"  id="riskCodeV" value="<s:property value="geKind.id.riskCode" />" readonly="readonly" style="width:170px;"  onchange="checkMain();" maxlength=4/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�ձ���룺</td>
		<td class="td_body" >
		   <s:property value="geKind.id.kindCode"/>
	       <input type="hidden" name="geKind.id.kindCode"  id="kindCodeV" value="<s:property value="geKind.id.kindCode"/>" readonly="readonly" style="width:170px;" onchange="checkMain();" maxlength=6/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ձ��־��</td>
		<td class="td_body" >
		  <select name="geKind.kindflag" id="kindFlag" style="width:170px;" onchange="checkMain();checkFlag();">
		  	<option value="">--��ѡ��--</option>
		    <option value="01" <s:if test="geKind.kindflag==01">selected</s:if> >
					���� 
			 </option>
			 <option value="02" <s:if test="geKind.kindflag==02">selected</s:if>>
					������
			 </option>
		 </select>
		 <input type="hidden"  name="hiddenKindflag" value="<s:property value="geKind.kindflag"/>" id="flagkind"/> 
		</td>
	</tr>
	<tr style="display:none" id="flagRelateShow">
		<td class="td_head" nowrap>�Ƿ������ձ�ά����ϵ��</td>
		<td class="td_body" >
			<select name="flagRelate" id="flagRelate" style="width:170px;" onchange="changeNoYes();checkAddFlag();">
					<option value="1"/>��</option>
					<option value="0"/>��</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�ձ��������ƣ�</td>
		<td class="td_body" >
			  <input type="text" name="geKind.kindCName" value="<s:property value="geKind.kindCName"/>" style="width:170px;" maxlength=12/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�ձ�Ӣ�����ƣ�</td>
		<td class="td_body" >
		    <input type="text" name="geKind.kindEName" value="<s:property value="geKind.kindEName"/>" style="width:170px;" maxlength=12 onchange="checkEn(this)"/>
		</td>
	</tr>
   <tr>
		<td class="td_head" nowrap>�ձ������ƣ�</td>
		<td class="td_body" >
		    <input type="text" name="geKind.kindtname" value="<s:property value="geKind.kindtname"/>" style="width:170px;" maxlength=12/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ƿ�����Ͷ���������⣺</td>
		<td class="td_body" >
		  <select name="geKind.nodeductflag" style="width:170px;">
		      <option value="">--��ѡ��--</option>
		      <option value="0" <s:if test="geKind.nodeductflag==0">selected</s:if>>
						����Ͷ����������
			 </option>
			 <option value="1" <s:if test="geKind.nodeductflag==1">selected</s:if>>
						����Ͷ����������
			</option>
		 </select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ƿ���Ч��</td>
		<td class="td_body" >
		   <select name="geKind.validInd" style="width:170px;">
		      <option value="">--��ѡ��--</option>
		      <option value="0" <s:if test="geKind.validInd==0">selected</s:if>>
						��Ч
			 </option>
			 <option value="1" <s:if test="geKind.validInd==1">selected</s:if>>
						��Ч
			 </option>
			</select>
		</td>
	</tr>
	<!--
	<tr>
		<td class="td_head" width="120px" nowrap>�ײ�������ʾ���ã�</td>
		<td class="td_body" width="530px">
		   <select name="geKind.isComboFlag" style="width:170px;">
		      <option value="">--��ѡ��--</option>
		      <option value="1" <s:if test="geKind.isComboFlag==1">selected</s:if>>
		                                                                     �����ײ����� 
			  </option>
			  <option value="0" <s:if test="geKind.isComboFlag==0">selected</s:if>>
			     	 �������Ӷ��������ײ�
			  </option>
		 </select>
		</td>
	</tr>
	-->
	
	<!--
	<tr>
		<td class="td_head" nowrap>�ձ�����</td>
		<td class="td_body" >
		  <input type="text" value="<s:property value=""/>" name="orderNo"  id="orderNo" maxlength="6" style="width:170px;" onchange="checkOrderNum();"> 
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head"   nowrap>�ձ���д<s:property value="geKind.orderNo"/>��</td>
		<td class="td_body" >
		  <input type="hidden" value="<s:property value="geKind.orderNo"/>" name="orderNo"  id="noOrder" />
		 <input type="hidden" name="geKind.isComboFlag" value="1"/>
		  <input type="text" name="geKind.abbreviation" value="<s:property value="geKind.abbreviation" />" maxlength="3" style="width:170px;"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>����������</td>
		<td class="td_body"  >
			<textarea rows="8" cols="40" id="kindDescription" name="geKind.kindDescription" maxlength="600" onkeyup="textAreaMaxLen(this);"><s:property value="geKind.kindDescription"/></textarea>
		</td>
	</tr>
	<tr>
	<td colspan="2">  
		<div class="frmCreate_kuang" style="margin-left:250px;width:250px;margin-top:10px; display:none;" id="kindDiv" >
		<div class="frmCreate_kuang_header">���������ͣ�
		<span  style="color: #FF9000; font-weight: bold;"></span></div>
		<table width="250px" id="productCallBack">
		<s:iterator value="geKind.geKindRelateList" id="relate" status="c">
				<tr style="line-height: 20px">
					<td width="40%">&nbsp;${c.index+1}��<s:property value="#relate.kindRelateCName"/>
					  <input type="hidden" name="hiddenkindCode" value="<s:property value="#relate.id.relateKindCode"/>">
					  <input type="hidden" name="hiddenRiskCode" value="<s:property value="#relate.id.riskCode"/>">
					  <input type="hidden" name="hiddenkindMainCode" value="<s:property value="#relate.id.kindCode"/>">
					</td>
				</tr>
		</s:iterator>
		</table>
		</div>
	 <div id="buttonShow" style="margin-left:250px;padding-top: 10px;text-align: left;display:none;"><input type="button" value="�༭������" onclick="updateKind();" ></div>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�������</td>
		<td class="td_body" >
		<select name="geKind.codeType" id="codeType" onchange="createValuerange();" style="width:170px;">
			<option value="">--��ѡ��--</option>
		    <option value="0" <s:if test="geKind.codeType==0">selected</s:if>>
					ȡֵ��Χ��ȡ
		    </option>
			<option value="1" <s:if test="geKind.codeType==1">selected</s:if>>
					 ͬ�³����ü�
			</option>
			<option value="2" <s:if test="geKind.codeType==2">selected</s:if>>
					ͬʵ�ʼ�ֵ
			</option>
			<option value="3" <s:if test="geKind.codeType==3">selected</s:if>>
					����
			</option>
		</select>
		</td>
	</tr>
	 <tr id="createInput" style="display:none">
	  <td class="td_head" nowrap>ͬ�³����ü�:</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly"  style="width:170px;" value="<s:property value="geKind.valuerange"/>" id="newCar">
	  </td>
	</tr>
	 <tr id="createInputT" style="display:none">
	  <td class="td_head" nowrap>ͬ����ʵ�ʼ�ֵ:</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" readonly="readonly"  style="width:170px;" value="<s:property value="geKind.valuerange"/>" id="carT">
	  </td>
	</tr>
		<!-- -----------------------    ��������ʾ   ��ʼ  ------------------------------------- -->
	 <tr id="createSelect" style="display:none">
	  <td colspan="4" >
	   <div  class="frmCreate_kuang" style="margin-left:60px;width:550px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header">����ȡֵ��Χ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addDiv">
	  </table>
     </div>
	</div>
     </td>
	</tr>
	   <%//----------------------    ����  ---------------------------------------------------- %>
	 <tr id="creatOther">
	  <td colspan="4">
		  <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;margin-left:70px;">
		  <div class="frmCreate_kuang_header">����<span  style="color:#FF9000;font-weight:bold;"></span></div>
		  <div style="padding-left:3px; padding-top:15px;">
			 <table id="addOther">
		         
		     </table>
		  </div>
		</div>
     </td>
	</tr>
	<%//-------------------------------- ���� ------------------------------------------ %>
		<!-- -----------------------    ��������ʾ   ���� ------------------------------------- -->
		<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
			    	<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="goToView();" nowrap>����</td>
				<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doUpdate();" nowrap>�޸�</td>
				<td id="updateButton"class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doClear();" nowrap>����</td>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript"><!--
//------------------- ��ʼ������֤ ----------------------------------------
$(document).ready(function(){
	var codeType = $("#codeType").val();
	var ttvf = tt.vf.req;//�ǿ�
	if(codeType=="0"){
		$("#createInputT").css("display","none");
		$("#createInput").css("display","none");
		$("#creatOther").css("display","none");
		$("#createSelect").css("display","");
		var divT = $("#addDiv");
		var valueRangeKind = '${geKind.valuerange}';   // ȡ�õ�ֵ
		var duty  = valueRangeKind.substring(0,valueRangeKind.indexOf("@{")); // ȡ������
		var tr = $(" <tr> </tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(tr);
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange'  value='"+duty+"' id='duty' maxlength='10' />  </td>  ").appendTo(tr);
		var tdButtonA = $("<td> <input type='button' style='float:right' value='�����޶�' onclick='insertMoney();'/> </td>").appendTo(tr);
        var sp = valueRangeKind.substring(valueRangeKind.indexOf("{")+1,valueRangeKind.indexOf("}"));
        var dou = sp.split(",");   
        for(var i =0 ; i < dou.length; i++){
        	var tmp = dou[i].split(":");
        	var trD = $("<tr></tr>").appendTo(divT);
        	if(tmp[0]){
        		if(i==0){
        			var td = $(" <td class='td_head' nowrap >�����޶</td> ").appendTo(trD);
            		var tdText = $(" <td class='td_body' >  <input type='text' style='font-style:oblique' id='du"+i+"' maxlength='7' name='geKind.valuerange'   onchange='checkMoney(this);'  onblur='fillText("+i+");' value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        		}else{
        			var td = $(" <td class='td_head' nowrap >�����޶</td> ").appendTo(trD);
            		var tdText = $(" <td class='td_body'>  <input type='text' style='font-style:oblique' name='geKind.valuerange'  maxlength='7'  id='du"+i+"'  onchange='checkMoney(this);' onblur='fillText("+i+");' value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        		}
        	}if(tmp[1]){
        	  if(i==0){
        		     var td = $(" <td class='td_head' nowrap style=padding-left:50px>�޶�������</td> ").appendTo(trD);
	        	  	var tdText = $(" <td class='td_body'>  <input type='text'  name='geKind.valuerange' id='mi"+i+"'  style='font-style:oblique' readonly='readonly' value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
	        		var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' value='�h��' /> </td>").appendTo(trD);
        	  }if(i !=0){
	        		var td = $(" <td class='td_head' nowrap style=padding-left:50px>�޶�������</td> ").appendTo(trD);
	        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' id='mi"+i+"' style='font-style:oblique'  readonly='readonly' value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
	        		var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' value='�h��' /> </td>").appendTo(trD);
        	   }
        	}
        }
        ttvf.rmId('newCar','carT');
        tt.vf.req.addId('duty');
        checkKind();
	}else if(codeType=="1"){
			$("#createSelect").css("display","none");
			$("#createInputT").css("display","none");
			$("#creatOther").css("display","none");
			$("#createInput").css("display","");
			ttvf.rmId('duty','carT');
			tt.vf.req.addId('newCar');
	}else if(codeType=='2'){
		    $("#createSelect").css("display","none");
			$("#createInput").css("display","none");
			$("#creatOther").css("display","none");
			$("#createInputT").css("display","");
			ttvf.rmId('duty','newCar');
			tt.vf.req.addId('carT');
	}else if (codeType=="3"){
		$("#createInputT").css("display","none");
		$("#createInput").css("display","none");
		$("#createSelect").css("display","none");
		$("#creatOther").css("display","");
		var divT = $("#addOther");
		var valueRangeKind = '${geKind.valuerange}';   // ȡ�õ�ֵ
		if(valueRangeKind!='' && valueRangeKind.indexOf("@{")==-1){
			var duty  = valueRangeKind ;
		}else{
		   var duty  = valueRangeKind.substring(0,valueRangeKind.indexOf("@{")); // ȡ������
		}
		var tr = $(" <tr> </tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(tr);
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' value='"+duty+"' id='otherDuty' maxlength='10' />  </td>  ").appendTo(tr);
		var tdButtonA = $("<td> <input type='button' style='float:right' value='����' onclick='insertOther();'/> </td>").appendTo(tr);
        var sp = valueRangeKind.substring(valueRangeKind.indexOf("{")+1,valueRangeKind.indexOf("}"));
        var dou = sp.split(",");
        for(var i =0 ; i < dou.length; i++){
        	var tmp = dou[i].split(":");
        	var trD = $("<tr></tr>").appendTo(divT);
        	if(tmp[0]){
        		if(i==0){
        			var td = $(" <td class='td_head' nowrap >����˵����</td> ").appendTo(trD);
            		var tdText = $(" <td class='td_body' >  <input type='text'  maxlength='7' name='geKind.valuerange'   onchange='checkOther(this)' id='otherh"+ i +"' value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        		}else{
        			var td = $(" <td class='td_head' nowrap >����˵����</td> ").appendTo(trD);
            		var tdText = $(" <td class='td_body'>  <input type='text'  name='geKind.valuerange'  maxlength='7'   onchange='checkOther(this)' id='otherh"+ i +"' checkOther value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        		}
        	}if(tmp[1]){
        	  if(i==0){
        		    var td = $(" <td class='td_head' nowrap style='padding-left:50px;width:150px;'>����������</td> ").appendTo(trD);
	        	  	var tdText = $(" <td class='td_body'>  <input type='text' onchange='checkOther(this);' name='geKind.valuerange' id='aotherh"+ i +"' value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
	        		var tdButtonD =  $("<td class='td_body' > <input type='button'  value='�h��' style='padding-left:10px' onclick='removeMoney(this);' /> </td>").appendTo(trD);
        	  }if(i !=0){
	        		var td = $(" <td class='td_head' nowrap style='padding-left:50px;width:150px;'>����������</td> ").appendTo(trD);
	        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange'  onchange='checkOther(this);' id='aotherh"+ i +"'   value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
	        		var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' style='padding-left:10px' value='�h��' /> </td>").appendTo(trD);
        	   }
        	}
        }
	}
	checkMain();
	
	//pop��ʾ��Ϣ
	var ids = ['kindDescription','codeType'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['����ǰ�˽����ձ���ϸ��Ϣ','����ȡ�������޶���ս��'];
		
    	for ( var i = 0 ; i < 10 ; i++ ){
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
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			}); 
    	}
//pop��ʾ��Ϣ����
});
var otherNum = 0;
function insertOther(){
	
	    var dex = $("input[id^='other']").length ;
	    if(dex<5){
	    	var divD = $("#addOther");
			var trT = $("<tr></tr>").appendTo(divD);
			var td = $(" <td class='td_head' nowrap >����˵����</td> ").appendTo(trT);
			var tdText = $(" <td class='td_body'>  <input type='text'  name='geKind.valuerange' id='other"+otherNum+"' value='' onchange='checkOther(this);' maxLength='7' />  </td>  ").appendTo(trT);
			var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(trT);
			var tdText = $(" <td class='td_body'  style='width:150px;'>  <input type='text'  name='geKind.valuerange' id='aother"+otherNum+"' value='' maxLength='7' onchange='checkOther(this);'/>  </td>  ").appendTo(trT);
			var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' style='padding-left:10px' value='�h��' /> </td>").appendTo(trT);
			otherNum++;
	    }else{
	    	alert("������5��");
	    	return false ;
	    }
}
function pageValidate(){
	tt.vf.req.add("geKind.combinekindcode"
			,"geKind.kindCName","geKind.kindflag"
			,"geKind.codeType","geKind.nodeductflag","geKind.abbreviation"
			,"geKind.validInd","geKind.kindDescription"); //��֤�ǿ�
    var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
    testCName.add("geKind.kindCName");//��֤����
    var ttNumAndCharacter = new tt.RV().set(new RegExp("^[A-Za-z0-9]+$"), "ֻ���������ֻ���ĸ"); 
    ttNumAndCharacter.add("geKind.id.kindCode");
   // var ttNumOrder = new tt.RV().set(new RegExp( "^\\d+$" ),"�Բ���ֻ������������");
    // ttNumOrder.add("orderNo");

}

function checkEn(str){
	if(!/^.[A-Za-z]+$/.test(str.value)){
		alert("��������Ӣ���ַ�");
		str.value = '' ;
		return false;
	}
}

function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("�������600����");
	    }
}

function goToView(){
	window.location.href='${ctx }/business/cmpProductManage/riskAndKindManage/viewKind.do?geKind.id.riskCode=${geKind.id.riskCode}&geKind.id.kindCode=${geKind.id.kindCode}'
}
--></script>
</body>
</html>
