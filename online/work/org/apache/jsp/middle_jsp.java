package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class middle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/global/ui/taglibs_data.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
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

      out.write('\r');
      out.write('\n');
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery.tools.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery.colorbox.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/emailpop.css\"></link>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/emailpop.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/common/alert/alert.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(\"#user_name\").emailpop();\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction login() {\r\n");
      out.write("\t\tvar userName = $(\"#user_name\").val();\r\n");
      out.write("\t\tif (userName == \"请输入您的邮箱/手机/身份证\" || userName == \"\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"请输入登录账号!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tvar password = $(\"#password\").val();\r\n");
      out.write("\t\tif (password == \"请输入密码\" || password == \"\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"请输入登录密码!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t//error：用户名密码错误\r\n");
      out.write("\t\t//success：成功\r\n");
      out.write("\t\t//failed：未激活\r\n");
      out.write("\t\tvar loginFlag = \"\";\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/checkLogin.do\",\r\n");
      out.write("\t\t\tdataType : 'text',\r\n");
      out.write("\t\t\tdata : {\r\n");
      out.write("\t\t\t\tuserName : userName,\r\n");
      out.write("\t\t\t\tpassword : password\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tloginFlag = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tif (loginFlag == \"userNameNull\") {\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"参数错误\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function() {\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"null\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"用户名不存在\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function() {\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"idNumNull\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"证件号或密码错误\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function() {\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"accountLock\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码输入错误已超过3次，该账户已被锁定，您可在2小时后再登陆\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if (loginFlag == \"error\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码错误，请重新输入\",\r\n");
      out.write("\t\t\t\tsubContentStr:\"如果连续三次密码输入错误，帐号将被绑定\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"failed\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"帐号未激活，<a href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/register/sendEmailToActive.do?userAccount=\"\r\n");
      out.write("\t\t\t\t\t+ userName + \"'>立即激活</a>\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"lock\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"密码输入错误已超过3次，该账户已被锁定，您可在2小时后再登陆\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"bindFalse\") {\r\n");
      out.write("\t\t\t$(\"#message\").html(\"\");\r\n");
      out.write("\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\tcontentStr: \"您尚未购买过保单或尚未进行过保单绑定，不可通过身份证号登录\",\r\n");
      out.write("\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else if (loginFlag == \"success\") {\r\n");
      out.write("\t\t\twindow.location.href= \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/userPersonalLogin.do\";\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else {//同一个身份证号对应多个密码\r\n");
      out.write("\t\t\tvar accounts = loginFlag.split(\"%%%\");\r\n");
      out.write("\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction openLoginWindow(accounts) {\r\n");
      out.write("\t\tnew Sinosoft.InteractiveDialog({\r\n");
      out.write("\t\t\tlayout : loginByIdNum(accounts),\r\n");
      out.write("\t\t\twidth:410,//自定义面板宽度-根据设计来调整\r\n");
      out.write("\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\tokStr : '确认',\r\n");
      out.write("\t\t\tcancelStr : '取消',\r\n");
      out.write("\t\t\tokFunc:function() {\r\n");
      out.write("\t\t\t\tvar account = $('input[name=\"loginAccount\"]:checked').val();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif (account == null || account == \"\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"请选择您要登录的帐号\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar flag = document.getElementById('remeberMe').checked;\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/remeber.do\",\r\n");
      out.write("\t\t\t\t\tdataType : 'text',\r\n");
      out.write("\t\t\t\t\tdata : {remeber : flag},\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar idLoginFlag= \"\";\r\n");
      out.write("\t\t\t\tvar password = $(\"#password\").val();\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/checkLogin.do\",\r\n");
      out.write("\t\t\t\t\tdataType : 'text',\r\n");
      out.write("\t\t\t\t\tdata : {userName : account,password : password },\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\tidLoginFlag = data;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tif (idLoginFlag == \"userNameNull\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"用户名不能为空\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t} else if (idLoginFlag == \"null\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"用户名不存在，请确认\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t} else if (idLoginFlag == \"lock\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"帐号已锁定\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t} else if (idLoginFlag == \"pwdNull\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"密码不能为空\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t} else if (idLoginFlag == \"error\") {\r\n");
      out.write("\t\t\t\t\tSinosoft.alert({\r\n");
      out.write("\t\t\t\t\t\tcontentStr: \"密码错误，请确认输入密码是否正确\",\r\n");
      out.write("\t\t\t\t\t\twidth:480,\r\n");
      out.write("\t\t\t\t\t\tokStr: '确定',\r\n");
      out.write("\t\t\t\t\t\tcancelBtnShow:false,//是否显示关闭按钮\r\n");
      out.write("\t\t\t\t\t\tokFunc:function(){\r\n");
      out.write("\t\t\t\t\t\t\topenLoginWindow(accounts);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t} else if (idLoginFlag == \"success\") {\r\n");
      out.write("\t\t\t\t\twindow.location.href= \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/login/userPersonalLogin.do\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t}).open();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction onReturn(evt) {\r\n");
      out.write("\t\tvar currKey=evt.keyCode||evt.which||evt.charCode; \r\n");
      out.write("\t\tif (currKey==13) {\r\n");
      out.write("\t\t\tlogin();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction loginByIdNum(accounts){\r\n");
      out.write("\t\tvar subSuccess = '<div class=\"alert_subscribe\"><div class=\"alert_subscribecss\">请选择您要登录的账号:</div><div class=\"main_content\"><div class=\"sub_txt\">';\r\n");
      out.write("\t\tfor (var i = 0; i < accounts.length; i ++) {\r\n");
      out.write("\t\t\tsubSuccess += '<input type=\"radio\" name=\"loginAccount\" value=\"'+accounts[i]+'\">'+accounts[i]+'</input><br>';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tsubSuccess += '</div></div></div>';\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tsubSuccess = $(subSuccess);\r\n");
      out.write("\t\treturn subSuccess;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!--页面主体部分 开始-->\r\n");
      out.write("<div class=\"middle\">\r\n");
      out.write("\t<div class=\"index_head\">\r\n");
      out.write("\t\t<div class=\"index_head_layout\">\r\n");
      out.write("\t\t\t<div class=\"head_action\">\r\n");
      out.write("\t\t\t\t<div class=\"action_layout head1 active\">\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/productsDisplay/onlineShop.do\" target=\"_blank\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"action_layout head2\">\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\" target=\"_blank\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"action_layout head3\">\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"action_layout head4\">\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/web/user/login/index.jsp\" target=\"_blank\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"display_nav\">\r\n");
      out.write("\t\t\t\t<div class=\"active\">&nbsp;</div>\r\n");
      out.write("\t\t\t\t<div>&nbsp;</div>\r\n");
      out.write("\t\t\t\t<div>&nbsp;</div>\r\n");
      out.write("\t\t\t\t<div>&nbsp;</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"h_layout\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"h_layout\">\r\n");
      out.write("\t\t<div class=\"index_area\">\r\n");
      out.write("\t\t\t<div class=\"area_title\">一生相伴</div>\r\n");
      out.write("\t\t\t<div class=\"area_main\">\r\n");
      out.write("\t\t\t\t<div class=\"age_tab_main\">\r\n");
      out.write("\t\t\t\t\t<div class=\"active_bar\"></div>\r\n");
      out.write("\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t<li><div>0-20岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li class=\"active select\"><div>20-30岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li class=\"active\"><div>30-40岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li><div>40-50岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li><div>50-60岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li><div>60-70岁</div>&gt;</li>\r\n");
      out.write("\t\t\t\t\t\t<li><div>70岁以上</div></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"age_tab_container\">\r\n");
      out.write("\t\t\t\t\t<div class=\"content\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content show age20\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"talk\"></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"age_product_list\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"age_product first\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_age20_product1.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"name\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\">懒人理财宝</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span>产品简介：</span>稳健性短期投资首选；\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">全网已有5000个客户做了选择！</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">半年、9个月、1年持有期三档任选；</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">1年期预期年化收益率5.3%；</p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"age_product last\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_age20_product2.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"name\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017\" target=\"_blank\">出行无忧计划</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span>产品简介：</span>商务出行、蜜月度假、回家探亲必选；飞机、火车（动车、高铁、地铁）、客车、轮船、自驾车意外险；按需选择，更低价；\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content age30\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"talk\"></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"age_product_list\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"age_product first\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_age30_product1.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"name\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\" target=\"_blank\">百万身驾</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span>产品简介：</span>一款保额高达200万，最适合有车一族的人身保险。保障期限长达30年，满期返还保费的120%。每天只需2元，超低保费。\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"age_product last\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\"><img\r\n");
      out.write("\t\t\t\t\t\t\t\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_age30_product2.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"name\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\">宝利来理财险</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span>产品简介：</span>半年、9个月、1年持有期三档任选；\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">1年期预期年化收益率5.3%</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">9个月预期年化收益率5.12%</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"instro\">半年期预期年化收益率5.02%</p>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content\">4无内容</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content\">5无内容</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content\">6无内容</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"content\">7无内容</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"index_area\">\r\n");
      out.write("\t\t\t<div class=\"area_title\">适合您的产品</div>\r\n");
      out.write("\t\t\t<div class=\"area_main suit_product\">\r\n");
      out.write("\t\t\t\t<div class=\"product_list\">\r\n");
      out.write("\t\t\t\t\t<div class=\"product\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_suit1.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\" target=\"_blank\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>只买了车险？你out了？</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>200万私家车车主人身保险计划赶快加入你的平安才是对家人最好的关爱！</p>\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"product\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_suit2.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>什么都在涨，只有工资不涨，怎么办？信泰保险理财计划，带你跑赢通胀！现在、即刻、马上加入吧。</p>\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"product\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_suit3.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017\" target=\"_blank\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>安全到达目的地，拒绝裸飞裸奔！</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>飞机？高铁？动车？</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>乘坐啥交通工具就买啥保险！按需求选择，价格更低廉！</p>\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"product\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/index/index_suit4.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\" target=\"_blank\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>喊着我的小闺蜜一起存钱了！</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>存银行？收益太低！</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>买基金？风险太高！</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>信泰“懒人理财宝”，满足您的需求！</p>\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/web/quicktunnel/quicktunnel.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!--页面主体部分 结束-->");
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

  private boolean _jspx_meth_c_005fchoose_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent(null);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /middle.jsp(290,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty geUserPersonal}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "web/common/indexUserInfoFrame.jsp", out, false);
        out.write("\r\n");
        out.write("\t\t\t\t");
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

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /middle.jsp(293,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty geUserPersonal}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "web/common/indexLoginFrame.jsp", out, false);
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }
}
