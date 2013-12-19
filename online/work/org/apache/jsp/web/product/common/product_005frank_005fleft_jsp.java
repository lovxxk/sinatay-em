package org.apache.jsp.web.product.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class product_005frank_005fleft_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/global/ui/taglibs_data.jsp");
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
      out.write("<div class=\"product_rank dynamic_frame\">\r\n");
      out.write("\t<p class=\"dynamic_title\">产品销售排行</p>\r\n");
      out.write("\t<div class=\"rank_list\">\r\n");
      out.write("\t\t<ul class=\"dynamic_list\" id=\"life_topProduct\">\r\n");
      out.write("<!-- \t\t\t<li class=\"last\"> -->\r\n");
      out.write("<!-- \t\t\t\t<div class=\"rank\"> -->\r\n");
      out.write("<!-- \t\t\t\t\t<div class=\"rank_img home\"><a href=\"#\"></a></div> -->\r\n");
      out.write("<!-- \t\t\t\t\t<p class=\"rank_title\"><a href=\"#\">产品名称产品名称产 品名称产品的名</a></p> -->\r\n");
      out.write("<!-- \t\t\t\t</div> -->\r\n");
      out.write("<!-- \t\t\t</li> -->\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype: \"POST\",\r\n");
      out.write("\t\turl: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/productsDisplay/lift_HotProduct.do\",\r\n");
      out.write("\t\tdataType: 'json',\r\n");
      out.write("\t\tsuccess: function(data){\r\n");
      out.write("\t\t\tif(data != ''){\r\n");
      out.write("\t\t\t\t$.each(data, function(index,item){\r\n");
      out.write("// \t\t\t\t\tconsole.log(item.EID+\", \"+item.COREPRODUCTCODE+\", \"+item.PRODUCTNAME+\", \"+item.NUB);\r\n");
      out.write("\t\t\t\t\tvar url = contextRootPath+\"/sale/toQuote.do?eid=\"+item.EID;\r\n");
      out.write("\t\t\t\t\tvar html = \"<li><div class=\\\"rank\\\"><div class=\\\"rank_img\\\"><a href=\\\"\"+url+\"\\\"><img src=\\\"\"+contextRootPath+\"/upload/images/\"+item.COREPRODUCTCODE+\"/\"+item.COREPRODUCTCODE+\"_detail_main.jpg\\\"></a></div><p class=\\\"rank_title\\\"><a href=\\\"\"+url+\"\\\">\"+item.PRODUCTNAME+\"</a></p></div></li>\";\r\n");
      out.write("\t\t\t\t\t$(\".dynamic_frame #life_topProduct\").append(html);\r\n");
      out.write("\t\t\t\t\t//插码\r\n");
      out.write("\t\t\t\t\t$.each($(\"#life_topProduct a\"), function(i,n){\r\n");
      out.write("\t\t\t\t\t\t$(n).click(function(){\r\n");
      out.write("\t\t\t\t\t\t\t_ga.push(['_trackEvent', '产品详情', '产品销售排行栏: '+$(this).text()]).send();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("// \t\t\t\t_result.html(\"未找到对应职业信息！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\terror:function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t$(\".dynamic_frame #life_topProduct\").html(\"操作异常，请稍候再试！\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
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
