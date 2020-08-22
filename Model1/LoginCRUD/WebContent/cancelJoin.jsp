<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	session.setAttribute("msg", "가입이 취소되었습니다.");
%>
<jsp:forward page="loginForm.jsp" />