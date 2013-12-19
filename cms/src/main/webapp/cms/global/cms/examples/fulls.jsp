<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String ID = request.getParameter("ID") == null ? "" : request.getParameter("ID").trim();
%>
<script type="text/javascript" src="${ctx }/cms/global/cms/jscripts/tiny_mce/tiny_mce_src.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${ctx }/cms/global/cms/jscripts/tiny_mce/plugins/advimage/css/advimage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	tinyMCE.init({
		// General options
		mode : "textareas",
		theme : "advanced",
		plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,accessories,template",
		width : "500",
		height : "480",
		//save,newdocument,|,,acronym,del,ins,|
		// Theme options
		theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect,|,nonbreaking,pagebreak",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,|,search,replace,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,forecolor,backcolor,|,insertdate,inserttime,preview,",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,media,advhr,|,print,|,ltr,rtl,|,template",
		//theme_advanced_buttons3_add : "accessories",
		//theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
		//theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
		//theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
		//theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		//theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		// Example content CSS (should be your site CSS)
		content_css : "css/content.css",

		// Drop lists for link/image/media/template dialogs
		template_external_list_url : "lists/template_list.js",
		external_link_list_url : "lists/link_list.js",
		external_image_list_url : "lists/image_list.js",
		media_external_list_url : "lists/media_list.js",

		relative_urls : false,
//		remove_script_host : false,

		// Replace values for the template plugin
		template_replace_values : {
			username : "Some User",
			staffid : "991234"
		}
		
	});
	//
	function save(){
		document.getElementById("elm2").value = tinyMCE.get('elm1').getContent();
	}
</script>
<table>
  <tr>
  	<td><textarea id="elm1" name="elm1" rows="15" cols="80" style="width:100%">
			${docContent }
		</textarea>
	</td>
  </tr>
</table>
<input name="ID" id="ID" type="hidden" value="<%=ID %>"/>
<input name="tag" id="tag" type="hidden" value=""/>
<textarea id="elm2" name="elm2" style="display:none" ></textarea>
