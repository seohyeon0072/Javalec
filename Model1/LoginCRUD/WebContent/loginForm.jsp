<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// url 별로 메시지 전달(리다이렉트로는 쓸수 없어서 세션으로 전달)
	String msg = (String)session.getAttribute("msg");

	if(msg == null){
		msg = "어서오세요";
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// 회원가입 창으로 이동
	function goJoin(){
		location.href="join.jsp";
	}
	
	// 로그인 처리 페이지로 이동
	function checkLogin(){
		var obj = document.loginForm;
		obj.action = "checkLogin.jsp";
		obj.method = "post";
		obj.submit();
	}
</script>
</head>
<body>
	<p class="titleStr">
		Login
	</p>
	<form name="loginForm">
		<div class="centerBox">
			<label for="u_id">ID :</label><input type="text" name="u_id" /><br/>
			<label for="u_pw">PW :</label><input type="password" name="u_pw" /><br/>
			<div class="btns">
				<input type="button" value="로그인" onclick="checkLogin()" />
				<input type="button" value="회원가입" onclick="goJoin()" />
			</div>
		</div>
		<div class="msgBox">
			<%= msg %>
		</div>
	</form>
</body>
</html>
<!-- 메시지는 현재페이지에서 사용되야하기때문에 다른페이지를 가면 삭제가 되어야한다. -->
<%@ include file="removeMessage.jspf" %>