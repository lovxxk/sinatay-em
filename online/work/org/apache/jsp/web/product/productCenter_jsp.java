package org.apache.jsp.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class productCenter_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div class=\"middle\">\r\n");
      out.write("\t<div class=\"h_layout\">\r\n");
      out.write("\t\t<div class=\"nav_index\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/index.jsp \">首页</a><span> &gt;</span></li>\r\n");
      out.write("\t\t\t\t<li class=\"at\">产品选购</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"product_main\">\r\n");
      out.write("\t\t\t<div class=\"left_dynamic\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/web/product/common/product_map.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/web/product/common/product_commend.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/web/product/common/product_rank_left.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"display\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"right_product\">\r\n");
      out.write("\t\t\t\t<div class=\"center_top\">\r\n");
      out.write("\t\t\t\t\t<div class=\"product_display\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"active\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/productsDisplay/onlineShop.do\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_show1.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_show2.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_show3.jpg\"></a>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"display_nav\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"active\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"product_quote\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"quote_tab\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"tab_item select\">交通工具</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"quote_main\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"quote_body traffic\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"input_field selector\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"input_label\">交通类型：</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul class=\"input_select\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"select\" tag=\"type\"><div>飞机</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"type\"><div>火车</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"type\"><div>客运</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"type\"><div>轮船</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"type\"><div>自驾车</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"input_field bottom selector\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"input_label\">保险期间：</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul class=\"input_select\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"select\" tag=\"day\"><div>1个月</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"day\"><div>6个月</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li tag=\"day\"><div>1年</div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"action\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"type\" name=\"type\" value=\"0\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"day\" name=\"day\" value=\"0\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<button class=\"quote_btn click_btn\">立即报价</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"sub_cate_name\">热销产品</div>\r\n");
      out.write("\t\t\t\t<div class=\"product_area product_list\">\r\n");
      out.write("\t\t\t\t\t<div class=\"product_list_main page1\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=0&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon1.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title accident\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=0&day=0\">百万身驾—私家车车主专享保险计划（标准版）</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-50岁</span><span>保险期间：30年</span><span>交费年期：5年、10年</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">有车一族必备保险！100000名私家车主的共同选择，截止目前已累计提供1000亿的保障。</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>私家车意外身故：<span>100万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>交通工具意外身故：<span>57.5万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>意外身故/伤残：<span>7.5万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>一般身故保障</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>满期生存保障金</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">1127.5</span><span class=\"p2\">元/年起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=0&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=1&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon2.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title accident\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=1&day=0\">百万身驾—私家车车主专享保险计划（豪华版）</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-50岁</span><span>保险期间：30年</span><span>交费年期：5年、10年</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">有车一族必备保险！私家车意外最高保障高达200万；100000名私家车主的共同选择，截止目前已累计提供1000亿的保障。</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>私家车意外身故：<span>200万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>交通工具意外身故：<span>115万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>一般身故保障</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>意外身故/伤残：<span>15万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>满期生存保障金</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">2255</span><span class=\"p2\">元/年起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152808046&type=1&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034&type=0&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon3.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title finance\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034&type=0&day=0\">懒人理财宝—<span>稳健性短期投资首选！</span></a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-64岁</span><span>购买额度：500元起</span><span>单笔最高购买金额：19万</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">比存银行收益高，比购买基金风险小，最低购买额度500元起；无初始扣费，保单管理费；线上领取更便捷！三个方案，供您挑选！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>持有期：<span>6个月</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>持有期：<span>9个月</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>持有期：<span>1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>预期收益：<span>5.02%</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>预期收益：<span>5.12%</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>预期收益：<span>5.3%</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">500</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152647034&type=0&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=0&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon4.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title transport\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=0&day=0\">航空意外伤害保险</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">你还在买着单次20元60万航空意外险吗？信泰航空意外险7天100万只需要7元钱，有比较才有差异，赶紧下手吧！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"c1\">保险期间：<span> 1个月 ／ 6个月 ／ 1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>保额：<span>50万－100万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">5</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=0&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=1&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon5.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title transport\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=1&day=0\">轨道意外伤害保险</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">动车、火车、高铁、地铁都在保障范围，出差、旅行、度假、探亲、上下班，多一份保障，多一份安心！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"c1\">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>保额：<span>10万－50万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">1.4</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=1&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"product_list_main page2\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=4&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon6.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title transport\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=4&day=0\">乘用车意外伤害保险</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">旅游、度假、自驾游、探亲、上下班，按需选择，价格低廉，快乐出行，安全第一！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"c1\">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>保额：<span>10万－50万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">12</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=4&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=2&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon7.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title transport\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=2&day=0\">客运汽车意外伤害保险</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">你还在客运汽车站买保险吗？一次2元，保额2万？ 双城生活的夫妻、情侣和常回家探望父母的孝顺儿女们，赶紧重新做选择吧！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"c1\">保险期间：<span> 1个月 ／ 6个月 ／ 1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>保额：<span>10万－50万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">5</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=2&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"product_item\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=3&day=0\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/image/product/product_center_icon8.jpg\"/></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_title transport\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=3&day=0\">轮船意外伤害保险</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"base_info\"><span>年龄：18-80岁</span><span>生效日期：可自主选择</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"product_instro\">你是否正在计划与你的那个ta来一次泰坦尼克的浪漫之旅，面向大海，沐浴海风，感受滚滚红尘间的万种风情！海上风险，信泰来担！</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"more_info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_clause\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li class=\"c1\">保险期间：<span>1个月 ／ 6个月 ／ 1年</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<li>保额：<span>10万－50万</span></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"product_price\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"price\"><span class=\"p1\">3</span><span class=\"p2\">元起</span></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"buy click_btn\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sale/toQuote.do?eid=G120130902152737017&type=3&day=0\">了解详情</a></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"page_index\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"page prev_page\" title=\"1\"><a>上一页</a></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"page page_num now\" title=\"1\"><a>1</a></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"page page_num\" title=\"2\"><a>2</a></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"page next_page\" title=\"2\"><a>下一页</a></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
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
