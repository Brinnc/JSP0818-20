<%@page import="org.sp.model1board.util.PoolManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
	//JNDI를 이용하여, 자바 코드 밖에서 설정한 자원을 접근해 사용해봄
	//PoolManager pool=new PoolManager();

%>        
<% 
	//이름 검색 객체 생성
	//Connection con=pool.getConnection(); //대여
	
	//out.print(con);
	//pool.release(con); //반납
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>