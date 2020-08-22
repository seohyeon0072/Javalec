<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.ac.green.*" %>
<%
	Vector<Doc> docList = (Vector<Doc>)application.getAttribute("docList");
	if(docList == null){
		docList = new Vector<Doc>();
	}
	
	Collections.sort(docList);
	
	int pageNum = 1;
	String paramPageNum = request.getParameter("pageNum");
	if(paramPageNum != null){
		pageNum = Integer.parseInt(paramPageNum);
	}
	
	int size = docList.size();
	int perPage = 3;
	int pageCount = size / perPage;
	if((size % perPage) != 0){
		pageCount++;
	}
	
	int start = (pageNum - 1) * perPage;
	int end = start + perPage;
	if(end > size){
		end = size;
	}
	
	List<Doc> list = docList.subList(start, end);

%>

<table id="docList">
	<caption> - 글목록 - </caption>
	<thead>
		<tr id="docListHeader">
			<th id="docNum">번호</th>
			<th id="docContents">제목</th>
			<th id="docWriter">작성자</th>
			<th id="docDate">작성일</th>
		</tr>
	</thead>
	<%
		if(size != 0){
	%>
	<tfoot>
		<tr>
			<td colspan="4">
				<%
					for(int i = 1; i <= pageCount; i++){
						if(i == pageNum){
				%>
					[<b><%= i %></b>] 
				<%
					} else {
				%>	
					[<a href="template.jsp?contentsPage=list&pageNum=<%=i %>"><%= i %></a>]
				<%
						}
					}
				%>
			</td>
		</tr>
	</tfoot>
	<% 
		}
	%>
	<tbody>
		<%
			if(size == 0){
		%>
			<tr>
				<td colspan="4">no data</td>
			</tr>
		<%
			}else{
				for(int num = 0; num<list.size(); num++){				
				Doc doc = list.get(num);					
		%>
				<tr onclick="select(<%=doc.getNum()%>)">
				<td><%= doc.getNum() %></td>
				<td><%= doc.getTitle() %></td>
				<td><%= doc.getWriter() %></td>
				<td><%= doc.getDate() %></td>
			</tr>
			
		<%
				}
			}
		%>	
	</tbody>
	
</table>
<form id="hiddenForm" name="hiddenForm" action="showDetail.jsp">
	<input type="hidden" name="num" />
</form>
<script>

	function select(num){
		var myForm = document.hiddenForm;
		myForm.num.value = num;
		myForm.submit();
	}	

</script>