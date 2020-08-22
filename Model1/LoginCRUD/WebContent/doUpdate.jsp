<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ include file="checkSession.jspf" %>    

<%
	request.setCharacterEncoding("euc_kr");
	//세션에서 비밀번호와 닉네임을 가져와서 setter로 수정
	member.setU_pw(request.getParameter("u_pw"));
	member.setU_nick(request.getParameter("u_nick"));
	
	// updateMember() -> application <-> session 일치하는게 있다면(true) 정상적으로 수정 
	MemberManager.updateMember(application, member);
	session.setAttribute("msg", "회원정보가 수정되었습니다.");
	response.sendRedirect("loginSuccess.jsp");
%>