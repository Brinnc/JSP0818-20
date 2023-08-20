<%@page import="org.sp.model1board.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@page import="org.sp.model1board.model.GalleryDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%! 
	GalleryDAO galleryDAO=new GalleryDAO();
	
%>
<% 
	List<Gallery> galleryList=galleryDAO.selectAll();

	int totalRecord=galleryList.size(); //총 레코드 수
	int pageSize=10; //한 페이지 당 보여질 레코드 수
	
	//총 페이지 수 구하는 공식***
	//int로 계산 시(정수의 계산), 나눗셈의 나머지 즉 실수는 보존되지 않음 -> float(실수의 계산)자료형으로 변경
	//단, 최종적으로 값을 픽스할 때는 정수형으로 다시 치환해야함(:페이징처리)
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize); 
	//Math.ceil() : 해당 값의 바로 위에 있는 정수값
	
	int blockSize=10; //하단 페이징처리에서 블럭당 보여질 범위
	int currentPage=1; //현재 보고있는 페이지 (초기값은 디폴트 1페이지)
	
	//만약 파라미터값이 넘어오는 경우라면, 즉 페이지 링크를 통해 접근하면
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int firstPage=currentPage-((currentPage-1)%blockSize); //블럭 당 반복문의 시작 페이지
	int lastPage=firstPage+(blockSize-1); //블럭 당 반복문의 마지막 페이지
	
	int curPos=(currentPage-1)*pageSize; //페이지 당 보여질 List의 인덱스
	int num=totalRecord-curPos; //페이지 당 시작번호
	
%>
<%="totalRecord는 "+totalRecord %><br>
<%="pageSize는 "+pageSize %><br>
<%="totalPage는 "+totalPage %><br> 
<%="blockSize는 "+blockSize %><br> 
<%="currentPage는 "+currentPage %><br> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a{
	text-decoration: none; 
}

/*선택된 페이지에 효과 정의*/
.numStyle{
	font-weight: bold;
	font-size: 28px;
	color: red;
}

</style>
</head>
<body>


	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
			<th>다운로드</th>
		</tr>
		<% for(int i=1; i<=pageSize; i++){ %>
		<%if(num<1)break; %>
		<%Gallery gallery=galleryList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><img src="/data/<%=gallery.getFilename()%>" width="35px"></td>
			<td><%=gallery.getTitle() %></td>
			<td><%=gallery.getWriter() %></td>
			<td><%=gallery.getRegdate() %></td>
			<td><%=gallery.getHit() %></td>
			<td><a href="/gallery/download?filename=<%=gallery.getFilename() %>"><%=gallery.getFilename() %></a></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="7" align="center">
				<%if(firstPage-1>0){ %>
					<a href="/gallery/list.jsp?currentPage=<%=firstPage-1 %>">◀</a>
				<%}else{ %>
					<a href="javascript:alert('첫 페이지임');">◀</a>
				<%} %>
				
				<%for(int i=firstPage; i<=lastPage; i++){ %>
					<%if(i>totalPage)break; %>
					<a <%if(currentPage==i){ %>class="numStyle"<% } %> href="/gallery/list.jsp?currentPage=<%=i %>">[<%=i %>]</a>
				<%} %>
				<%if(lastPage+1>totalPage){ %>
					<a href="javascript:alert('마지막 페이지임');">▶</a>
				<%}else{%>
					<a href="/gallery/list.jsp?currentPage=<%=lastPage+1 %>">▶</a>
				<%} %>
			</td>
		</tr>
		
		<tr>
			<td colspan="7"">
				<button onClick="location.href='/gallery/registform.jsp';">글쓰기</button>
			</td>
		</tr>

	</table>

</body>
</html>
