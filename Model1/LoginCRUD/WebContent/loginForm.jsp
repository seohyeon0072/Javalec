<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// url ���� �޽��� ����(�����̷�Ʈ�δ� ���� ��� �������� ����)
	String msg = (String)session.getAttribute("msg");

	if(msg == null){
		msg = "�������";
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// ȸ������ â���� �̵�
	function goJoin(){
		location.href="join.jsp";
	}
	
	// �α��� ó�� �������� �̵�
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
				<input type="button" value="�α���" onclick="checkLogin()" />
				<input type="button" value="ȸ������" onclick="goJoin()" />
			</div>
		</div>
		<div class="msgBox">
			<%= msg %>
		</div>
	</form>
</body>
</html>
<!-- �޽����� �������������� ���Ǿ��ϱ⶧���� �ٸ��������� ���� ������ �Ǿ���Ѵ�. -->
<%@ include file="removeMessage.jspf" %>