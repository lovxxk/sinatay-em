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
			  FitPageOnLoad : true, //加载后适合高度
			  FitWidthOnLoad : true, //加载后适合宽度
			  PrintEnabled : false,   //是否支持打印
			  FullScreenAsMaxWindow : true,  //是否支持全屏
			  ProgressiveLoading : true,  //是否支持延迟加载
			  PrintToolsVisible : false, //这个是是否禁止打印的   (但是网上普遍反映禁止不了打印)
			  ViewModeToolsVisible : true,	//查看方式是否可见
			  ZoomToolsVisible : true,		//放大器是否可见
			  FullScreenVisible : false,	  //全频可见
			  NavToolsVisible : true,    //翻页是否可见
			  CursorToolsVisible : false, //光标是否可见(可否用鼠标点着文件拖动)
			  SearchToolsVisible : true, //搜索工具 是否可见
			  localeChain: "zh_CN" //语言
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