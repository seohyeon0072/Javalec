<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="kr.ac.green.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	Vector<Doc> list = (Vector<Doc>)application.getAttribute("docList");
	
	Doc doc = list.get(list.indexOf(new Doc(num))); 
	
%>


<fieldset>
	<legend>상세화면</legend>
	<!-- 사용자 입력값 -->
	<form id="modifyForm" method="post">
		글번호 : <input type="text" name="num" placeholder="번호" value="<%= doc.getNum()%>" readonly />
		<br/><br/> 
		제목 : <input type="text" name="title" placeholder="제목" value="<%= doc.getTitle() %>" />
		<br/><br/> 
		작성자 : <input type="text" name="writer" placeholder="작성자" value="<%=doc.getWriter() %>" readonly />
		<br/><br/>
		내용 : <br>
		<textarea name="contents" rows="5" ><%= doc.getContents() %></textarea>
		<br><br/>
		날짜 : <input type="text" name="date" placeholder="날짜" value="<%= doc.getDate() %>" />
		<br/><br/>
		비밀번호 : <input type="password" name="pw" placeholder="비밀번호" />
		<br/><br/>
		<input type="button" value="목록가기" onclick="goList()" />
		<input type="button" value="수정" onclick="todo('update')"/>
		<input type="button" value="삭제" onclick="todo('delete')"/>
	</form> 
</fieldset> 
<script>
	function goList(){ 
		location.href ="template.jsp";
	}
	
	function todo(){
		var what = arguments[0];
		
		var modifyForm = document.getElementById("modifyForm");
		
		modifyForm.action = what + ".jsp";
		modifyForm.submit();
	}
</script>

 
