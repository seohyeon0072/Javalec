<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="kr.ac.green.Article" %>
    <%@ page import="java.util.Vector" %>
<%
	request.setCharacterEncoding("euc_kr");

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String contents = request.getParameter("contents");
	String pw = request.getParameter("pw");
	
	Article article = new Article(title, writer, contents, pw);
	
	Vector<Article> list = (Vector<Article>)application.getAttribute("list");
	if(list == null){
		list = new Vector<Article>();
		application.setAttribute("list", list);
	}
	
	list.add(article);
	
	response.sendRedirect("list.jsp");

%>