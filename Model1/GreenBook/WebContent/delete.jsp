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
	
	String msg = "������ �Ϸ�Ǿ����ϴ�.";
	if(pw.equals(original.getPw())){
		list.remove(temp);
	}else{
		msg ="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	}
	
	session.setAttribute("msg", msg);
	response.sendRedirect("template.jsp");
%>