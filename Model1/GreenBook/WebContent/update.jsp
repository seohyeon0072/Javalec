<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.ac.green.*" %>
<%
	// ��ȣ, ����, ����, ��й�ȣ
	int num = Integer.parseInt(request.getParameter("num"));
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String pw = request.getParameter("pw");
	
	Vector<Doc> list = (Vector<Doc>)application.getAttribute("docList");
	
	Doc original = list.get(list.indexOf(new Doc(num)));
	
	
	String msg = "������ �Ϸ�Ǿ����ϴ�.";
	if(pw.equals(original.getPw())){
		original.setTitle(title);
		original.setContents(contents);
	}else{
		msg = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	}
	
	
	session.setAttribute("msg", msg);
	response.sendRedirect("template.jsp");
%>