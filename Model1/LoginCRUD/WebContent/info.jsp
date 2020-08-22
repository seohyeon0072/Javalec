<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="checkSession.jspf" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script>
	// ȸ������ ����
	function doUpdate(){
		var obj = document.infoForm;
		
		var flag = true;
		for(var i = 0; flag && i<obj.length; i++){
			if(obj[i].type.toLowerCase() != "button"){
				if(obj[i].value.trim().length == 0){
					document.getElementById("msg").innerHTML =
						"�ʼ��Է��׸� ���� : " + obj[i].title;
					flag = false;
				}
			}
		}
		
		if(flag){
			if(obj.u_pw.value != obj.u_re.value){
				document.getElementById("msg").innerHTML ="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				flag = false;
			}
		}
		
		if(flag){
			obj.method = "post";
			obj.action = "doUpdate.jsp";
			obj.submit();
		}
	}
	
	// �������� ���
	function cancelInfo(){
		location.href="cancelInfo.jsp";
	}
	
	// ȸ��Ż��
	function deleteMember(){
		/*
			### �ڹ�ũ��ũ��Ʈ���� false�� ó���Ǵ� �� ###
			null
			undefined(�ڹٽ�ũ��Ʈ���� ���� �ϰ� ���� ����)
			+0, -0 (0�� ������ ���ڴ� �� true)
			Nan (���ڰ� �ƴϴ�.)
			false 
		*/
		if(window.confirm("���� Ż���Ͻðڽ��ϱ�?")){
			location.href="doDelete.jsp";
		}
	}
</script>
</head>
<body>
	<p class="titleStr">
		Info
	</p>
	<form name="infoForm">
		<div class="centerBox">
			<label for="u_id">ID :</label> <input type="text" name="u_id" value="<%=member.getU_id()%>" readonly /><br/>
			<label for="u_pw">PW :</label> <input type="password" name="u_pw" title="Password" /><br/>
			<label for="u_re">Retry :</label> <input type="password" name="u_re" title="Retry" /><br/>
			<label for="u_name">Name :</label> <input type="text" name="u_name" title="Name" value="<%=member.getU_name()%>" readonly /><br/>
			<label for="u_nick">Nick :</label> <input type="text" name="u_nick" title="Nickname" value="<%=member.getU_nick()%>" /><br/>				
			<div class="btns"> 
				<input type="button" value="����" onclick="doUpdate()" />
				<input type="button" value="���" onclick="cancelInfo()"/> 
				<input type="button" value="ȸ��Ż��" onclick="deleteMember()"/>
			</div>
		</div>
		<div class="msgBox" id="msg">
			<%= session.getAttribute("msg") %>
		</div>		 
	</form>
</body>
</html>
<%@ include file="removeMessage.jspf" %>