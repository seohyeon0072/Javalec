
  
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

	String msg = "������ �Ϸ�Ǿ����ϴ�.";
	if (pw.equals(original.getPw())) {
		original.setTitle(title);
		original.setContents(contents);
	} else {
		msg = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	}

	session.setAttribute("msg", msg);
	response.sendRedirect("list.jsp");
%>
