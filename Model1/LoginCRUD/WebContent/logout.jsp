<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// �α׾ƿ��� ������ ��� ������ ����
	session.invalidate();
	
	// ������ ������ ������ �����߱� ������ ���ο� ������ ����(�α��� ���� ���� �޽��� ������ �̸� �޾ƿ�)
	session = request.getSession();
	session.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
%>
<jsp:forward page="loginForm.jsp" />