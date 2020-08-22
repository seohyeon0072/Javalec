<!--  
JSP Model 1 ���

-data => application 


-->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.Vector" %>
    <%@ page import="kr.ac.green.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list.jsp</title>
</head>
<body>
<%
	// �⺻ �޽���
	String msg = "�������";
	// delete���� �������� �޾ƿ�
	Object o = session.getAttribute("msg");
	// ������ ���� �޽���
	if(o != null){
		msg = o.toString();
	}
	// �⺻ �������� �޾ƿ� �޽��� ����
	session.removeAttribute("msg");
%>
<div>message : <%= msg %></div>
<a href="write.jsp">�� ���</a>
<hr>
<table>
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>�۾���</th>
		<th>�ۼ���</th>
	</tr>
	<%
		Vector<Article> list = (Vector<Article>)application.getAttribute("list");
	
		if(list == null || list.size() == 0){
			
	%>
	<tr>
		<th colspan="4">��ϵ� ���� �����ϴ�.</th>
	</tr>
	<%
		} else {
			for(int i = list.size()-1; i>=0; i--){
				Article temp = list.get(i);
	%>
	<!-- onclick ������ �����ϸ� -->
	<tr onclick="select(<%=temp.getNum()%>)">
		<td><%= temp.getNum() %></td>
		<td><%= temp.getTitle() %></td>
		<td><%= temp.getWriter() %></td>
		<td><%= temp.getDateString() %></td>
	</tr>
	<%
			} 
		}
	%>
</table>
<form id="hiddenForm" name="hiddenForm" action="readArticle.jsp">
	<input type="hidden" name="num" />
</form>
<script>

// 	function select(num){
// 		var myForm = document.getElementById("hiddenForm");
// 		myForm.num.value = num;
// 		myForm.submit();
// 	}

	function select(num){
// 		var myForm = document.forms["hiddenForm"]
		var myForm = document.hiddenForm;
		myForm.num.value = num;
		myForm.submit();
	}	

// 	alert("1" == 1);
// 	alert("2" === 2);

	// �Ű������� ���ٰ� �ؼ� ���°��� �ƴ�(�ڹٽ�ũ��Ʈ�� ���û���)
// 	function select(num){
// 		location.href="readArticle.jsp?num=" + num;
// 	}	
</script>
</body>
</html>