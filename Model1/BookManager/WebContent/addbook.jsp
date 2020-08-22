<!-- model 1 방식  data->file 
	1.먼저 xml  파일 경로 추가 
	2.webContent date 폴더 생성+.dat파일 생성
	
 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="kr.ac.green.Book" %>   
<%@ page import="kr.ac.green.BookManager" %>   

<%
	request.setCharacterEncoding("euc_kr");

	String title = request.getParameter("title");
	String author = request.getParameter("author");
	String date = request.getParameter("date");
	int price = Integer.parseInt(request.getParameter("price"));
	
	Book book = new Book(title, author, date, price);
	
	String path =
			application.getRealPath(application.getInitParameter("bookPath"));
	
	Vector<Book> list = BookManager.getList(path);
	
	list.add(book);
	
	BookManager.addBook(path, list);
	
	response.sendRedirect("books.jsp");
%>
