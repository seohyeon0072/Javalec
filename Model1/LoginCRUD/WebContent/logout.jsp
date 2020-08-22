<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// 로그아웃시 세션의 모든 데이터 삭제
	session.invalidate();
	
	// 위에서 기존의 세션은 삭제했기 때문에 새로운 세션을 생성(로그인 폼에 보낼 메시지 때문에 미리 받아옴)
	session = request.getSession();
	session.setAttribute("msg", "로그아웃 되었습니다.");
%>
<jsp:forward page="loginForm.jsp" />