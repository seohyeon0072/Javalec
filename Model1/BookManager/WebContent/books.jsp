<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="kr.ac.green.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="addbook.jsp" method="post">
		���� : <input type="text" name="title" /> <br /> ���� : <input
			type="text" name="author" /> <br /> ������ : <input type="text"
			name="date" /> <br /> ���� : <input type="text" name="price" /> <br />
		<input type="submit" /> <input type="reset" />
	</form>
	<hr>
	<%
		String path = application.getRealPath(
				application.getInitParameter("bookPath"));
		Vector<Book> list = BookManager.getList(path);
	%>
	<table>
		<tr>
			<th>����</th>
			<th>����</th>
			<th>����</th>
			<th>������</th>
		</tr>
		<%
			int size = list.size();
			if(size == 0){
		%>
		<tr>
			<td colspan="4">no books</td>
		</tr>
		<%
			}else{
				for(int i = size -1; i >=0; i--){
					Book book = list.get(i);
		%>
		<tr>
			<td><%= book.getTitle() %>
			<td><%= book.getAuthor() %>
			<td><%= book.getPrice() %>
			<td><%= book.getDate() %>
		</tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>