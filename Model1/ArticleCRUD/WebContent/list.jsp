<!--  
JSP Model 1 방식

-data => application 


-->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.Vector" %>
    <%@ page import="kr.ac.green.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list.jsp</title>
</head>
<body>
<%
	// 기본 메시지
	String msg = "어서오세요";
	// delete에서 세션으로 받아옴
	Object o = session.getAttribute("msg");
	// 있으면 실패 메시지
	if(o != null){
		msg = o.toString();
	}
	// 기본 세션으로 받아온 메시지 제거
	session.removeAttribute("msg");
%>
<div>message : <%= msg %></div>
<a href="write.jsp">글 등록</a>
<hr>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>작성일</th>
	</tr>
	<%
		Vector<Article> list = (Vector<Article>)application.getAttribute("list");
	
		if(list == null || list.size() == 0){
			
	%>
	<tr>
		<th colspan="4">등록된 글이 없습니다.</th>
	</tr>
	<%
		} else {
			for(int i = list.size()-1; i>=0; i--){
				Article temp = list.get(i);
	%>
	<!-- onclick 이줄을 선택하면 -->
	<tr onclick="select(<%=temp.getNum()%>)">
		<td><%= temp.getNum() %></td>
		<td><%= temp.getTitle() %></td>
		<td><%= temp.getWriter() %></td>
		<td><%= temp.getDateString() %></td>
	</tr>
	<%
			} 
		}
	%>
</table>
<form id="hiddenForm" name="hiddenForm" action="readArticle.jsp">
	<input type="hidden" name="num" />
</form>
<script>

// 	function select(num){
// 		var myForm = document.getElementById("hiddenForm");
// 		myForm.num.value = num;
// 		myForm.submit();
// 	}

	function select(num){
// 		var myForm = document.forms["hiddenForm"]
		var myForm = document.hiddenForm;
		myForm.num.value = num;
		myForm.submit();
	}	

// 	alert("1" == 1);
// 	alert("2" === 2);

	// 매개변수가 없다고 해서 없는것이 아님(자바스크립트는 선택사항)
// 	function select(num){
// 		location.href="readArticle.jsp?num=" + num;
// 	}	
</script>
</body>
</html>