<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
    <%@ page import="kr.ac.green.Article" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	String pw = request.getParameter("pw");

	Vector<Article> list = (Vector<Article>) application.getAttribute("list");
	
	Article temp = new Article(num);
	
	Article original = list.get(list.indexOf(temp));

	String msg = "삭제가 완료되었습니다.";
	if(pw.equals(original.getPw())){
		list.remove(temp);
	}else{
		msg ="비밀번호가 일치하지 않습니다.";
	}
	// 세션으로 보냄
	session.setAttribute("msg", msg);
	response.sendRedirect("list.jsp");
%>		
