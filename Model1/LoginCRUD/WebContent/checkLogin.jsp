<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%
	String u_id = request.getParameter("u_id");
	String u_pw = request.getParameter("u_pw");
	// findMember() -> id�� ��ġ�ϴ� ��ü�� ã�´�.
	Member member = MemberManager.findMember(application, u_id);
	String msg;
	String nextPage = "loginForm.jsp";
	// ���̵� application������ ���� ���� ������
	if(member == null){
		msg = "���� �� �̿��� �ּ���";
	}else{
		// ���������� ��й�ȣ�� �ٸ���
		if(!member.getU_pw().equals(u_pw)){
			msg = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
		}else{
			// ��� ���ǿ� �����Ҷ�
			msg = member.getU_nick() + "�� ȯ���մϴ�.";
			nextPage = "loginSuccess.jsp";
			// ���ǿ� �α��� ����(���̵�, ��й�ȣ) ����
			session.setAttribute("member", member);
		}
	}
	session.setAttribute("msg", msg);
%>
<jsp:forward page="<%= nextPage %>" />
