<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="cn.com.sinosoft.ebusiness.cms.domain.CmsChannel"%>
<%@ page import = "java.util.Calendar"%>
<%@ page import = "java.util.Date"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
CmsChannel channel = (CmsChannel)request.getAttribute("channel");
String ID = channel.getChannelID()+"";
String chnnlDesName = channel.getChnlDesName();
String tmpDocId="";
Calendar nowTime=Calendar.getInstance();
tmpDocId=nowTime.getTimeInMillis()+"";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
String timeStr = sdf.format(new Date()); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/cms.css">
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<script language="javascript" type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<title>普通文章</title>
<script>
function doEnclosure(state){
	/*if(check()){
		document.getElementById("state").value=state;
		var aa=document.getElementById("a").contentWindow.document;
		var aaSubmit=aa.getElementById("abc");
		aaSubmit.submit();
	}*/
	if(check()){
		document.getElementsByName("submit")[0].click();
	}
}

function submitContent(){
	document.getElementById("fraMain").action = "${ctx}/articleManage/toUploadeEdit.do?tmpDocId=<%=tmpDocId%>";
	document.getElementById("fraMain").target = "getAPFrame";
	document.getElementsByName("submit")[0].click();
}

function check(){
	document.getElementById("framContent").contentWindow.save();
	var elm2Value = document.getElementById("framContent").contentWindow.document.getElementById("elm2").value;
	document.getElementsByName("elm1")[0].value = elm2Value;
	var elm1 = document.getElementsByName("elm1")[0].value;
	var value="docTitle,docKeysword,docreltime";
	var valueArry=value.split(",");
	for(var i=0;i<valueArry.length;i++){
		if(eval("document.getElementsByName(\""+valueArry[i]+"\")[0].value==''")){	
		 	alert("请输入该信息！");
		 	eval("document.getElementsByName(\""+valueArry[i]+"\")[0].focus()")
			return false;
			
		}
	}
	if(elm1==""){
		alert("请输入编辑器信息！");
		return false;
		
	}
	return true;
}
</script>
</head>
<body topmargin=0 leftmargin=0>
<s:form  id="fraMain" method="post" action="toUploadeImage" target="getAPFrame" width="100%" height="100%" border="0" enctype="multipart/form-data">
	<table border=0 width=879 cellpadding=0 cellspacing=0>
		<tr>
			<td style="BACKGROUND: url(${ctx}/cms/global/images/cms_tc_top_bg.jpg);font-size:13px;color:#4a4c4b;font-weight:bold;" width=135 height=36 valign="top">&nbsp;&nbsp;<img src="${ctx}/cms/global/images/cms_tc_top_ico.jpg"/>&nbsp;&nbsp;文章新建</td>
			<td valign="top" width=37><img src="${ctx}/cms/global/images/cms_tc_top_se.jpg"/></td>
			<td background="${ctx}/cms/global/images/cms_tc_top_bg2.jpg" valign="top" width=723></td>
		</tr>
		<tr>
			<td colspan=3 height=10></td>
		</tr>
		<tr>
			<td colspan=3 style="BACKGROUND: url(${ctx}/cms/global/images/cms_tc_foot_bg.jpg) repeat-x bottom;vertical-align:top;" height=670 align=center>
				<table border=0 width=870 cellpadding=0 cellspacing=0 class="table_Show">
					<tr>
						<td class="td_in1" width=88 nowrap height=40>文章标题</td>
						<td width=2 nowrap></td>
						<td class="td_in2" width=198 align="left">&nbsp;
						<input value="" type=text name=docTitle maxlength=100 />
						</td>
						<td width=2 nowrap></td>
						<td class="td_in1" width=88 nowrap height=40>文章归属栏目</td>
						<td width=2 nowrap></td>
						<td class="td_in2" width=198 align="left">&nbsp;
							
							
							<input value="<%=chnnlDesName %>" type=text name="docChannelName" readonly />
							<input value="<%=ID %>" type=hidden name="docChannel" id="docChannel" />
							<input value="<%=tmpDocId %>" type=hidden name="tmpDocId" id="tmpDocId" />
							
						</td>
						<td width=2 nowrap></td>
						<td class="td_in1" nowrap height=40>文章类型</td>
						<td width=2 nowrap></td>
						<td class="td_in2" align="left">&nbsp;
							<input  value="普通文档" type=text name="docTypeName"  readonly/>
							<input  value="0" type="hidden" name="docType" />
						</td>
						<td width=2 nowrap></td>
					</tr>
				</table>
				<table>
					<tr>
						<td height=10></td>
					</tr>
				</table>
				<table width=870 height=520 cellspacing=0 cellpadding=0 border=0 align=center>
					<tr>
						<td width=702 height=520  class="table_border_grey">
						<iframe src="${ctx}/cms/global/cms/examples/fulls.jsp" id="framContent" name="framContent" width="700" height="520" scrolling="no"  frameborder="0"></iframe>
						</td>
						<td width=5></td>
						<td>
							<table width=141 height=460 cellspacing=0 cellpadding=0 border=0 align=center>
								<tr>
									<td height=20></td>
								</tr>
								<tr>
									<td height=50 class="font_t1" align=center>文章关键字</td>
								</tr>
								<tr>
									<td height=30>
										<textarea rows="10" cols="15" name="docKeysword"></textarea>
									</td>
								</tr>
								<tr>
									<td height=20></td>
								</tr>
								<tr>
									<td height=50 class="font_t1" align=center>文章撰写时间</td>
								</tr>
								<tr>
									<td height=30>
										<input id="d12" type="text" name="docreltime" size="11"  value="<%=timeStr %>" readonly/>
										<img onclick="WdatePicker({el:$dp.$('d12')})" src="${ctx}/cms/global/js/date/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
									</td>
									
								</tr>
								<!--  
								<tr>
									<td height=90 align=center>
										<table>
											<tr>
												<td class="font_t1" >缩略图上传</td>
											</tr>
											<tr>
												<td class="td_body">
													<s:file name="doc" label="File"></s:file>
												</td>
											</tr>
										</table> 
										<div id="show"></div>
									</td>
								</tr>
								-->
								<tr>
									<td height=20></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width=870 height=63 cellspacing=0 cellpadding=0 border=0 align=center>
					<tr>
						<td width=300></td>
						<td width=130 height=50>
							<table>
								<tr>
									<td class="btn_ord2"  onclick="doEnclosure('save');">&nbsp;&nbsp;保存</td>
								</tr>
							</table>
						</td>
						<td width=10></td>
						<td width=130>
							<table>
								<tr>
									<td class="btn_ord2" onclick="window.top.close();" >关闭</td>
								</tr>
							</table>
						</td>
						<td width=300></td>
					</tr>
				</table>
				<table width="95%">
					<tr>
						<td height=20></td>
					</tr>
					<tr>
						<td height=2 style="background: url(${ctx}/cms/global/images/cms_tc_foot_line.jpg)"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input style="display:none" name="docFileName" value=""/>
	<input style="display:none" name="acctachpic" value=""/>
	<textarea id="elm1" style="display:none" name="elm1"></textarea>
	<input type="submit" name="submit" style="display:none" />
	<input name="docOrder" value="0" style="display:none;"/>
	<input type="submit" name="submit2" style="display:none" />
	<input type="hidden" name="state" id="state" value=""/>
	<input type="hidden" name="imageTmpID" id="imageTmpID" value=""/>
