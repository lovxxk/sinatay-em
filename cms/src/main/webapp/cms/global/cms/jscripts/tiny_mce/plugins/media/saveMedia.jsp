<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import = "com.sinosoft.apache.*"%>
<%@ page import = "com.CMS.*"%>
<%
Upload upload =new Upload();
Enclosure enclosure =new Enclosure();
try{upload.uploadfile(pageContext);}catch(Exception e) {}
String docID=upload.get("docID").toString();
String fileName=upload.getfileName();
enclosure.set("AttName",docID+"_"+fileName);
enclosure.set("AttDocId",docID);
enclosure.set("AttDesc",fileName);
enclosure.set("AttPath","/cms/global/CMS/examples/media/"+docID+"_"+fileName);
enclosure.set("AttType","1");
enclosure.create();
upload.setfileName(docID+"_"+fileName);
upload.saveAs("/cms/global/CMS/examples/media/");
%>
<script type="text/javascript">
window.opener.document.getElementById('src').value="media/"+"<%=docID+"_"+fileName%>";
//window.opener.document.getElementById('alt').value="<%=fileName%>";
//window.opener.document.getElementById('title').value="<%=fileName%>";
window.opener.document.getElementById('src').onchange();
window.close();
</script>

