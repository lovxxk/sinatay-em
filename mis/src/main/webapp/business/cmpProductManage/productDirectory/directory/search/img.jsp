<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.InputStream" %>
<%
	response.setHeader("Cache-Control", "No-Cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0L);
	
	String img = request.getParameter("img");
	BufferedOutputStream bos = null;
	System.out.println("come in jsp");
	if(img != null){
		try{
			out.clear(); 
			out = pageContext.pushBody();
			bos = new BufferedOutputStream(response.getOutputStream()); 
			byte b[] = img.getBytes();
			for(int i=0;i<b.length;i++){
				bos.write(b[i]);
			}
			bos.flush();
			bos.close();
			bos = null;
		}
		catch(Exception e){
			System.out.println("download.jsp error:"+e.getMessage());
		}
		finally {
			if (bos != null){
				bos.close();
				bos = null;
			}
		}
	}
%>
