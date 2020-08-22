<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="org.doo.*"%>
<%
	// ���ǿ� ����� msg
	String msg = (String)session.getAttribute("msg");
	if(msg == null){
		msg = "��� �׸��� �ʼ��Է��Դϴ�.";
	}
	
	// ���ǿ� ����Ǿ��ִ� member�� ������ ��� ��ü
	Member member = (Member)session.getAttribute("tempMember");
	// ó���� �α��� ������ �Ѿ�� ���� ����־���ϱ� ������ getBlankMember()�޼��带 ���
	if(member == null){
		// ���� �� ���̶�� getBlacnkMember()�� ȣ���Ͽ� ""���� �ο�
		member = MemberManager.getBlankMember();
	}
	// ���� ����� ����
	session.removeAttribute("tempMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// ȸ������
	function checkAndMove(){
		// joinForm�� form�� name��(�������� joinForm)
		var obj = document.joinForm;
		// ���۰� true
		var flag = true;
		// ���� : true, obj�ȿ� ��ȿ�� ��
		for(var i = 0; flag && i<obj.length; i++){
			// joinForm���� Ÿ�� ���� �ҹ��ڷ� ���� -> Ÿ�԰��� button�ƴ� ���
			if(obj[i].type.toLowerCase() != "button"){
				// button������ Ÿ�Ե��� ���� ������ ���
				if(obj[i].value.trim().length == 0){
					// id�� msg�� �±׿�  �˸��� + ������ �ִ� �±� �̸��� ���
					// innerHTML -> 
					document.getElementById("msg").innerHTML =
						"�ʼ��Է��׸� ���� : " + obj[i].title;
					// ������ ������ �ٽ� ����˻��Ϸ� ��ȯ
					flag = false;
				}
			}
		}
		// ������ ������ 
		if(flag){
			// form�� �Էµ� ��й�ȣ�� ���Է� ���� ��
			if(obj.u_pw.value != obj.u_re.value){
				// ��ġ���� ������ �˸� ���
				document.getElementById("msg").innerHTML = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				// �ٽ� ��ȯ
				flag = false;
			}
		}
		
		// ��ȿ�� �˻� ������ ����
		if(flag){
			obj.method = "post";
			obj.action = "doJoin.jsp";
			obj.submit();
		}
	}
	 
	// ȸ������ ���
	function cancelJoin(){
		// ��� �޽��� ������ �̵�
		location.href="cancelJoin.jsp";
	}
</script>
</head>
<body>
	<p class="titleStr">
		Join Member
	</p>
	<form name="joinForm">
		<div class="centerBox">
			<label for="u_id">ID :</label>
			<!-- value�� �� : �Է� ���н� ������ ���� �����ϱ����ؼ� ���ǿ��� �޾ƿ� -->
			<input type="text" name="u_id" title="ID" value="<%=member.getU_id() %>" /><br/>
			<label for="u_pw">PW :</label>
			<input type="text" name="u_pw" title="PASSWORD" /><br/>
			<label for="u_re">RETRY :</label>
			<input type="text" name="u_re" title="RETRY" /><br/>
			<label for="u_name">NAME :</label>
			<input type="text" name="u_name" title="NAME" value="<%=member.getU_name() %>" /><br/>
			<label for="u_nick">NICK :</label>
			<input type="text" name="u_nick" title="NICKNAME" value="<%=member.getU_nick() %>" /><br/>
			<div class="btns">
				<input type="button" value="ȸ������" onclick="checkAndMove()" />
				<input type="button" value="���" onclick="cancelJoin()" />
			</div>
		</div>
		<div class="msgBox" id="msg">
			<%= msg %>
		</div>
	</form>
</body>
</html>
<%-- ���ǿ� ��� ��� msg ���� --%>
<%@ include file="removeMessage.jspf" %>