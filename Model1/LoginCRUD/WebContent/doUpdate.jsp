<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ include file="checkSession.jspf" %>    

<%
	request.setCharacterEncoding("euc_kr");
	//���ǿ��� ��й�ȣ�� �г����� �����ͼ� setter�� ����
	member.setU_pw(request.getParameter("u_pw"));
	member.setU_nick(request.getParameter("u_nick"));
	
	// updateMember() -> application <-> session ��ġ�ϴ°� �ִٸ�(true) ���������� ���� 
	MemberManager.updateMember(application, member);
	session.setAttribute("msg", "ȸ�������� �����Ǿ����ϴ�.");
	response.sendRedirect("loginSuccess.jsp");
%>