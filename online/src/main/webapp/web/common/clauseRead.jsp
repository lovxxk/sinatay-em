<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/flexpaper/swfobject/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/global/js/flexpaper/flexpaper_flash.js"></script>
<script type="text/javascript"> 
	var swfVersionStr = "9.0.124";
	var xiSwfUrlStr = "${ctx}/global/js/flexpaper/playerProductInstall.swf";
	var Request = new Object();
	Request = GetRequest();
	var flashvars = {
	          SwfFile : escape("${ctx}/upload/clause/"+Request['productCode']+"/"+Request['riskCode']+".swf"),
			  Scale : 0.8, 
			  ZoomTransition : "easeOut",
			  ZoomTime : 0.5,
			  ZoomInterval : 0.1,
			  FitPageOnLoad : true, //���غ��ʺϸ߶�
			  FitWidthOnLoad : true, //���غ��ʺϿ��
			  PrintEnabled : false,   //�Ƿ�֧�ִ�ӡ
			  FullScreenAsMaxWindow : true,  //�Ƿ�֧��ȫ��
			  ProgressiveLoading : true,  //�Ƿ�֧���ӳټ���
			  PrintToolsVisible : false, //������Ƿ��ֹ��ӡ��   (���������ձ鷴ӳ��ֹ���˴�ӡ)
			  ViewModeToolsVisible : true,	//�鿴��ʽ�Ƿ�ɼ�
			  ZoomToolsVisible : true,		//�Ŵ����Ƿ�ɼ�
			  FullScreenVisible : false,	  //ȫƵ�ɼ�
			  NavToolsVisible : true,    //��ҳ�Ƿ�ɼ�
			  CursorToolsVisible : false, //����Ƿ�ɼ�(�ɷ����������ļ��϶�)
			  SearchToolsVisible : true, //�������� �Ƿ�ɼ�
			  localeChain: "zh_CN" //����
		  };
	var params = {};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "FlexPaperViewer";
	attributes.name = "FlexPaperViewer";
	swfobject.embedSWF("${ctx}/global/js/flexpaper/FlexPaperViewer.swf", "flashContent", "1024", "800", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:left;");
</script>
<div id="flashContent">
	<p>
	To view this page ensure that Adobe Flash Player version 10.0.0 or greater is installed. 
	</p>
	<script type="text/javascript"> 
		var pageHost = ((document.location.protocol == "https:") ? "https://" :	"http://"); 
		document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
			+ pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
	</script> 
</div>