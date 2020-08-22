<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="kr.ac.green.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	Vector<Doc> list = (Vector<Doc>)application.getAttribute("docList");
	
	Doc doc = list.get(list.indexOf(new Doc(num))); 
	
%>


<fieldset>
	<legend>��ȭ��</legend>
	<!-- ����� �Է°� -->
	<form id="modifyForm" method="post">
		�۹�ȣ : <input type="text" name="num" placeholder="��ȣ" value="<%= doc.getNum()%>" readonly />
		<br/><br/> 
		���� : <input type="text" name="title" placeholder="����" value="<%= doc.getTitle() %>" />
		<br/><br/> 
		�ۼ��� : <input type="text" name="writer" placeholder="�ۼ���" value="<%=doc.getWriter() %>" readonly />
		<br/><br/>
		���� : <br>
		<textarea name="contents" rows="5" ><%= doc.getContents() %></textarea>
		<br><br/>
		��¥ : <input type="text" name="date" placeholder="��¥" value="<%= doc.getDate() %>" />
		<br/><br/>
		��й�ȣ : <input type="password" name="pw" placeholder="��й�ȣ" />
		<br/><br/>
		<input type="button" value="��ϰ���" onclick="goList()" />
		<input type="button" value="����" onclick="todo('update')"/>
		<input type="button" value="����" onclick="todo('delete')"/>
	</form> 
</fieldset> 
<script>
	function goList(){ 
		location.href ="template.jsp";
	}
	
	function todo(){
		var what = arguments[0];
		
		var modifyForm = document.getElementById("modifyForm");
		
		modifyForm.action = what + ".jsp";
		modifyForm.submit();
	}
</script>

 
