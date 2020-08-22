<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.ac.green.*" %>
<%
	// 번호, 제목, 내용, 비밀번호
	int num = Integer.parseInt(request.getParameter("num"));
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String pw = request.getParameter("pw");
	
	Vector<Doc> list = (Vector<Doc>)application.getAttribute("docList");
	
	Doc original = list.get(list.indexOf(new Doc(num)));
	
	
	String msg = "수정이 완료되었습니다.";
	if(pw.equals(original.getPw())){
		original.setTitle(title);
		original.setContents(contents);
	}else{
		msg = "비밀번호가 일치하지 않습니다.";
	}
	
	
	session.setAttribute("msg", msg);
	response.sendRedirect("template.jsp");
%>