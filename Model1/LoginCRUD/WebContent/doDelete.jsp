<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ include file="checkSession.jspf" %>    
<%	
	// deleteMember() -> application <-> session(u_id)��ġ�ϴ� ���� �ִٸ� ���������� ����
	if(MemberManager.deleteMember(application, member.getU_id())){
		session.invalidate();
		session = request.getSession();
		session.setAttribute("msg", "ȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
		response.sendRedirect("loginForm.jsp");
	}else{
		// ������ ���� �˸� �� �� ��� info�� �ٽ� �̵�
		session.setAttribute("msg", "ȸ��Ż���� ������ �߻��߽��ϴ�.");
%>
	<jsp:forward page="info.jsp" />
<%
	}
%>