<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  var contextRootPath = "${ctx}";
</script>

<%
	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "No-Cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<script type="text/vbscript">
Function str2asc(strstr)
str2asc = hex(asc(strstr))
End Function
Function asc2str(ascasc)
asc2str = chr(ascasc)
End Function
</script>
<script type="text/javascript">
	/*UrlEncode or UrlDecode*/
	function UrlEncode(str) {
		var ret = "";
		var strSpecial = "!\"#$%&'()*+,/:;<=>?[]^`{|}~%";
		var tt = "";

		for ( var i = 0; i < str.length; i++) {
			var chr = str.charAt(i);
			var c = str2asc(chr);
			tt += chr + ":" + c + "n";
			if (parseInt("0x" + c) > 0x7f) {
				ret += "%" + c.slice(0, 2) + "%" + c.slice(-2);
			} else {
				if (chr == " ")
					ret += "+";
				else if (strSpecial.indexOf(chr) != -1)
					ret += "%" + c.toString(16);
				else
					ret += chr;
			}
		}
		return ret;
	}
	function UrlDecode(str) {
		var ret = "";
		for ( var i = 0; i < str.length; i++) {
			var chr = str.charAt(i);
			if (chr == "+") {
				ret += " ";
			} else if (chr == "%") {
				var asc = str.substring(i + 1, i + 3);
				if (parseInt("0x" + asc) > 0x7f) {
					ret += asc2str(parseInt("0x" + asc
							+ str.substring(i + 4, i + 6)));
					i += 5;
				} else {
					ret += asc2str(parseInt("0x" + asc));
					i += 2;
				}
			} else {
				ret += chr;
			}
		}
		return ret;
	}
	function GetQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) { 
	        return unescape(r[2]);
	    }
	    return null;
	}
</script>