<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.Vector" %>
    <%@ page import="kr.ac.green.Article" %>
    <%
    	int num = Integer.parseInt(request.getParameter("num"));
    
    	Vector<Article> list = (Vector<Article>)application.getAttribute("list");
    	Article article = list.get(list.indexOf(new Article(num)));
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>readArticle.jsp</title>
</head>
<body>
		<form id="modifyForm" method="post">
		번호 : <input type="text" name="num" value="<%= article.getNum() %>" readonly />
		<br/>
		제목 : <input type="text" name="title" value="<%= article.getTitle() %>"/>
		<br/>
		글쓴이 : <input type="text" name="writer" value="<%= article.getWriter() %>"  />
		<br/>
		작성일 : <input type="text" name="date" value="<%= article.getDateString() %>" /> 
		<br/>
		비밀번호 : <input type="password" name="pw"  />
		<br/>
		내용
		<br/>
		<textarea name="contents" rows="5" cols="50" ><%= article.getContents() %></textarea>
		<br/>
		<input type="button" value="목록보기" onclick="goList()"/>&nbsp;
		<input type="button" value="수정" onclick="todo('update')"/>&nbsp;
		<input type="button" value="삭제" onclick="todo('delete')"/>&nbsp;
		<input type="reset" />
	</form>
	<script>
		function goList(){
			location.href = "list.jsp";
		}
		
		function todo(){
			var what = arguments[0];
			
			// modifyForm을 가진 id값을 가져와서 action속성을 >>
			var modifyForm = document.getElementById("modifyForm");
			modifyForm.action = what + ".jsp";
			
			modifyForm.submit();
		}
	</script>
</body>
</html>