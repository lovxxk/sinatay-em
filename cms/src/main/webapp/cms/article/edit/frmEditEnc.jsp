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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="${ctx}/cms/global/css/common.css">
<script language="javascript" type="text/javascript" src="${ctx}/cms/global/js/date/WdatePicker.js"></script>
<title>附件文章</title>
<script>
function doEnclosure(state){
	/*
	if(check()){	
		document.getElementById("state").value=state;
		var aa=document.getElementById("a").contentWindow.document;
		var aaSubmit=aa.getElementById("abc");
		aa.getElementById("doc").value = document.getElementById('doc').value;
		//if(document.getElementById("show").getElementsByTagName("a").length<=0){
				//alert("请添加附件！");
				//return false;
			//}
		aaSubmit.submit();
	}
	*/
	document.getElementsByName("submit")[0].click();
}

function submitContent(){
	document.getElementById("fraMain").action = "${ctx}/articleManage/toUploadeEdit.do?tmpDocId=<%=tmpDocId%>";
	document.getElementById("fraMain").target = "getAPFrame";
	document.getElementsByName("submit")[0].click();
}

function check(){
	var value="docTitle,docKeysword,docreltime";
	var valueArry=value.split(",");
	for(var i=0;i<valueArry.length;i++){
		if(eval("document.getElementsByName(\""+valueArry[i]+"\")[0].value==''")){	
		 	alert("请输入该信息！");
		 	eval("document.getElementsByName(\""+valueArry[i]+"\")[0].focus()")
			return false;
			
		}
	}
	return true;
}
</script>
</head>
<body topmargin=0 leftmargin=0>
<s:form id="fraMain" method="post" action="toUploadeImage" target="getAPFrame" width="100%" height="100%" border="2" enctype="multipart/form-data">
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
			<td colspan=3 style="BACKGROUND: url(../../../global/images/cms_tc_foot_bg.jpg) repeat-x bottom;vertical-align:top;" height=310 align=center>
				<table border=0 width=870 cellpadding=0 cellspacing=0 class="table_Show">
					<tr>
						<td class="td_in1"  nowrap height=40>文章标题</td>
						<td width=2 nowrap></td>
						<td class="td_in2"  align="left">&nbsp;
							<input value="" type=text name=docTitle maxlength=100 /></td>
						<td width=2 nowrap></td>
						<td class="td_in1"  nowrap height=40>文章归属栏目</td>
						<td width=2 nowrap></td>
						<td class="td_in2"  align="left">&nbsp;
								
							<input value="<%=chnnlDesName %>" type=text name="docChannelName" readonly />
							<input value="<%=ID %>" type=hidden name="docChannel" id="docChannel" />
							<input value="<%=tmpDocId %>" type=hidden name="tmpDocId" id="tmpDocId" />
						</td>
						<td width=2 nowrap></td>
					</tr>
					
					<tr>
						<td class="td_in1" nowrap height=40>文章关键字</td>
						<td nowrap></td>
						<td class="td_in2" align="left">&nbsp;
							<input type="text" name="docKeysword" value="" maxlength=200 />
						</td>
						<td nowrap></td>
						<td class="td_in1" nowrap height=40>文章撰写时间</td>
						<td nowrap></td>
						<td class="td_in2" align="left">&nbsp;
							<input id="d12" type="text" name="docreltime"  readonly/>
							<img onclick="WdatePicker({el:$dp.$('d12')})" src="${ctx}/cms/global/js/date/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
						</td>
					</tr>
					<tr>
						<td class="td_in1" nowrap height=40 >文章类型</td>
						<td nowrap></td>
						<td class="td_in2" align="left" colspan=5>&nbsp;
							<input  value="附件文档" type=text name="docTypeName"  readonly/>
							<input  value="2" type="hidden" name="docType" />
						</td>
					</tr>
					<tr>	
						<!--<td class="td_in1" nowrap height=40 ><a onclick="accessories();" style="cursor:hand;text-decoration:underline;"><font color=blue>增加附件</font></a></td>
						<td nowrap>
						<td class="td_in2" align="left" colspan=5>&nbsp;
							<div id="show" value="show"></div>
						
						</td>
					--></tr>
					<tr>
						<td class="td_in1" nowrap height=40 >增加附件</td>
						<td nowrap></td>
						<td class="td_body">
							<s:file name="doc" label="File"></s:file>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td height=10></td>
					</tr>
				</table>
				<table width=870 height=130 cellspacing=0 cellpadding=0 border=0 align=center>
					<tr>
						<td>
							<table height=100 cellspacing=0 cellpadding=0 border=0 align=center>
								<tr>
								
									<td width=10 align=center></td>
									<td align=center>
										<table>
											<tr>
												<td class="btn_ord2" onclick="doEnclosure('save');">&nbsp;&nbsp;保存</td>
											</tr>
										</table>
									</td>
									<td width=10 align=center></td>
									<td align=center>
										<table>
											<tr>
												<td class="btn_ord2" onclick="window.top.close();">&nbsp;&nbsp;关闭</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="95%">
					<tr>
						<td height=2 style="background: url(${ctx}/cms/global/images/cms_tc_foot_line.jpg)"></td>
					</tr>
					<tr>
						<td height=10></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!-- <input style="display:none" name="docFileName" value=""/>  -->
	<input style="display:none" name="acctachpic" value=""/>
	<textarea id="elm1" style="display:none" name="elm1"></textarea>
	<input type="submit" name="submit" style="display:none" />
	<input name="docOrder" value="0" style="display:none;"/>
	<input type="submit" name="submit2" style="display:none" />
	<input type="hidden" name="state" id="state" value=""/>
</s:form>

<iframe style="display:none" name="getAPFrame" id="getAPFrame"></iframe>
<!-- <iframe id="a" name="a" src="${ctx}/cms/article/edit/enclosure.jsp?tmpDocId=<%=tmpDocId%>" height=0 width=0></iframe> -->

<script type="text/javascript">
	function accessories(){
		window.open("${ctx}/cms/article/edit/uploadA.jsp","","width=500,height=300,toolbar=no,top=200,left=300");
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