</s:form>
<iframe style="display:none" name="getAPFrame" id="getAPFrame"></iframe>
<!--<iframe id="a" name="a" src="${ctx}/cms/article/edit/enclosure.jsp?tmpDocId=<%=tmpDocId%>" height=0 width=0></iframe>-->
<script type="text/javascript">
function accessories(){
	window.open("${ctx}/cms/article/edit/uploadImage.jsp","","width=500,height=300,toolbar=no,top=200,left=300");
}

function getFileName(_sFileName){
	var sFileName = _sFileName || "";
	if(!sFileName || sFileName == ""){
		return "";
	}
	
	sFileName =sFileName.replace('/','\\');
	var nPointIndex =sFileName.lastIndexOf('\\');
	if(nPointIndex < 0){
		return sFileName;
	}
	return sFileName.substring(nPointIndex+1);
}

function deleteSpan(sId){
	var bbshow=document.getElementById("show");
	var delteDom=document.getElementById("newspan"+sId);
	bbshow.removeChild(delteDom);
	var aa=document.getElementById("a").contentWindow.document;
	var deleteFraDom1=aa.getElementById("UploadFileAdd"+(sId-1));
	aa.getElementById("abc").removeChild(deleteFraDom1);
	var deleteFraDom2=aa.getElementById("FileName"+sId);
	aa.getElementById("abc").removeChild(deleteFraDom2);
}

function updateSpan(sId){
	window.open("${ctx}/cms/article/edit/updateAName.jsp?sId="+sId,"","width=500,height=300,toolbar=no,top=200,left=300");
}
</script>
</body>
</html>
