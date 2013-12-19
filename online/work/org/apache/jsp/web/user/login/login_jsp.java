package org.apache.jsp.web.user.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.lang.StringUtils;
import cn.com.sinosoft.ebusiness.service.user.personal.service.spring.UpdateLoginServiceSpringImpl;
import cn.com.sinosoft.ebusiness.sys.permission.service.spring.LoginUserException;
import java.util.*;
import cn.com.sinosoft.util.io.PathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import cn.com.sinosoft.util.io.PathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/global/ui/taglibs_data.jsp");
    _jspx_dependants.add("/web/user/login/userPersonLoginValidate.jsp");
    _jspx_dependants.add("/web/user/login/taglibs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("/**\r\n");
      out.write(" * GetQueryString(\"parameter1\");\r\n");
      out.write(" * \r\n");
      out.write(" */\r\n");
      out.write("function getQueryString(name) {\r\n");
      out.write("\tvar reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\", \"i\");\r\n");
      out.write("\tvar r = window.location.search.substr(1).match(reg);\r\n");
      out.write("\tif (r != null) return unescape(r[2]); return null;\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * var Request = new Object();\r\n");
      out.write(" * Request = GetRequest();\r\n");
      out.write(" * var parameter1,parameter2,parameterN; \r\n");
      out.write(" * parameter1 = Request['parameter1'];\r\n");
      out.write(" * parameter2 = Request['parameter2'];\r\n");
      out.write(" * parameterN = Request['parameterN'];\r\n");
      out.write(" */\r\n");
      out.write("function GetRequest() {\r\n");
      out.write("\tvar url = location.search;\r\n");
      out.write("    var theRequest = new Object();\r\n");
      out.write("    if (url.indexOf(\"?\") != -1) {\r\n");
      out.write("        var str = url.substr(1);\r\n");
      out.write("        if (str.indexOf(\"&\") != -1) {\r\n");
      out.write("            strs = str.split(\"&\");\r\n");
      out.write("            for (var i = 0; i < strs.length; i++) {\r\n");
      out.write("                theRequest[strs[i].split(\"=\")[0]] = unescape(strs[i].split(\"=\")[1]);\r\n");
      out.write("            }\r\n");
      out.write("        } else {\r\n");
      out.write("            theRequest[str.split(\"=\")[0]] = unescape(str.split(\"=\")[1]);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    return theRequest;\r\n");
      out.write("}\r\n");
      out.write("</script>");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  var contextRootPath = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");

	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "No-Cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	
// 	PropertyFileUtils.init(PathUtil.getClassBuildPath() + "/config/companyInfo.properties");

      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/vbscript\">\r\n");
      out.write("Function str2asc(strstr)\r\n");
      out.write("str2asc = hex(asc(strstr))\r\n");
      out.write("End Function\r\n");
      out.write("Function asc2str(ascasc)\r\n");
      out.write("asc2str = chr(ascasc)\r\n");
      out.write("End Function\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t/*UrlEncode or UrlDecode*/\r\n");
      out.write("\tfunction UrlEncode(str) {\r\n");
      out.write("\t\tvar ret = \"\";\r\n");
      out.write("\t\tvar strSpecial = \"!\\\"#$%&'()*+,/:;<=>?[]^`{|}~%\";\r\n");
      out.write("\t\tvar tt = \"\";\r\n");
      out.write("\r\n");
      out.write("\t\tfor ( var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\t\tvar chr = str.charAt(i);\r\n");
      out.write("\t\t\tvar c = str2asc(chr);\r\n");
      out.write("\t\t\ttt += chr + \":\" + c + \"n\";\r\n");
      out.write("\t\t\tif (parseInt(\"0x\" + c) > 0x7f) {\r\n");
      out.write("\t\t\t\tret += \"%\" + c.slice(0, 2) + \"%\" + c.slice(-2);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tif (chr == \" \")\r\n");
      out.write("\t\t\t\t\tret += \"+\";\r\n");
      out.write("\t\t\t\telse if (strSpecial.indexOf(chr) != -1)\r\n");
      out.write("\t\t\t\t\tret += \"%\" + c.toString(16);\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t\tret += chr;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn ret;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction UrlDecode(str) {\r\n");
      out.write("\t\tvar ret = \"\";\r\n");
      out.write("\t\tfor ( var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\t\tvar chr = str.charAt(i);\r\n");
      out.write("\t\t\tif (chr == \"+\") {\r\n");
      out.write("\t\t\t\tret += \" \";\r\n");
      out.write("\t\t\t} else if (chr == \"%\") {\r\n");
      out.write("\t\t\t\tvar asc = str.substring(i + 1, i + 3);\r\n");
      out.write("\t\t\t\tif (parseInt(\"0x\" + asc) > 0x7f) {\r\n");
      out.write("\t\t\t\t\tret += asc2str(parseInt(\"0x\" + asc\r\n");
      out.write("\t\t\t\t\t\t\t+ str.substring(i + 4, i + 6)));\r\n");
      out.write("\t\t\t\t\ti += 5;\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tret += asc2str(parseInt(\"0x\" + asc));\r\n");
      out.write("\t\t\t\t\ti += 2;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tret += chr;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn ret;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction GetQueryString(name) {\r\n");
      out.write("\t    var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\");\r\n");
      out.write("\t    var r = window.location.search.substr(1).match(reg);\r\n");
      out.write("\t   \r\n");
      out.write("\t    if (r != null) { \r\n");
      out.write("\t        return unescape(r[2]);\r\n");
      out.write("\t    }\r\n");
      out.write("\t    return null;\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /web/user/login/userPersonLoginValidate.jsp(9,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty param.error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write('\r');
          out.write('\n');

AuthenticationException authenticationException = (AuthenticationException)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
if(authenticationException != null){
	//用户名密码有误
	if (authenticationException.getAuthentication() instanceof UsernamePasswordAuthenticationToken ){
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		if (authenticationException.getMessage().equals("Bad credentials")) {
			request.setAttribute("loginMessage", "密码错误，请重新输入");
			
			UpdateLoginServiceSpringImpl.updateUserLoginFailedCount(userAccName);
			
		} else {
			request.setAttribute("loginMessage", new String(authenticationException.getMessage()));
		}
		request.setAttribute("userAccName",userAccName);
		
		
		//还原用户名
		userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
	
	
	//用户自定义登录异常
	if(authenticationException instanceof LoginUserException){
		request.setAttribute("loginMessage","该用户已经为无效用户,无法登录!");
		
		//还原用户名
		String userAccName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
		request.setAttribute("userAccName",userAccName);
	}
}

          out.write('\r');
          out.write('\n');
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  var contextRootPath = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");

	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "No-Cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	
// 	PropertyFileUtils.init(PathUtil.getClassBuildPath() + "/config/companyInfo.properties");

      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/vbscript\">\r\n");
      out.write("Function str2asc(strstr)\r\n");
      out.write("str2asc = hex(asc(strstr))\r\n");
      out.write("End Function\r\n");
      out.write("Function asc2str(ascasc)\r\n");
      out.write("asc2str = chr(ascasc)\r\n");
      out.write("End Function\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t/*UrlEncode or UrlDecode*/\r\n");
      out.write("\tfunction UrlEncode(str) {\r\n");
      out.write("\t\tvar ret = \"\";\r\n");
      out.write("\t\tvar strSpecial = \"!\\\"#$%&'()*+,/:;<=>?[]^`{|}~%\";\r\n");
      out.write("\t\tvar tt = \"\";\r\n");
      out.write("\r\n");
      out.write("\t\tfor ( var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\t\tvar chr = str.charAt(i);\r\n");
      out.write("\t\t\tvar c = str2asc(chr);\r\n");
      out.write("\t\t\ttt += chr + \":\" + c + \"n\";\r\n");
      out.write("\t\t\tif (parseInt(\"0x\" + c) > 0x7f) {\r\n");
      out.write("\t\t\t\tret += \"%\" + c.slice(0, 2) + \"%\" + c.slice(-2);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tif (chr == \" \")\r\n");
      out.write("\t\t\t\t\tret += \"+\";\r\n");
      out.write("\t\t\t\telse if (strSpecial.indexOf(chr) != -1)\r\n");
      out.write("\t\t\t\t\tret += \"%\" + c.toString(16);\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t\tret += chr;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn ret;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction UrlDecode(str) {\r\n");
      out.write("\t\tvar ret = \"\";\r\n");
      out.write("\t\tfor ( var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\t\tvar chr = str.charAt(i);\r\n");
      out.write("\t\t\tif (chr == \"+\") {\r\n");
      out.write("\t\t\t\tret += \" \";\r\n");
      out.write("\t\t\t} else if (chr == \"%\") {\r\n");
      out.write("\t\t\t\tvar asc = str.substring(i + 1, i + 3);\r\n");
      out.write("\t\t\t\tif (parseInt(\"0x\" + asc) > 0x7f) {\r\n");
      out.write("\t\t\t\t\tret += asc2str(parseInt(\"0x\" + asc\r\n");
      out.write("\t\t\t\t\t\t\t+ str.substring(i + 4, i + 6)));\r\n");
      out.write("\t\t\t\t\ti += 5;\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tret += asc2str(parseInt(\"0x\" + asc));\r\n");
      out.write("\t\t\t\t\ti += 2;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tret += chr;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn ret;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction GetQueryString(name) {\r\n");
      out.write("\t    var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\");\r\n");
      out.write("\t    var r = window.location.search.substr(1).match(reg);\r\n");
      out.write("\t   \r\n");
      out.write("\t    if (r != null) { \r\n");
      out.write("\t        return unescape(r[2]);\r\n");
      out.write("\t    }\r\n");
      out.write("\t    return null;\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery.tools.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery.colorbox.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/emailpop.css\"></link>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/emailpop.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var s_userName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal.userAccount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$(\"#user_name\").emailpop();\r\n");
      out.write("});\r\n");
      out.write("function showErrorMessage(){\r\n");
      out.write("\t$(\"#message\").html(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMessage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function login(){\r\n");
      out.write("\tvar userName = $(\"#user_name\").val();\r\n");
      out.write("\tif(userName == \"\" || userName == \"邮箱/手机/身份证\"){\r\n");
      out.write("\t\t$(\"#message\").html(\"请输入登录账号!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar password = $(\"#password\").val();\r\n");
      out.write("\tif(password == \"\" || password == \"请输入密码\"){\r\n");
      out.write("\t\t$(\"#message\").html(\"请输入登录密码!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar flag = document.getElementById('remeberMe').checked;\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype : \"POST\",\r\n");
      out.write("\t\tasync : false,\r\n");
      out.write("\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/remeber.do\",\r\n");
      out.write("\t\tdataType : 'text',\r\n");
      out.write("\t\tdata : {remeber : flag },\r\n");
      out.write("\t\tsuccess: function(data) {}\r\n");
      out.write("\t});\r\n");
      out.write("\tvar result = \"\";\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype : \"POST\",\r\n");
      out.write("\t\tasync : false,\r\n");
      out.write("\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/checkSubmitLoginForm.do\",\r\n");
      out.write("\t\tdataType : 'text',\r\n");
      out.write("\t\tdata : {userName : userName, password : password },\r\n");
      out.write("\t\tsuccess: function(data) {\r\n");
      out.write("\t\t\tresult = data;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\tif (result == \"paramError\") {\r\n");
      out.write("\t\tSinosoft.alert({\r\n");
      out.write("\t\t\tcontentStr: \"参数错误\",\r\n");
      out.write("\t\t\twidth:480,\r\n");
      out.write("\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}　else if (result == \"accountLogin\") {//不是通过身份证号登录的,提交保单\r\n");
      out.write("\t\t$(\"#login_form\").submit();\r\n");
      out.write("\t} else if (result == \"idNumError\") {\r\n");
      out.write("\t\tSinosoft.alert({\r\n");
      out.write("\t\t\tcontentStr: \"证件号或密码错误\",\r\n");
      out.write("\t\t\twidth:480,\r\n");
      out.write("\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else if (result == \"accountLock\"){//帐号已被锁定\r\n");
      out.write("\t\tSinosoft.alert({\r\n");
      out.write("\t\t\tcontentStr: \"帐号已被锁定，请2个小时之后登录\",\r\n");
      out.write("\t\t\twidth:480,\r\n");
      out.write("\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t} else if (result == \"bindFalse\") {\r\n");
      out.write("\t\tSinosoft.alert({\r\n");
      out.write("\t\t\tcontentStr: \"您尚未购买过保单或尚未进行过保单绑定，不可通过身份证号登录\",\r\n");
      out.write("\t\t\twidth:480,\r\n");
      out.write("\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t} else if (result == \"idNumLogin\") {//该身份证号与密码只对应一个帐号\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/idNumLogin.do\",\r\n");
      out.write("\t\t\tdataType : 'text',\r\n");
      out.write("\t\t\tdata : {userAccount : userName,pwd : password },\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tidLoginFlag = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tif (idLoginFlag == \"accountNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"用户名不能为空\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"customerNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"用户名不存在，请确认\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"lock\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"帐号已锁定\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"pwdNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码不能为空\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"pwdError\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码错误，请确认输入密码是否正确\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"bindFalse\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"您尚未购买过保单或尚未进行过保单绑定，不可以通过身份证号登录\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}else if (idLoginFlag == \"success\") {\r\n");
      out.write("\t\t\twindow.location.href= \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/userPersonalLogin.do\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else {\r\n");
      out.write("\t\tvar accounts = result.split(\"%%%\");\r\n");
      out.write("\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function openLoginWindow(accounts) {\r\n");
      out.write("\tnew Sinosoft.InteractiveDialog({\r\n");
      out.write("\t\tlayout : loginByIdNum(accounts),\r\n");
      out.write("\t\twidth:410,//自定义面板宽度-根据设计来调整\r\n");
      out.write("\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\tokStr : '确认',\r\n");
      out.write("\t\tcancelStr : '取消',\r\n");
      out.write("\t\tokFunc:function() {\r\n");
      out.write("\t\t\tvar account = $('input[name=\"loginAccount\"]:checked').val();\r\n");
      out.write("\t\t\tif (account == null || account == \"\") {\r\n");
      out.write("\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\tcontentStr: \"请选择您要登录的帐号\",\r\n");
      out.write("\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tidentifyNumLogin(accounts, account);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}).open();\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tfunction identifyNumLogin(accounts, account) {\r\n");
      out.write("\t\tvar idLoginFlag= \"\";\r\n");
      out.write("\t\tvar password = $(\"#password\").val();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/checkLogin.do\",\r\n");
      out.write("\t\t\tdataType : 'text',\r\n");
      out.write("\t\t\tdata : {userName : account,password : password },\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tidLoginFlag = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tif (idLoginFlag == \"accountNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"用户名不能为空\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"customerNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"用户名不存在，请确认\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"lock\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"帐号已锁定\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"pwdNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码不能为空\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"pwdError\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码错误，请确认输入密码是否正确\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t} else if (idLoginFlag == \"success\") {\r\n");
      out.write("\t\t\twindow.location.href= \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/userPersonalLogin.do\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function loginByIdNum(accounts){\r\n");
      out.write("\tvar subSuccess = '<div class=\"alert_subscribe\">请选择您要登录的账号:<div class=\"success\"></div><div class=\"main_content\">';\r\n");
      out.write("\tfor (var i = 0; i < accounts.length; i ++) {\r\n");
      out.write("\t\tsubSuccess += '<div class=\"sub_txt\"><input type=\"radio\" name=\"loginAccount\" value=\"'+accounts[i]+'\">'+accounts[i]+'</input><br></div>';\r\n");
      out.write("\t}\r\n");
      out.write("\tsubSuccess += '</div></div>';\r\n");
      out.write("\t\r\n");
      out.write("\tsubSuccess = $(subSuccess);\r\n");
      out.write("\treturn subSuccess;\r\n");
      out.write("}\r\n");
      out.write("function onReturn(evt) {\r\n");
      out.write("\tvar currKey=evt.keyCode||evt.which||evt.charCode; \r\n");
      out.write("\tif (currKey==13) {\r\n");
      out.write("\t\tlogin();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\t<body onkeypress=\"return onReturn(event);\" onload=\"showErrorMessage();\">\r\n");
      out.write("\t\t<div class=\"middle\">\r\n");
      out.write("\t\t\t<div class=\"h_layout\">\r\n");
      out.write("\t\t\t\t<div class=\"login_main\">\r\n");
      out.write("\t\t\t\t\t<div class=\"login_form\">\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/j_spring_security_check\" id=\"login_form\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t");
      //  c:choose
      org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
      _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fchoose_005f0.setParent(null);
      int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
      if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t");
          //  c:otherwise
          org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
          _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
          _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
          int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
          if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t<label for=\"user_name\" class=\"input_label\">会员名：<span id=\"message\" escapeXml=\"false\"></span></label>\r\n");
              out.write("\t\t\t\t\t\t\t\t<input class=\"input_field\" type=\"text\" name=\"j_username\" id=\"user_name\" value=\"");

									if (request.getAttribute("userAccName") != null && request.getAttribute("userAccName") != "null") {
										
              out.print(request.getAttribute("userAccName") );
 
									}
								
              out.write("\"/>\r\n");
              out.write("\t\t\t\t\t\t\t");
              int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"input_field\" type=\"password\" name=\"j_password\" id=\"password\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"action\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"remeberMe\" id=\"remeberMe\" type=\"checkbox\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"remember\">记住我</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a class=\"forget_password\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/web/user/resetPwd/index.jsp\">忘记密码？</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a class=\"reg\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/web/user/register/index.jsp\">立即注册</a>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"login_submit\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"login\" onclick=\"return login();\">立即登录</button>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"other_login\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"login_type alipay\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/web/user/login/alipayLogin/alipayapi.jsp\">支付宝登录</a></p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"login_type weibo\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/sinaLogin.do\">微博登录</a></p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"login_type tencent\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oauth/qqoauth\">QQ登录</a></p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</body>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /global/ui/taglibs_data.jsp(5,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /global/ui/taglibs_data.jsp(5,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent(null);
    // /web/user/login/taglibs.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("ctx");
    // /web/user/login/taglibs.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /web/user/login/taglibs.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("ctx");
    // /web/user/login/taglibs.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /web/user/login/login.jsp(293,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal != null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<br><br>\r\n");
        out.write("\t\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了您的账户安全，请确认密码：\r\n");
        out.write("\t\t\t\t\t\t\t<br>\r\n");
        out.write("\t\t\t\t\t\t\t<br>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /web/user/login/login.jsp(294,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty message }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目前您已登录帐号：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal.userAccount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /web/user/login/login.jsp(297,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty message }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t目前您已登录帐号：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal.userAccount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /web/user/login/login.jsp(308,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal != null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<input class=\"input_field\" type=\"hidden\" name=\"j_username\" id=\"user_name\" value=\"\"/>\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent(null);
    // /web/user/login/login.jsp(320,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geUserPersonal == null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<label for=\"password\"  class=\"input_label\">密码：</label>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }
}
