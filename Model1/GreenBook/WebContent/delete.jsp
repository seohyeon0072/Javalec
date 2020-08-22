<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="kr.ac.green.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	String pw = request.getParameter("pw");
	 	
	Vector<Doc> list = (Vector<Doc>)application.getAttribute("docList");
	
	Doc temp = new Doc(num);
	
	Doc original = list.get(list.indexOf(temp));
	
	String msg = "삭제가 완료되었습니다.";
	if(pw.equals(original.getPw())){
		list.remove(temp);
	}else{
		msg ="비밀번호가 일치하지 않습니다.";
	}
	
	session.setAttribute("msg", msg);
	response.sendRedirect("template.jsp");
%>