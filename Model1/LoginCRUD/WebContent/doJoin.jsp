<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.doo.*" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("euc_kr");
%>
<!-- �ڹٺ󿡼� Member��ü ��� -->
<jsp:useBean id="member" class="org.doo.Member" />
<!-- * -> ��� ���� setter(�Է��� ���� name �� = ������� �� -->
<jsp:setProperty property="*" name="member"/> 

<%
	// ���� ����
	String msg ="ȸ������ ����";
	String nextPage = "loginForm.jsp";
	// ���� ���� (addMember()���� �ߺ��� ���̵� ������ false�� ��ȯ !false
	if(!MemberManager.addMember(application, member)){
		msg = "�̹� �����ϴ� ���̵� �Դϴ�.";
		// �ߺ��� ������ ������ ������ �ٽ� ���󺹱� ��Ų��.(submit()�� �ʱ�ȭ �Ǳ⶧��)
		session.setAttribute("tempMember", member);
		// ���н� �ٽ� Joinâ���� �̵�
		nextPage = "join.jsp";
	}
	session.setAttribute("msg", msg);
	response.sendRedirect(nextPage);
%>