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

	String msg = "������ �Ϸ�Ǿ����ϴ�.";
	if(pw.equals(original.getPw())){
		list.remove(temp);
	}else{
		msg ="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	}
	// �������� ����
	session.setAttribute("msg", msg);
	response.sendRedirect("list.jsp");
%>		
