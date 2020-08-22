
  
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="kr.ac.green.Article"%>
<%
	request.setCharacterEncoding("euc_kr");

	int num = Integer.parseInt(request.getParameter("num"));
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String pw = request.getParameter("pw");

	Vector<Article> list = (Vector<Article>) application.getAttribute("list");

	Article original = list.get(list.indexOf(new Article(num)));

	String msg = "수정이 완료되었습니다.";
	if (pw.equals(original.getPw())) {
		original.setTitle(title);
		original.setContents(contents);
	} else {
		msg = "비밀번호가 일치하지 않습니다.";
	}

	session.setAttribute("msg", msg);
	response.sendRedirect("list.jsp");
%>
