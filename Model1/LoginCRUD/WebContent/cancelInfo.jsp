<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	session.setAttribute("msg", "정보보기가 취소되었습니다.");
%>
<jsp:forward page="loginSuccess.jsp" />