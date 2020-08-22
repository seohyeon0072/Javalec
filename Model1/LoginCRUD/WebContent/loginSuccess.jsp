<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- 로그인을 한 사람만 볼 수 있는 페이지라서 설정  -->
<%@ include file="checkSession.jspf" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// 로그아웃 페이지로 이동
	function goLogout(){
		location.href="logout.jsp";
	}
	
	// 정보보기 페이지로 이동
	function goInfo(){
		var obj = document.successForm;
		obj.action = "goInfo.jsp";
		obj.method = "post";
		obj.submit();
	}
</script>
</head>
<body>
	<p class="titleStr">
		Success!!
	</p>
	<form name="successForm">
		<div class="centerBox">
			<div class="btns">
				<input type="button" value="정보보기" onclick="goInfo()" />
				<input type="button" value="로그아웃" onclick="goLogout()" />
			</div>
		</div>
		<div class="msgBox">
		<%= session.getAttribute("msg") %>
		 </div>
	</form>
</body>
</html>
<%@ include file="removeMessage.jspf" %>