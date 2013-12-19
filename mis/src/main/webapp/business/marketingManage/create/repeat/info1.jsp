<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String messages = request.getParameter("message");
  String[] shows = messages.split("@");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<style type="text/css">
body{padding:0px; margin:0px; background-color:#FFFFFF; font-size:12px; font-family:Arial; color:#000000;}
div,p,h1,h2,h3,h4,h5,h6{padding:0px; margin:0px;}
ul,li{padding:0px; margin:0px; list-style-type:none;}


.popss_warpper{width:625px; height:387px;background:url(../../../../global/images/prompt_popup_bg.jpg) no-repeat; clear:both;}
.popss_title{clear:both; height:38px; line-height:38px; color:#FFFFFF; padding-left:36px; letter-spacing:8px;}
.popss_title h4{line-height:55px; font-size:14px; color:#FFFFFF; padding-left:235px; font-weight:lighter; letter-spacing:8px;}
.popss_news{clear:both; background:url(../../../../global/images/prompt_popup_img2.jpg) no-repeat 175px 92px; padding:140px 0px 100px 295px;}
.popss_news1{ padding:0px 0px 100px 250px;}
</style>
</head>
<body  onload="clazzBr();">
<div style="height:20px"></div>
<div style="padding-left:210px;">
	<div class="popss_warpper" id="auditN">
		<div class="popss_title"><h4>系统信息</h4></div>
		<div class="popss_news"><table><tr> <td class="clazza" id="clazza0"  style= "word-break:break-all ; width: 280px;"><span style="font-weight: bold; font-size: 12px"> <%=shows[2]%></span></td></tr> </table>&nbsp;营销活动<%=shows[0] %>！<br/>
		
		&nbsp;是否提交审核：
		<input type="radio" name="audit" value="yes" onchange="subbtn();">是
		<input type="radio" name="audit" value="no" onchange="subbtn();">否	<br><br/>
	<div style="padding-left: 50px"><input type="button" id="btn" value="&nbsp;确&nbsp;&nbsp;定&nbsp;"   onclick="submitAuditMarketing('<%=shows[1]%>','<%=shows[2]%>');"></div>
		</div>
		<div class="popss_news1">
		</div>
		<!--<c:if test="${!empty param.buttonValue}">
			<div style="padding-left:210px;">
				<c:if test="${param.buttonValue == 'close'}">
					<input type="button" value="关闭" onclick="javascript：window.top.close();">
				</c:if>
				<c:if test="${param.buttonValue == 'back'}">
					<input type="button" value="关闭" onclick="javascript：window.top.close();">
				</c:if>
				<c:if test="${param.buttonValue == 'homePage'}">
					<input type="button" value="返回首页" onclick="javascript：window.top.location.href = '${ctx}/index.jsp';">
				</c:if>
			</div>
		</c:if>-->
	</div>
	<div class="popss_warpper" id="auditB" style="display: none;">
		<div class="popss_title"><h4>系统信息</h4></div>
	<div class="popss_news"  ><table><tr><td class="clazza1" id="clazza1"  style= "word-break:break-all ; width: 280px;"><span style="font-weight: bold; font-size: 12px"> <%=shows[2]%></span></br><span style="font-size: 12px">营销活动提交审核成功！</span></td></tr></table></div>
	</div>
	<div class="popss_warpper" id="auditC" style="display: none;">
		<div class="popss_title"><h4>系统信息</h4></div>
	<div class="popss_news"  ><table><tr><td class="clazza2" id="clazza2"  style= "word-break:break-all ; width: 280px;"><span style="font-weight: bold; font-size: 12px"> <%=shows[2]%></span></br><span style="font-size: 12px">营销活动<%=shows[0] %>！</span></td></tr></table></div>
	</div>
</div>
</body>
<script type="text/javascript">
	function view(id){
		window.open("${ctx}/marketing/view.do?activityId=" + id,"查询活动","top=100, left=100, width=1100,height=600,toolbar=no,menubar=no,scrollbars=yes");
	}
	
	function subbtn(){
		//var auditcheked=$("input[@type='radio'][name='audit'][checked]").val();
		//$('input:button').removeAttr("disabled");
	}
	function submitAuditMarketing(actid,actName){
		var auditcheked=$("input[@type='radio'][name='audit'][checked]").val();
		if(auditcheked==undefined){
			//$("input:button").attr("disabled","disabled");
		}else{
			if(auditcheked=="yes"){
				$("input:button").attr("disabled","");
			//	submitApprovalMarketing(activityId);
			submitApprovalMarketing(actid,actName)
			}else{
				document.getElementById("auditB").style.display="none";
  				document.getElementById("auditN").style.display="none";
  				document.getElementById("auditC").style.display="block";
			}
		}
		/*if($("#authorityDepartmentManager").val()==""){
			alert("请先选择  活动地域");	
		}else{
			//提练出业务领域
			var authorityid = $("#authorityid").val();
			window.open("${ctx}/business/marketingManage/create/selectProduct/index.jsp?count="+count+"&deptId="+authorityid+"&coreProductCode="+ obj.value, "查询产品", "top=100, left=100, width=900,height=600,toolbar=no");	
		}*/
		
	}
	//提交审核对应的 事件 
	function submitApprovalMarketing(activityId,actName){
			$.ajax({
		  		  type: "POST",
		  		  async:false,
		  		  url: contextRootPath+'/marketing/submitApplyMarketing.do',
		  		  dataType: 'text',
		  		  data:{"activityId":activityId},
		  		  success: function(backData){
		  			  if(backData == '01'){
		  				document.getElementById("auditB").style.display="block";
		  				document.getElementById("auditN").style.display="none";
		  			  }else{
		  				alert('提交审核出现异常');
		  				window.close();
		  				window.parent.opener.parent.frames[0].doSearch();
		  			  }
		  		  }
		  		});	//end ajax
	}
	/*window.setInterval(clazzBr, 3000); 
	function showalert() 
	{ 
	alert("a"); 
	} */
	
	function clazzBr(){
		if($(".clazza").length>0){
			for(var i=0;i<$(".clazza").length;i++){
				var obj2=document.getElementById("clazza0");
				
				//alert(obj2.innerHTML);
				var strContent2=obj2.innerHTML;
			    var strTemp2="";
			    while(strContent2.length>10){
			          strTemp2+=strContent2.substr(0,110)+"<br />"; 
			          strContent2=strContent2.substr(110,strContent2.length); 
			    }
			    strTemp2+=" "+strContent2;
			    obj2.innerHTML=strTemp2;
			}
		}
	}
</script>
</html>