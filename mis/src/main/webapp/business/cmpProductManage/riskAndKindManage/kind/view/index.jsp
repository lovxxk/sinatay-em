<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�鿴�ձ���Ϣ</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�鿴�ձ���Ϣ
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx }/business/cmpProductManage/riskAndKindManage/deleteByCode.do?geKind.id.riskCode=${geKind.id.riskCode}&geKind.id.kindCode=${geKind.id.kindCode}" id="frmInput" method="post"  target="kindIframe" >
	<table align="center" style="width:550px;line-height:25px;"  id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>���ִ��룺</td>
		<td class="td_body" >
			<s:property value="geKind.id.riskCode" />
		</td>
	</tr>
	
	<tr>
		<td class="td_head"  nowrap>�ձ���룺</td>
		<td class="td_body">
			<s:property value="geKind.id.kindCode"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�ձ��־��</td>
		<td class="td_body" width="530px">
			<s:if test="geKind.kindflag==01">
					����
			</s:if>
			<s:if test="geKind.kindflag==02">
					������
			</s:if>
		</td>
	</tr>
		<tr style="display:none" id="flagRelateShow">
		<td class="td_head" nowrap>�Ƿ������ձ�ά����ϵ��</td>
		<td class="td_body" id="flagRelate">
		</td>
	</tr>
	<!--LDAP���������ֶ�start-->
	<tr>
		<td class="td_head" nowrap>�ձ��������ƣ�</td>
		<td class="td_body" >
			<s:property value="geKind.kindCName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ձ�Ӣ�����ƣ�</td>
		<td class="td_body" width="530px">
			<s:property value="geKind.kindEName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�ձ������ƣ�</td>
		<td class="td_body" >
			<s:property value="geKind.kindtname"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ƿ�����Ͷ���������⣺</td>
		<td class="td_body" width="530px">
			<s:if test="geKind.nodeductflag==0">
					����Ͷ����������
			</s:if>
			<s:if test="geKind.nodeductflag==1">
					����Ͷ����������
			</s:if>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>�Ƿ���Ч��</td>
		<td class="td_body" width="530px">
			<s:if test="geKind.validInd==0">
					��Ч
			</s:if>
			<s:if test="geKind.validInd==1">
					��Ч
			</s:if>
		</td>
	</tr>
	<!--
	<tr>
		<td class="td_head" width="120px" nowrap>�ײ�������ʾ���ã�</td>
		<td class="td_body" width="530px">
			<s:if test="geKind.isComboFlag==1">
                                                                     �����ײ����� 
			</s:if>
			<s:if test="geKind.isComboFlag==0">
				�������Ӷ��������ײ�
			</s:if>
		</td>
	</tr>
	-->
	<!--
	<tr>
		<td class="td_head" nowrap>�ձ�����</td>
		<td class="td_body" >
		    <s:property value="geKind.orderNo"/>
		</td>
	</tr>
	-->
	<tr>
		<td class="td_head"  nowrap>�ձ���д��</td>
		<td class="td_body">
		    <input type="hidden" name="geKind.isComboFlag" value="1"/>
		    <s:property value="geKind.abbreviation"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>����������</td>
		<td class="td_body"  >
			<textarea rows="8" cols="40"  name="geKind.kindDescription" disabled maxlength="600" onkeyup="textAreaMaxLen(this);"><s:property value="geKind.kindDescription"/></textarea>
		</td>
	</tr>
	<tr>
	<td colspan="2">  
		<div class="frmCreate_kuang" style="margin-left:150px;width:250px;margin-top:10px;display:none;" id="kindDiv">
		<div class="frmCreate_kuang_header">���������ͣ�
		<span  style="color: #FF9000; font-weight: bold;"></span></div>
		<table width="250px">
		<s:iterator value="geKind.geKindRelateList" id="relate" status="c">
				<tr style="line-height: 20px">
					<td width="40%">&nbsp;${c.index+1}.<s:property value="#relate.kindRelateCName" />
					  	 <input type="hidden" name="hiddenkindCode" value="<s:property value="#relate.id.relateKindCode"/>">
					</td>
				</tr>
		</s:iterator>
		</table>
		</div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�ײͼ������</td>
		<td class="td_body" >
		<select name="geKind.codeType"  style="width:170px;" id="codeType" onchange="createValuerange();" disabled="disabled">
					<option value="">--��ѡ��--</option> 
					<option  value="0" <s:if test="geKind.codeType==0">selected</s:if> />ȡֵ��Χ��ȡ </option>
					<option value="1" <s:if test="geKind.codeType==1">selected</s:if>/>ͬ�³����ü� </option>
					<option value="2" <s:if test="geKind.codeType==2">selected</s:if> />ͬʵ�ʼ�ֵ </option>
					<option value="3" <s:if test="geKind.codeType==3">selected</s:if> />���� </option>
			</select>
			<input type="hidden" value="<s:property value="geKind.codeType"/>"/>
		    <input type="hidden" id = "kindCodeV" value="<s:property value="geKind.id.kindCode"/>"/>
			
		</td>
	</tr>
	 <tr id="createInput" style="display:none">
	  <td class="td_head" nowrap>ͬ�³����ü�:</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" disabled="disabled" style="width:170px;" value="<s:property value="geKind.valuerange"/>">
	    <input type="hidden"  name="geKind.valuerange" value="<s:property value="geKind.valuerange"/>">
	  </td>
	</tr>
	 <tr id="createInputT" style="display:none">
	  <td class="td_head" nowrap>ͬ����ʵ�ʼ�ֵ:</td>
	  <td class="td_body">
	    <input type="text" name="geKind.valuerange" disabled="disabled"   style="width:170px;" value="<s:property value="geKind.valuerange"/>">
	    <input type="hidden" name="geKind.valuerange"  value="<s:property value="geKind.valuerange"/>">
	  </td>
	</tr>
		<!-- -----------------------    ��������ʾ  ��ʼ------------------------------------- -->
	 <tr id="createSelect" style="display:none">
	  <td colspan="4">
	   <div  class="frmCreate_kuang" style="margin-left:50px;width:450px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header">����ȡֵ��Χ<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addDiv">
	</table>
     </div>
	</div>
     </td>
	</tr>
	
	   <%//----------------------    ����  ---------------------------------------------------- %>
	 <tr >
	  <td colspan="4">
	  <div id="creatOther" style="display:none">
	   <div  class="frmCreate_kuang" style="width:547px;margin-top:15px;padding-bottom: 15px;">
	  <div class="frmCreate_kuang_header">����<span  style="color:#FF9000;font-weight:bold;"></span></div>
	  <div style="padding-left:3px; padding-top:15px;">
	  <table id="addOther">

     	</table>
     </div>
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
			  <acc:showView source="ROLE_BU_P_C_K_U">
                   <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" onclick="doUpate();" nowrap>�༭</td>
                  </acc:showView>
                   <acc:showView source="ROLE_BU_P_C_K_D">
                    <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                        onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>ɾ��</td>
                  </acc:showView>
				<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe  name="kindIframe" style="display: none"></iframe>
	</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var codeType = $("#codeType").val();
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
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly'  value='"+duty+"'/>  </td>  ").appendTo(tr);
        var sp = valueRangeKind.substring(valueRangeKind.indexOf("{")+1,valueRangeKind.indexOf("}"));
        var dou = sp.split(",");   
        for(var i =0 ; i < dou.length; i++){
        	var tmp = dou[i].split(":");
        	var trD = $("<tr></tr>").appendTo(divT);
        	if(tmp[0]){
        		var td = $(" <td class='td_head' nowrap >�����޶</td> ").appendTo(trD);
        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly' value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        	}if(tmp[1]){
        		var td = $(" <td class='td_head' nowrap >�޶�������</td> ").appendTo(trD);
        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly' value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        	}
        }
	}else if(codeType=="1"){
			$("#createSelect").css("display","none");
			$("#createInputT").css("display","none");
			$("#creatOther").css("display","none");
			$("#createInput").css("display","");
	}else if(codeType=='2'){
			$("#createSelect").css("display","none");
			$("#createInput").css("display","none");
			$("#creatOther").css("display","none");
			$("#createInputT").css("display","");
	}else if(codeType=='3'){
		$("#createInputT").css("display","none");
		$("#createInput").css("display","none");
		$("#createSelect").css("display","none");
		$("#creatOther").css("display","block");
		var divT = $("#addOther");
		var valueRangeKind = '${geKind.valuerange}';   // ȡ�õ�ֵ
		if(valueRangeKind!='' && valueRangeKind.indexOf("@{")==-1){
			var duty  = valueRangeKind ;
		}else{
		   var duty  = valueRangeKind.substring(0,valueRangeKind.indexOf("@{")); // ȡ������
		}
		var tr = $(" <tr> </tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(tr);
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly'  value='"+duty+"'/>  </td>  ").appendTo(tr);
        var sp = valueRangeKind.substring(valueRangeKind.indexOf("{")+1,valueRangeKind.indexOf("}"));
        var dou = sp.split(",");   
        for(var i =0 ; i < dou.length; i++){
        	var tmp = dou[i].split(":");
        	var trD = $("<tr></tr>").appendTo(divT);
        	if(tmp[0]){
        		var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(trD);
        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly' value='"+tmp[0].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        	}if(tmp[1]){
        		var td = $(" <td class='td_head' nowrap >����������</td> ").appendTo(trD);
        		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly' value='"+tmp[1].replace(/\s+/g,"")+"'/>  </td>  ").appendTo(trD);
        	}
        }
	}
	    var idsLength = document.getElementsByName("hiddenkindCode");
		var kindFlag = '${geKind.kindflag}';
		if(kindFlag == '01'){
			$("#flagRelateShow").show();
			if(idsLength.length>0){
				$("#flagRelate").append("��");
				$("#kindDiv").show();
			}else {
				$("#flagRelate").append("��");
				$("#kindDiv").hide();	
				$("#flagRelateShow").show();
			}
		}
});

   function doUpate(){
   	  window.location.href = "${ctx }/business/cmpProductManage/riskAndKindManage/prepareUpdateGeKind.do?geKind.id.riskCode=${geKind.id.riskCode}&geKind.id.kindCode=${geKind.id.kindCode}";
   }
  
   //������ձ����ײ���ʹ�� ����ɾ��
   function doDelete(){
	   var kindCode = document.getElementById("kindCodeV").value;
	   var riskC = '${geKind.id.riskCode}';
	   $.ajax({
	  		  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkKindCodeUnique.do',
	  		  dataType: 'text',
	  		  data:{"geKind.id.kindCode":kindCode,"geKind.id.riskCode":riskC},
	  		  success: function(backData){
	  			  if(backData == '01'){
	  				 var kindFlagv = '${geKind.kindflag}';
	  				  if(kindFlagv=='02'){
	  					  if(vaildateRelateCode()){
	  						 if(confirm('��ȷ��ɾ�����ձ���')){
	  							$("#frmInput").submit();
	  						 }
	  					   }
	  				  }else {
	  					if(confirm('��ȷ��ɾ�����ձ���')){
	  					  $("#frmInput").submit();
	  					}
	  				  }
	  			  }else{
	  				alert('�Բ�����ձ������ײ���ʹ��');
	  				window.parent.opener.parent.frames[0].doSearch();
	  				window.parent.close();
	  			  }
	  		  }
	  		});
   }
   

   // �жϸø������Ƿ������չ�ϵ 
   function vaildateRelateCode(){
	   var flag = true ;
	   var kindCode = document.getElementById("kindCodeV").value;
	   var riskCode ='${geKind.id.riskCode}';
	   $.ajax({
	  		  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/vaildateRelateCode.do',
	  		  dataType: 'json',
	  		  data:{"kindCode":kindCode,"riskCode":riskCode},
	  		  success: function(backData){
	  			  if(backData.tempList!= ''){
	  				alert('�ø������ѱ�����'+backData.tempList+'����');
	  				flag = false ;
	  			  }
	  		  }
	  		});
	   return flag ;
   }
   
   function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("�������600����");
		    }
	}
</script>
</html>
