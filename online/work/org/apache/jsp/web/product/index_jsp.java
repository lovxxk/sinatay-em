package org.apache.jsp.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/global/ui/taglibs_data.jsp");
    _jspx_dependants.add("/global/ui/taglibs_head.jsp");
    _jspx_dependants.add("/global/google/inner.jsp");
    _jspx_dependants.add("/global/baidu/inner.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
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
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"/>\r\n");
      out.write("\t\t<title>保险产品选购—人寿保险，投资理财，车主保险，意外险—信泰保险网上商城</title>\r\n");
      out.write("\t\t<meta name=\"description\" content=\"根据您的需求自主选择保险产品，我们为您提供投资理财、车主保险、意外险等产品的选购。\" />\r\n");
      out.write("\t\t<meta name=\"keywords\" content=\"人寿保险，投资理财，车主保险，意外险、交通工具意外险，”网上保险“\"/>\r\n");
      out.write("\t\t");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/global.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/frame.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery-1.7.1.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery-gbk-ajax.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/common/main.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/util.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/frame.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/json/insureflow.formwork.json.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\tvar contextRootPath = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(typeof JSON == 'undefined'){\r\n");
      out.write("\t\t\t\tSinosoft.loader.script(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/json/json2.js\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction banBackSpace(e){      \r\n");
      out.write("\t\t\t    var ev = e || window.event;\r\n");
      out.write("\t\t\t    var obj = ev.target || ev.srcElement;\r\n");
      out.write("\t\t\t       \r\n");
      out.write("\t\t\t    var t = obj.type || obj.getAttribute('type');\r\n");
      out.write("\t\t\t       \r\n");
      out.write("\t\t\t    var vReadOnly = obj.getAttribute('readonly');   \r\n");
      out.write("\t\t\t    var vEnabled = obj.getAttribute('enabled');   \r\n");
      out.write("\t\t\t    vReadOnly = (vReadOnly == null) ? false : vReadOnly;   \r\n");
      out.write("\t\t\t    vEnabled = (vEnabled == null) ? true : vEnabled;\r\n");
      out.write("\t\t\t       \r\n");
      out.write("\t\t\t    var flag1=(ev.keyCode == 8 && (t==\"password\" || t==\"text\" || t==\"textarea\")    \r\n");
      out.write("\t\t\t                && (vReadOnly==true || vEnabled!=true))?true:false;   \r\n");
      out.write("\t\t\t      \r\n");
      out.write("\t\t\t    var flag2=(ev.keyCode == 8 && t != \"password\" && t != \"text\" && t != \"textarea\")   \r\n");
      out.write("\t\t\t                ?true:false;           \r\n");
      out.write("\t\t\t       \r\n");
      out.write("\t\t\t    if(flag2){   \r\n");
      out.write("\t\t\t        return false;   \r\n");
      out.write("\t\t\t    }   \r\n");
      out.write("\t\t\t    if(flag1){      \r\n");
      out.write("\t\t\t        return false;      \r\n");
      out.write("\t\t\t    }      \r\n");
      out.write("\t\t\t}   \r\n");
      out.write("\t\t\t  \r\n");
      out.write("\t\t\tdocument.onkeypress=banBackSpace;   \r\n");
      out.write("\t\t\tdocument.onkeydown=banBackSpace;   \r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/google/ga.js\"></script> \r\n");
      out.write("<script>\r\n");
      out.write("var _ga = new _GA('UA-43863244-1');\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t\t");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/baidu/hm.js\"></script> \r\n");
      out.write("<script>\r\n");
      out.write("var _hm = new _HM('f2648de1cf8c8f689b9657f4672a5e52');\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/jSelect.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery/jquery.sinosoft.select.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/product/product_frame.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/product/product_center.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/product/product_center.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "productCenter.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<!-- 插码 -->\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t_ga.push(['_trackPageview','/web/product']).send();\r\n");
      out.write("\t\t\t$(\".login_info .login\").click(function(){\r\n");
      out.write("\t\t\t\t_ga.push(['_trackEvent', '产品中心', '登录']).send();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".quick_menu a\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', $(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".logo\").click(function(){\r\n");
      out.write("\t\t\t\t_ga.push(['_trackEvent', '产品中心', '信泰保险logo']).send();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".nav_menu a\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '导航菜单栏: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".nav_index\").click(function(){\r\n");
      out.write("\t\t\t\t_ga.push(['_trackEvent', '产品中心', '导航栏']).send();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".product_map a\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '产品类别栏: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".product_container a\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '产品推荐栏: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t/*\r\n");
      out.write("\t\t\t$.each($(\".discount_recommend a\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '优惠产品推荐栏: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".combo_recommend .combo_buy\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '套餐推荐栏立即购买']).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t*/\r\n");
      out.write("\t\t\t$.each($(\".product_list_main .buy\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '热销排行栏立即购买']).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$.each($(\".quote_tab .tab_item\"), function(i,n){\r\n");
      out.write("\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t_ga.push(['_trackEvent', '产品中心', '试算选项卡: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});  \r\n");
      out.write("\t\t\t$(\".action button\").click(function(){\r\n");
      out.write("\t\t\t\t_ga.push(['_trackEvent', '产品中心', '试算立即购买']).send();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".product_display\").click(function(){\r\n");
      out.write("\t\t\t\t_ga.push(['_trackEvent', '产品中心', '产品广告位']).send();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t_hm.push(['_trackPageview','/web/product']).send();\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
      out.write("<script>\r\n");
      out.write("$('.click_btn').click(function(){\r\n");
      out.write("\twindow.location.href = contextRootPath+\"/sale/toQuote.do?eid=G120130902152737017&type=\"+$(\"#type\").val()+\"&day=\"+$(\"#day\").val();\r\n");
      out.write("});\r\n");
      out.write("</script>");
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
}
