<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	session.setAttribute("msg", "비밀번호와 닉네임이 변경가능합니다.");
%>
<jsp:forward page="info.jsp" />